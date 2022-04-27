package com.milkbasket.rest.services.backofficelog.test.repository;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.core.framework.test.BasicTestConfiguration;
import com.milkbasket.rest.services.backofficelog.report.bean.DbLogFilterBean;
import com.milkbasket.rest.services.backofficelog.report.bean.DbLogPageRequestBean;
import com.milkbasket.rest.services.backofficelog.report.bean.DbLogSortBean;
import com.milkbasket.rest.services.backofficelog.report.bean.DbLogViewBean;
import com.milkbasket.rest.services.backofficelog.report.repository.DbLogReportRepository;

public class DbLogReportRepositoryTest extends BasicTestConfiguration {
	@Autowired
	private DbLogReportRepository dbLogReportRepository;

	@Test(priority = 0)
	public void test_Repository() {
		assertNotNull(dbLogReportRepository);
	}

	@Test(priority = 5)
	public void test_getDbLogDataList_default() {
		DbLogPageRequestBean pageRequestBody = new DbLogPageRequestBean();
		pageRequestBody.setFilters(new DbLogFilterBean());
		PageAndSortResult<DbLogViewBean> results = dbLogReportRepository.getDbLogDataList(pageRequestBody);
		assertNotNull(results);
	}

	@Test(priority = 102)
	public void test_getDbLogDataList_2() {

		DbLogFilterBean filterBean = new DbLogFilterBean();
		filterBean.setRequestId("2312hbhfv");

		DbLogSortBean sort = new DbLogSortBean();
		// sort.setId(SortDirection.DESC);
		DbLogPageRequestBean pageRequestBody = new DbLogPageRequestBean();
		pageRequestBody.setPageNo(1);
		pageRequestBody.setPageSize(30);
		pageRequestBody.setFilters(filterBean);
		pageRequestBody.setSort(sort);

		PageAndSortResult<DbLogViewBean> results = dbLogReportRepository.getDbLogDataList(pageRequestBody);

		assertNotNull(results);
		assertEquals(results.getPageNo().intValue(), 1);
		assertEquals(results.getPageSize().intValue(), 30);
	}

	@Test(priority = 153)
	public void test_getDbLogDataList_3() {

		DbLogFilterBean filterBean = new DbLogFilterBean();
		filterBean.setRequestId("2312hbhfv");
		filterBean.setModule("Test");

		DbLogSortBean sort = new DbLogSortBean();
		// sort.setId(SortDirection.DESC);
		DbLogPageRequestBean pageRequestBody = new DbLogPageRequestBean();
		pageRequestBody.setPageNo(1);
		pageRequestBody.setPageSize(30);
		pageRequestBody.setFilters(filterBean);
		pageRequestBody.setSort(sort);

		PageAndSortResult<DbLogViewBean> results = dbLogReportRepository.getDbLogDataList(pageRequestBody);

		assertNotNull(results);
		assertEquals(results.getPageNo().intValue(), 1);
		assertEquals(results.getPageSize().intValue(), 30);
	}

	@Test(priority = 204)
	public void test_getDbLogDataList_4() {

		DbLogFilterBean filterBean = new DbLogFilterBean();
		filterBean.setRequestId("2312hbhfv");
		filterBean.setModule("Test");
		filterBean.setStartDate("2019-01-01");

		DbLogSortBean sort = new DbLogSortBean();
		// sort.setId(SortDirection.DESC);
		DbLogPageRequestBean pageRequestBody = new DbLogPageRequestBean();
		pageRequestBody.setPageNo(1);
		pageRequestBody.setPageSize(30);
		pageRequestBody.setFilters(filterBean);
		pageRequestBody.setSort(sort);

		PageAndSortResult<DbLogViewBean> results = dbLogReportRepository.getDbLogDataList(pageRequestBody);

		assertNotNull(results);
		assertEquals(results.getPageNo().intValue(), 1);
		assertEquals(results.getPageSize().intValue(), 30);
	}

	@Test(priority = 255)
	public void test_getDbLogDataList_5() {

		DbLogFilterBean filterBean = new DbLogFilterBean();
		filterBean.setRequestId("2312hbhfv");
		filterBean.setModule("Test");
		filterBean.setStartDate("2019-01-01");
		filterBean.setEndDate("2019-01-01");

		DbLogSortBean sort = new DbLogSortBean();
		// sort.setId(SortDirection.DESC);
		DbLogPageRequestBean pageRequestBody = new DbLogPageRequestBean();
		pageRequestBody.setPageNo(1);
		pageRequestBody.setPageSize(30);
		pageRequestBody.setFilters(filterBean);
		pageRequestBody.setSort(sort);

		PageAndSortResult<DbLogViewBean> results = dbLogReportRepository.getDbLogDataList(pageRequestBody);

		assertNotNull(results);
		assertEquals(results.getPageNo().intValue(), 1);
		assertEquals(results.getPageSize().intValue(), 30);
	}
}
