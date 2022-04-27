package com.milkbasket.rest.services.scheduledjob.report.bean;

import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection;
import com.milkbasket.core.framework.websupport.base.bean.PageAndSortRequestBean;

/**
 * <p>
 * ScheduledJobSummaryPageRequestBean class.
 * </p>
 *
 * @author vipulagarwal
 * @version $Id: $Id
 */
public class ScheduledJobSummaryPageRequestBean extends PageAndSortRequestBean<ScheduledJobSummaryFilterBean, ScheduledJobSummarySortBean> {

	private static final long serialVersionUID = 9189757601473275486L;

	private static final ScheduledJobSummarySortBean DEFAULT_EXPORT_SORT = getDefaultExportSort();

	private static final ScheduledJobSummarySortBean DEFAULT_DATALIST_SORT = getDefaultDataListSort();

	private static ScheduledJobSummarySortBean getDefaultExportSort() {
		final ScheduledJobSummarySortBean defaultExportSort = new ScheduledJobSummarySortBean();
		defaultExportSort.setJobDate(SortDirection.DESC);
		return defaultExportSort;
	}

	private static ScheduledJobSummarySortBean getDefaultDataListSort() {
		final ScheduledJobSummarySortBean defaultExportSort = new ScheduledJobSummarySortBean();
		defaultExportSort.setJobDate(SortDirection.DESC);
		return defaultExportSort;
	}

	/**
	 * <p>
	 * createExportRequest.
	 * </p>
	 */
	public static ScheduledJobSummaryPageRequestBean createExportRequest(final ScheduledJobSummaryFilterBean filter) {
		final ScheduledJobSummaryPageRequestBean request = new ScheduledJobSummaryPageRequestBean();
		request.setPageNo(1);
		request.setPageSize(Integer.MAX_VALUE);
		request.setFilters(filter == null ? new ScheduledJobSummaryFilterBean() : filter);
		request.setSort(DEFAULT_EXPORT_SORT);
		return request;
	}

	/**
	 * <p>
	 * createDatalistRequest.
	 * </p>
	 */
	public static ScheduledJobSummaryPageRequestBean createDatalistRequest(ScheduledJobSummaryPageRequestBean request) {

		if (request == null) {
			request = new ScheduledJobSummaryPageRequestBean();
		}
		if (request.getFilters() == null) {
			request.setFilters(new ScheduledJobSummaryFilterBean());
		}
		request.getFilterCriteriaMap();
		if (!PageAndSortRequestBean.isSortSet(request)) {
			request.setSort(DEFAULT_DATALIST_SORT);
		}
		return request;

	}

}
