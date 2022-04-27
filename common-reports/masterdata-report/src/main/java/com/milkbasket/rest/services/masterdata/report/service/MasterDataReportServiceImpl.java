package com.milkbasket.rest.services.masterdata.report.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.rest.services.masterdata.report.bean.DataKeyValue;
import com.milkbasket.rest.services.masterdata.report.bean.MasterDataPageRequestBean;
import com.milkbasket.rest.services.masterdata.report.bean.MasterDataViewBean;
import com.milkbasket.rest.services.masterdata.report.repository.MasterDataReportRepository;

@Service
/**
 * <p>
 * MasterDataReportServiceImpl class.
 * </p>
 *
 * @author Neeraj
 * @version $Id: $Id
 */
public class MasterDataReportServiceImpl implements MasterDataReportService {

	@Autowired
	private MasterDataReportRepository masterDataReportRepository;

	/** {@inheritDoc} */
	@Override
	public PageAndSortResult<MasterDataViewBean> findMasterDataDataList(final MasterDataPageRequestBean pageRequestBean) {

		final PageAndSortResult<MasterDataViewBean> result = masterDataReportRepository.getMasterDataDataList(pageRequestBean);
		setMdKeyValue(result);
		return result;
	}

	/** {@inheritDoc} */
	@Override
	public PageAndSortResult<MasterDataViewBean> exportMasterDataDataList(final MasterDataPageRequestBean pageRequestBean) {

		final PageAndSortResult<MasterDataViewBean> result = masterDataReportRepository.getMasterDataDataList(pageRequestBean);
		setMdKeyValue(result);
		return result;
	}

	private void setMdKeyValue(final PageAndSortResult<MasterDataViewBean> result) {
		final List<MasterDataViewBean> masterDataViewBeans = result.getData();
		final List<String> mdKeys = new ArrayList<>();
		masterDataViewBeans.forEach(masterDataViewBean -> mdKeys.add(masterDataViewBean.getMasterKey()));
		final List<DataKeyValue> dataKeyValues = masterDataReportRepository.getDataKeyValue(mdKeys);

		final Map<String, List<DataKeyValue>> dkMap = dataKeyValues.stream().collect(Collectors.groupingBy(DataKeyValue::getMasterKey));

		masterDataViewBeans.forEach(masterData -> masterData.setDataKeyValue(dkMap.get(masterData.getMasterKey())));
	}

}
