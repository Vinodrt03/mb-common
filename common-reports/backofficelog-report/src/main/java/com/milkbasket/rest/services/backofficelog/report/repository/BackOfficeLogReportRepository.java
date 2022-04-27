package com.milkbasket.rest.services.backofficelog.report.repository;

import java.util.List;

import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.core.framework.dbsupport.jdbc.repository.JdbcTemplateRepository;
import com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogPageRequestBean;
import com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogSummaryFilterBean;
import com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogSummaryViewBean;
import com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogViewBean;

/**
 * Filter bean to fetch list of backoffice logs
 *
 * @author NitinKhaitan
 * @version $Id: $Id
 */
public interface BackOfficeLogReportRepository extends JdbcTemplateRepository {

	/**
	 * <p>
	 * getBackOfficeLogDataList.
	 * </p>
	 *
	 * @param pageRequestBody
	 *            a
	 *            {@link com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogPageRequestBean}
	 *            object.
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult}
	 *         object.
	 */
	PageAndSortResult<BackOfficeLogViewBean> getBackOfficeLogDataList(BackOfficeLogPageRequestBean pageRequestBody);

	/**
	 * <p>
	 * getBackOfficeLogSummary.
	 * </p>
	 *
	 * @param filter
	 *            a
	 *            {@link com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogSummaryFilterBean}
	 *            object.
	 * @return a {@link java.util.List} object.
	 */
	List<BackOfficeLogSummaryViewBean> getBackOfficeLogSummary(BackOfficeLogSummaryFilterBean filter);
}
