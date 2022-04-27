package com.milkbasket.rest.services.param.test.repository;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.core.framework.test.BasicTestConfiguration;
import com.milkbasket.rest.services.param.report.bean.ParamFilterBean;
import com.milkbasket.rest.services.param.report.bean.ParamPageRequestBean;
import com.milkbasket.rest.services.param.report.bean.ParamSortBean;
import com.milkbasket.rest.services.param.report.bean.ParamViewBean;
import com.milkbasket.rest.services.param.report.repository.ParamReportRepository;

public class ParamReportRepositoryTest extends BasicTestConfiguration {
	@Autowired
	private ParamReportRepository paramReportRepository;

	@Test(priority = 0)
	public void test_Repository() {
		assertNotNull(paramReportRepository);
	}

	@Test(priority = 102)
	public void test_getParamDataList_2() {

		final ParamFilterBean filterBean = new ParamFilterBean();
		// filterBean.setParamKey("LAUNCH_DATE");

		final ParamSortBean sort = new ParamSortBean();
		final ParamPageRequestBean pageRequestBody = new ParamPageRequestBean();
		pageRequestBody.setPageNo(1);
		pageRequestBody.setPageSize(30);
		pageRequestBody.setFilters(filterBean);
		pageRequestBody.setSort(sort);

		final PageAndSortResult<ParamViewBean> results = paramReportRepository.getParamDataList(pageRequestBody);

		assertNotNull(results);
		assertEquals(results.getPageNo().intValue(), 1);
		assertEquals(results.getPageSize().intValue(), 30);
	}
}
