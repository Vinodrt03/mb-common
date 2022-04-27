package com.milkbasket.rest.services.masterdata.report.bean;

import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection;
import com.milkbasket.core.framework.websupport.base.bean.SortBean;

/**
 * <p>
 * MasterDataSortBean class.
 * </p>
 *
 * @author Neeraj
 * @version $Id: $Id
 */
public class MasterDataSortBean extends SortBean {

	private static final long serialVersionUID = 201901162321010621L;

	private SortDirection masterKey;

	/**
	 * <p>
	 * Getter for the field <code>masterKey</code>.
	 * </p>
	 *
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection}
	 *         object.
	 */
	public SortDirection getMasterKey() {
		return masterKey;
	}

	/**
	 * <p>
	 * Setter for the field <code>masterKey</code>.
	 * </p>
	 *
	 * @param masterKey
	 *            a
	 *            {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection}
	 *            object.
	 */
	public void setMasterKey(final SortDirection masterKey) {
		this.masterKey = masterKey;
	}

}
