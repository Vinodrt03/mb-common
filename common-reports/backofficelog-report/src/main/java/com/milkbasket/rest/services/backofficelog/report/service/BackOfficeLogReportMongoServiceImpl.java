package com.milkbasket.rest.services.backofficelog.report.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.milkbasket.core.framework.common.bean.log.AdminLog;
import com.milkbasket.core.framework.common.constants.CommonFormats;
import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.core.framework.utility.DateUtils;
import com.milkbasket.core.framework.utility.JSONUtils;
import com.milkbasket.core.framework.utility.StringUtils;
import com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogFilterBean;
import com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogPageRequestBean;
import com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogViewBean;
import com.milkbasket.rest.services.backofficelog.report.repository.BackOfficeLogReportMongoRepository;

/**
 * Service to fetch list of backoffice logs from mongodb
 *
 * @author Rishab
 * @version $Id: $Id
 */
@Service
public class BackOfficeLogReportMongoServiceImpl implements BackOfficeLogReportMongoService {

	@Autowired
	private BackOfficeLogReportMongoRepository backOfficeLogReportRepository;

	/** {@inheritDoc} */
	@Override
	public PageAndSortResult<BackOfficeLogViewBean> findBackOfficeLogDataList(@NotNull final BackOfficeLogPageRequestBean pageRequestBean) {

		final BackOfficeLogFilterBean filterBean = pageRequestBean.getFilters();
		ckeckFilterDate(filterBean);
		final Page<AdminLog> result = backOfficeLogReportRepository.getBackOfficeLogDataList(pageRequestBean);
		final PageAndSortResult<BackOfficeLogViewBean> response = new PageAndSortResult<>();
		setResponse(result, response);

		return response;
	}

	private void setResponse(final Page<AdminLog> result, final PageAndSortResult<BackOfficeLogViewBean> response) {
		final List<AdminLog> content = result.getContent();
		final List<BackOfficeLogViewBean> data = new ArrayList<>();
		content.stream().forEach(log -> {
			final BackOfficeLogViewBean bean = new BackOfficeLogViewBean();
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
			bean.setEmail(log.getEmail());
			bean.setHubId(log.getHubId());
			bean.setName(log.getName());
			bean.setProductId(log.getProductId());
			bean.setUserId(log.getUserId());

			data.add(bean);
		});
		response.setData(data);
		response.setPageNo(result.getNumber() + 1);
		response.setPageSize(result.getSize());
		response.setPages(result.getTotalPages());
		response.setTotal((int) result.getTotalElements());

	}

	/** {@inheritDoc} */
	@Override
	public PageAndSortResult<BackOfficeLogViewBean> exportBackOfficeLogDataList(@NotNull final BackOfficeLogPageRequestBean pageRequestBean) {

		final BackOfficeLogFilterBean filterBean = pageRequestBean.getFilters();
		ckeckFilterDate(filterBean);
		final Page<AdminLog> result = backOfficeLogReportRepository.getBackOfficeLogDataList(pageRequestBean);
		final PageAndSortResult<BackOfficeLogViewBean> response = new PageAndSortResult<>();
		setResponse(result, response);

		return response;
	}

	private BackOfficeLogFilterBean ckeckFilterDate(final BackOfficeLogFilterBean filterBean) {
		if (StringUtils.isEmpty(filterBean.getStartDate()) && StringUtils.isEmpty(filterBean.getEndDate())) {
			filterBean.setEndDate(DateUtils.toString(CommonFormats.DATE_FORMAT, new Date()) + " 23:59:59");
			filterBean.setStartDate(DateUtils.toString(CommonFormats.DATE_FORMAT, new Date()) + " 00:00:00");
		}
		return filterBean;
	}

}
