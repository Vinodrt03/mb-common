package com.milkbasket.rest.services.param.report.bean;

import java.util.LinkedHashMap;
import java.util.Map;

import com.milkbasket.core.framework.validator.annotation.IsFlag;
import com.milkbasket.core.framework.validator.annotation.MinLength;
import com.milkbasket.core.framework.websupport.base.bean.FilterBean;

/**
 * <p>
 * ParamFilterBean class.
 * </p>
 *
 * @author Neeraj
 * @version $Id: $Id
 */
public class ParamFilterBean extends FilterBean {

	private static final long serialVersionUID = 201901241745570885L;

	@MinLength(length = 1)
	private String paramKey;

	@IsFlag
	private Integer isEditable;

	/**
	 * <p>
	 * Setter for the field <code>paramKey</code>.
	 * </p>
	 *
	 * @param paramKey
	 *            of v param.
	 */
	public void setParamKey(final String paramKey) {
		this.paramKey = paramKey;
	}

	/**
	 * <p>
	 * Getter for the field <code>paramKey</code>.
	 * </p>
	 *
	 * @return paramKey of v param.
	 */
	public String getParamKey() {
		return paramKey;
	}

	public Integer getIsEditable() {
		return isEditable;
	}

	public void setIsEditable(final Integer isEditable) {
		this.isEditable = isEditable;
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

		final Map<String, Object> logInfo = new LinkedHashMap<>();
		if (this.paramKey != null) {
			logInfo.put("Param Key", paramKey);
		}
		if (this.isEditable != null) {
			logInfo.put("is editable", isEditable);
		}
		logInfo.put("Record Count", total != null ? total : 0);
		return logInfo;
	}

}
