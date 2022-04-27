package com.milkbasket.rest.services.backofficelog.report.repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Component;

import com.milkbasket.core.framework.common.bean.log.AppErrorLog;
import com.milkbasket.core.framework.common.bean.log.AppLog;
import com.milkbasket.core.framework.common.constants.CommonConstants;
import com.milkbasket.core.framework.dbsupport.jdbc.pagination.FilterCriteria;
import com.milkbasket.core.framework.dbsupport.jdbc.pagination.SortCriteria;
import com.milkbasket.core.framework.utility.DateUtils;
import com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogPageRequestBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppLogPageRequestBean;

@Component
/**
 * <p>
 * AppLogReportMongoRepositoryImpl class.
 * </p>
 *
 * @author Rishab
 * @version $Id: $Id
 */
public class AppLogReportMongoRepositoryImpl implements AppLogReportMongoRepository {

	@Autowired
	@Qualifier(CommonConstants.MONGODB_LOG_TEMPLATE)
	private MongoTemplate mongoTemplate;

	/** {@inheritDoc} */
	@Override
	public Page<AppLog> getAppLogDataList(final AppLogPageRequestBean pageRequestBody) {

		// pages in mongo start from 0 therefore -1
		final Pageable pageable = PageRequest.of(pageRequestBody.getPageNo() - 1, pageRequestBody.getPageSize());

		final Query query = new Query().with(pageable);
		applyFilterAndSort(query, pageRequestBody);
		final List<AppLog> logs = mongoTemplate.find(query, AppLog.class);
		return PageableExecutionUtils.getPage(logs, pageable, () -> mongoTemplate.count(query.limit(0).skip(0), AppLog.class));
	}

	/** {@inheritDoc} */
	@Override
	public Page<AppErrorLog> getAppErrorLogDataList(final AppErrorLogPageRequestBean pageRequestBody) {

		// pages in mongo start from 0 therefore -1
		final Pageable pageable = PageRequest.of(pageRequestBody.getPageNo() - 1, pageRequestBody.getPageSize());

		final Query query = new Query().with(pageable);
		applyFilterAndSort(query, pageRequestBody);
		final List<AppErrorLog> logs = mongoTemplate.find(query, AppErrorLog.class);
		return PageableExecutionUtils.getPage(logs, pageable, () -> mongoTemplate.count(query.limit(0).skip(0), AppErrorLog.class));
	}

	private void applyFilterAndSort(final Query query, final AppLogPageRequestBean pageRequestBody) {
		final Map<String, FilterCriteria> filters = pageRequestBody.getFilters().extractFilterCriteria();

		final Set<String> filterKeySet = filters.keySet();
		final List<Criteria> criteria = new ArrayList<>();
		if (filterKeySet.contains("action")) {
			criteria.add(Criteria.where("action").is(filters.get("action").getValue()));
		}
		if (filterKeySet.contains("customerId")) {
			criteria.add(Criteria.where("userId").is(filters.get("customerId").getValue()));
		}
		if (filterKeySet.contains("startDate")) {
			final String dateStr = pageRequestBody.getFilters().getStartDate();
			final Date startDate = DateUtils.getDate(DateUtils.SHORT_DATE_FMT, dateStr);

			criteria.add(Criteria.where("date").gte(new Date(startDate.getTime())));
		}
		if (filterKeySet.contains("endDate")) {
			final String dateStr = pageRequestBody.getFilters().getEndDate();
			final Date endDate = DateUtils.getDate(DateUtils.SHORT_DATE_FMT, dateStr);
			final Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(endDate.getTime());
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			criteria.add(Criteria.where("date").lte(new Date(cal.getTimeInMillis())));
		}
		if (!criteria.isEmpty()) {
			query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[0])));
		}
		final Map<String, SortCriteria> sort = pageRequestBody.getSort().extractSortCriteria();
		final Set<String> sortKeySet = sort.keySet();
		if (CollectionUtils.isEmpty(sortKeySet)) {
			query.with(new Sort(Sort.Direction.DESC, "dateTime"));
		} else {

			if (sortKeySet.contains("dateTime")) {
				query.with(new Sort(Sort.Direction.fromString(sort.get("dateTime").getDirection().name()), "dateTime"));
			}
			if (sortKeySet.contains("action")) {
				query.with(new Sort(Sort.Direction.fromString(sort.get("action").getDirection().name()), "action"));
			}
			if (sortKeySet.contains("customerName")) {
				query.with(new Sort(Sort.Direction.fromString(sort.get("customerName").getDirection().name()), "name"));
			}
			if (sortKeySet.contains("id")) {
				query.with(new Sort(Sort.Direction.fromString(sort.get("id").getDirection().name()), "id"));
			}
		}
	}

	private void applyFilterAndSort(final Query query, final AppErrorLogPageRequestBean pageRequestBody) {
		final Map<String, FilterCriteria> filters = pageRequestBody.getFilters().extractFilterCriteria();

		final Set<String> filterKeySet = filters.keySet();
		final List<Criteria> criteria = new ArrayList<>();
		if (filterKeySet.contains("error")) {
			criteria.add(Criteria.where("error").is(filters.get("error").getValue()));
		}
		if (filterKeySet.contains("module")) {
			criteria.add(Criteria.where("module").is(filters.get("module").getValue()));
		}
		if (filterKeySet.contains("customerId")) {
			criteria.add(Criteria.where("userId").is(filters.get("customerId").getValue()));
		}
		if (filterKeySet.contains("startDate")) {
			final String dateStr = pageRequestBody.getFilters().getStartDate();
			final Date startDate = DateUtils.getDate(DateUtils.SHORT_DATE_FMT, dateStr);

			criteria.add(Criteria.where("date").gte(new Date(startDate.getTime())));
		}
		if (filterKeySet.contains("endDate")) {
			final String dateStr = pageRequestBody.getFilters().getEndDate();
			final Date endDate = DateUtils.getDate(DateUtils.SHORT_DATE_FMT, dateStr);
			final Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(endDate.getTime());
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			criteria.add(Criteria.where("date").lte(new Date(cal.getTimeInMillis())));
		}
		if (!criteria.isEmpty()) {
			query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[0])));
		}
		final Map<String, SortCriteria> sort = pageRequestBody.getSort().extractSortCriteria();
		final Set<String> sortKeySet = sort.keySet();
		if (CollectionUtils.isEmpty(sortKeySet)) {
			query.with(new Sort(Sort.Direction.DESC, "dateTime"));
		} else {

			if (sortKeySet.contains("dateTime")) {
				query.with(new Sort(Sort.Direction.fromString(sort.get("dateTime").getDirection().name()), "dateTime"));
			}
			if (sortKeySet.contains("error")) {
				query.with(new Sort(Sort.Direction.fromString(sort.get("error").getDirection().name()), "error"));
			}
			if (sortKeySet.contains("customerName")) {
				query.with(new Sort(Sort.Direction.fromString(sort.get("customerName").getDirection().name()), "name"));
			}
			if (sortKeySet.contains("id")) {
				query.with(new Sort(Sort.Direction.fromString(sort.get("id").getDirection().name()), "id"));
			}
			if (sortKeySet.contains("module")) {
				query.with(new Sort(Sort.Direction.fromString(sort.get("module").getDirection().name()), "module"));
			}
		}
	}

}
