package com.milkbasket.rest.services.batch.submittor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.batch.model.ContainerOverrides;
import com.amazonaws.services.batch.model.JobDependency;
import com.amazonaws.services.batch.model.KeyValuePair;
import com.amazonaws.services.batch.model.SubmitJobRequest;
import com.amazonaws.services.batch.model.SubmitJobResult;
import com.milkbasket.core.framework.batch.operation.BatchOperations;
import com.milkbasket.core.framework.common.bean.ErrorBean;
import com.milkbasket.core.framework.common.exception.BusinessException;
import com.milkbasket.core.framework.common.utils.ContextUtils;
import com.milkbasket.core.framework.common.utils.SessionUtils;
import com.milkbasket.core.framework.events.utils.EventUtils;
import com.milkbasket.core.framework.logging.core.AppLogger;
import com.milkbasket.core.framework.logging.manager.LoggingManager;
import com.milkbasket.core.framework.params.utils.ParamsUtils;
import com.milkbasket.core.framework.property.utils.PropertiesUtils;
import com.milkbasket.core.framework.utility.CollectionUtils;
import com.milkbasket.core.framework.utility.DateUtils;
import com.milkbasket.core.framework.utility.JSONUtils;
import com.milkbasket.core.framework.utility.ObjectUtils;
import com.milkbasket.core.framework.utility.StringUtils;
import com.milkbasket.rest.services.batch.bean.JobBean;
import com.milkbasket.rest.services.batch.bean.JobEnvBean;
import com.milkbasket.rest.services.batch.config.JobSelector;
import com.milkbasket.rest.shared.constant.BusinessEvent;
import com.milkbasket.rest.shared.event.bean.BatchJobRequestBean;
import com.milkbasket.rest.shared.event.bean.JobDetailBean;

/**
 * <p>
 * JobSubmittor class.
 * </p>
 *
 * @author nitin
 * @version $Id: $Id
 */
public class JobSubmittor {

	private static final AppLogger _LOGGER = LoggingManager.getLogger(JobSubmittor.class);

	public static void executeJobs(final String jobKey, final String[] hubIds, final String date, final String environment, final String release,
			final Map<String, Object> additionalData) {
		final JobEnvBean jobEnv = getJobEnvBean(jobKey, environment, release);
		if (hubIds == null) {
			execute(null, date, jobEnv, additionalData);
		} else {
			for (final String hubId : hubIds) {
				final JobEnvBean jobEnvBean = (JobEnvBean) ObjectUtils.deepCopy(jobEnv);
				jobEnvBean.setHubId(hubId);
				_LOGGER.info(String.format("Hub ID %s | Job Key %s ! Compute Type %s ! Hub Type %s", hubId, jobEnvBean.getJobKey(),
						jobEnvBean.getComputeType(), jobEnvBean.getHubType()));
				execute(hubId, date, jobEnvBean, additionalData);
			}
		}
	}

	public static void execute(final String jobKey, final String hubId, final String date, final String environment, final String release) {
		final JobEnvBean jobEnvBean = getJobEnvBean(jobKey, environment, release);
		jobEnvBean.setHubId(hubId);
		execute(hubId, date, jobEnvBean, null);
	}

	private static void execute(final String hubId, final String date, final JobEnvBean jobEnvBean, final Map<String, Object> additionalData) {
		final JobBean jobBean = JobSelector.getJobDetail(jobEnvBean);
		manageJobs(jobBean, null, hubId, date, additionalData);
	}

	private static JobEnvBean getJobEnvBean(final String jobKey, final String environment, final String release) {
		final String jobEnvProps = PropertiesUtils.getProperty("JOB_ENV_CONFIG",
				"{\"zone\":\"ap-south-1\",\"account\":\"399080174938\",\"hubAsWarehouse\":\"1,2,3,4,5,7\"}");
		final JobEnvBean jobEnvBean = JSONUtils.jsonToObject(jobEnvProps, JobEnvBean.class);
		jobEnvBean.setEnvironment(environment);
		jobEnvBean.setRelease(release);
		jobEnvBean.setJobKey(jobKey);
		return jobEnvBean;
	}

	private static SubmitJobResult manageJobs(final JobBean jobBean, final String parentJobId, final String hubId, final String date,
			final Map<String, Object> additionalData) {
		if (StringUtils.isBlank(jobBean.getJobName()) || StringUtils.isBlank(jobBean.getJobDefinition())
				|| StringUtils.isBlank(jobBean.getJobQueue())) {
			_LOGGER.error("Invalid call for jobName: " + jobBean.getJobName() + "; jobDefinition: " + jobBean.getJobDefinition() + "; jobQueue:"
					+ jobBean.getJobQueue());
			throw new BusinessException(ErrorBean.withError("INVALID_JOB_REQUEST"));
		}
		jobBean.setData(parentJobId, jobBean, hubId, date, additionalData);
		final SubmitJobResult submitJobResult = submitJob(jobBean);

		if (CollectionUtils.isNotEmpty(jobBean.getDependentJobs())) {
			for (final JobBean dependentJob : jobBean.getDependentJobs()) {
				try {
					manageJobs(dependentJob, submitJobResult.getJobId(), hubId, date, additionalData);
				} catch (final Exception e) {
					final String error = "ERROR occured while submitting Job \n " + dependentJob.getJobName() + "\n Hub ID : " + hubId + "\n Date : "
							+ date;
					_LOGGER.error(error);
					sendEmail(error);
				}
			}
		}

		return submitJobResult;
	}

	private static void sendEmail(final String error) {
		final Map<String, Object> contextData = new HashMap<>();
		contextData.put("ROOT_CAUSE", error);
		final Map<String, Object> emailBean = new HashMap<>();
		emailBean.put("toIds", Arrays.asList(ParamsUtils.getParam("TECH_ISSUE_EMAIL", "tech-issue@milkbasket.com")));
		emailBean.put("templateName", "JOB_FAIL_EMAIL");
		emailBean.put("contextData", contextData);

		EventUtils.publishEvent(BusinessEvent.PROCESS_EMAIL, emailBean);
	}

	private static SubmitJobResult submitJob(final JobBean jobBean) {

		final SubmitJobRequest jobRequest = new SubmitJobRequest();

		jobRequest.setJobName(jobBean.getJobName());
		jobRequest.setJobDefinition(jobBean.getJobDefinition());
		jobRequest.setJobQueue(jobBean.getJobQueue());

		final List<KeyValuePair> keyValuePairs = new ArrayList<>();
		String parentJobId = null;
		String hubId = null;
		String date = null;
		Map<String, Object> additionalData = new HashMap<String, Object>();
		Map<String, Object> data = jobBean.getData();

		if (CollectionUtils.isNotEmpty(data)) {
			parentJobId = data.containsKey("parentJobId") ? data.get("parentJobId").toString() : null;
			hubId = data.containsKey("hubId") ? data.get("hubId").toString() : null;
			date = data.containsKey("date") ? data.get("date").toString() : null;
			additionalData = data.containsKey("additionalData") ? (Map<String, Object>) data.get("additionalData") : null;
		}
		if (StringUtils.isNotBlank(hubId)) {
			final KeyValuePair keyValuePair = new KeyValuePair();
			keyValuePair.setName("hub");
			keyValuePair.setValue(hubId);
			keyValuePairs.add(keyValuePair);
		}

		if (StringUtils.isNotBlank(date)) {
			final KeyValuePair keyValuePairDate = new KeyValuePair();
			keyValuePairDate.setName("date");
			keyValuePairDate.setValue(date);
			keyValuePairs.add(keyValuePairDate);
		}

		if (CollectionUtils.isNotEmpty(additionalData)) {
			final KeyValuePair keyValuePairAdditionalData = new KeyValuePair();
			keyValuePairAdditionalData.setName("additionalData");
			keyValuePairAdditionalData.setValue(JSONUtils.objectToJson(additionalData));
			keyValuePairs.add(keyValuePairAdditionalData);
		}

		if (SessionUtils.getAuthUserId() != null) {
			final KeyValuePair keyValuePairDate = new KeyValuePair();
			keyValuePairDate.setName("initiatedBy");
			keyValuePairDate.setValue(SessionUtils.getAuthUserId() + "");
			keyValuePairs.add(keyValuePairDate);
		}

		final ContainerOverrides containerOverrides = new ContainerOverrides();
		containerOverrides.setEnvironment(keyValuePairs);
		jobRequest.setContainerOverrides(containerOverrides);

		if (StringUtils.isNotBlank(parentJobId)) {
			final List<JobDependency> jobDependencies = new ArrayList<>();
			final JobDependency jobDependent = new JobDependency();
			jobDependent.setJobId(parentJobId);
			jobDependencies.add(jobDependent);

			jobRequest.setDependsOn(jobDependencies);
		}

		SubmitJobResult submitJobResult = new SubmitJobResult();
		if (!ContextUtils.isProfileTest()) {
			_LOGGER.info(String.format("Job Name %s ! Job Queue %s", jobRequest.getJobName(), jobRequest.getJobQueue()));
			_LOGGER.info(JSONUtils.objectToJSON(jobRequest));
			submitJobResult = BatchOperations.submitJob(jobRequest);
		}
		return submitJobResult;
	}

	public static void submitJobs(final String payload) {
		submitJobs(JSONUtils.jsonToObject(payload, JobDetailBean.class));
	}

	public static void submitJobs(final JobDetailBean bean) {

		if (bean != null) {
			final String environment = ContextUtils.getActiveProfile();
			final String release = ContextUtils.getRelease();

			final BatchJobRequestBean request = bean.getDetail();

			final String isNextDate = request.getIsNextDate();
			if (request.getDate() == null && "1".equals(isNextDate)) {
				final String date = DateUtils.toString(DateUtils.addDays(DateUtils.getDate(), 1));
				request.setDate(date);
			}
			_LOGGER.info(String.format("Job Key :%s Date :%s Hub :%s Environment :%s Release :%s", request.getJobKey(), request.getDate(),
					request.getHubId(), environment, release));
			JobSubmittor.executeJobs(request.getJobKey(), request.getHubIds(), request.getDate(), environment, release, request.getAdditionalData());
		} else {
			_LOGGER.info("Batch Request found null or not able to parse request");
		}
	}

	/**
	 * <p>
	 * main.
	 * </p>
	 *
	 * @param args
	 *            an array of {@link java.lang.String} objects.
	 */
	public static void main(final String[] args) {
		JobSubmittor.execute("PROCURE_BASKETS", "1", null, "dev", "current");
	}
}
