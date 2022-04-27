package com.milkbasket.rest.services.backofficelog.test.handler;

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
import com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogFilterBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogPageRequestBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogSortBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogSummaryFilterBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogViewBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppLogFilterBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppLogPageRequestBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppLogSortBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppLogSummaryFilterBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppLogViewBean;

import io.restassured.response.Response;

public class AppLogReportHandlerTest extends WebTestConfiguration {

	/** The headers. */
	Map<String, String> headers = new HashMap<>();

	@Test(priority = 10)
	public void test_beans_app_log() {
		final AppLogViewBean viewBean = new AppLogViewBean();
		viewBean.setId(1L);
		viewBean.setCustomerId(1L);
		viewBean.setCustomerName("Test");
		viewBean.setDateTime("2019-01-01 01:01:01");
		viewBean.setAction("Test");
		viewBean.setDataJson("Test");

		assertNotNull(viewBean.csvRow());
		final AppLogFilterBean filterBean = new AppLogFilterBean();
		filterBean.setCustomerId(1L);
		filterBean.setAction("Test");

		assertNotNull(filterBean.getCustomerId());

		assertNotNull(filterBean.getAction());

	}

	@Test(priority = 10)
	public void test_beans_app_error_logs() {
		final AppErrorLogViewBean viewBean = new AppErrorLogViewBean();
		viewBean.setId(1L);
		viewBean.setCustomerId(1L);
		viewBean.setCustomerName("Test");
		viewBean.setDateTime("2019-01-01 01:01:01");
		viewBean.setError("Test");
		viewBean.setModule("Test");
		viewBean.setDataJson("Test");

		assertNotNull(viewBean.csvRow());
		final AppErrorLogFilterBean filterBean = new AppErrorLogFilterBean();
		filterBean.setCustomerId(1L);
		filterBean.setError("Test");

		assertNotNull(filterBean.getCustomerId());

		assertNotNull(filterBean.getError());
		final AppErrorLogSortBean sort = new AppErrorLogSortBean();
		sort.setCustomerName(SortDirection.ASC);
		sort.setDateTime(SortDirection.ASC);
		sort.setError(SortDirection.ASC);
		sort.setModifiedOn(SortDirection.ASC);
		sort.setModule(SortDirection.ASC);
		sort.setId(SortDirection.ASC);
		assertNotNull(sort.getCustomerName());
		assertNotNull(sort.getDateTime());
		assertNotNull(sort.getError());
		assertNotNull(sort.getId());
		assertNotNull(sort.getModifiedOn());
		assertNotNull(sort.getModule());
	}

	@Test(priority = 20)
	public void test_app_logs_datalist() {

		final String dataListHandler = "/app-logs/datalist";

		final AppLogPageRequestBean request = new AppLogPageRequestBean();
		request.setPageNo(1);
		request.setPageSize(10);

		final AppLogFilterBean filters = new AppLogFilterBean();
		filters.setStartDate("2019-01-01 01:01:01");
		// request.setFilters(filters);

		final AppLogSortBean sort = new AppLogSortBean();
		sort.setAction(SortDirection.DESC);
		sort.setId(SortDirection.ASC);
		sort.setCustomerName(SortDirection.DESC);
		request.setSort(sort);

		final Response response = post(dataListHandler, request, headers, null, null).then().statusCode(HttpStatus.OK.value()).extract().response();

		final Map<?, ?> pageResult = asObject(response.body().asString(), LinkedHashMap.class);
		System.out.println(asString(pageResult));
		assertTrue(((List<?>) pageResult.get("data")).size() <= 10);
		assertEquals(((Integer) pageResult.get("pageNo")).intValue(), 1);
		assertEquals(((Integer) pageResult.get("pageSize")).intValue(), 10);

		final int defaultSize = PropertiesUtils.getIntegerProperty(CommonConstants.DEFAULT_PAGE_SIZE);
		final int defaultPage = PropertiesUtils.getIntegerProperty(CommonConstants.DEFAULT_PAGE_NO);

		final AppLogPageRequestBean request2 = new AppLogPageRequestBean();
		final Response response2 = post(dataListHandler, request2, headers, null, null).then().statusCode(HttpStatus.OK.value()).extract().response();
		final Map<?, ?> pageResult2 = asObject(response2.body().asString(), LinkedHashMap.class);

		assertEquals(((Integer) pageResult2.get("pageNo")).intValue(), defaultPage);
		assertEquals(((Integer) pageResult2.get("pageSize")).intValue(), defaultSize);

		post(dataListHandler, null, headers, null, null).then().statusCode(HttpStatus.OK.value());

	}

	@Test(priority = 30)
	public void test_export_app_logs_datalist() {

		final String exportHandler = "/app-logs/export";

		final AppLogFilterBean filters = new AppLogFilterBean();
		filters.setEndDate("2019-01-01");
		filters.setAction("1");

		final Response response = post(exportHandler, filters, headers, null, null).then().statusCode(HttpStatus.OK.value()).extract().response();
		final String pageResult = response.body().asString();
		assertNotNull(pageResult);

		final Response response2 = post(exportHandler, null, headers, null, null).then().statusCode(HttpStatus.OK.value()).extract().response();
		final String pageResult2 = response2.body().asString();
		assertNotNull(pageResult2);

		post(exportHandler, null, headers, null, null).then().statusCode(HttpStatus.OK.value());
	}

	@Test(priority = 40)
	public void test_apperror_logs_datalist() {

		final String dataListHandler = "/apperror-logs/datalist";

		final AppErrorLogPageRequestBean request = new AppErrorLogPageRequestBean();
		request.setPageNo(1);
		request.setPageSize(10);

		final AppErrorLogFilterBean filters = new AppErrorLogFilterBean();
		filters.setStartDate("2019-01-01");
		request.setFilters(filters);

		final AppErrorLogSortBean sort = new AppErrorLogSortBean();
		sort.setDateTime(SortDirection.ASC);

		request.setSort(sort);

		final Response response = post(dataListHandler, request, headers, null, null).then().statusCode(HttpStatus.OK.value()).extract().response();

		final Map<?, ?> pageResult = asObject(response.body().asString(), LinkedHashMap.class);

		assertTrue(((List<?>) pageResult.get("data")).size() <= 10);
		assertEquals(((Integer) pageResult.get("pageNo")).intValue(), 1);
		assertEquals(((Integer) pageResult.get("pageSize")).intValue(), 10);

		final int defaultSize = PropertiesUtils.getIntegerProperty(CommonConstants.DEFAULT_PAGE_SIZE);
		final int defaultPage = PropertiesUtils.getIntegerProperty(CommonConstants.DEFAULT_PAGE_NO);

		final AppErrorLogPageRequestBean request2 = new AppErrorLogPageRequestBean();
		final Response response2 = post(dataListHandler, request2, headers, null, null).then().statusCode(HttpStatus.OK.value()).extract().response();
		final Map<?, ?> pageResult2 = asObject(response2.body().asString(), LinkedHashMap.class);

		assertEquals(((Integer) pageResult2.get("pageNo")).intValue(), defaultPage);
		assertEquals(((Integer) pageResult2.get("pageSize")).intValue(), defaultSize);

		post(dataListHandler, null, headers, null, null).then().statusCode(HttpStatus.OK.value());

	}

	@Test(priority = 50)
	public void test_exporterros_app_logs_datalist() {

		final String exportHandler = "/apperror-logs/export";

		final AppErrorLogFilterBean filters = new AppErrorLogFilterBean();
		filters.setCustomerId(1l);

		final Response response = post(exportHandler, filters, headers, null, null).then().statusCode(HttpStatus.OK.value()).extract().response();
		final String pageResult = response.body().asString();
		assertNotNull(pageResult);

		final Response response2 = post(exportHandler, null, headers, null, null).then().statusCode(HttpStatus.OK.value()).extract().response();
		final String pageResult2 = response2.body().asString();
		assertNotNull(pageResult2);

		post(exportHandler, null, headers, null, null).then().statusCode(HttpStatus.OK.value());
	}

	@Test(priority = 60)
	public void test_app_summary_datalist() {

		final String dataListHandler = "/app-logs/summary";

		final AppLogSummaryFilterBean filterBean = new AppLogSummaryFilterBean();
		filterBean.setAction("%view%");

		post(dataListHandler, filterBean, headers, null, null).then().statusCode(HttpStatus.OK.value()).extract().response();

		filterBean.setEndDate("2019-01-01");
		doBadPost(dataListHandler, headers, filterBean);

		final AppLogSummaryFilterBean filterBean2 = new AppLogSummaryFilterBean();
		post(dataListHandler, filterBean2, headers, null, null).then().statusCode(HttpStatus.OK.value()).extract().response();

		post(dataListHandler, null, headers, null, null).then().statusCode(HttpStatus.OK.value());

		filterBean2.setStartDate("2018-01-01");
		filterBean2.setEndDate("2018-07-07");

		doBadPost(dataListHandler, headers, filterBean2);

	}

	@Test(priority = 70)
	public void test_apperror_summary_datalist() {

		final String dataListHandler = "/apperror-logs/summary";

		final AppErrorLogSummaryFilterBean filterBean = new AppErrorLogSummaryFilterBean();
		filterBean.setError("%view%");

		post(dataListHandler, filterBean, headers, null, null).then().statusCode(HttpStatus.OK.value()).extract().response();
		filterBean.setEndDate("2019-01-01");
		doBadPost(dataListHandler, headers, filterBean);

		final AppErrorLogSummaryFilterBean filterBean2 = new AppErrorLogSummaryFilterBean();
		post(dataListHandler, filterBean2, headers, null, null).then().statusCode(HttpStatus.OK.value()).extract().response();

		post(dataListHandler, null, headers, null, null).then().statusCode(HttpStatus.OK.value());

		filterBean2.setStartDate("2018-01-01");
		filterBean2.setEndDate("2018-07-07");

		doBadPost(dataListHandler, headers, filterBean2);
	}

}
