package com.milkbasket.rest.services.backofficelog.report.service;

import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogPageRequestBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogViewBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppLogPageRequestBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppLogViewBean;

/**
 * <p>
 * AppLogReportMongoService interface.
 * </p>
 *
 * @author Rishab
 * @version $Id: $Id
 */
public interface AppLogReportMongoService {

	/**
	 * <p>
	 * findAppLogDataList.
	 * </p>
	 *
	 * @param pageRequestBean
	 *            a
	 *            {@link com.milkbasket.rest.services.backofficelog.report.bean.AppLogPageRequestBean}
	 *            object.
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult}
	 *         object.
	 */
	PageAndSortResult<AppLogViewBean> findAppLogDataList(AppLogPageRequestBean pageRequestBean);

	/**
	 * <p>
	 * exportAppLogDataList.
	 * </p>
	 *
	 * @param pageRequestBean
	 *            a
	 *            {@link com.milkbasket.rest.services.backofficelog.report.bean.AppLogPageRequestBean}
	 *            object.
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult}
	 *         object.
	 */
	PageAndSortResult<AppLogViewBean> exportAppLogDataList(AppLogPageRequestBean pageRequestBean);

	/**
	 * <p>
	 * findAppErrorLogDataList.
	 * </p>
	 *
	 * @param pageRequestBean
	 *            a
	 *            {@link com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogPageRequestBean}
	 *            object.
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult}
	 *         object.
	 */
	PageAndSortResult<AppErrorLogViewBean> findAppErrorLogDataList(AppErrorLogPageRequestBean pageRequestBean);

	/**
	 * <p>
	 * exportAppErrorLogDataList.
	 * </p>
	 *
	 * @param pageRequestBean
	 *            a
	 *            {@link com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogPageRequestBean}
	 *            object.
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult}
	 *         object.
	 */
	PageAndSortResult<AppErrorLogViewBean> exportAppErrorLogDataList(AppErrorLogPageRequestBean pageRequestBean);

}
