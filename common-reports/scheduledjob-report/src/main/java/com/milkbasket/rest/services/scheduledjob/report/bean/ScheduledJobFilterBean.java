package com.milkbasket.rest.services.scheduledjob.report.bean;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.constraints.Min;

import com.milkbasket.core.framework.common.constants.CommonFormats;
import com.milkbasket.core.framework.validator.annotation.DateFormat;
import com.milkbasket.core.framework.validator.annotation.MinLength;
import com.milkbasket.core.framework.websupport.base.bean.FilterBean;

/**
 * <p>
 * ScheduledJobFilterBean class.
 * </p>
 *
 * @author Neeraj
 * @version $Id: $Id
 */
public class ScheduledJobFilterBean extends FilterBean {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 201811281611160020L;

	/** The hub id. */
	@Min(1)
	private Long hubId;

	/** The job name. */
	@MinLength(length = 1)
	private String jobName;

	/** The job status. */
	@MinLength(length = 1)
	private String jobStatus;

	@DateFormat(pattern = CommonFormats.DATE_FORMAT_WITH_TIME)
	private String startTime;

	@DateFormat(pattern = CommonFormats.DATE_FORMAT_WITH_TIME)
	private String endTime;

	/** Source of scheduled Job **/
	private Integer source;

	/**
	 * <p>
	 * Setter for the field <code>hubId</code>.
	 * </p>
	 *
	 * @param hubId
	 *            of v scheduledJob.
	 */
	public void setHubId(final Long hubId) {
		this.hubId = hubId;
	}

	/**
	 * <p>
	 * Getter for the field <code>hubId</code>.
	 * </p>
	 *
	 * @return hubId of v scheduledJob.
	 */
	public Long getHubId() {
		return hubId;
	}

	/**
	 * <p>
	 * Setter for the field <code>jobName</code>.
	 * </p>
	 *
	 * @param jobName
	 *            of v scheduledJob.
	 */
	public void setJobName(final String jobName) {
		this.jobName = jobName;
	}

	/**
	 * <p>
	 * Getter for the field <code>jobName</code>.
	 * </p>
	 *
	 * @return jobName of v scheduledJob.
	 */
	public String getJobName() {
		return jobName;
	}

	/**
	 * <p>
	 * Setter for the field <code>jobStatus</code>.
	 * </p>
	 *
	 * @param jobStatus
	 *            of v scheduledJob.
	 */
	public void setJobStatus(final String jobStatus) {
		this.jobStatus = jobStatus;
	}

	/**
	 * <p>
	 * Getter for the field <code>jobStatus</code>.
	 * </p>
	 *
	 * @return jobStatus of v scheduledJob.
	 */
	public String getJobStatus() {
		return jobStatus;
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
	 * @return the source
	 */
	public Integer getSource() {
		return source;
	}

	/**
	 * @param source
	 *            the source to set
	 */
	public void setSource(final Integer source) {
		this.source = source;
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
		if (this.source != null) {
			logInfo.put("Source", source);
		}
		logInfo.put("Record Count", total != null ? total : 0);
		return logInfo;
	}

}
