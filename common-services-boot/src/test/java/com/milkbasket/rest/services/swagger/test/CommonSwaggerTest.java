package com.milkbasket.rest.services.swagger.test;

import org.testng.annotations.Test;

import com.milkbasket.core.framework.common.utils.ContextUtils;
import com.milkbasket.core.framework.test.WebTestConfiguration;

public class CommonSwaggerTest extends WebTestConfiguration {

	@Test(priority = 0)
	public void test_generateSwagger() {
		generateSwagger(ContextUtils.getBootName(), "swagger", ContextUtils.getExternalConfig().getSwaggerHost());
		generateSwagger(ContextUtils.getBootName(), "uat-swagger", ContextUtils.getExternalConfig().getSwaggerHostUat());
	}
}
