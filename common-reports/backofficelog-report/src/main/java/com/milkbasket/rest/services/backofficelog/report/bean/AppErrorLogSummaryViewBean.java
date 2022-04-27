package com.milkbasket.rest.services.backofficelog.report.bean;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Bean to be returned with VIEW
 *
 * @author NitinKhaitan
 * @version $Id: $Id
 */
@ApiModel(description = "Back Office Log Response")
public class AppErrorLogSummaryViewBean implements Serializable {

	private static final long serialVersionUID = 201806151645380988L;

	@ApiModelProperty(value = "error name of backOfficeLog.")
	private String error;

	@ApiModelProperty(value = "date of backOfficeLog.")
	private String date;

	@ApiModelProperty(value = "count of backOfficeLog.")
	private Integer count;

	/**
	 * <p>
	 * Setter for the field <code>date</code>.
	 * </p>
	 *
	 * @param date
	 *            of backOfficeLog.
	 */
	public void setDate(final String date) {
		this.date = date;
	}

	/**
	 * <p>
	 * Getter for the field <code>date</code>.
	 * </p>
	 *
	 * @return date of backOfficeLog.
	 */
	public String getDate() {
		return date;
	}

	/**
	 * <p>
	 * Setter for the field <code>count</code>.
	 * </p>
	 *
	 * @param count
	 *            of backOfficeLog.
	 */
	public void setCount(final Integer count) {
		this.count = count;
	}

	/**
	 * <p>
	 * Getter for the field <code>count</code>.
	 * </p>
	 *
	 * @return count of backOfficeLog.
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * <p>
	 * Setter for the field <code>error</code>.
	 * </p>
	 *
	 * @param error
	 *            of backOfficeLog.
	 */
	public void setError(final String error) {
		this.error = error;
	}

	/**
	 * <p>
	 * Getter for the field <code>error</code>.
	 * </p>
	 *
	 * @return error of backOfficeLog.
	 */
	public String getError() {
		return error;
	}

}
