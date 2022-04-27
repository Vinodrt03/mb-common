package com.milkbasket.rest.services.cache.test.handler;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Test;

import com.milkbasket.core.framework.caching.repository.CacheRepository;
import com.milkbasket.core.framework.caching.service.EntityCacheService;
import com.milkbasket.core.framework.caching.utils.CacheUtils.Keys;
import com.milkbasket.core.framework.common.exception.ServerException;
import com.milkbasket.core.framework.common.utils.ContextUtils;
import com.milkbasket.core.framework.logging.core.AppLogger;
import com.milkbasket.core.framework.logging.manager.LoggingManager;
import com.milkbasket.core.framework.test.WebTestConfiguration;
import com.milkbasket.core.framework.utility.JSONUtils;
import com.milkbasket.rest.services.cache.caching.CustomCacheDTO;

import io.restassured.response.Response;

/**
 * The Class CommunicationsHandlerTest.
 */
public class CacheHandlerTest extends WebTestConfiguration {

	@Autowired
	private EntityCacheService cacheReportService;

	private static final AppLogger _LOGGER = LoggingManager.getLogger(CacheHandlerTest.class);

	/** The initial count. */
	private int initialCount = 0;

	/** The new id. */
	private Long newId = -1l;

	Map<String, String> headers = new HashMap<>();

	@Test(priority = 10)
	public void test_reloadAll() {
		final Map<String, String> params = new HashMap<>();
		// params.put("tableName", "product_catalog");
		// get("/cache/reload-entity", headers, params,
		// null).then().statusCode(HttpStatus.OK.value()).extract().response();
		// params.put("tableName", "product_hub");
		get("/cache/reload-entity", headers, params, null).then().statusCode(HttpStatus.OK.value()).extract().response();

		// assertTrue(responseObj != null);
		// initialCount = responseObj.size();
	}

	@Test(priority = 100)
	public void test_Cache() {
		cacheReportService.reloadAllEntities("brands");
	}

	@Test(priority = 20)
	public void test_reload() {
		final Map<String, String> params = new HashMap<>();
		params.put("tableName", "product_catalog");
		get("/cache/reload-entity", headers, params, null).then().statusCode(HttpStatus.OK.value()).extract().response();
		params.put("tableName", "product_hub");
		get("/cache/reload-entity", headers, params, null).then().statusCode(HttpStatus.OK.value()).extract().response();

		// assertTrue(responseObj != null);
		// initialCount = responseObj.size();
	}

	@Test(priority = 30)
	public void test_saveProfile() {

		final ProfileTestBean profile = new ProfileTestBean();

		profile.setId(13l);
		profile.setName("Sanjeev 11");
		profile.setHubId(1l);
		profile.setMobile("9560834443");
		profile.setCountryCode("91");
		profile.setPhone("9560824443");
		profile.setEmail("sanjeev.jha@milkbasket.com");
		profile.setCreatedOn("2020-01-31 10:10:10");
		profile.setAddress("Gurgaion ABCD ..");
		profile.setSocietyId(4l);
		profile.setSocietyName("Test Society");
		profile.setSocietyActive(1);
		profile.setThrottleStatus(1);
		profile.setInactivityFlag(0);
		profile.setDeliveryStartDate("2018-01-31");
		profile.setCity("Gurgaion");
		profile.setCityId(1l);
		profile.setPaid(1);
		profile.setUpi(1);
		profile.setIsSMS(1);
		profile.setIsEmail(1);
		profile.setIsPush(1);
		profile.setIsValidAddress(1);
		profile.setIsDuplicate(0);
		profile.setFirstPaymentDate("2018-01-31 10:10:10");
		profile.setFirstOrderDate("2018-01-31 10:10:10");
		profile.setLastOrderDate("2020-02-29 10:10:10");
		profile.setReferrerCode("ABCD11");
		profile.setReferralExpiryDate("2020-01-31 10:10:10");
		profile.setCreditLimit(100);
		profile.setCimsUrl("/test/cims");
		profile.setIsMbeyond(true);
		profile.setFirstName("Sanjeev");
		profile.setLastName("Sanjeev");
		profile.setReferredByCode("Sanjeev");
		profile.setInviteCode("Sanjeev");
		profile.setSocietyReferralActive(false);

		final CustomCacheDTO dto = new CustomCacheDTO();
		dto.setGroupName("profile");
		dto.setEntity(profile);
		dto.setKeyValues(new Object[] { 1l, 11l });

		// params.put("tableName", "product_hub");

		// post("/cache/entity", dto, headers, null, null);
		// get("/cache/reload-entity", headers, params,
		// null).then().statusCode(HttpStatus.OK.value()).extract().response();
		// assertTrue(responseObj != null);
		// initialCount = responseObj.size();

		cacheUserProfile(profile);

	}

	private void cacheUserProfile1(ProfileTestBean userProfile) {
		final EntityCacheService<?> cacheReportService = ContextUtils.getBean(EntityCacheService.class);
		cacheReportService.saveObject("profile", userProfile, false, userProfile.getHubId(), userProfile.getId());
		final Map<String, Object> object = cacheReportService.getEntityByKey("profile", userProfile.getHubId(), userProfile.getId());
		System.out.println("Object =" + JSONUtils.objectToJson(object));

		// cacheReportService.saveObject(payload.getGroupName(), payload.getEntity(),
		// noExpiry, payload.getKeyValues());
	}

	private void cacheUserProfile(ProfileTestBean userProfile) {
		final CacheRepository<Object> repo = ContextUtils.getBean(CacheRepository.class);
		final String key = Keys.newSharedKey(getKey(userProfile.getHubId(), userProfile.getId()), "profile");
		repo.save(key, userProfile);
		final EntityCacheService<?> cacheReportService = ContextUtils.getBean(EntityCacheService.class);
		final Map<String, Object> object = cacheReportService.getEntityByKey("profile", userProfile.getHubId(), userProfile.getId());
		System.out.println("Object =" + JSONUtils.objectToJson(object));

	}

	private String getKey(Long hubId, Long customerId) {
		final StringBuilder key = new StringBuilder();
		key.append(hubId);
		key.append(".");
		key.append(customerId);
		return key.toString();
	}

	@Test(priority = 2)
	public void test_find_profile() {
		final Map<String, String> params = new HashMap<>();
		params.put("tableName", "profile");
		params.put("ck", "1,13");
		final Response response = get("/cache/entity", headers, params, null).then().statusCode(HttpStatus.OK.value()).extract().response();
		final Map<?, ?> responseObj = asObject(response.body().asString(), Map.class);
		System.out.println(JSONUtils.objectToJson(responseObj));
		assertTrue(responseObj != null);
		// initialCount = responseObj.size();
	}

	/**
	 * Test template find all.
	 */
	@Test(priority = 5)
	public void test_findOnCK() {
		final Map<String, String> params = new HashMap<>();
		params.put("tableName", "product_hub");
		params.put("ck", "1,110");
		final Response response = get("/cache/entity", headers, params, null).then().statusCode(HttpStatus.OK.value()).extract().response();
		final Map<?, ?> responseObj = asObject(response.body().asString(), Map.class);
		System.out.println(JSONUtils.objectToJson(responseObj));
		assertTrue(responseObj != null);
		// initialCount = responseObj.size();
	}

	@Test(priority = 110)
	public void test_Clear_profileCache() {
		final Map<String, String> params = new HashMap<>();
		// params.put("customerId", "5");
		final Response response = get("/cache/remove/profile", headers, params, null).then().statusCode(HttpStatus.OK.value()).extract().response();
		assertNotNull(response);

	}

	@Test(priority = 115)
	public void test_Clear_vendorHolidayCache() {
		final Map<String, String> params = new HashMap<>();
		// params.put("hubId", "1");
		// params.put("vendorId", "39");
		// params.put("date", "2020-07-01");
		final Response response = get("/cache/remove/vendor-holiday", headers, params, null).then().statusCode(HttpStatus.OK.value()).extract()
				.response();
		assertNotNull(response);

	}

	@Test(priority = 120)
	public void test_Clear_deliverHolidayCache() {
		final Map<String, String> params = new HashMap<>();
		// params.put("hubId", "1");
		// params.put("date", "2020-07-01");
		final Response response = get("/cache/remove/delivery-holiday", headers, params, null).then().statusCode(HttpStatus.OK.value()).extract()
				.response();
		assertNotNull(response);

	}

	/**
	 * Test clean up.
	 *
	 * @throws ServerException
	 *             the server exception
	 */
	@Test(priority = 190)
	public void test_CleanUp() {

	}

}
