package com.milkbasket.rest.services.property.test.service;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.core.framework.test.BasicTestConfiguration;
import com.milkbasket.rest.services.property.report.bean.PropertyFilterBean;
import com.milkbasket.rest.services.property.report.bean.PropertyPageRequestBean;
import com.milkbasket.rest.services.property.report.bean.PropertyViewBean;
import com.milkbasket.rest.services.property.report.service.PropertyReportService;

public class PropertyReportServiceTest extends BasicTestConfiguration {

	@Autowired
	private PropertyReportService propertyReportService;

	@Test(priority = 0)
	public void test_serviceNotNull() {
		assertNotNull(propertyReportService);
	}

	@Test(priority = 15)
	public void test_findPropertyDataList() {
		PropertyPageRequestBean request = new PropertyPageRequestBean();
		request.setPageNo(1);
		request.setPageSize(10);

		final PropertyFilterBean filter = new PropertyFilterBean();
		request.setFilters(filter);

		PageAndSortResult<PropertyViewBean> results = propertyReportService.findPropertyDataList(request);

		assertNotNull(results);
		assertEquals(results.getPageNo().intValue(), 1);
		assertEquals(results.getPageSize().intValue(), 10);
	}

}
