package com.milkbasket.rest.services.backofficelog.report.bean;

import com.milkbasket.core.framework.common.constants.CommonConstants;
import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection;
import com.milkbasket.core.framework.property.utils.PropertiesUtils;
import com.milkbasket.core.framework.websupport.base.bean.PageAndSortRequestBean;

/**
 * <p>
 * AppLogPageRequestBean class.
 * </p>
 *
 * @author Sanjay Wadhwa
 * @version $Id: $Id
 */
public class AppLogPageRequestBean extends PageAndSortRequestBean<AppLogFilterBean, AppLogSortBean> {

	private static final long serialVersionUID = 201806271705490611L;

	private static final AppLogSortBean DEFAULT_EXPORT_SORT = getDefaultExportSort();

	private static final AppLogSortBean DEFAULT_DATALIST_SORT = getDefaultDataListSort();

	private static AppLogSortBean getDefaultExportSort() {
		final AppLogSortBean defaultExportSort = new AppLogSortBean();
		defaultExportSort.setDateTime(SortDirection.DESC);
		return defaultExportSort;
	}

	private static AppLogSortBean getDefaultDataListSort() {
		final AppLogSortBean defaultExportSort = new AppLogSortBean();
		defaultExportSort.setDateTime(SortDirection.DESC);
		return defaultExportSort;
	}

	/**
	 * <p>
	 * createExportRequest.
	 * </p>
	 *
	 * @param filter
	 *            a
	 *            {@link com.milkbasket.rest.services.backofficelog.report.bean.AppLogFilterBean}
	 *            object.
	 * @return a
	 *         {@link com.milkbasket.rest.services.backofficelog.report.bean.AppLogPageRequestBean}
	 *         object.
	 */
	public static AppLogPageRequestBean createExportRequest(final AppLogFilterBean filter) {
		final AppLogPageRequestBean request = new AppLogPageRequestBean();
		request.setPageNo(1);
		request.setPageSize(PropertiesUtils.getIntegerProperty(CommonConstants.MAX_RECORD_LIMIT_EXPORT, 10000));
		request.setFilters(filter == null ? new AppLogFilterBean() : filter);
		request.setSort(DEFAULT_EXPORT_SORT);
		return request;
	}

	/**
	 * <p>
	 * createDatalistRequest.
	 * </p>
	 *
	 * @return a
	 *         {@link com.milkbasket.rest.services.backofficelog.report.bean.AppLogPageRequestBean}
	 *         object.
	 * @param request
	 *            a
	 *            {@link com.milkbasket.rest.services.backofficelog.report.bean.AppLogPageRequestBean}
	 *            object.
	 */
	public static AppLogPageRequestBean createDatalistRequest(AppLogPageRequestBean request) {

		if (request == null) {
			request = new AppLogPageRequestBean();
		}
		if (request.getFilters() == null) {
			request.setFilters(new AppLogFilterBean());
		}
		request.getFilterCriteriaMap();
		if (!PageAndSortRequestBean.isSortSet(request)) {
			request.setSort(DEFAULT_DATALIST_SORT);
		}
		return request;

	}

}
