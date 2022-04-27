package com.milkbasket.rest.services.backofficelog.report.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.milkbasket.core.framework.common.bean.log.AppErrorLog;
import com.milkbasket.core.framework.common.bean.log.AppLog;
import com.milkbasket.core.framework.common.constants.CommonFormats;
import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.core.framework.utility.DateUtils;
import com.milkbasket.core.framework.utility.JSONUtils;
import com.milkbasket.core.framework.utility.StringUtils;
import com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogFilterBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogPageRequestBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogViewBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppLogFilterBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppLogPageRequestBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppLogViewBean;
import com.milkbasket.rest.services.backofficelog.report.repository.AppLogReportMongoRepository;

@Service
/**
 * <p>
 * AppLogReportMongoServiceImpl class.
 * </p>
 *
 * @author Rishab
 * @version $Id: $Id
 */
public class AppLogReportMongoServiceImpl implements AppLogReportMongoService {

	@Autowired
	private AppLogReportMongoRepository appLogReportRepository;

	/** {@inheritDoc} */
	@Override
	public PageAndSortResult<AppLogViewBean> findAppLogDataList(final AppLogPageRequestBean pageRequestBean) {
		final AppLogFilterBean filterBean = pageRequestBean.getFilters();
		ckeckFilterDate(filterBean);
		final Page<AppLog> result = appLogReportRepository.getAppLogDataList(pageRequestBean);
		final PageAndSortResult<AppLogViewBean> response = new PageAndSortResult<>();
		setResponse(result, response);

		return response;
	}

	/** {@inheritDoc} */
	@Override
	public PageAndSortResult<AppLogViewBean> exportAppLogDataList(final AppLogPageRequestBean pageRequestBean) {

		final AppLogFilterBean filterBean = pageRequestBean.getFilters();
		ckeckFilterDate(filterBean);
		final Page<AppLog> result = appLogReportRepository.getAppLogDataList(pageRequestBean);
		final PageAndSortResult<AppLogViewBean> response = new PageAndSortResult<>();
		setResponse(result, response);

		return response;
	}

	/** {@inheritDoc} */
	@Override
	public PageAndSortResult<AppErrorLogViewBean> findAppErrorLogDataList(final AppErrorLogPageRequestBean pageRequestBean) {
		final AppErrorLogFilterBean filterBean = pageRequestBean.getFilters();
		ckeckFilterDate(filterBean);
		final Page<AppErrorLog> result = appLogReportRepository.getAppErrorLogDataList(pageRequestBean);
		final PageAndSortResult<AppErrorLogViewBean> response = new PageAndSortResult<>();
		setErrorResponse(result, response);

		return response;
	}

	/** {@inheritDoc} */
	@Override
	public PageAndSortResult<AppErrorLogViewBean> exportAppErrorLogDataList(final AppErrorLogPageRequestBean pageRequestBean) {

		final AppErrorLogFilterBean filterBean = pageRequestBean.getFilters();
		ckeckFilterDate(filterBean);
		final Page<AppErrorLog> result = appLogReportRepository.getAppErrorLogDataList(pageRequestBean);
		final PageAndSortResult<AppErrorLogViewBean> response = new PageAndSortResult<>();
		setErrorResponse(result, response);

		return response;
	}

	private void setErrorResponse(final Page<AppErrorLog> result, final PageAndSortResult<AppErrorLogViewBean> response) {
		final List<AppErrorLog> content = result.getContent();
		final List<AppErrorLogViewBean> data = new ArrayList<>();
		content.stream().forEach(log -> {
			final AppErrorLogViewBean bean = new AppErrorLogViewBean();
			bean.setId(log.getId());
			bean.setModule(log.getModule());
			if (null != log.getData()) {
				final String payload = JSONUtils.objectToJson(org.apache.commons.collections.CollectionUtils.get(log.getData(), 0));
				@SuppressWarnings("unchecked")
				final Map<String, Object> dataMap = JSONUtils.jsonToObject(payload, Map.class);
				bean.setData(dataMap);
			}
			if (null != log.getDateTime()) {
				bean.setDateTime(DateUtils.toString(CommonFormats.DATE_FORMAT_WITH_TIME, log.getDateTime()));
			}
			bean.setCustomerId(log.getUserId());
			bean.setCustomerName(log.getName());
			bean.setError(log.getAction());

			data.add(bean);
		});
		response.setData(data);
		response.setPageNo(result.getNumber() + 1);
		response.setPageSize(result.getSize());
		response.setPages(result.getTotalPages());
		response.setTotal((int) result.getTotalElements());

	}

	private void setResponse(final Page<AppLog> result, final PageAndSortResult<AppLogViewBean> response) {
		final List<AppLog> content = result.getContent();
		final List<AppLogViewBean> data = new ArrayList<>();
		content.stream().forEach(log -> {
			final AppLogViewBean bean = new AppLogViewBean();
			bean.setId(log.getId());
			bean.setAction(log.getAction());
			if (null != log.getData()) {
				try {
					final String payload = JSONUtils.objectToJson(log.getData());
					@SuppressWarnings("unchecked")
					final Map<String, Object> dataMap = JSONUtils.jsonToObject(payload, Map.class);
					bean.setData(dataMap);
				} catch (final Exception e) {

				}
			}
			if (null != log.getDateTime()) {
				bean.setDateTime(DateUtils.toString(CommonFormats.DATE_FORMAT_WITH_TIME, log.getDateTime()));
			}
			bean.setCustomerId(log.getUserId());
			bean.setCustomerName(log.getName());

			data.add(bean);
		});
		response.setData(data);
		response.setPageNo(result.getNumber() + 1);
		response.setPageSize(result.getSize());
		response.setPages(result.getTotalPages());
		response.setTotal((int) result.getTotalElements());

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
