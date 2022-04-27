package com.milkbasket.rest.services.template.report.bean;

import com.milkbasket.core.framework.common.constants.CommonConstants;
import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection;
import com.milkbasket.core.framework.property.utils.PropertiesUtils;
import com.milkbasket.core.framework.websupport.base.bean.PageAndSortRequestBean;

/**
 * <p>
 * TemplatePageRequestBean class.
 * </p>
 *
 * @author Sanjay Wadhwa
 * @version $Id: $Id
 */
public class TemplatePageRequestBean extends PageAndSortRequestBean<TemplateFilterBean, TemplateSortBean> {

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
	 *            {@link com.milkbasket.rest.services.template.report.bean.TemplateFilterBean}
	 *            object.
	 * @return a
	 *         {@link com.milkbasket.rest.services.template.report.bean.TemplatePageRequestBean}
	 *         object.
	 */
	public static TemplatePageRequestBean createExportRequest(final TemplateFilterBean filter) {
		final TemplatePageRequestBean request = new TemplatePageRequestBean();
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
	 *         {@link com.milkbasket.rest.services.template.report.bean.TemplatePageRequestBean}
	 *         object.
	 * @param request
	 *            a
	 *            {@link com.milkbasket.rest.services.template.report.bean.TemplatePageRequestBean}
	 *            object.
	 */
	public static TemplatePageRequestBean createDatalistRequest(TemplatePageRequestBean request) {

		if (request == null) {
			request = new TemplatePageRequestBean();
		}
		if (request.getFilters() == null) {
			request.setFilters(new TemplateFilterBean());
		}
		request.getFilterCriteriaMap();
		if (!PageAndSortRequestBean.isSortSet(request)) {
			request.setSort(DEFAULT_DATALIST_SORT);
		}
		return request;

	}
}
