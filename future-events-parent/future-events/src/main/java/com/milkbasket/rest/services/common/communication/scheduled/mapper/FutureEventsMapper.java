package com.milkbasket.rest.services.common.communication.scheduled.mapper;

import org.springframework.stereotype.Component;

import com.milkbasket.core.framework.common.constants.CommonFormats;
import com.milkbasket.rest.services.entity.base.handler.lib.DateToStringTypeHandler;
import com.milkbasket.rest.services.entity.base.mapping.AnnotationConfig;
import com.milkbasket.rest.services.entity.base.mapping.BaseMapper;
import com.milkbasket.rest.services.entity.base.mapping.MappingConfig;
import com.milkbasket.rest.services.communication.entity.FutureEventsEntity;

@Component
public class FutureEventsMapper extends BaseMapper<FutureEventsEntity, Object> {

	/** {@inheritDoc} */
	@Override
	public MappingConfig getMappingConfig() {
		final MappingConfig mappingConfig = getCommonFieldsMap(FutureEventsEntity.class);
		mappingConfig.put("dateTime", new DateToStringTypeHandler(CommonFormats.DATE_FORMAT_WITH_TIME));
		return mappingConfig;
	}

	/** {@inheritDoc} */
	@Override
	public AnnotationConfig getAnnotationConfig() {
		return super.getAnnotationsMap(FutureEventsEntity.class);
	}

	/** {@inheritDoc} */
	@Override
	public Class<FutureEventsEntity> getEntityType() {
		return FutureEventsEntity.class;
	}

}
