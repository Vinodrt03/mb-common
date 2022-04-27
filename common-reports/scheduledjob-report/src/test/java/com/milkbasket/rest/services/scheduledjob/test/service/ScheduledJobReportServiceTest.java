package com.milkbasket.rest.services.scheduledjob.test.service;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.core.framework.test.BasicTestConfiguration;
import com.milkbasket.rest.services.scheduledjob.report.bean.ScheduledJobFilterBean;
import com.milkbasket.rest.services.scheduledjob.report.bean.ScheduledJobPageRequestBean;
import com.milkbasket.rest.services.scheduledjob.report.bean.ScheduledJobViewBean;
import com.milkbasket.rest.services.scheduledjob.report.service.ScheduledJobReportService;

public class ScheduledJobReportServiceTest extends BasicTestConfiguration {

	@Autowired
	private ScheduledJobReportService scheduledJobReportService;

	@Test(priority = 0)
	public void test_serviceNotNull() {
		assertNotNull(scheduledJobReportService);
	}

	@Test(priority = 10)
	public void test_exportScheduledJobDataList() {
		ScheduledJobPageRequestBean request = new ScheduledJobPageRequestBean();
		request.setPageNo(1);
		request.setPageSize(10);
		// TODO change valeus in request
		request.setFilters(new ScheduledJobFilterBean());
		PageAndSortResult<ScheduledJobViewBean> results = scheduledJobReportService.exportScheduledJobDataList(request);

		assertNotNull(results);
		assertEquals(results.getPageNo().intValue(), 1);
		assertEquals(results.getPageSize().intValue(), 10);
	}

	@Test(priority = 15)
	public void test_findScheduledJobDataList() {
		ScheduledJobPageRequestBean request = new ScheduledJobPageRequestBean();
		request.setPageNo(1);
		request.setPageSize(10);
		// TODO change valeus in request
		request.setFilters(new ScheduledJobFilterBean());
		PageAndSortResult<ScheduledJobViewBean> results = scheduledJobReportService.findScheduledJobDataList(request);

		assertNotNull(results);
		assertEquals(results.getPageNo().intValue(), 1);
		assertEquals(results.getPageSize().intValue(), 10);
	}

}
