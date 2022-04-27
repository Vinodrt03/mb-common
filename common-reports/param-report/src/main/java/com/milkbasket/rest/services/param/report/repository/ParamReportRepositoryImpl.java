package com.milkbasket.rest.services.param.report.repository;

import org.springframework.stereotype.Component;

import com.milkbasket.core.framework.dbsupport.jdbc.constants.Operation;
import com.milkbasket.core.framework.dbsupport.jdbc.pagination.FilterCriteria;
import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.core.framework.dbsupport.jdbc.repository.JdbcTemplateRepositoryImpl;
import com.milkbasket.rest.services.param.report.bean.ParamPageRequestBean;
import com.milkbasket.rest.services.param.report.bean.ParamViewBean;

@Component
/**
 * <p>
 * ParamReportRepositoryImpl class.
 * </p>
 *
 * @author Neeraj
 * @version $Id: $Id
 */
public class ParamReportRepositoryImpl extends JdbcTemplateRepositoryImpl implements ParamReportRepository {

	/** {@inheritDoc} */
	@Override
	public PageAndSortResult<ParamViewBean> getParamDataList(final ParamPageRequestBean pageRequestBody) {

		final StringBuilder baseQuery = new StringBuilder("select paramKey, paramName, isEditable, paramId, paramValue from v_param");
		pageRequestBody.replaceFilter("paramKey", new FilterCriteria("paramKey", like(pageRequestBody.getFilters().getParamKey()), Operation.LIKE));

		return datalist(baseQuery, pageRequestBody.toPageRequest(), ParamViewBean.class);
	}

}
