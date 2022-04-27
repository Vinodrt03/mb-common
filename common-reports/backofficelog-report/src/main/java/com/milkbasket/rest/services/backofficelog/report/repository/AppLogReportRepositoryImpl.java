package com.milkbasket.rest.services.backofficelog.report.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.milkbasket.core.framework.dbsupport.jdbc.constants.Operation;
import com.milkbasket.core.framework.dbsupport.jdbc.pagination.FilterCriteria;
import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.core.framework.dbsupport.jdbc.repository.JdbcTemplateRepositoryImpl;
import com.milkbasket.core.framework.utility.StringUtils;
import com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogPageRequestBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogSummaryFilterBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogSummaryViewBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogViewBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppLogPageRequestBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppLogSummaryFilterBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppLogSummaryViewBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppLogViewBean;

@Component
/**
 * <p>
 * AppLogReportRepositoryImpl class.
 * </p>
 *
 * @author Sanjay Wadhwa
 * @version $Id: $Id
 */
public class AppLogReportRepositoryImpl extends JdbcTemplateRepositoryImpl implements AppLogReportRepository {

	/** {@inheritDoc} */
	@Override
	public PageAndSortResult<AppLogViewBean> getAppLogDataList(final AppLogPageRequestBean pageRequestBody) {

		final StringBuilder baseQuery = new StringBuilder("select id, customerId, customerName, dateTime, action, dataJson from v_appLog");
		pageRequestBody.replaceFilter("startDate", new FilterCriteria("startDate", pageRequestBody.getFilters().getStartDate(), Operation.GTE))
				.replaceFilter("endDate", new FilterCriteria("endDate", pageRequestBody.getFilters().getEndDate(), Operation.LTE));

		return datalist(baseQuery, pageRequestBody.toPageRequest(), AppLogViewBean.class);
	}

	/** {@inheritDoc} */
	@Override
	public PageAndSortResult<AppErrorLogViewBean> getAppErrorLogDataList(final AppErrorLogPageRequestBean pageRequestBody) {

		final StringBuilder baseQuery = new StringBuilder(
				"select id, customerId, customerName, error, module, dateTime, dataJson from v_appErrorLog");
		pageRequestBody.replaceFilter("startDate", new FilterCriteria("startDate", pageRequestBody.getFilters().getStartDate(), Operation.GTE))
				.replaceFilter("endDate", new FilterCriteria("endDate", pageRequestBody.getFilters().getEndDate(), Operation.LTE));

		return datalist(baseQuery, pageRequestBody.toPageRequest(), AppErrorLogViewBean.class);
	}

	///
	/** {@inheritDoc} */
	@Override
	public List<AppLogSummaryViewBean> getAppLogSummary(final AppLogSummaryFilterBean filter) {
		final List<Object> params = new ArrayList<>();

		final StringBuilder query = new StringBuilder(
				"select action, date, count(1) as count from v_appLogSummary WHERE DATE(date) BETWEEN ? AND ? ");

		params.add(filter.getStartDate());
		params.add(filter.getEndDate());
		if (StringUtils.isNotEmpty(filter.getAction())) {
			query.append(" AND action = ?");
			params.add(filter.getAction());
		}

		query.append(" group by action, date ").append(" order by dateTime desc");

		return records(query, params, AppLogSummaryViewBean.class);
	}

	/** {@inheritDoc} */
	@Override
	public List<AppErrorLogSummaryViewBean> getAppErrorLogSummary(final AppErrorLogSummaryFilterBean filter) {
		final List<Object> params = new ArrayList<>();

		final StringBuilder query = new StringBuilder("select error, date, count from v_appErrorLogSummary WHERE DATE(DATE) BETWEEN ? AND ? ");

		params.add(filter.getStartDate());
		params.add(filter.getEndDate());
		if (StringUtils.isNotEmpty(filter.getError())) {
			query.append(" AND error = ?");
			params.add(filter.getError());
		}

		return records(query, params, AppErrorLogSummaryViewBean.class);
	}

}
