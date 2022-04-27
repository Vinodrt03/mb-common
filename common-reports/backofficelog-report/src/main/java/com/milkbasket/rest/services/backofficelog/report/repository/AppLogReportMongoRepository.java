package com.milkbasket.rest.services.backofficelog.report.repository;

import org.springframework.data.domain.Page;

import com.milkbasket.core.framework.common.bean.log.AppErrorLog;
import com.milkbasket.core.framework.common.bean.log.AppLog;
import com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogPageRequestBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppLogPageRequestBean;

/**
 * <p>
 * AppLogReportMongoRepository interface.
 * </p>
 *
 * @author Rishab
 * @version $Id: $Id
 */
public interface AppLogReportMongoRepository {

	/**
	 * <p>
	 * getAppLogDataList.
	 * </p>
	 *
	 * @param pageRequestBody
	 *            a
	 *            {@link com.milkbasket.rest.services.backofficelog.report.bean.AppLogPageRequestBean}
	 *            object.
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult}
	 *         object.
	 */
	Page<AppLog> getAppLogDataList(AppLogPageRequestBean pageRequestBody);

	/**
	 * <p>
	 * getAppErrorLogDataList.
	 * </p>
	 *
	 * @param pageRequestBody
	 *            a
	 *            {@link com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogPageRequestBean}
	 *            object.
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult}
	 *         object.
	 */
	Page<AppErrorLog> getAppErrorLogDataList(AppErrorLogPageRequestBean pageRequestBody);

}
