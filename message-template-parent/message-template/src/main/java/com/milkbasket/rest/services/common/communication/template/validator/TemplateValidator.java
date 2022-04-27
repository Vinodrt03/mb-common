/*
 * @author Sushant
 * Copyright milkbasket.com
 */
package com.milkbasket.rest.services.common.communication.template.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.milkbasket.core.framework.common.properties.Errors;
import com.milkbasket.core.framework.utility.StringUtils;
import com.milkbasket.rest.services.communication.entity.TemplateEntity;
import com.milkbasket.rest.services.common.communication.template.bean.TemplateBean;
import com.milkbasket.rest.services.common.communication.template.service.TemplateService;

/**
 * The Class TemplateValidator.
 *
 * @author SanjeevJha
 * @version $Id: $Id
 */
@Component
public class TemplateValidator {

	/** The service. */
	@Autowired
	private TemplateService service;

	/**
	 * Validate template.
	 *
	 * @param bean
	 *            the bean
	 * @param errors
	 *            the errors
	 */
	public void validateTemplate(final TemplateBean bean, final org.springframework.validation.Errors errors) {
		if (!errors.hasErrors()) {
			if (TemplateEntity.Type.SMS.toString().equalsIgnoreCase(bean.getType()) && bean.getSendType() == null) {
				errors.reject(Errors.MANDATORY, new Object[] { "Send Type is requried for SMS" }, null);
			}
			if (bean.getType().equals(TemplateEntity.Type.EMAIL.toString()) && StringUtils.isBlank(bean.getSubject())) {
				errors.reject(Errors.MANDATORY, new Object[] { "Subject is requried for EMAIL" }, null);
			}
			if (!service.isUniqueTemplate(bean.getId(), bean.getType(), bean.getName())) {
				errors.rejectValue("name", Errors.UNIQUE_VALUE);
			}
			/*
			 * if (!service.isUniqueName(bean.getId(), bean.getName())) {
			 * errors.rejectValue("name", Errors.UNIQUE_VALUE); }
			 */
		}

	}

}
