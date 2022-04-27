package com.milkbasket.rest.services.common.event.test.handler;

import static org.testng.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.testng.annotations.Test;

import com.milkbasket.core.framework.common.utils.ContextUtils;
import com.milkbasket.core.framework.dbsupport.jdbc.constants.BooleanFlag;
import com.milkbasket.core.framework.events.bean.Event;
import com.milkbasket.core.framework.events.bean.EventRetryRequestBean;
import com.milkbasket.core.framework.events.bean.EventSubscriber;
import com.milkbasket.core.framework.events.dao.EventMongoDBRepositoryImpl;
import com.milkbasket.core.framework.logging.core.AppLogger;
import com.milkbasket.core.framework.logging.manager.LoggingManager;
import com.milkbasket.core.framework.mongodb.support.bean.MongoDbProperties;
import com.milkbasket.core.framework.test.WebTestConfiguration;
import com.milkbasket.core.framework.utility.DateUtils;
import com.milkbasket.core.framework.utility.JSONUtils;

import io.restassured.response.Response;

/**
 * The Class EmailsHandlerTest.
 */
public class EventHandlerTest extends WebTestConfiguration {

	private static final AppLogger _LOGGER = LoggingManager.getLogger(EventHandlerTest.class);

	Map<String, String> headers = new HashMap<>();

	private Long idCreated = -1L;

	// @Test(priority = 1)
	/*
	 * public void test_createEventTables() { try { final DynamoDbRepositoryImpl
	 * dynamoDbRepository = ContextUtils.getBean(DynamoDbRepositoryImpl.class);
	 * final Method method =
	 * dynamoDbRepository.getClass().getDeclaredMethod("getClient");
	 * method.setAccessible(true); final AmazonDynamoDB dbClient = (AmazonDynamoDB)
	 * method.invoke(dynamoDbRepository); final ProvisionedThroughput
	 * provisionedThroughput = new ProvisionedThroughput(1L, 1L); try { final
	 * DynamoDBMapper dbMapper = dynamoDbRepository.getMapper(Event.TABLE_NAME);
	 * final CreateTableRequest event =
	 * dbMapper.generateCreateTableRequest(Event.class);
	 * event.setProvisionedThroughput(provisionedThroughput);
	 * dbClient.createTable(event); } catch (final Exception e) {
	 * e.printStackTrace(); }
	 *
	 * try { final DynamoDBMapper dbMapper1 =
	 * dynamoDbRepository.getMapper(EventSubscriber.TABLE_NAME); final
	 * CreateTableRequest eventSub =
	 * dbMapper1.generateCreateTableRequest(EventSubscriber.class);
	 * eventSub.setProvisionedThroughput(provisionedThroughput);
	 * eventSub.getGlobalSecondaryIndexes().forEach(v -> {
	 * v.setProvisionedThroughput(provisionedThroughput); v.setProjection(new
	 * Projection().withProjectionType(ProjectionType.ALL)); });
	 * dbClient.createTable(eventSub); } catch (final Exception e) {
	 * e.printStackTrace(); }
	 *
	 * } catch (final Exception e) { e.printStackTrace(); } }
	 */

	@Test(priority = 1)
	public void test_MongoConfig() {
		final MongoDbProperties props = new MongoDbProperties();
		props.setConnectionsPerHost(100);
		props.setConnectTimeout(1000);
		props.setCredential("Passw0rd");
		props.setHost("localhost");
		props.setMaxConnectionIdleTime(0);
		props.setMaxConnectionLifeTime(0);
		props.setMaxWaitTime(1500);
		props.setMinConnectionsPerHost(1);
		props.setThreadsAllowedToBlockForConnectionMultiplier(1500);
		props.setPort(27017);
		props.setUserName("mbeventuser");
		_LOGGER.info("Props = " + JSONUtils.objectToJson(props));

	}

	@Test(priority = 2)
	public void test_findEventSubscriberByIdMong() {
		final EventMongoDBRepositoryImpl eventRepo = ContextUtils.getBean(EventMongoDBRepositoryImpl.class);
		final Event event = eventRepo.findEventById("", 66612094304927744L);
		_LOGGER.info("Event = " + JSONUtils.objectToJson(event));
		final EventSubscriber evSub = eventRepo.findEventSubscriberById(1L, 66612095336071168L);
		_LOGGER.info("EventSubscriber = " + JSONUtils.objectToJson(evSub));

		final List<EventSubscriber> subs = eventRepo.findFailedEventSubscribers(new EventRetryRequestBean());
		_LOGGER.info("EventSubscriber = " + JSONUtils.objectToJson(subs));

		final List<EventSubscriber> subs1 = eventRepo.findEventSubscribers(66612094304927744L);
		_LOGGER.info("EventSubscriber = " + JSONUtils.objectToJson(subs1));

		final List<EventSubscriber> subs2 = eventRepo.findEventSubscribers(66612094304927744L, "66612094304927744");
		_LOGGER.info("EventSubscriber = " + JSONUtils.objectToJson(subs2));

		final List<EventSubscriber> subs3 = eventRepo.findEventSubscribers(0, 4, null, 2);
		_LOGGER.info("EventSubscriber = " + JSONUtils.objectToJson(subs3));

		final Date modifiedOnStart = DateUtils.getDate(DateUtils.DATE_FMT_WITH_TIME, "2019-11-01 00:00:01");
		final Date modifiedOnEnd = DateUtils.getDate(DateUtils.DATE_FMT_WITH_TIME, "2019-11-01 23:59:59");

		final List<EventSubscriber> subs4 = eventRepo.findEventSubscribers("TEST", 0, modifiedOnStart, modifiedOnEnd);
		_LOGGER.info("EventSubscriber = " + JSONUtils.objectToJson(subs4));

	}

	@Test(priority = 3)
	public void test_eventSubscriberSaveMongo() {
		final EventMongoDBRepositoryImpl eventRepo = ContextUtils.getBean(EventMongoDBRepositoryImpl.class);

		final Event event = Event.newInstance();
		event.setMessageId("TestMessageId");
		event.setRequestId("TestRequestId");
		event.setEventName("TEST");
		event.setEventData("{}");
		event.setCreatedBy(1L);
		event.setModifiedBy(1L);
		// event.setCreatedOn(new Date());
		// event.setModifiedOn(event.getCreatedOn());

		final Event createdEvent = eventRepo.createEvent(event);

		idCreated = createdEvent.getId();

		_LOGGER.info("Event ID = " + createdEvent.getId() + " Event Name = " + createdEvent.getEventName());

		final List<EventSubscriber> subscribers = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			final EventSubscriber eventSubscriber = prepareEventSubscriber(createdEvent, i);

			try {
				final EventSubscriber cratedSubscriber = eventRepo.createEventSubscriber(eventSubscriber);
				subscribers.add(cratedSubscriber);

			} catch (final Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		subscribers.stream().forEach(sub -> {
			_LOGGER.info("Event Sub ID = " + sub.getId() + " Event Name = " + sub.getEventName());

		});
	}

	@Test(priority = 4)
	public void test_forceCloseMongo() {
		final EventMongoDBRepositoryImpl eventRepo = ContextUtils.getBean(EventMongoDBRepositoryImpl.class);
		eventRepo.forceCloseEvents(30);
	}

	// @Test(priority = 5)
	/*
	 * public void test_eventSubscriberSave() { final EventDynamoDBRepositoryImpl
	 * eventRepo = ContextUtils.getBean(EventDynamoDBRepositoryImpl.class);
	 *
	 * final Event event = new Event(); event.setMessageId("TestMessageId");
	 * event.setRequestId("TestRequestId"); event.setEventName("TEST");
	 * event.setEventData("{}"); event.setCreatedBy(1L); event.setModifiedBy(1L);
	 * event.setCreatedOn(new Date()); event.setModifiedOn(event.getCreatedOn());
	 *
	 * final Event createdEvent = eventRepo.createEvent(event);
	 *
	 * idCreated = createdEvent.getId();
	 *
	 * _LOGGER.info("Event ID = " + createdEvent.getId() + " Event Name = " +
	 * createdEvent.getEventName());
	 *
	 * final List<EventSubscriber> subscribers = new ArrayList<>();
	 *
	 * for (int i = 0; i < 5; i++) { final EventSubscriber eventSubscriber =
	 * prepareEventSubscriber(createdEvent, i);
	 *
	 * try { final EventSubscriber cratedSubscriber =
	 * eventRepo.createEventSubscriber(eventSubscriber);
	 * subscribers.add(cratedSubscriber);
	 *
	 * } catch (final Exception e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } }
	 *
	 * subscribers.stream().forEach(sub -> { _LOGGER.info("Event Sub ID = " +
	 * sub.getId() + " Event Name = " + sub.getEventName());
	 *
	 * }); }
	 */

	/*
	 * // @Test(priority = 10) public void test_eventSubscriberFindFailed() { final
	 * EventDynamoDBRepositoryImpl eventRepo =
	 * ContextUtils.getBean(EventDynamoDBRepositoryImpl.class); final
	 * List<EventSubscriber> subscribers = eventRepo.findFailedEventSubscribers();
	 * subscribers.forEach(sub -> { _LOGGER.info("JSON ==>" +
	 * JSONUtils.objectToJson(sub)); }); }
	 *
	 * // @Test(priority = 15) public void test_eventSubscriberFind() { final
	 * EventDynamoDBRepositoryImpl eventRepo =
	 * ContextUtils.getBean(EventDynamoDBRepositoryImpl.class); final Date today =
	 * DateUtils.getDate(); final Date startDate = DateUtils.addDays(today, -1);
	 * final Date endDate = DateUtils.addDays(today, 1); final List<EventSubscriber>
	 * subscribers = eventRepo.findEventSubscribers("TEST", 0, startDate, endDate);
	 * subscribers.forEach(sub -> { _LOGGER.info("JSON ==>" +
	 * JSONUtils.objectToJson(sub)); }); }
	 */

	// @Test(priority = 30)
	public void test_eventSubscriber() {
		final Map<String, String> reqMarams = new HashMap<>();
		reqMarams.put("eventId", String.valueOf(idCreated));
		reqMarams.put("eventName", "TEST");
		final Response response = get("/events/subscribers", headers, reqMarams, null).then().statusCode(HttpStatus.OK.value()).extract().response();
		assertNotNull(response);
		final List<?> subscribers = asObject(response.body().asString(), List.class);
		subscribers.forEach(sub -> {
			_LOGGER.info("JSON ==>" + JSONUtils.objectToJson(sub));
		});
	}

	// @Test(priority = 40)
	public void test_eventSubscriber_1() {
		final Map<String, String> reqMarams = new HashMap<>();
		reqMarams.put("processed", "0");
		reqMarams.put("attempt", "0");
		Response response = get("/events/subscribers/processed", headers, reqMarams, null).then().statusCode(HttpStatus.OK.value()).extract()
				.response();
		assertNotNull(response);
		List<?> subscribers = asObject(response.body().asString(), List.class);
		subscribers.forEach(sub -> {
			_LOGGER.info("JSON ==>" + JSONUtils.objectToJson(sub));
		});

		reqMarams.clear();

		reqMarams.put("processed", "0");
		reqMarams.put("eventName", "TEST");
		// reqMarams.put("modifiedStart", "TEST");
		// reqMarams.put("modifiedEnd", "TEST");

		response = get("/events/subscribers/processed", headers, reqMarams, null).then().statusCode(HttpStatus.OK.value()).extract().response();
		assertNotNull(response);
		subscribers = asObject(response.body().asString(), List.class);
		subscribers.forEach(sub -> {
			_LOGGER.info("JSON ==>" + JSONUtils.objectToJson(sub));
		});
	}

	@Test(priority = 100)
	public void test_eventSubscriber_email() {
		final Map<String, String> reqMarams = new HashMap<>();
		reqMarams.put("processed", "0");
		reqMarams.put("attempt", "0");
		reqMarams.put("fields", "event_name, id, event_id");
		reqMarams.put("limit", "100");
		final Response response = get("/events/subscribers/unprocessed", headers, reqMarams, null).then().statusCode(HttpStatus.OK.value()).extract()
				.response();
		assertNotNull(response);
		final List<?> subscribers = asObject(response.body().asString(), List.class);

		_LOGGER.info("Size = " + subscribers.size());
		subscribers.forEach(sub -> {
			_LOGGER.info("JSON ==>" + JSONUtils.objectToJson(sub));
		});

	}

	@Test(priority = 110)
	public void test_update_eventSubscriber_email() {
		final Map<String, String> reqMarams = new HashMap<>();
		reqMarams.put("eventName", "PROCESS_EMAIL");
		reqMarams.put("attempt", "1");
		reqMarams.put("processed", "0");
		patch("/events/subscribers/change-unprocessed", null, headers, reqMarams, null);
	}

	@Test(priority = 120)
	public void test_topics_cache_refresh() {
		patch("/events/topics/refresh", null, headers, null, null);
	}

	/*
	 * // @Test(priority = 500) public void test_eventSubscriberDelete() {
	 *
	 * final DynamoDbRepositoryImpl dynamoDbRepository =
	 * ContextUtils.getBean(DynamoDbRepositoryImpl.class); final
	 * EventDynamoDBRepositoryImpl eventRepo =
	 * ContextUtils.getBean(EventDynamoDBRepositoryImpl.class);
	 *
	 * final Event event = eventRepo.findEventWithSubscribers("TEST", idCreated); if
	 * (CollectionUtils.isNotEmpty(event.getSubscribers())) { try {
	 * event.getSubscribers().stream().forEach(sub -> { final DynamoDBMapper
	 * dbMapper = dynamoDbRepository.getMapper(EventSubscriber.TABLE_NAME);
	 * dbMapper.delete(sub); }); } catch (final Exception e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } } try { final
	 * DynamoDBMapper dbMapper1 = dynamoDbRepository.getMapper(Event.TABLE_NAME);
	 * dbMapper1.delete(event); } catch (final Exception e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); } }
	 */

	private EventSubscriber prepareEventSubscriber(final Event createdEvent, final int i) {
		final EventSubscriber subscriber = new EventSubscriber();
		subscriber.setRequestId(createdEvent.getRequestId());
		subscriber.setEventId(createdEvent.getId());
		subscriber.setMessageId(createdEvent.getMessageId() + i);
		subscriber.setProcessed(BooleanFlag.NO);
		subscriber.setAttempt(0);
		subscriber.setEventName(createdEvent.getEventName());
		subscriber.setEventData(createdEvent.getEventData());
		subscriber.setSubscriberId(String.valueOf(createdEvent.getId()));
		subscriber.setCreatedBy(createdEvent.getCreatedBy());
		subscriber.setCreatedOn(createdEvent.getCreatedOn());
		subscriber.setModifiedBy(createdEvent.getModifiedBy());
		subscriber.setModifiedOn(createdEvent.getModifiedOn());
		subscriber.setExpireAt(createdEvent.getExpireAt());
		return subscriber;
	}

}
