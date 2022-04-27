package com.milkbasket.rest.services.backofficelog.report.service;

import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogPageRequestBean;
import com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogViewBean;

/**
 * Service to fetch list of backoffice logs from mongodb
 *
 * @author Rishab
 * @version $Id: $Id
 */
public interface BackOfficeLogReportMongoService {

	PageAndSortResult<BackOfficeLogViewBean> findBackOfficeLogDataList(BackOfficeLogPageRequestBean pageRequestBean);

	PageAndSortResult<BackOfficeLogViewBean> exportBackOfficeLogDataList(BackOfficeLogPageRequestBean pageRequestBean);

}
