package com.milkbasket.rest.services.push.common.repository;

import org.springframework.stereotype.Repository;

import com.milkbasket.core.framework.dbsupport.crud.BaseCrudRepository;
import com.milkbasket.rest.services.communication.entity.DeviceEntity;

/**
 * The Interface DeviceRepository.
 *
 * @author Neeraj
 * @version $Id: $Id
 */
@Repository
public interface DeviceRepository extends BaseCrudRepository<DeviceEntity, Long> {

}
