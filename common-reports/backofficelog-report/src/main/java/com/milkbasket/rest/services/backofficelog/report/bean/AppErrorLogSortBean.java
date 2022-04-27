package com.milkbasket.rest.services.backofficelog.report.bean;

import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection;
import com.milkbasket.core.framework.websupport.base.bean.SortBean;

/**
 * <p>
 * AppErrorLogSortBean class.
 * </p>
 *
 * @author Sanjay Wadhwa
 * @version $Id: $Id
 */
public class AppErrorLogSortBean extends SortBean {

	private static final long serialVersionUID = 201806281746440893L;

	private SortDirection id;

	private SortDirection dateTime;

	private SortDirection error;

	private SortDirection module;
	private SortDirection customerName;

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
	 * Getter for the field <code>dateTime</code>.
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
	 * Setter for the field <code>dateTime</code>.
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
	 * Getter for the field <code>error</code>.
	 * </p>
	 *
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection}
	 *         object.
	 */
	public SortDirection getError() {
		return error;
	}

	/**
	 * <p>
	 * Setter for the field <code>error</code>.
	 * </p>
	 *
	 * @param error
	 *            a
	 *            {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection}
	 *            object.
	 */
	public void setError(final SortDirection error) {
		this.error = error;
	}

	/**
	 * <p>
	 * Getter for the field <code>module</code>.
	 * </p>
	 *
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection}
	 *         object.
	 */
	public SortDirection getModule() {
		return module;
	}

	/**
	 * <p>
	 * Setter for the field <code>module</code>.
	 * </p>
	 *
	 * @param module
	 *            a
	 *            {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection}
	 *            object.
	 */
	public void setModule(final SortDirection module) {
		this.module = module;
	}

	/**
	 * <p>
	 * Getter for the field <code>customerName</code>.
	 * </p>
	 *
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection}
	 *         object.
	 */
	public SortDirection getCustomerName() {
		return customerName;
	}

	/**
	 * <p>
	 * Setter for the field <code>customerName</code>.
	 * </p>
	 *
	 * @param customerName
	 *            a
	 *            {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection}
	 *            object.
	 */
	public void setCustomerName(final SortDirection customerName) {
		this.customerName = customerName;
	}

}
