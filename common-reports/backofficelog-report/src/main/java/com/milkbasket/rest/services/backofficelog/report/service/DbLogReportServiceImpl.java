package com.milkbasket.rest.services.backofficelog.report.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milkbasket.core.framework.common.constants.CommonFormats;
import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.core.framework.utility.CollectionUtils;
import com.milkbasket.core.framework.utility.DateUtils;
import com.milkbasket.core.framework.utility.StringUtils;
import com.milkbasket.rest.services.backofficelog.report.bean.DbLogFilterBean;
import com.milkbasket.rest.services.backofficelog.report.bean.DbLogPageRequestBean;
import com.milkbasket.rest.services.backofficelog.report.bean.DbLogViewBean;
import com.milkbasket.rest.services.backofficelog.report.repository.DbLogReportRepository;

@Service
/**
 * <p>
 * DbLogReportServiceImpl class.
 * </p>
 *
 * @author nitin
 * @version $Id: $Id
 */
public class DbLogReportServiceImpl implements DbLogReportService {

	@Autowired
	private DbLogReportRepository dbLogReportRepository;

	/** {@inheritDoc} */
	@Override
	public PageAndSortResult<DbLogViewBean> findDbLogDataList(final DbLogPageRequestBean pageRequestBean) {

		final DbLogFilterBean filterBean = pageRequestBean.getFilters();
		ckeckFilterDate(filterBean);
		final PageAndSortResult<DbLogViewBean> result = dbLogReportRepository.getDbLogDataList(pageRequestBean);
		exchangeDates(result);
		return result;
	}

	/** {@inheritDoc} */
	@Override
	public PageAndSortResult<DbLogViewBean> exportDbLogDataList(final DbLogPageRequestBean pageRequestBean) {

		final DbLogFilterBean filterBean = pageRequestBean.getFilters();
		ckeckFilterDate(filterBean);
		final PageAndSortResult<DbLogViewBean> result = dbLogReportRepository.getDbLogDataList(pageRequestBean);

		exchangeDates(result);
		return result;
	}

	private void exchangeDates(final PageAndSortResult<DbLogViewBean> result) {
		if (CollectionUtils.isNotEmpty(result.getData())) {
			result.getData().forEach(DbLogViewBean::exchangeDates);
		}
	}

	private DbLogFilterBean ckeckFilterDate(final DbLogFilterBean filterBean) {
		if (StringUtils.isEmpty(filterBean.getStartDate()) && StringUtils.isEmpty(filterBean.getEndDate())) {
			filterBean.setEndDate(DateUtils.toString(CommonFormats.DATE_FORMAT, new Date()) + " 23:59:59");
			filterBean.setStartDate(DateUtils.toString(CommonFormats.DATE_FORMAT, new Date()) + " 00:00:00");
		}
		return filterBean;
	}

}
