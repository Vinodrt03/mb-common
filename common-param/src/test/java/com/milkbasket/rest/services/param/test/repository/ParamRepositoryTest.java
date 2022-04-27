package com.milkbasket.rest.services.param.test.repository;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.milkbasket.core.framework.test.BasicTestConfiguration;
import com.milkbasket.rest.services.param.entity.ParamEntity;
import com.milkbasket.rest.services.param.repository.ParamRepository;

public class ParamRepositoryTest extends BasicTestConfiguration {
	@Autowired
	ParamRepository paramRepository;

	@Test(priority = 4)
	public void test_findForNull() {
		Optional<ParamEntity> savedEntity = paramRepository.findById(9999999999L);
		ParamEntity entity = savedEntity.orElse(null);
		assertNull(entity);
	}

	@Test(priority = 8)
	public void test_find() {
		Optional<ParamEntity> savedEntity = paramRepository.findById(4L);
		ParamEntity entity = savedEntity.orElse(null);
		assertNotNull(entity);
		assertNotNull(entity.getParamKey());
		assertNotNull(entity.getParamValue());
		assertEquals(entity.getId().equals(4L), true);
		// assertEquals(entity.getActive().equals(1), true);
		assertEquals(entity.getFlag().equals(1), true);
	}
}
