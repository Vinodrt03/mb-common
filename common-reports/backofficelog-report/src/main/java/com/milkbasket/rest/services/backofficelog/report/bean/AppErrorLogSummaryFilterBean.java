package com.milkbasket.rest.services.backofficelog.report.bean;

import java.util.LinkedHashMap;
import java.util.Map;

import com.milkbasket.core.framework.common.constants.CommonFormats;
import com.milkbasket.core.framework.validator.annotation.DateFormat;
import com.milkbasket.core.framework.websupport.base.bean.FilterBean;

/**
 * Filter bean to fetch list of backoffice logs
 *
 * @author NitinKhaitan
 * @version $Id: $Id
 */
public class AppErrorLogSummaryFilterBean extends FilterBean {

	private static final long serialVersionUID = 201806151645380996L;

	private String error;

	@DateFormat(pattern = CommonFormats.DATE_FORMAT)
	private String startDate;

	@DateFormat(pattern = CommonFormats.DATE_FORMAT)
	private String endDate;

	/**
	 * <p>
	 * Getter for the field <code>startDate</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getStartDate() {
		return startDate;
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
	 * Getter for the field <code>endDate</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getEndDate() {
		return endDate;
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
	 * Getter for the field <code>error</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getError() {
		return error;
	}

	/**
	 * <p>
	 * Setter for the field <code>error</code>.
	 * </p>
	 *
	 * @param error
	 *            a {@link java.lang.String} object.
	 */
	public void setError(final String error) {
		this.error = error;
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
		if (getError() != null) {
			logInfo.put("Error", getError());
		}
		if (getStartDate() != null) {
			logInfo.put("Start Date", getStartDate());
		}
		if (getEndDate() != null) {
			logInfo.put("End Date", getEndDate());
		}
		logInfo.put("Record Count", total != null ? total : 0);
		return logInfo;
	}
}
