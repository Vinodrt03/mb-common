package com.milkbasket.rest.services.cache.test.handler;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Bean to be returned with VIEW
 *
 * @author iTuple
 * @version $Id: $Id
 */
public class ProfileTestBean implements Serializable {

	private static final long serialVersionUID = -340763450003218830L;

	private Long id;

	private String name;

	private Long hubId;

	private String mobile;

	@JsonProperty("country_code")
	private String countryCode;

	private String phone;

	private String email;

	@JsonProperty("created")
	private String createdOn;

	private String address;

	@JsonProperty("society_id")
	private Long societyId;

	@JsonProperty("society")
	private String societyName;

	@JsonProperty("society_active")
	private Integer societyActive;

	@JsonProperty("throttle_status")
	private Integer throttleStatus;

	@JsonProperty("inactivity_flag")
	private Integer inactivityFlag;

	@JsonIgnore
	private String lastCustomerActivity;

	@JsonProperty("delivery_start_date")
	private String deliveryStartDate;

	private String city;

	@JsonProperty("city_id")
	private Long cityId;

	private Integer paid;

	private Integer upi;

	@JsonProperty("is_sms")
	private Integer isSMS;

	@JsonProperty("is_email")
	private Integer isEmail;

	@JsonProperty("is_push")
	private Integer isPush;

	@JsonProperty("is_valid_address")
	private Integer isValidAddress;

	@JsonProperty("is_duplicate")
	private Integer isDuplicate;

	@JsonProperty("first_payment_date")
	private String firstPaymentDate;

	@JsonProperty("first_order_date")
	private String firstOrderDate;

	@JsonProperty("last_order_date")
	private String lastOrderDate;

	@JsonProperty("referrer_code")
	private String referrerCode;

	@JsonIgnore
	private Long referredBy;

	@JsonIgnore
	private Double cashback;

	@JsonIgnore
	private String referralExpiryDate;

	@JsonProperty("credit_limit")
	private Integer creditLimit;

	@JsonIgnore
	private Integer isBeta;

	@JsonIgnore
	private Double netCredit;

	@JsonProperty("cims_url")
	private String cimsUrl;

	@JsonProperty("is_mbeyond")
	private Boolean isMbeyond;

	private String firstName;

	private String lastName;

	private String referredByCode;

	private String inviteCode;

	private boolean isSocietyReferralActive = false;

	/**
	 * @return the hubId
	 */
	public Long getHubId() {
		return hubId;
	}

	/**
	 * @param hubId
	 *            the hubId to set
	 */
	public void setHubId(Long hubId) {
		this.hubId = hubId;
	}

	/**
	 * @return the lastCustomerActivity
	 */
	public String getLastCustomerActivity() {
		return lastCustomerActivity;
	}

	/**
	 * @param lastCustomerActivity
	 *            the lastCustomerActivity to set
	 */
	public void setLastCustomerActivity(String lastCustomerActivity) {
		this.lastCustomerActivity = lastCustomerActivity;
	}

	/**
	 * @return the inactivityFlag
	 */
	public Integer getInactivityFlag() {
		return inactivityFlag;
	}

	/**
	 * @param inactivityFlag
	 *            the inactivityFlag to set
	 */
	public void setInactivityFlag(Integer inactivityFlag) {
		this.inactivityFlag = inactivityFlag;
	}

	/**
	 * @return the throttleStatus
	 */
	public Integer getThrottleStatus() {
		return throttleStatus;
	}

	/**
	 * @param throttleStatus
	 *            the throttleStatus to set
	 */
	public void setThrottleStatus(Integer throttleStatus) {
		this.throttleStatus = throttleStatus;
	}

	/**
	 * @return the isSocietyReferralActive
	 */
	public boolean isSocietyReferralActive() {
		return isSocietyReferralActive;
	}

	/**
	 * @param isSocietyReferralActive
	 *            the isSocietyReferralActive to set
	 */
	public void setSocietyReferralActive(boolean isSocietyReferralActive) {
		this.isSocietyReferralActive = isSocietyReferralActive;
	}

	/**
	 * @return the referredByCode
	 */
	public String getReferredByCode() {
		return referredByCode;
	}

	/**
	 * @param referredByCode
	 *            the referredByCode to set
	 */
	public void setReferredByCode(String referredByCode) {
		this.referredByCode = referredByCode;
	}

	/**
	 * @return the inviteCode
	 */
	public String getInviteCode() {
		return inviteCode;
	}

	/**
	 * @param inviteCode
	 *            the inviteCode to set
	 */
	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * <p>
	 * Getter for the field <code>id</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.Long} object.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * <p>
	 * Setter for the field <code>id</code>.
	 * </p>
	 *
	 * @param id
	 *            a {@link java.lang.Long} object.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * <p>
	 * Getter for the field <code>name</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getName() {
		return name;
	}

	/**
	 * <p>
	 * Setter for the field <code>name</code>.
	 * </p>
	 *
	 * @param name
	 *            a {@link java.lang.String} object.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * <p>
	 * Getter for the field <code>mobile</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * <p>
	 * Setter for the field <code>mobile</code>.
	 * </p>
	 *
	 * @param mobile
	 *            a {@link java.lang.String} object.
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * <p>
	 * Getter for the field <code>countryCode</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * <p>
	 * Setter for the field <code>countryCode</code>.
	 * </p>
	 *
	 * @param countryCode
	 *            a {@link java.lang.String} object.
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * <p>
	 * Getter for the field <code>phone</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * <p>
	 * Setter for the field <code>phone</code>.
	 * </p>
	 *
	 * @param phone
	 *            a {@link java.lang.String} object.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * <p>
	 * Getter for the field <code>email</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * <p>
	 * Setter for the field <code>email</code>.
	 * </p>
	 *
	 * @param email
	 *            a {@link java.lang.String} object.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * <p>
	 * Getter for the field <code>createdOn</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getCreatedOn() {
		return createdOn;
	}

	/**
	 * <p>
	 * Setter for the field <code>createdOn</code>.
	 * </p>
	 *
	 * @param createdOn
	 *            a {@link java.lang.String} object.
	 */
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * <p>
	 * Getter for the field <code>address</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * <p>
	 * Setter for the field <code>address</code>.
	 * </p>
	 *
	 * @param address
	 *            a {@link java.lang.String} object.
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * <p>
	 * Getter for the field <code>societyId</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.Long} object.
	 */
	public Long getSocietyId() {
		return societyId;
	}

	/**
	 * <p>
	 * Setter for the field <code>cityId</code>.
	 * </p>
	 *
	 * @param cityId
	 *            a {@link java.lang.Long} object.
	 */
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	/**
	 * <p>
	 * Getter for the field <code>cityId</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.Long} object.
	 */
	public Long getCityId() {
		return cityId;
	}

	/**
	 * <p>
	 * Setter for the field <code>societyId</code>.
	 * </p>
	 *
	 * @param societyId
	 *            a {@link java.lang.Long} object.
	 */
	public void setSocietyId(Long societyId) {
		this.societyId = societyId;
	}

	/**
	 * <p>
	 * Getter for the field <code>societyName</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getSocietyName() {
		return societyName;
	}

	/**
	 * <p>
	 * Setter for the field <code>societyName</code>.
	 * </p>
	 *
	 * @param societyName
	 *            a {@link java.lang.String} object.
	 */
	public void setSocietyName(String societyName) {
		this.societyName = societyName;
	}

	/**
	 * <p>
	 * Getter for the field <code>societyActive</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.Integer} object.
	 */
	public Integer getSocietyActive() {
		return societyActive;
	}

	/**
	 * <p>
	 * Setter for the field <code>societyActive</code>.
	 * </p>
	 *
	 * @param societyActive
	 *            a {@link java.lang.Integer} object.
	 */
	public void setSocietyActive(Integer societyActive) {
		this.societyActive = societyActive;
	}

	/**
	 * <p>
	 * Getter for the field <code>deliveryStartDate</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getDeliveryStartDate() {
		return deliveryStartDate;
	}

	/**
	 * <p>
	 * Setter for the field <code>deliveryStartDate</code>.
	 * </p>
	 *
	 * @param deliveryStartDate
	 *            a {@link java.lang.String} object.
	 */
	public void setDeliveryStartDate(String deliveryStartDate) {
		this.deliveryStartDate = deliveryStartDate;
	}

	/**
	 * <p>
	 * Getter for the field <code>city</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getCity() {
		return city;
	}

	/**
	 * <p>
	 * Setter for the field <code>city</code>.
	 * </p>
	 *
	 * @param city
	 *            a {@link java.lang.String} object.
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * <p>
	 * Getter for the field <code>paid</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.Integer} object.
	 */
	public Integer getPaid() {
		return paid;
	}

	/**
	 * <p>
	 * Setter for the field <code>paid</code>.
	 * </p>
	 *
	 * @param paid
	 *            a {@link java.lang.Integer} object.
	 */
	public void setPaid(Integer paid) {
		this.paid = paid;
	}

	/**
	 * <p>
	 * Getter for the field <code>upi</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.Integer} object.
	 */
	public Integer getUpi() {
		return upi;
	}

	/**
	 * <p>
	 * Setter for the field <code>upi</code>.
	 * </p>
	 *
	 * @param upi
	 *            a {@link java.lang.Integer} object.
	 */
	public void setUpi(Integer upi) {
		this.upi = upi;
	}

	/**
	 * <p>
	 * Getter for the field <code>isSMS</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.Integer} object.
	 */
	public Integer getIsSMS() {
		return isSMS;
	}

	/**
	 * <p>
	 * Setter for the field <code>isSMS</code>.
	 * </p>
	 *
	 * @param isSMS
	 *            a {@link java.lang.Integer} object.
	 */
	public void setIsSMS(Integer isSMS) {
		this.isSMS = isSMS;
	}

	/**
	 * <p>
	 * Getter for the field <code>isEmail</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.Integer} object.
	 */
	public Integer getIsEmail() {
		return isEmail;
	}

	/**
	 * <p>
	 * Setter for the field <code>isEmail</code>.
	 * </p>
	 *
	 * @param isEmail
	 *            a {@link java.lang.Integer} object.
	 */
	public void setIsEmail(Integer isEmail) {
		this.isEmail = isEmail;
	}

	/**
	 * <p>
	 * Getter for the field <code>isPush</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.Integer} object.
	 */
	public Integer getIsPush() {
		return isPush;
	}

	/**
	 * <p>
	 * Setter for the field <code>isPush</code>.
	 * </p>
	 *
	 * @param isPush
	 *            a {@link java.lang.Integer} object.
	 */
	public void setIsPush(Integer isPush) {
		this.isPush = isPush;
	}

	/**
	 * <p>
	 * Getter for the field <code>isValidAddress</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.Integer} object.
	 */
	public Integer getIsValidAddress() {
		return isValidAddress;
	}

	/**
	 * <p>
	 * Setter for the field <code>isValidAddress</code>.
	 * </p>
	 *
	 * @param isValidAddress
	 *            a {@link java.lang.Integer} object.
	 */
	public void setIsValidAddress(Integer isValidAddress) {
		this.isValidAddress = isValidAddress;
	}

	/**
	 * <p>
	 * Getter for the field <code>isDuplicate</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.Integer} object.
	 */
	public Integer getIsDuplicate() {
		return isDuplicate;
	}

	/**
	 * <p>
	 * Setter for the field <code>isDuplicate</code>.
	 * </p>
	 *
	 * @param isDuplicate
	 *            a {@link java.lang.Integer} object.
	 */
	public void setIsDuplicate(Integer isDuplicate) {
		this.isDuplicate = isDuplicate;
	}

	/**
	 * <p>
	 * Getter for the field <code>firstPaymentDate</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getFirstPaymentDate() {
		return firstPaymentDate;
	}

	/**
	 * <p>
	 * Setter for the field <code>firstPaymentDate</code>.
	 * </p>
	 *
	 * @param firstPaymentDate
	 *            a {@link java.lang.String} object.
	 */
	public void setFirstPaymentDate(String firstPaymentDate) {
		this.firstPaymentDate = firstPaymentDate;
	}

	/**
	 * <p>
	 * Getter for the field <code>firstOrderDate</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getFirstOrderDate() {
		return firstOrderDate;
	}

	/**
	 * <p>
	 * Setter for the field <code>firstOrderDate</code>.
	 * </p>
	 *
	 * @param firstOrderDate
	 *            a {@link java.lang.String} object.
	 */
	public void setFirstOrderDate(String firstOrderDate) {
		this.firstOrderDate = firstOrderDate;
	}

	/**
	 * <p>
	 * Getter for the field <code>lastOrderDate</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getLastOrderDate() {
		return lastOrderDate;
	}

	/**
	 * <p>
	 * Setter for the field <code>lastOrderDate</code>.
	 * </p>
	 *
	 * @param lastOrderDate
	 *            a {@link java.lang.String} object.
	 */
	public void setLastOrderDate(String lastOrderDate) {
		this.lastOrderDate = lastOrderDate;
	}

	/**
	 * <p>
	 * Getter for the field <code>referrerCode</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getReferrerCode() {
		return referrerCode;
	}

	/**
	 * <p>
	 * Setter for the field <code>referrerCode</code>.
	 * </p>
	 *
	 * @param referrerCode
	 *            a {@link java.lang.String} object.
	 */
	public void setReferrerCode(String referrerCode) {
		this.referrerCode = referrerCode;
	}

	/**
	 * <p>
	 * Getter for the field <code>netCredit</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.Double} object.
	 */
	public Double getNetCredit() {
		return netCredit;
	}

	/**
	 * <p>
	 * Setter for the field <code>netCredit</code>.
	 * </p>
	 *
	 * @param netCredit
	 *            a {@link java.lang.Double} object.
	 */
	public void setNetCredit(Double netCredit) {
		this.netCredit = netCredit;
	}

	/**
	 * @return the cashback
	 */
	public Double getCashback() {
		return cashback;
	}

	/**
	 * @param cashback
	 *            the cashback to set
	 */
	public void setCashback(Double cashback) {
		this.cashback = cashback;
	}

	/**
	 * <p>
	 * Getter for the field <code>creditLimit</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.Integer} object.
	 */
	public Integer getCreditLimit() {
		return creditLimit;
	}

	/**
	 * <p>
	 * Setter for the field <code>creditLimit</code>.
	 * </p>
	 *
	 * @param creditLimit
	 *            a {@link java.lang.Integer} object.
	 */
	public void setCreditLimit(Integer creditLimit) {
		this.creditLimit = creditLimit;
	}

	/**
	 * <p>
	 * Getter for the field <code>isBeta</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.Integer} object.
	 */
	public Integer getIsBeta() {
		return isBeta;
	}

	/**
	 * <p>
	 * Setter for the field <code>isBeta</code>.
	 * </p>
	 *
	 * @param isBeta
	 *            a {@link java.lang.Integer} object.
	 */
	public void setIsBeta(Integer isBeta) {
		this.isBeta = isBeta;
	}

	/**
	 * <p>
	 * Getter for the field <code>cimsUrl</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getCimsUrl() {
		return cimsUrl;
	}

	/**
	 * <p>
	 * Setter for the field <code>cimsUrl</code>.
	 * </p>
	 *
	 * @param cimsUrl
	 *            a {@link java.lang.String} object.
	 */
	public void setCimsUrl(String cimsUrl) {
		this.cimsUrl = cimsUrl;
	}

	public Long getReferredBy() {
		return referredBy;
	}

	public void setReferredBy(Long referredBy) {
		this.referredBy = referredBy;
	}

	@JsonIgnore
	public boolean isBeta() {
		return this.getIsBeta() != null && this.getIsBeta() > 0;
	}

	@JsonIgnore
	public boolean isPaidUser() {
		return this.getPaid() != null && this.getPaid().intValue() == 1;
	}

	/**
	 * @return the isMbeyond
	 */
	public Boolean getIsMbeyond() {
		return isMbeyond;
	}

	/**
	 * @param isMbeyond
	 *            the isMbeyond to set
	 */
	public void setIsMbeyond(Boolean isMbeyond) {
		this.isMbeyond = isMbeyond;
	}

	/**
	 * @return the referralExpiryDate
	 */
	public String getReferralExpiryDate() {
		return referralExpiryDate;
	}

	/**
	 * @param referralExpiryDate
	 *            the referralExpiryDate to set
	 */
	public void setReferralExpiryDate(String referralExpiryDate) {
		this.referralExpiryDate = referralExpiryDate;
	}

}
