package com.milkbasket.rest.services.backofficelog.report.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.milkbasket.core.framework.dbsupport.jdbc.constants.Operation;
import com.milkbasket.core.framework.dbsupport.jdbc.pagination.FilterCriteria;
import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.core.framework.dbsupport.jdbc.repository.JdbcTemplateRepositoryImpl;
import com.milkbasket.core.framework.utility.StringUtils;
import com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogPageRequestBean;
import com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogSummaryFilterBean;
import com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogSummaryViewBean;
import com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogViewBean;

/**
 * Repository to fetch list of backoffice logs
 *
 * @author NitinKhaitan
 * @version $Id: $Id
 */
@Component
public class BackOfficeLogReportRepositoryImpl extends JdbcTemplateRepositoryImpl implements BackOfficeLogReportRepository {

	/** {@inheritDoc} */
	@Override
	public PageAndSortResult<BackOfficeLogViewBean> getBackOfficeLogDataList(final BackOfficeLogPageRequestBean pageRequestBody) {

		final StringBuilder baseQuery = new StringBuilder(
				"select id, userId, name,productId,hubId, dateTime, action, email, dataJson from v_backOfficeLog");
		pageRequestBody.replaceFilter("startDate", new FilterCriteria("startDate", pageRequestBody.getFilters().getStartDate(), Operation.GTE))
				.replaceFilter("endDate", new FilterCriteria("endDate", pageRequestBody.getFilters().getEndDate(), Operation.LTE));

		return datalist(baseQuery, pageRequestBody.toPageRequest(), BackOfficeLogViewBean.class);
	}

	/** {@inheritDoc} */
	@Override
	public List<BackOfficeLogSummaryViewBean> getBackOfficeLogSummary(final BackOfficeLogSummaryFilterBean filter) {
		final List<Object> params = new ArrayList<>();

		final StringBuilder query = new StringBuilder("select action, date, count from v_backOfficeLogSummary WHERE DATE(DATE) BETWEEN ? AND ? ");

		params.add(filter.getStartDate());
		params.add(filter.getEndDate());
		if (StringUtils.isNotEmpty(filter.getAction())) {
			query.append(" AND action = ?");
			params.add(filter.getAction());
		}

		return records(query, params, BackOfficeLogSummaryViewBean.class);
	}
}
