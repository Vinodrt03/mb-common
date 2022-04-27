package com.milkbasket.rest.services.push.common.service;

import java.util.List;
import java.util.Map;

import com.milkbasket.rest.services.push.common.bean.DeviceBean;
import com.milkbasket.rest.services.push.common.bean.UnregisterDevice;
import com.milkbasket.rest.services.communication.entity.DeviceEntity;

/**
 * The Interface DeviceService.
 *
 * @author Neeraj
 * @version $Id: $Id
 */
public interface DeviceService {

	/** Constant <code>SERVICE="Device"</code> */
	String SERVICE = "Device";

	/**
	 * Service method for finding Device entity based on 'id'
	 *
	 * @param id
	 *            the id
	 * @return the Device entity
	 */
	DeviceEntity find(Long id);

	/**
	 * Service method for saving Device entity
	 *
	 * @param entity
	 *            a
	 *            {@link com.milkbasket.rest.services.push.common.entity.DeviceEntity}
	 *            object.
	 * @return the Device entity
	 */
	DeviceEntity save(DeviceEntity entity);

	void saveAll(List<DeviceEntity> entityList);

	/**
	 * Service method for updating Device entity
	 *
	 * @return the Device entity
	 * @param entity
	 *            a
	 *            {@link com.milkbasket.rest.services.push.common.entity.DeviceEntity}
	 *            object.
	 */
	DeviceEntity update(DeviceEntity entity);

	/**
	 * Service method for finding Device entities based on defined field.
	 *
	 * @param filters
	 *            Map with filter key and value
	 * @return the list of Device entities
	 */
	List<DeviceEntity> findAll(Map<String, Object> filters);

	List<DeviceEntity> findAllWithoutAnyLimit(Map<String, Object> filters);

	/**
	 * Find all Device entities including soft deleted rows.
	 *
	 * @param filters
	 *            Map with filter key and value
	 * @return the list of all Device entities (including soft delted).
	 */
	List<DeviceEntity> findAllWithDeleted(Map<String, Object> filters);

	/**
	 * Service method for deleting Device entity based on 'id'
	 *
	 * @param id
	 *            the id
	 */
	void delete(Long id);

	/**
	 * Delete by id method for deleting Device entity
	 *
	 * @param id
	 *            the id
	 */
	void deleteById(Long id);

	Long unregisterDevice(UnregisterDevice device, boolean logoutFromAllDevices);

	DeviceEntity registerDevice(DeviceBean deviceBean);

	boolean isvalidPushUser(Long userId);

}
