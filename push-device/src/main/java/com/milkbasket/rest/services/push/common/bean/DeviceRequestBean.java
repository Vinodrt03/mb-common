package com.milkbasket.rest.services.push.common.bean;

import java.io.Serializable;
import java.util.List;

import com.milkbasket.core.framework.validator.annotation.IsFlag;
import com.milkbasket.core.framework.validator.annotation.Mandatory;
import com.milkbasket.core.framework.validator.annotation.ValidColumnValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Bean to be returned with device related information
 *
 * @author iTuple
 * @version $Id: $Id
 */
@ApiModel(description = "Device Request Bean")
public class DeviceRequestBean implements Serializable {

	private static final long serialVersionUID = 201808291936360533L;

	@ApiModelProperty(value = "Customer id with which basket is associated. Mandatory input field. Accept numeric characters only.", allowEmptyValue = false)
	@ValidColumnValue(table = "customers", column = "id")
	@Mandatory
	private List<Long> customerId;

	@ApiModelProperty(value = "Is device logout. Mandatory input field. Accept numeric characters only.", allowEmptyValue = false)
	@Mandatory
	@IsFlag
	private Integer deviceLogout;

	/**
	 * <p>
	 * Getter for the field <code>customerId</code>.
	 * </p>
	 *
	 * @return a {@link java.util.List} object.
	 */
	public List<Long> getCustomerId() {
		return customerId;
	}

	/**
	 * <p>
	 * Setter for the field <code>customerId</code>.
	 * </p>
	 *
	 * @param customerId
	 *            a {@link java.util.List} object.
	 */
	public void setCustomerId(final List<Long> customerId) {
		this.customerId = customerId;
	}

	/**
	 * <p>
	 * Getter for the field <code>deviceLogout</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.Integer} object.
	 */
	public Integer getDeviceLogout() {
		return deviceLogout;
	}

	/**
	 * <p>
	 * Setter for the field <code>deviceLogout</code>.
	 * </p>
	 *
	 * @param deviceLogout
	 *            a {@link java.lang.Integer} object.
	 */
	public void setDeviceLogout(final Integer deviceLogout) {
		this.deviceLogout = deviceLogout;
	}

	/**
	 * <p>
	 * newInstance.
	 * </p>
	 *
	 * @return a
	 *         {@link com.milkbasket.rest.services.push.common.bean.DeviceRequestBean}
	 *         object.
	 */
	public static DeviceRequestBean newInstance() {
		return new DeviceRequestBean();
	}
}
