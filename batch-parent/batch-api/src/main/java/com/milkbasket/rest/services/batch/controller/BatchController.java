package com.milkbasket.rest.services.batch.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.milkbasket.core.framework.common.anotation.NewRelicIgnoreTransaction;
import com.milkbasket.core.framework.common.anotation.SnsSubscription;
import com.milkbasket.core.framework.utility.JSONUtils;
import com.milkbasket.rest.shared.batch.aws.config.AwsBatchJobConfig;
import com.milkbasket.rest.shared.batch.aws.utils.AwsJobSubmittor;
import com.milkbasket.rest.shared.job.bean.BatchJobRequestBean;
import com.milkbasket.rest.shared.job.bean.JobDetailBean;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
/**
 * <p>
 * BatchController class.
 * </p>
 *
 * @author nitin
 * @version $Id: $Id
 */
@Api(tags = { "Batch Services" })
public class BatchController {

	// private static final AppLogger _LOGGER =
	// LoggingManager.getLogger(BatchController.class);

	@SuppressWarnings("unused")
	private AwsBatchJobConfig jobConfig;

	@ApiOperation(value = "Execute Batch Job", nickname = "executeBatchJob")
	@PostMapping("/batch")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@NewRelicIgnoreTransaction
	public void executeBatch(@Valid @RequestBody(required = false) final BatchJobRequestBean request) {
		AwsJobSubmittor.executeJobs(request);
	}

	@ApiOperation(value = "Execute Batch Job(s) via sns subscription [B2B]", nickname = "executeBatchJobs")
	@PostMapping("/batch/subscription")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@SnsSubscription
	@NewRelicIgnoreTransaction
	public void executeBatchJobs(final HttpServletRequest httpServletRequest, @RequestBody final String payload) {
		final JobDetailBean job = JSONUtils.jsonToObject(payload, JobDetailBean.class);
		AwsJobSubmittor.executeJobs(job.getDetail());
	}

	@ApiOperation(value = "Run cron job. Created to trigger validate_customer_sample cron from the upload page. ", nickname = "runCronJob")
	@PostMapping("/batch/job/key")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void runCronJob(@Valid @RequestBody(required = false) final BatchJobRequestBean request) {
		AwsJobSubmittor.executeJobs(request);
	}

}
