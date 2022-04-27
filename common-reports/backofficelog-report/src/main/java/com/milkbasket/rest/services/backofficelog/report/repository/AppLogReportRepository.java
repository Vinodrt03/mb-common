package com.milkbasket.rest.services.backofficelog.report.repository;

import java.util.List;

import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.core.framework.dbsupport.jdbc.repository.JdbcTemplateRepository;
import com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogPageRequestBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogSummaryFilterBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogSummaryViewBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogViewBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppLogPageRequestBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppLogSummaryFilterBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppLogSummaryViewBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppLogViewBean;

/**
 * <p>
 * AppLogReportRepository interface.
 * </p>
 *
 * @author Sanjay Wadhwa
 * @version $Id: $Id
 */
public interface AppLogReportRepository extends JdbcTemplateRepository {

	/**
	 * <p>
	 * getAppLogDataList.
	 * </p>
	 *
	 * @param pageRequestBody
	 *            a
	 *            {@link com.milkbasket.rest.services.backofficelog.report.bean.AppLogPageRequestBean}
	 *            object.
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult}
	 *         object.
	 */
	PageAndSortResult<AppLogViewBean> getAppLogDataList(AppLogPageRequestBean pageRequestBody);

	/**
	 * <p>
	 * getAppErrorLogDataList.
	 * </p>
	 *
	 * @param pageRequestBody
	 *            a
	 *            {@link com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogPageRequestBean}
	 *            object.
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult}
	 *         object.
	 */
	PageAndSortResult<AppErrorLogViewBean> getAppErrorLogDataList(AppErrorLogPageRequestBean pageRequestBody);

	/**
	 * <p>
	 * getAppLogSummary.
	 * </p>
	 *
	 * @param filter
	 *            a
	 *            {@link com.milkbasket.rest.services.backofficelog.report.bean.AppLogSummaryFilterBean}
	 *            object.
	 * @return a {@link java.util.List} object.
	 */
	List<AppLogSummaryViewBean> getAppLogSummary(AppLogSummaryFilterBean filter);

	/**
	 * <p>
	 * getAppErrorLogSummary.
	 * </p>
	 *
	 * @param filter
	 *            a
	 *            {@link com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogSummaryFilterBean}
	 *            object.
	 * @return a {@link java.util.List} object.
	 */
	List<AppErrorLogSummaryViewBean> getAppErrorLogSummary(AppErrorLogSummaryFilterBean filter);

}
