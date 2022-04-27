package com.milkbasket.rest.services.masterdata.report.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.milkbasket.core.framework.dbsupport.jdbc.constants.Operation;
import com.milkbasket.core.framework.dbsupport.jdbc.pagination.FilterCriteria;
import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.core.framework.dbsupport.jdbc.repository.JdbcTemplateRepositoryImpl;
import com.milkbasket.rest.services.masterdata.report.bean.DataKeyValue;
import com.milkbasket.rest.services.masterdata.report.bean.MasterDataPageRequestBean;
import com.milkbasket.rest.services.masterdata.report.bean.MasterDataViewBean;

@Component
/**
 * <p>
 * MasterDataReportRepositoryImpl class.
 * </p>
 *
 * @author Neeraj
 * @version $Id: $Id
 */
public class MasterDataReportRepositoryImpl extends JdbcTemplateRepositoryImpl implements MasterDataReportRepository {

	/** {@inheritDoc} */
	@Override
	public PageAndSortResult<MasterDataViewBean> getMasterDataDataList(final MasterDataPageRequestBean pageRequestBody) {

		final StringBuilder baseQuery = new StringBuilder("select * from v_masterData");
		pageRequestBody.replaceFilter("masterKey",
				new FilterCriteria("masterKey", like(pageRequestBody.getFilters().getMasterKey()), Operation.LIKE));

		return datalist(baseQuery, pageRequestBody.toPageRequest(), MasterDataViewBean.class);
	}

	/** {@inheritDoc} */
	@Override
	public List<DataKeyValue> getDataKeyValue(final List<String> mdKeys) {

		final List<Object> params = new ArrayList<>(mdKeys);
		final StringBuilder baseQuery = new StringBuilder("select * from v_masterDataInner");
		prepareInQuery(baseQuery, "masterKey", params);

		return records(baseQuery, params, DataKeyValue.class);
	}

}
