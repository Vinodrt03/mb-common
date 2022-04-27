package com.milkbasket.rest.services.common.event.service;

import java.util.Date;
import java.util.List;

import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.core.framework.events.bean.EventRetryRequestBean;
import com.milkbasket.core.framework.events.bean.EventSubscriber;
import com.milkbasket.core.framework.events.bean.EventSubscriberBean;
import com.milkbasket.core.framework.events.bean.EventSubscriberPageRequestBean;
import com.milkbasket.core.framework.events.bean.EventSubscriberSummaryPageRequestBean;
import com.milkbasket.core.framework.events.bean.EventSubscriberSummaryViewBean;
import com.milkbasket.rest.services.common.event.bean.EventRequestBean;

public interface EventService {

	List<EventSubscriberBean> findEventSubscribers(String eventName, Long eventId);

	List<EventSubscriberBean> findEventSubscribers(Integer processed, Integer attempt);

	List<EventSubscriberBean> findEventSubscribers(String eventName, Integer processed, Date modifiedStart, Date modifiedEnd);

	List<EventSubscriberBean> findEventSubscribers(Integer processed, Integer attempt, String projection, Integer limit);

	void updateProcessedAndAttempt(Long eventId, Long id, Integer processed, Integer attempt);

	void raiseEvent(EventRequestBean event);

	void updateSubscriber(EventSubscriber eventSubscriber);

	EventSubscriber initiateSubscrption(Long eventId, String subscriberId, String messageId);

	void forceCloseEvents(Integer minutes);

	void retryEvents(EventRetryRequestBean retryRequestBean);

	void refreshTopicCache(Integer log);

	PageAndSortResult<EventSubscriberBean> findEventSubscribersDataList(EventSubscriberPageRequestBean request);

	PageAndSortResult<EventSubscriberSummaryViewBean> getEventSubscriberSummaryDataList(EventSubscriberSummaryPageRequestBean request);

}
