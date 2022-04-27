package com.milkbasket.rest.services.template.report.bean;

import com.milkbasket.core.framework.common.constants.CommonConstants;
import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection;
import com.milkbasket.core.framework.property.utils.PropertiesUtils;
import com.milkbasket.core.framework.websupport.base.bean.PageAndSortRequestBean;

/**
 * <p>
 * TemplateBasePageRequestBean class.
 * </p>
 *
 * @author Sanjay Wadhwa
 * @version $Id: $Id
 */
public class TemplateBasePageRequestBean extends PageAndSortRequestBean<TemplateBaseFilterBean, TemplateSortBean> {

	private static final long serialVersionUID = 201806131252180610L;

	private static final TemplateSortBean DEFAULT_EXPORT_SORT = getDefaultExportSort();

	private static final TemplateSortBean DEFAULT_DATALIST_SORT = getDefaultDataListSort();

	private static TemplateSortBean getDefaultExportSort() {
		final TemplateSortBean defaultExportSort = new TemplateSortBean();
		defaultExportSort.setModifiedOn(SortDirection.DESC);
		return defaultExportSort;
	}

	private static TemplateSortBean getDefaultDataListSort() {
		final TemplateSortBean defaultExportSort = new TemplateSortBean();
		defaultExportSort.setName(SortDirection.ASC);
		return defaultExportSort;
	}

	/**
	 * <p>
	 * createExportRequest.
	 * </p>
	 *
	 * @param filter
	 *            a
	 *            {@link com.milkbasket.rest.services.template.report.bean.TemplateBaseFilterBean}
	 *            object.
	 * @return a
	 *         {@link com.milkbasket.rest.services.template.report.bean.TemplateBasePageRequestBean}
	 *         object.
	 */
	public static TemplateBasePageRequestBean createExportRequest(final TemplateBaseFilterBean filter) {
		final TemplateBasePageRequestBean request = new TemplateBasePageRequestBean();
		request.setPageNo(1);
		request.setPageSize(PropertiesUtils.getIntegerProperty(CommonConstants.MAX_RECORD_LIMIT_EXPORT, 10000));
		request.setFilters(filter == null ? new TemplateFilterBean() : filter);
		request.setSort(DEFAULT_EXPORT_SORT);
		return request;
	}

	/**
	 * <p>
	 * createDatalistRequest.
	 * </p>
	 *
	 * @return a
	 *         {@link com.milkbasket.rest.services.template.report.bean.TemplateBasePageRequestBean}
	 *         object.
	 * @param request
	 *            a
	 *            {@link com.milkbasket.rest.services.template.report.bean.TemplateBasePageRequestBean}
	 *            object.
	 */
	public static TemplateBasePageRequestBean createDatalistRequest(TemplateBasePageRequestBean request) {

		if (request == null) {
			request = new TemplateBasePageRequestBean();
		}
		if (request.getFilters() == null) {
			request.setFilters(new TemplateBaseFilterBean());
		}
		request.getFilterCriteriaMap();
		if (!PageAndSortRequestBean.isSortSet(request)) {
			request.setSort(DEFAULT_DATALIST_SORT);
		}
		return request;

	}
}
