package com.milkbasket.rest.services.masterdata.report.bean;

import java.util.LinkedHashMap;
import java.util.Map;

import com.milkbasket.core.framework.validator.annotation.MinLength;
import com.milkbasket.core.framework.websupport.base.bean.FilterBean;

/**
 * <p>
 * MasterDataFilterBean class.
 * </p>
 *
 *
 * @author Neeraj
 * @version $Id: $Id
 */
public class MasterDataFilterBean extends FilterBean {

	private static final long serialVersionUID = 201901162321010623L;

	@MinLength(length = 1)
	private String masterKey;

	/**
	 * <p>
	 * Setter for the field <code>masterKey</code>.
	 * </p>
	 *
	 * @param masterKey
	 *            of v masterData.
	 */
	public void setMasterKey(final String masterKey) {
		this.masterKey = masterKey;
	}

	/**
	 * <p>
	 * Getter for the field <code>masterKey</code>.
	 * </p>
	 *
	 * @return masterKey of v masterData.
	 */
	public String getMasterKey() {
		return masterKey;
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
		if (this.masterKey != null) {
			logInfo.put("Master Key", masterKey);
		}
		logInfo.put("Record Count", total != null ? total : 0);
		return logInfo;
	}

}
