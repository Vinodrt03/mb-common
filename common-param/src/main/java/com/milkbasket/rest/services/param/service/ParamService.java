package com.milkbasket.rest.services.param.service;

import com.milkbasket.rest.services.param.entity.ParamEntity;

/**
 * <p>
 * ParamService interface.
 * </p>
 *
 * @author tanuja
 * @version $Id: $Id
 */
public interface ParamService {

	/**
	 * <p>
	 * find.
	 * </p>
	 *
	 * @param id
	 *            a {@link java.lang.Long} object.
	 * @return a {@link com.milkbasket.rest.services.param.entity.ParamEntity}
	 *         object.
	 */
	ParamEntity find(Long id);

	/**
	 * <p>
	 * update.
	 * </p>
	 *
	 * @param pre
	 *            a {@link com.milkbasket.rest.services.param.entity.ParamEntity}
	 *            object.
	 * @return a {@link com.milkbasket.rest.services.param.entity.ParamEntity}
	 *         object.
	 */
	ParamEntity update(ParamEntity pre);
}
