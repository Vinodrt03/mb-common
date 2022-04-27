package com.milkbasket.rest.services.backofficelog.report.bean;

import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection;
import com.milkbasket.core.framework.websupport.base.bean.SortBean;

/**
 * <p>
 * DbLogSortBean class.
 * </p>
 *
 * @author nitin
 * @version $Id: $Id
 */
public class DbLogSortBean extends SortBean {

	private static final long serialVersionUID = 201809022236590994L;

	private SortDirection date;

	private SortDirection userName;

	/**
	 * <p>
	 * Getter for the field <code>date</code>.
	 * </p>
	 *
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection}
	 *         object.
	 */
	public SortDirection getDate() {
		return date;
	}

	/**
	 * <p>
	 * Setter for the field <code>date</code>.
	 * </p>
	 *
	 * @param date
	 *            a
	 *            {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection}
	 *            object.
	 */
	public void setDate(final SortDirection date) {
		this.date = date;
	}

	/**
	 * <p>
	 * Getter for the field <code>userName</code>.
	 * </p>
	 *
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection}
	 *         object.
	 */
	public SortDirection getUserName() {
		return userName;
	}

	/**
	 * <p>
	 * Setter for the field <code>userName</code>.
	 * </p>
	 *
	 * @param userName
	 *            a
	 *            {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection}
	 *            object.
	 */
	public void setUserName(final SortDirection userName) {
		this.userName = userName;
	}

}
