package com.milkbasket.rest.services.masterdata.test.service;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.core.framework.test.BasicTestConfiguration;
import com.milkbasket.rest.services.masterdata.report.bean.MasterDataFilterBean;
import com.milkbasket.rest.services.masterdata.report.bean.MasterDataPageRequestBean;
import com.milkbasket.rest.services.masterdata.report.bean.MasterDataViewBean;
import com.milkbasket.rest.services.masterdata.report.service.MasterDataReportService;

public class MasterDataReportServiceTest extends BasicTestConfiguration {

	@Autowired
	private MasterDataReportService masterDataReportService;

	@Test(priority = 0)
	public void test_serviceNotNull() {
		assertNotNull(masterDataReportService);
	}

	@Test(priority = 10)
	public void test_exportMasterDataDataList() {
		final MasterDataPageRequestBean request = new MasterDataPageRequestBean();
		request.setPageNo(1);
		request.setPageSize(10);

		final MasterDataFilterBean filter = new MasterDataFilterBean();
		request.setFilters(filter);
		// TODO change valeus in request

		final PageAndSortResult<MasterDataViewBean> results = masterDataReportService.exportMasterDataDataList(request);

		assertNotNull(results);
		assertEquals(results.getPageNo().intValue(), 1);
		assertEquals(results.getPageSize().intValue(), 10);
	}

	@Test(priority = 15)
	public void test_findMasterDataDataList() {
		final MasterDataPageRequestBean request = new MasterDataPageRequestBean();
		request.setPageNo(1);
		request.setPageSize(10);
		// TODO change valeus in request
		final MasterDataFilterBean filter = new MasterDataFilterBean();
		request.setFilters(filter);

		final PageAndSortResult<MasterDataViewBean> results = masterDataReportService.findMasterDataDataList(request);

		assertNotNull(results);
		assertEquals(results.getPageNo().intValue(), 1);
		assertEquals(results.getPageSize().intValue(), 10);
	}

}
