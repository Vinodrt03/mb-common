package com.milkbasket.rest.services.param.test.handler;

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
import com.milkbasket.rest.services.param.report.bean.ParamFilterBean;
import com.milkbasket.rest.services.param.report.bean.ParamPageRequestBean;
import com.milkbasket.rest.services.param.report.bean.ParamSortBean;
import com.milkbasket.rest.services.param.report.bean.ParamViewBean;

import io.restassured.response.Response;

public class ParamReportHandlerTest extends WebTestConfiguration {

	/** The headers. */
	Map<String, String> headers = new HashMap<>();

	@Test(priority = 10)
	public void test_beans() {
		final ParamViewBean viewBean = new ParamViewBean();
		viewBean.setParamKey("Test");
		viewBean.setParamValue("Test");
		viewBean.setIsEditable(1);
		viewBean.setParamName("Test");

		final ParamFilterBean filterBean = new ParamFilterBean();
		filterBean.setParamKey("Test");

		assertNotNull(filterBean.getParamKey());

	}

	@Test(priority = 20)
	public void test_params_datalist() {

		final String dataListHandler = "/params/datalist";

		final ParamPageRequestBean request = new ParamPageRequestBean();
		request.setPageNo(1);
		request.setPageSize(10);

		final ParamFilterBean filters = new ParamFilterBean();
		filters.setParamKey("123test2");
		request.setFilters(filters);

		final ParamSortBean sort = new ParamSortBean();
		sort.setParamKey(SortDirection.ASC);

		request.setSort(sort);

		final Response response = post(dataListHandler, request, headers, null, null).then().statusCode(HttpStatus.OK.value()).extract().response();

		final Map<?, ?> pageResult = asObject(response.body().asString(), LinkedHashMap.class);

		assertTrue(((List<?>) pageResult.get("data")).size() <= 10);
		assertEquals(((Integer) pageResult.get("pageNo")).intValue(), 1);
		assertEquals(((Integer) pageResult.get("pageSize")).intValue(), 10);

		final int defaultSize = PropertiesUtils.getIntegerProperty(CommonConstants.DEFAULT_PAGE_SIZE);
		final int defaultPage = PropertiesUtils.getIntegerProperty(CommonConstants.DEFAULT_PAGE_NO);

		final ParamPageRequestBean request2 = new ParamPageRequestBean();
		final Response response2 = post(dataListHandler, request2, headers, null, null).then().statusCode(HttpStatus.OK.value()).extract().response();
		final Map<?, ?> pageResult2 = asObject(response2.body().asString(), LinkedHashMap.class);

		assertEquals(((Integer) pageResult2.get("pageNo")).intValue(), defaultPage);
		assertEquals(((Integer) pageResult2.get("pageSize")).intValue(), defaultSize);

		post(dataListHandler, null, headers, null, null).then().statusCode(HttpStatus.OK.value());

	}

	@Test(priority = 30)
	public void test_params_datalist_1() {

		final String dataListHandler = "/params/datalist";

		final ParamPageRequestBean request = new ParamPageRequestBean();
		request.setPageNo(1);
		request.setPageSize(10);

		final ParamFilterBean filters = new ParamFilterBean();
		filters.setParamKey("LAUNCH_DATE");
		filters.setIsEditable(0);
		request.setFilters(filters);

		final ParamSortBean sort = new ParamSortBean();
		sort.setParamKey(SortDirection.ASC);

		request.setSort(sort);

		final Response response = post(dataListHandler, request, headers, null, null).then().statusCode(HttpStatus.OK.value()).extract().response();

		final Map<?, ?> pageResult = asObject(response.body().asString(), LinkedHashMap.class);

		assertTrue(((List<?>) pageResult.get("data")).size() <= 10);
		assertEquals(((Integer) pageResult.get("pageNo")).intValue(), 1);
		assertEquals(((Integer) pageResult.get("pageSize")).intValue(), 10);

		final int defaultSize = PropertiesUtils.getIntegerProperty(CommonConstants.DEFAULT_PAGE_SIZE);
		final int defaultPage = PropertiesUtils.getIntegerProperty(CommonConstants.DEFAULT_PAGE_NO);

		final ParamPageRequestBean request2 = new ParamPageRequestBean();
		final Response response2 = post(dataListHandler, request2, headers, null, null).then().statusCode(HttpStatus.OK.value()).extract().response();
		final Map<?, ?> pageResult2 = asObject(response2.body().asString(), LinkedHashMap.class);

		assertEquals(((Integer) pageResult2.get("pageNo")).intValue(), defaultPage);
		assertEquals(((Integer) pageResult2.get("pageSize")).intValue(), defaultSize);

		post(dataListHandler, null, headers, null, null).then().statusCode(HttpStatus.OK.value());

	}

}
