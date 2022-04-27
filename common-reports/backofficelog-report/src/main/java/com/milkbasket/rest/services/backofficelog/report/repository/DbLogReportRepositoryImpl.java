package com.milkbasket.rest.services.backofficelog.report.repository;

import org.springframework.stereotype.Component;

import com.milkbasket.core.framework.dbsupport.jdbc.constants.Operation;
import com.milkbasket.core.framework.dbsupport.jdbc.pagination.FilterCriteria;
import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.core.framework.dbsupport.jdbc.repository.JdbcTemplateRepositoryImpl;
import com.milkbasket.rest.services.backofficelog.report.bean.DbLogPageRequestBean;
import com.milkbasket.rest.services.backofficelog.report.bean.DbLogViewBean;

@Component
/**
 * <p>
 * DbLogReportRepositoryImpl class.
 * </p>
 *
 * @author nitin
 * @version $Id: $Id
 */
public class DbLogReportRepositoryImpl extends JdbcTemplateRepositoryImpl implements DbLogReportRepository {

	/** {@inheritDoc} */
	@Override
	public PageAndSortResult<DbLogViewBean> getDbLogDataList(final DbLogPageRequestBean pageRequestBody) {

		final StringBuilder baseQuery = new StringBuilder(
				"select id, userId, userName, requestId, module, action, method, sqlQuery, timeTaken, date from v_dbLogs");
		pageRequestBody.replaceFilter("startDate", new FilterCriteria("startDate", pageRequestBody.getFilters().getStartDate(), Operation.GTE))
				.replaceFilter("endDate", new FilterCriteria("endDate", pageRequestBody.getFilters().getEndDate(), Operation.LTE));

		return datalist(baseQuery, pageRequestBody.toPageRequest(), DbLogViewBean.class);
	}

}
