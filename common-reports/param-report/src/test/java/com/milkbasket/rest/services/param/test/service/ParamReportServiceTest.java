package com.milkbasket.rest.services.param.test.service;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.core.framework.test.BasicTestConfiguration;
import com.milkbasket.rest.services.param.report.bean.ParamFilterBean;
import com.milkbasket.rest.services.param.report.bean.ParamPageRequestBean;
import com.milkbasket.rest.services.param.report.bean.ParamViewBean;
import com.milkbasket.rest.services.param.report.service.ParamReportService;

public class ParamReportServiceTest extends BasicTestConfiguration {

	@Autowired
	private ParamReportService paramReportService;

	@Test(priority = 0)
	public void test_serviceNotNull() {
		assertNotNull(paramReportService);
	}

	@Test(priority = 15)
	public void test_findParamDataList() {
		ParamPageRequestBean request = new ParamPageRequestBean();
		request.setPageNo(1);
		request.setPageSize(10);

		final ParamFilterBean filter = new ParamFilterBean();
		request.setFilters(filter);

		PageAndSortResult<ParamViewBean> results = paramReportService.findParamDataList(request);

		assertNotNull(results);
		assertEquals(results.getPageNo().intValue(), 1);
		assertEquals(results.getPageSize().intValue(), 10);
	}

}
