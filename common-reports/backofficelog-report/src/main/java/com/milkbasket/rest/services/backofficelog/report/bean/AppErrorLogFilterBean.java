package com.milkbasket.rest.services.backofficelog.report.bean;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.constraints.Min;

import com.milkbasket.core.framework.common.constants.CommonFormats;
import com.milkbasket.core.framework.dbsupport.jdbc.annotation.IgnoreCriteria;
import com.milkbasket.core.framework.validator.annotation.DateFormat;
import com.milkbasket.core.framework.validator.annotation.MinLength;
import com.milkbasket.core.framework.websupport.base.bean.FilterBean;

/**
 * <p>
 * AppErrorLogFilterBean class
 * </p>
 *
 * @author Sanjay
 * @version $Id: $Id
 */
public class AppErrorLogFilterBean extends FilterBean {

	private static final long serialVersionUID = 201806281746440893L;

	@Min(1)
	private Long customerId;

	@MinLength(length = 1)
	private String error;

	@MinLength(length = 1)
	private String module;

	@DateFormat(pattern = CommonFormats.DATE_FORMAT)
	private String startDate;

	@DateFormat(pattern = CommonFormats.DATE_FORMAT)
	private String endDate;

	@IgnoreCriteria
	private Integer newDb;

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
	 * Setter for the field <code>error</code>.
	 * </p>
	 *
	 * @param error
	 *            of v appErrorLog.
	 */
	public void setError(final String error) {
		this.error = error;
	}

	/**
	 * <p>
	 * Getter for the field <code>error</code>.
	 * </p>
	 *
	 * @return error of v appErrorLog.
	 */
	public String getError() {
		return error;
	}

	/**
	 * <p>
	 * Setter for the field <code>module</code>.
	 * </p>
	 *
	 * @param module
	 *            of v appErrorLog.
	 */
	public void setModule(final String module) {
		this.module = module;
	}

	/**
	 * <p>
	 * Getter for the field <code>module</code>.
	 * </p>
	 *
	 * @return module of v appErrorLog.
	 */
	public String getModule() {
		return module;
	}

	/**
	 * <p>
	 * Setter for the field <code>startDate</code>.
	 * </p>
	 *
	 * @param startDate
	 *            of v appErrorLog.
	 */
	public void setStartDate(final String startDate) {
		this.startDate = startDate;
	}

	/**
	 * <p>
	 * Getter for the field <code>startDate</code>.
	 * </p>
	 *
	 * @return startDate of v appErrorLog.
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * <p>
	 * Setter for the field <code>endDate</code>.
	 * </p>
	 *
	 * @param endDate
	 *            of v appErrorLog.
	 */
	public void setEndDate(final String endDate) {
		this.endDate = endDate;
	}

	/**
	 * <p>
	 * Getter for the field <code>endDate</code>.
	 * </p>
	 *
	 * @return endDate of v appErrorLog.
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * <p>
	 * logInfo.
	 * </p>
	 *
	 * @param total
	 *            a {@link java.lang.Integer} object.
	 * @return a {@link java.util.Map} object.
	 */
	public Map<String, Object> logInfo(final Integer total) {

		final Map<String, Object> logInfo = new LinkedHashMap<>();
		if (this.startDate != null) {
			logInfo.put("Start Date", startDate);
		}
		if (this.endDate != null) {
			logInfo.put("End Date", endDate);
		}
		if (this.customerId != null) {
			logInfo.put("Customer ID", customerId);
		}
		if (this.error != null) {
			logInfo.put("Error", error);
		}
		if (this.module != null) {
			logInfo.put("Module", module);
		}
		if (this.newDb != null) {
			logInfo.put("NewDb", newDb);
		}
		logInfo.put("Record Count", total != null ? total : 0);
		return logInfo;
	}

	public Integer getNewDb() {
		return newDb;
	}

	public void setNewDb(final Integer newDb) {
		this.newDb = newDb;
	}

}
