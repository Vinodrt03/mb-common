package com.milkbasket.rest.services.scheduledjob.report.repository;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.milkbasket.core.framework.dbsupport.jdbc.constants.Operation;
import com.milkbasket.core.framework.dbsupport.jdbc.pagination.FilterCriteria;
import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest;
import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.core.framework.dbsupport.jdbc.repository.JdbcTemplateRepositoryImpl;
import com.milkbasket.rest.services.scheduledjob.report.bean.ScheduledJobPageRequestBean;
import com.milkbasket.rest.services.scheduledjob.report.bean.ScheduledJobSummaryPageRequestBean;
import com.milkbasket.rest.services.scheduledjob.report.bean.ScheduledJobSummaryViewBean;
import com.milkbasket.rest.services.scheduledjob.report.bean.ScheduledJobViewBean;

@Component
/**
 * <p>
 * ScheduledJobReportRepositoryImpl class.
 * </p>
 *
 * @author Neeraj
 * @version $Id: $Id
 */
public class ScheduledJobReportRepositoryImpl extends JdbcTemplateRepositoryImpl implements ScheduledJobReportRepository {

	/** {@inheritDoc} */
	@Override
	public PageAndSortResult<ScheduledJobViewBean> getScheduledJobDataList(final ScheduledJobPageRequestBean pageRequestBody) {

		final StringBuilder baseQuery = new StringBuilder("select * from v_scheduledJob");

		pageRequestBody.replaceFilter("jobName", new FilterCriteria("jobName", like(pageRequestBody.getFilters().getJobName()), Operation.LIKE))
				.replaceFilter("startTime", new FilterCriteria("startTime", pageRequestBody.getFilters().getStartTime(), Operation.GTE))
				.replaceFilter("endTime", new FilterCriteria("endTime", pageRequestBody.getFilters().getEndTime(), Operation.LTE))
		.replaceFilter("jobStatus", new FilterCriteria("jobStatus", pageRequestBody.getFilters().getJobStatus(), Operation.EQUALS))
		.replaceFilter("source", new FilterCriteria("source", pageRequestBody.getFilters().getSource(), Operation.EQUALS));

		return datalist(baseQuery, pageRequestBody.toPageRequest(), ScheduledJobViewBean.class);
	}

	@Override
	public PageAndSortResult<ScheduledJobSummaryViewBean> getScheduledJobSummaryDataList(final ScheduledJobSummaryPageRequestBean pageRequestBody) {

		final StringBuilder baseQuery = new StringBuilder("select count(*) as total, SUM(job_status='SUCCESS') as success, "
				+ "SUM(job_status='FAILED') as failed, SUM(job_status='INITIATED') as initiated, avg(time_taken) as averageTime ");
		final StringBuilder groupBy = new StringBuilder(" GROUP BY ");
		boolean addGroupByClause = false;

		// Query for job name
		if (pageRequestBody.getFilterCriteriaMap().containsKey("jobName")) {
			baseQuery.append(", job_name as jobName ");
			groupBy.append("job_name,");
			addGroupByClause = true;
			String filterJobName = pageRequestBody.getFilters().getJobName();
			filterJobName = StringUtils.isEmpty(filterJobName) ? "%" : filterJobName;
			pageRequestBody.replaceFilter("jobName", new FilterCriteria("job_name", like(filterJobName), Operation.LIKE));
		}
		// Query for hub id
		if (pageRequestBody.getFilterCriteriaMap().containsKey("hubId")) {
			baseQuery.append(", hub_id as hubId ");
			groupBy.append("hub_id,");
			addGroupByClause = true;
			pageRequestBody.replaceFilter("hubId", new FilterCriteria("hub_id", pageRequestBody.getFilters().getHubId(), Operation.EQUALS));
		}
		// Query for job from date
		if (pageRequestBody.getFilterCriteriaMap().containsKey("startTime")) {

			baseQuery.append(", job_date as jobDate ");
			pageRequestBody.addFilter(new FilterCriteria("job_date",
					pageRequestBody.getFilters().getDatePart(pageRequestBody.getFilters().getStartTime()), Operation.GTE));
			pageRequestBody.replaceFilter("startTime",
					new FilterCriteria("start_time", pageRequestBody.getFilters().getStartTime(), Operation.GTE));
		}
		// Query for job to date
		if (pageRequestBody.getFilterCriteriaMap().containsKey("endTime")) {
			pageRequestBody.replaceFilter("endTime",
					new FilterCriteria("end_time", pageRequestBody.getFilters().getEndTime(), Operation.LTE));
		}

		baseQuery.append(" from scheduled_job ");

		pageRequestBody.replaceFilter("jobStatus", new FilterCriteria("job_status", pageRequestBody.getFilters().getJobStatus(), Operation.EQUALS));

		if (addGroupByClause) {
			PageAndSortRequest.setGroupToken(StringUtils.stripEnd(groupBy.toString(), ","));
		}
		return datalist(baseQuery, pageRequestBody.toPageRequest(), ScheduledJobSummaryViewBean.class);
	}

}
