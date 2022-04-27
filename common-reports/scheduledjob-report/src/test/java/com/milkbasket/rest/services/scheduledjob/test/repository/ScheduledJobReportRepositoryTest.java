package com.milkbasket.rest.services.scheduledjob.test.repository;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.core.framework.test.BasicTestConfiguration;
import com.milkbasket.rest.services.scheduledjob.report.bean.ScheduledJobFilterBean;
import com.milkbasket.rest.services.scheduledjob.report.bean.ScheduledJobPageRequestBean;
import com.milkbasket.rest.services.scheduledjob.report.bean.ScheduledJobSortBean;
import com.milkbasket.rest.services.scheduledjob.report.bean.ScheduledJobViewBean;
import com.milkbasket.rest.services.scheduledjob.report.repository.ScheduledJobReportRepository;

public class ScheduledJobReportRepositoryTest extends BasicTestConfiguration {
	@Autowired
	private ScheduledJobReportRepository scheduledJobReportRepository;

	@Test(priority = 0)
	public void test_Repository() {
		assertNotNull(scheduledJobReportRepository);
	}

	@Test(priority = 5)
	public void test_getScheduledJobDataList_default() {
		ScheduledJobPageRequestBean pageRequestBody = new ScheduledJobPageRequestBean();
		pageRequestBody.setFilters(new ScheduledJobFilterBean());
		PageAndSortResult<ScheduledJobViewBean> results = scheduledJobReportRepository
				.getScheduledJobDataList(pageRequestBody);
		assertNotNull(results);
	}

	@Test(priority = 102)
	public void test_getScheduledJobDataList_2() {

		ScheduledJobFilterBean filterBean = new ScheduledJobFilterBean();
		filterBean.setHubId(1L);

		ScheduledJobSortBean sort = new ScheduledJobSortBean();
		// sort.setId(SortDirection.DESC);
		ScheduledJobPageRequestBean pageRequestBody = new ScheduledJobPageRequestBean();
		pageRequestBody.setPageNo(1);
		pageRequestBody.setPageSize(30);
		pageRequestBody.setFilters(filterBean);
		pageRequestBody.setSort(sort);

		PageAndSortResult<ScheduledJobViewBean> results = scheduledJobReportRepository
				.getScheduledJobDataList(pageRequestBody);

		assertNotNull(results);
		assertEquals(results.getPageNo().intValue(), 1);
		assertEquals(results.getPageSize().intValue(), 30);
	}

	@Test(priority = 153)
	public void test_getScheduledJobDataList_3() {

		ScheduledJobFilterBean filterBean = new ScheduledJobFilterBean();
		filterBean.setHubId(1L);
		filterBean.setJobName("Test");

		ScheduledJobSortBean sort = new ScheduledJobSortBean();
		// sort.setId(SortDirection.DESC);
		ScheduledJobPageRequestBean pageRequestBody = new ScheduledJobPageRequestBean();
		pageRequestBody.setPageNo(1);
		pageRequestBody.setPageSize(30);
		pageRequestBody.setFilters(filterBean);
		pageRequestBody.setSort(sort);

		PageAndSortResult<ScheduledJobViewBean> results = scheduledJobReportRepository
				.getScheduledJobDataList(pageRequestBody);

		assertNotNull(results);
		assertEquals(results.getPageNo().intValue(), 1);
		assertEquals(results.getPageSize().intValue(), 30);
	}

	@Test(priority = 204)
	public void test_getScheduledJobDataList_4() {

		ScheduledJobFilterBean filterBean = new ScheduledJobFilterBean();
		filterBean.setHubId(1L);
		filterBean.setJobName("Test");
		filterBean.setJobStatus("Test");

		ScheduledJobSortBean sort = new ScheduledJobSortBean();
		// sort.setId(SortDirection.DESC);
		ScheduledJobPageRequestBean pageRequestBody = new ScheduledJobPageRequestBean();
		pageRequestBody.setPageNo(1);
		pageRequestBody.setPageSize(30);
		pageRequestBody.setFilters(filterBean);
		pageRequestBody.setSort(sort);

		PageAndSortResult<ScheduledJobViewBean> results = scheduledJobReportRepository
				.getScheduledJobDataList(pageRequestBody);

		assertNotNull(results);
		assertEquals(results.getPageNo().intValue(), 1);
		assertEquals(results.getPageSize().intValue(), 30);
	}
}
