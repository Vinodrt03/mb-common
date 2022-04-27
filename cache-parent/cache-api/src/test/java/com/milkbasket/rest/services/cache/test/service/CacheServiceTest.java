package com.milkbasket.rest.services.cache.test.service;

import static org.testng.Assert.assertEquals;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.milkbasket.core.framework.caching.service.EntityCacheService;
import com.milkbasket.core.framework.common.exception.NoDataException;
import com.milkbasket.core.framework.common.exception.ServerException;
import com.milkbasket.core.framework.logging.core.AppLogger;
import com.milkbasket.core.framework.logging.manager.LoggingManager;
import com.milkbasket.core.framework.test.BasicTestConfiguration;
import com.milkbasket.core.framework.utility.JSONUtils;

/**
 * The Class TemplateServiceTest.
 */
public class CacheServiceTest extends BasicTestConfiguration {

	private static final AppLogger _LOGGER = LoggingManager.getLogger(CacheServiceTest.class);

	@Autowired
	private EntityCacheService cacheReportService;

	/** The id. */
	private Long id;

	/**
	 * Test service not null.
	 */
	@Test(priority = 0)
	public void test_serviceNotNull() {
		// assertNotNull(templateService);
	}

	@Test(priority = 100)
	public void test_Cache() {
		cacheReportService.reloadAllEntities("brands");
	}

	@Test(priority = 2)
	public void test_ReadValue() {
		try {
			// cacheReportService.reloadAll();

			cacheReportService.reloadAllEntities("product_hub");

			final Object obj1 = cacheReportService.getEntityById("product_hub", 2l);

			if (obj1 != null) {
				_LOGGER.info("Object 1=" + JSONUtils.objectToJson(obj1));
			}

			final Map<?, ?> obj2 = cacheReportService.getEntityByKey("product_hub", 2l, 100l);

			if (obj2 != null) {
				_LOGGER.info("Object 2=" + JSONUtils.objectToJson(obj2));
			} else {
				_LOGGER.info("Object 2 is null");
			}

		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 5)
	public void test_ReadValue_1() {
		try {
			// cacheReportService.reloadAll();

			// cacheReportService.reloadAllEntities("vendors_hub");

			final Object obj1 = cacheReportService.getEntityById("vendors_hub", 1l);

			if (obj1 != null) {
				_LOGGER.info("Object 1=" + JSONUtils.objectToJson(obj1));
			}

			final Map<?, ?> obj2 = cacheReportService.getEntityByKey("vendors_hub", 1l, 1l);

			if (obj2 != null) {
				_LOGGER.info("Object 2=" + JSONUtils.objectToJson(obj2));
			} else {
				_LOGGER.info("Object 2 is null");
			}

		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 6)
	public void test_ReadValue1() {
		try {
			// cacheReportService.reloadAll();

			cacheReportService.reloadAllEntities("product_hub");

			final Map<?, ?> obj2 = cacheReportService.getEntityByKey("product_hub", 2l, 100l);

			if (obj2 != null) {
				_LOGGER.info("Object 2=" + JSONUtils.objectToJson(obj2));
			} else {
				_LOGGER.info("Object 2 is null");
			}

		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 7)
	public void test_ReadValue2() {
		try {
			// cacheReportService.reloadAll();

			final Map<String, Object> param = new HashMap<>();

			param.put("id", 11l);
			param.put("hubId", 5l);
			param.put("cityId", 1l);
			param.put("societyId", 100l);
			param.put("firstName", "Sanjeev");
			param.put("lastName", "Jha");
			param.put("lastOrderDate", new Date());

			final Map<String, Object> param2 = new HashMap<>();

			param2.put("id", 12l);
			param2.put("hubId", 5l);
			param2.put("cityId", 1l);
			param2.put("societyId", 100l);
			param2.put("firstName", "Smita");
			param2.put("lastName", "Jha");
			param2.put("lastOrderDate", new Date());

			cacheReportService.saveObject("profile", param, false, 5l, 11l);
			cacheReportService.saveObject("profile", param2, false, 5l, 12l);

			cacheReportService.saveObject("profile", param, false, 5l);

			final Map<?, ?> obj2 = cacheReportService.getEntityByKey("profile", 5l, 11l);

			if (obj2 != null) {
				_LOGGER.info("Object 2=" + JSONUtils.objectToJson(obj2));
			} else {
				_LOGGER.info("Object 2 is null");
			}

		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 9)
	public void test_ReadValue4() {
		try {
			// final Collection<Object> object =
			// cacheReportService.getEntityListByKeys("profile", 5l, "*");

			final Collection<Object> object = cacheReportService.getEntityList("test.shared_cache.product_hub.1.100",
					"test.shared_cache.product_hub.1.1000000", "test.shared_cache.product_hub.1.102", "test.shared_cache.product_hub.1.99999999",
					"test.shared_cache.product_hub.1.101", "test.shared_cache.product_hub.1.1"

			);

			object.forEach(obj -> {
				_LOGGER.info("Object =" + JSONUtils.objectToJson(obj));
			});

		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 8)
	public void test_ReadValue3() {
		try {
			// final Collection<Object> object =
			// cacheReportService.getEntityListByKeys("profile", 5l, "*");

			final Collection<Object> object = cacheReportService.getEntityListByKeys("product_hub", 1l, "*");

			object.forEach(obj -> {
				_LOGGER.info("Object =" + JSONUtils.objectToJson(obj));
			});

		} catch (final Exception e) {
			e.printStackTrace();
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
			// templateService.deleteById(id);
			// final TemplateEntity savedEntity = templateService.find(id);
			// assertNull(savedEntity);
		} catch (final Exception e) {
			assertEquals(true, e instanceof NoDataException);
		}

	}
}
