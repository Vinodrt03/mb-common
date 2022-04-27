package com.milkbasket.rest.services.common.communication.template.test.repository;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.milkbasket.core.framework.dbsupport.jdbc.constants.Flag;
import com.milkbasket.core.framework.test.BasicTestConfiguration;
import com.milkbasket.rest.services.common.communication.template.entity.TemplateConstants.SendType;
import com.milkbasket.rest.services.common.communication.template.entity.TemplateEntity;
import com.milkbasket.rest.services.common.communication.template.repository.TemplateRepository;

/**
 * The Class TemplateRepositoryTest to prepare test suite for repository layer
 * testing.
 */
public class TemplateRepositoryTest extends BasicTestConfiguration {

	/** The communications repository. */
	@Autowired
	private TemplateRepository templateRepository;

	/** The id. */
	private Long id;

	/** The count. */
	private Integer count;

	/**
	 * Repository not null.
	 */
	@Test(priority = 0)
	public void repositoryNotNull() {
		assertNotNull(templateRepository);
	}

	/**
	 * Test find all.
	 */
	@Test(priority = 0)
	public void test_findAll() {
		final List<TemplateEntity> entities = (List<TemplateEntity>) templateRepository.findAll();
		count = entities.size();
		assertNotNull(entities);
	}

	/**
	 * Test save.
	 */
	@Test(priority = 10)
	public void test_save() {
		final TemplateEntity entity = new TemplateEntity();

		entity.setName("TEST_REPO_ORDER_PLACED");
		entity.setType("SMS");
		entity.setText("Hi, test template created");
		entity.setActive(Flag.ACTIVE);
		entity.setFlag(Flag.ACTIVE);
		entity.setManual(1);
		entity.setModule("BASKET");
		entity.setSendType(SendType.TRANSACTIONAL.toString());

		final TemplateEntity savedEntity = templateRepository.save(entity);
		assertNotNull(savedEntity.getId());
		id = savedEntity.getId();
		count = count + 1;

		final List<TemplateEntity> templates = templateRepository.findTemplatesOnTypeAndName(savedEntity.getType(), savedEntity.getName());
		assertNotNull(templates);
		assertTrue(templates.size() >= 1);

		final int cnt = templateRepository.getManuallyCreatedTemplateCount(savedEntity.getType(), savedEntity.getName());
		assertTrue(cnt >= 1);
	}

	/**
	 * Test find.
	 */
	@Test(priority = 20)
	public void test_find() {
		final Optional<TemplateEntity> savedEntity = templateRepository.findById(id);
		final TemplateEntity entity = savedEntity.orElse(null);
		assertNotNull(entity);
	}

	/**
	 * Test find for null.
	 */
	@Test(priority = 30)
	public void test_findForNull() {
		final Optional<TemplateEntity> savedEntity = templateRepository.findById(9999999999L);
		final TemplateEntity entity = savedEntity.orElse(null);
		assertNull(entity);
	}

	/**
	 * Test exists.
	 */
	@Test(priority = 40)
	public void test_exists() {
		assertTrue(templateRepository.existsById(id));
	}

	/**
	 * Test count.
	 */
	@Test(priority = 50)
	public void test_count() {
		assertEquals(templateRepository.count(), Long.valueOf(count).longValue());
	}

	/**
	 * Test find all by ids.
	 */
	@Test(priority = 60)
	public void test_findAllByIds() {
		final List<TemplateEntity> entities = (List<TemplateEntity>) templateRepository.findAllById(Arrays.asList(id, 9999999999L));
		assertEquals(entities.size(), 1);
	}

	/**
	 * Test save all.
	 */
	@Test(priority = 70)
	public void test_saveAll() {
		List<TemplateEntity> entities = (List<TemplateEntity>) templateRepository.findAllById(Arrays.asList(id, 9999999999L));
		final String slugNew = "TEST_REPO_ORDER_PLACED_ONE";

		entities = entities.stream().map(arg0 -> {
			arg0.setName(slugNew);
			arg0.setType("PUSH");
			arg0.setText("Hi, test template created");
			arg0.setActive(Flag.ACTIVE);
			arg0.setManual(1);
			arg0.setModule("BASKET");
			arg0.setSendType(SendType.TRANSACTIONAL.toString());
			arg0.setFlag(Flag.ACTIVE);

			return arg0;
		}).collect(Collectors.toList());
		templateRepository.saveAll(entities);
		final TemplateEntity entity = templateRepository.findById(id).orElse(null);
		assertNotNull(entity);

		assertEquals(entity.getName(), slugNew);
		assertEquals(entity.getType(), "PUSH");
		assertEquals(entity.getText(), "Hi, test template created");
		assertEquals(entity.getActive(), new Integer(1));
		assertEquals(entity.getFlag(), new Integer(1));
	}

	/**
	 * Test delete all.
	 */
	@Test(priority = 80)
	public void test_deleteAll() {
		try {
			templateRepository.deleteAll();
			assertFalse(true, "Delete All Not Permitted");
		} catch (final Exception e) {
			assertEquals(true, true);
		}
	}

	/**
	 * Test delete.
	 */
	@Test(priority = 90)
	public void test_delete() {
		try {
			final TemplateEntity entity = templateRepository.findById(id).orElse(null);
			templateRepository.delete(entity);
			assertFalse(true, "Delete Not Permitted");
		} catch (final Exception e) {
			assertEquals(true, true);
		}
	}

	/**
	 * Test soft delete.
	 */
	@Test(priority = 100)
	public void test_softDelete() {
		templateRepository.softDelete(id);
		final TemplateEntity entity = templateRepository.findById(id).orElse(null);

		count--;
		assertEquals(entity.getFlag().intValue(), 0);
	}

	/**
	 * Test delete all entities.
	 */
	@Test(priority = 120)
	public void test_deleteAllEntities() {
		final List<TemplateEntity> entities = (List<TemplateEntity>) templateRepository.findAllById(Arrays.asList(id));
		try {
			templateRepository.deleteAll(entities);
			assertFalse(true, "Delete All Not Permitted");
		} catch (final Exception e) {
			assertEquals(true, true);
		}
	}

	/**
	 * Test delete by id.
	 */
	@Test(priority = 130)
	public void test_deleteById() {
		try {
			templateRepository.deleteById(id);
			assertEquals(true, true);
		} catch (final Exception e) {
			assertFalse(true, "Delete by Id Should be Permitted in test mode.");
		}
	}
}
