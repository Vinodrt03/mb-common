package com.milkbasket.rest.services.masterdata.report.service;

import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.rest.services.masterdata.report.bean.MasterDataPageRequestBean;
import com.milkbasket.rest.services.masterdata.report.bean.MasterDataViewBean;

/**
 * <p>
 * MasterDataReportService interface.
 * </p>
 *
 * @author Neeraj
 * @version $Id: $Id
 */
public interface MasterDataReportService {

	/**
	 * <p>
	 * findMasterDataDataList.
	 * </p>
	 *
	 * @param pageRequestBean
	 *            a
	 *            {@link com.milkbasket.rest.services.masterdata.report.bean.MasterDataPageRequestBean}
	 *            object.
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult}
	 *         object.
	 */
	PageAndSortResult<MasterDataViewBean> findMasterDataDataList(MasterDataPageRequestBean pageRequestBean);

	/**
	 * <p>
	 * exportMasterDataDataList.
	 * </p>
	 *
	 * @param pageRequestBean
	 *            a
	 *            {@link com.milkbasket.rest.services.masterdata.report.bean.MasterDataPageRequestBean}
	 *            object.
	 * @return a
	 *         {@link com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult}
	 *         object.
	 */
	PageAndSortResult<MasterDataViewBean> exportMasterDataDataList(MasterDataPageRequestBean pageRequestBean);

}
