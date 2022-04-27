package com.milkbasket.rest.services.template.report.bean;

import java.util.HashMap;
import java.util.Map;

import com.milkbasket.core.framework.validator.annotation.MinLength;
import com.milkbasket.core.framework.websupport.base.bean.FilterBean;

/**
 * <p>
 * TemplateBaseFilterBean class.
 * </p>
 *
 * @author Sanjay Wadhwa
 * @version $Id: $Id
 */
public class TemplateBaseFilterBean extends FilterBean {

	private static final long serialVersionUID = 201806131252180605L;

	@MinLength(length = 1)
	private String type;

	private Integer manual;

	@MinLength(length = 1)
	private String module;

	/**
	 * <p>
	 * Setter for the field <code>type</code>.
	 * </p>
	 *
	 * @param type
	 *            a {@link java.lang.String} object.
	 */
	public void setType(final String type) {
		this.type = type;
	}

	/**
	 * <p>
	 * Getter for the field <code>type</code>.
	 * </p>
	 *
	 * @return Template Type. Accepts fixed values
	 */
	public String getType() {
		return type;
	}

	/**
	 * <p>
	 * Setter for the field <code>manual</code>.
	 * </p>
	 *
	 * @param manual
	 *            a {@link java.lang.Integer} object.
	 */
	public void setManual(final Integer manual) {
		this.manual = manual;
	}

	/**
	 * <p>
	 * Getter for the field <code>manual</code>.
	 * </p>
	 *
	 * @return Manual flag which is either 0(False) or 1(True)
	 */
	public Integer getManual() {
		return manual;
	}

	/**
	 * <p>
	 * Setter for the field <code>module</code>.
	 * </p>
	 *
	 * @param module
	 *            a {@link java.lang.String} object.
	 */
	public void setModule(final String module) {
		this.module = module;
	}

	/**
	 * <p>
	 * Getter for the field <code>module</code>.
	 * </p>
	 *
	 * @return Template Module. Required field. Accepts fixed values for Msg
	 *         Template Module
	 */
	public String getModule() {
		return module;
	}

	/**
	 * <p>
	 * logInfo.
	 * </p>
	 *
	 * @param total
	 *            a {@link java.lang.Integer} object.
	 * @return a {@link java.util.Map} object.
	 */
	public Map<String, Object> logInfo(final Integer total) {

		final Map<String, Object> logInfo = new HashMap<>();

		if (this.type != null) {
			logInfo.put("Type", type);
		}
		if (this.manual != null) {
			logInfo.put("Manual", manual);
		}
		if (this.module != null) {
			logInfo.put("Module", module);
		}
		logInfo.put("Record Count", total != null ? total : 0);
		return logInfo;
	}
}
