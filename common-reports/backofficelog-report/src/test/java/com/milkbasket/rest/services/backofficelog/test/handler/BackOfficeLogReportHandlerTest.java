package com.milkbasket.rest.services.backofficelog.test.handler;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.testng.annotations.Test;

import com.milkbasket.core.framework.common.constants.CommonConstants;
import com.milkbasket.core.framework.common.constants.CommonFormats;
import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection;
import com.milkbasket.core.framework.property.utils.PropertiesUtils;
import com.milkbasket.core.framework.test.WebTestConfiguration;
import com.milkbasket.core.framework.utility.DateUtils;
import com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogFilterBean;
import com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogPageRequestBean;
import com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogSortBean;
import com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogSummaryFilterBean;
import com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogViewBean;

import io.restassured.response.Response;

/**
 * Handler to test fetch of backoffice logs
 *
 * @author NitinKhaitan
 * @version $Id: $Id
 */
public class BackOfficeLogReportHandlerTest extends WebTestConfiguration {

	/** The headers. */
	Map<String, String> headers = new HashMap<>();

	@Test(priority = 10)
	public void test_beans() {
		final BackOfficeLogViewBean viewBean = new BackOfficeLogViewBean();
		viewBean.setId(1L);
		viewBean.setUserId(1L);
		viewBean.setName("Test");
		viewBean.setDateTime("2019-01-01");
		viewBean.setAction("Test");
		viewBean.setHubId(1L);
		viewBean.setProductId(1L);
		viewBean.setEmail("test@gmail.com");

		assertNotNull(viewBean.csvRow());
		final BackOfficeLogFilterBean filterBean = new BackOfficeLogFilterBean();
		filterBean.setUserId(1L);
		filterBean.setStartDate("2019-01-01");
		filterBean.setEndDate("2019-01-01");
		filterBean.setHubId(1L);
		filterBean.setProductId(1L);
		filterBean.setAction("Test");
		filterBean.setEmail("test@gmail.com");

		assertNotNull(filterBean.getUserId());
		assertNotNull(filterBean.getStartDate());
		assertNotNull(filterBean.getEndDate());
		assertNotNull(filterBean.getAction());
		assertNotNull(filterBean.getHubId());
		assertNotNull(filterBean.getProductId());
	}

	@Test(priority = 20)
	public void test_users_datalist() {

		final String dataListHandler = "/backoffice-logs/datalist";

		final BackOfficeLogPageRequestBean request = new BackOfficeLogPageRequestBean();
		request.setPageNo(1);
		request.setPageSize(10);

		final BackOfficeLogFilterBean filterBean = new BackOfficeLogFilterBean();
		filterBean.setAction("%view%");
		filterBean.setStartDate(DateUtils.toString(CommonFormats.DATE_FORMAT, new Date()));
		filterBean.setUserId(Long.valueOf(1));
		request.setFilters(filterBean);

		final BackOfficeLogSortBean sort = new BackOfficeLogSortBean();
		sort.setId(SortDirection.DESC);
		sort.setAction(SortDirection.ASC);
		sort.setDateTime(SortDirection.ASC);
		sort.setName(SortDirection.ASC);
		request.setSort(sort);

		final Response response = post(dataListHandler, request, headers, null, null).then().statusCode(HttpStatus.OK.value()).extract().response();

		final Map<?, ?> pageResult = asObject(response.body().asString(), LinkedHashMap.class);

		assertTrue(((List<?>) pageResult.get("data")).size() <= 10);
		assertEquals(((Integer) pageResult.get("pageNo")).intValue(), 1);
		assertEquals(((Integer) pageResult.get("pageSize")).intValue(), 10);

		final int defaultSize = PropertiesUtils.getIntegerProperty(CommonConstants.DEFAULT_PAGE_SIZE);
		final int defaultPage = PropertiesUtils.getIntegerProperty(CommonConstants.DEFAULT_PAGE_NO);

		final BackOfficeLogPageRequestBean request2 = new BackOfficeLogPageRequestBean();
		final Response response2 = post(dataListHandler, request2, headers, null, null).then().statusCode(HttpStatus.OK.value()).extract().response();
		final Map<?, ?> pageResult2 = asObject(response2.body().asString(), LinkedHashMap.class);

		assertEquals(((Integer) pageResult2.get("pageNo")).intValue(), defaultPage);
		assertEquals(((Integer) pageResult2.get("pageSize")).intValue(), defaultSize);

		post(dataListHandler, null, headers, null, null).then().statusCode(HttpStatus.OK.value());

	}

	@Test(priority = 25)
	public void test_export_users_datalist() {

		final String exportHandler = "/backoffice-logs/export";

		final BackOfficeLogPageRequestBean request = new BackOfficeLogPageRequestBean();
		request.setPageNo(1);
		request.setPageSize(10);

		final BackOfficeLogFilterBean filterBean = new BackOfficeLogFilterBean();
		filterBean.setAction("%view%");
		filterBean.setEndDate(DateUtils.toString(CommonFormats.DATE_FORMAT, new Date()));
		filterBean.setUserId(Long.valueOf(1));

		final BackOfficeLogSortBean sort = new BackOfficeLogSortBean();
		sort.setId(SortDirection.DESC);
		sort.setAction(SortDirection.ASC);
		sort.setDateTime(SortDirection.ASC);

		final Response response = post(exportHandler, request, headers, null, null).then().statusCode(HttpStatus.OK.value()).extract().response();
		final String pageResult = response.body().asString();
		assertNotNull(pageResult);

		final Response response2 = post(exportHandler, null, headers, null, null).then().statusCode(HttpStatus.OK.value()).extract().response();
		final String pageResult2 = response2.body().asString();
		assertNotNull(pageResult2);
	}

	@Test(priority = 30)
	public void test_backofficelog_summary_datalist() {

		final String dataListHandler = "/backoffice-logs/summary";

		final BackOfficeLogSummaryFilterBean filterBean = new BackOfficeLogSummaryFilterBean();
		filterBean.setAction("%view%");

		post(dataListHandler, filterBean, headers, null, null).then().statusCode(HttpStatus.OK.value()).extract().response();
		filterBean.setEndDate("2019-01-01");
		doBadPost(dataListHandler, headers, filterBean);

		final BackOfficeLogSummaryFilterBean filterBean2 = new BackOfficeLogSummaryFilterBean();
		post(dataListHandler, filterBean2, headers, null, null).then().statusCode(HttpStatus.OK.value()).extract().response();

		post(dataListHandler, null, headers, null, null).then().statusCode(HttpStatus.OK.value());

		filterBean2.setStartDate("2018-01-01");
		filterBean2.setEndDate("2018-07-07");

		doBadPost(dataListHandler, headers, filterBean2);
	}
}
