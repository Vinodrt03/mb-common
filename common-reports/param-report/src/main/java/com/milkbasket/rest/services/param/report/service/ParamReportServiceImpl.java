package com.milkbasket.rest.services.param.report.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.rest.services.param.report.bean.ParamPageRequestBean;
import com.milkbasket.rest.services.param.report.bean.ParamViewBean;
import com.milkbasket.rest.services.param.report.repository.ParamReportRepository;

@Service
/**
 * <p>
 * ParamReportServiceImpl class.
 * </p>
 *
 * @author Neeraj
 * @version $Id: $Id
 */
public class ParamReportServiceImpl implements ParamReportService {

	@Autowired
	private ParamReportRepository paramReportRepository;

	/** {@inheritDoc} */
	@Override
	public PageAndSortResult<ParamViewBean> findParamDataList(final ParamPageRequestBean pageRequestBean) {

		return paramReportRepository.getParamDataList(pageRequestBean);
	}

}
