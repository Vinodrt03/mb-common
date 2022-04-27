package com.milkbasket.rest.services.common.communication.template.mapper;

import org.springframework.stereotype.Component;

import com.milkbasket.rest.services.communication.entity.TemplateConstants;
import com.milkbasket.rest.services.communication.entity.TemplateEntity;
import com.milkbasket.rest.services.common.communication.template.bean.TemplateBean;
import com.milkbasket.rest.services.entity.base.handler.lib.EnumToStringTypeHandler;
import com.milkbasket.rest.services.entity.base.mapping.AnnotationConfig;
import com.milkbasket.rest.services.entity.base.mapping.BaseMapper;
import com.milkbasket.rest.services.entity.base.mapping.MappingConfig;

@Component
/**
 * <p>
 * TemplateMapper class.
 * </p>
 *
 * @author SanjeevJha
 * @version $Id: $Id
 */
public class TemplateMapper extends BaseMapper<TemplateEntity, TemplateBean> {
	/** {@inheritDoc} */
	@Override
	public MappingConfig getMappingConfig() {
		final MappingConfig mappingConfig = getCommonFieldsMap(TemplateEntity.class);
		mappingConfig.put("sendType", "sendType", new EnumToStringTypeHandler<>(TemplateConstants.SendType.PROMOTIONAL));
		return mappingConfig;
	}

	/** {@inheritDoc} */
	@Override
	public AnnotationConfig getAnnotationConfig() {
		return super.getAnnotationsMap(TemplateEntity.class);
	}

	/** {@inheritDoc} */
	@Override
	public Class<TemplateEntity> getEntityType() {
		return TemplateEntity.class;
	}
}
