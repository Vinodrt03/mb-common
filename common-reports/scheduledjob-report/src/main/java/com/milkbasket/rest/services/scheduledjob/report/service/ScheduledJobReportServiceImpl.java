package com.milkbasket.rest.services.scheduledjob.report.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.core.framework.utility.CollectionUtils;
import com.milkbasket.rest.services.scheduledjob.report.bean.ScheduledJobPageRequestBean;
import com.milkbasket.rest.services.scheduledjob.report.bean.ScheduledJobSummaryPageRequestBean;
import com.milkbasket.rest.services.scheduledjob.report.bean.ScheduledJobSummaryViewBean;
import com.milkbasket.rest.services.scheduledjob.report.bean.ScheduledJobViewBean;
import com.milkbasket.rest.services.scheduledjob.report.repository.ScheduledJobReportRepository;

@Service
/**
 * <p>
 * ScheduledJobReportServiceImpl class.
 * </p>
 *
 * @author Neeraj
 * @version $Id: $Id
 */
public class ScheduledJobReportServiceImpl implements ScheduledJobReportService {

	@Autowired
	private ScheduledJobReportRepository scheduledJobReportRepository;

	/** {@inheritDoc} */
	@Override
	public PageAndSortResult<ScheduledJobViewBean> findScheduledJobDataList(final ScheduledJobPageRequestBean pageRequestBean) {
		final PageAndSortResult<ScheduledJobViewBean> result = scheduledJobReportRepository.getScheduledJobDataList(pageRequestBean);
		if (CollectionUtils.isNotEmpty(result.getData())) {
			result.getData().forEach(ScheduledJobViewBean::exchangeJSONData);
		}
		exchangeDates(result);
		return result;
	}

	/** {@inheritDoc} */
	@Override
	public PageAndSortResult<ScheduledJobViewBean> exportScheduledJobDataList(final ScheduledJobPageRequestBean pageRequestBean) {

		final PageAndSortResult<ScheduledJobViewBean> result = scheduledJobReportRepository.getScheduledJobDataList(pageRequestBean);
		if (CollectionUtils.isNotEmpty(result.getData())) {
			result.getData().forEach(ScheduledJobViewBean::exchangeJSONData);
		}
		exchangeDates(result);
		return result;
	}

	private void exchangeDates(final PageAndSortResult<ScheduledJobViewBean> result) {
		if (CollectionUtils.isNotEmpty(result.getData())) {
			result.getData().forEach(ScheduledJobViewBean::exchangeDates);
		}
	}

	@Override
	public PageAndSortResult<ScheduledJobSummaryViewBean> findScheduledJobSummaryDataList(final ScheduledJobSummaryPageRequestBean pageRequestBean) {
		pageRequestBean.getFilters().presetDates();
		ScheduledJobSummaryPageRequestBean.createDatalistRequest(pageRequestBean);
		final PageAndSortResult<ScheduledJobSummaryViewBean> result = scheduledJobReportRepository.getScheduledJobSummaryDataList(pageRequestBean);
		return result;
	}

}
