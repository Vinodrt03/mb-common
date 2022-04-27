package com.milkbasket.rest.services.push.common.test.repository;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.milkbasket.core.framework.test.BasicTestConfiguration;
import com.milkbasket.rest.services.communication.entity.DeviceEntity;
import com.milkbasket.rest.services.push.common.repository.DeviceRepository;
import com.milkbasket.rest.services.push.common.test.data.DeviceTestData;

/**
 * The Class DeviceRepositoryTest.
 */
public class DeviceRepositoryTest extends BasicTestConfiguration {

	/** The Device repository. */
	@Autowired
	private DeviceRepository deviceRepository;

	/** The id. */
	private Long id;

	/** The count. */
	private Integer count = 0;

	private Long customerId = 11l;

	private Set<Long> idSet = new HashSet<>();

	/**
	 * Repository not null.
	 */
	@Test(priority = 0)
	public void repositoryNotNull() {
		assertNotNull(deviceRepository);
	}

	/**
	 * Test find all.
	 */
	@Test(priority = 10)
	public void test_findAll() {
		List<DeviceEntity> entities = (List<DeviceEntity>) deviceRepository.findAll();
		count = entities.size();
		assertNotNull(entities);
	}

	/**
	 * Test save.
	 */
	@Test(priority = 20)
	public void test_save() {
		DeviceEntity entity = DeviceTestData.newEntity(0, customerId);
		DeviceEntity savedEntity = deviceRepository.save(entity);
		assertNotNull(savedEntity.getId());
		id = savedEntity.getId();
		idSet.add(id);
		count = count + 1;
	}

	/**
	 * Test find.
	 */
	@Test(priority = 30)
	public void test_find() {
		Optional<DeviceEntity> savedEntity = deviceRepository.findById(id);
		DeviceEntity entity = savedEntity.orElse(null);
		assertNotNull(entity);
	}

	/**
	 * Test find for null.
	 */
	@Test(priority = 40)
	public void test_findForNull() {
		Optional<DeviceEntity> savedEntity = deviceRepository.findById(9999999999L);
		DeviceEntity entity = savedEntity.orElse(null);
		assertNull(entity);
	}

	/**
	 * Test exists.
	 */
	@Test(priority = 50)
	public void test_exists() {
		assertTrue(deviceRepository.existsById(id));
	}

	/**
	 * Test count.
	 */
	@Test(priority = 60)
	public void test_count() {
		assertEquals(deviceRepository.count(), Long.valueOf(count).longValue());
	}

	/**
	 * Test find all by ids.
	 */
	@Test(priority = 70)
	public void test_findAllByIds() {
		List<DeviceEntity> entities = (List<DeviceEntity>) deviceRepository.findAllById(Arrays.asList(id, 9999999999L));
		DeviceEntity entitity = deviceRepository.getById(id);
		assertNotNull(entitity);
		assertEquals(entities.size(), 1);
	}

	@Test(priority = 80)
	public void test_saveAll() {
		Iterable<DeviceEntity> savedEntities = deviceRepository.saveAll(DeviceTestData.newEntityList(0, customerId));
		for (DeviceEntity entity : savedEntities) {
			assertNotNull(entity);
			idSet.add(entity.getId());
		}
		List<DeviceEntity> savedList = (List<DeviceEntity>) deviceRepository.findAllById(idSet);
		assertTrue(21 <= savedList.size());
	}

	/*
	 * @Test(priority = 90) public void test_Unique() { boolean newRecord = true;
	 * boolean existingRecord = false; boolean updateRecord= true; DeviceEntity
	 * enitity = deviceRepository.getById(id); //newRecord =
	 * deviceRepository.isUnique(null, "", System.currentTimeMillis() + "", false);
	 * //existingRecord = deviceRepository.isUnique(null, "", enitity.get(), false);
	 * //updateRecord = deviceRepository.isUnique(id, "", enitity.get(), false);
	 * assertTrue(newRecord); assertFalse(existingRecord); assertTrue(updateRecord);
	 * 
	 * }
	 * 
	 */

	/**
	 * Test delete all.
	 */
	@Test(priority = 160)
	public void test_deleteAll() {
		try {
			deviceRepository.deleteAll();
			assertFalse(true, "Delete All Not Permitted");
		} catch (Exception e) {
			assertTrue(true, "Delete All Not Permitted");
		}
	}

	/**
	 * Test delete.
	 */
	@Test(priority = 170)
	public void test_delete() {
		try {
			DeviceEntity entity = deviceRepository.findById(id).orElse(null);
			deviceRepository.delete(entity);
			assertFalse(true, "Delete Not Permitted");
		} catch (Exception e) {
			assertTrue(true, "Delete Not Permitted");
		}
	}

	/**
	 * Test soft delete.
	 */
	@Test(priority = 180)
	public void test_softDelete() {
		deviceRepository.softDelete(id);
		DeviceEntity entity = deviceRepository.findById(id).orElse(null);
		assertEquals(entity.getFlag().intValue(), 0);
	}

	/**
	 * Test delete all entities.
	 */
	@Test(priority = 190)
	public void test_deleteAllEntities() {
		List<DeviceEntity> entities = (List<DeviceEntity>) deviceRepository.findAllById(Arrays.asList(id));
		try {
			deviceRepository.deleteAll(entities);
			assertFalse(true, "Delete All Not Permitted");
		} catch (Exception e) {
			assertEquals(true, true);
		}
	}

	/**
	 * Test delete by id.
	 */
	@Test(priority = 200)
	public void test_deleteById() {
		for (Long entityId : idSet) {
			try {
				deviceRepository.deleteById(entityId);
				assertEquals(true, true);
			} catch (Exception e) {
				assertFalse(true, "Delete by Id Should be Permitted in test mode.");
			}
		}
	}
}
