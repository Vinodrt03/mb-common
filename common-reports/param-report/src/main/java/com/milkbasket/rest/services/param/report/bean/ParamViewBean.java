package com.milkbasket.rest.services.param.report.bean;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Bean to be returned with VIEW.
 *
 * @author iTuple
 * @version $Id: $Id
 */
@ApiModel(description = "Param Response")
public class ParamViewBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 201901241745570880L;

	/** The param id. */
	@ApiModelProperty(value = "Param Id", allowEmptyValue = false)
	private Long paramId;

	/** The param key. */
	@ApiModelProperty(value = "Key of param (slug for settings). It should be unique among all the entities stored in the database.", allowEmptyValue = false)
	private String paramKey;

	/** The param name. */
	@ApiModelProperty(value = "Name of param.", allowEmptyValue = false)
	private String paramName;

	/** The param value. */
	@ApiModelProperty(value = "Value of param.", allowEmptyValue = false)
	private String paramValue;

	/** The is editable. */
	@ApiModelProperty(value = "Is Editable", allowEmptyValue = false)
	private Integer isEditable;

	/**
	 * Gets the checks if is editable.
	 *
	 * @return the checks if is editable
	 */
	public Integer getIsEditable() {
		return isEditable;
	}

	/**
	 * Sets the checks if is editable.
	 *
	 * @param isEditable
	 *            the new checks if is editable
	 */
	public void setIsEditable(final Integer isEditable) {
		this.isEditable = isEditable;
	}

	/**
	 * <p>
	 * Getter for the field <code>paramId</code>.
	 * </p>
	 *
	 * @return paramId of v param.
	 */
	public Long getParamId() {
		return paramId;
	}

	/**
	 * <p>
	 * Setter for the field <code>paramId</code>.
	 * </p>
	 *
	 * @param paramId
	 *            of v param.
	 */
	public void setParamId(final Long paramId) {
		this.paramId = paramId;
	}

	/**
	 * <p>
	 * Setter for the field <code>paramKey</code>.
	 * </p>
	 *
	 * @param paramKey
	 *            of v param.
	 */
	public void setParamKey(final String paramKey) {
		this.paramKey = paramKey;
	}

	/**
	 * <p>
	 * Getter for the field <code>paramKey</code>.
	 * </p>
	 *
	 * @return paramKey of v param.
	 */
	public String getParamKey() {
		return paramKey;
	}

	/**
	 * <p>
	 * Setter for the field <code>paramValue</code>.
	 * </p>
	 *
	 * @param paramValue
	 *            of v param.
	 */
	public void setParamValue(final String paramValue) {
		this.paramValue = paramValue;
	}

	/**
	 * <p>
	 * Getter for the field <code>paramValue</code>.
	 * </p>
	 *
	 * @return paramValue of v param.
	 */
	public String getParamValue() {
		return paramValue;
	}

	/**
	 * Gets the param name.
	 *
	 * @return the param name
	 */
	public String getParamName() {
		return paramName;
	}

	/**
	 * Sets the param name.
	 *
	 * @param paramName
	 *            the new param name
	 */
	public void setParamName(final String paramName) {
		this.paramName = paramName;
	}

}
