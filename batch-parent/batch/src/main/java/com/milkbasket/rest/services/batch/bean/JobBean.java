package com.milkbasket.rest.services.batch.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.milkbasket.core.framework.utility.CollectionUtils;
import com.milkbasket.core.framework.utility.StringUtils;

/**
 * <p>
 * JobBean class.
 * </p>
 *
 * @author nitin
 * @version $Id: $Id
 */
public class JobBean implements Serializable {
	// private static final AppLogger _LOGGER =
	// LoggingManager.getLogger(JobBean.class);

	private static final long serialVersionUID = -3244136666123155939L;

	private String jobName;
	private String jobDefinition;
	private String jobQueue;
	private List<JobBean> dependentJobs;
	private Map<String, Object> data;

	/**
	 * <p>
	 * Constructor for JobBean.
	 * </p>
	 */
	public JobBean() {

	}

	/**
	 * <p>
	 * Constructor for JobBean.
	 * </p>
	 *
	 * @param jobName
	 *            a {@link java.lang.String} object.
	 * @param jobDefinition
	 *            a {@link java.lang.String} object.
	 * @param jobQueue
	 *            a {@link java.lang.String} object.
	 */
	public JobBean(final String jobName, final String jobDefinition, final String jobQueue) {
		this(jobName, jobDefinition, jobQueue, null);
	}

	/**
	 * <p>
	 * Constructor for JobBean.
	 * </p>
	 *
	 * @param jobName
	 *            a {@link java.lang.String} object.
	 * @param jobDefinition
	 *            a {@link java.lang.String} object.
	 * @param jobQueue
	 *            a {@link java.lang.String} object.
	 * @param dependentJobs
	 *            a {@link java.util.List} object.
	 */
	public JobBean(final String jobName, final String jobDefinition, final String jobQueue, final List<JobBean> dependentJobs) {
		this.jobName = jobName;
		this.jobDefinition = jobDefinition;
		this.jobQueue = jobQueue;
		this.dependentJobs = dependentJobs;
	}

	/**
	 * <p>
	 * Getter for the field <code>jobName</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getJobName() {
		return jobName;
	}

	/**
	 * <p>
	 * Setter for the field <code>jobName</code>.
	 * </p>
	 *
	 * @param jobName
	 *            a {@link java.lang.String} object.
	 */
	public void setJobName(final String jobName) {
		this.jobName = jobName;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	/**
	 * <p>
	 * Getter for the field <code>jobDefinition</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getJobDefinition() {
		return jobDefinition;
	}

	/**
	 * <p>
	 * Setter for the field <code>jobDefinition</code>.
	 * </p>
	 *
	 * @param jobDefinition
	 *            a {@link java.lang.String} object.
	 */
	public void setJobDefinition(final String jobDefinition) {
		this.jobDefinition = jobDefinition;
	}

	/**
	 * <p>
	 * Getter for the field <code>jobQueue</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getJobQueue() {
		return jobQueue;
	}

	/**
	 * <p>
	 * Setter for the field <code>jobQueue</code>.
	 * </p>
	 *
	 * @param jobQueue
	 *            a {@link java.lang.String} object.
	 */
	public void setJobQueue(final String jobQueue) {
		this.jobQueue = jobQueue;
	}

	/**
	 * <p>
	 * Getter for the field <code>dependentJobs</code>.
	 * </p>
	 *
	 * @return a {@link java.util.List} object.
	 */
	public List<JobBean> getDependentJobs() {
		return dependentJobs;
	}

	/**
	 * <p>
	 * Setter for the field <code>dependentJobs</code>.
	 * </p>
	 *
	 * @param dependentJobs
	 *            a {@link java.util.List} object.
	 */
	public void setDependentJobs(final List<JobBean> dependentJobs) {
		this.dependentJobs = dependentJobs;
	}

	/**
	 * <p>
	 * replaceHubAndEnv.
	 * </p>
	 *
	 * @param environment
	 *            a {@link java.lang.String} object.
	 * @param release
	 *            a {@link java.lang.String} object.
	 */
	public void replaceHubAndEnv(final String environment, final String release) {
		// _LOGGER.info("environment: " + environment);

		this.setJobName(this.getJobName().replace("@env@", environment));
		this.setJobName(this.getJobName().replace("@release@", release));

		this.setJobDefinition(this.getJobDefinition().replace("@env@", environment));
		this.setJobDefinition(this.getJobDefinition().replace("@release@", release));

		this.setJobQueue(this.getJobQueue().replace("@env@", environment));
		this.setJobQueue(this.getJobQueue().replace("@release@", release));
	}

	public void replaceEnvVars(final JobEnvBean envBean) {
		final String jobName = this.getJobName().replace("@env@", envBean.getEnvironment()).replace("@release@", envBean.getRelease())
				.replace("@hubType@", envBean.getHubType());
		this.setJobName(jobName);

		final String jobDefinition = this.getJobDefinition().replace("@env@", envBean.getEnvironment()).replace("@release@", envBean.getRelease())
				.replace("@hubType@", envBean.getHubType());
		this.setJobDefinition(jobDefinition);

		final String jobQueue = this.getJobQueue().replace("@zone@", envBean.getZone()).replace("@account@", envBean.getAccount())
				.replace("@env@", envBean.getEnvironment()).replace("@release@", envBean.getRelease())
				.replace("@computeType@", envBean.getComputeType());
		this.setJobQueue(jobQueue);

		if (CollectionUtils.isNotEmpty(getDependentJobs())) {
			getDependentJobs().stream().forEach(job -> job.replaceEnvVars(envBean));
		}

	}

	/**
	 * <p>
	 * convertToBean.
	 * </p>
	 *
	 * @param job
	 *            a {@link java.util.Map} object.
	 * @return a {@link com.milkbasket.rest.services.batch.bean.JobBean} object.
	 */
	@SuppressWarnings("unchecked")
	public static JobBean convertToBean(final Map<String, Object> job) {
		final JobBean jobBean = new JobBean();
		jobBean.setJobName((String) job.get("jobName"));
		jobBean.setJobDefinition((String) job.get("jobDefinition"));
		jobBean.setJobQueue((String) job.get("jobQueue"));

		final List<Object> dependentJobs = (List<Object>) job.get("dependentJobs");
		if (dependentJobs != null && CollectionUtils.isNotEmpty(dependentJobs)) {
			final List<JobBean> dependentJobBeans = new ArrayList<>();
			for (final Object dependentJob : dependentJobs) {
				dependentJobBeans.add(convertToBean((Map<String, Object>) dependentJob));
			}
			jobBean.setDependentJobs(dependentJobBeans);
		}

		return jobBean;
	}

	public void setData(String parentJobId, final JobBean jobBean, final String hubId, final String date, final Map<String, Object> additionalData) {
		Map<String, Object> data = new HashMap<>();
		if (StringUtils.isNotEmpty(parentJobId)) {
			data.put("parentJobId", parentJobId);
		}
		if (StringUtils.isNotEmpty(hubId)) {
			data.put("hubId", hubId);
		}
		if (StringUtils.isNotEmpty(date)) {
			data.put("date", date);
		}
		if (CollectionUtils.isNotEmpty(additionalData)) {
			data.put("additionalData", additionalData);
		}
		jobBean.setData(data);
	}

}
