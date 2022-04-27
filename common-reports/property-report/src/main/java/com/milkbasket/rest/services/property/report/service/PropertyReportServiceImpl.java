package com.milkbasket.rest.services.property.report.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milkbasket.core.framework.dbsupport.jdbc.pagination.PageAndSortResult;
import com.milkbasket.rest.services.property.report.bean.PropertyPageRequestBean;
import com.milkbasket.rest.services.property.report.bean.PropertyViewBean;
import com.milkbasket.rest.services.property.report.repository.PropertyReportRepository;

@Service
/**
 * <p>
 * PropertyReportServiceImpl class.
 * </p>
 *
 * @author Tanuja
 * @version $Id: $Id
 */
public class PropertyReportServiceImpl implements PropertyReportService {

	@Autowired
	private PropertyReportRepository propertyReportRepository;

	/** {@inheritDoc} */
	@Override
	public PageAndSortResult<PropertyViewBean> findPropertyDataList(final PropertyPageRequestBean pageRequestBean) {

		return propertyReportRepository.getPropertyDataList(pageRequestBean);
	}

}
