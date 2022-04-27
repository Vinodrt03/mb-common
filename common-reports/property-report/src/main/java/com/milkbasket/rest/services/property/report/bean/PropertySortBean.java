package com.milkbasket.rest.services.property.report.bean;

import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection;
import com.milkbasket.core.framework.websupport.base.bean.SortBean;

/**
 * <p>
 * PropertySortBean class.
 * </p>
 *
 * @author Tanuja
 * @version $Id: $Id
 */
public class PropertySortBean extends SortBean {

	private static final long serialVersionUID = 201901241745570884L;

	private SortDirection propertyKey;

	/**
	 * <p>
	 * Getter for the field <code>propertyKey</code>.
	 * </p>
	 *
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection}
	 *         object.
	 */
	public SortDirection getPropertyKey() {
		return propertyKey;
	}

	/**
	 * <p>
	 * Setter for the field <code>propertyKey</code>.
	 * </p>
	 *
	 * @property propertyKey a
	 *           {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection}
	 *           object.
	 * @param propertyKey
	 *            a
	 *            {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection}
	 *            object.
	 */
	public void setPropertyKey(final SortDirection propertyKey) {
		this.propertyKey = propertyKey;
	}

}
