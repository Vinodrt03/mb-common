package com.milkbasket.rest.services.property.report.service;

import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.rest.services.property.report.bean.PropertyPageRequestBean;
import com.milkbasket.rest.services.property.report.bean.PropertyViewBean;

/**
 * <p>
 * PropertyReportService interface.
 * </p>
 *
 * @author Tanuja
 * @version $Id: $Id
 */
public interface PropertyReportService {

	/**
	 * <p>
	 * findPropertyDataList.
	 * </p>
	 *
	 * @property pageRequestBean a
	 *           {@link com.milkbasket.rest.services.property.report.bean.PropertyPageRequestBean}
	 *           object.
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult}
	 *         object.
	 * @param pageRequestBean
	 *            a
	 *            {@link com.milkbasket.rest.services.property.report.bean.PropertyPageRequestBean}
	 *            object.
	 */
	PageAndSortResult<PropertyViewBean> findPropertyDataList(PropertyPageRequestBean pageRequestBean);

}
