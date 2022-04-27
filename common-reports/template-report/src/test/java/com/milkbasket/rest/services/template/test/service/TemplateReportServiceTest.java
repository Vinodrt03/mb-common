package com.milkbasket.rest.services.template.test.service;

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
import com.milkbasket.rest.services.template.report.service.TemplateReportService;

public class TemplateReportServiceTest extends BasicTestConfiguration {

	@Autowired
	private TemplateReportService templateReportService;

	@Test(priority = 0)
	public void test_serviceNotNull() {
		assertNotNull(templateReportService);
	}

	@Test(priority = 10)
	public void test_exportTemplateDataList() {
		TemplatePageRequestBean request = new TemplatePageRequestBean();
		request.setPageNo(1);
		request.setPageSize(10);

		TemplateFilterBean filter = new TemplateFilterBean();

		filter.setActive(1);
		filter.setManual(1);
		filter.setModule("");
		filter.setType("EMAIL");
		request.setFilters(filter);

		TemplateSortBean sort = new TemplateSortBean();
		sort.setActive(SortDirection.ASC);
		sort.setId(SortDirection.DESC);
		sort.setManual(SortDirection.ASC);
		sort.setModule(SortDirection.ASC);
		sort.setName(SortDirection.ASC);
		sort.setText(SortDirection.DESC);
		sort.setType(SortDirection.ASC);
		request.setSort(sort);

		PageAndSortResult<TemplateViewBean> results = templateReportService.exportTemplateDataList(request);

		assertNotNull(results);
		assertEquals(results.getPageNo().intValue(), 1);
		assertEquals(results.getPageSize().intValue(), 10);
	}

	@Test(priority = 15)
	public void test_findTemplateDataList() {
		TemplatePageRequestBean request = new TemplatePageRequestBean();
		request.setPageNo(1);
		request.setPageSize(10);

		TemplateFilterBean filter = new TemplateFilterBean();

		filter.setActive(1);
		filter.setManual(1);
		filter.setModule("");
		filter.setType("EMAIL");
		request.setFilters(filter);

		TemplateSortBean sort = new TemplateSortBean();
		sort.setActive(SortDirection.ASC);
		sort.setId(SortDirection.DESC);
		sort.setManual(SortDirection.ASC);
		sort.setModule(SortDirection.ASC);
		sort.setName(SortDirection.ASC);
		sort.setText(SortDirection.DESC);
		sort.setType(SortDirection.ASC);
		request.setSort(sort);

		PageAndSortResult<TemplateViewBean> results = templateReportService.findTemplateDataList(request);

		assertNotNull(results);
		assertEquals(results.getPageNo().intValue(), 1);
		assertEquals(results.getPageSize().intValue(), 10);
	}

	@Test(priority = 20)
	public void test_exportTemplateDataListStopped() {
		TemplateBasePageRequestBean request = new TemplateBasePageRequestBean();
		request.setPageNo(1);
		request.setPageSize(10);

		TemplateBaseFilterBean filter = new TemplateBaseFilterBean();

		filter.setManual(1);
		filter.setModule("");
		filter.setType("EMAIL");
		request.setFilters(filter);

		TemplateSortBean sort = new TemplateSortBean();
		sort.setActive(SortDirection.ASC);
		sort.setId(SortDirection.DESC);
		sort.setManual(SortDirection.ASC);
		sort.setModule(SortDirection.ASC);
		sort.setName(SortDirection.ASC);
		sort.setText(SortDirection.DESC);
		sort.setType(SortDirection.ASC);
		request.setSort(sort);

		PageAndSortResult<TemplateViewBean> results = templateReportService.exportTemplateDataListStopped(request);

		assertNotNull(results);
		assertEquals(results.getPageNo().intValue(), 1);
		assertEquals(results.getPageSize().intValue(), 10);
	}

	@Test(priority = 25)
	public void test_findTemplateDataListStopped() {
		TemplateBasePageRequestBean request = new TemplateBasePageRequestBean();
		request.setPageNo(1);
		request.setPageSize(10);

		TemplateBaseFilterBean filter = new TemplateBaseFilterBean();

		filter.setManual(1);
		filter.setModule("");
		filter.setType("EMAIL");
		request.setFilters(filter);

		TemplateSortBean sort = new TemplateSortBean();
		sort.setActive(SortDirection.ASC);
		sort.setId(SortDirection.DESC);
		sort.setManual(SortDirection.ASC);
		sort.setModule(SortDirection.ASC);
		sort.setName(SortDirection.ASC);
		sort.setText(SortDirection.DESC);
		sort.setType(SortDirection.ASC);
		request.setSort(sort);

		PageAndSortResult<TemplateViewBean> results = templateReportService.findTemplateDataListStopped(request);

		assertNotNull(results);
		assertEquals(results.getPageNo().intValue(), 1);
		assertEquals(results.getPageSize().intValue(), 10);
	}

}
