package com.milkbasket.rest.services.push.common.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.milkbasket.core.framework.validator.annotation.Mandatory;
import com.milkbasket.rest.services.communication.entity.DeviceConstants.Device;

public class RegisterPushRequest implements Serializable {

	private static final long serialVersionUID = 5081997097263879232L;

	@JsonProperty("device_os")
	@Mandatory
	private Device deviceOs;

	@JsonProperty("udid")
	@Mandatory
	private String udid;

	@JsonProperty("push_id")
	@Mandatory
	private String pushId;

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

	public String getPushId() {
		return pushId;
	}

	public void setPushId(final String pushId) {
		this.pushId = pushId;
	}

}
