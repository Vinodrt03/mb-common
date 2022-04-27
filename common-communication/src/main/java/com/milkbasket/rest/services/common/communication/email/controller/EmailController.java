package com.milkbasket.rest.services.common.communication.email.controller;

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
import com.milkbasket.core.framework.utility.JSONUtils;
import com.milkbasket.rest.services.common.communication.email.bean.EmailRequestBean;
import com.milkbasket.rest.services.common.communication.email.service.EmailService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
/**
 * <p>
 * EmailController class.
 * </p>
 *
 * @author NitinKhaitan
 * @version $Id: $Id
 */
@Api(tags = "Emails Services")
public class EmailController {

	private static final AppLogger _LOGGER = LoggingManager.getLogger(EmailController.class);

	@Autowired
	private EmailService emailService;

	/**
	 * <p>
	 * requestSend.
	 * </p>
	 *
	 * @param email
	 *            a
	 *            {@link com.milkbasket.rest.services.common.communication.email.bean.EmailRequestBean}
	 *            object.
	 * @return a {@link java.lang.Long} object.
	 */
	@ApiOperation(value = "Request send for email", nickname = "sendEmail", notes = "<br/><strong>NOTES:</strong>"
			+ "<br/>  # Id should not be provided at the time of creation" + "<br/>  # On success, save should return updated record with id")
	@PostMapping("/communication/emails")
	@ResponseStatus(HttpStatus.CREATED)
	public Long send(@Valid @RequestBody final EmailRequestBean email) {
		emailService.sendAsync(email);
		return 1L;
	}

	@ApiOperation(value = "Handle message comming via sns subscription [B2B]", nickname = "processEmailSubscription")
	@PostMapping("/communication/subscription/email")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@SnsSubscription
	@NewRelicIgnoreTransaction
	public void send(final HttpServletRequest httpServletRequest, @RequestBody final String payload) {
		final EmailRequestBean bean = JSONUtils.jsonToObject(payload, EmailRequestBean.class);
		if (bean != null) {
			emailService.send(bean);
		} else {
			_LOGGER.info("Email Request found null or not able to parse request :" + payload);
		}
	}

}
