package com.milkbasket.rest.services.backofficelog.report.bean;

import java.util.LinkedHashMap;
import java.util.Map;

import com.milkbasket.core.framework.common.constants.CommonFormats;
import com.milkbasket.core.framework.dbsupport.jdbc.annotation.IgnoreCriteria;
import com.milkbasket.core.framework.validator.annotation.DateFormat;
import com.milkbasket.core.framework.validator.annotation.Mandatory;
import com.milkbasket.core.framework.validator.annotation.MinLength;
import com.milkbasket.core.framework.websupport.base.bean.FilterBean;

/**
 * <p>
 * DbLogFilterBean class.
 * </p>
 *
 * @author nitin
 * @version $Id: $Id
 */
public class DbLogFilterBean extends FilterBean {

	private static final long serialVersionUID = 201809022236590996L;

	@MinLength(length = 1)
	private String requestId;

	@MinLength(length = 1)
	@Mandatory
	private String module;

	@DateFormat(pattern = CommonFormats.DATE_FORMAT)
	@Mandatory
	private String startDate;

	@DateFormat(pattern = CommonFormats.DATE_FORMAT)
	@Mandatory
	private String endDate;

	@IgnoreCriteria
	private Integer newDb;

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
	 * Setter for the field <code>startDate</code>.
	 * </p>
	 *
	 * @param startDate
	 *            of v dbLogs.
	 */
	public void setStartDate(final String startDate) {
		this.startDate = startDate;
	}

	/**
	 * <p>
	 * Getter for the field <code>startDate</code>.
	 * </p>
	 *
	 * @return startDate of v dbLogs.
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
	 *            of v dbLogs.
	 */
	public void setEndDate(final String endDate) {
		this.endDate = endDate;
	}

	/**
	 * <p>
	 * Getter for the field <code>endDate</code>.
	 * </p>
	 *
	 * @return endDate of v dbLogs.
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
		if (this.requestId != null) {
			logInfo.put("Request ID", requestId);
		}
		if (this.module != null) {
			logInfo.put("Module", module);
		}
		if (this.startDate != null) {
			logInfo.put("Start Date", startDate);
		}
		if (this.endDate != null) {
			logInfo.put("End Date", endDate);
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
