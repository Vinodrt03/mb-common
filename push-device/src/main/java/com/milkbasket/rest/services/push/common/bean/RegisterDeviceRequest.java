package com.milkbasket.rest.services.push.common.bean;

import java.io.Serializable;

import com.milkbasket.core.framework.validator.annotation.Mandatory;
import com.milkbasket.rest.services.communication.entity.DeviceConstants.Device;

public class RegisterDeviceRequest implements Serializable {

	private static final long serialVersionUID = 5081997097263879232L;

	@Mandatory
	private Device deviceOs;

	@Mandatory
	private String udid;

	@Mandatory
	private Long customerId;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(final Long customerId) {
		this.customerId = customerId;
	}

	public Device getDeviceOs() {
		return deviceOs;
	}

	public void setDeviceOs(final Device deviceOs) {
		this.deviceOs = deviceOs;
	}

	public String getUdid() {
		return udid;
	}

	public void setUdid(final String udid) {
		this.udid = udid;
	}

}
