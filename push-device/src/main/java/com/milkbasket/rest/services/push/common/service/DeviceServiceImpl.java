package com.milkbasket.rest.services.push.common.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milkbasket.core.framework.common.utils.LoggingUtils;
import com.milkbasket.core.framework.dbsupport.crud.BaseCrudRepository;
import com.milkbasket.core.framework.dbsupport.jdbc.constants.BooleanFlag;
import com.milkbasket.core.framework.utility.CollectionUtils;
import com.milkbasket.core.framework.websupport.base.BaseService;
import com.milkbasket.rest.services.push.common.bean.DeviceBean;
import com.milkbasket.rest.services.push.common.bean.UnregisterDevice;
import com.milkbasket.rest.services.communication.entity.DeviceEntity;
import com.milkbasket.rest.services.communication.entity.DeviceEntity_;
import com.milkbasket.rest.services.push.common.repository.DeviceRepository;

/**
 * The Class DeviceServiceImpl.
 *
 * @author Neeraj
 * @version $Id: $Id
 */
@Service
public class DeviceServiceImpl implements DeviceService, BaseService<DeviceEntity> {

	// private static final AppLogger _LOGGER =
	// LoggingManager.getLogger(DeviceServiceImpl.class);

	/** The device repository. */
	@Autowired
	private DeviceRepository deviceRepository;

	/** {@inheritDoc} */
	@Override
	public DeviceEntity find(final Long id) {
		return findRecordById(id);
	}

	/** {@inheritDoc} */
	@Override
	public DeviceEntity save(final DeviceEntity entity) {
		deviceRepository.save(entity);
		return entity;
	}

	/** {@inheritDoc} */
	@Override
	public void saveAll(final List<DeviceEntity> entityList) {
		deviceRepository.saveAll(entityList);
	}

	@Override
	public DeviceEntity registerDevice(final DeviceBean deviceBean) {
		DeviceEntity device = getExistingDevice(deviceBean);
		if (device == null) {
			device = copy(deviceBean, DeviceEntity.newInstance());
			device.setDeviceOs(deviceBean.getDeviceOs().name());
			LoggingUtils.logAppAction("register_push", "Device OS", deviceBean.getDeviceOs().name(), "Push ID", deviceBean.getPushId(), "UDID",
					deviceBean.getUdid());
		} else if (null != deviceBean.getPushId()) {
			device.setPushId(deviceBean.getPushId());
			LoggingUtils.logAppAction("register_push_update", "Device OS", deviceBean.getDeviceOs().name(), "Push ID", deviceBean.getPushId(), "UDID",
					deviceBean.getUdid());
		}
		device.setDeviceLogout(BooleanFlag.NO);
		save(device);
		return device;
	}

	private DeviceEntity getExistingDevice(final DeviceBean deviceBean) {
		final Map<String, Object> filter = new HashMap<>();
		filter.put(DeviceEntity_.customerId.getName(), deviceBean.getCustomerId());
		filter.put(DeviceEntity_.udid.getName(), deviceBean.getUdid());
		filter.put(DeviceEntity_.deviceOs.getName(), deviceBean.getDeviceOs().name());

		final List<DeviceEntity> deviceEntryList = findAll(filter);
		return CollectionUtils.isNotEmpty(deviceEntryList) ? deviceEntryList.get(0) : null;
	}

	@Override
	public Long unregisterDevice(final UnregisterDevice device, final boolean logoutFromAllDevices) {
		final Map<String, Object> filter = new HashMap<>();
		filter.put(DeviceEntity_.customerId.getName(), device.getCustomerId());
		if (!logoutFromAllDevices) {
			filter.put(DeviceEntity_.udid.getName(), device.getUdid());
		}

		final List<DeviceEntity> deviceEntryList = findAll(filter);
		if (CollectionUtils.isNotEmpty(deviceEntryList)) {
			deviceEntryList.stream().forEach(deviceEntry -> deviceEntry.setDeviceLogout(BooleanFlag.YES));
			saveAll(deviceEntryList);
		}
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public DeviceEntity update(final DeviceEntity entity) {
		deviceRepository.save(entity);
		return entity;
	}

	/** {@inheritDoc} */
	@Override
	public List<DeviceEntity> findAll(final Map<String, Object> filters) {
		return findRecords(filters);
	}

	/** {@inheritDoc} */
	@Override
	public List<DeviceEntity> findAllWithoutAnyLimit(final Map<String, Object> filters) {
		return findRecordsWithoutAnyLimit(filters);
	}

	/** {@inheritDoc} */
	@Override
	public boolean isvalidPushUser(final Long userId) {
		final Map<String, Object> devicefilters = new HashMap<>();
		devicefilters.put(DeviceEntity_.customerId.getName(), userId);
		devicefilters.put(DeviceEntity_.deviceLogout.getName(), 0);
		final Long cnt = deviceRepository.count(devicefilters);
		return cnt != null && cnt.longValue() > 0;
	}

	/** {@inheritDoc} */
	@Override
	public void delete(final Long id) {
		softDeleteRecord(id);
	}

	/** {@inheritDoc} */
	@Override
	public void deleteById(final Long id) {
		deviceRepository.deleteById(id);
	}

	/** {@inheritDoc} */
	@Override
	public List<DeviceEntity> findAllWithDeleted(final Map<String, Object> filters) {
		return findAllRecords(filters);
	}

	/** {@inheritDoc} */
	@Override
	public BaseCrudRepository<DeviceEntity, Long> getRepository() {
		return deviceRepository;
	}

	/** {@inheritDoc} */
	@Override
	public Class<DeviceEntity> getEntity() {
		return DeviceEntity.class;
	}

}
