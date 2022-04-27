package com.milkbasket.rest.services.backofficelog.report.repository;

import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.core.framework.dbsupport.jdbc.repository.JdbcTemplateRepository;
import com.milkbasket.rest.services.backofficelog.report.bean.DbLogPageRequestBean;
import com.milkbasket.rest.services.backofficelog.report.bean.DbLogViewBean;

/**
 * <p>
 * DbLogReportRepository interface.
 * </p>
 *
 * @author nitin
 * @version $Id: $Id
 */
public interface DbLogReportRepository extends JdbcTemplateRepository {

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
	PageAndSortResult<DbLogViewBean> getDbLogDataList(DbLogPageRequestBean pageRequestBody);

}
