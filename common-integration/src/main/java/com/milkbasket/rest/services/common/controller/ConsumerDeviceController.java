package com.milkbasket.rest.services.common.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.milkbasket.core.framework.common.advice.anotation.ConsumerAdvice;
import com.milkbasket.core.framework.common.bean.UIResponse;
import com.milkbasket.core.framework.common.utils.SessionUtils;
import com.milkbasket.core.framework.websupport.base.BaseController;
import com.milkbasket.rest.services.push.common.bean.DeviceBean;
import com.milkbasket.rest.services.push.common.bean.RegisterDeviceRequest;
import com.milkbasket.rest.services.push.common.bean.RegisterPushRequest;
import com.milkbasket.rest.services.communication.entity.DeviceConstants.Device;
import com.milkbasket.rest.services.push.common.service.DeviceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@ConsumerAdvice
/**
 * <p>
 * ConsumerDeviceController class.
 * </p>
 *
 * @author Sanjeev
 * @version $Id: $Id
 */
@Api(tags = "Push Services")
public class ConsumerDeviceController implements BaseController {

	@Autowired
	private DeviceService deviceService;

	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "Register push for device", nickname = "registerPush")
	@PostMapping("/consumer/user/register_push")
	public UIResponse registerPush(@RequestBody(required = true) final RegisterPushRequest request) {
		return register(request.getDeviceOs(), request.getPushId(), request.getUdid(), SessionUtils.getAuthUserId());
	}

	private UIResponse register(final Device deviceOs, final String pushId, final String udid, final Long customerId) {
		final DeviceBean bean = new DeviceBean();
		bean.setCustomerId(customerId);
		if (null != deviceOs) {
			bean.setDeviceOs(Device.valueOf(deviceOs.name()));
		}
		if (null != pushId) {
			bean.setPushId(pushId);
		}
		bean.setUdid(udid);
		bean.setDeviceLogout(0);
		try {
			deviceService.registerDevice(bean);
		} catch (final HttpClientErrorException e) {
			return UIResponse.create().error("invalid_request", "Invalid request body");
		}
		return UIResponse.create().data(new ArrayList<>());
	}

	@ApiOperation(value = "Register device", nickname = "registerDevice")
	@PostMapping("/consumer/user/register_push/device")
	public void registerDevice(@RequestBody(required = true) final RegisterDeviceRequest request) {
		register(request.getDeviceOs(), null, request.getUdid(), request.getCustomerId());
	}

}
