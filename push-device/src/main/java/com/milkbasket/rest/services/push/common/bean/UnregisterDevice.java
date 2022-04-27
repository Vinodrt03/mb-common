package com.milkbasket.rest.services.push.common.bean;

import java.io.Serializable;

import com.milkbasket.core.framework.validator.annotation.Mandatory;
import com.milkbasket.core.framework.validator.annotation.MaxLength;
import com.milkbasket.core.framework.validator.annotation.MinLength;
import com.milkbasket.core.framework.validator.annotation.ValidColumnValue;

import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * UnregisterDevice class.
 * </p>
 *
 * @author nitin
 * @version $Id: $Id
 */
public class UnregisterDevice implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1401799663980565956L;

	@ApiModelProperty(value = "UDID associated with device", allowEmptyValue = false)
	@Mandatory
	@MinLength(length = 1)
	@MaxLength(length = 45)
	private String udid;

	@ApiModelProperty(value = "Customer Id", allowEmptyValue = false)
	@ValidColumnValue(table = "customers", column = "id")
	@Mandatory
	private Long customerId;

	/**
	 * <p>
	 * Getter for the field <code>udid</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getUdid() {
		return udid;
	}

	/**
	 * <p>
	 * Setter for the field <code>udid</code>.
	 * </p>
	 *
	 * @param udid
	 *            a {@link java.lang.String} object.
	 */
	public void setUdid(final String udid) {
		this.udid = udid;
	}

	/**
	 * <p>
	 * Getter for the field <code>customerId</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.Long} object.
	 */
	public Long getCustomerId() {
		return customerId;
	}

	/**
	 * <p>
	 * Setter for the field <code>customerId</code>.
	 * </p>
	 *
	 * @param customerId
	 *            a {@link java.lang.Long} object.
	 */
	public void setCustomerId(final Long customerId) {
		this.customerId = customerId;
	}

}
