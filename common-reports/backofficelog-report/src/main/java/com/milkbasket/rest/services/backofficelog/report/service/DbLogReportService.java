package com.milkbasket.rest.services.backofficelog.report.service;

import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.rest.services.backofficelog.report.bean.DbLogPageRequestBean;
import com.milkbasket.rest.services.backofficelog.report.bean.DbLogViewBean;

/**
 * <p>
 * DbLogReportService interface.
 * </p>
 *
 * @author nitin
 * @version $Id: $Id
 */
public interface DbLogReportService {

	/**
	 * <p>
	 * findDbLogDataList.
	 * </p>
	 *
	 * @param pageRequestBean
	 *            a
	 *            {@link com.milkbasket.rest.services.backofficelog.report.bean.DbLogPageRequestBean}
	 *            object.
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult}
	 *         object.
	 */
	PageAndSortResult<DbLogViewBean> findDbLogDataList(DbLogPageRequestBean pageRequestBean);

	/**
	 * <p>
	 * exportDbLogDataList.
	 * </p>
	 *
	 * @param pageRequestBean
	 *            a
	 *            {@link com.milkbasket.rest.services.backofficelog.report.bean.DbLogPageRequestBean}
	 *            object.
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult}
	 *         object.
	 */
	PageAndSortResult<DbLogViewBean> exportDbLogDataList(DbLogPageRequestBean pageRequestBean);

}
