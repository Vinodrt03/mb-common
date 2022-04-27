package com.milkbasket.rest.services.scheduledjob.report.service;

import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.rest.services.scheduledjob.report.bean.ScheduledJobPageRequestBean;
import com.milkbasket.rest.services.scheduledjob.report.bean.ScheduledJobSummaryPageRequestBean;
import com.milkbasket.rest.services.scheduledjob.report.bean.ScheduledJobSummaryViewBean;
import com.milkbasket.rest.services.scheduledjob.report.bean.ScheduledJobViewBean;

/**
 * <p>
 * ScheduledJobReportService interface.
 * </p>
 *
 * @author Neeraj
 * @version $Id: $Id
 */
public interface ScheduledJobReportService {

	/**
	 * <p>
	 * findScheduledJobDataList.
	 * </p>
	 *
	 * @param pageRequestBean
	 *            a
	 *            {@link com.milkbasket.rest.services.scheduledjob.report.bean.ScheduledJobPageRequestBean}
	 *            object.
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult}
	 *         object.
	 */
	PageAndSortResult<ScheduledJobViewBean> findScheduledJobDataList(ScheduledJobPageRequestBean pageRequestBean);

	/**
	 * <p>
	 * exportScheduledJobDataList.
	 * </p>
	 *
	 * @param pageRequestBean
	 *            a
	 *            {@link com.milkbasket.rest.services.scheduledjob.report.bean.ScheduledJobPageRequestBean}
	 *            object.
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult}
	 *         object.
	 */
	PageAndSortResult<ScheduledJobViewBean> exportScheduledJobDataList(ScheduledJobPageRequestBean pageRequestBean);

	/**
	 *
	 * @param pageRequestBean
	 * @return
	 */
	PageAndSortResult<ScheduledJobSummaryViewBean> findScheduledJobSummaryDataList(ScheduledJobSummaryPageRequestBean pageRequestBean);

}
