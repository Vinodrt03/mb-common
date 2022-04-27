package com.milkbasket.rest.services.scheduledjob.report.bean;

import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection;
import com.milkbasket.core.framework.websupport.base.bean.PageAndSortRequestBean;

/**
 * <p>
 * ScheduledJobPageRequestBean class.
 * </p>
 *
 * @author Neeraj
 * @version $Id: $Id
 */
public class ScheduledJobPageRequestBean extends PageAndSortRequestBean<ScheduledJobFilterBean, ScheduledJobSortBean> {

	private static final long serialVersionUID = 201811281611160023L;

	private static final ScheduledJobSortBean DEFAULT_EXPORT_SORT = getDefaultExportSort();

	private static final ScheduledJobSortBean DEFAULT_DATALIST_SORT = getDefaultDataListSort();

	private static ScheduledJobSortBean getDefaultExportSort() {
		final ScheduledJobSortBean defaultExportSort = new ScheduledJobSortBean();
		defaultExportSort.setJobDate(SortDirection.DESC);
		return defaultExportSort;
	}

	private static ScheduledJobSortBean getDefaultDataListSort() {
		final ScheduledJobSortBean defaultExportSort = new ScheduledJobSortBean();
		defaultExportSort.setJobDate(SortDirection.DESC);
		return defaultExportSort;
	}

	/**
	 * <p>
	 * createExportRequest.
	 * </p>
	 *
	 * @param filter
	 *            a
	 *            {@link com.milkbasket.rest.services.scheduledjob.report.bean.ScheduledJobFilterBean}
	 *            object.
	 * @return a
	 *         {@link com.milkbasket.rest.services.scheduledjob.report.bean.ScheduledJobPageRequestBean}
	 *         object.
	 */
	public static ScheduledJobPageRequestBean createExportRequest(final ScheduledJobFilterBean filter) {
		final ScheduledJobPageRequestBean request = new ScheduledJobPageRequestBean();
		request.setPageNo(1);
		request.setPageSize(Integer.MAX_VALUE);
		request.setFilters(filter == null ? new ScheduledJobFilterBean() : filter);
		request.setSort(DEFAULT_EXPORT_SORT);
		return request;
	}

	/**
	 * <p>
	 * createDatalistRequest.
	 * </p>
	 *
	 * @return a
	 *         {@link com.milkbasket.rest.services.scheduledjob.report.bean.ScheduledJobPageRequestBean}
	 *         object.
	 * @param request
	 *            a
	 *            {@link com.milkbasket.rest.services.scheduledjob.report.bean.ScheduledJobPageRequestBean}
	 *            object.
	 */
	public static ScheduledJobPageRequestBean createDatalistRequest(ScheduledJobPageRequestBean request) {

		if (request == null) {
			request = new ScheduledJobPageRequestBean();
		}
		if (request.getFilters() == null) {
			request.setFilters(new ScheduledJobFilterBean());
		}
		request.getFilterCriteriaMap();
		if (!PageAndSortRequestBean.isSortSet(request)) {
			request.setSort(DEFAULT_DATALIST_SORT);
		}
		return request;

	}

}
