package com.milkbasket.rest.services.template.report.bean;

import java.util.HashMap;
import java.util.Map;

import com.milkbasket.core.framework.validator.annotation.IsFlag;

/**
 * <p>
 * TemplateFilterBean class.
 * </p>
 *
 * @author Sanjay Wadhwa
 * @version $Id: $Id
 */
public class TemplateFilterBean extends TemplateBaseFilterBean {

	private static final long serialVersionUID = 201806131252180605L;

	@IsFlag
	private Integer active;

	/**
	 * <p>
	 * Setter for the field <code>active</code>.
	 * </p>
	 *
	 * @param active
	 *            a {@link java.lang.Integer} object.
	 */
	public void setActive(final Integer active) {
		this.active = active;
	}

	/**
	 * <p>
	 * Getter for the field <code>active</code>.
	 * </p>
	 *
	 * @return Active flag which is either 0(False) or 1(True).
	 */
	public Integer getActive() {
		return active;
	}

	/** {@inheritDoc} */
	@Override
	public Map<String, Object> logInfo(final Integer total) {

		final Map<String, Object> logInfo = new HashMap<>();

		if (getType() != null) {
			logInfo.put("Type", getType());
		}
		if (getManual() != null) {
			logInfo.put("Manual", getManual());
		}
		if (getModule() != null) {
			logInfo.put("Module", getModule());
		}
		if (this.active != null) {
			logInfo.put("Active", active);
		}
		logInfo.put("Record Count", total != null ? total : 0);
		return logInfo;
	}
}
