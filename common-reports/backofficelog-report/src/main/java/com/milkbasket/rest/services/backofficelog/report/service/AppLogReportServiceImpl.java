package com.milkbasket.rest.services.backofficelog.report.service;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.milkbasket.core.framework.common.constants.CommonFormats;
import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.core.framework.utility.CollectionUtils;
import com.milkbasket.core.framework.utility.DateUtils;
import com.milkbasket.core.framework.utility.StringUtils;
import com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogFilterBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogPageRequestBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogSummaryFilterBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogSummaryViewBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogViewBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppLogFilterBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppLogPageRequestBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppLogSummaryFilterBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppLogSummaryViewBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppLogViewBean;
import com.milkbasket.rest.services.backofficelog.report.repository.AppLogReportRepository;

@Service
/**
 * <p>
 * AppLogReportServiceImpl class.
 * </p>
 *
 * @author Sanjay Wadhwa
 * @version $Id: $Id
 */
public class AppLogReportServiceImpl implements AppLogReportService {

	@Autowired
	private AppLogReportRepository appLogReportRepository;

	/** {@inheritDoc} */
	@Override
	public PageAndSortResult<AppLogViewBean> findAppLogDataList(final AppLogPageRequestBean pageRequestBean) {
		final AppLogFilterBean filterBean = pageRequestBean.getFilters();
		ckeckFilterDate(filterBean);
		final PageAndSortResult<AppLogViewBean> result = appLogReportRepository.getAppLogDataList(pageRequestBean);

		if (CollectionUtils.isNotEmpty(result.getData())) {
			result.getData().forEach(AppLogViewBean::exchangeJSONData);
		}
		exchangeDatesAppLog(result);
		return result;
	}

	/** {@inheritDoc} */
	@Override
	public PageAndSortResult<AppLogViewBean> exportAppLogDataList(final AppLogPageRequestBean pageRequestBean) {

		final AppLogFilterBean filterBean = pageRequestBean.getFilters();
		ckeckFilterDate(filterBean);
		final PageAndSortResult<AppLogViewBean> result = appLogReportRepository.getAppLogDataList(pageRequestBean);

		if (CollectionUtils.isNotEmpty(result.getData())) {
			result.getData().forEach(AppLogViewBean::exchangeJSONData);
		}

		exchangeDatesAppLog(result);
		return result;
	}

	/** {@inheritDoc} */
	@Override
	public PageAndSortResult<AppErrorLogViewBean> findAppErrorLogDataList(final AppErrorLogPageRequestBean pageRequestBean) {
		final AppErrorLogFilterBean filterBean = pageRequestBean.getFilters();
		ckeckFilterDate(filterBean);
		final PageAndSortResult<AppErrorLogViewBean> result = appLogReportRepository.getAppErrorLogDataList(pageRequestBean);
		exchangeDatesAppErrorLog(result);

		if (CollectionUtils.isNotEmpty(result.getData())) {
			result.getData().forEach(AppErrorLogViewBean::exchangeJSONData);
		}

		return result;
	}

	/** {@inheritDoc} */
	@Override
	public PageAndSortResult<AppErrorLogViewBean> exportAppErrorLogDataList(final AppErrorLogPageRequestBean pageRequestBean) {

		final AppErrorLogFilterBean filterBean = pageRequestBean.getFilters();
		ckeckFilterDate(filterBean);
		final PageAndSortResult<AppErrorLogViewBean> result = appLogReportRepository.getAppErrorLogDataList(pageRequestBean);

		if (CollectionUtils.isNotEmpty(result.getData())) {
			result.getData().forEach(AppErrorLogViewBean::exchangeJSONData);
		}

		exchangeDatesAppErrorLog(result);
		return result;
	}

	/** {@inheritDoc} */
	@Override
	public List<AppLogSummaryViewBean> findAppLogSummary(@NotNull final AppLogSummaryFilterBean filter) {

		Assert.notNull(filter, "App Log Summary Filter should not be null.");

		if (StringUtils.isEmpty(filter.getStartDate()) && StringUtils.isEmpty(filter.getEndDate())) {
			filter.setEndDate(DateUtils.toString(CommonFormats.DATE_FORMAT, new Date()) + " 23:59:59");
			filter.setStartDate(DateUtils.toString(CommonFormats.DATE_FORMAT, new Date()) + " 00:00:00");
		}

		return appLogReportRepository.getAppLogSummary(filter);
	}

	/** {@inheritDoc} */
	@Override
	public List<AppErrorLogSummaryViewBean> findAppErrorLogSummary(@NotNull final AppErrorLogSummaryFilterBean filter) {

		Assert.notNull(filter, "App Log Summary Filter should not be null.");

		if (StringUtils.isEmpty(filter.getStartDate()) && StringUtils.isEmpty(filter.getEndDate())) {
			filter.setEndDate(DateUtils.toString(CommonFormats.DATE_FORMAT, new Date()) + " 23:59:59");
			filter.setStartDate(DateUtils.toString(CommonFormats.DATE_FORMAT, new Date()) + " 00:00:00");
		}

		return appLogReportRepository.getAppErrorLogSummary(filter);
	}

	private void exchangeDatesAppLog(final PageAndSortResult<AppLogViewBean> result) {
		if (CollectionUtils.isNotEmpty(result.getData())) {
			result.getData().forEach(AppLogViewBean::exchangeDates);
		}
	}

	private void exchangeDatesAppErrorLog(final PageAndSortResult<AppErrorLogViewBean> result) {
		if (CollectionUtils.isNotEmpty(result.getData())) {
			result.getData().forEach(AppErrorLogViewBean::exchangeDates);
		}
	}

	private AppLogFilterBean ckeckFilterDate(final AppLogFilterBean filterBean) {
		if (StringUtils.isEmpty(filterBean.getStartDate()) && StringUtils.isEmpty(filterBean.getEndDate())) {
			filterBean.setEndDate(DateUtils.toString(CommonFormats.DATE_FORMAT, new Date()) + " 23:59:59");
			filterBean.setStartDate(DateUtils.toString(CommonFormats.DATE_FORMAT, new Date()) + " 00:00:00");
		}
		return filterBean;
	}

	private AppErrorLogFilterBean ckeckFilterDate(final AppErrorLogFilterBean filterBean) {
		if (StringUtils.isEmpty(filterBean.getStartDate()) && StringUtils.isEmpty(filterBean.getEndDate())) {
			filterBean.setEndDate(DateUtils.toString(CommonFormats.DATE_FORMAT, new Date()) + " 23:59:59");
			filterBean.setStartDate(DateUtils.toString(CommonFormats.DATE_FORMAT, new Date()) + " 00:00:00");
		}
		return filterBean;
	}

}
