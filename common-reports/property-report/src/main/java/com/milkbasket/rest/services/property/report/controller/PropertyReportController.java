package com.milkbasket.rest.services.property.report.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.core.framework.websupport.base.controller.ReportSupportController;
import com.milkbasket.rest.services.property.report.bean.PropertyPageRequestBean;
import com.milkbasket.rest.services.property.report.bean.PropertyViewBean;
import com.milkbasket.rest.services.property.report.service.PropertyReportService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
/**
 * <p>
 * PropertyReportController class.
 * </p>
 *
 * @author Tanuja
 * @version $Id: $Id
 */
@Api(tags = { "Property Report Services" }, description = "Manages property Report related services")
public class PropertyReportController implements ReportSupportController {

	@Autowired
	private PropertyReportService propertyReportService;

	/**
	 * <p>
	 * findpropertyDataList.
	 * </p>
	 *
	 * @property request a
	 *           {@link com.milkbasket.rest.services.property.report.bean.PropertyPageRequestBean}
	 *           object.
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult}
	 *         object.
	 * @param request
	 *            a
	 *            {@link com.milkbasket.rest.services.property.report.bean.PropertyPageRequestBean}
	 *            object.
	 */
	@ApiOperation(value = "List paginated property related dataList information from application store.")
	@PostMapping("/properties/datalist")
	public PageAndSortResult<PropertyViewBean> findPropertyDataList(@Valid @RequestBody(required = false) PropertyPageRequestBean request) {
		request = PropertyPageRequestBean.createDatalistRequest(request);
		return propertyReportService.findPropertyDataList(request);
	}

}
