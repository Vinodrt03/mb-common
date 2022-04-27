package com.milkbasket.rest.services.backofficelog.report.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.milkbasket.core.framework.common.constants.CommonFormats;
import com.milkbasket.core.framework.utility.DateUtils;
import com.milkbasket.core.framework.utility.JSONUtils;
import com.milkbasket.core.framework.utility.StringUtils;
import com.milkbasket.core.framework.websupport.base.bean.CsvBean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Bean to be returned with VIEW
 *
 * @author NitinKhaitan
 * @version $Id: $Id
 */
@ApiModel(description = "Back Office Log Response")
public class BackOfficeLogViewBean implements Serializable, CsvBean {

	private static final long serialVersionUID = 201806151645380988L;

	/** Constant <code>HEADER="ID,User ID,User Name,Date,Action,Data"</code> */
	public static final String HEADER = "Backoffice Log ID,User ID,User Name,Email,Product ID,Hub ID, Date Time,Action,Data";

	@ApiModelProperty(value = "id of backOfficeLog. Accept numeric characters only.", allowEmptyValue = false)
	private Long id;

	@ApiModelProperty(value = "userId of backOfficeLog. Non-mandatory input field. Accept numeric characters only.", allowEmptyValue = true)
	private Long userId;

	@ApiModelProperty(value = "name of backOfficeLog. Non-mandatory input field.", allowEmptyValue = true)
	private String name;

	@ApiModelProperty(value = "productId of backOfficeLog. Non-mandatory input field. Accept numeric characters only.", allowEmptyValue = true)
	private Long productId;

	@ApiModelProperty(value = "hubId of backOfficeLog. Non-mandatory input field. Accept numeric characters only.", allowEmptyValue = true)
	private Long hubId;

	@ApiModelProperty(value = "dateTime of backOfficeLog. Non-mandatory input field.", allowEmptyValue = true)
	private String dateTime;

	@ApiModelProperty(value = "action of backOfficeLog. Non-mandatory input field.", allowEmptyValue = true)
	private String action;

	@ApiModelProperty(value = "email of backOfficeLog. Non-mandatory input field.", allowEmptyValue = true)
	private String email;

	@JsonIgnore
	@ApiModelProperty(value = "dataJson of v backOfficeLog. Non-mandatory input field.", allowEmptyValue = true)
	private String dataJson;

	@ApiModelProperty(value = "data of v backOfficeLog. Non-mandatory input field.", allowEmptyValue = true)
	private Map<String, Object> data;

	/**
	 * <p>
	 * Setter for the field <code>id</code>.
	 * </p>
	 *
	 * @param id
	 *            of backOfficeLog.
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * <p>
	 * Getter for the field <code>id</code>.
	 * </p>
	 *
	 * @return id of backOfficeLog.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * <p>
	 * Setter for the field <code>userId</code>.
	 * </p>
	 *
	 * @param userId
	 *            of backOfficeLog.
	 */
	public void setUserId(final Long userId) {
		this.userId = userId;
	}

	/**
	 * <p>
	 * Getter for the field <code>userId</code>.
	 * </p>
	 *
	 * @return userId of backOfficeLog.
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * <p>
	 * Setter for the field <code>name</code>.
	 * </p>
	 *
	 * @param name
	 *            of backOfficeLog.
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * <p>
	 * Getter for the field <code>name</code>.
	 * </p>
	 *
	 * @return name of backOfficeLog.
	 */
	public String getName() {
		return name;
	}

	/**
	 * <p>
	 * Setter for the field <code>dateTime</code>.
	 * </p>
	 *
	 * @param dateTime
	 *            of backOfficeLog.
	 */
	public void setDateTime(final String dateTime) {
		this.dateTime = dateTime;
	}

	/**
	 * <p>
	 * Getter for the field <code>dateTime</code>.
	 * </p>
	 *
	 * @return dateTime of backOfficeLog.
	 */
	public String getDateTime() {
		return dateTime;
	}

	/**
	 * <p>
	 * Setter for the field <code>action</code>.
	 * </p>
	 *
	 * @param action
	 *            of backOfficeLog.
	 */
	public void setAction(final String action) {
		this.action = action;
	}

	/**
	 * <p>
	 * Getter for the field <code>action</code>.
	 * </p>
	 *
	 * @return action of backOfficeLog.
	 */
	public String getAction() {
		return action;
	}

	/**
	 * <p>
	 * Setter for the field <code>email</code>.
	 * </p>
	 *
	 * @param email
	 *            of backOfficeLog.
	 */
	public void setEmail(final String email) {
		this.email = email;
	}

	/**
	 * <p>
	 * Getter for the field <code>email</code>.
	 * </p>
	 *
	 * @return email of backOfficeLog.
	 */
	public String getEmail() {
		return email;
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
	 * Getter for the field <code>productId</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.Long} object.
	 */
	public Long getProductId() {
		return productId;
	}

	/**
	 * <p>
	 * Setter for the field <code>productId</code>.
	 * </p>
	 *
	 * @param productId
	 *            a {@link java.lang.Long} object.
	 */
	public void setProductId(final Long productId) {
		this.productId = productId;
	}

	/**
	 * <p>
	 * Getter for the field <code>hubId</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.Long} object.
	 */
	public Long getHubId() {
		return hubId;
	}

	/**
	 * <p>
	 * Setter for the field <code>hubId</code>.
	 * </p>
	 *
	 * @param hubId
	 *            a {@link java.lang.Long} object.
	 */
	public void setHubId(final Long hubId) {
		this.hubId = hubId;
	}

	/** {@inheritDoc} */
	@Override
	public String csvRow() {
		final StringBuilder sb = new StringBuilder();
		sb.append(getCellValue(getId()));
		sb.append(',').append(getCellValue(getUserId()));
		sb.append(',').append(getCellValue(getName()));
		sb.append(',').append(getCellValue(getEmail()));
		sb.append(',').append(getCellValue(getProductId()));
		sb.append(',').append(getCellValue(getHubId()));
		sb.append(',').append(getCellValue(getDateTime()));
		sb.append(',').append(getCellValue(getAction()));
		sb.append(',').append(getCellValue(escapeQuotes(getDataJson())));
		return sb.toString();
	}

	private static String escapeQuotes(final String text) {
		if (text == null) {
			return "";
		}
		return text.replace(',', ';').replace('"', '\'');
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
