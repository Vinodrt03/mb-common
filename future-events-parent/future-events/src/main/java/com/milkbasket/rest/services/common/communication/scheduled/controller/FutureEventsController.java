package com.milkbasket.rest.services.common.communication.scheduled.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.milkbasket.core.framework.utility.DateUtils;
import com.milkbasket.rest.services.common.communication.scheduled.bean.CancelFutureEventsRequestBean;
import com.milkbasket.rest.services.communication.entity.FutureEventsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.milkbasket.core.framework.common.anotation.NewRelicIgnoreTransaction;
import com.milkbasket.core.framework.common.anotation.SnsSubscription;
import com.milkbasket.core.framework.common.utils.LoggingUtils;
import com.milkbasket.core.framework.logging.core.AppLogger;
import com.milkbasket.core.framework.logging.manager.LoggingManager;
import com.milkbasket.core.framework.utility.CollectionUtils;
import com.milkbasket.core.framework.utility.JSONUtils;
import com.milkbasket.core.framework.websupport.base.BaseController;
import com.milkbasket.rest.services.common.communication.scheduled.bean.FutureEventsRequestBean;
import com.milkbasket.rest.services.common.communication.scheduled.mapper.FutureEventsMapper;
import com.milkbasket.rest.services.common.communication.scheduled.service.FutureEventsService;
import com.milkbasket.rest.services.common.communication.scheduled.validator.FutureEventsValidator;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
/**
 * <p>
 * ScheduledCommunicationController class.
 * </p>
 *
 * @author Neeraj
 * @version $Id: $Id
 */
@Api(tags = "Future Events Services")
public class FutureEventsController implements BaseController {

	private static final AppLogger _LOGGER = LoggingManager.getLogger(FutureEventsController.class);

	@Autowired
	@SuppressWarnings("unused")
	private FutureEventsMapper futureEventsMapper;

	@Autowired
	private FutureEventsService futureEventsService;

	@Autowired
	private FutureEventsValidator futureEventsValidator;

	@ApiOperation(value = "Handle future events message coming via sns subscription.[B2B]", nickname = "sendFutureEvents")
	@PostMapping("/process/future/event-subscription")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@SnsSubscription
	@NewRelicIgnoreTransaction
	public void sendFutureEvents(final HttpServletRequest httpServletRequest, @RequestBody final String payload) {
		final FutureEventsRequestBean futureEventsRequestBean = JSONUtils.jsonToObject(payload, FutureEventsRequestBean.class);
		if (futureEventsRequestBean != null) {
			final FutureEventsEntity futureEventsEntity = new FutureEventsEntity();
			if (!futureEventsValidator.isRepeatCommunication(futureEventsRequestBean)) {
				futureEventsValidator.validateOnSave(futureEventsRequestBean, futureEventsEntity);

				final FutureEventsEntity entity = convertBeanToEntity(futureEventsEntity, futureEventsRequestBean);
				entity.setContextDataAsMap(futureEventsRequestBean.getContextData());
				entity.setNumbersAsList(futureEventsRequestBean.getNumbers());
				entity.setUserIdsAsList(futureEventsRequestBean.getUserIds());
				futureEventsService.save(entity);
			}
		} else {
			_LOGGER.info("Scheduled Communication Request found null or not appropriate :" + payload);
		}
	}

	@ApiOperation(value = "create new Future Event Request", nickname = "createFutureEvent", notes = "<br/><strong>NOTES:</strong>"
			+ "<br/>  # Id should not be provided at the time of creation" + "<br/>  # On success, save should return updated record with id"
			+ "<br/>  # Get call will return value if type has valid value")
	@PostMapping("/process/future/event")
	@ResponseStatus(HttpStatus.CREATED)
	public Long save(@Valid @RequestBody final FutureEventsRequestBean requestBean) {
		final FutureEventsEntity futureEventsEntity = new FutureEventsEntity();
		if (!futureEventsValidator.isRepeatCommunication(requestBean)) {
			futureEventsValidator.validateOnSave(requestBean, futureEventsEntity);

			final FutureEventsEntity entity = convertBeanToEntity(futureEventsEntity, requestBean);

			entity.setContextDataAsMap(requestBean.getContextData());
			entity.setNumbersAsList(requestBean.getNumbers());
			entity.setUserIdsAsList(requestBean.getUserIds());
			futureEventsService.cancelFutureEventIfItExistsOnSameDay(entity);
			final FutureEventsEntity savedEntity = futureEventsService.save(entity);
			LoggingUtils.logAdminAction(FutureEventsEntity.Events.create_future_events.toString(), "ID", entity.getId(), "Source: ",
					entity.getSource(), "Identifier: ", entity.getIdentifier());
			return savedEntity.getId();
		}
		return null;
	}

	@ApiOperation(value = "cancel Future Events Request", nickname = "cancelScheduledCommunication", notes = "<br/><strong>NOTES:</strong>")
	@PutMapping("/process/future/event/cancel")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void cancelEvents(@Valid @RequestBody final CancelFutureEventsRequestBean cancelFutureEventsRequestBean) {
		cancelFutureEvents(cancelFutureEventsRequestBean);
	}

	private void cancelFutureEvents(final CancelFutureEventsRequestBean cancelFutureEventsRequestBean){
		Date date = null;
		if (null != cancelFutureEventsRequestBean.getDate()) {
			date = DateUtils.getDate(cancelFutureEventsRequestBean.getDate());
		}
		final List<FutureEventsEntity> entityList = futureEventsService.findAllBySourceAndIdentifierAndDate(cancelFutureEventsRequestBean.getSource(),
				cancelFutureEventsRequestBean.getIdentifier(), date);
		if (CollectionUtils.isNotEmpty(entityList)) {
			futureEventsService.cancelRequests(entityList);
			LoggingUtils.logAdminAction(FutureEventsEntity.Events.cancel_future_event.toString(), "Source: ",
					cancelFutureEventsRequestBean.getSource(), ",Identifier: ", cancelFutureEventsRequestBean.getIdentifier(), ", Date: ", date);
		}
	}

	@ApiOperation(value = "Handle cancel future events request coming via sns subscription.[B2B]", nickname = "cancelFutureEvents")
	@PostMapping("/process/future/event/cancel-subscription")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@SnsSubscription
	@NewRelicIgnoreTransaction
	public void cancelEventsSubscription(HttpServletRequest httpServletRequest, @RequestBody final String payload) {
		final CancelFutureEventsRequestBean cancelFutureEventsRequestBean = JSONUtils.jsonToObject(payload, CancelFutureEventsRequestBean.class);
		if (cancelFutureEventsRequestBean != null) {
			cancelFutureEvents(cancelFutureEventsRequestBean);
		} else {
			_LOGGER.info("Handle cancel future events request found null or not appropriate :" + payload);
		}
	}

	@ApiOperation(value = "Cron Future Events Request", nickname = "futureEventsCron", notes = "<br/><strong>NOTES:</strong>")
	@PostMapping("/process/future/event/cron")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void futureEventsCron() {
		futureEventsService.sendFutureEvents();
	}
}
