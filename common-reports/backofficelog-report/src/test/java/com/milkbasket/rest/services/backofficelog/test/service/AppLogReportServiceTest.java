package com.milkbasket.rest.services.backofficelog.test.service;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.core.framework.test.BasicTestConfiguration;
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
import com.milkbasket.rest.services.backofficelog.report.service.AppLogReportService;

public class AppLogReportServiceTest extends BasicTestConfiguration {

	@Autowired
	private AppLogReportService appLogReportService;

	@Test(priority = 0)
	public void test_serviceNotNull() {
		assertNotNull(appLogReportService);
	}

	@Test(priority = 10)
	public void test_exportAppLogDataList() {
		AppLogPageRequestBean request = new AppLogPageRequestBean();
		request.setPageNo(1);
		request.setPageSize(10);
		request.setFilters(new AppLogFilterBean());
		PageAndSortResult<AppLogViewBean> results = appLogReportService.exportAppLogDataList(request);

		assertNotNull(results);
		assertEquals(results.getPageNo().intValue(), 1);
		assertEquals(results.getPageSize().intValue(), 10);
	}

	@Test(priority = 20)
	public void test_findAppLogDataList() {
		AppLogPageRequestBean request = new AppLogPageRequestBean();
		request.setPageNo(1);
		request.setPageSize(10);
		request.setFilters(new AppLogFilterBean());
		PageAndSortResult<AppLogViewBean> results = appLogReportService.findAppLogDataList(request);

		assertNotNull(results);
		assertEquals(results.getPageNo().intValue(), 1);
		assertEquals(results.getPageSize().intValue(), 10);
	}

	@Test(priority = 30)
	public void test_exportAppErrorLogDataList() {
		AppErrorLogPageRequestBean request = new AppErrorLogPageRequestBean();
		request.setPageNo(1);
		request.setPageSize(10);
		request.setFilters(new AppErrorLogFilterBean());
		PageAndSortResult<AppErrorLogViewBean> results = appLogReportService.exportAppErrorLogDataList(request);

		assertNotNull(results);
		assertEquals(results.getPageNo().intValue(), 1);
		assertEquals(results.getPageSize().intValue(), 10);
	}

	@Test(priority = 40)
	public void test_findAppErrorLogDataList() {
		AppErrorLogPageRequestBean request = new AppErrorLogPageRequestBean();
		request.setPageNo(1);
		request.setPageSize(10);
		request.setFilters(new AppErrorLogFilterBean());
		PageAndSortResult<AppErrorLogViewBean> results = appLogReportService.findAppErrorLogDataList(request);

		assertNotNull(results);
		assertEquals(results.getPageNo().intValue(), 1);
		assertEquals(results.getPageSize().intValue(), 10);
	}

	@Test(priority = 50)
	public void test_exportAppSummaryLogDataList() {
		AppLogSummaryFilterBean filterBean = new AppLogSummaryFilterBean();
		filterBean.setAction("%view%");

		List<AppLogSummaryViewBean> results = appLogReportService.findAppLogSummary(filterBean);

		assertNotNull(results);
	}

	@Test(priority = 60)
	public void test_exportAppErrorSummaryLogDataList() {
		AppErrorLogSummaryFilterBean filterBean = new AppErrorLogSummaryFilterBean();
		filterBean.setError("%view%");

		List<AppErrorLogSummaryViewBean> results = appLogReportService.findAppErrorLogSummary(filterBean);

		assertNotNull(results);
	}

}
