package com.milkbasket.rest.services.backofficelog.report.bean;

import com.milkbasket.core.framework.common.constants.CommonConstants;
import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection;
import com.milkbasket.core.framework.property.utils.PropertiesUtils;
import com.milkbasket.core.framework.websupport.base.bean.PageAndSortRequestBean;

/**
 * <p>
 * AppErrorLogPageRequestBean class.
 * </p>
 *
 * @author Sanjay Wadhwa
 * @version $Id: $Id
 */
public class AppErrorLogPageRequestBean extends PageAndSortRequestBean<AppErrorLogFilterBean, AppErrorLogSortBean> {

	private static final long serialVersionUID = 201806281746440908L;

	private static final AppErrorLogSortBean DEFAULT_EXPORT_SORT = getDefaultExportSort();

	private static final AppErrorLogSortBean DEFAULT_DATALIST_SORT = getDefaultDataListSort();

	private static AppErrorLogSortBean getDefaultExportSort() {
		final AppErrorLogSortBean defaultExportSort = new AppErrorLogSortBean();
		defaultExportSort.setDateTime(SortDirection.DESC);
		return defaultExportSort;
	}

	private static AppErrorLogSortBean getDefaultDataListSort() {
		final AppErrorLogSortBean defaultExportSort = new AppErrorLogSortBean();
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
	 *            {@link com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogFilterBean}
	 *            object.
	 * @return a
	 *         {@link com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogPageRequestBean}
	 *         object.
	 */
	public static AppErrorLogPageRequestBean createExportRequest(final AppErrorLogFilterBean filter) {
		final AppErrorLogPageRequestBean request = new AppErrorLogPageRequestBean();
		request.setPageNo(1);
		request.setPageSize(PropertiesUtils.getIntegerProperty(CommonConstants.MAX_RECORD_LIMIT_EXPORT, 10000));
		request.setFilters(filter == null ? new AppErrorLogFilterBean() : filter);
		request.setSort(DEFAULT_EXPORT_SORT);
		return request;
	}

	/**
	 * <p>
	 * createDatalistRequest.
	 * </p>
	 *
	 * @return a
	 *         {@link com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogPageRequestBean}
	 *         object.
	 * @param request
	 *            a
	 *            {@link com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogPageRequestBean}
	 *            object.
	 */
	public static AppErrorLogPageRequestBean createDatalistRequest(AppErrorLogPageRequestBean request) {
		if (request == null) {
			request = new AppErrorLogPageRequestBean();
		}
		if (request.getFilters() == null) {
			request.setFilters(new AppErrorLogFilterBean());
		}
		request.getFilterCriteriaMap();
		if (!PageAndSortRequestBean.isSortSet(request)) {
			request.setSort(DEFAULT_DATALIST_SORT);
		}
		return request;

	}

}
