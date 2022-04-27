package com.milkbasket.rest.services.param.test.service;

import static org.testng.Assert.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.milkbasket.core.framework.test.BasicTestConfiguration;
import com.milkbasket.rest.services.param.service.ParamService;

public class ParamServiceTest extends BasicTestConfiguration {
	@Autowired
	ParamService paramService;
	
	@Test
	public void test_serviceNotNull() {
		assertNotNull(paramService);
	}
	
}
