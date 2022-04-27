package com.milkbasket.rest.services.param.report.repository;

import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.core.framework.dbsupport.jdbc.repository.JdbcTemplateRepository;
import com.milkbasket.rest.services.param.report.bean.ParamPageRequestBean;
import com.milkbasket.rest.services.param.report.bean.ParamViewBean;

/**
 * <p>
 * ParamReportRepository interface.
 * </p>
 *
 * @author Neeraj
 * @version $Id: $Id
 */
public interface ParamReportRepository extends JdbcTemplateRepository {

	/**
	 * <p>
	 * getParamDataList.
	 * </p>
	 *
	 * @param pageRequestBody
	 *            a
	 *            {@link com.milkbasket.rest.services.param.report.bean.ParamPageRequestBean}
	 *            object.
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult}
	 *         object.
	 */
	PageAndSortResult<ParamViewBean> getParamDataList(ParamPageRequestBean pageRequestBody);

}
