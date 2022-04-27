package com.milkbasket.rest.services.common.communication.test.handler;

import static org.testng.Assert.assertNotNull;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.milkbasket.core.framework.common.utils.ContextUtils;
import com.milkbasket.core.framework.logging.core.AppLogger;
import com.milkbasket.core.framework.logging.manager.LoggingManager;
import com.milkbasket.core.framework.test.WebTestConfiguration;
import com.milkbasket.core.framework.utility.JSONUtils;
import com.milkbasket.rest.services.common.communication.push.bean.PushRequestBean;
import com.milkbasket.rest.services.common.communication.push.entity.PushCustomerEntity;
import com.milkbasket.rest.services.common.communication.push.entity.PushDeviceEntity;
import com.milkbasket.rest.services.common.communication.push.entity.PushDeviceResultEntity;
import com.milkbasket.rest.services.common.communication.push.entity.PushEntity;
import com.milkbasket.rest.services.common.communication.push.repository.PushCustomerMongoDBRepositoryImpl;
import com.milkbasket.rest.services.common.communication.push.repository.PushDeviceMongoDBRepositoryImpl;
import com.milkbasket.rest.services.common.communication.push.repository.PushDeviceResultMongoDBRepositoryImpl;
import com.milkbasket.rest.services.common.communication.push.repository.PushMongoDBRepositoryImpl;
import com.milkbasket.rest.services.common.communication.push.service.PushService;
import com.milkbasket.rest.services.common.communication.test.data.PushTestData;

/**
 * The Class PushHandlerTest.
 */
public class PushHandlerTest extends WebTestConfiguration {

	private static final AppLogger _LOGGER = LoggingManager.getLogger(PushHandlerTest.class);

	/** The initial count. */
	// private int initialCount = 0;

	/** The new id. */
	private Long newId = -1L;

	/** The bean. */
	private PushRequestBean bean;

	/** The Push service. */
	@SuppressWarnings("unused")
	@Autowired
	private PushService pushService;

	private Set<Long> idSet = new HashSet<>();

	Map<String, String> headers = new HashMap<>();

	/**
	 * Test Push save.
	 */
	@Test(priority = 30)
	public void test_send_request() {
		bean = PushTestData.newBean(0);
		newId = doSave("/communication/push", headers, bean);
		idSet.add(newId);
		assertNotNull(newId);

	}

	/*
	 * // @Test(priority = 100) public void test_createTable() { try {
	 *
	 * final DynamoDbRepositoryImpl dynamoDbRepository =
	 * ContextUtils.getBean(DynamoDbRepositoryImpl.class); final Method method =
	 * dynamoDbRepository.getClass().getDeclaredMethod("getClient");
	 * method.setAccessible(true); final AmazonDynamoDB dbClient = (AmazonDynamoDB)
	 * method.invoke(dynamoDbRepository); final ProvisionedThroughput
	 * provisionedThroughput = new ProvisionedThroughput(1L, 1L);
	 *
	 * try { // push final DynamoDBMapper push =
	 * dynamoDbRepository.getMapper(PushEntity.TABLE_NAME); final CreateTableRequest
	 * pushTable = push.generateCreateTableRequest(PushEntity.class);
	 * pushTable.setProvisionedThroughput(provisionedThroughput);
	 * dbClient.createTable(pushTable); } catch (final Exception e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); }
	 *
	 * try { // push-device final DynamoDBMapper pushDevice =
	 * dynamoDbRepository.getMapper(PushDeviceEntity.TABLE_NAME); final
	 * CreateTableRequest pushDeviceTable =
	 * pushDevice.generateCreateTableRequest(PushDeviceEntity.class);
	 * pushDeviceTable.setProvisionedThroughput(provisionedThroughput);
	 * dbClient.createTable(pushDeviceTable); } catch (final Exception e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); }
	 *
	 * try { // push-device-result final DynamoDBMapper pushDeviceRes =
	 * dynamoDbRepository.getMapper(PushDeviceResultEntity.TABLE_NAME); final
	 * CreateTableRequest pushDeviceResTable =
	 * pushDeviceRes.generateCreateTableRequest(PushDeviceResultEntity.class);
	 * pushDeviceResTable.setProvisionedThroughput(provisionedThroughput);
	 * pushDeviceResTable.getGlobalSecondaryIndexes().forEach(v -> {
	 * v.setProvisionedThroughput(provisionedThroughput); v.setProjection(new
	 * Projection().withProjectionType(ProjectionType.ALL)); });
	 *
	 * dbClient.createTable(pushDeviceResTable); } catch (final Exception e) { //
	 * TODO Auto-generated catch block e.printStackTrace(); }
	 *
	 * try { // push-customer final DynamoDBMapper pushCustomer =
	 * dynamoDbRepository.getMapper(PushCustomerEntity.TABLE_NAME); final
	 * CreateTableRequest pushCustomerTable =
	 * pushCustomer.generateCreateTableRequest(PushCustomerEntity.class);
	 * pushCustomerTable.setProvisionedThroughput(provisionedThroughput);
	 * pushCustomerTable.getGlobalSecondaryIndexes().forEach(v -> {
	 * v.setProvisionedThroughput(provisionedThroughput); v.setProjection(new
	 * Projection().withProjectionType(ProjectionType.ALL)); });
	 * dbClient.createTable(pushCustomerTable); } catch (final Exception e) { //
	 * TODO Auto-generated catch block e.printStackTrace(); }
	 *
	 * // final String json = //
	 * "{\"id\":9292195047410,\"pushId\":9291384201530,\"customerId\":11,\"deviceId\":1,\"processed\":0,\"processedTime\":null,\"push\":0}";
	 * // dynamoDbRepository.save(PushCustomerEntity.TABLE_NAME, //
	 * JSONUtils.jsonToObject(json, PushCustomerEntity.class));
	 *
	 * } catch (final Exception e) { e.printStackTrace(); } }
	 */

	@Test(priority = 40)
	public void test_insert_mongo_push() {
		try {
			final PushEntity pushEntity = PushEntity.newInstance();
			pushEntity.setLink("Sample Link");
			pushEntity.setMessage("Test Message");
			pushEntity.setMessageLang("Test");
			pushEntity.setMessage("{}");
			final PushMongoDBRepositoryImpl pushRepo = ContextUtils.getBean(PushMongoDBRepositoryImpl.class);
			pushRepo.save(pushEntity);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 41)
	public void test_insert_mongo_push_device() {
		try {
			final PushDeviceEntity pushEntity = PushDeviceEntity.newInstance();
			pushEntity.setPushId(1L);
			pushEntity.setMulticastId(1L);
			pushEntity.setTotal(1);
			pushEntity.setSuccess(1);
			pushEntity.setFailure(0);
			pushEntity.setData("Test");
			final PushDeviceMongoDBRepositoryImpl pushRepo = ContextUtils.getBean(PushDeviceMongoDBRepositoryImpl.class);
			pushRepo.save(pushEntity);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 42)
	public void test_insert_mongo_push_device_result() {
		try {
			final PushDeviceResultEntity pushEntity = PushDeviceResultEntity.newInstance();
			pushEntity.setPushDeviceId(1L);
			pushEntity.setCustomerId(1L);
			pushEntity.setPushCustomerId(1L);
			pushEntity.setMessageId("Test");
			pushEntity.setError("NONE");
			final PushDeviceResultMongoDBRepositoryImpl pushRepo = ContextUtils.getBean(PushDeviceResultMongoDBRepositoryImpl.class);
			pushRepo.save(pushEntity);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 43)
	public void test_insert_mongo_push_customer() {
		try {
			final PushCustomerEntity pushEntity = PushCustomerEntity.newInstance();
			pushEntity.setPushId(100L);
			pushEntity.setCustomerId(2L);
			pushEntity.setDeviceId(2L);
			pushEntity.setProcessed(1);
			pushEntity.setPush(0);
			pushEntity.setCreatedOn(new Date());
			pushEntity.setProcessedTime(new Date());
			final PushCustomerMongoDBRepositoryImpl pushRepo = ContextUtils.getBean(PushCustomerMongoDBRepositoryImpl.class);
			pushRepo.save(pushEntity);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 45)
	public void test_findOnPushId() {
		final PushCustomerMongoDBRepositoryImpl pushRepo = ContextUtils.getBean(PushCustomerMongoDBRepositoryImpl.class);
		final List<PushCustomerEntity> list = pushRepo.findByPushId(100L);
		_LOGGER.info(JSONUtils.objectToJson(list));
	}
}
