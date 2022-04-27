package com.milkbasket.rest.services.property.report.repository;

import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.core.framework.dbsupport.jdbc.repository.JdbcTemplateRepository;
import com.milkbasket.rest.services.property.report.bean.PropertyPageRequestBean;
import com.milkbasket.rest.services.property.report.bean.PropertyViewBean;

/**
 * <p>
 * PropertyReportRepository interface.
 * </p>
 *
 * @author Tanuja
 * @version $Id: $Id
 */
public interface PropertyReportRepository extends JdbcTemplateRepository {

	/**
	 * <p>
	 * getPropertyDataList.
	 * </p>
	 *
	 * @property pageRequestBody a
	 *           {@link com.milkbasket.rest.services.property.report.bean.PropertyPageRequestBean}
	 *           object.
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult}
	 *         object.
	 * @param pageRequestBody
	 *            a
	 *            {@link com.milkbasket.rest.services.property.report.bean.PropertyPageRequestBean}
	 *            object.
	 */
	PageAndSortResult<PropertyViewBean> getPropertyDataList(PropertyPageRequestBean pageRequestBody);

}
