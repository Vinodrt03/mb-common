package com.milkbasket.rest.services.scheduledjob.report.controller;

import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.milkbasket.core.framework.common.constants.ResponseMediaType;
import com.milkbasket.core.framework.common.utils.LoggingUtils;
import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.core.framework.websupport.base.controller.ReportSupportController;
import com.milkbasket.rest.services.scheduledjob.report.bean.ScheduledJobFilterBean;
import com.milkbasket.rest.services.scheduledjob.report.bean.ScheduledJobPageRequestBean;
import com.milkbasket.rest.services.scheduledjob.report.bean.ScheduledJobSummaryPageRequestBean;
import com.milkbasket.rest.services.scheduledjob.report.bean.ScheduledJobSummaryViewBean;
import com.milkbasket.rest.services.scheduledjob.report.bean.ScheduledJobViewBean;
import com.milkbasket.rest.services.scheduledjob.report.service.ScheduledJobReportService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
/**
 * <p>
 * ScheduledJobReportController class.
 * </p>
 *
 * @author Neeraj
 * @version $Id: $Id
 */
@Api(tags = { "ScheduledJob Report Services" }, description = "Manages ScheduledJob Report related services")
public class ScheduledJobReportController implements ReportSupportController {

	@Autowired
	private ScheduledJobReportService scheduledJobReportService;

	/**
	 * <p>
	 * findScheduledJobDataList.
	 * </p>
	 *
	 * @param request
	 *            a
	 *            {@link com.milkbasket.rest.services.scheduledjob.report.bean.ScheduledJobPageRequestBean}
	 *            object.
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult}
	 *         object.
	 */
	@ApiOperation(value = "List paginated ScheduledJob related dataList information from application store.", nickname = "findScheduledJobDataList")
	@PostMapping("/scheduled-jobs/datalist")
	public PageAndSortResult<ScheduledJobViewBean> findScheduledJobDataList(
			@Valid @RequestBody(required = false) ScheduledJobPageRequestBean request) {
		request = ScheduledJobPageRequestBean.createDatalistRequest(request);
		final PageAndSortResult<ScheduledJobViewBean> result = scheduledJobReportService.findScheduledJobDataList(request);
		LoggingUtils.logAdminAction("datalist_scheduled_job", request.getFilters().logInfo(result.getTotal()));
		return result;
	}

	/**
	 * <p>
	 * exportScheduledJobDataList.
	 * </p>
	 *
	 * @param filter
	 *            a
	 *            {@link com.milkbasket.rest.services.scheduledjob.report.bean.ScheduledJobFilterBean}
	 *            object.
	 * @param response
	 *            a {@link javax.servlet.http.HttpServletResponse} object.
	 */
	@ApiOperation(value = "Export ScheduledJob related dataList information from application store.", nickname = "exportScheduledJobDataList")
	@PostMapping("/scheduled-jobs/export")
	public void exportScheduledJobDataList(@Valid @RequestBody(required = false) final ScheduledJobFilterBean filter,
			final HttpServletResponse response) {

		final ScheduledJobPageRequestBean exportRequest = ScheduledJobPageRequestBean.createExportRequest(filter);
		final PageAndSortResult<ScheduledJobViewBean> result = scheduledJobReportService.exportScheduledJobDataList(exportRequest);
		final List<ScheduledJobViewBean> results = result.getData();
		final String fileName = "scheduledjob_" + getExportSuffix();
		final byte[] data = convertToCSV(ScheduledJobViewBean.HEADER, results).getBytes(Charset.forName("UTF-8"));

		LoggingUtils.logAdminAction("export_scheduled_job", exportRequest.getFilters().logInfo(result.getTotal()));
		writeOnResponseStream(response, data, ResponseMediaType.TEXT_CSV, fileName);
	}

	/**
	 *
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "List paginated ScheduledJob summary related dataList information from application store.", nickname = "getScheduledJobSummary")
	@PostMapping("/scheduled-jobs/summary/datalist")
	public PageAndSortResult<ScheduledJobSummaryViewBean> getScheduledJobsSummaryDataList(
			@Valid @RequestBody(required = false) final ScheduledJobSummaryPageRequestBean request) {
		// request = ScheduledJobSummaryPageRequestBean.createDatalistRequest(request);
		final PageAndSortResult<ScheduledJobSummaryViewBean> result = scheduledJobReportService.findScheduledJobSummaryDataList(request);
		LoggingUtils.logAdminAction("datalist_scheduled_job_summary", request.getFilters().logInfo(result.getTotal()));
		return result;
	}

}
