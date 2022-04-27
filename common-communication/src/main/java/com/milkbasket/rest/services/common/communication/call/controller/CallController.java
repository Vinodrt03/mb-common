/**
 *
 */
package com.milkbasket.rest.services.common.communication.call.controller;

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
import com.milkbasket.rest.services.common.communication.call.bean.IvrCallbackResponse;
import com.milkbasket.rest.services.common.communication.call.bean.IvrRequestBean;
import com.milkbasket.rest.services.common.communication.call.service.CallLogService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
/**
 * @author rajneshrajput
 *
 */
@Api(tags = "Call Services")
public class CallController {

	private static final AppLogger _LOGGER = LoggingManager.getLogger(CallController.class);

	@Autowired
	private CallLogService callService;

	/**
	 * <p>
	 * requestSend.
	 * </p>
	 *
	 * @param call
	 * @return a {@link java.lang.Long} object.
	 */
	@ApiOperation(value = "Make a call", nickname = "makeIvrCall", notes = "<br/><strong>NOTES:</strong>"
			+ "<br/>  # Id should not be provided at the time of creation" + "<br/>  # On success, save should return updated record with id")
	@PostMapping("/communication/ivr/call")
	@ResponseStatus(HttpStatus.CREATED)
	public Long send(@Valid @RequestBody final IvrRequestBean bean) {
		callService.makeAsyncIvr(bean);
		return 1L;
	}

	@ApiOperation(value = "Handle message comming via sns subscription [B2B]", nickname = "processIvrCallSubscription")
	@PostMapping("/communication/subscription/ivr/call")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@SnsSubscription
	@NewRelicIgnoreTransaction
	public void send(final HttpServletRequest httpServletRequest, @RequestBody final String payload) {
		final IvrRequestBean bean = JSONUtils.jsonToObject(payload, IvrRequestBean.class);
		if (bean != null) {
			callService.makeIvr(bean);
		} else {
			_LOGGER.info("Call Request found null or not able to parse request :" + payload);
		}
	}

	/**
	 * <p>
	 * requestSend.
	 * </p>
	 *
	 * @param call
	 * @return a {@link java.lang.Long} object.
	 */
	@ApiOperation(value = "Callback for ivr call", nickname = "ivrCallBack", notes = "<br/><strong>NOTES:</strong>"
			+ "<br/>  # Id should not be provided at the time of creation" + "<br/>  # On success, save should return updated record with id")
	@PostMapping("/communication/ivr/callback")
	@ResponseStatus(HttpStatus.CREATED)
	public Long exotelCallback(@RequestBody final IvrCallbackResponse bean) {
		callService.ivrCallback(bean);
		return 1L;
	}
}
