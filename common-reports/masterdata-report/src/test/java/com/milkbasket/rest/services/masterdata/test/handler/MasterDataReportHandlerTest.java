package com.milkbasket.rest.services.masterdata.test.handler;

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
import com.milkbasket.rest.services.masterdata.report.bean.DataKeyValue;
import com.milkbasket.rest.services.masterdata.report.bean.MasterDataFilterBean;
import com.milkbasket.rest.services.masterdata.report.bean.MasterDataPageRequestBean;
import com.milkbasket.rest.services.masterdata.report.bean.MasterDataSortBean;
import com.milkbasket.rest.services.masterdata.report.bean.MasterDataViewBean;

import io.restassured.response.Response;

public class MasterDataReportHandlerTest extends WebTestConfiguration {

	/** The headers. */
	Map<String, String> headers = new HashMap<>();

	@Test(priority = 10)
	public void test_beans() {
		final MasterDataViewBean viewBean = new MasterDataViewBean();
		viewBean.setMasterKey("Test");

		final DataKeyValue viewBean2 = new DataKeyValue();
		viewBean2.setMasterKey("Test");
		viewBean2.setDataKey("0");
		viewBean2.setDataValue("Test0");

		assertNotNull(viewBean2.getMasterKey());
		assertNotNull(viewBean2.getDataKey());
		assertNotNull(viewBean2.getDataValue());

		assertNotNull(viewBean.csvRow());
		final MasterDataFilterBean filterBean = new MasterDataFilterBean();
		filterBean.setMasterKey("Test");

		assertNotNull(filterBean.getMasterKey());

	}

	@Test(priority = 20)
	public void test_datalist() {

		final String dataListHandler = "/masterdata/datalist";

		final MasterDataPageRequestBean request = new MasterDataPageRequestBean();
		request.setPageNo(1);
		request.setPageSize(10);

		final MasterDataFilterBean filters = new MasterDataFilterBean();
		filters.setMasterKey("123test2");
		request.setFilters(filters);

		final MasterDataSortBean sort = new MasterDataSortBean();
		sort.setMasterKey(SortDirection.DESC);

		request.setSort(sort);

		final Response response = post(dataListHandler, request, headers, null, null).then().statusCode(HttpStatus.OK.value()).extract().response();

		final Map<?, ?> pageResult = asObject(response.body().asString(), LinkedHashMap.class);

		assertTrue(((List<?>) pageResult.get("data")).size() <= 10);
		assertEquals(((Integer) pageResult.get("pageNo")).intValue(), 1);
		assertEquals(((Integer) pageResult.get("pageSize")).intValue(), 10);

		final int defaultSize = PropertiesUtils.getIntegerProperty(CommonConstants.DEFAULT_PAGE_SIZE);
		final int defaultPage = PropertiesUtils.getIntegerProperty(CommonConstants.DEFAULT_PAGE_NO);

		final MasterDataPageRequestBean request2 = new MasterDataPageRequestBean();
		final Response response2 = post(dataListHandler, request2, headers, null, null).then().statusCode(HttpStatus.OK.value()).extract().response();
		final Map<?, ?> pageResult2 = asObject(response2.body().asString(), LinkedHashMap.class);

		assertEquals(((Integer) pageResult2.get("pageNo")).intValue(), defaultPage);
		assertEquals(((Integer) pageResult2.get("pageSize")).intValue(), defaultSize);

		post(dataListHandler, null, headers, null, null).then().statusCode(HttpStatus.OK.value());

	}

	@Test(priority = 25)
	public void test_export_datalist() {

		final String exportHandler = "/masterdata/export";

		final MasterDataFilterBean filters = new MasterDataFilterBean();
		filters.setMasterKey("123test2");

		final Response response = post(exportHandler, filters, headers, null, null).then().statusCode(HttpStatus.OK.value()).extract().response();
		final String pageResult = response.body().asString();
		assertNotNull(pageResult);

		final Response response2 = post(exportHandler, null, headers, null, null).then().statusCode(HttpStatus.OK.value()).extract().response();
		final String pageResult2 = response2.body().asString();
		assertNotNull(pageResult2);

		post(exportHandler, null, headers, null, null).then().statusCode(HttpStatus.OK.value());
	}

	@Test(priority = 40)
	public void test_getAllMasterData() {
		final String handler = "/masterdata";
		final Map<String, String> map = new HashMap<>();
		map.put("masterKey", "SCHEDULED_JOB");
		final Response response = get(handler, new HashMap<String, String>(), map, new HashMap<>());
		final List<?> result = response.getBody().as(List.class);
		// result.stream().forEach(row ->
		// System.out.println(JSONUtils.objectToJson(row)));
		assertTrue(result.size() > 0, "SCHEDULED_JOB not found in masterdata");
	}
}
