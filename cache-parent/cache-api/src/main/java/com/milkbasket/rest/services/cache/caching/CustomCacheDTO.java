package com.milkbasket.rest.services.cache.caching;

import java.io.Serializable;

import com.milkbasket.core.framework.validator.annotation.Mandatory;

public class CustomCacheDTO implements Serializable {

	private static final long serialVersionUID = -8886128729818527266L;

	@Mandatory
	private String groupName;

	@Mandatory
	private Object entity;

	private int noExpiry = 0;

	@Mandatory
	private Object[] keyValues;

	public CustomCacheDTO() {
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(final String groupName) {
		this.groupName = groupName;
	}

	public Object getEntity() {
		return entity;
	}

	public void setEntity(final Object entity) {
		this.entity = entity;
	}

	public int getNoExpiry() {
		return noExpiry;
	}

	public void setNoExpiry(final int noExpiry) {
		this.noExpiry = noExpiry;
	}

	public Object[] getKeyValues() {
		return keyValues;
	}

	public void setKeyValues(final Object[] keyValues) {
		this.keyValues = keyValues;
	}

}
