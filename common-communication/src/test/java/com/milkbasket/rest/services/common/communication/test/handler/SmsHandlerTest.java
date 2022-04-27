package com.milkbasket.rest.services.common.communication.test.handler;

import static org.testng.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.milkbasket.core.framework.common.utils.ContextUtils;
import com.milkbasket.core.framework.test.WebTestConfiguration;
import com.milkbasket.core.framework.utility.DateUtils;
import com.milkbasket.rest.services.common.communication.sms.bean.SmsRequestBean;
import com.milkbasket.rest.services.common.communication.sms.entity.SmsEntity;
import com.milkbasket.rest.services.common.communication.sms.repository.SmsMongoDBRepositoryImpl;

import io.restassured.response.Response;

/**
 * The Class SmsHandlerTest.
 */
public class SmsHandlerTest extends WebTestConfiguration {

	private static final String SMS_REGISTER_OTP = "welcome_otp";
	/** Constant <code>SMS_LOGIN_OTP</code> */
	private static final String SMS_LOGIN_OTP = "login_otp";

	/** The new id. */
	private Long newId = -1L;

	/** List of numbers. */
	private List<String> numbers = new ArrayList<>();

	/** List of numbers. */
	private List<String> instantNumbers = new ArrayList<>();

	/** List of context Data (if any). */
	private Map<String, Object> contextData = new HashMap<>();

	/** The bean. */
	private SmsRequestBean bean;

	private String templateName = "SMS_TEST_DEFAULT_TEMPLATE";

	Map<String, String> headers = new HashMap<>();

	@BeforeTest
	public void test_prepareData() {
		numbers.add("9873271404");
		numbers.add("9873271404");

		final String startNuber = "9873271";

		for (int i = 0; i < 498; i++) {
			final String num = String.valueOf(i);
			if (num.length() == 1) {
				numbers.add(startNuber.concat("00").concat(num));
			} else if (num.length() == 2) {
				numbers.add(startNuber.concat("0").concat(num));
			} else {
				numbers.add(startNuber.concat(num));
			}
		}

		instantNumbers.add("9873271404");
		instantNumbers.add("9873271404");

		contextData.put("name", "Nitin");
		contextData.put("status", "pass");
	}

	/**
	 * Test sms find all.
	 */
	@Test(priority = 1)
	public void test_smsFindAll() {
	}

	/*
	 * // @Test(priority = 100) public void test_createTable() { try { final
	 * DynamoDbRepositoryImpl dynamoDbRepository =
	 * ContextUtils.getBean(DynamoDbRepositoryImpl.class); final Method method =
	 * dynamoDbRepository.getClass().getDeclaredMethod("getClient");
	 * method.setAccessible(true); final AmazonDynamoDB dbClient = (AmazonDynamoDB)
	 * method.invoke(dynamoDbRepository); final DynamoDBMapper dbMapper =
	 * dynamoDbRepository.getMapper(SmsEntity.TABLE_NAME); final
	 * ProvisionedThroughput provisionedThroughput = new ProvisionedThroughput(1L,
	 * 1L); final CreateTableRequest email =
	 * dbMapper.generateCreateTableRequest(SmsEntity.class);
	 * email.setProvisionedThroughput(provisionedThroughput);
	 * dbClient.createTable(email); } catch (final Exception e) {
	 * e.printStackTrace(); } }
	 */

	/**
	 * Test sms save.
	 */
	@Test(priority = 10)
	public void test_smsRequestSave() {
		bean = SmsRequestBean.newInstance();

		bean.setTemplateName(templateName);
		bean.setContextData(contextData);
		bean.setNumbers(numbers);
		// bean.setType(SmsConstants.Type.TRANSACTIONAL);
		// bean.setPriority(1);

		final Response response = post("/communication/sms", bean, headers, null, null).then().statusCode(HttpStatus.CREATED.value()).extract()
				.response();
		newId = asObject(response.body().asString(), Long.class);

		assertNotNull(newId);
	}

	@Test(priority = 11)
	public void test_OtpSms_Register() {
		final String otp = "1234";
		final String appHash = null;
		final String mobile = "9560814443";
		final SmsRequestBean sms = SmsRequestBean.newInstance();
		final Map<String, Object> context = new HashMap<>();
		context.put("otp", otp);
		context.put("appHash", appHash != null ? appHash : "");
		sms.setContextData(context);
		sms.setNumbers(Arrays.asList(mobile));
		sms.setTemplateName(SMS_REGISTER_OTP);
		sms.setIsOtp(true);

		final Response response = post("/communication/sms", sms, headers, null, null).then().statusCode(HttpStatus.CREATED.value()).extract()
				.response();
		newId = asObject(response.body().asString(), Long.class);

		assertNotNull(newId);
	}

	@Test(priority = 12)
	public void test_OtpLogin() {
		final String otp = "1234";
		final String appHash = null;
		final String mobile = "9560814443";
		final SmsRequestBean sms = SmsRequestBean.newInstance();
		final Map<String, Object> context = new HashMap<>();
		context.put("otp", otp);
		context.put("appHash", appHash != null ? appHash : "");
		context.put("otp_expiry", DateUtils.toString("dd/MM/yyyy hh:mm:ss a", new Date()));

		sms.setContextData(context);
		sms.setNumbers(Arrays.asList(mobile));
		sms.setTemplateName(SMS_LOGIN_OTP);
		sms.setIsOtp(true);

		final Response response = post("/communication/sms", sms, headers, null, null).then().statusCode(HttpStatus.CREATED.value()).extract()
				.response();
		newId = asObject(response.body().asString(), Long.class);

		assertNotNull(newId);
	}

	@Test(priority = 13)
	public void test_other_sms() {
		final String otp = "1234";
		final String appHash = null;
		final String mobile = "9560814443";
		final SmsRequestBean sms = SmsRequestBean.newInstance();
		final Map<String, Object> context = new HashMap<>();
		context.put("otp", otp);
		context.put("appHash", appHash != null ? appHash : "");
		context.put("otp_expiry", DateUtils.toString("dd/MM/yyyy hh:mm:ss a", new Date()));
		sms.setIsOtp(false);
		sms.setContextData(context);
		sms.setNumbers(Arrays.asList(mobile));
		sms.setTemplateName(SMS_LOGIN_OTP);
		sms.setIsOtp(false);

		final Response response = post("/communication/sms", sms, headers, null, null).then().statusCode(HttpStatus.CREATED.value()).extract()
				.response();
		newId = asObject(response.body().asString(), Long.class);

		assertNotNull(newId);
	}

	@Test(priority = 18)
	public void test_insert_mongo() {
		try {

			final SmsEntity entity = SmsEntity.newInstance();
			entity.setTemplateName(templateName);
			entity.setFromNumber("1234567890");
			entity.setNumbers("9876543210");
			entity.setMessage("Sample Email Message");
			entity.setProvider("Test Provider");
			entity.setTemplateUsedOn(DateUtils.toString(new Date()));
			final SmsMongoDBRepositoryImpl repo = ContextUtils.getBean(SmsMongoDBRepositoryImpl.class);
			repo.saveSms(entity);

		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

}
