package com.milkbasket.rest.services.backofficelog.report.bean;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;

import com.milkbasket.core.framework.common.constants.CommonFormats;
import com.milkbasket.core.framework.dbsupport.jdbc.annotation.IgnoreCriteria;
import com.milkbasket.core.framework.validator.annotation.DateFormat;
import com.milkbasket.core.framework.validator.annotation.MaxLength;
import com.milkbasket.core.framework.validator.annotation.MinLength;
import com.milkbasket.core.framework.websupport.base.bean.FilterBean;

/**
 * Filter bean to fetch list of backoffice logs
 *
 * @author NitinKhaitan
 * @version $Id: $Id
 */
public class BackOfficeLogFilterBean extends FilterBean {

	private static final long serialVersionUID = 201806151645380996L;

	@Min(1)
	private Long userId;

	@Min(1)
	private Long productId;

	@Min(1)
	private Long hubId;

	@DateFormat(pattern = CommonFormats.DATE_FORMAT)
	private String startDate;

	@DateFormat(pattern = CommonFormats.DATE_FORMAT)
	private String endDate;

	@MinLength(length = 1)
	private String action;

	@MaxLength(length = 100)
	@MinLength(length = 5)
	@Email(regexp = CommonFormats.EMAIL_FORMAT)
	private String email;

	private Long customerId;

	@IgnoreCriteria
	private Integer newDb;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(final Long customerId) {
		this.customerId = customerId;
	}

	/**
	 * <p>
	 * Setter for the field <code>email</code>.
	 * </p>
	 *
	 * @param email
	 *            of backOfficeLog.
	 */
	public void setEmail(final String email) {
		this.email = email;
	}

	/**
	 * <p>
	 * Getter for the field <code>email</code>.
	 * </p>
	 *
	 * @return Email of backOfficeLog
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * <p>
	 * Setter for the field <code>userId</code>.
	 * </p>
	 *
	 * @param userId
	 *            of backOfficeLog.
	 */
	public void setUserId(final Long userId) {
		this.userId = userId;
	}

	/**
	 * <p>
	 * Getter for the field <code>userId</code>.
	 * </p>
	 *
	 * @return userId of backOfficeLog.
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * <p>
	 * Setter for the field <code>date</code>.
	 * </p>
	 *
	 * @param startDate
	 *            a {@link java.lang.String} object.
	 */
	public void setStartDate(final String startDate) {
		this.startDate = startDate;
	}

	/**
	 * <p>
	 * Getter for the field <code>Start Date</code>.
	 * </p>
	 *
	 * @return start date of backOfficeLog.
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * <p>
	 * Getter for the field <code>endDate</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * <p>
	 * Setter for the field <code>endDate</code>.
	 * </p>
	 *
	 * @param endDate
	 *            a {@link java.lang.String} object.
	 */
	public void setEndDate(final String endDate) {
		this.endDate = endDate;
	}

	/**
	 * <p>
	 * Setter for the field <code>action</code>.
	 * </p>
	 *
	 * @param action
	 *            of backOfficeLog.
	 */
	public void setAction(final String action) {
		this.action = action;
	}

	/**
	 * <p>
	 * Getter for the field <code>action</code>.
	 * </p>
	 *
	 * @return action of backOfficeLog.
	 */
	public String getAction() {
		return action;
	}

	/**
	 * <p>
	 * Getter for the field <code>productId</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.Long} object.
	 */
	public Long getProductId() {
		return productId;
	}

	/**
	 * <p>
	 * Setter for the field <code>productId</code>.
	 * </p>
	 *
	 * @param productId
	 *            a {@link java.lang.Long} object.
	 */
	public void setProductId(final Long productId) {
		this.productId = productId;
	}

	/**
	 * <p>
	 * Getter for the field <code>hubId</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.Long} object.
	 */
	public Long getHubId() {
		return hubId;
	}

	/**
	 * <p>
	 * Setter for the field <code>hubId</code>.
	 * </p>
	 *
	 * @param hubId
	 *            a {@link java.lang.Long} object.
	 */
	public void setHubId(final Long hubId) {
		this.hubId = hubId;
	}

	public Integer getNewDb() {
		return newDb;
	}

	public void setNewDb(final Integer newDb) {
		this.newDb = newDb;
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

		if (getUserId() != null) {
			logInfo.put("User ID", getUserId());
		}

		if (getProductId() != null) {
			logInfo.put("Product ID", getProductId());
		}

		if (getHubId() != null) {
			logInfo.put("Hub ID", getHubId());
		}

		if (getStartDate() != null) {
			logInfo.put("Start Date", getStartDate());
		}
		if (getEndDate() != null) {
			logInfo.put("End Date", getEndDate());
		}
		if (getAction() != null) {
			logInfo.put("Action", getAction());
		}
		if (getEmail() != null) {
			logInfo.put("Email", getEmail());
		}
		if (getNewDb() != null) {
			logInfo.put("NewDb", getNewDb());
		}
		logInfo.put("Record Count", total != null ? total : 0);
		return logInfo;
	}

}
