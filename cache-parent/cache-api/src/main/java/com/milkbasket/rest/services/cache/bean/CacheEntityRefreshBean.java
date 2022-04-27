package com.milkbasket.rest.services.cache.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CacheEntityRefreshBean implements Serializable {

	private static final long serialVersionUID = -8578864695644726471L;

	private String tableName;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(final String tableName) {
		this.tableName = tableName;
	}

	@JsonIgnore
	public String[] getTableNames() {
		if (tableName != null) {
			return tableName.split(",");
		}
		return null;
	}

}
