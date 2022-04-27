package com.milkbasket.rest.services.backofficelog.report.service;

import java.util.List;

import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
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
 * AppLogReportService interface.
 * </p>
 *
 * @author Sanjay Wadhwa
 * @version $Id: $Id
 */
public interface AppLogReportService {

	/**
	 * <p>
	 * findAppLogDataList.
	 * </p>
	 *
	 * @param pageRequestBean
	 *            a
	 *            {@link com.milkbasket.rest.services.backofficelog.report.bean.AppLogPageRequestBean}
	 *            object.
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult}
	 *         object.
	 */
	PageAndSortResult<AppLogViewBean> findAppLogDataList(AppLogPageRequestBean pageRequestBean);

	/**
	 * <p>
	 * exportAppLogDataList.
	 * </p>
	 *
	 * @param pageRequestBean
	 *            a
	 *            {@link com.milkbasket.rest.services.backofficelog.report.bean.AppLogPageRequestBean}
	 *            object.
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult}
	 *         object.
	 */
	PageAndSortResult<AppLogViewBean> exportAppLogDataList(AppLogPageRequestBean pageRequestBean);

	/**
	 * <p>
	 * findAppErrorLogDataList.
	 * </p>
	 *
	 * @param pageRequestBean
	 *            a
	 *            {@link com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogPageRequestBean}
	 *            object.
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult}
	 *         object.
	 */
	PageAndSortResult<AppErrorLogViewBean> findAppErrorLogDataList(AppErrorLogPageRequestBean pageRequestBean);

	/**
	 * <p>
	 * exportAppErrorLogDataList.
	 * </p>
	 *
	 * @param pageRequestBean
	 *            a
	 *            {@link com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogPageRequestBean}
	 *            object.
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult}
	 *         object.
	 */
	PageAndSortResult<AppErrorLogViewBean> exportAppErrorLogDataList(AppErrorLogPageRequestBean pageRequestBean);

	/**
	 * <p>
	 * findAppLogSummary.
	 * </p>
	 *
	 * @param filter
	 *            a
	 *            {@link com.milkbasket.rest.services.backofficelog.report.bean.AppLogSummaryFilterBean}
	 *            object.
	 * @return a {@link java.util.List} object.
	 */
	List<AppLogSummaryViewBean> findAppLogSummary(AppLogSummaryFilterBean filter);

	/**
	 * <p>
	 * findAppErrorLogSummary.
	 * </p>
	 *
	 * @param filter
	 *            a
	 *            {@link com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogSummaryFilterBean}
	 *            object.
	 * @return a {@link java.util.List} object.
	 */
	List<AppErrorLogSummaryViewBean> findAppErrorLogSummary(AppErrorLogSummaryFilterBean filter);

}
