package com.milkbasket.rest.services.backofficelog.report.bean;

import java.io.Serializable;
import java.util.Date;

import com.milkbasket.core.framework.common.constants.CommonFormats;
import com.milkbasket.core.framework.utility.DateUtils;
import com.milkbasket.core.framework.utility.StringUtils;
import com.milkbasket.core.framework.websupport.base.bean.CsvBean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Bean to be returned with VIEW
 *
 * @author iTuple
 * @version $Id: $Id
 */
@ApiModel(description = "Db Logs Response")
public class DbLogViewBean implements Serializable, CsvBean {

	private static final long serialVersionUID = 201809022236590979L;

	/**
	 * Constant
	 * <code>HEADER="ID,User ID,User Name,Request ID,Module,"{trunked}</code>
	 */
	public static final String HEADER = "Db Log ID,User ID,User Name,Request ID,Module,Action,Method,Sql Query,Time Taken,Date";

	@ApiModelProperty(value = "id of v dbLogs. Accept numeric characters only.", allowEmptyValue = false)
	private Long id;

	@ApiModelProperty(value = "userId of v dbLogs. Non-mandatory input field. Accept numeric characters only.", allowEmptyValue = true)
	private Long userId;

	@ApiModelProperty(value = "userName of v dbLogs. Non-mandatory input field.", allowEmptyValue = true)
	private String userName;

	@ApiModelProperty(value = "requestId of v dbLogs. Non-mandatory input field. Accept numeric characters only.", allowEmptyValue = true)
	private String requestId;

	@ApiModelProperty(value = "module of v dbLogs. Non-mandatory input field.", allowEmptyValue = true)
	private String module;

	@ApiModelProperty(value = "action of v dbLogs. Non-mandatory input field.", allowEmptyValue = true)
	private String action;

	@ApiModelProperty(value = "method of v dbLogs. Non-mandatory input field.", allowEmptyValue = true)
	private String method;

	@ApiModelProperty(value = "sqlQuery of v dbLogs. Non-mandatory input field.", allowEmptyValue = true)
	private String sqlQuery;

	@ApiModelProperty(value = "timeTaken of v dbLogs. Non-mandatory input field. Accept numeric characters only.", allowEmptyValue = true)
	private Integer timeTaken;

	@ApiModelProperty(value = "date of v dbLogs. Non-mandatory input field.", allowEmptyValue = true)
	private String date;

	/**
	 * <p>
	 * Setter for the field <code>id</code>.
	 * </p>
	 *
	 * @param id
	 *            of v dbLogs.
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * <p>
	 * Getter for the field <code>id</code>.
	 * </p>
	 *
	 * @return id of v dbLogs.
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
	 *            of v dbLogs.
	 */
	public void setUserId(final Long userId) {
		this.userId = userId;
	}

	/**
	 * <p>
	 * Getter for the field <code>userId</code>.
	 * </p>
	 *
	 * @return userId of v dbLogs.
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * <p>
	 * Setter for the field <code>userName</code>.
	 * </p>
	 *
	 * @param userName
	 *            of v dbLogs.
	 */
	public void setUserName(final String userName) {
		this.userName = userName;
	}

	/**
	 * <p>
	 * Getter for the field <code>userName</code>.
	 * </p>
	 *
	 * @return userName of v dbLogs.
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * <p>
	 * Setter for the field <code>requestId</code>.
	 * </p>
	 *
	 * @param requestId
	 *            of v dbLogs.
	 */
	public void setRequestId(final String requestId) {
		this.requestId = requestId;
	}

	/**
	 * <p>
	 * Getter for the field <code>requestId</code>.
	 * </p>
	 *
	 * @return requestId of v dbLogs.
	 */
	public String getRequestId() {
		return requestId;
	}

	/**
	 * <p>
	 * Setter for the field <code>module</code>.
	 * </p>
	 *
	 * @param module
	 *            of v dbLogs.
	 */
	public void setModule(final String module) {
		this.module = module;
	}

	/**
	 * <p>
	 * Getter for the field <code>module</code>.
	 * </p>
	 *
	 * @return module of v dbLogs.
	 */
	public String getModule() {
		return module;
	}

	/**
	 * <p>
	 * Setter for the field <code>action</code>.
	 * </p>
	 *
	 * @param action
	 *            of v dbLogs.
	 */
	public void setAction(final String action) {
		this.action = action;
	}

	/**
	 * <p>
	 * Getter for the field <code>action</code>.
	 * </p>
	 *
	 * @return action of v dbLogs.
	 */
	public String getAction() {
		return action;
	}

	/**
	 * <p>
	 * Setter for the field <code>method</code>.
	 * </p>
	 *
	 * @param method
	 *            of v dbLogs.
	 */
	public void setMethod(final String method) {
		this.method = method;
	}

	/**
	 * <p>
	 * Getter for the field <code>method</code>.
	 * </p>
	 *
	 * @return method of v dbLogs.
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * <p>
	 * Setter for the field <code>sqlQuery</code>.
	 * </p>
	 *
	 * @param sqlQuery
	 *            of v dbLogs.
	 */
	public void setSqlQuery(final String sqlQuery) {
		this.sqlQuery = sqlQuery;
	}

	/**
	 * <p>
	 * Getter for the field <code>sqlQuery</code>.
	 * </p>
	 *
	 * @return sqlQuery of v dbLogs.
	 */
	public String getSqlQuery() {
		return sqlQuery;
	}

	/**
	 * <p>
	 * Setter for the field <code>timeTaken</code>.
	 * </p>
	 *
	 * @param timeTaken
	 *            of v dbLogs.
	 */
	public void setTimeTaken(final Integer timeTaken) {
		this.timeTaken = timeTaken;
	}

	/**
	 * <p>
	 * Getter for the field <code>timeTaken</code>.
	 * </p>
	 *
	 * @return timeTaken of v dbLogs.
	 */
	public Integer getTimeTaken() {
		return timeTaken;
	}

	/**
	 * <p>
	 * Setter for the field <code>date</code>.
	 * </p>
	 *
	 * @param date
	 *            of v dbLogs.
	 */
	public void setDate(final String date) {
		this.date = date;
	}

	/**
	 * <p>
	 * Getter for the field <code>date</code>.
	 * </p>
	 *
	 * @return date of v dbLogs.
	 */
	public String getDate() {
		return date;
	}

	/**
	 * <p>
	 * csvRow.
	 * </p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	@Override
	public String csvRow() {
		final StringBuilder sb = new StringBuilder();
		sb.append(getCellValue(getId()));
		sb.append(',').append(getCellValue(getUserId()));
		sb.append(',').append(getCellValue(getUserName()));
		sb.append(',').append(getCellValue(getRequestId()));
		sb.append(',').append(getCellValue(getModule()));
		sb.append(',').append(getCellValue(getAction()));
		sb.append(',').append(getCellValue(getMethod()));
		sb.append(',').append(getCellValue(getSqlQuery()));
		sb.append(',').append(getCellValue(getTimeTaken()));
		sb.append(',').append(getCellValue(getDate()));
		return sb.toString();
	}

	/**
	 * <p>
	 * exchangeDates.
	 * </p>
	 */
	public void exchangeDates() {
		if (StringUtils.isNotEmpty(getDate())) {
			final Date dateObj = DateUtils.getDate(CommonFormats.DEFAULT_MYSQL_DATE_TIME_FORMAT, getDate());
			setDate(DateUtils.toString(CommonFormats.DATE_FORMAT_WITH_TIME, dateObj));
		}

	}

}
