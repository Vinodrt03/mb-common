package com.milkbasket.rest.services.backofficelog.test.repository;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.milkbasket.core.framework.common.constants.CommonFormats;
import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection;
import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.core.framework.test.BasicTestConfiguration;
import com.milkbasket.core.framework.utility.DateUtils;
import com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogFilterBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogPageRequestBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogSortBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogSummaryFilterBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogSummaryViewBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogViewBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppLogFilterBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppLogPageRequestBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppLogSortBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppLogSummaryFilterBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppLogSummaryViewBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppLogViewBean;
import com.milkbasket.rest.services.backofficelog.report.repository.AppLogReportRepository;

public class AppLogReportRepositoryTest extends BasicTestConfiguration {
	@Autowired
	private AppLogReportRepository appLogReportRepository;

	@Test(priority = 0)
	public void test_Repository() {
		assertNotNull(appLogReportRepository);
	}

	@Test(priority = 10)
	public void test_getAppLogDataList_default() {
		AppLogPageRequestBean pageRequestBody = new AppLogPageRequestBean();
		pageRequestBody.setFilters(new AppLogFilterBean());
		PageAndSortResult<AppLogViewBean> results = appLogReportRepository.getAppLogDataList(pageRequestBody);
		assertNotNull(results);
	}

	@Test(priority = 20)
	public void test_getAppLogDataList_2() {

		AppLogFilterBean filterBean = new AppLogFilterBean();
		filterBean.setCustomerId(1L);
		filterBean.setStartDate("2019-01-01 01:01:01");
		AppLogSortBean sort = new AppLogSortBean();
		sort.setId(SortDirection.DESC);
		AppLogPageRequestBean pageRequestBody = new AppLogPageRequestBean();
		pageRequestBody.setPageNo(1);
		pageRequestBody.setPageSize(30);
		pageRequestBody.setFilters(filterBean);
		pageRequestBody.setSort(sort);

		PageAndSortResult<AppLogViewBean> results = appLogReportRepository.getAppLogDataList(pageRequestBody);

		assertNotNull(results);
		assertEquals(results.getPageNo().intValue(), 1);
		assertEquals(results.getPageSize().intValue(), 30);
	}

	@Test(priority = 30)
	public void test_getAppLogDataList_3() {

		AppLogFilterBean filterBean = new AppLogFilterBean();
		filterBean.setCustomerId(1L);
		filterBean.setAction("Test");

		AppLogSortBean sort = new AppLogSortBean();
		sort.setId(SortDirection.DESC);
		AppLogPageRequestBean pageRequestBody = new AppLogPageRequestBean();
		pageRequestBody.setPageNo(1);
		pageRequestBody.setPageSize(30);
		pageRequestBody.setFilters(filterBean);
		pageRequestBody.setSort(sort);

		PageAndSortResult<AppLogViewBean> results = appLogReportRepository.getAppLogDataList(pageRequestBody);

		assertNotNull(results);
		assertEquals(results.getPageNo().intValue(), 1);
		assertEquals(results.getPageSize().intValue(), 30);
	}

	@Test(priority = 110)
	public void test_getAppErrorLogDataList_default() {
		AppErrorLogPageRequestBean pageRequestBody = new AppErrorLogPageRequestBean();
		pageRequestBody.setFilters(new AppErrorLogFilterBean());
		PageAndSortResult<AppErrorLogViewBean> results = appLogReportRepository.getAppErrorLogDataList(pageRequestBody);
		assertNotNull(results);
	}

	@Test(priority = 120)
	public void test_getAppErrorLogDataList_2() {

		AppErrorLogFilterBean filterBean = new AppErrorLogFilterBean();
		filterBean.setCustomerId(1L);

		AppErrorLogSortBean sort = new AppErrorLogSortBean();
		AppErrorLogPageRequestBean pageRequestBody = new AppErrorLogPageRequestBean();
		pageRequestBody.setPageNo(1);
		pageRequestBody.setPageSize(30);
		pageRequestBody.setFilters(filterBean);
		pageRequestBody.setSort(sort);

		PageAndSortResult<AppErrorLogViewBean> results = appLogReportRepository.getAppErrorLogDataList(pageRequestBody);

		assertNotNull(results);
		assertEquals(results.getPageNo().intValue(), 1);
		assertEquals(results.getPageSize().intValue(), 30);
	}

	@Test(priority = 130)
	public void test_getAppErrorLogDataList_3() {

		AppErrorLogFilterBean filterBean = new AppErrorLogFilterBean();
		filterBean.setCustomerId(1L);
		filterBean.setError("Test");

		AppErrorLogSortBean sort = new AppErrorLogSortBean();
		AppErrorLogPageRequestBean pageRequestBody = new AppErrorLogPageRequestBean();
		pageRequestBody.setPageNo(1);
		pageRequestBody.setPageSize(30);
		pageRequestBody.setFilters(filterBean);
		pageRequestBody.setSort(sort);

		PageAndSortResult<AppErrorLogViewBean> results = appLogReportRepository.getAppErrorLogDataList(pageRequestBody);

		assertNotNull(results);
		assertEquals(results.getPageNo().intValue(), 1);
		assertEquals(results.getPageSize().intValue(), 30);
	}

	@Test(priority = 140)
	public void test_getAppLogSummaryDataList_6() {

		AppLogSummaryFilterBean filterBean = new AppLogSummaryFilterBean();
		filterBean.setAction("%view%");
		filterBean.setStartDate(DateUtils.toString(CommonFormats.DATE_FORMAT, new Date()) + " 00:00:00");
		filterBean.setEndDate(DateUtils.toString(CommonFormats.DATE_FORMAT, new Date()) + " 23:59:59");

		List<AppLogSummaryViewBean> results = appLogReportRepository.getAppLogSummary(filterBean);

		assertNotNull(results);
	}

	@Test(priority = 140)
	public void test_getAppErrorLogSummaryDataList_6() {

		AppErrorLogSummaryFilterBean filterBean = new AppErrorLogSummaryFilterBean();
		filterBean.setError("%view%");
		filterBean.setStartDate(DateUtils.toString(CommonFormats.DATE_FORMAT, new Date()) + " 00:00:00");
		filterBean.setEndDate(DateUtils.toString(CommonFormats.DATE_FORMAT, new Date()) + " 23:59:59");

		List<AppErrorLogSummaryViewBean> results = appLogReportRepository.getAppErrorLogSummary(filterBean);

		assertNotNull(results);
	}

}
