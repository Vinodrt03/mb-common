package com.milkbasket.rest.services.template.report.repository;

import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.core.framework.dbsupport.jdbc.repository.JdbcTemplateRepository;
import com.milkbasket.rest.services.template.report.bean.TemplateBasePageRequestBean;
import com.milkbasket.rest.services.template.report.bean.TemplatePageRequestBean;
import com.milkbasket.rest.services.template.report.bean.TemplateViewBean;

/**
 * <p>
 * TemplateReportRepository interface.
 * </p>
 *
 * @author Sanjay Wadhwa
 * @version $Id: $Id
 */
public interface TemplateReportRepository extends JdbcTemplateRepository {

	/**
	 * <p>
	 * getTemplateDataList.
	 * </p>
	 *
	 * @param pageRequestBody
	 *            a
	 *            {@link com.milkbasket.rest.services.template.report.bean.TemplatePageRequestBean}
	 *            object.
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult}
	 *         object.
	 */
	PageAndSortResult<TemplateViewBean> getTemplateDataList(TemplatePageRequestBean pageRequestBody);

	/**
	 * <p>
	 * getTemplateDataListStopped.
	 * </p>
	 *
	 * @param pageRequestBody
	 *            a
	 *            {@link com.milkbasket.rest.services.template.report.bean.TemplateBasePageRequestBean}
	 *            object.
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult}
	 *         object.
	 */
	PageAndSortResult<TemplateViewBean> getTemplateDataListStopped(TemplateBasePageRequestBean pageRequestBody);

}
