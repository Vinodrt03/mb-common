package com.milkbasket.rest.services.backofficelog.report.bean;

import com.milkbasket.core.framework.common.constants.CommonConstants;
import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection;
import com.milkbasket.core.framework.property.utils.PropertiesUtils;
import com.milkbasket.core.framework.websupport.base.bean.PageAndSortRequestBean;

/**
 * Page Request bean to fetch list of backoffice logs
 *
 * @author NitinKhaitan
 * @version $Id: $Id
 */
public class BackOfficeLogPageRequestBean extends PageAndSortRequestBean<BackOfficeLogFilterBean, BackOfficeLogSortBean> {

	private static final long serialVersionUID = 201806151645380998L;

	private static final BackOfficeLogSortBean DEFAULT_EXPORT_SORT = getDefaultExportSort();

	private static final BackOfficeLogSortBean DEFAULT_DATALIST_SORT = getDefaultDataListSort();

	private static BackOfficeLogSortBean getDefaultExportSort() {
		final BackOfficeLogSortBean defaultExportSort = new BackOfficeLogSortBean();
		defaultExportSort.setDateTime(SortDirection.DESC);
		return defaultExportSort;
	}

	private static BackOfficeLogSortBean getDefaultDataListSort() {
		final BackOfficeLogSortBean defaultExportSort = new BackOfficeLogSortBean();
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
	 *            {@link com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogFilterBean}
	 *            object.
	 * @return a
	 *         {@link com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogPageRequestBean}
	 *         object.
	 */
	public static BackOfficeLogPageRequestBean createExportRequest(final BackOfficeLogFilterBean filter) {
		final BackOfficeLogPageRequestBean request = new BackOfficeLogPageRequestBean();
		request.setPageNo(1);
		request.setPageSize(PropertiesUtils.getIntegerProperty(CommonConstants.MAX_RECORD_LIMIT_EXPORT, 10000));
		request.setFilters(filter == null ? new BackOfficeLogFilterBean() : filter);
		request.setSort(DEFAULT_EXPORT_SORT);
		return request;
	}

	/**
	 * <p>
	 * createDatalistRequest.
	 * </p>
	 *
	 * @return a
	 *         {@link com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogPageRequestBean}
	 *         object.
	 * @param request
	 *            a
	 *            {@link com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogPageRequestBean}
	 *            object.
	 */
	public static BackOfficeLogPageRequestBean createDatalistRequest(BackOfficeLogPageRequestBean request) {
		if (request == null) {
			request = new BackOfficeLogPageRequestBean();
		}
		if (request.getFilters() == null) {
			request.setFilters(new BackOfficeLogFilterBean());
		}
		request.getFilterCriteriaMap();
		if (!PageAndSortRequestBean.isSortSet(request)) {
			request.setSort(DEFAULT_DATALIST_SORT);
		}
		return request;

	}

}
