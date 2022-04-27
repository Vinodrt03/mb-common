package com.milkbasket.rest.services.common.communication.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UnfulfilledBasketBean implements Serializable {

	private static final long serialVersionUID = 1863173765407748519L;

	private static final String BASE_LINK = "milkbasket://app/similarproducts/";

	private Long basketId;

	private Long productId;

	private String productName;

	private Integer productQuantity;

	private Long userId;

	private String date;

	private Long hubId;

	private String customerPhone;

	private String firstName;

	@JsonIgnore
	public String getLink() {
		return BASE_LINK.concat(String.valueOf(getProductId()));
	}

	public Long getHubId() {
		return hubId;
	}

	public void setHubId(final Long hubId) {
		this.hubId = hubId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(final Long userId) {
		this.userId = userId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(final String date) {
		this.date = date;
	}

	public Long getBasketId() {
		return basketId;
	}

	public void setBasketId(final Long basketId) {
		this.basketId = basketId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(final Long productId) {
		this.productId = productId;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(final String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public Integer getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(final Integer productQuantity) {
		this.productQuantity = productQuantity;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(final String productName) {
		this.productName = productName;
	}

}
