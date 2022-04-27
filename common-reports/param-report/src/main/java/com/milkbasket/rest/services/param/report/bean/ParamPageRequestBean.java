package com.milkbasket.rest.services.param.report.bean;

import com.milkbasket.core.framework.common.constants.CommonConstants;
import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection;
import com.milkbasket.core.framework.property.utils.PropertiesUtils;
import com.milkbasket.core.framework.websupport.base.bean.PageAndSortRequestBean;

/**
 * <p>
 * ParamPageRequestBean class.
 * </p>
 *
 * @author Neeraj
 * @version $Id: $Id
 */
public class ParamPageRequestBean extends PageAndSortRequestBean<ParamFilterBean, ParamSortBean> {

	private static final long serialVersionUID = 201901241745570886L;

	private static final ParamSortBean DEFAULT_DATALIST_SORT = getDefaultDataListSort();

	private static ParamSortBean getDefaultDataListSort() {
		final ParamSortBean defaultExportSort = new ParamSortBean();
		defaultExportSort.setParamKey(SortDirection.ASC);
		return defaultExportSort;
	}

	/**
	 * <p>
	 * createDatalistRequest.
	 * </p>
	 *
	 * @param viewRequest
	 *            a
	 *            {@link com.milkbasket.rest.services.param.report.bean.ParamPageRequestBean}
	 *            object.
	 * @return a
	 *         {@link com.milkbasket.rest.services.param.report.bean.ParamPageRequestBean}
	 *         object.
	 */
	public static ParamPageRequestBean createDatalistRequest(final ParamPageRequestBean viewRequest) {
		final ParamPageRequestBean request = new ParamPageRequestBean();
		request.setPageNo(viewRequest == null || viewRequest.getPageNo() == null ? PropertiesUtils.getIntegerProperty(CommonConstants.DEFAULT_PAGE_NO)
				: viewRequest.getPageNo());
		request.setPageSize(
				viewRequest == null || viewRequest.getPageSize() == null ? PropertiesUtils.getIntegerProperty(CommonConstants.DEFAULT_PAGE_SIZE)
						: viewRequest.getPageSize());
		request.setFilters(viewRequest == null || viewRequest.getFilters() == null ? new ParamFilterBean() : viewRequest.getFilters());
		request.setSort(viewRequest == null || viewRequest.getSort() == null ? DEFAULT_DATALIST_SORT : viewRequest.getSort());
		return request;
	}

}
