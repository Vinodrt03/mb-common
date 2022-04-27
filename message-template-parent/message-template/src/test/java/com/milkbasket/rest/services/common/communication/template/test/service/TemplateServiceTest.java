package com.milkbasket.rest.services.common.communication.template.test.service;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.milkbasket.core.framework.common.exception.NoDataException;
import com.milkbasket.core.framework.common.exception.ServerException;
import com.milkbasket.core.framework.common.exception.ValidationException;
import com.milkbasket.core.framework.dbsupport.jdbc.constants.Flag;
import com.milkbasket.core.framework.test.BasicTestConfiguration;
import com.milkbasket.rest.services.communication.entity.TemplateConstants;
import com.milkbasket.rest.services.communication.entity.TemplateEntity;
import com.milkbasket.rest.services.common.communication.template.service.TemplateService;

/**
 * The Class TemplateServiceTest.
 */
public class TemplateServiceTest extends BasicTestConfiguration {

	/** The communications service. */
	@Autowired
	private TemplateService templateService;

	/** The id. */
	private Long id;

	Set<Long> createdIds = new HashSet<>();

	String flagValue = RandomStringUtils.randomAlphanumeric(5);

	/**
	 * Test service not null.
	 */
	@Test(priority = 0)
	public void test_serviceNotNull() {
		assertNotNull(templateService);
	}

	@Test(priority = 5)
	public void test_findDeleteWhichIsNotAvailable() {
		try {
			templateService.find(-10000L);
		} catch (final Exception e) {
			assertEquals(true, e instanceof NoDataException);
		}

		try {
			templateService.delete(-10000L);
		} catch (final Exception e) {
			assertEquals(true, e instanceof NoDataException);
		}
	}

	/**
	 * Test find all with parameter.
	 *
	 * @throws ServerException
	 *             the server exception
	 */
	@Test(priority = 11)
	public void test_findAll_with_parameter() {
		final Map<String, Object> params = new HashMap<>();
		params.put("type", "SMS");
		final List<TemplateEntity> results = templateService.findAll(params);
		assertNotNull(results);
	}

	/**
	 * Test save.
	 *
	 * @throws ServerException
	 *             the server exception
	 *
	 */
	@Test(priority = 20)
	public void test_save() {
		TemplateEntity entity = null;
		try {
			entity = TemplateEntity.newInstance();
			entity.setName("TEST_REPO_ORDER_PLACED" + flagValue);
			entity.setType("EMAIL");
			entity.setText("Hi, test template created");
			entity.setActive(Flag.ACTIVE);
			entity.setFlag(Flag.ACTIVE);
			entity.setManual(1);
			entity.setModule("BASKET");
			entity.setSendType(TemplateConstants.SendType.TRANSACTIONAL.toString());
			entity = templateService.save(entity);
			id = entity.getId();
			createdIds.add(id);

		} catch (final Exception e) {
			assertEquals(true, e instanceof ValidationException);
		}

		entity = TemplateEntity.newInstance();

		entity.setName("TEST_REPO_ORDER_PLACED" + flagValue);
		entity.setType("SMS");
		entity.setText("Hi, test template created");
		entity.setActive(Flag.ACTIVE);
		entity.setFlag(Flag.ACTIVE);
		entity.setManual(1);
		entity.setModule("BASKET");

		entity = templateService.save(entity);
		assertNotNull(entity.getId());
		id = entity.getId();
		createdIds.add(id);

		final TemplateEntity template = templateService.findOnTypeAndName(entity.getType(), entity.getName());
		assertNotNull(template);

		final Boolean status = templateService.validateIfTemplateIsManual(entity.getType(), entity.getName());
		assertTrue(status);

		try {
			templateService.findOnTypeAndName("SMS", RandomStringUtils.randomAlphabetic(10));
		} catch (final Exception e) {
			assertEquals(true, e instanceof NoDataException);
		}

		try {
			entity = TemplateEntity.newInstance();
			entity.setName("TEST_REPO_ORDER_PLACED" + flagValue);
			entity.setType("SMS");
			entity.setText("Hi, test template created");
			entity.setActive(Flag.ACTIVE);
			entity.setFlag(Flag.ACTIVE);
			entity.setManual(1);
			entity.setModule("BASKET");
			templateService.save(entity);
			id = entity.getId();
			createdIds.add(id);
		} catch (final Exception e) {
			assertEquals(true, e instanceof ValidationException);
		}

	}

	/**
	 * Test find and update.
	 *
	 * @throws ServerException
	 *             the server exception
	 *
	 */
	@Test(priority = 30)
	public void test_findAndUpdate() {
		TemplateEntity entity = TemplateEntity.newInstance();
		entity = TemplateEntity.newInstance();

		entity.setName("TEST_REPO_ORDER_PLACED" + flagValue);
		entity.setType("SMS");
		entity.setText("Hi, test template created");
		entity.setActive(Flag.ACTIVE);
		entity.setFlag(Flag.ACTIVE);
		entity.setManual(1);
		entity.setModule("BASKET");
		entity.setSendType(TemplateConstants.SendType.TRANSACTIONAL.toString());
		final String name = flagValue + "TEST_PLACE_ORDER";
		entity.setName(name);
		entity.setId(id);
		TemplateEntity savedEntity = templateService.update(entity);

		assertEquals(savedEntity.getId(), entity.getId());
		assertEquals(savedEntity.getName(), name);
		assertEquals(savedEntity.getType(), entity.getType());
		assertEquals(savedEntity.getText(), entity.getText());

		try {
			savedEntity = templateService.update(entity);
			// assertEquals(savedEntity.getName(), "");
		} catch (final Exception e) {
			assertEquals(true, e instanceof ValidationException);
		}

	}

	/**
	 * Test soft delete.
	 *
	 * @throws ServerException
	 *             the server exception
	 *
	 */
	@Test(priority = 40)
	public void test_softDelete() {
		templateService.delete(id);
		try {
			final TemplateEntity savedEntity = templateService.find(id);
			assertNull(savedEntity);
		} catch (final Exception e) {
			assertEquals(true, e instanceof NoDataException);
		}
	}

	/**
	 * Test clean up.
	 *
	 * @throws ServerException
	 *             the server exception
	 *
	 */
	@Test(priority = 50)
	public void test_cleanUp() {

		try {
			templateService.deleteById(id);
			final TemplateEntity savedEntity = templateService.find(id);
			assertNull(savedEntity);
		} catch (final Exception e) {
			assertEquals(true, e instanceof NoDataException);
		}

		for (final Long long1 : createdIds) {
			try {
				templateService.deleteById(long1);
			} catch (final Exception e) {
			}
		}
	}
}
