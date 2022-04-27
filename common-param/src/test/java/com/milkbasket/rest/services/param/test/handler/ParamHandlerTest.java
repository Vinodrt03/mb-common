package com.milkbasket.rest.services.param.test.handler;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import com.milkbasket.core.framework.test.WebTestConfiguration;
import com.milkbasket.rest.services.param.bean.ParamBean;

public class ParamHandlerTest extends WebTestConfiguration {

	Map<String, String> headers = new HashMap<>();

	private Object getParamBean() {
		ParamBean param = ParamBean.newInstance();
		param.setParamValue("3.4.0");
		return param;
	}

	@Test
	public void test_updateParam() {
		Map<String, String> requestParams = new HashMap<>();
		Map<String, String> pathParams = new HashMap<>();
		pathParams.put("id", "1450");
		io.restassured.response.Response response = patch("/params/{id}/value", getParamBean(), headers, requestParams, pathParams);
		assertEquals(response.getStatusCode(), HttpStatus.SC_NOT_FOUND);
		response.getBody().asString();

		pathParams.put("id", "4");
		io.restassured.response.Response response2 = patch("/params/{id}/value", getParamBean(), headers, requestParams, pathParams);
		assertEquals(response2.getStatusCode(), HttpStatus.SC_BAD_REQUEST);

		pathParams.put("id", "40000");
		io.restassured.response.Response response3 = patch("/params/{id}/value", getParamBean(), headers, requestParams, pathParams);
		assertEquals(response3.getStatusCode(), HttpStatus.SC_NOT_FOUND);
	}
}
