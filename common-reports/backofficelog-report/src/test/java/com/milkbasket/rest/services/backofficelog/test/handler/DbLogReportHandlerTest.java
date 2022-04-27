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
import com.milkbasket.rest.services.backofficelog.report.bean.DbLogFilterBean;
import com.milkbasket.rest.services.backofficelog.report.bean.DbLogPageRequestBean;
import com.milkbasket.rest.services.backofficelog.report.bean.DbLogSortBean;
import com.milkbasket.rest.services.backofficelog.report.bean.DbLogViewBean;

import io.restassured.response.Response;

public class DbLogReportHandlerTest extends WebTestConfiguration {

	/** The headers. */
	Map<String, String> headers = new HashMap<>();

	@Test(priority = 10)
	public void test_beans() {
		final DbLogViewBean viewBean = new DbLogViewBean();
		viewBean.setId(1L);
		viewBean.setUserId(1L);
		viewBean.setUserName("Test");
		viewBean.setRequestId("fsd");
		viewBean.setModule("Test");
		viewBean.setAction("Test");
		viewBean.setMethod("Test");
		viewBean.setSqlQuery("Test");
		viewBean.setTimeTaken(1);
		viewBean.setDate("2019-01-01 01:01:01");

		assertNotNull(viewBean.csvRow());
		final DbLogFilterBean filterBean = new DbLogFilterBean();
		filterBean.setRequestId("fsd");
		filterBean.setModule("Test");
		filterBean.setStartDate("2019-01-01");
		filterBean.setEndDate("2019-01-01");

		assertNotNull(filterBean.getRequestId());

		assertNotNull(filterBean.getModule());

		assertNotNull(filterBean.getStartDate());

		assertNotNull(filterBean.getEndDate());

	}

	@Test(priority = 20)
	public void test_db_logs_datalist() {

		final String dataListHandler = "/db-logs/datalist";

		final DbLogPageRequestBean request = new DbLogPageRequestBean();
		request.setPageNo(1);
		request.setPageSize(10);

		final DbLogFilterBean filters = new DbLogFilterBean();
		filters.setRequestId("2312hbhfv");
		filters.setModule("Test");
		filters.setStartDate("2019-01-01");
		filters.setEndDate("2019-01-01");
		request.setFilters(filters);

		final DbLogSortBean sort = new DbLogSortBean();
		sort.setDate(SortDirection.ASC);
		sort.setUserName(SortDirection.DESC);
		request.setSort(sort);

		final Response response = post(dataListHandler, request, headers, null, null).then().statusCode(HttpStatus.OK.value()).extract().response();

		final Map<?, ?> pageResult = asObject(response.body().asString(), LinkedHashMap.class);

		assertTrue(((List<?>) pageResult.get("data")).size() <= 10);
		assertEquals(((Integer) pageResult.get("pageNo")).intValue(), 1);
		assertEquals(((Integer) pageResult.get("pageSize")).intValue(), 10);

		final int defaultSize = PropertiesUtils.getIntegerProperty(CommonConstants.DEFAULT_PAGE_SIZE);
		final int defaultPage = PropertiesUtils.getIntegerProperty(CommonConstants.DEFAULT_PAGE_NO);

		final DbLogPageRequestBean request2 = new DbLogPageRequestBean();
		final Response response2 = post(dataListHandler, request2, headers, null, null).then().statusCode(HttpStatus.OK.value()).extract().response();
		final Map<?, ?> pageResult2 = asObject(response2.body().asString(), LinkedHashMap.class);

		assertEquals(((Integer) pageResult2.get("pageNo")).intValue(), defaultPage);
		assertEquals(((Integer) pageResult2.get("pageSize")).intValue(), defaultSize);

		post(dataListHandler, null, headers, null, null).then().statusCode(HttpStatus.OK.value());

	}

	@Test(priority = 25)
	public void test_export_db_logs_datalist() {

		final String exportHandler = "/db-logs/export";

		final DbLogFilterBean filters = new DbLogFilterBean();

		Response response = post(exportHandler, filters, headers, null, null).then().statusCode(HttpStatus.BAD_REQUEST.value()).extract().response();
		filters.setStartDate("2018-10-10");
		filters.setEndDate("2018-10-11");
		filters.setModule("Test");
		response = post(exportHandler, filters, headers, null, null).then().statusCode(HttpStatus.OK.value()).extract().response();
		final String pageResult = response.body().asString();
		assertNotNull(pageResult);

		final Response response2 = post(exportHandler, null, headers, null, null).then().statusCode(HttpStatus.BAD_REQUEST.value()).extract()
				.response();
		final String pageResult2 = response2.body().asString();
		assertNotNull(pageResult2);

		post(exportHandler, null, headers, null, null).then().statusCode(HttpStatus.BAD_REQUEST.value());
	}

}
