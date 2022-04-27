package com.milkbasket.rest.services.scheduledjob.test.handler;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.testng.annotations.Test;

import com.milkbasket.core.framework.common.constants.CommonConstants;
import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection;
import com.milkbasket.core.framework.property.utils.PropertiesUtils;
import com.milkbasket.core.framework.test.WebTestConfiguration;
import com.milkbasket.rest.services.scheduledjob.report.bean.ScheduledJobFilterBean;
import com.milkbasket.rest.services.scheduledjob.report.bean.ScheduledJobPageRequestBean;
import com.milkbasket.rest.services.scheduledjob.report.bean.ScheduledJobSortBean;
import com.milkbasket.rest.services.scheduledjob.report.bean.ScheduledJobViewBean;

import io.restassured.response.Response;

public class ScheduledJobReportHandlerTest extends WebTestConfiguration {

	/** The headers. */
	Map<String, String> headers = new HashMap<>();

	@Test(priority = 10)
	public void test_beans() {
		final ScheduledJobViewBean viewBean = new ScheduledJobViewBean();
		viewBean.setHubId(1L);
		viewBean.setHubName("Test");
		viewBean.setJobName("Test");
		viewBean.setJobDate("2019-01-01");
		viewBean.setStartTime("2019-01-01 01:01:01");
		viewBean.setEndTime("2019-01-01 01:01:01");
		viewBean.setTimeTaken(1);
		viewBean.setJobResultData("Test");
		viewBean.setInitiatedBy("Test");

		assertNotNull(viewBean.csvRow());
		final ScheduledJobFilterBean filterBean = new ScheduledJobFilterBean();
		filterBean.setHubId(1L);
		filterBean.setJobName("Test");
		filterBean.setJobStatus("Test");
		filterBean.setStartTime("2018-10-10 01:01:01");
		filterBean.setEndTime("2018-10-10 01:01:01");

		assertNotNull(filterBean.getHubId());

		assertNotNull(filterBean.getJobName());

		assertNotNull(filterBean.getJobStatus());

		assertNotNull(filterBean.getStartTime());

		assertNotNull(filterBean.getEndTime());
	}

	@Test(priority = 20)
	public void test_datalist() {

		final String dataListHandler = "/scheduled-jobs/datalist";

		final ScheduledJobPageRequestBean request = new ScheduledJobPageRequestBean();
		request.setPageNo(1);
		request.setPageSize(10);

		final ScheduledJobFilterBean filters = new ScheduledJobFilterBean();
		filters.setHubId(1l);
		filters.setJobName("basket");
		filters.setJobStatus("success");
		request.setFilters(filters);

		final ScheduledJobSortBean sort = new ScheduledJobSortBean();
		sort.setHubId(SortDirection.DESC);
		sort.setJobDate(SortDirection.ASC);
		sort.setJobName(SortDirection.ASC);
		request.setSort(sort);

		final Response response = post(dataListHandler, request, headers, null, null).then().statusCode(HttpStatus.OK.value()).extract().response();

		final Map<?, ?> pageResult = asObject(response.body().asString(), LinkedHashMap.class);

		assertTrue(((List<?>) pageResult.get("data")).size() <= 10);
		assertEquals(((Integer) pageResult.get("pageNo")).intValue(), 1);
		assertEquals(((Integer) pageResult.get("pageSize")).intValue(), 10);

		final int defaultSize = PropertiesUtils.getIntegerProperty(CommonConstants.DEFAULT_PAGE_SIZE);
		final int defaultPage = PropertiesUtils.getIntegerProperty(CommonConstants.DEFAULT_PAGE_NO);

		final ScheduledJobPageRequestBean request2 = new ScheduledJobPageRequestBean();
		final Response response2 = post(dataListHandler, request2, headers, null, null).then().statusCode(HttpStatus.OK.value()).extract().response();
		final Map<?, ?> pageResult2 = asObject(response2.body().asString(), LinkedHashMap.class);

		assertEquals(((Integer) pageResult2.get("pageNo")).intValue(), defaultPage);
		assertEquals(((Integer) pageResult2.get("pageSize")).intValue(), defaultSize);

		post(dataListHandler, null, headers, null, null).then().statusCode(HttpStatus.OK.value());

	}

	@Test(priority = 25)
	public void test_export_datalist() {

		final String exportHandler = "/scheduled-jobs/export";

		final ScheduledJobFilterBean filters = new ScheduledJobFilterBean();

		final Response response = post(exportHandler, filters, headers, null, null).then().statusCode(HttpStatus.OK.value()).extract().response();
		final String pageResult = response.body().asString();
		assertNotNull(pageResult);

		final Response response2 = post(exportHandler, null, headers, null, null).then().statusCode(HttpStatus.OK.value()).extract().response();
		final String pageResult2 = response2.body().asString();
		assertNotNull(pageResult2);

		post(exportHandler, null, headers, null, null).then().statusCode(HttpStatus.OK.value());
	}

}
