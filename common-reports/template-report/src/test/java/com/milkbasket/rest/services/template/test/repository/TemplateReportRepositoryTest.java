package com.milkbasket.rest.services.template.test.repository;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortRequest.SortDirection;
import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.core.framework.test.BasicTestConfiguration;
import com.milkbasket.rest.services.template.report.bean.TemplateBaseFilterBean;
import com.milkbasket.rest.services.template.report.bean.TemplateBasePageRequestBean;
import com.milkbasket.rest.services.template.report.bean.TemplateFilterBean;
import com.milkbasket.rest.services.template.report.bean.TemplatePageRequestBean;
import com.milkbasket.rest.services.template.report.bean.TemplateSortBean;
import com.milkbasket.rest.services.template.report.bean.TemplateViewBean;
import com.milkbasket.rest.services.template.report.repository.TemplateReportRepository;

public class TemplateReportRepositoryTest extends BasicTestConfiguration {
	@Autowired
	private TemplateReportRepository templateReportRepository;

	@Test(priority = 0)
	public void test_Repository() {
		assertNotNull(templateReportRepository);
	}

	@Test(priority = 10)
	public void test_getTemplateDataList() {
		TemplateFilterBean filter = new TemplateFilterBean();

		filter.setActive(1);
		filter.setManual(1);
		filter.setModule("");
		filter.setType("EMAIL");

		TemplateSortBean sort = new TemplateSortBean();
		sort.setActive(SortDirection.ASC);
		sort.setId(SortDirection.DESC);
		sort.setManual(SortDirection.ASC);
		sort.setModule(SortDirection.ASC);
		sort.setName(SortDirection.ASC);
		sort.setText(SortDirection.DESC);
		sort.setType(SortDirection.ASC);

		TemplatePageRequestBean pageRequestBody = new TemplatePageRequestBean();
		pageRequestBody.setPageNo(1);
		pageRequestBody.setPageSize(30);
		pageRequestBody.setFilters(filter);
		pageRequestBody.setSort(sort);

		PageAndSortResult<TemplateViewBean> results = templateReportRepository.getTemplateDataList(pageRequestBody);

		assertNotNull(results);
		assertEquals(results.getPageNo().intValue(), 1);
		assertEquals(results.getPageSize().intValue(), 30);
	}

	@Test(priority = 15)
	public void test_getTemplateDataListStopped() {

		TemplateBaseFilterBean filter = new TemplateBaseFilterBean();

		filter.setManual(1);
		filter.setModule("");
		filter.setType("EMAIL");

		TemplateSortBean sort = new TemplateSortBean();
		sort.setActive(SortDirection.ASC);
		sort.setId(SortDirection.DESC);
		sort.setManual(SortDirection.ASC);
		sort.setModule(SortDirection.ASC);
		sort.setName(SortDirection.ASC);
		sort.setText(SortDirection.DESC);
		sort.setType(SortDirection.ASC);

		TemplateBasePageRequestBean pageRequestBody = new TemplateBasePageRequestBean();
		pageRequestBody.setPageNo(1);
		pageRequestBody.setPageSize(30);
		pageRequestBody.setFilters(filter);
		pageRequestBody.setSort(sort);

		PageAndSortResult<TemplateViewBean> results = templateReportRepository.getTemplateDataListStopped(pageRequestBody);

		assertNotNull(results);
		assertEquals(results.getPageNo().intValue(), 1);
		assertEquals(results.getPageSize().intValue(), 30);
	}
}
