package com.milkbasket.rest.services.backofficelog.test.service;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.core.framework.test.BasicTestConfiguration;
import com.milkbasket.rest.services.backofficelog.report.bean.DbLogFilterBean;
import com.milkbasket.rest.services.backofficelog.report.bean.DbLogPageRequestBean;
import com.milkbasket.rest.services.backofficelog.report.bean.DbLogViewBean;
import com.milkbasket.rest.services.backofficelog.report.service.DbLogReportService;

public class DbLogReportServiceTest extends BasicTestConfiguration {

	@Autowired
	private DbLogReportService dbLogReportService;

	@Test(priority = 0)
	public void test_serviceNotNull() {
		assertNotNull(dbLogReportService);
	}

	@Test(priority = 10)
	public void test_exportDbLogDataList() {
		DbLogPageRequestBean request = new DbLogPageRequestBean();
		request.setPageNo(1);
		request.setPageSize(10);
		
		request.setFilters(new DbLogFilterBean());
		PageAndSortResult<DbLogViewBean> results = dbLogReportService.exportDbLogDataList(request);

		assertNotNull(results);
		assertEquals(results.getPageNo().intValue(), 1);
		assertEquals(results.getPageSize().intValue(), 10);
	}

	@Test(priority = 15)
	public void test_findDbLogDataList() {
		DbLogPageRequestBean request = new DbLogPageRequestBean();
		request.setPageNo(1);
		request.setPageSize(10);
		
		request.setFilters(new DbLogFilterBean());
		PageAndSortResult<DbLogViewBean> results = dbLogReportService.findDbLogDataList(request);

		assertNotNull(results);
		assertEquals(results.getPageNo().intValue(), 1);
		assertEquals(results.getPageSize().intValue(), 10);
	}

}
