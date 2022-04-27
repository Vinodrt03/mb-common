package com.milkbasket.rest.services.masterdata.report.repository;

import java.util.List;

import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.core.framework.dbsupport.jdbc.repository.JdbcTemplateRepository;
import com.milkbasket.rest.services.masterdata.report.bean.DataKeyValue;
import com.milkbasket.rest.services.masterdata.report.bean.MasterDataPageRequestBean;
import com.milkbasket.rest.services.masterdata.report.bean.MasterDataViewBean;

/**
 * <p>
 * MasterDataReportRepository interface.
 * </p>
 *
 * @author Neeraj
 * @version $Id: $Id
 */
public interface MasterDataReportRepository extends JdbcTemplateRepository {

	/**
	 * <p>
	 * getMasterDataDataList.
	 * </p>
	 *
	 * @param pageRequestBody
	 *            a
	 *            {@link com.milkbasket.rest.services.masterdata.report.bean.MasterDataPageRequestBean}
	 *            object.
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult}
	 *         object.
	 */
	PageAndSortResult<MasterDataViewBean> getMasterDataDataList(MasterDataPageRequestBean pageRequestBody);

	/**
	 * <p>
	 * getDataKeyValue.
	 * </p>
	 *
	 * @param mdKeys
	 *            a {@link java.util.List} object.
	 * @return a {@link java.util.List} object.
	 */
	List<DataKeyValue> getDataKeyValue(List<String> mdKeys);

}
