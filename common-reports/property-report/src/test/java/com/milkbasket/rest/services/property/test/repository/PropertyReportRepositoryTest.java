package com.milkbasket.rest.services.property.test.repository;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.core.framework.test.BasicTestConfiguration;
import com.milkbasket.rest.services.property.report.bean.PropertyFilterBean;
import com.milkbasket.rest.services.property.report.bean.PropertyPageRequestBean;
import com.milkbasket.rest.services.property.report.bean.PropertySortBean;
import com.milkbasket.rest.services.property.report.bean.PropertyViewBean;
import com.milkbasket.rest.services.property.report.repository.PropertyReportRepository;

public class PropertyReportRepositoryTest extends BasicTestConfiguration {
	@Autowired
	private PropertyReportRepository propertyReportRepository;

	@Test(priority = 0)
	public void test_Repository() {
		assertNotNull(propertyReportRepository);
	}

	@Test(priority = 102)
	public void test_getPropertyDataList_2() {

		PropertyFilterBean filterBean = new PropertyFilterBean();
		filterBean.setPropertyKey("Test");

		PropertySortBean sort = new PropertySortBean();
		PropertyPageRequestBean pageRequestBody = new PropertyPageRequestBean();
		pageRequestBody.setPageNo(1);
		pageRequestBody.setPageSize(30);
		pageRequestBody.setFilters(filterBean);
		pageRequestBody.setSort(sort);

		PageAndSortResult<PropertyViewBean> results = propertyReportRepository.getPropertyDataList(pageRequestBody);

		assertNotNull(results);
		assertEquals(results.getPageNo().intValue(), 1);
		assertEquals(results.getPageSize().intValue(), 30);
	}
}
