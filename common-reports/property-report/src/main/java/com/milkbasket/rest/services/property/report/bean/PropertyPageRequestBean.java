package com.milkbasket.rest.services.property.report.bean;

import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection;
import com.milkbasket.core.framework.websupport.base.bean.PageAndSortRequestBean;

/**
 * <p>
 * PropertyPageRequestBean class.
 * </p>
 *
 * @author Tanuja
 * @version $Id: $Id
 */
public class PropertyPageRequestBean extends PageAndSortRequestBean<PropertyFilterBean, PropertySortBean> {

	private static final long serialVersionUID = 201901241745570886L;

	private static final PropertySortBean DEFAULT_DATALIST_SORT = getDefaultDataListSort();

	private static PropertySortBean getDefaultDataListSort() {
		final PropertySortBean defaultExportSort = new PropertySortBean();
		defaultExportSort.setPropertyKey(SortDirection.ASC);
		return defaultExportSort;
	}

	/**
	 * <p>
	 * createDatalistRequest.
	 * </p>
	 *
	 * @property viewRequest a
	 *           {@link com.milkbasket.rest.services.property.report.bean.PropertyPageRequestBean}
	 *           object.
	 * @return a
	 *         {@link com.milkbasket.rest.services.property.report.bean.PropertyPageRequestBean}
	 *         object.
	 * @param viewRequest
	 *            a
	 *            {@link com.milkbasket.rest.services.property.report.bean.PropertyPageRequestBean}
	 *            object.
	 */
	public static PropertyPageRequestBean createDatalistRequest(PropertyPageRequestBean viewRequest) {
		if (viewRequest == null) {
			viewRequest = new PropertyPageRequestBean();
		}

		if (viewRequest.getFilters() == null) {
			viewRequest.setFilters(new PropertyFilterBean());
		}
		if (!PageAndSortRequestBean.isSortSet(viewRequest)) {
			viewRequest.setSort(DEFAULT_DATALIST_SORT);
		}
		return viewRequest;
	}

}
