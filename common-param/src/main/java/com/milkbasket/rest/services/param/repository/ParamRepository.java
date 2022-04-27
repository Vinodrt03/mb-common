package com.milkbasket.rest.services.param.repository;

import org.springframework.stereotype.Repository;

import com.milkbasket.core.framework.dbsupport.crud.BaseCrudRepository;
import com.milkbasket.rest.services.param.entity.ParamEntity;

@Repository
/**
 * <p>
 * ParamRepository interface.
 * </p>
 *
 * @author tanuja
 * @version $Id: $Id
 */
public interface ParamRepository extends BaseCrudRepository<ParamEntity, Long> {

}
