package com.milkbasket.rest.services.common.communication.template.test.handler;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

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
import com.milkbasket.core.framework.dbsupport.jdbc.constants.Flag;
import com.milkbasket.core.framework.logging.core.AppLogger;
import com.milkbasket.core.framework.logging.manager.LoggingManager;
import com.milkbasket.core.framework.test.WebTestConfiguration;
import com.milkbasket.rest.services.common.communication.template.bean.TemplateBean;
import com.milkbasket.rest.services.common.communication.template.entity.TemplateConstants;
import com.milkbasket.rest.services.common.communication.template.service.TemplateService;

import io.restassured.response.Response;

/**
 * The Class CommunicationsHandlerTest.
 */
public class TemplateHandlerTest extends WebTestConfiguration {

	private static final AppLogger _LOGGER = LoggingManager.getLogger(TemplateHandlerTest.class);

	/** The initial count. */
	private int initialCount = 0;

	/** The new id. */
	private Long newId = -1l;

	/** The bean. */
	private TemplateBean bean;

	private Set<Long> createdIds = new HashSet<>();

	@Autowired
	private TemplateService templateService;

	Map<String, String> headers = new HashMap<>();

	@Test(priority = 2)
	public void test_DataValidation() {
		final TemplateBean bean = TemplateBean.newInstance();
		post("/message-templates", bean, headers, null, null).then().statusCode(HttpStatus.BAD_REQUEST.value()).extract().response();

		bean.setName(RandomStringUtils.randomAlphanumeric(99) + "@");
		bean.setType("PUSH");
		bean.setText("Hi, 2nd message");
		bean.setActive(Flag.ACTIVE);
		bean.setManual(1);
		bean.setModule("BASKET");

		post("/message-templates", bean, headers, null, null).then().statusCode(HttpStatus.BAD_REQUEST.value()).extract().response();

		bean.setName(RandomStringUtils.randomAlphanumeric(101));

		post("/message-templates", bean, headers, null, null).then().statusCode(HttpStatus.BAD_REQUEST.value()).extract().response();

		bean.setName(RandomStringUtils.randomAlphanumeric(100));
		bean.setType(RandomStringUtils.randomAlphanumeric(51));
		post("/message-templates", bean, headers, null, null).then().statusCode(HttpStatus.BAD_REQUEST.value()).extract().response();

		bean.setType("ABCD");
		post("/message-templates", bean, headers, null, null).then().statusCode(HttpStatus.BAD_REQUEST.value()).extract().response();

		bean.setType("EMAIL");
		bean.setModule("ABCD");
		post("/message-templates", bean, headers, null, null).then().statusCode(HttpStatus.BAD_REQUEST.value()).extract().response();

		bean.setModule("BASKET");
		bean.setSubject(null);
		post("/message-templates", bean, headers, null, null).then().statusCode(HttpStatus.BAD_REQUEST.value()).extract().response();
		bean.setSubject(RandomStringUtils.randomAlphabetic(513));
		post("/message-templates", bean, headers, null, null).then().statusCode(HttpStatus.BAD_REQUEST.value()).extract().response();
		bean.setSubject(RandomStringUtils.randomAlphabetic(512));
		bean.setText(RandomStringUtils.randomAlphabetic(60001));
		post("/message-templates", bean, headers, null, null).then().statusCode(HttpStatus.BAD_REQUEST.value()).extract().response();
		bean.setText(RandomStringUtils.randomAlphabetic(60000));
		bean.setManual(2);
		post("/message-templates", bean, headers, null, null).then().statusCode(HttpStatus.BAD_REQUEST.value()).extract().response();
		bean.setManual(1);
		bean.setActive(2);
		post("/message-templates", bean, headers, null, null).then().statusCode(HttpStatus.BAD_REQUEST.value()).extract().response();

	}

	/**
	 * Test template find all.
	 */
	@Test(priority = 5)
	public void test_templateFindAll() {
		final Response response = get("/message-templates", headers, null, null).then().statusCode(HttpStatus.OK.value()).extract().response();
		final List<?> responseObj = asObject(response.body().asString(), List.class);
		initialCount = responseObj.size();
	}

	/**
	 * Test template save.
	 */
	@Test(priority = 10)
	public void test_templateSave() {
		bean = TemplateBean.newInstance();
		bean.setType("SMS");
		bean.setText("Hi ${NAME?default('User')}, Your Order has been delived.");
		bean.setName("TEST REPO ORDER PLACED1");
		bean.setActive(Flag.ACTIVE);
		// bean.setFlag(Flag.ACTIVE);
		bean.setManual(1);
		bean.setSendType(TemplateConstants.SendType.TRANSACTIONAL);
		bean.setModule("BASKET");
		newId = save(bean);

		createdIds.add(Long.valueOf(newId));

		bean.setType("PUSH");
		bean.setName("TEST REPO ORDER PLACED2");
		newId = save(bean);
		createdIds.add(Long.valueOf(newId));

		final Map<String, String> pathParams = new HashMap<>();
		pathParams.put("type", "PUSH");
		pathParams.put("name", "TEST REPO ORDER PLACED2");

		/// message-templates/types/{type}/names/{name}

		final Response response = get("/message-templates/types/{type}/names/{name}", headers, null, pathParams).then()
				.statusCode(HttpStatus.OK.value()).extract().response();
		final TemplateBean responseObj = asObject(response.body().asString(), TemplateBean.class);

		assertNotNull(responseObj);
		assertEquals(responseObj.getName(), bean.getName());
		assertEquals(responseObj.getType(), bean.getType());
		assertEquals(responseObj.getText(), bean.getText());

		Response validResponse = get("/message-templates/types/{type}/names/{name}/manual", headers, null, pathParams).then()
				.statusCode(HttpStatus.OK.value()).extract().response();

		assertNotNull(validResponse);
		assertTrue(asObject(validResponse.body().asString(), Boolean.class));
		pathParams.put("name", RandomStringUtils.randomAlphabetic(100));

		validResponse = get("/message-templates/types/{type}/names/{name}/manual", headers, null, pathParams).then().statusCode(HttpStatus.OK.value())
				.extract().response();

		assertNotNull(validResponse);
		assertFalse(asObject(validResponse.body().asString(), Boolean.class));

	}

	private Long save(TemplateBean bean) {
		final Response response = post("/message-templates", bean, headers, null, null).then().statusCode(HttpStatus.CREATED.value()).extract()
				.response();

		final Long responseObj = asObject(response.body().asString(), Long.class);
		// assertNotNull(newId);

		createdIds.add(responseObj);

		assertNotNull(responseObj);

		return responseObj;
	}

	/**
	 * Test template find all with 1 parameter.
	 */
	@Test(priority = 15)
	public void test_templateFindAll_with_1_parameter() {
		// Map<String, String> requestParam = new HashMap<>();
		// requestParam.put("type", "SMS");
		//
		// Response response = get("/message-templates", headers, requestParam,
		// null).then()
		// .statusCode(HttpStatus.OK.value()).extract().response();
		// List<?> responseObj = asObject(response.body().asString(),
		// List.class);
		// assertNotNull(responseObj);
	}

	/**
	 * Test template update.
	 */
	@Test(priority = 20)
	public void test_templateUpdate() {
		final TemplateBean bean = TemplateBean.newInstance();

		bean.setType("SMS");
		bean.setText("Hi ${NAME?default('User')}, Your Order has been delived on ${DATE?default('_')}.");
		bean.setName("TEST REPO ORDER PLACED3");
		bean.setActive(Flag.ACTIVE);
		// bean.setFlag(Flag.ACTIVE);
		bean.setManual(1);
		bean.setModule("BASKET");
		bean.setSendType(TemplateConstants.SendType.TRANSACTIONAL);

		final Map<String, String> pathParams = new HashMap<>();
		pathParams.put("id", String.valueOf(newId));

		put("/message-templates/{id}", bean, headers, null, pathParams).then().statusCode(HttpStatus.NO_CONTENT.value()).extract().response();

		final Response response = get("/message-templates/{id}", headers, null, pathParams).then().statusCode(HttpStatus.OK.value()).extract()
				.response();
		final TemplateBean responseObj = asObject(response.body().asString(), TemplateBean.class);

		assertNotNull(newId);
		assertEquals(responseObj.getName(), bean.getName());
		assertEquals(responseObj.getType(), bean.getType());
		assertEquals(responseObj.getText(), bean.getText());

		_LOGGER.info("Text ==>" + responseObj.getText());
		_LOGGER.info("Params ==>" + responseObj.getParams());

	}

	/**
	 * Test template find one.
	 */
	@Test(priority = 30)
	public void test_templateFindOne() {

		assertNotEquals(newId, -1);

		final Map<String, String> pathParams = new HashMap<>();
		pathParams.put("id", String.valueOf(newId));
		final Response response = get("/message-templates/{id}", headers, null, pathParams).then().statusCode(HttpStatus.OK.value()).extract()
				.response();
		final TemplateBean responseObj = asObject(response.body().asString(), TemplateBean.class);

		assertEquals(responseObj.getId().intValue(), newId.intValue());

		_LOGGER.info("Text ==>" + bean.getText());
		_LOGGER.info("Params ==>" + bean.getParams());
	}

	/**
	 * Test template find all again.
	 */
	@Test(priority = 40)
	public void test_templateFindAllAgain() {
		final Response response = get("/message-templates", headers, null, null).then().statusCode(HttpStatus.OK.value()).extract().response();
		final List<?> responseObj = asObject(response.body().asString(), List.class);
		// final int newCount = responseObj.size();
		// assertEquals(newCount - 1, initialCount);
		assertNotNull(responseObj);
	}

	/**
	 * Test template delete.
	 */
	@Test(priority = 50)
	public void test_templateDelete() {

		final Map<String, String> pathParams = new HashMap<>();
		pathParams.put("id", String.valueOf(newId));

		final Response response = delete("/message-templates/{id}", headers, null, pathParams).then().statusCode(HttpStatus.OK.value()).extract()
				.response();
		final Long responseObj = asObject(response.body().asString(), Long.class);

		assertEquals(responseObj.intValue(), newId.intValue());

	}

	/**
	 * Test template delete flag.
	 */
	@Test(priority = 60)
	public void test_templateDeleteFlag() {
		final Map<String, String> pathParams = new HashMap<>();
		pathParams.put("id", String.valueOf(newId));
		get("/message-templates/{id}", headers, null, pathParams).then().statusCode(HttpStatus.NOT_FOUND.value()).extract().response();
	}

	/**
	 * Test autocomplete.
	 */
	@Test(priority = 140)
	public void test_autocomplete() {
		final Map<String, String> params = new HashMap<>();
		params.put("text", bean.getName().substring(0, 3));

		final Response response = get("/message-templates/autocomplete", headers, params, null).then().statusCode(HttpStatus.OK.value()).extract()
				.response();
		final List<?> autoResult = asObject(response.body().asString(), List.class);
		assertNotNull(autoResult);
		// assertEquals(autoResult.size(), 2);
	}

	/**
	 * Test city delete.
	 */
	@Test(priority = 160)
	public void test_Delete() {
		// delete(newId);
	}

	/**
	 * Test city delete flag.
	 */
	@Test(priority = 170)
	public void test_DeleteFlag() {
		final Map<String, String> pathParams = new HashMap<>();
		pathParams.put("id", String.valueOf(newId));
		get("/message-templates/{id}", headers, null, pathParams).then().statusCode(HttpStatus.NOT_FOUND.value()).extract().response();
	}

	/**
	 * Test findwith deleted.
	 */
	@Test(priority = 180)
	public void test_findwithDeleted() {
		final Map<String, String> filter = new HashMap<>();
		filter.put(TemplateConstants.NAME, bean.getName() + " UPDATED");

		final Response response = get("/message-templates/all", headers, filter, null).then().statusCode(HttpStatus.OK.value()).extract().response();
		asObject(response.body().asString(), List.class);
	}

	/**
	 * Test clean up.
	 *
	 * @throws ServerException
	 *             the server exception
	 */
	@Test(priority = 190)
	public void test_CleanUp() {
		templateService.deleteById(Long.valueOf(newId));
		for (final Long long1 : createdIds) {
			try {
				templateService.deleteById(long1);
			} catch (final Exception e) {
			}
		}
	}

}
