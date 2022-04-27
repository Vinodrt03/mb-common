package com.milkbasket.rest.services.push.common.test.service;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.milkbasket.core.framework.common.exception.NoDataException;
import com.milkbasket.core.framework.test.BasicTestConfiguration;
import com.milkbasket.rest.services.communication.entity.DeviceEntity;
import com.milkbasket.rest.services.push.common.repository.DeviceRepository;
import com.milkbasket.rest.services.push.common.service.DeviceService;
import com.milkbasket.rest.services.push.common.test.data.DeviceTestData;

/**
 * The Class DeviceServiceTest.
 */
public class DeviceServiceTest extends BasicTestConfiguration {

	/** The Device service. */
	@Autowired
	private DeviceService deviceService;

	@Autowired
	private DeviceRepository deviceRepository;

	/** The id. */
	private Long id;

	private Long customerId = 11l;

	private Set<Long> idSet = new HashSet<>();

	private DeviceEntity savedEntity = null;

	@Test(priority = 0)
	public void test_serviceNotNull() {
		assertNotNull(deviceService);
		assertNotNull(deviceRepository);
	}

	@Test(priority = 20)
	public void test_save() {
		DeviceEntity entity = DeviceTestData.newEntity(0, customerId);
		savedEntity = deviceService.save(entity);
		assertNotNull(savedEntity.getId());
		id = savedEntity.getId();
		idSet.add(id);

	}

	@Test(priority = 40)
	public void test_findWithFilter() {
		DeviceEntity entity = deviceService.find(id);
		Map<String, Object> filters = new HashMap<>();
		// check for sort field
		filters.put("deviceOs", entity.getDeviceOs());
		DeviceEntity findWithFilter = deviceService.findAll(filters).get(0);
		assertEquals(findWithFilter.getDeviceOs(), entity.getDeviceOs());
	}

	@Test(priority = 50)
	public void test_findDeleteWhichIsNotAvailable() {
		try {
			deviceService.find(-10000L);
		} catch (Exception e) {
			assertEquals(true, e instanceof NoDataException);
		}
		try {
			deviceService.delete(-10000L);
		} catch (Exception e) {
			assertEquals(true, e instanceof NoDataException);
		}
	}

	@Test(priority = 80)
	public void test_findAndUpdate() {
		DeviceEntity entity = DeviceTestData.newEntity(0, customerId);
		entity.setDeviceLogout(entity.getDeviceLogout());
		entity.setId(id);
		entity = deviceService.update(entity);
		DeviceEntity savedEntity = deviceService.find(id);
		assertNotNull(savedEntity);
		assertEquals(savedEntity.getId(), entity.getId());
		assertEquals(savedEntity.getDeviceLogout(), entity.getDeviceLogout());
	}

	@Test(priority = 120)
	public void test_softDelete() {
		deviceService.delete(id);
		try {
			DeviceEntity savedEntity = deviceService.find(id);
			assertNull(savedEntity);
		} catch (Exception e) {
			assertEquals(true, e instanceof NoDataException);
		}
	}

	@Test(priority = 140)
	public void testfindWithDeletedFilter() {
		List<DeviceEntity> findWithDeleted = deviceService.findAllWithDeleted(null);
		boolean hasId = findWithDeleted.stream().anyMatch(p -> p.getId().equals(id));
		assertTrue(hasId);
		Map<String, Object> filters = new HashMap<>();

		filters.put("deviceOs", DeviceTestData.AUTO_KEY.toLowerCase() + "To be changed");
		List<DeviceEntity> findWithFilterDeleted = deviceService.findAllWithDeleted(filters);
		hasId = findWithDeleted.stream().anyMatch(p -> p.getId().equals(id));
		// assertTrue(findWithFilterDeleted.size() >= 1);
	}

	@Test(priority = 150)
	public void test_AllWithoutLimit() {
		final Map<String, Object> filters = new HashMap<>();
		final List<DeviceEntity> list = deviceService.findAllWithoutAnyLimit(filters);
		assertNotNull(list);
	}

	@Test(priority = 200)
	public void test_cleanUp() {
		for (Long entityId : idSet) {
			try {
				deviceService.deleteById(entityId);
				DeviceEntity savedEntity = deviceService.find(entityId);
				assertNull(savedEntity);
			} catch (Exception e) {
				assertEquals(true, e instanceof NoDataException);
			}
		}
	}
}
