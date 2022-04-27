package com.milkbasket.rest.services.scheduledjob.report.repository;

import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.core.framework.dbsupport.jdbc.repository.JdbcTemplateRepository;
import com.milkbasket.rest.services.scheduledjob.report.bean.ScheduledJobPageRequestBean;
import com.milkbasket.rest.services.scheduledjob.report.bean.ScheduledJobSummaryPageRequestBean;
import com.milkbasket.rest.services.scheduledjob.report.bean.ScheduledJobSummaryViewBean;
import com.milkbasket.rest.services.scheduledjob.report.bean.ScheduledJobViewBean;

/**
 * <p>
 * ScheduledJobReportRepository interface.
 * </p>
 *
 * @author Neeraj
 * @version $Id: $Id
 */
public interface ScheduledJobReportRepository extends JdbcTemplateRepository {

	/**
	 * <p>
	 * getScheduledJobDataList.
	 * </p>
	 *
	 * @param pageRequestBody
	 *            a
	 *            {@link com.milkbasket.rest.services.scheduledjob.report.bean.ScheduledJobPageRequestBean}
	 *            object.
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult}
	 *         object.
	 */
	PageAndSortResult<ScheduledJobViewBean> getScheduledJobDataList(ScheduledJobPageRequestBean pageRequestBody);

	/**
	 *
	 * @param pageRequestBean
	 * @return
	 */
	PageAndSortResult<ScheduledJobSummaryViewBean> getScheduledJobSummaryDataList(ScheduledJobSummaryPageRequestBean pageRequestBody);

}
