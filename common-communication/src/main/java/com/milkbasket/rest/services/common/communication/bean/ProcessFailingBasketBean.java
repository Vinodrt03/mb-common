package com.milkbasket.rest.services.common.communication.bean;

import java.io.Serializable;

public class ProcessFailingBasketBean implements Serializable {

	private static final long serialVersionUID = -3302927611477513232L;

	private Integer userFailingCount;

	private Integer maxFailingCount;

	private FailingBasketBean failingBasket;

	public Integer getUserFailingCount() {
		return userFailingCount;
	}

	public void setUserFailingCount(final Integer userFailingCount) {
		this.userFailingCount = userFailingCount;
	}

	public Integer getMaxFailingCount() {
		return maxFailingCount;
	}

	public void setMaxFailingCount(final Integer maxFailingCount) {
		this.maxFailingCount = maxFailingCount;
	}

	public FailingBasketBean getFailingBasket() {
		return failingBasket;
	}

	public void setFailingBasket(final FailingBasketBean failingBasket) {
		this.failingBasket = failingBasket;
	}
}
