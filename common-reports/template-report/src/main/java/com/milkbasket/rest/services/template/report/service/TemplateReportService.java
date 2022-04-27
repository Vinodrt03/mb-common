package com.milkbasket.rest.services.template.report.service;

import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.rest.services.template.report.bean.TemplateBasePageRequestBean;
import com.milkbasket.rest.services.template.report.bean.TemplatePageRequestBean;
import com.milkbasket.rest.services.template.report.bean.TemplateViewBean;

/**
 * <p>
 * TemplateReportService interface.
 * </p>
 *
 * @author Sanjay Wadhwa
 * @version $Id: $Id
 */
public interface TemplateReportService {

	/**
	 * <p>
	 * findTemplateDataList.
	 * </p>
	 *
	 * @param pageRequestBean
	 *            a
	 *            {@link com.milkbasket.rest.services.template.report.bean.TemplatePageRequestBean}
	 *            object.
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult}
	 *         object.
	 */
	PageAndSortResult<TemplateViewBean> findTemplateDataList(TemplatePageRequestBean pageRequestBean);

	/**
	 * <p>
	 * exportTemplateDataList.
	 * </p>
	 *
	 * @param pageRequestBean
	 *            a
	 *            {@link com.milkbasket.rest.services.template.report.bean.TemplatePageRequestBean}
	 *            object.
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult}
	 *         object.
	 */
	PageAndSortResult<TemplateViewBean> exportTemplateDataList(TemplatePageRequestBean pageRequestBean);

	/**
	 * <p>
	 * findTemplateDataListStopped.
	 * </p>
	 *
	 * @param pageRequestBean
	 *            a
	 *            {@link com.milkbasket.rest.services.template.report.bean.TemplateBasePageRequestBean}
	 *            object.
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult}
	 *         object.
	 */
	PageAndSortResult<TemplateViewBean> findTemplateDataListStopped(TemplateBasePageRequestBean pageRequestBean);

	/**
	 * <p>
	 * exportTemplateDataListStopped.
	 * </p>
	 *
	 * @param pageRequestBean
	 *            a
	 *            {@link com.milkbasket.rest.services.template.report.bean.TemplateBasePageRequestBean}
	 *            object.
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult}
	 *         object.
	 */
	PageAndSortResult<TemplateViewBean> exportTemplateDataListStopped(TemplateBasePageRequestBean pageRequestBean);

}
