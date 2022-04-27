package com.milkbasket.rest.services.template.report.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.rest.services.template.report.bean.TemplateBasePageRequestBean;
import com.milkbasket.rest.services.template.report.bean.TemplatePageRequestBean;
import com.milkbasket.rest.services.template.report.bean.TemplateViewBean;
import com.milkbasket.rest.services.template.report.repository.TemplateReportRepository;

@Service
/**
 * <p>
 * TemplateReportServiceImpl class.
 * </p>
 *
 * @author Sanjay Wadhwa
 * @version $Id: $Id
 */
public class TemplateReportServiceImpl implements TemplateReportService {

	@Autowired
	private TemplateReportRepository templateReportRepository;

	/** {@inheritDoc} */
	@Override
	public PageAndSortResult<TemplateViewBean> findTemplateDataList(final TemplatePageRequestBean pageRequestBean) {
		return templateReportRepository.getTemplateDataList(pageRequestBean);
	}

	/** {@inheritDoc} */
	@Override
	public PageAndSortResult<TemplateViewBean> exportTemplateDataList(final TemplatePageRequestBean pageRequestBean) {

		return templateReportRepository.getTemplateDataList(pageRequestBean);
	}

	/** {@inheritDoc} */
	@Override
	public PageAndSortResult<TemplateViewBean> findTemplateDataListStopped(final TemplateBasePageRequestBean pageRequestBean) {

		return templateReportRepository.getTemplateDataListStopped(pageRequestBean);
	}

	/** {@inheritDoc} */
	@Override
	public PageAndSortResult<TemplateViewBean> exportTemplateDataListStopped(final TemplateBasePageRequestBean pageRequestBean) {

		return templateReportRepository.getTemplateDataListStopped(pageRequestBean);
	}

}
