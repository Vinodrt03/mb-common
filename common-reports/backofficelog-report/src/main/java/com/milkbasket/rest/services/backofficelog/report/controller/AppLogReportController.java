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
import com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogFilterBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogPageRequestBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogSummaryFilterBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogSummaryViewBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogViewBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppLogFilterBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppLogPageRequestBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppLogSummaryFilterBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppLogSummaryViewBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppLogViewBean;
import com.milkbasket.rest.services.backofficelog.report.service.AppLogReportMongoService;
import com.milkbasket.rest.services.backofficelog.report.service.AppLogReportService;
import com.milkbasket.rest.services.backofficelog.report.validator.AppLogSummaryValidator;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
/**
 * <p>
 * AppLogReportController class.
 * </p>
 *
 * @author Sanjay Wadhwa
 * @version $Id: $Id
 */
@Api(tags = { "AppLog Report Services" }, description = "Manages AppLog Report related services")
public class AppLogReportController implements ReportSupportController {

	@Autowired
	private AppLogReportService appLogReportService;

	@Autowired
	private AppLogSummaryValidator appLogSummaryValidator;

	@Autowired
	private AppLogReportMongoService appLogReportMongoService;

	/**
	 * <p>
	 * findAppLogDataList.
	 * </p>
	 *
	 * @param request
	 *            a
	 *            {@link com.milkbasket.rest.services.backofficelog.report.bean.AppLogPageRequestBean}
	 *            object.
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult}
	 *         object.
	 */
	@ApiOperation(value = "List paginated AppLog related dataList information from application store.", nickname = "findAppLogDataList")
	@PostMapping("/app-logs/datalist")
	public PageAndSortResult<AppLogViewBean> findAppLogDataList(@Valid @RequestBody(required = false) AppLogPageRequestBean request,
			final Errors errors) {
		request = AppLogPageRequestBean.createDatalistRequest(request);
		appLogSummaryValidator.validate(request.getFilters(), errors);

		checkError(errors);
		PageAndSortResult<AppLogViewBean> result = new PageAndSortResult<>();
		if (request.getFilters().getNewDb() != null && request.getFilters().getNewDb() == 0) {
			result = appLogReportService.findAppLogDataList(request);
		} else {
			result = appLogReportMongoService.findAppLogDataList(request);
		}
		LoggingUtils.logAdminAction("datalist_app_log", request.getFilters().logInfo(result.getTotal()));
		return result;
	}

	/**
	 * <p>
	 * exportAppLogDataList.
	 * </p>
	 *
	 * @param filter
	 *            a
	 *            {@link com.milkbasket.rest.services.backofficelog.report.bean.AppLogFilterBean}
	 *            object.
	 * @param response
	 *            a {@link javax.servlet.http.HttpServletResponse} object.
	 */
	@ApiOperation(value = "Export AppLog related dataList information from application store.", nickname = "exportAppLogDataList")
	@PostMapping("/app-logs/export")
	public void exportAppLogDataList(@Valid @RequestBody(required = false) final AppLogFilterBean filter, final HttpServletResponse response,
			final Errors errors) {

		final AppLogPageRequestBean exportRequest = AppLogPageRequestBean.createExportRequest(filter);
		appLogSummaryValidator.validate(exportRequest.getFilters(), errors);

		checkError(errors);
		PageAndSortResult<AppLogViewBean> result = new PageAndSortResult<>();
		if (filter.getNewDb() != null && filter.getNewDb() == 0) {
			result = appLogReportService.exportAppLogDataList(exportRequest);
		} else {
			result = appLogReportMongoService.exportAppLogDataList(exportRequest);
		}
		final List<AppLogViewBean> results = result.getData();
		final String fileName = "applog_" + getExportSuffix();
		final byte[] data = convertToCSV(AppLogViewBean.HEADER, results).getBytes(Charset.forName("UTF-8"));

		LoggingUtils.logAdminAction("export_app_log", exportRequest.getFilters().logInfo(result.getTotal()));
		writeOnResponseStream(response, data, ResponseMediaType.TEXT_CSV, fileName);
	}

	/**
	 * <p>
	 * findAppErrorLogDataList.
	 * </p>
	 *
	 * @param request
	 *            a
	 *            {@link com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogPageRequestBean}
	 *            object.
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult}
	 *         object.
	 */
	@ApiOperation(value = "List paginated AppErrorLog related dataList information from application store.", nickname = "findAppErrorLogDataList")
	@PostMapping("/apperror-logs/datalist")
	public PageAndSortResult<AppErrorLogViewBean> findAppErrorLogDataList(@Valid @RequestBody(required = false) AppErrorLogPageRequestBean request,
			final Errors errors) {
		request = AppErrorLogPageRequestBean.createDatalistRequest(request);
		appLogSummaryValidator.validate(request.getFilters(), errors);

		checkError(errors);
		PageAndSortResult<AppErrorLogViewBean> result = new PageAndSortResult<>();
		if (request.getFilters().getNewDb() != null && request.getFilters().getNewDb() == 0) {
			result = appLogReportService.findAppErrorLogDataList(request);
		} else {
			result = appLogReportMongoService.findAppErrorLogDataList(request);
		}
		LoggingUtils.logAdminAction("datalist_apperror_log", request.getFilters().logInfo(result.getTotal()));
		return result;
	}

	/**
	 * <p>
	 * exportAppErrorLogDataList.
	 * </p>
	 *
	 * @param filter
	 *            a
	 *            {@link com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogFilterBean}
	 *            object.
	 * @param response
	 *            a {@link javax.servlet.http.HttpServletResponse} object.
	 */
	@ApiOperation(value = "Export AppErrorLog related dataList information from application store.", nickname = "exportAppErrorLogDataList")
	@PostMapping("/apperror-logs/export")
	public void exportAppErrorLogDataList(@Valid @RequestBody(required = false) final AppErrorLogFilterBean filter,
			final HttpServletResponse response, final Errors errors) {

		final AppErrorLogPageRequestBean exportRequest = AppErrorLogPageRequestBean.createExportRequest(filter);
		appLogSummaryValidator.validate(exportRequest.getFilters(), errors);

		checkError(errors);
		PageAndSortResult<AppErrorLogViewBean> result = new PageAndSortResult<>();
		if (filter.getNewDb() != null && filter.getNewDb() == 0) {
			result = appLogReportService.exportAppErrorLogDataList(exportRequest);
		} else {
			result = appLogReportMongoService.exportAppErrorLogDataList(exportRequest);
		}
		final List<AppErrorLogViewBean> results = result.getData();
		final String fileName = "applog_" + getExportSuffix();
		final byte[] data = convertToCSV(AppErrorLogViewBean.HEADER, results).getBytes(Charset.forName("UTF-8"));

		LoggingUtils.logAdminAction("export_apperror_log", exportRequest.getFilters().logInfo(result.getTotal()));
		writeOnResponseStream(response, data, ResponseMediaType.TEXT_CSV, fileName);
	}

	/**
	 * <p>
	 * findAppLogSummaryDataList.
	 * </p>
	 *
	 * @param filter
	 *            a
	 *            {@link com.milkbasket.rest.services.backofficelog.report.bean.AppLogSummaryFilterBean}
	 *            object.
	 * @param errors
	 *            a {@link org.springframework.validation.Errors} object.
	 * @return a {@link java.util.List} object.
	 */
	@ApiOperation(value = "List paginated AppLog summary related dataList information from application store.", nickname = "findAppLogSummaryDataList")
	@PostMapping("/app-logs/summary")
	public List<AppLogSummaryViewBean> findAppLogSummaryDataList(@Valid @RequestBody(required = false) AppLogSummaryFilterBean filter,
			final Errors errors) {
		if (filter == null) {
			filter = new AppLogSummaryFilterBean();
		}

		appLogSummaryValidator.validate(filter, errors);

		checkError(errors);

		final List<AppLogSummaryViewBean> result = appLogReportService.findAppLogSummary(filter);

		LoggingUtils.logAdminAction("datalist_app_summary_log", filter.logInfo(result.size()));

		return result;
	}

	/**
	 * <p>
	 * findAppErrorLogSummaryDataList.
	 * </p>
	 *
	 * @param filter
	 *            a
	 *            {@link com.milkbasket.rest.services.backofficelog.report.bean.AppLogSummaryFilterBean}
	 *            object.
	 * @param errors
	 *            a {@link org.springframework.validation.Errors} object.
	 * @return a {@link java.util.List} object.
	 */
	@ApiOperation(value = "List paginated AppErrorLog summary related dataList information from application store.", nickname = "findAppErrorLogSummaryDataList")
	@PostMapping("/apperror-logs/summary")
	public List<AppErrorLogSummaryViewBean> findAppErrorLogSummaryDataList(@Valid @RequestBody(required = false) AppErrorLogSummaryFilterBean filter,
			final Errors errors) {
		if (filter == null) {
			filter = new AppErrorLogSummaryFilterBean();
		}

		appLogSummaryValidator.validate(filter, errors);

		checkError(errors);

		final List<AppErrorLogSummaryViewBean> result = appLogReportService.findAppErrorLogSummary(filter);

		LoggingUtils.logAdminAction("datalist_apperror_summary_log", filter.logInfo(result.size()));

		return result;
	}

}
