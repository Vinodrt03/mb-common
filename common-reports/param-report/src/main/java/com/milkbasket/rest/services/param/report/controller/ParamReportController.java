package com.milkbasket.rest.services.param.report.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.core.framework.websupport.base.controller.ReportSupportController;
import com.milkbasket.rest.services.param.report.bean.ParamPageRequestBean;
import com.milkbasket.rest.services.param.report.bean.ParamViewBean;
import com.milkbasket.rest.services.param.report.service.ParamReportService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
/**
 * <p>
 * ParamReportController class.
 * </p>
 *
 * @author Neeraj
 * @version $Id: $Id
 */
@Api(tags = { "Param Report Services" }, description = "Manages Param Report related services")
public class ParamReportController implements ReportSupportController {

	@Autowired
	private ParamReportService paramReportService;

	/**
	 * <p>
	 * findParamDataList.
	 * </p>
	 *
	 * @param request
	 *            a
	 *            {@link com.milkbasket.rest.services.param.report.bean.ParamPageRequestBean}
	 *            object.
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult}
	 *         object.
	 */
	@ApiOperation(value = "List paginated Param related dataList information from application store.")
	@PostMapping("/params/datalist")
	public PageAndSortResult<ParamViewBean> findParamDataList(@Valid @RequestBody(required = false) ParamPageRequestBean request) {
		request = ParamPageRequestBean.createDatalistRequest(request);
		return paramReportService.findParamDataList(request);
	}

}
