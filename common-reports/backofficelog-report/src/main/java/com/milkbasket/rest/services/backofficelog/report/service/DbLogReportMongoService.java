package com.milkbasket.rest.services.backofficelog.report.service;

import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.rest.services.backofficelog.report.bean.DbLogPageRequestBean;
import com.milkbasket.rest.services.backofficelog.report.bean.DbLogViewBean;

/**
 * <p>
 * DbLogReportMongoService interface.
 * </p>
 *
 * @author Rishab
 * @version $Id: $Id
 */
public interface DbLogReportMongoService {

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
