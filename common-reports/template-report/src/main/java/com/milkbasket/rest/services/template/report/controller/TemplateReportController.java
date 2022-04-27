package com.milkbasket.rest.services.template.report.controller;

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
import com.milkbasket.rest.services.template.report.bean.TemplateBaseFilterBean;
import com.milkbasket.rest.services.template.report.bean.TemplateBasePageRequestBean;
import com.milkbasket.rest.services.template.report.bean.TemplateFilterBean;
import com.milkbasket.rest.services.template.report.bean.TemplatePageRequestBean;
import com.milkbasket.rest.services.template.report.bean.TemplateViewBean;
import com.milkbasket.rest.services.template.report.service.TemplateReportService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
/**
 * <p>
 * TemplateReportController class.
 * </p>
 *
 * @author Sanjay Wadhwa
 * @version $Id: $Id
 */
@Api(tags = { "Template Services" }, description = "Manages Template related services")
public class TemplateReportController implements ReportSupportController {

	@Autowired
	private TemplateReportService templateReportService;

	/**
	 * <p>
	 * findTemplateDataList.
	 * </p>
	 *
	 * @param request
	 *            a
	 *            {@link com.milkbasket.rest.services.template.report.bean.TemplatePageRequestBean}
	 *            object.
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult}
	 *         object.
	 */
	@ApiOperation(value = "List paginated Template related dataList information from application store.", nickname = "findTemplateDataList")
	@PostMapping("/message-templates/datalist")
	public PageAndSortResult<TemplateViewBean> findTemplateDataList(@Valid @RequestBody(required = false) TemplatePageRequestBean request) {
		request = TemplatePageRequestBean.createDatalistRequest(request);
		final PageAndSortResult<TemplateViewBean> result = templateReportService.findTemplateDataList(request);
		LoggingUtils.logAdminAction("datalist_msg_template", request.getFilters().logInfo(result.getTotal()));
		return result;
	}

	/**
	 * <p>
	 * exportTemplateDataList.
	 * </p>
	 *
	 * @param filter
	 *            a
	 *            {@link com.milkbasket.rest.services.template.report.bean.TemplateFilterBean}
	 *            object.
	 * @param response
	 *            a {@link javax.servlet.http.HttpServletResponse} object.
	 */
	@ApiOperation(value = "Export Template related dataList information from application store.", nickname = "exportTemplateDataList")
	@PostMapping("/message-templates/export")
	public void exportTemplateDataList(@Valid @RequestBody(required = false) final TemplateFilterBean filter, final HttpServletResponse response) {

		final TemplatePageRequestBean exportRequest = TemplatePageRequestBean.createExportRequest(filter);
		final PageAndSortResult<TemplateViewBean> result = templateReportService.exportTemplateDataList(exportRequest);
		final List<TemplateViewBean> results = result.getData();
		final String fileName = "template_" + getExportSuffix();
		final byte[] data = convertToCSV(TemplateViewBean.HEADER, results).getBytes(Charset.forName("UTF-8"));

		LoggingUtils.logAdminAction("export_msg_template", exportRequest.getFilters().logInfo(result.getTotal()));
		writeOnResponseStream(response, data, ResponseMediaType.TEXT_CSV, fileName);
	}

	/**
	 * <p>
	 * findTemplateDataListStopped.
	 * </p>
	 *
	 * @param request
	 *            a
	 *            {@link com.milkbasket.rest.services.template.report.bean.TemplateBasePageRequestBean}
	 *            object.
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult}
	 *         object.
	 */
	@ApiOperation(value = "List paginated Template related dataListStopped information from application store.", nickname = "findTemplateDataListStopped")
	@PostMapping("/message-templates/datalist/stopped")
	public PageAndSortResult<TemplateViewBean> findTemplateDataListStopped(
			@Valid @RequestBody(required = false) TemplateBasePageRequestBean request) {
		request = TemplateBasePageRequestBean.createDatalistRequest(request);
		final PageAndSortResult<TemplateViewBean> result = templateReportService.findTemplateDataListStopped(request);
		LoggingUtils.logAdminAction("datalist_msg_template_stopped", request.getFilters().logInfo(result.getTotal()));
		return result;
	}

	/**
	 * <p>
	 * exportTemplateDataListStopped.
	 * </p>
	 *
	 * @param filter
	 *            a
	 *            {@link com.milkbasket.rest.services.template.report.bean.TemplateBaseFilterBean}
	 *            object.
	 * @param response
	 *            a {@link javax.servlet.http.HttpServletResponse} object.
	 */
	@ApiOperation(value = "Export Template related dataListStopped information from application store.", nickname = "exportTemplateDataListStopped")
	@PostMapping("/message-templates/export/stopped")
	public void exportTemplateDataListStopped(@Valid @RequestBody(required = false) final TemplateBaseFilterBean filter,
			final HttpServletResponse response) {

		final TemplateBasePageRequestBean exportRequest = TemplateBasePageRequestBean.createExportRequest(filter);
		final PageAndSortResult<TemplateViewBean> result = templateReportService.exportTemplateDataListStopped(exportRequest);
		final List<TemplateViewBean> results = result.getData();
		final String fileName = "template_" + getExportSuffix();
		final byte[] data = convertToCSV(TemplateViewBean.HEADER, results).getBytes(Charset.forName("UTF-8"));

		LoggingUtils.logAdminAction("export_msg_template_stopped", exportRequest.getFilters().logInfo(result.getTotal()));
		writeOnResponseStream(response, data, ResponseMediaType.TEXT_CSV, fileName);
	}

}
