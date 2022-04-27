package com.milkbasket.rest.services.template.test.handler;

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
import com.milkbasket.rest.services.template.report.bean.TemplateFilterBean;
import com.milkbasket.rest.services.template.report.bean.TemplatePageRequestBean;
import com.milkbasket.rest.services.template.report.bean.TemplateSortBean;
import com.milkbasket.rest.services.template.report.bean.TemplateViewBean;

import io.restassured.response.Response;

public class TemplateReportHandlerTest extends WebTestConfiguration {

	/** The headers. */
	Map<String, String> headers = new HashMap<>();

	@Test(priority = 10)
	public void test_beans() {
		TemplateViewBean viewBean = new TemplateViewBean();
		viewBean.setId(1L);
		viewBean.setType("Test");
		viewBean.setName("Test");
		viewBean.setSubject("Test");
		viewBean.setText("Test");
		viewBean.setManual(1);
		viewBean.setModule("Test");
		viewBean.setActive(1);
		viewBean.setActiveValue("Yes");
		viewBean.setManualValue("Yes");
		viewBean.setModuleValue("Yes");

		assertNotNull(viewBean.csvRow());
		TemplateFilterBean filterBean = new TemplateFilterBean();

		filterBean.setType("Test");
		filterBean.setManual(1);
		filterBean.setModule("Test");
		filterBean.setActive(1);

		assertNotNull(filterBean.getType());

		assertNotNull(filterBean.getManual());

		assertNotNull(filterBean.getModule());

		assertNotNull(filterBean.getActive());

	}

	@Test(priority = 20)
	public void test_message_templates_datalist() {

		String dataListHandler = "/message-templates/datalist";

		TemplatePageRequestBean request = new TemplatePageRequestBean();
		request.setPageNo(1);
		request.setPageSize(10);

		TemplateFilterBean filters = new TemplateFilterBean();
		request.setFilters(filters);

		TemplateSortBean sort = new TemplateSortBean();
		sort.setActive(SortDirection.ASC);
		sort.setId(SortDirection.ASC);
		sort.setManual(SortDirection.ASC);
		sort.setModule(SortDirection.DESC);

		request.setSort(sort);

		Response response = post(dataListHandler, request, headers, null, null).then().statusCode(HttpStatus.OK.value()).extract().response();

		Map<?, ?> pageResult = asObject(response.body().asString(), LinkedHashMap.class);

		assertTrue(((List<?>) pageResult.get("data")).size() <= 10);
		assertEquals(((Integer) pageResult.get("pageNo")).intValue(), 1);
		assertEquals(((Integer) pageResult.get("pageSize")).intValue(), 10);

		int defaultSize = PropertiesUtils.getIntegerProperty(CommonConstants.DEFAULT_PAGE_SIZE);
		int defaultPage = PropertiesUtils.getIntegerProperty(CommonConstants.DEFAULT_PAGE_NO);

		Response response2 = post(dataListHandler, null, headers, null, null).then().statusCode(HttpStatus.OK.value()).extract().response();
		Map<?, ?> pageResult2 = asObject(response2.body().asString(), LinkedHashMap.class);

		assertEquals(((Integer) pageResult2.get("pageNo")).intValue(), defaultPage);
		assertEquals(((Integer) pageResult2.get("pageSize")).intValue(), defaultSize);

		post(dataListHandler, null, headers, null, null).then().statusCode(HttpStatus.OK.value());

	}

	@Test(priority = 25)
	public void test_message_templates_datalist_stopped() {

		String dataListHandler = "/message-templates/datalist/stopped";

		TemplatePageRequestBean request = new TemplatePageRequestBean();
		request.setPageNo(1);
		request.setPageSize(10);

		TemplateFilterBean filters = new TemplateFilterBean();
		request.setFilters(filters);

		TemplateSortBean sort = new TemplateSortBean();
		sort.setName(SortDirection.ASC);
		sort.setText(SortDirection.ASC);
		sort.setType(SortDirection.ASC);

		request.setSort(sort);

		Response response = post(dataListHandler, request, headers, null, null).then().statusCode(HttpStatus.OK.value()).extract().response();

		Map<?, ?> pageResult = asObject(response.body().asString(), LinkedHashMap.class);

		assertTrue(((List<?>) pageResult.get("data")).size() <= 10);
		assertEquals(((Integer) pageResult.get("pageNo")).intValue(), 1);
		assertEquals(((Integer) pageResult.get("pageSize")).intValue(), 10);

		int defaultSize = PropertiesUtils.getIntegerProperty(CommonConstants.DEFAULT_PAGE_SIZE);
		int defaultPage = PropertiesUtils.getIntegerProperty(CommonConstants.DEFAULT_PAGE_NO);

		Response response2 = post(dataListHandler, null, headers, null, null).then().statusCode(HttpStatus.OK.value()).extract().response();
		Map<?, ?> pageResult2 = asObject(response2.body().asString(), LinkedHashMap.class);

		assertEquals(((Integer) pageResult2.get("pageNo")).intValue(), defaultPage);
		assertEquals(((Integer) pageResult2.get("pageSize")).intValue(), defaultSize);

		post(dataListHandler, null, headers, null, null).then().statusCode(HttpStatus.OK.value());

	}

	@Test(priority = 30)
	public void test_export_message_templates_datalist() {
		String exportHandler = "/message-templates/export";

		TemplateFilterBean filters = new TemplateFilterBean();

		Response response = post(exportHandler, filters, headers, null, null).then().statusCode(HttpStatus.OK.value()).extract().response();
		String pageResult = response.body().asString();
		assertNotNull(pageResult);

		Response response2 = post(exportHandler, null, headers, null, null).then().statusCode(HttpStatus.OK.value()).extract().response();
		String pageResult2 = response2.body().asString();
		assertNotNull(pageResult2);

		post(exportHandler, null, headers, null, null).then().statusCode(HttpStatus.OK.value());
	}

	@Test(priority = 35)
	public void test_export_message_templates_datalist_stopped() {

		String exportHandler = "/message-templates/export/stopped";

		TemplateFilterBean filters = new TemplateFilterBean();

		Response response = post(exportHandler, filters, headers, null, null).then().statusCode(HttpStatus.OK.value()).extract().response();
		String pageResult = response.body().asString();
		assertNotNull(pageResult);

		Response response2 = post(exportHandler, null, headers, null, null).then().statusCode(HttpStatus.OK.value()).extract().response();
		String pageResult2 = response2.body().asString();
		assertNotNull(pageResult2);

		post(exportHandler, null, headers, null, null).then().statusCode(HttpStatus.OK.value());
	}

}
