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
 * AppLogFilterBean class.
 * </p>
 *
 * @author Sanjay Wadhwa
 * @version $Id: $Id
 */
public class AppLogFilterBean extends FilterBean {

	private static final long serialVersionUID = 201806271705490611L;

	@Min(1)
	private Long customerId;

	@MinLength(length = 1)
	private String action;

	@DateFormat(pattern = CommonFormats.DATE_FORMAT)
	private String startDate;

	@DateFormat(pattern = CommonFormats.DATE_FORMAT)
	private String endDate;

	@IgnoreCriteria
	private Integer newDb;

	/**
	 * <p>
	 * Setter for the field <code>customerId</code>.
	 * </p>
	 *
	 * @param userId
	 *            a {@link java.lang.Long} object.
	 */
	public void setCustomerId(final Long userId) {
		this.customerId = userId;
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
	 * Setter for the field <code>action</code>.
	 * </p>
	 *
	 * @param action
	 *            of v appLog.
	 */
	public void setAction(final String action) {
		this.action = action;
	}

	/**
	 * <p>
	 * Getter for the field <code>action</code>.
	 * </p>
	 *
	 * @return action of v appLog.
	 */
	public String getAction() {
		return action;
	}

	/**
	 * <p>
	 * Setter for the field <code>startDate</code>.
	 * </p>
	 *
	 * @param startDate
	 *            a {@link java.lang.String} object.
	 */
	public void setStartDate(final String startDate) {
		this.startDate = startDate;
	}

	/**
	 * <p>
	 * Getter for the field <code>startDate</code>.
	 * </p>
	 *
	 * @return startDate
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
	 *            a {@link java.lang.String} object.
	 */
	public void setEndDate(final String endDate) {
		this.endDate = endDate;
	}

	/**
	 * <p>
	 * Getter for the field <code>endDate</code>.
	 * </p>
	 *
	 * @return endDate
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
		if (this.action != null) {
			logInfo.put("Action", action);
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
