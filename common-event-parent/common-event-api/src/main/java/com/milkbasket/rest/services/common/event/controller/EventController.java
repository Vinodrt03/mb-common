package com.milkbasket.rest.services.common.event.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.milkbasket.core.framework.common.bean.ErrorBean;
import com.milkbasket.core.framework.common.exception.ValidationException;
import com.milkbasket.core.framework.common.properties.Errors;
import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.core.framework.events.bean.EventRetryRequestBean;
import com.milkbasket.core.framework.events.bean.EventSubscriber;
import com.milkbasket.core.framework.events.bean.EventSubscriberBean;
import com.milkbasket.core.framework.events.bean.EventSubscriberPageRequestBean;
import com.milkbasket.core.framework.events.bean.EventSubscriberSummaryPageRequestBean;
import com.milkbasket.core.framework.events.bean.EventSubscriberSummaryViewBean;
import com.milkbasket.core.framework.logging.core.AppLogger;
import com.milkbasket.core.framework.logging.manager.LoggingManager;
import com.milkbasket.core.framework.utility.CollectionUtils;
import com.milkbasket.core.framework.utility.DateUtils;
import com.milkbasket.core.framework.utility.StringUtils;
import com.milkbasket.rest.services.common.event.bean.EventRequestBean;
import com.milkbasket.rest.services.common.event.service.EventService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController

@Api(tags = "Events Services")
public class EventController {

	private static final AppLogger _LOGGER = LoggingManager.getLogger(EventController.class);

	@Autowired
	private EventService eventService;

	@ApiOperation(value = "Raise Event.[B2B]", nickname = "raiseEvent")
	@PutMapping("/events")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void raiseEvent(@RequestBody final EventRequestBean event) {
		if (event == null || StringUtils.isBlank(event.getEventName()) || null == event.getEventData()) {
			throw new ValidationException(ErrorBean.withError("EVENT_DATA_ERROR", "Raise Event With proper event name and event data.", null));
		}
		eventService.raiseEvent(event);
	}

	@ApiOperation(value = "Retry Events.[B2B]", nickname = "retryEvents")
	@PutMapping("/events/retry")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void retryEvents(@RequestBody final EventRetryRequestBean payload) {
		eventService.retryEvents(payload);
	}

	@ApiOperation(value = "Initiate Subscrption.[B2B]", nickname = "initiateSubscrption")
	@PostMapping("/events/subscribers")
	public EventSubscriber initiateSubscrption(@RequestParam(name = "eventId", required = true) final Long eventId,
			@RequestParam(name = "subscriberId", required = true) final String subscriberId,
			@RequestParam(name = "messageId", required = true) final String messageId) {
		return eventService.initiateSubscrption(eventId, subscriberId, messageId);
	}

	@ApiOperation(value = "Update Subscriber.[B2B]", nickname = "updateSubscriber")
	@PutMapping("/events/subscribers")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateSubscriber(@RequestBody final EventSubscriber eventSubscriber) {
		eventService.updateSubscriber(eventSubscriber);
	}

	@ApiOperation(value = "Get Event and Subscribers detail.[B2B]", nickname = "findEventWithSubscribers")
	@GetMapping("/events/subscribers")
	public List<EventSubscriberBean> findEventSubscribers(@RequestParam(name = "eventName", required = true) final String eventName,
			@RequestParam(name = "eventId", required = true) final Long eventId) {
		return eventService.findEventSubscribers(eventName, eventId);
	}

	@ApiOperation(value = "Get Event and Subscribers detail.[B2B]", nickname = "findEventSubscribersOnProcessed")
	@GetMapping("/events/subscribers/processed")
	public List<EventSubscriberBean> findEventSubscribersOnProcessedAndAttempt(
			@RequestParam(name = "processed", required = true) final Integer processed,
			@RequestParam(name = "attempt", required = false) final Integer attempt,
			@RequestParam(name = "eventName", required = false) final String eventName,
			@RequestParam(name = "modifiedStart", required = false) final String modifiedStart,
			@RequestParam(name = "modifiedEnd", required = false) final String modifiedEnd) {

		if (attempt == null && StringUtils.isBlank(eventName)) {
			throw new ValidationException(ErrorBean.withError(Errors.MANDATORY, "attempt or eventName is required.", null));
		}
		if (StringUtils.isBlank(eventName)) {
			return eventService.findEventSubscribers(processed, attempt);
		}
		Date modifiedStartDt = DateUtils.getDate(DateUtils.DATE_FMT_WITH_TIME, modifiedStart);
		Date modifiedEndDt = DateUtils.getDate(DateUtils.DATE_FMT_WITH_TIME, modifiedEnd);

		if (modifiedStartDt == null || modifiedEndDt == null) {
			final Date today = DateUtils.getDate();
			modifiedStartDt = DateUtils.addDays(today, -1);
			modifiedEndDt = DateUtils.addDays(today, 1);
		}
		return eventService.findEventSubscribers(eventName, processed, modifiedStartDt, modifiedEndDt);
	}

	@ApiOperation(value = "Get Event and Subscribers detail.[B2B]", nickname = "findUnprocessedEventSubscribers")
	@GetMapping("/events/subscribers/unprocessed")
	public List<EventSubscriberBean> findUnprocessedEventSubscribers(@RequestParam(name = "processed", required = false) final Integer processed,
			@RequestParam(name = "attempt", required = false) final Integer attempt,
			@RequestParam(name = "fields", required = false) final String fields,
			@RequestParam(name = "limit", required = false) final Integer limit) {
		final Integer processedValue = processed == null ? 0 : processed;
		final Integer attemptValue = attempt == null ? 0 : attempt;
		return eventService.findEventSubscribers(processedValue, attemptValue, fields, limit);
	}

	@ApiOperation(value = "Update processed/attempt of unprocessed events[B2B]", nickname = "updateProcessedAndAttempt")
	@PatchMapping("/events/subscribers/change-unprocessed")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void resetCustomerPassword(@RequestParam(required = true) final String eventName, @RequestParam(required = false) final Integer processed,
			@RequestParam(required = true) final Integer attempt) {
		final List<EventSubscriberBean> unprocessEvents = eventService.findEventSubscribers(0, 0, "event_name, id, event_id", 500);
		if (CollectionUtils.isNotEmpty(unprocessEvents)) {
			unprocessEvents.parallelStream().forEach(sub -> {
				if (eventName.equals(sub.getEventName())) {
					eventService.updateProcessedAndAttempt(sub.getEventId(), sub.getId(), processed, attempt);
					_LOGGER.info(String.format("Event ID = %s Sub Id =%s updated.", sub.getEventId(), sub.getId()));
				}
			});
		}
	}

	@ApiOperation(value = "Refresh Topic Cache[B2B]", nickname = "refreshTopicCache")
	@GetMapping("/events/topics/refresh")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void refreshTopicCache(@RequestParam(required = false) final Integer log) {
		eventService.refreshTopicCache(log);
	}

	@ApiOperation(value = "Get paginated Event and Subscribers detail.", nickname = "findEventSubscribersDataList")
	@PostMapping("/events/subscribers/datalist")
	public final PageAndSortResult<EventSubscriberBean> findEventSubscribersDataList(
			@Valid @RequestBody(required = false) EventSubscriberPageRequestBean request) {

		request = EventSubscriberPageRequestBean.createDatalistRequest(request);
		return eventService.findEventSubscribersDataList(request);
	}

	@ApiOperation(value = "Get Summary of Event Subscribers.", nickname = "findEventSubscriberSummaryDataList")
	@PostMapping("/events/subscribers/summary/datalist")
	public final PageAndSortResult<EventSubscriberSummaryViewBean> getEventSubscribersSummaryDataList(
			@Valid @RequestBody(required = false) EventSubscriberSummaryPageRequestBean request) {

		request = EventSubscriberSummaryPageRequestBean.createDatalistRequest(request);
		return eventService.getEventSubscriberSummaryDataList(request);
	}

}
