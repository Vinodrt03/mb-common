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
import com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogFilterBean;
import com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogPageRequestBean;
import com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogSortBean;
import com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogSummaryFilterBean;
import com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogSummaryViewBean;
import com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogViewBean;
import com.milkbasket.rest.services.backofficelog.report.repository.BackOfficeLogReportRepository;

/**
 * Repository to test fetch of backoffice logs
 *
 * @author NitinKhaitan
 * @version $Id: $Id
 */
public class BackOfficeLogReportRepositoryTest extends BasicTestConfiguration {
	@Autowired
	private BackOfficeLogReportRepository backOfficeLogReportRepository;

	@Test(priority = 0)
	public void test_Repository() {
		assertNotNull(backOfficeLogReportRepository);
	}

	@Test(priority = 5)
	public void test_getBackOfficeLogDataList_default() {
		BackOfficeLogPageRequestBean pageRequestBody = new BackOfficeLogPageRequestBean();
		pageRequestBody.setFilters(new BackOfficeLogFilterBean());
		PageAndSortResult<BackOfficeLogViewBean> results = backOfficeLogReportRepository.getBackOfficeLogDataList(pageRequestBody);
		assertNotNull(results);
	}

	@Test(priority = 102)
	public void test_getBackOfficeLogDataList_2() {

		BackOfficeLogFilterBean filterBean = new BackOfficeLogFilterBean();

		BackOfficeLogSortBean sort = new BackOfficeLogSortBean();
		// sort.setId(SortDirection.DESC);

		BackOfficeLogPageRequestBean pageRequestBody = new BackOfficeLogPageRequestBean();
		pageRequestBody.setPageNo(1);
		pageRequestBody.setPageSize(30);
		pageRequestBody.setFilters(filterBean);
		pageRequestBody.setSort(sort);

		PageAndSortResult<BackOfficeLogViewBean> results = backOfficeLogReportRepository.getBackOfficeLogDataList(pageRequestBody);

		assertNotNull(results);
		assertEquals(results.getPageNo().intValue(), 1);
		assertEquals(results.getPageSize().intValue(), 30);
	}

	@Test(priority = 153)
	public void test_getBackOfficeLogDataList_3() {

		BackOfficeLogFilterBean filterBean = new BackOfficeLogFilterBean();

		filterBean.setUserId(1L);

		BackOfficeLogSortBean sort = new BackOfficeLogSortBean();
		// sort.setId(SortDirection.DESC);

		BackOfficeLogPageRequestBean pageRequestBody = new BackOfficeLogPageRequestBean();
		pageRequestBody.setPageNo(1);
		pageRequestBody.setPageSize(30);
		pageRequestBody.setFilters(filterBean);
		pageRequestBody.setSort(sort);

		PageAndSortResult<BackOfficeLogViewBean> results = backOfficeLogReportRepository.getBackOfficeLogDataList(pageRequestBody);

		assertNotNull(results);
		assertEquals(results.getPageNo().intValue(), 1);
		assertEquals(results.getPageSize().intValue(), 30);
	}

	@Test(priority = 204)
	public void test_getBackOfficeLogDataList_4() {

		BackOfficeLogFilterBean filterBean = new BackOfficeLogFilterBean();

		filterBean.setUserId(1L);
		filterBean.setStartDate("2019-01-01");

		BackOfficeLogSortBean sort = new BackOfficeLogSortBean();
		// sort.setId(SortDirection.DESC);

		BackOfficeLogPageRequestBean pageRequestBody = new BackOfficeLogPageRequestBean();
		pageRequestBody.setPageNo(1);
		pageRequestBody.setPageSize(30);
		pageRequestBody.setFilters(filterBean);
		pageRequestBody.setSort(sort);

		PageAndSortResult<BackOfficeLogViewBean> results = backOfficeLogReportRepository.getBackOfficeLogDataList(pageRequestBody);

		assertNotNull(results);
		assertEquals(results.getPageNo().intValue(), 1);
		assertEquals(results.getPageSize().intValue(), 30);
	}

	@Test(priority = 255)
	public void test_getBackOfficeLogDataList_5() {

		BackOfficeLogFilterBean filterBean = new BackOfficeLogFilterBean();

		filterBean.setUserId(1L);
		filterBean.setEndDate("2019-01-01");
		filterBean.setAction("Test");

		BackOfficeLogSortBean sort = new BackOfficeLogSortBean();
		// sort.setId(SortDirection.DESC);

		BackOfficeLogPageRequestBean pageRequestBody = new BackOfficeLogPageRequestBean();
		pageRequestBody.setPageNo(1);
		pageRequestBody.setPageSize(30);
		pageRequestBody.setFilters(filterBean);
		pageRequestBody.setSort(sort);

		PageAndSortResult<BackOfficeLogViewBean> results = backOfficeLogReportRepository.getBackOfficeLogDataList(pageRequestBody);

		assertNotNull(results);
		assertEquals(results.getPageNo().intValue(), 1);
		assertEquals(results.getPageSize().intValue(), 30);
	}

	@Test(priority = 256)
	public void test_getBackOfficeLogDataList_6() {

		BackOfficeLogFilterBean filterBean = new BackOfficeLogFilterBean();

		filterBean.setAction("%view%");
		filterBean.setStartDate(DateUtils.toString(CommonFormats.DATE_FORMAT, new Date()));
		filterBean.setUserId(Long.valueOf(1));

		BackOfficeLogSortBean sort = new BackOfficeLogSortBean();
		sort.setId(SortDirection.DESC);
		sort.setAction(SortDirection.ASC);
		sort.setDateTime(SortDirection.ASC);

		BackOfficeLogPageRequestBean pageRequestBody = new BackOfficeLogPageRequestBean();
		pageRequestBody.setPageNo(1);
		pageRequestBody.setPageSize(30);
		pageRequestBody.setFilters(filterBean);
		pageRequestBody.setSort(sort);

		PageAndSortResult<BackOfficeLogViewBean> results = backOfficeLogReportRepository.getBackOfficeLogDataList(pageRequestBody);

		assertNotNull(results);
		assertEquals(results.getPageNo().intValue(), 1);
		assertEquals(results.getPageSize().intValue(), 30);
	}

	@Test(priority = 260)
	public void test_getBackOfficeLogSummaryDataList_6() {

		BackOfficeLogSummaryFilterBean filterBean = new BackOfficeLogSummaryFilterBean();
		filterBean.setAction("%view%");
		filterBean.setStartDate(DateUtils.toString(CommonFormats.DATE_FORMAT, new Date()) + " 00:00:00");
		filterBean.setEndDate(DateUtils.toString(CommonFormats.DATE_FORMAT, new Date()) + " 23:59:59");

		List<BackOfficeLogSummaryViewBean> results = backOfficeLogReportRepository.getBackOfficeLogSummary(filterBean);

		assertNotNull(results);
	}
}
