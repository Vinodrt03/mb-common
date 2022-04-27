package com.milkbasket.rest.services.common.communication.push.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.milkbasket.core.framework.common.anotation.NewRelicIgnoreTransaction;
import com.milkbasket.core.framework.common.anotation.SnsSubscription;
import com.milkbasket.core.framework.common.utils.ContextUtils;
import com.milkbasket.core.framework.logging.core.AppLogger;
import com.milkbasket.core.framework.logging.manager.LoggingManager;
import com.milkbasket.core.framework.params.utils.ParamsUtils;
import com.milkbasket.core.framework.utility.JSONUtils;
import com.milkbasket.core.framework.websupport.base.BaseController;
import com.milkbasket.rest.services.common.communication.bean.ProcessFailingBasketBean;
import com.milkbasket.rest.services.common.communication.bean.UnfulfilledBasketBean;
import com.milkbasket.rest.services.common.communication.push.bean.PushRequestBean;
import com.milkbasket.rest.services.common.communication.push.entity.PushConstants.Type;
import com.milkbasket.rest.services.common.communication.push.service.PushService;
import com.milkbasket.rest.services.common.communication.push.validator.PushValidator;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
/**
 * <p>
 * PushController class.
 * </p>
 *
 * @author Neeraj
 * @version $Id: $Id
 */
@Api(tags = "Push Services")
public class PushController implements BaseController {

	private static final AppLogger _LOGGER = LoggingManager.getLogger(PushController.class);

	private static final Long TEST_USER_ID = Long.valueOf(ParamsUtils.getParam("INTERCOM_MSG_TEST_USER_ID", "4"));

	@Autowired
	private PushService pushService;

	@Autowired
	private PushValidator pushValidator;

	@ApiOperation(value = "create new Push request", notes = "<br/><strong>NOTES:</strong>"
			+ "<br/>  # Id should not be provided at the time of creation" + "<br/>  # On success, save should return updated record with id"
			+ "<br/>  # Get call will return value if type has valid value", nickname = "sendPush")
	@PostMapping("/communication/push")
	@ResponseStatus(HttpStatus.CREATED)
	public Long send(@Valid @RequestBody final PushRequestBean pushBean, final org.springframework.validation.Errors errors) {
		pushValidator.validateOnSave(pushBean, errors);
		checkError(errors);
		pushService.sendAsync(pushBean);
		return 1L;
	}

	/**
	 * <p>
	 * isPushUser.
	 * </p>
	 *
	 * @param userId
	 *            a {@link java.lang.Long} object.
	 * @return a {@link java.lang.Boolean} object.
	 */
	@ApiOperation(value = "Check is valid user", nickname = "isValidPushUser")
	@GetMapping("/communication/push/is-valid-user/{userId}")
	public Boolean isPushUser(@PathVariable final Long userId) {
		return pushService.isPushUser(userId);
	}

	/**
	 * <p>
	 * getPushUsers.
	 * </p>
	 *
	 * @param userId
	 *            a {@link java.lang.Long} object.
	 * @return a {@link java.lang.Boolean} object.
	 */
	@ApiOperation(value = "Get valid user for push notifications", nickname = "getValidPushUsers")
	@GetMapping("/communication/push/is-valid-users")
	public Set<Long> getPushUsers(@RequestParam final List<Long> userId) {
		return pushService.getPushUsers(userId);
	}

	@ApiOperation(value = "Handle message comming via sns subscription [B2B]", nickname = "processPushSubscription")
	@PostMapping("/communication/subscription/push")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@SnsSubscription
	@NewRelicIgnoreTransaction
	public void send(final HttpServletRequest httpServletRequest, @RequestBody final String payload) {
		final PushRequestBean bean = JSONUtils.jsonToObject(payload, PushRequestBean.class);
		if (bean != null) {
			pushService.send(bean);
		} else {
			_LOGGER.info("Push Request found null or not able to parse request :" + payload);
		}
	}

	@ApiOperation(value = "Handle failing basket push message comming via sns subscription.[B2B]", nickname = "processFailingBasketPush")
	@PostMapping("/communication/subscription/failing-basket-push")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@SnsSubscription
	@NewRelicIgnoreTransaction
	public void sendFailingBasketPush(final HttpServletRequest httpServletRequest, @RequestBody final String payload) {
		final ProcessFailingBasketBean failingBasket = JSONUtils.jsonToObject(payload, ProcessFailingBasketBean.class);
		if (failingBasket != null && failingBasket.getUserFailingCount() < failingBasket.getMaxFailingCount()) {
			final PushRequestBean pushRequestBean = new PushRequestBean();
			pushRequestBean.setUserIds(Arrays.asList(failingBasket.getFailingBasket().getUserId()));
			pushRequestBean.setMessageLang("FAILING_BASKET_PUSH");
			pushRequestBean.setLink("milkbasket://app/topup");
			pushRequestBean.setType(Type.MESSAGE);
			pushService.sendAsync(pushRequestBean);
		} else {
			_LOGGER.info("Failing Basket PUSH Request found null or not appropriate :" + payload);
		}
	}

	@ApiOperation(value = "Handle unfulfilled basket push message comming via sns subscription.[B2B]", nickname = "processUnfulfilledPush")
	@PostMapping("/communication/subscription/unfulfilled-basket-push")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@SnsSubscription
	@NewRelicIgnoreTransaction
	public void sendUnfulfilledBasketPush(final HttpServletRequest httpServletRequest, @RequestBody final String payload) {
		final UnfulfilledBasketBean unfulfilledBasketBean = JSONUtils.jsonToObject(payload, UnfulfilledBasketBean.class);
		if (unfulfilledBasketBean != null) {
			final Map<String, Object> context = new HashMap<>();
			context.put("FIRST_NAME", unfulfilledBasketBean.getFirstName());
			context.put("PRODUCT_NAME", unfulfilledBasketBean.getProductName());
			context.put("UNIT", unfulfilledBasketBean.getProductQuantity());
			final PushRequestBean pushRequestBean = new PushRequestBean();
			final Long userId = ContextUtils.isProfileProd() ? unfulfilledBasketBean.getUserId() : TEST_USER_ID;
			pushRequestBean.setUserIds(Arrays.asList(userId));
			pushRequestBean.setMessageLang("delete_unfulfilled_basket");
			pushRequestBean.setMessageData(context);
			pushRequestBean.setLink(unfulfilledBasketBean.getLink());
			pushRequestBean.setType(Type.MESSAGE);
			pushService.sendAsync(pushRequestBean);
		} else {
			_LOGGER.info("Unfulfilled Basket PUSH Request found null or not appropriate :" + payload);
		}
	}
}
