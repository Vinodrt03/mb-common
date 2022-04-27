package com.milkbasket.rest.services.backofficelog.report.bean;

import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection;
import com.milkbasket.core.framework.websupport.base.bean.SortBean;

/**
 * Sort bean to fetch list of backoffice logs
 *
 * @author NitinKhaitan
 * @version $Id: $Id
 */
public class BackOfficeLogSortBean extends SortBean {

	private static final long serialVersionUID = 201806151645380994L;

	private SortDirection id;

	private SortDirection dateTime;

	private SortDirection action;
	private SortDirection name;

	/**
	 * <p>
	 * Getter for the field <code>id</code>.
	 * </p>
	 *
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection}
	 *         object.
	 */
	public SortDirection getId() {
		return id;
	}

	/**
	 * <p>
	 * Setter for the field <code>id</code>.
	 * </p>
	 *
	 * @param id
	 *            a
	 *            {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection}
	 *            object.
	 */
	public void setId(final SortDirection id) {
		this.id = id;
	}

	/**
	 * <p>
	 * Getter for the field <code>date</code>.
	 * </p>
	 *
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection}
	 *         object.
	 */
	public SortDirection getDateTime() {
		return dateTime;
	}

	/**
	 * <p>
	 * Setter for the field <code>date time</code>.
	 * </p>
	 *
	 * @param dateTime
	 *            a
	 *            {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection}
	 *            object.
	 */
	public void setDateTime(final SortDirection dateTime) {
		this.dateTime = dateTime;
	}

	/**
	 * <p>
	 * Getter for the field <code>action</code>.
	 * </p>
	 *
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection}
	 *         object.
	 */
	public SortDirection getAction() {
		return action;
	}

	/**
	 * <p>
	 * Setter for the field <code>action</code>.
	 * </p>
	 *
	 * @param action
	 *            a
	 *            {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection}
	 *            object.
	 */
	public void setAction(final SortDirection action) {
		this.action = action;
	}

	/**
	 * <p>
	 * Getter for the field <code>name</code>.
	 * </p>
	 *
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection}
	 *         object.
	 */
	public SortDirection getName() {
		return name;
	}

	/**
	 * <p>
	 * Setter for the field <code>name</code>.
	 * </p>
	 *
	 * @param name
	 *            a
	 *            {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection}
	 *            object.
	 */
	public void setName(final SortDirection name) {
		this.name = name;
	}

}
