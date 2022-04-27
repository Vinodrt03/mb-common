package com.milkbasket.rest.services.scheduledjob.report.bean;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.constraints.Min;

import com.milkbasket.core.framework.common.constants.CommonFormats;
import com.milkbasket.core.framework.utility.DateUtils;
import com.milkbasket.core.framework.utility.StringUtils;
import com.milkbasket.core.framework.validator.annotation.DateFormat;
import com.milkbasket.core.framework.validator.annotation.MinLength;
import com.milkbasket.core.framework.websupport.base.bean.FilterBean;

/**
 * <p>
 * ScheduledJobSummaryFilterBean class.
 * </p>
 *
 * @author vipulagarwal
 * @version $Id: $Id
 */
public class ScheduledJobSummaryFilterBean extends FilterBean {

	private static final long serialVersionUID = 3779415457131990438L;

	@Min(1)
	private Long hubId;

	@MinLength(length = 1)
	private String jobName;

	@MinLength(length = 1)
	private String jobStatus;

	@DateFormat(pattern = CommonFormats.DATE_FORMAT_WITH_TIME)
	private String startTime;

	@DateFormat(pattern = CommonFormats.DATE_FORMAT_WITH_TIME)
	private String endTime;


	/**
	 * @return the hubId
	 */
	public Long getHubId() {
		return hubId;
	}

	/**
	 * @param hubId
	 *            the hubId to set
	 */
	public void setHubId(final Long hubId) {
		this.hubId = hubId;
	}

	/**
	 * @return the jobName
	 */
	public String getJobName() {
		return jobName;
	}

	/**
	 * @param jobName
	 *            the jobName to set
	 */
	public void setJobName(final String jobName) {
		this.jobName = jobName;
	}

	/**
	 * @return the jobStatus
	 */
	public String getJobStatus() {
		return jobStatus;
	}

	/**
	 * @param jobStatus
	 *            the jobStatus to set
	 */
	public void setJobStatus(final String jobStatus) {
		this.jobStatus = jobStatus;
	}


	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(final String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(final String endTime) {
		this.endTime = endTime;
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
		if (this.hubId != null) {
			logInfo.put("Hub ID", hubId);
		}
		if (this.jobName != null) {
			logInfo.put("Job Name", jobName);
		}
		if (this.jobStatus != null) {
			logInfo.put("Job Status", jobStatus);
		}
		if (this.startTime != null) {
			logInfo.put("Start Date", startTime);
		}
		if (this.endTime != null) {
			logInfo.put("End Date", endTime);
		}
		logInfo.put("Record Count", total != null ? total : 0);
		return logInfo;
	}

	/**
	 *
	 */
	public void presetDates() {
		if (StringUtils.isNotEmpty(getStartTime())) {
			if (StringUtils.isEmpty(getEndTime())) {
				setEndTime(DateUtils.toString(CommonFormats.DATE_FORMAT_WITH_TIME, DateUtils.getDateTime()));
			}
		} else {
			setEndTime(null);
		}
	}

	/**
	 *
	 * @param dateTime
	 * @return
	 */
	public String getDatePart(final String dateTime) {
		final Date dateObj = DateUtils.getDate(CommonFormats.DEFAULT_MYSQL_DATE_TIME_FORMAT, dateTime);
		return DateUtils.toString(CommonFormats.DATE_FORMAT, dateObj);
	}

}
