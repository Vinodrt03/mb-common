package com.milkbasket.rest.services.push.common.mapper;

import org.springframework.stereotype.Component;

import com.milkbasket.rest.services.entity.base.handler.lib.EnumToStringTypeHandler;
import com.milkbasket.rest.services.entity.base.mapping.AnnotationConfig;
import com.milkbasket.rest.services.entity.base.mapping.BaseMapper;
import com.milkbasket.rest.services.entity.base.mapping.MappingConfig;
import com.milkbasket.rest.services.push.common.bean.DeviceBean;
import com.milkbasket.rest.services.communication.entity.DeviceEntity;
import com.milkbasket.rest.services.communication.entity.DeviceConstants;

@Component
/**
 * <p>
 * DeviceMapper class.
 * </p>
 *
 * @author Neeraj
 * @version $Id: $Id
 */
public class DeviceMapper extends BaseMapper<DeviceEntity, DeviceBean> {

	/** {@inheritDoc} */
	@Override
	public MappingConfig getMappingConfig() {
		final MappingConfig mappingConfig = getCommonFieldsMap(DeviceEntity.class);
		mappingConfig.put("deviceOs", "deviceOs", new EnumToStringTypeHandler<>(DeviceConstants.Device.AND));

		return mappingConfig;
	}

	/** {@inheritDoc} */
	@Override
	public AnnotationConfig getAnnotationConfig() {
		return super.getAnnotationsMap(DeviceEntity.class);
	}

	/** {@inheritDoc} */
	@Override
	public Class<DeviceEntity> getEntityType() {
		return DeviceEntity.class;
	}

	/** {@inheritDoc} */
	@Override
	public DeviceEntity buildEntity(final DeviceEntity entity, final DeviceBean bean) {
		populateEntity(entity, bean);

		entity.setDeviceOs(DeviceConstants.Device.getValue(bean.getDeviceOs()));

		return entity;
	}
}
