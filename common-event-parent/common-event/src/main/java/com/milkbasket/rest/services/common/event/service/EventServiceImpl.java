package com.milkbasket.rest.services.common.event.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.milkbasket.core.framework.common.bean.CompletableFutureContextBean;
import com.milkbasket.core.framework.common.constants.CommonConstants;
import com.milkbasket.core.framework.common.constants.CommonConstants.DeploymentType;
import com.milkbasket.core.framework.common.utils.ContextUtils;
import com.milkbasket.core.framework.common.utils.LoggingUtils;
import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.core.framework.events.bean.Event;
import com.milkbasket.core.framework.events.bean.EventRetryRequestBean;
import com.milkbasket.core.framework.events.bean.EventSubscriber;
import com.milkbasket.core.framework.events.bean.EventSubscriberBean;
import com.milkbasket.core.framework.events.bean.EventSubscriberPageRequestBean;
import com.milkbasket.core.framework.events.bean.EventSubscriberSummaryPageRequestBean;
import com.milkbasket.core.framework.events.bean.EventSubscriberSummaryViewBean;
import com.milkbasket.core.framework.events.dao.EventRepository;
import com.milkbasket.core.framework.events.dao.EventRepositoryFactory;
import com.milkbasket.core.framework.events.publisher.EventProcessor;
import com.milkbasket.core.framework.events.sns.SNSOperations;
import com.milkbasket.core.framework.events.utils.EventUtils;
import com.milkbasket.core.framework.logging.core.AppLogger;
import com.milkbasket.core.framework.logging.manager.LoggingManager;
import com.milkbasket.core.framework.property.utils.PropertiesUtils;
import com.milkbasket.core.framework.utility.CollectionUtils;
import com.milkbasket.core.framework.utility.StringUtils;
import com.milkbasket.rest.services.common.event.bean.EventRequestBean;

@Service
public class EventServiceImpl implements EventService {

	private static final AppLogger _LOGGER = LoggingManager.getLogger(EventServiceImpl.class);

	@Override
	public List<EventSubscriberBean> findEventSubscribers(final String eventName, final Long eventId) {
		final Event event = eventRepo().findEventWithSubscribers(eventName, eventId);
		if (event != null && CollectionUtils.isNotEmpty(event.getSubscribers())) {
			return event.getSubscribers().stream().map(this::getSubscriberBean).collect(Collectors.toList());
		}
		return new ArrayList<>();
	}

	@Override
	public PageAndSortResult<EventSubscriberBean> findEventSubscribersDataList(final EventSubscriberPageRequestBean request) {
		return eventRepo().findEventSubscribersDataList(request);
	}

	@Override
	public PageAndSortResult<EventSubscriberSummaryViewBean> getEventSubscriberSummaryDataList(final EventSubscriberSummaryPageRequestBean request) {
		request.getFilters().presetDates();
		return eventRepo().findEventSubscriberSummaryDataList(request);
	}

	@Override
	public List<EventSubscriberBean> findEventSubscribers(final Integer processed, final Integer attempt) {
		final List<EventSubscriber> subs = eventRepo().findEventSubscribers(processed, attempt);
		if (CollectionUtils.isNotEmpty(subs)) {
			return subs.parallelStream().map(this::getSubscriberBean).collect(Collectors.toList());
		}
		return new ArrayList<>();
	}

	@Override
	public List<EventSubscriberBean> findEventSubscribers(final Integer processed, final Integer attempt, final String projection,
			final Integer limit) {
		final List<EventSubscriber> subs = eventRepo().findEventSubscribers(processed, attempt, projection, limit);
		if (CollectionUtils.isNotEmpty(subs)) {
			return subs.parallelStream().map(this::getSubscriberBean).collect(Collectors.toList());
		}
		return new ArrayList<>();
	}

	private EventSubscriberBean getSubscriberBean(final EventSubscriber subscriber) {
		return EventSubscriberBean.getSubscriberBean(subscriber);
	}

	private EventRepository eventRepo() {
		return EventRepositoryFactory.getService(PropertiesUtils.getProperty("EVENT_REPOSITORY", CommonConstants.EVENT_MYSQL_REPO));
	}

	@Override
	public List<EventSubscriberBean> findEventSubscribers(final String eventName, final Integer processed, final Date modifiedStart,
			final Date modifiedEnd) {
		final List<EventSubscriber> subs = eventRepo().findEventSubscribers(eventName, processed, modifiedStart, modifiedEnd);

		if (CollectionUtils.isNotEmpty(subs)) {
			return subs.parallelStream().map(this::getSubscriberBean).collect(Collectors.toList());
		}
		return new ArrayList<>();
	}

	@Override
	public void updateProcessedAndAttempt(final Long eventId, final Long id, final Integer processed, final Integer attempt) {
		eventRepo().updateProcessedAndAttempt(eventId, id, processed, attempt);
	}

	@Override
	public void forceCloseEvents(final Integer minutes) {
		eventRepo().forceCloseEvents(minutes);
	}

	@Override
	public void raiseEvent(final EventRequestBean event) {
		final CompletableFutureContextBean context = CompletableFutureContextBean.getContext();
		CompletableFuture.runAsync(() -> raiseEvent(context, event.getEventName(), event.getEventData()));
	}

	@Override
	public void updateSubscriber(final EventSubscriber eventSubscriber) {
		final CompletableFutureContextBean context = CompletableFutureContextBean.getContext();
		CompletableFuture.runAsync(() -> updateSubscriber(context, eventSubscriber));
	}

	private void raiseEvent(final CompletableFutureContextBean context, final String eventName, final Object eventData) {
		CompletableFutureContextBean.setContext(context);
		EventUtils.publishEvent(eventName, eventData);
	}

	private void updateSubscriber(final CompletableFutureContextBean context, final EventSubscriber eventSubscriber) {
		CompletableFutureContextBean.setContext(context);
		EventProcessor.updateSubscriber(eventSubscriber);
	}

	@Override
	public EventSubscriber initiateSubscrption(final Long eventId, final String subscriberId, final String messageId) {
		return EventProcessor.initiateSubscrption(eventId, subscriberId, messageId);
	}

	@Override
	public void retryEvents(final EventRetryRequestBean retryRequestBean) {
		final CompletableFutureContextBean context = CompletableFutureContextBean.getContext();
		final boolean batchCall = ContextUtils.getDeploymentType().equalsIgnoreCase(DeploymentType.BATCH.toString());
		if (batchCall || ContextUtils.isProfileTest()) {
			retryEvents(context, retryRequestBean);
		} else {
			CompletableFuture.runAsync(() -> retryEvents(context, retryRequestBean));
		}
	}

	private void retryEvents(final CompletableFutureContextBean context, final EventRetryRequestBean retryRequestBean) {
		CompletableFutureContextBean.setContext(context);
		final List<Event> failedEvents = eventRepo().findFailedEvents(retryRequestBean);
		// _LOGGER.info("Found " + failedEvents.size() + " Failed Events");
		final String profile = ContextUtils.getActiveProfile();
		failedEvents.stream().forEach(event -> republish(event, profile));
		_LOGGER.info("Failed Events  Processed count: " + failedEvents.size());
	}

	private void republish(final Event event, final String profile) {
		if (event != null) {
			final String topicArnKey = SNSOperations.getTopicEvent(profile, event.getEventName().toLowerCase());
			final String topicArn = SNSOperations.getValidTopic(topicArnKey);
			if (StringUtils.isNotEmpty(topicArn)) {
				LoggingUtils.logAdminAction(event.getEventName(), "RETRY");
				SNSOperations.republishOnValidTopic(topicArn, event);
			} else {
				_LOGGER.warn("Event Arn not found for " + topicArnKey);
			}
		}
	}

	@Override
	public void refreshTopicCache(final Integer log) {
		final CompletableFutureContextBean context = CompletableFutureContextBean.getContext();
		final boolean batchCall = ContextUtils.getDeploymentType().equalsIgnoreCase(DeploymentType.BATCH.toString());
		if (batchCall || ContextUtils.isProfileTest()) {
			refreshSnsTopicCache(context, log);
		} else {
			CompletableFuture.runAsync(() -> refreshSnsTopicCache(context, log));
		}
	}

	private void refreshSnsTopicCache(final CompletableFutureContextBean context, final Integer log) {
		CompletableFutureContextBean.setContext(context);
		SNSOperations.populate();
		if (log != null && log.intValue() == 1) {
			final List<String> redisKeys = SNSOperations.getAllTopicArns();
			if (CollectionUtils.isNotEmpty(redisKeys)) {
				redisKeys.stream().forEach(arn -> {
					final String mbArn = arn.substring(arn.indexOf(":milkbasket_") + 1);
					final String validTopicArn = SNSOperations.getValidTopic(mbArn);
					_LOGGER.info(mbArn + " : VALID ARN :" + validTopicArn);
					if (StringUtils.isNotBlank(validTopicArn)) {
						_LOGGER.info(validTopicArn + ": SUBSCRIBERS :" + SNSOperations.getTopicSubscribers(validTopicArn));
					}
				});
			} else {
				_LOGGER.error("Event Topics not refreshed... ");
			}
		}
	}

}
