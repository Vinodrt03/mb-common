package com.milkbasket.rest.services.backofficelog.report.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.milkbasket.core.framework.common.bean.log.SqlLog;
import com.milkbasket.core.framework.common.constants.CommonFormats;
import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.core.framework.utility.DateUtils;
import com.milkbasket.core.framework.utility.StringUtils;
import com.milkbasket.rest.services.backofficelog.report.bean.DbLogFilterBean;
import com.milkbasket.rest.services.backofficelog.report.bean.DbLogPageRequestBean;
import com.milkbasket.rest.services.backofficelog.report.bean.DbLogViewBean;
import com.milkbasket.rest.services.backofficelog.report.repository.DbLogReportMongoRepository;

@Service
/**
 * <p>
 * DbLogReportMongoServiceImpl class.
 * </p>
 *
 * @author Rishab
 * @version $Id: $Id
 */
public class DbLogReportMongoServiceImpl implements DbLogReportMongoService {

	@Autowired
	private DbLogReportMongoRepository dbLogReportRepository;

	/** {@inheritDoc} */
	@Override
	public PageAndSortResult<DbLogViewBean> findDbLogDataList(@NotNull final DbLogPageRequestBean pageRequestBean) {

		final DbLogFilterBean filterBean = pageRequestBean.getFilters();
		ckeckFilterDate(filterBean);
		final Page<SqlLog> result = dbLogReportRepository.getDbLogDataList(pageRequestBean);
		final PageAndSortResult<DbLogViewBean> response = new PageAndSortResult<>();
		setResponse(result, response);

		return response;
	}

	private void setResponse(final Page<SqlLog> result, final PageAndSortResult<DbLogViewBean> response) {
		final List<SqlLog> content = result.getContent();
		final List<DbLogViewBean> data = new ArrayList<>();
		content.stream().forEach(log -> {
			final DbLogViewBean bean = new DbLogViewBean();
			bean.setId(log.getId());
			bean.setAction(log.getAction());
			if (null != log.getDate()) {
				bean.setDate(DateUtils.toString(CommonFormats.DATE_FORMAT_WITH_TIME, log.getDateTime()));
			}
			bean.setAction(log.getAction());
			bean.setMethod(log.getMethod());
			bean.setModule(log.getModule());
			bean.setRequestId(log.getRequestId());
			bean.setSqlQuery(log.getSql());
			bean.setTimeTaken(log.getTimeTaken().intValue());
			bean.setUserName(log.getUserName());
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
	public PageAndSortResult<DbLogViewBean> exportDbLogDataList(final DbLogPageRequestBean pageRequestBean) {

		final DbLogFilterBean filterBean = pageRequestBean.getFilters();
		ckeckFilterDate(filterBean);
		final Page<SqlLog> result = dbLogReportRepository.getDbLogDataList(pageRequestBean);
		final PageAndSortResult<DbLogViewBean> response = new PageAndSortResult<>();
		setResponse(result, response);

		return response;
	}

	private DbLogFilterBean ckeckFilterDate(final DbLogFilterBean filterBean) {
		if (StringUtils.isEmpty(filterBean.getStartDate()) && StringUtils.isEmpty(filterBean.getEndDate())) {
			filterBean.setEndDate(DateUtils.toString(CommonFormats.DATE_FORMAT, new Date()) + " 23:59:59");
			filterBean.setStartDate(DateUtils.toString(CommonFormats.DATE_FORMAT, new Date()) + " 00:00:00");
		}
		return filterBean;
	}

}
