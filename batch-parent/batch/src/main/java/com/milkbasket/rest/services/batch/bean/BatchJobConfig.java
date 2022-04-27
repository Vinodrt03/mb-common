package com.milkbasket.rest.services.batch.bean;

import java.io.Serializable;
import java.util.Map;

public class BatchJobConfig implements Serializable {

	private static final long serialVersionUID = -4840681919627276946L;

	private Map<String, JobBean> jobMap;

	public Map<String, JobBean> getJobMap() {
		return jobMap;
	}

	public void setJobMap(final Map<String, JobBean> jobMap) {
		this.jobMap = jobMap;
	}

}
