package com.milkbasket.rest.services.backofficelog.report.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.milkbasket.core.framework.common.constants.CommonFormats;
import com.milkbasket.core.framework.utility.DateUtils;
import com.milkbasket.core.framework.utility.JSONUtils;
import com.milkbasket.core.framework.utility.StringUtils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Bean to be returned with VIEW
 *
 * @author iTuple
 * @version $Id: $Id
 */
@ApiModel(description = "App Error Log Response")
public class AppLogBaseViewBean implements Serializable {

	private static final long serialVersionUID = 201806281708430793L;

	@ApiModelProperty(value = "id of v appErrorLog. Accept numeric characters only.", allowEmptyValue = false)
	private Long id;

	@ApiModelProperty(value = "customerId of v appErrorLog. Non-mandatory input field. Accept numeric characters only.", allowEmptyValue = true)
	private Long customerId;

	@ApiModelProperty(value = "customerName of v appErrorLog. Non-mandatory input field.", allowEmptyValue = true)
	private String customerName;

	@ApiModelProperty(value = "dateTime of v appErrorLog. Non-mandatory input field.", allowEmptyValue = true)
	private String dateTime;

	@JsonIgnore
	@ApiModelProperty(value = "dataJson of v appErrorLog. Non-mandatory input field.", allowEmptyValue = true)
	private String dataJson;

	@ApiModelProperty(value = "data of v appErrorLog. Non-mandatory input field.", allowEmptyValue = true)
	private Map<String, Object> data;

	/**
	 * <p>
	 * Setter for the field <code>id</code>.
	 * </p>
	 *
	 * @param id
	 *            of v appErrorLog.
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * <p>
	 * Getter for the field <code>id</code>.
	 * </p>
	 *
	 * @return id of v appErrorLog.
	 */
	public Long getId() {
		return id;
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

	/**
	 * <p>
	 * Getter for the field <code>customerName</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * <p>
	 * Setter for the field <code>customerName</code>.
	 * </p>
	 *
	 * @param customerName
	 *            a {@link java.lang.String} object.
	 */
	public void setCustomerName(final String customerName) {
		this.customerName = customerName;
	}

	/**
	 * <p>
	 * Setter for the field <code>dateTime</code>.
	 * </p>
	 *
	 * @param dateTime
	 *            of v appErrorLog.
	 */
	public void setDateTime(final String dateTime) {
		this.dateTime = dateTime;
	}

	/**
	 * <p>
	 * Getter for the field <code>dateTime</code>.
	 * </p>
	 *
	 * @return dateTime of v appErrorLog.
	 */
	public String getDateTime() {
		return dateTime;
	}

	/**
	 * <p>
	 * Getter for the field <code>dataJson</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getDataJson() {
		return dataJson;
	}

	/**
	 * <p>
	 * Setter for the field <code>dataJson</code>.
	 * </p>
	 *
	 * @param dataJson
	 *            a {@link java.lang.String} object.
	 */
	public void setDataJson(final String dataJson) {
		this.dataJson = dataJson;
	}

	/**
	 * <p>
	 * Getter for the field <code>data</code>.
	 * </p>
	 *
	 * @return a {@link java.util.Map} object.
	 */
	public Map<String, Object> getData() {
		return data;
	}

	/**
	 * <p>
	 * Setter for the field <code>data</code>.
	 * </p>
	 *
	 * @param data
	 *            a {@link java.util.Map} object.
	 */
	public void setData(final Map<String, Object> data) {
		this.data = data;
	}

	/**
	 * <p>
	 * exchangeDates.
	 * </p>
	 */
	public void exchangeDates() {
		if (StringUtils.isNotEmpty(getDateTime())) {
			final Date dateObj = DateUtils.getDate(CommonFormats.DEFAULT_MYSQL_DATE_TIME_FORMAT, getDateTime());
			setDateTime(DateUtils.toString(CommonFormats.DATE_FORMAT_WITH_TIME, dateObj));
		}
	}

	/**
	 * <p>
	 * exchangeJSONData.
	 * </p>
	 */
	@SuppressWarnings("unchecked")
	public void exchangeJSONData() {
		if (StringUtils.isNotEmpty(getDataJson())) {
			data = JSONUtils.jsonToObject(getDataJson(), Map.class);
		}
	}

}
