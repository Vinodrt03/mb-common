package com.milkbasket.rest.services.backofficelog.report.repository;

import org.springframework.data.domain.Page;

import com.milkbasket.core.framework.common.bean.log.AdminLog;
import com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogPageRequestBean;

/**
 * Filter bean to fetch list of backoffice logs from mongodb
 *
 * @author Rishab
 * @version $Id: $Id
 */
public interface BackOfficeLogReportMongoRepository {

	Page<AdminLog> getBackOfficeLogDataList(BackOfficeLogPageRequestBean pageRequestBody);

}
