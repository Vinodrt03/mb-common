package com.milkbasket.rest.services.param.report.service;

import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.rest.services.param.report.bean.ParamPageRequestBean;
import com.milkbasket.rest.services.param.report.bean.ParamViewBean;

/**
 * <p>
 * ParamReportService interface.
 * </p>
 *
 * @author Neeraj
 * @version $Id: $Id
 */
public interface ParamReportService {

	/**
	 * <p>
	 * findParamDataList.
	 * </p>
	 *
	 * @param pageRequestBean
	 *            a
	 *            {@link com.milkbasket.rest.services.param.report.bean.ParamPageRequestBean}
	 *            object.
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult}
	 *         object.
	 */
	PageAndSortResult<ParamViewBean> findParamDataList(ParamPageRequestBean pageRequestBean);

}
