package com.milkbasket.rest.services.scheduledjob.report.bean;

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
 * @author iTuple
 * @version $Id: $Id
 */
@ApiModel(description = "Scheduled Job Response")
public class ScheduledJobViewBean implements Serializable, CsvBean {

	private static final long serialVersionUID = 201811281611160013L;

	/**
	 * Constant
	 * <code>HEADER="Hub ID,Hub Name,Job Name,Job Date,Start"{trunked}</code>
	 */
	public static final String HEADER = "Hub ID,Hub Name,Job Name,Job Date,Start Time,End Time,Time Taken,Job Result,Initiated By";

	@ApiModelProperty(value = "hubId of v scheduledJob. Non-mandatory input field. Accept numeric characters only.", allowEmptyValue = true)
	private Long hubId;

	@ApiModelProperty(value = "name with which the hub is known.", allowEmptyValue = false)
	private String hubName;

	@ApiModelProperty(value = "jobName of v scheduledJob.", allowEmptyValue = false)
	private String jobName;

	@ApiModelProperty(value = "Status of the scheduled job. Could be INITIATED/SUCCESS/FAILED.", allowEmptyValue = true)
	private String jobStatus;

	@ApiModelProperty(value = "jobDate of v scheduledJob. Non-mandatory input field.", allowEmptyValue = true)
	private String jobDate;

	@ApiModelProperty(value = "startTime of v scheduledJob.", allowEmptyValue = false)
	private String startTime;

	@ApiModelProperty(value = "endTime of v scheduledJob. Non-mandatory input field.", allowEmptyValue = true)
	private String endTime;

	@ApiModelProperty(value = "timeTaken of v scheduledJob. Non-mandatory input field. Accept numeric characters only.", allowEmptyValue = true)
	private Integer timeTaken;

	@JsonIgnore
	@ApiModelProperty(value = "jobResult of v scheduledJob. Non-mandatory input field.", allowEmptyValue = true)
	private String jobResultData;

	@ApiModelProperty(value = "jobResult of v backOfficeLog. Non-mandatory input field.", allowEmptyValue = true)
	private Map<String, Object> jobResult;

	@ApiModelProperty(value = "initiatedBy of v scheduledJob. Non-mandatory input field.", allowEmptyValue = true)
	private String initiatedBy;

	@ApiModelProperty(value = "Source of the scheduled Job. Could be batch/event/scheduler.", allowEmptyValue = true)
	private Integer source;


	/**
	 * <p>
	 * Getter for the field <code>jobResult</code>.
	 * </p>
	 *
	 * @return a {@link java.util.Map} object.
	 */
	public Map<String, Object> getJobResult() {
		return jobResult;
	}

	/**
	 * <p>
	 * Setter for the field <code>jobResult</code>.
	 * </p>
	 *
	 * @param jobResult
	 *            a {@link java.util.Map} object.
	 */
	public void setJobResult(final Map<String, Object> jobResult) {
		this.jobResult = jobResult;
	}

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
	 * Setter for the field <code>hubName</code>.
	 * </p>
	 *
	 * @param hubName
	 *            a {@link java.lang.String} object.
	 */
	public void setHubName(final String hubName) {
		this.hubName = hubName;
	}

	/**
	 * <p>
	 * Getter for the field <code>hubName</code>.
	 * </p>
	 *
	 * @return name with which the hub is known
	 */
	public String getHubName() {
		return hubName;
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
	 * <p>
	 * Setter for the field <code>jobDate</code>.
	 * </p>
	 *
	 * @param jobDate
	 *            of v scheduledJob.
	 */
	public void setJobDate(final String jobDate) {
		this.jobDate = jobDate;
	}

	/**
	 * <p>
	 * Getter for the field <code>jobDate</code>.
	 * </p>
	 *
	 * @return jobDate of v scheduledJob.
	 */
	public String getJobDate() {
		return jobDate;
	}

	/**
	 * <p>
	 * Setter for the field <code>startTime</code>.
	 * </p>
	 *
	 * @param startTime
	 *            of v scheduledJob.
	 */
	public void setStartTime(final String startTime) {
		this.startTime = startTime;
	}

	/**
	 * <p>
	 * Getter for the field <code>startTime</code>.
	 * </p>
	 *
	 * @return startTime of v scheduledJob.
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * <p>
	 * Setter for the field <code>endTime</code>.
	 * </p>
	 *
	 * @param endTime
	 *            of v scheduledJob.
	 */
	public void setEndTime(final String endTime) {
		this.endTime = endTime;
	}

	/**
	 * <p>
	 * Getter for the field <code>endTime</code>.
	 * </p>
	 *
	 * @return endTime of v scheduledJob.
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * <p>
	 * Setter for the field <code>timeTaken</code>.
	 * </p>
	 *
	 * @param timeTaken
	 *            of v scheduledJob.
	 */
	public void setTimeTaken(final Integer timeTaken) {
		this.timeTaken = timeTaken;
	}

	/**
	 * <p>
	 * Getter for the field <code>timeTaken</code>.
	 * </p>
	 *
	 * @return timeTaken of v scheduledJob.
	 */
	public Integer getTimeTaken() {
		return timeTaken;
	}

	/**
	 * <p>
	 * Setter for the field <code>jobResultData</code>.
	 * </p>
	 *
	 * @param jobResultData
	 *            of v scheduledJob.
	 */
	public void setJobResultData(final String jobResultData) {
		this.jobResultData = jobResultData;
	}

	/**
	 * <p>
	 * Getter for the field <code>jobResultData</code>.
	 * </p>
	 *
	 * @return jobResultData of v scheduledJob.
	 */
	public String getJobResultData() {
		return jobResultData;
	}

	/**
	 * <p>
	 * Setter for the field <code>initiatedBy</code>.
	 * </p>
	 *
	 * @param initiatedBy
	 *            of v scheduledJob.
	 */
	public void setInitiatedBy(final String initiatedBy) {
		this.initiatedBy = initiatedBy;
	}

	/**
	 * <p>
	 * Getter for the field <code>initiatedBy</code>.
	 * </p>
	 *
	 * @return initiatedBy of v scheduledJob.
	 */
	public String getInitiatedBy() {
		return initiatedBy;
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
	 * csvRow.
	 * </p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	@Override
	public String csvRow() {
		final StringBuilder sb = new StringBuilder();
		sb.append(getCellValue(getHubId()));
		sb.append(',').append(getCellValue(getHubName()));
		sb.append(',').append(getCellValue(getJobName()));
		sb.append(',').append(getCellValue(getJobDate()));
		sb.append(',').append(getCellValue(getStartTime()));
		sb.append(',').append(getCellValue(getEndTime()));
		sb.append(',').append(getCellValue(getTimeTaken()));
		sb.append(',').append(getCellValue(escapeQuotes(getJobResultData())));
		sb.append(',').append(getCellValue(getInitiatedBy()));
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
		if (StringUtils.isNotEmpty(getStartTime())) {
			final Date dateObj = DateUtils.getDate(CommonFormats.DEFAULT_MYSQL_DATE_TIME_FORMAT, getStartTime());
			setStartTime(DateUtils.toString(CommonFormats.DATE_FORMAT_WITH_TIME, dateObj));
		}
		if (StringUtils.isNotEmpty(getEndTime())) {
			final Date dateObj = DateUtils.getDate(CommonFormats.DEFAULT_MYSQL_DATE_TIME_FORMAT, getEndTime());
			setEndTime(DateUtils.toString(CommonFormats.DATE_FORMAT_WITH_TIME, dateObj));
		}

	}

	/**
	 * <p>
	 * exchangeJSONData.
	 * </p>
	 */
	@SuppressWarnings("unchecked")
	public void exchangeJSONData() {
		if (StringUtils.isNotEmpty(getJobResultData())) {
			jobResult = JSONUtils.jsonToObject(getJobResultData(), Map.class);
		}
	}


}
