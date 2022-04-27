package com.milkbasket.rest.services.common.test.handler;

import static org.testng.Assert.assertNotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Test;

import com.milkbasket.core.framework.common.exception.ServerException;
import com.milkbasket.core.framework.test.WebTestConfiguration;
import com.milkbasket.rest.services.push.common.bean.DeviceBean;
import com.milkbasket.rest.services.push.common.bean.DeviceRequestBean;
import com.milkbasket.rest.services.push.common.bean.UnregisterDevice;
import com.milkbasket.rest.services.communication.entity.DeviceConstants.Device;
import com.milkbasket.rest.services.push.common.service.DeviceService;

import io.restassured.response.Response;

/**
 * The Class DeviceHandlerTest.
 */
public class DeviceHandlerTest extends WebTestConfiguration {

	/** The initial count. */
	private int initialCount = 0;

	/** The new id. */
	private Long newId = -1l;

	/** The bean. */
	private DeviceBean bean;

	/** The Device service. */
	@Autowired
	private DeviceService deviceService;

	private Long customerId = 11l;

	private Set<Long> idSet = new HashSet<>();

	Map<String, String> headers = new HashMap<>();

	@Test(priority = 10)
	public void test_find_all() {
		DeviceRequestBean bean = DeviceRequestBean.newInstance();
		bean.setCustomerId(Arrays.asList(Long.valueOf(1)));
		bean.setDeviceLogout(0);
		Response response = post("/devices/find", bean, headers, null, null).then().statusCode(HttpStatus.OK.value()).extract().response();
		List<?> responseObj = asObject(response.body().asString(), List.class);
		initialCount = responseObj.size();
	}

	// @Test(priority = 20)
	// public void test_pre_validate_Save() {
	// Collection<DeviceBean> invalidData = DeviceTestData.getInvalidBeans(0,
	// customerId).values();
	// for (DeviceBean bean : invalidData) {
	// // post("/devices", bean, headers, null,
	// //
	// null).then().statusCode(HttpStatus.BAD_REQUEST.value()).extract().response();
	// doBadPost("/devices", headers, bean);
	// }
	// }

	/**
	 * Test Device save.
	 */
	// @Test(priority = 30)
	// public void test_save() {
	// bean = DeviceTestData.newBean(0, customerId);
	// newId = doSave("/devices", headers, bean);
	// idSet.add(newId);
	// assertNotNull(newId);
	//
	// Map<String, String> pathParams = new HashMap<>();
	// pathParams.put("id", String.valueOf(newId));
	//
	// DeviceBean findOneBean = doFind("/devices/{id}", headers, newId,
	// DeviceBean.newInstance());
	// assertNotNull(findOneBean.getId());
	//
	// }

	/**
	 * Test Device update.
	 */
	// @Test(priority = 50)
	// public void test_DeviceUpdate() {
	//
	// DeviceBean beanToUpdate = DeviceTestData.newBean(0, customerId);
	//
	// doPut("/devices/{id}", headers, newId, beanToUpdate);
	//
	// DeviceBean findOneBean = doFind("/devices/{id}", headers, newId,
	// DeviceBean.newInstance());
	//
	// assertEquals(findOneBean.getId(), newId);
	//
	// }

	DeviceBean registerBean = new DeviceBean();
	Long registeredDeviceId = -1L;

	@Test(priority = 51)
	public void test_register() {

		registerBean.setCustomerId(11L);
		registerBean.setDeviceLogout(0);
		registerBean.setDeviceOs(Device.AND);
		registerBean.setPushId(RandomStringUtils.randomAlphabetic(10));
		registerBean.setUdid(RandomStringUtils.randomAlphabetic(15));

		String savedDeviceStr = post("/devices/register", registerBean, headers, null, null).then().statusCode(HttpStatus.OK.value()).extract()
				.asString();
		assertNotNull(savedDeviceStr);
		registeredDeviceId = Long.valueOf(savedDeviceStr);

		post("/devices/register", registerBean, headers, null, null).then().statusCode(HttpStatus.OK.value());
	}

	@Test(priority = 52)
	public void test_unregister() {

		UnregisterDevice device = new UnregisterDevice();
		device.setCustomerId(registerBean.getCustomerId());
		device.setUdid(registerBean.getUdid());
		post("/devices/unregister", device, headers, null, null).then().statusCode(HttpStatus.OK.value());

		deviceService.deleteById(registeredDeviceId);
	}

	/**
	 * Test Device find one.
	 */
	// @Test(priority = 70)
	// public void test_DeviceFindOne() {
	//
	// assertNotEquals(newId, -1);
	// Map<String, String> pathParams = new HashMap<>();
	// pathParams.put("id", String.valueOf(newId));
	//
	// Response response = get("/devices/{id}", headers, null,
	// pathParams).then().statusCode(HttpStatus.OK.value()).extract().response();
	// DeviceBean responseObj = asObject(response.body().asString(),
	// DeviceBean.class);
	//
	// assertEquals(responseObj.getId().intValue(), newId.intValue());
	// }

	/**
	 * Test Device find all again.
	 */
	@Test(priority = 80)
	public void test_DeviceFindAllAgain() {
		DeviceRequestBean bean = DeviceRequestBean.newInstance();
		bean.setCustomerId(Arrays.asList(Long.valueOf(1)));
		bean.setDeviceLogout(0);

		Response response = post("/devices/find", bean, headers, null, null).then().statusCode(HttpStatus.OK.value()).extract().response();
		List<?> responseObj = asObject(response.body().asString(), List.class);
		int newCount = responseObj.size();
		// assertEquals(newCount - 1, initialCount);
	}

	@Test(priority = 100)
	public void test_findwithDeleted() {
		Map<String, String> filter = new HashMap<>();
		// filter.put("firstName", bean.getFirstName());

		Response response = get("/devices/all", headers, filter, null).then().statusCode(HttpStatus.OK.value()).extract().response();
		List<?> autoResult = asObject(response.body().asString(), List.class);
		assertNotNull(autoResult);
		// assertEquals(((LinkedHashMap<?, ?>) autoResult.get(0)).get("flag"), 0);
		// assertEquals(autoResult.size(), 1);
	}

	/**
	 * Test Device delete.
	 */
	// @Test(priority = 190)
	// public void test_DeviceDelete() {
	// Map<String, String> pathParams = new HashMap<>();
	// pathParams.put("id", String.valueOf(newId));
	// Response response = delete("/devices/{id}", headers, null,
	// pathParams).then().statusCode(HttpStatus.OK.value()).extract().response();
	// Long responseObj = asObject(response.body().asString(), Long.class);
	// assertEquals(responseObj.intValue(), newId.intValue());
	// }

	/**
	 * Test Device delete flag.
	 */
	@Test(priority = 200)
	public void test_DeviceDeleteFlag() {
		Map<String, String> pathParams = new HashMap<>();
		pathParams.put("id", String.valueOf(newId));
		get("/device/{id}", headers, null, pathParams).then().statusCode(HttpStatus.NOT_FOUND.value()).extract().response();
	}

	@Test(priority = 490)
	public void test_CleanUp() throws ServerException {
		for (Long entityId : idSet) {
			try {
				deviceService.deleteById(entityId);
			} catch (Exception e) {
			}
		}

	}

}
