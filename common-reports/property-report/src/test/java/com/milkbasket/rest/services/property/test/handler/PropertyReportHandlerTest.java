package com.milkbasket.rest.services.property.test.handler;

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
import com.milkbasket.rest.services.property.report.bean.PropertyFilterBean;
import com.milkbasket.rest.services.property.report.bean.PropertyPageRequestBean;
import com.milkbasket.rest.services.property.report.bean.PropertySortBean;
import com.milkbasket.rest.services.property.report.bean.PropertyViewBean;

import io.restassured.response.Response;

public class PropertyReportHandlerTest extends WebTestConfiguration {

	/** The headers. */
	Map<String, String> headers = new HashMap<>();

	@Test(priority = 10)
	public void test_beans() {
		PropertyViewBean viewBean = new PropertyViewBean();
		viewBean.setPropertyKey("Test");
		viewBean.setPropertyValue("Test");

		PropertyFilterBean filterBean = new PropertyFilterBean();
		filterBean.setPropertyKey("Test");
		assertNotNull(viewBean.getPropertyKey());
		assertNotNull(viewBean.getPropertyValue());
		assertNotNull(filterBean.getPropertyKey());

	}

	@Test(priority = 20)
	public void test_propertys_datalist() {

		String dataListHandler = "/properties/datalist";

		PropertyPageRequestBean request = new PropertyPageRequestBean();
		request.setPageNo(1);
		request.setPageSize(10);

		PropertyFilterBean filters = new PropertyFilterBean();
		filters.setPropertyKey("123test2");
		request.setFilters(filters);

		PropertySortBean sort = new PropertySortBean();
		sort.setPropertyKey(SortDirection.ASC);

		request.setSort(sort);

		Response response = post(dataListHandler, request, headers, null, null).then().statusCode(HttpStatus.OK.value()).extract().response();

		Map<?, ?> pageResult = asObject(response.body().asString(), LinkedHashMap.class);

		assertTrue(((List<?>) pageResult.get("data")).size() <= 10);
		assertEquals(((Integer) pageResult.get("pageNo")).intValue(), 1);
		assertEquals(((Integer) pageResult.get("pageSize")).intValue(), 10);

		int defaultSize = PropertiesUtils.getIntegerProperty(CommonConstants.DEFAULT_PAGE_SIZE);
		int defaultPage = PropertiesUtils.getIntegerProperty(CommonConstants.DEFAULT_PAGE_NO);

		PropertyPageRequestBean request2 = new PropertyPageRequestBean();
		Response response2 = post(dataListHandler, request2, headers, null, null).then().statusCode(HttpStatus.OK.value()).extract().response();
		Map<?, ?> pageResult2 = asObject(response2.body().asString(), LinkedHashMap.class);

		assertEquals(((Integer) pageResult2.get("pageNo")).intValue(), defaultPage);
		assertEquals(((Integer) pageResult2.get("pageSize")).intValue(), defaultSize);

		post(dataListHandler, null, headers, null, null).then().statusCode(HttpStatus.OK.value());

	}

}
