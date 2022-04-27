package com.milkbasket.rest.services.masterdata.report.controller;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.milkbasket.core.framework.common.constants.ResponseMediaType;
import com.milkbasket.core.framework.common.utils.LoggingUtils;
import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.core.framework.masterdata.reader.MasterData;
import com.milkbasket.core.framework.masterdata.reader.MasterDataItem;
import com.milkbasket.core.framework.masterdata.utils.MasterDataUtils;
import com.milkbasket.core.framework.utility.CollectionUtils;
import com.milkbasket.core.framework.websupport.base.controller.ReportSupportController;
import com.milkbasket.rest.services.masterdata.report.bean.MasterDataFilterBean;
import com.milkbasket.rest.services.masterdata.report.bean.MasterDataPageRequestBean;
import com.milkbasket.rest.services.masterdata.report.bean.MasterDataViewBean;
import com.milkbasket.rest.services.masterdata.report.service.MasterDataReportService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
/**
 * <p>
 * MasterDataReportController class.
 * </p>
 *
 * @author Neeraj
 * @version $Id: $Id
 */
@Api(tags = { "MasterData Report Services" }, description = "Manages MasterData Report related services")
public class MasterDataReportController implements ReportSupportController {

	@Autowired
	private MasterDataReportService masterDataReportService;

	/**
	 * <p>
	 * findMasterDataDataList.
	 * </p>
	 *
	 *
	 * @param request
	 *            a
	 *            {@link com.milkbasket.rest.services.masterdata.report.bean.MasterDataPageRequestBean}
	 *            object.
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult}
	 *         object.
	 */
	@ApiOperation(value = "List paginated MasterData related dataList information from application store.", nickname = "findMasterReportdataList")
	@PostMapping("/masterdata/datalist")
	public PageAndSortResult<MasterDataViewBean> findMasterDataDataList(@Valid @RequestBody(required = false) MasterDataPageRequestBean request) {
		request = MasterDataPageRequestBean.createDatalistRequest(request);
		return masterDataReportService.findMasterDataDataList(request);
	}

	/**
	 * <p>
	 * getMasterReportList.
	 * </p>
	 *
	 * @param masterKey
	 *            a {@link java.lang.String} object.
	 * @return a {@link java.util.List} object.
	 */
	@ApiOperation(value = "Get MasterData list information from application store.", nickname = "getMasterReportList")
	@GetMapping("/masterdata")
	public List<MasterDataItem> getMasterReportList(@RequestParam(required = true) final String masterKey) {
		final MasterData masterData = MasterDataUtils.getMasterData(masterKey);
		return masterData != null && CollectionUtils.isNotEmpty(masterData.getItems()) ? masterData.getItems() : new ArrayList<>();
	}

	/**
	 * <p>
	 * exportMasterDataDataList.
	 * </p>
	 *
	 * @param filter
	 *            a
	 *            {@link com.milkbasket.rest.services.masterdata.report.bean.MasterDataFilterBean}
	 *            object.
	 * @param response
	 *            a {@link javax.servlet.http.HttpServletResponse} object.
	 */
	@ApiOperation(value = "Export MasterData related dataList information from application store.", nickname = "exportMasterReportDataDataList")
	@PostMapping("/masterdata/export")
	public void exportMasterDataDataList(@Valid @RequestBody(required = false) final MasterDataFilterBean filter,
			final HttpServletResponse response) {

		final MasterDataPageRequestBean exportRequest = MasterDataPageRequestBean.createExportRequest(filter);
		final PageAndSortResult<MasterDataViewBean> result = masterDataReportService.exportMasterDataDataList(exportRequest);
		final List<MasterDataViewBean> results = result.getData();
		final String fileName = "masterdata_" + getExportSuffix();
		final byte[] data = convertToCSV(MasterDataViewBean.HEADER, results).getBytes(Charset.forName("UTF-8"));

		LoggingUtils.logAdminAction("export_master_data", exportRequest.getFilters().logInfo(result.getTotal()));
		writeOnResponseStream(response, data, ResponseMediaType.TEXT_CSV, fileName);
	}

}
