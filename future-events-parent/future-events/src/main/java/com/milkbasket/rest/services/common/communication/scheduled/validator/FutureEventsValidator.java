package com.milkbasket.rest.services.common.communication.scheduled.validator;

import java.util.List;

import com.milkbasket.core.framework.utility.JSONUtils;
import com.milkbasket.rest.services.communication.entity.FutureEventsConstants;
import com.milkbasket.rest.services.communication.entity.FutureEventsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.milkbasket.core.framework.logging.core.AppLogger;
import com.milkbasket.core.framework.logging.manager.LoggingManager;
import com.milkbasket.core.framework.utility.CollectionUtils;
import com.milkbasket.core.framework.validator.provider.utils.ValidationUtils;
import com.milkbasket.rest.services.common.communication.scheduled.bean.FutureEventsRequestBean;
import com.milkbasket.rest.services.common.communication.scheduled.service.FutureEventsService;

/**
 * The Class FutureEventsValidator.
 */
@Component
/**
 * <p>
 * FutureEventsValidator class.
 * </p>
 *
 * @author Neeraj
 * @version $Id: $Id
 */
public class FutureEventsValidator {

	AppLogger _LOGGER = LoggingManager.getLogger(FutureEventsValidator.class);

	@Autowired
	private FutureEventsService futureEventsService;

	public void validateOnSave(final FutureEventsRequestBean scheduledRequestBean, final FutureEventsEntity scheduledCommunicationEntity) {
		final StringBuilder productName = new StringBuilder();
		commonMandatoryFields(scheduledRequestBean, productName);
		if (null != scheduledRequestBean.getType() && "process_sms".equals(scheduledRequestBean.getType().toLowerCase())) {
			if (null == scheduledRequestBean.getNumbers()) {
				productName.append("Numbers field cannot be null, ");
			} else if (!ValidationUtils.isValidPhones(scheduledRequestBean.getNumbers())) {
				productName.append("Numbers field should be valid, ");
			}
		}
		if (null != scheduledRequestBean.getType() && "process_push".equals(scheduledRequestBean.getType().toLowerCase())) {
			if (null == scheduledRequestBean.getUserIds()) {
				productName.append("UserIds field cannot be null, ");
			}
			if (null == scheduledRequestBean.getPushType()) {
				productName.append("PushType field cannot be null, ");
			}
		}

		if (null != scheduledRequestBean.getType() && "process_event".equals(scheduledRequestBean.getType().toLowerCase())) {
			if (null == scheduledRequestBean.getContextData() || scheduledRequestBean.getContextData().size() == 0) {
				productName.append("Context Data cannot be null or empty, ");
			}
		}

		if (productName.length() > 0) {
			scheduledCommunicationEntity.setError(productName.toString());
			scheduledCommunicationEntity.setStatus(FutureEventsConstants.CommunicationStatus.Failed.getValue());
		}
	}

	private void commonMandatoryFields(final FutureEventsRequestBean scheduledRequestBean, final StringBuilder productName) {
		if (null == scheduledRequestBean.getSource()) {
			productName.append("Source field cannot be null, ");
		}
		if (null == scheduledRequestBean.getIdentifier()) {
			productName.append("Identifier field cannot be null, ");
		}
		if (null == scheduledRequestBean.getTemplateName()) {
			productName.append("TemplateName/messageLang field cannot be null, ");
		}
		if (null == scheduledRequestBean.getType()) {
			productName.append("Type field cannot be null, ");
		}
		if (null == scheduledRequestBean.getDateTime()) {
			productName.append("DateTime field cannot be null, ");
		}
	}

	public boolean isRepeatCommunication(final FutureEventsRequestBean scheduledRequestBean) {
		if(scheduledRequestBean.getType().equals(FutureEventsConstants.Type.process_event.toString()))
			return false;
		List<FutureEventsEntity> entityList = futureEventsService.findAllBySourceTypeIdentifierAndDateTime(scheduledRequestBean.getSource(),
				scheduledRequestBean.getIdentifier(), scheduledRequestBean.getDateTime(), scheduledRequestBean.getType());
		if (CollectionUtils.isNotEmpty(entityList)) {
			_LOGGER.info(
					String.format("Future Event Request skipped as a request with Source: %s, Type: %s, Identifier:%s and DateTime:%s already exists",
							scheduledRequestBean.getSource(), scheduledRequestBean.getType(), scheduledRequestBean.getIdentifier(),
							scheduledRequestBean.getDateTime()));
			return true;
		}
		return false;
	}

}