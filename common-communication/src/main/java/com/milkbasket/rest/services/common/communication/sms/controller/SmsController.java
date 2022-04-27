package com.milkbasket.rest.services.common.communication.sms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.milkbasket.core.framework.common.anotation.NewRelicIgnoreTransaction;
import com.milkbasket.core.framework.common.anotation.SnsSubscription;
import com.milkbasket.core.framework.logging.core.AppLogger;
import com.milkbasket.core.framework.logging.manager.LoggingManager;
import com.milkbasket.core.framework.utility.CollectionUtils;
import com.milkbasket.core.framework.utility.JSONUtils;
import com.milkbasket.rest.services.common.communication.bean.ProcessFailingBasketBean;
import com.milkbasket.rest.services.common.communication.sms.bean.SmsRequestBean;
import com.milkbasket.rest.services.common.communication.sms.service.SmsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
/**
 * <p>
 * SmsController class.
 * </p>
 *
 * @author NitinKhaitan
 * @version $Id: $Id
 */
@Api(tags = "Sms Services")
public class SmsController {

	private static final AppLogger _LOGGER = LoggingManager.getLogger(SmsController.class);

	@Autowired
	private SmsService smsService;

	@ApiOperation(value = "Send a sms", nickname = "sendSms", notes = "<br/><strong>NOTES:</strong>"
			+ "<br/>  # Id should not be provided at the time of creation" + "<br/>  # On success, save should return updated record with id")
	@PostMapping("/communication/sms")
	@ResponseStatus(HttpStatus.CREATED)
	public Long send(@Valid @RequestBody final SmsRequestBean sms) {
		smsService.sendAsync(sms);
		return 1L;
	}

	@ApiOperation(value = "Handle message comming via sns subscription [B2B]", nickname = "processSmsSubscription")
	@PostMapping("/communication/subscription/sms")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@SnsSubscription
	@NewRelicIgnoreTransaction
	public void send(final HttpServletRequest httpServletRequest, @RequestBody final String payload) {
		final SmsRequestBean bean = JSONUtils.jsonToObject(payload, SmsRequestBean.class);
		if (bean != null) {
			smsService.send(bean);
		} else {
			_LOGGER.info("SMS Request found null or not able to parse request :" + payload);
		}
	}

	@ApiOperation(value = "Handle OTP call (otp over voice message) via sns subscription [B2B]", nickname = "processOtpCall")
	@PostMapping("/communication/subscription/otp-call")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@SnsSubscription
	@NewRelicIgnoreTransaction
	public void sendOtpOverCall(final HttpServletRequest httpServletRequest, @RequestBody final String payload) {
		final SmsRequestBean bean = JSONUtils.jsonToObject(payload, SmsRequestBean.class);
		if (bean != null && CollectionUtils.isNotEmpty(bean.getNumbers())) {
			smsService.sendOtpViaCall(bean.getNumbers().get(0));
		} else {
			_LOGGER.info("OTP via call Request found null or not able to parse request :" + payload);
		}
	}

	@ApiOperation(value = "Handle failing basket sms message comming via sns subscription.[B2B]", nickname = "processFailingBasketSms")
	@PostMapping("/communication/subscription/failing-basket-sms")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@SnsSubscription
	@NewRelicIgnoreTransaction
	public void sendFailingBasketSms(final HttpServletRequest httpServletRequest, @RequestBody final String payload) {
		final ProcessFailingBasketBean failingBasket = JSONUtils.jsonToObject(payload, ProcessFailingBasketBean.class);
		if (failingBasket != null && failingBasket.getUserFailingCount() < failingBasket.getMaxFailingCount()) {
			final SmsRequestBean smsRequestBean = new SmsRequestBean();
			smsRequestBean.setTemplateName("FAILING_BASKET_SMS");
			smsRequestBean.setUserId(failingBasket.getFailingBasket().getUserId());
			final List<String> numbers = new ArrayList<>();
			numbers.add(failingBasket.getFailingBasket().getCustomerPhone());
			smsRequestBean.setNumbers(numbers);
			smsService.sendAsync(smsRequestBean);
		} else {
			_LOGGER.info("Failing Basket SMS Request found null or not appropriate :" + payload);
		}
	}
}
