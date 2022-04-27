package com.milkbasket.rest.services.scheduledjob.report.bean;

import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection;
import com.milkbasket.core.framework.websupport.base.bean.SortBean;

/**
 * <p>
 * ScheduledJobSortBean class.
 * </p>
 *
 * @author Neeraj
 * @version $Id: $Id
 */
public class ScheduledJobSortBean extends SortBean {

	private static final long serialVersionUID = 201811281611160019L;

	private SortDirection hubId;

	private SortDirection jobDate;

	private SortDirection startTime;

	private SortDirection endTime;

	private SortDirection timeTaken;

	private SortDirection jobName;

	/**
	 * <p>
	 * Getter for the field <code>hubId</code>.
	 * </p>
	 *
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection}
	 *         object.
	 */
	public SortDirection getHubId() {
		return hubId;
	}

	/**
	 * <p>
	 * Setter for the field <code>hubId</code>.
	 * </p>
	 *
	 * @param hubId
	 *            a
	 *            {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection}
	 *            object.
	 */
	public void setHubId(final SortDirection hubId) {
		this.hubId = hubId;
	}

	/**
	 * <p>
	 * Getter for the field <code>jobDate</code>.
	 * </p>
	 *
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection}
	 *         object.
	 */
	public SortDirection getJobDate() {
		return jobDate;
	}

	/**
	 * <p>
	 * Setter for the field <code>jobDate</code>.
	 * </p>
	 *
	 * @param jobDate
	 *            a
	 *            {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection}
	 *            object.
	 */
	public void setJobDate(final SortDirection jobDate) {
		this.jobDate = jobDate;
	}

	/**
	 * <p>
	 * Getter for the field <code>startTime</code>.
	 * </p>
	 *
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection}
	 *         object.
	 */
	public SortDirection getStartTime() {
		return startTime;
	}

	/**
	 * <p>
	 * Setter for the field <code>startTime</code>.
	 * </p>
	 *
	 * @param startTime
	 *            a
	 *            {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection}
	 *            object.
	 */
	public void setStartTime(final SortDirection startTime) {
		this.startTime = startTime;
	}

	/**
	 * <p>
	 * Getter for the field <code>endTime</code>.
	 * </p>
	 *
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection}
	 *         object.
	 */
	public SortDirection getEndTime() {
		return endTime;
	}

	/**
	 * <p>
	 * Setter for the field <code>endTime</code>.
	 * </p>
	 *
	 * @param endTime
	 *            a
	 *            {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection}
	 *            object.
	 */
	public void setEndTime(final SortDirection endTime) {
		this.endTime = endTime;
	}

	/**
	 * <p>
	 * Getter for the field <code>timeTaken</code>.
	 * </p>
	 *
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection}
	 *         object.
	 */
	public SortDirection getTimeTaken() {
		return timeTaken;
	}

	/**
	 * <p>
	 * Setter for the field <code>timeTaken</code>.
	 * </p>
	 *
	 * @param timeTaken
	 *            a
	 *            {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection}
	 *            object.
	 */
	public void setTimeTaken(final SortDirection timeTaken) {
		this.timeTaken = timeTaken;
	}

	/**
	 * <p>
	 * Getter for the field <code>jobName</code>.
	 * </p>
	 *
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection}
	 *         object.
	 */
	public SortDirection getJobName() {
		return jobName;
	}

	/**
	 * <p>
	 * Setter for the field <code>jobName</code>.
	 * </p>
	 *
	 * @param jobName
	 *            a
	 *            {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection}
	 *            object.
	 */
	public void setJobName(final SortDirection jobName) {
		this.jobName = jobName;
	}

}
