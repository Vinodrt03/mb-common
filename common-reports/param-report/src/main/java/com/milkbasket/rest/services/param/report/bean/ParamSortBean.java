package com.milkbasket.rest.services.param.report.bean;

import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection;
import com.milkbasket.core.framework.websupport.base.bean.SortBean;

/**
 * <p>
 * ParamSortBean class.
 * </p>
 *
 * @author Neeraj
 * @version $Id: $Id
 */
public class ParamSortBean extends SortBean {

	private static final long serialVersionUID = 201901241745570884L;

	private SortDirection paramKey;

	/**
	 * <p>
	 * Getter for the field <code>paramKey</code>.
	 * </p>
	 *
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection}
	 *         object.
	 */
	public SortDirection getParamKey() {
		return paramKey;
	}

	/**
	 * <p>
	 * Setter for the field <code>paramKey</code>.
	 * </p>
	 *
	 * @param paramKey
	 *            a
	 *            {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection}
	 *            object.
	 */
	public void setParamKey(final SortDirection paramKey) {
		this.paramKey = paramKey;
	}

}
