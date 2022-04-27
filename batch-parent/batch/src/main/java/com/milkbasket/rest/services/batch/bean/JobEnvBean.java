package com.milkbasket.rest.services.batch.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.milkbasket.core.framework.utility.JSONUtils;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class JobEnvBean implements Serializable {

	private static final long serialVersionUID = -4933389381220805486L;

	@JsonIgnore
	private List<String> hubIds = new ArrayList<>();
	private String zone = "ap-south-1";
	private String account;
	private String environment;
	private String release;
	private String jobKey;
	private String hubAsWarehouse;
	private String hubId;

	public JobEnvBean() {
	}

	public JobEnvBean(final String account) {
		this.account = account;
	}

	public JobEnvBean(final String account, final String environment, final String release) {
		this.account = account;
		this.environment = environment;
		this.release = release;
	}

	public JobEnvBean(final String zone, final String account, final String environment, final String release) {
		this(account, environment, release);
		this.zone = zone;
	}

	public JobEnvBean(final String zone, final String account, final String environment, final String release, final String hubAsWarehouse) {
		this(zone, account, environment, release);
		setHubAsWarehouse(hubAsWarehouse);
	}

	public JobEnvBean(final String zone, final String account, final String environment, final String release, final String hubAsWarehouse,
			final String hubId) {
		this(zone, account, environment, release, hubAsWarehouse);
		this.hubId = hubId;
	}

	public String getHubId() {
		return hubId;
	}

	public void setHubId(final String hubId) {
		this.hubId = hubId;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(final String zone) {
		this.zone = zone;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(final String account) {
		this.account = account;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(final String environment) {
		this.environment = environment;
	}

	public String getRelease() {
		return release;
	}

	public void setRelease(final String release) {
		this.release = release;
	}

	public String getJobKey() {
		return jobKey;
	}

	public void setJobKey(final String jobKey) {
		this.jobKey = jobKey;
	}

	public String getHubAsWarehouse() {
		return hubAsWarehouse;
	}

	public void setHubAsWarehouse(final String hubAsWarehouse) {
		this.hubAsWarehouse = hubAsWarehouse;
		this.hubIds = Arrays.asList(getHubAsWarehouse().split("\\s*,\\s*"));
	}

	@JsonIgnore
	public List<String> getHubIds() {
		return this.hubIds;
	}

	@JsonIgnore
	public String getHubType() {
		if (StringUtils.isEmpty(getHubId()) || getHubIds().contains(getHubId())) {
			return "";
		}
		return "store-";

	}

	@JsonIgnore
	public String getComputeType() {
		if (StringUtils.isEmpty(getHubId()) || getHubIds().contains(getHubId())) {
			return "medium";
		}
		return "small";
	}

	public static void main(final String[] args) {
		try {
			final String jobEnvProps = "{\"zone\":\"ap-south-1\",\"account\":\"399080174938\",\"hubAsWarehouse\":\"1,2,  3,4,5,7\"}";
			final String[] hubIds = { "9", "3", "2", null, "", "6" };

			final String environment = "prod";
			final String release = "current";
			final String jobKey = "PROCESS_BASKET";
			final JobEnvBean jobEnvBean = JSONUtils.jsonToObject(jobEnvProps, JobEnvBean.class);
			jobEnvBean.setEnvironment(environment);
			jobEnvBean.setRelease(release);
			jobEnvBean.setJobKey(jobKey);

			for (final String hubId : hubIds) {
				// final JobEnvBean jobEnvBean1 = (JobEnvBean) ObjectUtils.deepCopy(jobEnvBean);
				jobEnvBean.setHubId(hubId);
				System.out.println(jobEnvBean.getHubId() + " " + jobEnvBean.getComputeType() + " " + jobEnvBean.getHubType());
			}

		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

}
