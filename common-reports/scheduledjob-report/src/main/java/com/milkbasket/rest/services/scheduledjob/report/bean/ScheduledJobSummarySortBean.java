package com.milkbasket.rest.services.scheduledjob.report.bean;

import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection;
import com.milkbasket.core.framework.websupport.base.bean.SortBean;

/**
 * <p>
 * ScheduledJobSummarySortBean class.
 * </p>
 *
 * @author vipulagarwal
 * @version $Id: $Id
 */
public class ScheduledJobSummarySortBean extends SortBean {

	private static final long serialVersionUID = -1732804807815594572L;

	private SortDirection hubId;

	private SortDirection jobDate;

	private SortDirection startTime;

	private SortDirection endTime;

	private SortDirection averageTime;

	private SortDirection jobName;

	private SortDirection jobStatus;

	private Integer count;

	/**
	 * @return the hubId
	 */
	public SortDirection getHubId() {
		return hubId;
	}

	/**
	 * @param hubId
	 *            the hubId to set
	 */
	public void setHubId(final SortDirection hubId) {
		this.hubId = hubId;
	}

	/**
	 * @return the jobDate
	 */
	public SortDirection getJobDate() {
		return jobDate;
	}

	/**
	 * @param jobDate
	 *            the jobDate to set
	 */
	public void setJobDate(final SortDirection jobDate) {
		this.jobDate = jobDate;
	}

	/**
	 * @return the startTime
	 */
	public SortDirection getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(final SortDirection startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public SortDirection getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(final SortDirection endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the averageTime
	 */
	public SortDirection getAverageTime() {
		return averageTime;
	}

	/**
	 * @param averageTime
	 *            the averageTime to set
	 */
	public void setAverageTime(final SortDirection averageTime) {
		this.averageTime = averageTime;
	}

	/**
	 * @return the jobName
	 */
	public SortDirection getJobName() {
		return jobName;
	}

	/**
	 * @param jobName
	 *            the jobName to set
	 */
	public void setJobName(final SortDirection jobName) {
		this.jobName = jobName;
	}

	/**
	 * @return the jobStatus
	 */
	public SortDirection getJobStatus() {
		return jobStatus;
	}

	/**
	 * @param jobStatus
	 *            the jobStatus to set
	 */
	public void setJobStatus(final SortDirection jobStatus) {
		this.jobStatus = jobStatus;
	}

	/**
	 * @return the count
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(final Integer count) {
		this.count = count;
	}



}
