package com.milkbasket.rest.services.param.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milkbasket.core.framework.common.bean.ErrorBean;
import com.milkbasket.core.framework.common.exception.NoDataException;
import com.milkbasket.core.framework.common.properties.Errors;
import com.milkbasket.rest.services.param.entity.ParamEntity;
import com.milkbasket.rest.services.param.repository.ParamRepository;

@Service
/**
 * <p>
 * ParamServiceImpl class.
 * </p>
 *
 * @author tanuja
 * @version $Id: $Id
 */
public class ParamServiceImpl implements ParamService {

	@Autowired
	private ParamRepository paramRepository;

	/** {@inheritDoc} */
	@Override
	public ParamEntity update(final ParamEntity entity) {
		paramRepository.save(entity);
		return entity;
	}

	/** {@inheritDoc} */
	@Override
	public ParamEntity find(final Long id) {
		final ParamEntity entity = paramRepository.getById(id);
		if (entity == null) {
			throw new NoDataException("No Data Found", ErrorBean.withError(Errors.NO_DATA_FOUND));
		}
		return entity;
	}
}
