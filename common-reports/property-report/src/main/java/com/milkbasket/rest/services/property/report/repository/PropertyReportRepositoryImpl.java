package com.milkbasket.rest.services.property.report.repository;

import org.springframework.stereotype.Component;

import com.milkbasket.core.framework.common.constants.CommonConstants;
import com.milkbasket.core.framework.dbsupport.jdbc.constants.Operation;
import com.milkbasket.core.framework.dbsupport.jdbc.pagination.FilterCriteria;
import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.core.framework.dbsupport.jdbc.repository.JdbcTemplateRepositoryImpl;
import com.milkbasket.rest.services.property.report.bean.PropertyPageRequestBean;
import com.milkbasket.rest.services.property.report.bean.PropertyViewBean;

@Component
/**
 * <p>
 * PropertyReportRepositoryImpl class.
 * </p>
 *
 * @author Tanuja
 * @version $Id: $Id
 */
public class PropertyReportRepositoryImpl extends JdbcTemplateRepositoryImpl implements PropertyReportRepository {

	/** {@inheritDoc} */
	@Override
	public PageAndSortResult<PropertyViewBean> getPropertyDataList(final PropertyPageRequestBean pageRequestBody) {

		final StringBuilder baseQuery = new StringBuilder("select propertyKey, propertyValue from v_property");
		pageRequestBody.replaceFilter("propertyKey",
				new FilterCriteria("propertyKey", like(pageRequestBody.getFilters().getPropertyKey()), Operation.LIKE));
		pageRequestBody
				.addFilter(new FilterCriteria("propertyEnv", like(System.getProperty(CommonConstants.SPRING_PROFILE_ACTIVE_KEY)), Operation.LIKE));
		return datalist(baseQuery, pageRequestBody.toPageRequest(), PropertyViewBean.class);
	}

}
