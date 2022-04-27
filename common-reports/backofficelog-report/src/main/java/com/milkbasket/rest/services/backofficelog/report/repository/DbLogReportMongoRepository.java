package com.milkbasket.rest.services.backofficelog.report.repository;

import org.springframework.data.domain.Page;

import com.milkbasket.core.framework.common.bean.log.SqlLog;
import com.milkbasket.rest.services.backofficelog.report.bean.DbLogPageRequestBean;

/**
 * <p>
 * DbLogReportMongoRepository interface.
 * </p>
 *
 * @author Rishab
 * @version $Id: $Id
 */
public interface DbLogReportMongoRepository {

	/**
	 * <p>
	 * getDbLogDataList.
	 * </p>
	 *
	 * @param pageRequestBody
	 *            a
	 *            {@link com.milkbasket.rest.services.backofficelog.report.bean.DbLogPageRequestBean}
	 *            object.
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult}
	 *         object.
	 */
	Page<SqlLog> getDbLogDataList(DbLogPageRequestBean pageRequestBody);

}
