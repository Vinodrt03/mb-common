package com.milkbasket.rest.services.template.report.repository;

import org.springframework.stereotype.Component;

import com.milkbasket.core.framework.dbsupport.jdbc.constants.Operation;
import com.milkbasket.core.framework.dbsupport.jdbc.pagination.FilterCriteria;
import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.core.framework.dbsupport.jdbc.repository.JdbcTemplateRepositoryImpl;
import com.milkbasket.rest.services.template.report.bean.TemplateBasePageRequestBean;
import com.milkbasket.rest.services.template.report.bean.TemplatePageRequestBean;
import com.milkbasket.rest.services.template.report.bean.TemplateViewBean;

@Component
/**
 * <p>
 * TemplateReportRepositoryImpl class.
 * </p>
 *
 * @author Sanjay Wadhwa
 * @version $Id: $Id
 */
public class TemplateReportRepositoryImpl extends JdbcTemplateRepositoryImpl implements TemplateReportRepository {

	/** {@inheritDoc} */
	@Override
	public PageAndSortResult<TemplateViewBean> getTemplateDataList(final TemplatePageRequestBean pageRequestBody) {

		final StringBuilder baseQuery = new StringBuilder("select * from v_message_template");
		pageRequestBody.addFilter(new FilterCriteria("flag", 1, Operation.EQUALS));
		return datalist(baseQuery, pageRequestBody.toPageRequest(), TemplateViewBean.class);
	}

	/** {@inheritDoc} */
	@Override
	public PageAndSortResult<TemplateViewBean> getTemplateDataListStopped(final TemplateBasePageRequestBean pageRequestBody) {

		final StringBuilder baseQuery = new StringBuilder("select * from v_message_template");
		pageRequestBody.addFilter(new FilterCriteria("flag", 0, Operation.EQUALS));
		return datalist(baseQuery, pageRequestBody.toPageRequest(), TemplateViewBean.class);
	}

}
