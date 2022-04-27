package com.milkbasket.rest.services.backofficelog.report.bean;

import com.milkbasket.core.framework.common.constants.CommonConstants;
import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection;
import com.milkbasket.core.framework.property.utils.PropertiesUtils;
import com.milkbasket.core.framework.websupport.base.bean.PageAndSortRequestBean;

/**
 * <p>
 * DbLogPageRequestBean class.
 * </p>
 *
 * @author nitin
 * @version $Id: $Id
 */
public class DbLogPageRequestBean extends PageAndSortRequestBean<DbLogFilterBean, DbLogSortBean> {

	private static final long serialVersionUID = 201809022237000000L;

	private static final DbLogSortBean DEFAULT_EXPORT_SORT = getDefaultExportSort();

	private static final DbLogSortBean DEFAULT_DATALIST_SORT = getDefaultDataListSort();

	private static DbLogSortBean getDefaultExportSort() {
		final DbLogSortBean defaultExportSort = new DbLogSortBean();
		defaultExportSort.setDate(SortDirection.DESC);
		return defaultExportSort;
	}

	private static DbLogSortBean getDefaultDataListSort() {
		final DbLogSortBean defaultExportSort = new DbLogSortBean();
		defaultExportSort.setDate(SortDirection.DESC);
		return defaultExportSort;
	}

	/**
	 * <p>
	 * createExportRequest.
	 * </p>
	 *
	 * @param filter
	 *            a
	 *            {@link com.milkbasket.rest.services.backofficelog.report.bean.DbLogFilterBean}
	 *            object.
	 * @return a
	 *         {@link com.milkbasket.rest.services.backofficelog.report.bean.DbLogPageRequestBean}
	 *         object.
	 */
	public static DbLogPageRequestBean createExportRequest(final DbLogFilterBean filter) {

		final DbLogPageRequestBean request = new DbLogPageRequestBean();
		request.setPageNo(1);
		request.setPageSize(PropertiesUtils.getIntegerProperty(CommonConstants.MAX_RECORD_LIMIT_EXPORT, 10000));
		request.setFilters(filter == null ? new DbLogFilterBean() : filter);
		request.setSort(DEFAULT_EXPORT_SORT);
		return request;
	}

	/**
	 * <p>
	 * createDatalistRequest.
	 * </p>
	 *
	 * @return a
	 *         {@link com.milkbasket.rest.services.backofficelog.report.bean.DbLogPageRequestBean}
	 *         object.
	 * @param request
	 *            a
	 *            {@link com.milkbasket.rest.services.backofficelog.report.bean.DbLogPageRequestBean}
	 *            object.
	 */
	public static DbLogPageRequestBean createDatalistRequest(DbLogPageRequestBean request) {
		if (request == null) {
			request = new DbLogPageRequestBean();
		}
		if (request.getFilters() == null) {
			request.setFilters(new DbLogFilterBean());
		}
		request.getFilterCriteriaMap();
		if (!PageAndSortRequestBean.isSortSet(request)) {
			request.setSort(DEFAULT_DATALIST_SORT);
		}
		return request;

	}

}
