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

import com.milkbasket.core.framework.common.bean.log.SqlLog;
import com.milkbasket.core.framework.common.constants.CommonConstants;
import com.milkbasket.core.framework.dbsupport.jdbc.pagination.FilterCriteria;
import com.milkbasket.core.framework.dbsupport.jdbc.pagination.SortCriteria;
import com.milkbasket.core.framework.utility.DateUtils;
import com.milkbasket.rest.services.backofficelog.report.bean.DbLogPageRequestBean;

@Component
/**
 * <p>
 * DbLogReportMongoRepositoryImpl class.
 * </p>
 *
 * @author Rishab
 * @version $Id: $Id
 */
public class DbLogReportMongoRepositoryImpl implements DbLogReportMongoRepository {

	@Autowired
	@Qualifier(CommonConstants.MONGODB_LOG_TEMPLATE)
	private MongoTemplate mongoTemplate;

	/** {@inheritDoc} */
	@Override
	public Page<SqlLog> getDbLogDataList(final DbLogPageRequestBean pageRequestBody) {

		// pages in mongo start from 0 therefore -1
		final Pageable pageable = PageRequest.of(pageRequestBody.getPageNo() - 1, pageRequestBody.getPageSize());

		final Query query = new Query().with(pageable);
		applyFilterAndSort(query, pageRequestBody);
		final List<SqlLog> logs = mongoTemplate.find(query, SqlLog.class);
		return PageableExecutionUtils.getPage(logs, pageable, () -> mongoTemplate.count(query.limit(0).skip(0), SqlLog.class));
	}

	private void applyFilterAndSort(final Query query, final DbLogPageRequestBean pageRequestBody) {
		final Map<String, FilterCriteria> filters = pageRequestBody.getFilters().extractFilterCriteria();

		final Set<String> filterKeySet = filters.keySet();
		final List<Criteria> criteria = new ArrayList<>();
		if (filterKeySet.contains("requestId")) {
			criteria.add(Criteria.where("requestId").is(filters.get("requestId").getValue()));
		}
		if (filterKeySet.contains("module")) {
			criteria.add(Criteria.where("module").is(filters.get("module").getValue()));
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
			query.with(new Sort(Sort.Direction.DESC, "date"));
		} else {

			if (sortKeySet.contains("date")) {
				query.with(new Sort(Sort.Direction.fromString(sort.get("date").getDirection().name()), "dateTime"));
			}
			if (sortKeySet.contains("userName")) {
				query.with(new Sort(Sort.Direction.fromString(sort.get("userName").getDirection().name()), "userName"));
			}
		}
	}

}
