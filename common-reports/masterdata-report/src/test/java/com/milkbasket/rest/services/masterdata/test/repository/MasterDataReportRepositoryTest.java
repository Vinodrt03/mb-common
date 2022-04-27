package com.milkbasket.rest.services.masterdata.test.repository;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.core.framework.test.BasicTestConfiguration;
import com.milkbasket.rest.services.masterdata.report.bean.MasterDataFilterBean;
import com.milkbasket.rest.services.masterdata.report.bean.MasterDataPageRequestBean;
import com.milkbasket.rest.services.masterdata.report.bean.MasterDataSortBean;
import com.milkbasket.rest.services.masterdata.report.bean.MasterDataViewBean;
import com.milkbasket.rest.services.masterdata.report.repository.MasterDataReportRepository;

public class MasterDataReportRepositoryTest extends BasicTestConfiguration {
	@Autowired
	private MasterDataReportRepository masterDataReportRepository;

	@Test(priority = 0)
	public void test_Repository() {
		assertNotNull(masterDataReportRepository);
	}

	@Test(priority = 5)
	public void test_getMasterDataDataList_default() {
		final MasterDataPageRequestBean pageRequestBody = new MasterDataPageRequestBean();
		final MasterDataFilterBean filter = new MasterDataFilterBean();
		pageRequestBody.setFilters(filter);
		final PageAndSortResult<MasterDataViewBean> results = masterDataReportRepository.getMasterDataDataList(pageRequestBody);
		assertNotNull(results);
	}

	@Test(priority = 102)
	public void test_getMasterDataDataList_2() {

		final MasterDataFilterBean filterBean = new MasterDataFilterBean();
		filterBean.setMasterKey("Test");

		final MasterDataSortBean sort = new MasterDataSortBean();
		// sort.setId(SortDirection.DESC);
		final MasterDataPageRequestBean pageRequestBody = new MasterDataPageRequestBean();
		pageRequestBody.setPageNo(1);
		pageRequestBody.setPageSize(30);
		pageRequestBody.setFilters(filterBean);
		pageRequestBody.setSort(sort);

		final PageAndSortResult<MasterDataViewBean> results = masterDataReportRepository.getMasterDataDataList(pageRequestBody);

		assertNotNull(results);
		assertEquals(results.getPageNo().intValue(), 1);
		assertEquals(results.getPageSize().intValue(), 30);
	}
}
