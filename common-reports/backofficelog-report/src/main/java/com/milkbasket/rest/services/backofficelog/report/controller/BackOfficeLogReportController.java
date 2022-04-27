package com.milkbasket.rest.services.backofficelog.report.controller;

import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.milkbasket.core.framework.common.constants.ResponseMediaType;
import com.milkbasket.core.framework.common.utils.LoggingUtils;
import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.core.framework.websupport.base.controller.ReportSupportController;
import com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogFilterBean;
import com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogPageRequestBean;
import com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogSummaryFilterBean;
import com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogSummaryViewBean;
import com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogViewBean;
import com.milkbasket.rest.services.backofficelog.report.bean.DbLogFilterBean;
import com.milkbasket.rest.services.backofficelog.report.bean.DbLogPageRequestBean;
import com.milkbasket.rest.services.backofficelog.report.bean.DbLogViewBean;
import com.milkbasket.rest.services.backofficelog.report.service.BackOfficeLogReportMongoService;
import com.milkbasket.rest.services.backofficelog.report.service.BackOfficeLogReportService;
import com.milkbasket.rest.services.backofficelog.report.service.DbLogReportMongoService;
import com.milkbasket.rest.services.backofficelog.report.service.DbLogReportService;
import com.milkbasket.rest.services.backofficelog.report.validator.BackOfficeLogSummaryValidator;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Controller to fetch list of backoffice logs
 *
 * @author NitinKhaitan
 * @version $Id: $Id
 */
@RestController
@Api(tags = { "BackOfficeLog Report Services" }, description = "Manages BackOfficeLog Report related services")
public class BackOfficeLogReportController implements ReportSupportController {

	@Autowired
	private BackOfficeLogReportService backOfficeLogReportService;

	@Autowired
	private BackOfficeLogSummaryValidator backOfficeLogSummaryValidator;

	@Autowired
	private BackOfficeLogReportMongoService backOfficeLogReportMongoService;

	@Autowired
	private DbLogReportMongoService dbLogReportMongoService;

	/**
	 * <p>
	 * findBackOfficeLogDataList.
	 * </p>
	 *
	 * @param request
	 *            a
	 *            {@link com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogPageRequestBean}
	 *            object.
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult}
	 *         object.
	 */
	@ApiOperation(value = "List paginated BackOfficeLog related dataList information from application store.", nickname = "findBackOfficeLogDataList")
	@PostMapping("/backoffice-logs/datalist")
	public PageAndSortResult<BackOfficeLogViewBean> findBackOfficeLogDataList(
			@Valid @RequestBody(required = false) BackOfficeLogPageRequestBean request, final Errors errors) {

		request = BackOfficeLogPageRequestBean.createDatalistRequest(request);
		backOfficeLogSummaryValidator.validate(request.getFilters(), errors);

		checkError(errors);
		PageAndSortResult<BackOfficeLogViewBean> result = new PageAndSortResult<>();
		if (request.getFilters().getNewDb() != null && request.getFilters().getNewDb() == 0) {
			result = backOfficeLogReportService.findBackOfficeLogDataList(request);
		} else {
			result = backOfficeLogReportMongoService.findBackOfficeLogDataList(request);
		}
		LoggingUtils.logAdminAction("datalist_backoffice_log", request.getFilters().logInfo(result.getTotal()));

		return result;
	}

	/**
	 * <p>
	 * exportBackOfficeLogDataList.
	 * </p>
	 *
	 * @param filter
	 *            a
	 *            {@link com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogFilterBean}
	 *            object.
	 * @param response
	 *            a {@link javax.servlet.http.HttpServletResponse} object.
	 */
	@ApiOperation(value = "Export BackOfficeLog related dataList information from application store.", nickname = "exportBackOfficeLogDataList")
	@PostMapping("/backoffice-logs/export")
	public void exportBackOfficeLogDataList(@Valid @RequestBody(required = false) final BackOfficeLogFilterBean filter,
			final HttpServletResponse response, final Errors errors) {

		final BackOfficeLogPageRequestBean exportRequest = BackOfficeLogPageRequestBean.createExportRequest(filter);
		backOfficeLogSummaryValidator.validate(exportRequest.getFilters(), errors);

		checkError(errors);
		PageAndSortResult<BackOfficeLogViewBean> result = new PageAndSortResult<>();
		if (filter.getNewDb() != null && filter.getNewDb() == 0) {
			result = backOfficeLogReportService.exportBackOfficeLogDataList(exportRequest);
		} else {
			result = backOfficeLogReportMongoService.exportBackOfficeLogDataList(exportRequest);
		}
		final List<BackOfficeLogViewBean> results = result.getData();
		final String fileName = "backofficelog_" + getExportSuffix();
		final byte[] data = convertToCSV(BackOfficeLogViewBean.HEADER, results).getBytes(Charset.forName("UTF-8"));

		LoggingUtils.logAdminAction("export_backoffice_log", exportRequest.getFilters().logInfo(result.getTotal()));

		writeOnResponseStream(response, data, ResponseMediaType.TEXT_CSV, fileName);
	}

	/**
	 * <p>
	 * findBackOfficeLogSummaryDataList.
	 * </p>
	 *
	 * @param filter
	 *            a
	 *            {@link com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogSummaryFilterBean}
	 *            object.
	 * @param errors
	 *            a {@link org.springframework.validation.Errors} object.
	 * @return a {@link java.util.List} object.
	 */
	@ApiOperation(value = "List paginated BackOfficeLog summary related dataList information from application store.", nickname = "findBackOfficeLogSummaryDataList")
	@PostMapping("/backoffice-logs/summary")
	public List<BackOfficeLogSummaryViewBean> findBackOfficeLogSummaryDataList(
			@Valid @RequestBody(required = false) BackOfficeLogSummaryFilterBean filter, final Errors errors) {
		if (filter == null) {
			filter = new BackOfficeLogSummaryFilterBean();
		}

		backOfficeLogSummaryValidator.validate(filter, errors);

		checkError(errors);

		final List<BackOfficeLogSummaryViewBean> result = backOfficeLogReportService.findBackOfficeLogSummary(filter);

		LoggingUtils.logAdminAction("datalist_backoffice_summary_log", filter.logInfo(result.size()));

		return result;
	}

	@Autowired
	private DbLogReportService dbLogReportService;

	/**
	 * <p>
	 * findDbLogDataList.
	 * </p>
	 *
	 * @param request
	 *            a
	 *            {@link com.milkbasket.rest.services.backofficelog.report.bean.DbLogPageRequestBean}
	 *            object.
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult}
	 *         object.
	 */
	@ApiOperation(value = "List paginated DbLog related dataList information from application store.")
	@PostMapping("/db-logs/datalist")
	public PageAndSortResult<DbLogViewBean> findDbLogDataList(@Valid @RequestBody(required = false) DbLogPageRequestBean request,
			final Errors errors) {
		request = DbLogPageRequestBean.createDatalistRequest(request);
		backOfficeLogSummaryValidator.validate(request.getFilters(), errors);

		checkError(errors);
		PageAndSortResult<DbLogViewBean> result = new PageAndSortResult<>();
		if (request.getFilters().getNewDb() != null && request.getFilters().getNewDb() == 0) {
			result = dbLogReportService.findDbLogDataList(request);
		} else {
			result = dbLogReportMongoService.findDbLogDataList(request);
		}
		LoggingUtils.logAdminAction("datalist_db_log", request.getFilters().logInfo(result.getTotal()));
		return result;
	}

	/**
	 * <p>
	 * exportDbLogDataList.
	 * </p>
	 *
	 * @param filter
	 *            a
	 *            {@link com.milkbasket.rest.services.backofficelog.report.bean.DbLogFilterBean}
	 *            object.
	 * @param response
	 *            a {@link javax.servlet.http.HttpServletResponse} object.
	 */
	@ApiOperation(value = "Export DbLog related dataList information from application store.")
	@PostMapping("/db-logs/export")
	public void exportDbLogDataList(@Valid @RequestBody(required = true) final DbLogFilterBean filter, final HttpServletResponse response,
			final Errors errors) {
		// Assert.isTrue(PageAndSortRequestBean.isValidSortAndFilterRequest(request),
		// "Invalid Sort Bean");
		final DbLogPageRequestBean exportRequest = DbLogPageRequestBean.createExportRequest(filter);

		backOfficeLogSummaryValidator.validate(exportRequest.getFilters(), errors);

		checkError(errors);
		PageAndSortResult<DbLogViewBean> result = new PageAndSortResult<>();
		if (filter.getNewDb() != null && filter.getNewDb() == 0) {
			result = dbLogReportService.exportDbLogDataList(exportRequest);
		} else {
			result = dbLogReportMongoService.exportDbLogDataList(exportRequest);
		}
		final List<DbLogViewBean> results = result.getData();
		final String fileName = "dblog_" + getExportSuffix();
		final byte[] data = convertToCSV(DbLogViewBean.HEADER, results).getBytes(Charset.forName("UTF-8"));

		LoggingUtils.logAdminAction("export_db_log", exportRequest.getFilters().logInfo(result.getTotal()));
		writeOnResponseStream(response, data, ResponseMediaType.TEXT_CSV, fileName);
	}

}
