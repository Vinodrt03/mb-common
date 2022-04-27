package com.milkbasket.rest.services.masterdata.report.bean;

import com.milkbasket.core.framework.common.constants.CommonConstants;
import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection;
import com.milkbasket.core.framework.property.utils.PropertiesUtils;
import com.milkbasket.core.framework.websupport.base.bean.PageAndSortRequestBean;

/**
 * <p>
 * MasterDataPageRequestBean class.
 * </p>
 *
 * @author Neeraj
 * @version $Id: $Id
 */
public class MasterDataPageRequestBean extends PageAndSortRequestBean<MasterDataFilterBean, MasterDataSortBean> {

	private static final long serialVersionUID = 201901162321010624L;

	private static final MasterDataSortBean DEFAULT_EXPORT_SORT = getDefaultExportSort();

	private static final MasterDataSortBean DEFAULT_DATALIST_SORT = getDefaultDataListSort();

	private static MasterDataSortBean getDefaultExportSort() {
		final MasterDataSortBean defaultExportSort = new MasterDataSortBean();
		defaultExportSort.setMasterKey(SortDirection.ASC);
		return defaultExportSort;
	}

	private static MasterDataSortBean getDefaultDataListSort() {
		final MasterDataSortBean defaultExportSort = new MasterDataSortBean();
		defaultExportSort.setMasterKey(SortDirection.ASC);
		return defaultExportSort;
	}

	/**
	 * <p>
	 * createExportRequest.
	 * </p>
	 *
	 * @param filter
	 *            a
	 *            {@link com.milkbasket.rest.services.masterdata.report.bean.MasterDataFilterBean}
	 *            object.
	 * @return a
	 *         {@link com.milkbasket.rest.services.masterdata.report.bean.MasterDataPageRequestBean}
	 *         object.
	 */
	public static MasterDataPageRequestBean createExportRequest(final MasterDataFilterBean filter) {
		final MasterDataPageRequestBean request = new MasterDataPageRequestBean();
		request.setPageNo(1);
		request.setPageSize(Integer.MAX_VALUE);
		request.setFilters(filter == null ? new MasterDataFilterBean() : filter);
		request.setSort(DEFAULT_EXPORT_SORT);
		return request;
	}

	/**
	 * <p>
	 * createDatalistRequest.
	 * </p>
	 *
	 * @param viewRequest
	 *            a
	 *            {@link com.milkbasket.rest.services.masterdata.report.bean.MasterDataPageRequestBean}
	 *            object.
	 * @return a
	 *         {@link com.milkbasket.rest.services.masterdata.report.bean.MasterDataPageRequestBean}
	 *         object.
	 */
	public static MasterDataPageRequestBean createDatalistRequest(final MasterDataPageRequestBean viewRequest) {
		final MasterDataPageRequestBean request = new MasterDataPageRequestBean();
		request.setPageNo(viewRequest == null || viewRequest.getPageNo() == null ? PropertiesUtils.getIntegerProperty(CommonConstants.DEFAULT_PAGE_NO)
				: viewRequest.getPageNo());
		request.setPageSize(
				viewRequest == null || viewRequest.getPageSize() == null ? PropertiesUtils.getIntegerProperty(CommonConstants.DEFAULT_PAGE_SIZE)
						: viewRequest.getPageSize());
		request.setFilters(viewRequest == null || viewRequest.getFilters() == null ? new MasterDataFilterBean() : viewRequest.getFilters());
		request.setSort(viewRequest == null || viewRequest.getSort() == null ? DEFAULT_DATALIST_SORT : viewRequest.getSort());
		return request;
	}

}
