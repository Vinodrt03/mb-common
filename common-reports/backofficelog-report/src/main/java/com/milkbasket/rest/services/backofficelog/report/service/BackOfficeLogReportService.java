package com.milkbasket.rest.services.backofficelog.report.service;

import java.util.List;

import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogPageRequestBean;
import com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogSummaryFilterBean;
import com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogSummaryViewBean;
import com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogViewBean;

/**
 * Service to fetch list of backoffice logs
 *
 * @author NitinKhaitan
 * @version $Id: $Id
 */
public interface BackOfficeLogReportService {

	/**
	 * <p>
	 * findBackOfficeLogDataList.
	 * </p>
	 *
	 * @param pageRequestBean
	 *            a
	 *            {@link com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogPageRequestBean}
	 *            object.
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult}
	 *         object.
	 */
	PageAndSortResult<BackOfficeLogViewBean> findBackOfficeLogDataList(BackOfficeLogPageRequestBean pageRequestBean);

	/**
	 * <p>
	 * exportBackOfficeLogDataList.
	 * </p>
	 *
	 * @param pageRequestBean
	 *            a
	 *            {@link com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogPageRequestBean}
	 *            object.
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult}
	 *         object.
	 */
	PageAndSortResult<BackOfficeLogViewBean> exportBackOfficeLogDataList(BackOfficeLogPageRequestBean pageRequestBean);

	/**
	 * <p>
	 * findBackOfficeLogSummary.
	 * </p>
	 *
	 * @param filter
	 *            a
	 *            {@link com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogSummaryFilterBean}
	 *            object.
	 * @return a {@link java.util.List} object.
	 */
	List<BackOfficeLogSummaryViewBean> findBackOfficeLogSummary(BackOfficeLogSummaryFilterBean filter);
}
