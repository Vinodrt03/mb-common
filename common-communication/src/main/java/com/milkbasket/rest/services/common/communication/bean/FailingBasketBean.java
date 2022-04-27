package com.milkbasket.rest.services.common.communication.bean;

import java.io.Serializable;
import java.util.Date;

public class FailingBasketBean implements Serializable {

	private static final long serialVersionUID = 2838327939172106503L;

	private Long hubId;

	private Long userId;

	private Date date;

	private Long basketId;

	private Double net;

	private Double credits;

	private Integer creditLimt;

	private String customerPhone;

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

	public Date getDate() {
		return date;
	}

	public void setDate(final Date date) {
		this.date = date;
	}

	public Long getBasketId() {
		return basketId;
	}

	public void setBasketId(final Long basketId) {
		this.basketId = basketId;
	}

	public Double getNet() {
		return net;
	}

	public void setNet(final Double net) {
		this.net = net;
	}

	public Double getCredits() {
		return credits;
	}

	public void setCredits(final Double credits) {
		this.credits = credits;
	}

	public Integer getCreditLimt() {
		return creditLimt;
	}

	public void setCreditLimt(final Integer creditLimt) {
		this.creditLimt = creditLimt;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(final String customerPhone) {
		this.customerPhone = customerPhone;
	}
}
