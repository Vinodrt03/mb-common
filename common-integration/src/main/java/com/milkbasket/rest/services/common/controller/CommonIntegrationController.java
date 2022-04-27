package com.milkbasket.rest.services.common.controller;

import javax.servlet.http.HttpServletRequest;

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
import com.milkbasket.core.framework.utility.JSONUtils;
import com.milkbasket.core.framework.websupport.base.BaseController;
import com.milkbasket.rest.services.common.service.CommomIntegrationService;
import com.milkbasket.rest.shared.bean.PushOrSmsRequestBean;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
/**
 * <p>
 * CommonIntegrationController class.
 * </p>
 *
 * @author Sanjeev Jha
 * @version $Id: $Id
 */
@Api(tags = "Common Integration Services")
public class CommonIntegrationController implements BaseController {

	private static final AppLogger _LOGGER = LoggingManager.getLogger(CommonIntegrationController.class);

	@Autowired
	private CommomIntegrationService commomIntegrationService;

	@ApiOperation(value = "Handle push or sms message comming via sns subscription [B2B]", nickname = "processPushOrSmsSubscription")
	@PostMapping("/communication/subscription/push-sms")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@SnsSubscription
	@NewRelicIgnoreTransaction
	public void send(final HttpServletRequest httpServletRequest, @RequestBody final String payload) {

		final PushOrSmsRequestBean bean = JSONUtils.jsonToObject(payload, PushOrSmsRequestBean.class);

		if (bean != null) {
			commomIntegrationService.sendPushOrSms(bean);
		} else {
			_LOGGER.info("Push/SMS Request found null or not able to parse request :" + payload);
		}

	}

}
