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
import com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogFilterBean;
import com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogPageRequestBean;
import com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogSummaryFilterBean;
import com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogSummaryViewBean;
import com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogViewBean;
import com.milkbasket.rest.services.backofficelog.report.repository.BackOfficeLogReportRepository;

/**
 * Service to fetch list of backoffice logs
 *
 * @author NitinKhaitan
 * @version $Id: $Id
 */
@Service
public class BackOfficeLogReportServiceImpl implements BackOfficeLogReportService {

	@Autowired
	private BackOfficeLogReportRepository backOfficeLogReportRepository;

	/** {@inheritDoc} */
	@Override
	public PageAndSortResult<BackOfficeLogViewBean> findBackOfficeLogDataList(@NotNull final BackOfficeLogPageRequestBean pageRequestBean) {

		final BackOfficeLogFilterBean filterBean = pageRequestBean.getFilters();
		ckeckFilterDate(filterBean);
		final PageAndSortResult<BackOfficeLogViewBean> result = backOfficeLogReportRepository.getBackOfficeLogDataList(pageRequestBean);
		if (CollectionUtils.isNotEmpty(result.getData())) {
			result.getData().forEach(BackOfficeLogViewBean::exchangeJSONData);
		}
		exchangeDates(result);

		return result;
	}

	/** {@inheritDoc} */
	@Override
	public PageAndSortResult<BackOfficeLogViewBean> exportBackOfficeLogDataList(@NotNull final BackOfficeLogPageRequestBean pageRequestBean) {

		final BackOfficeLogFilterBean filterBean = pageRequestBean.getFilters();
		ckeckFilterDate(filterBean);
		final PageAndSortResult<BackOfficeLogViewBean> result = backOfficeLogReportRepository.getBackOfficeLogDataList(pageRequestBean);
		if (CollectionUtils.isNotEmpty(result.getData())) {
			result.getData().forEach(BackOfficeLogViewBean::exchangeJSONData);
		}
		exchangeDates(result);

		return result;
	}

	/** {@inheritDoc} */
	@Override
	public List<BackOfficeLogSummaryViewBean> findBackOfficeLogSummary(@NotNull final BackOfficeLogSummaryFilterBean filter) {

		Assert.notNull(filter, "BackOffice Log Summary Filter should not be null.");

		if (StringUtils.isEmpty(filter.getStartDate()) && StringUtils.isEmpty(filter.getEndDate())) {
			filter.setEndDate(DateUtils.toString(CommonFormats.DATE_FORMAT, new Date()) + " 23:59:59");
			filter.setStartDate(DateUtils.toString(CommonFormats.DATE_FORMAT, new Date()) + " 00:00:00");
		}

		return backOfficeLogReportRepository.getBackOfficeLogSummary(filter);
	}

	private void exchangeDates(final PageAndSortResult<BackOfficeLogViewBean> result) {
		if (CollectionUtils.isNotEmpty(result.getData())) {
			result.getData().forEach(BackOfficeLogViewBean::exchangeDates);
		}
	}

	private BackOfficeLogFilterBean ckeckFilterDate(final BackOfficeLogFilterBean filterBean) {
		if (StringUtils.isEmpty(filterBean.getStartDate()) && StringUtils.isEmpty(filterBean.getEndDate())) {
			filterBean.setEndDate(DateUtils.toString(CommonFormats.DATE_FORMAT, new Date()) + " 23:59:59");
			filterBean.setStartDate(DateUtils.toString(CommonFormats.DATE_FORMAT, new Date()) + " 00:00:00");
		}
		return filterBean;
	}

}
