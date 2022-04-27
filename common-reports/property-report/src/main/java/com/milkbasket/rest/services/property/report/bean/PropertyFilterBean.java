package com.milkbasket.rest.services.property.report.bean;

import com.milkbasket.core.framework.validator.annotation.MinLength;
import com.milkbasket.core.framework.websupport.base.bean.FilterBean;

/**
 * <p>
 * PropertyFilterBean class.
 * </p>
 *
 * @author Tanuja
 * @version $Id: $Id
 */
public class PropertyFilterBean extends FilterBean {

	private static final long serialVersionUID = 201901241745570885L;

	@MinLength(length = 1)
	private String propertyKey;

	/**
	 * <p>
	 * Setter for the field <code>propertyKey</code>.
	 * </p>
	 *
	 * @property propertyKey of v property.
	 * @param propertyKey
	 *            a {@link java.lang.String} object.
	 */
	public void setPropertyKey(final String propertyKey) {
		this.propertyKey = propertyKey;
	}

	/**
	 * <p>
	 * Getter for the field <code>propertyKey</code>.
	 * </p>
	 *
	 * @return propertyKey of v property.
	 */
	public String getPropertyKey() {
		return propertyKey;
	}

}
