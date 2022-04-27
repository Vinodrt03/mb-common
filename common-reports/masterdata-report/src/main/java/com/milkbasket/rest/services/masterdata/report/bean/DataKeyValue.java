package com.milkbasket.rest.services.masterdata.report.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * Cluster class.
 * </p>
 *
 * @author Neeraj
 * @version $Id: $Id
 */
public class DataKeyValue implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -3039558604876448660L;

	/** The master key. */
	@JsonIgnore
	private String masterKey;

	/** The data key. */
	@ApiModelProperty(value = "dataKey of v masterData.", allowEmptyValue = false)
	private String dataKey;

	/** The data value. */
	@ApiModelProperty(value = "dataValue of v masterData.", allowEmptyValue = false)
	private String dataValue;

	/**
	 * Sets the master key.
	 *
	 * @param masterKey
	 *            of v masterData.
	 */
	public void setMasterKey(final String masterKey) {
		this.masterKey = masterKey;
	}

	/**
	 * Gets the master key.
	 *
	 * @return masterKey of v masterData.
	 */
	public String getMasterKey() {
		return masterKey;
	}

	/**
	 * Sets the data key.
	 *
	 * @param dataKey
	 *            of v masterData.
	 */
	public void setDataKey(final String dataKey) {
		this.dataKey = dataKey;
	}

	/**
	 * Gets the data key.
	 *
	 * @return dataKey of v masterData.
	 */
	public String getDataKey() {
		return dataKey;
	}

	/**
	 * Sets the data value.
	 *
	 * @param dataValue
	 *            of v masterData.
	 */
	public void setDataValue(final String dataValue) {
		this.dataValue = dataValue;
	}

	/**
	 * Gets the data value.
	 *
	 * @return dataValue of v masterData.
	 */
	public String getDataValue() {
		return dataValue;
	}

}
