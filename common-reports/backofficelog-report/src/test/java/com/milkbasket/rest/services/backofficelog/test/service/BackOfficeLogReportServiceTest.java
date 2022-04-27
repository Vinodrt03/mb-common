package com.milkbasket.rest.services.backofficelog.test.service;

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
import com.milkbasket.rest.services.backofficelog.report.service.BackOfficeLogReportService;

/**
 * Service to test fetch of backoffice logs
 *
 * @author NitinKhaitan
 * @version $Id: $Id
 */
public class BackOfficeLogReportServiceTest extends BasicTestConfiguration {

	@Autowired
	private BackOfficeLogReportService backOfficeLogReportService;

	@Test(priority = 0)
	public void test_serviceNotNull() {
		assertNotNull(backOfficeLogReportService);
	}

	@Test(priority = 10)
	public void test_exportBackOfficeLogDataList() {
		BackOfficeLogPageRequestBean request = new BackOfficeLogPageRequestBean();
		request.setPageNo(1);
		request.setPageSize(10);

		BackOfficeLogFilterBean filterBean = new BackOfficeLogFilterBean();
		filterBean.setAction("%view%");
		filterBean.setStartDate(DateUtils.toString(CommonFormats.DATE_FORMAT, new Date()));
		filterBean.setUserId(Long.valueOf(1));
		request.setFilters(filterBean);

		BackOfficeLogSortBean sort = new BackOfficeLogSortBean();
		sort.setId(SortDirection.DESC);
		sort.setAction(SortDirection.ASC);
		sort.setDateTime(SortDirection.ASC);
		request.setSort(sort);

		PageAndSortResult<BackOfficeLogViewBean> results = backOfficeLogReportService.exportBackOfficeLogDataList(request);

		assertNotNull(results);
		assertEquals(results.getPageNo().intValue(), 1);
		assertEquals(results.getPageSize().intValue(), 10);
	}

	@Test(priority = 15)
	public void test_findBackOfficeLogDataList() {
		BackOfficeLogPageRequestBean request = new BackOfficeLogPageRequestBean();
		request.setPageNo(1);
		request.setPageSize(10);
		request.setFilters(new BackOfficeLogFilterBean());
		PageAndSortResult<BackOfficeLogViewBean> results = backOfficeLogReportService.findBackOfficeLogDataList(request);

		assertNotNull(results);
		assertEquals(results.getPageNo().intValue(), 1);
		assertEquals(results.getPageSize().intValue(), 10);
	}

	@Test(priority = 20)
	public void test_exportBackOfficeSummaryLogDataList() {
		BackOfficeLogSummaryFilterBean filterBean = new BackOfficeLogSummaryFilterBean();
		filterBean.setAction("%view%");

		List<BackOfficeLogSummaryViewBean> results = backOfficeLogReportService.findBackOfficeLogSummary(filterBean);

		assertNotNull(results);
	}

}
