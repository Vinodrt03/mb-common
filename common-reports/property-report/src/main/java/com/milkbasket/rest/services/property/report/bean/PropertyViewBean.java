package com.milkbasket.rest.services.property.report.bean;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Bean to be returned with VIEW
 *
 * @author Tanuja
 * @version $Id: $Id
 */
@ApiModel(description = "Property Response")
public class PropertyViewBean implements Serializable {

	private static final long serialVersionUID = 201901241745570880L;

	@ApiModelProperty(value = "Key of property. It should be unique among all the entities stored in the database.", allowEmptyValue = false)
	private String propertyKey;

	@ApiModelProperty(value = "Value of property.", allowEmptyValue = false)
	private String propertyValue;

	/**
	 * <p>
	 * Setter for the field <code>propertyKey</code>.
	 * </p>
	 *
	 * @property propertyKey of v property.
	 * @param propertyKey
	 *            a {@link java.lang.String} object.
	 */
	public void setPropertyKey(final String propertyKey) {
		this.propertyKey = propertyKey;
	}

	/**
	 * <p>
	 * Getter for the field <code>propertyKey</code>.
	 * </p>
	 *
	 * @return propertyKey of v property.
	 */
	public String getPropertyKey() {
		return propertyKey;
	}

	/**
	 * <p>
	 * Setter for the field <code>propertyValue</code>.
	 * </p>
	 *
	 * @property propertyValue of v property.
	 * @param propertyValue
	 *            a {@link java.lang.String} object.
	 */
	public void setPropertyValue(final String propertyValue) {
		this.propertyValue = propertyValue;
	}

	/**
	 * <p>
	 * Getter for the field <code>propertyValue</code>.
	 * </p>
	 *
	 * @return propertyValue of v property.
	 */
	public String getPropertyValue() {
		return propertyValue;
	}

}
