/*
 * @author Sushant
 * Copyright milkbasket.com
 */
package com.milkbasket.rest.services.batch.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

import com.milkbasket.core.framework.common.bean.ErrorBean;
import com.milkbasket.core.framework.common.constants.ApplicationProfiles;
import com.milkbasket.core.framework.common.constants.CommonConstants;
import com.milkbasket.core.framework.common.exception.ServerException;
import com.milkbasket.core.framework.file.utility.S3FileUtils;
import com.milkbasket.core.framework.logging.core.AppLogger;
import com.milkbasket.core.framework.logging.manager.LoggingManager;
import com.milkbasket.core.framework.utility.CollectionUtils;
import com.milkbasket.core.framework.utility.JSONUtils;
import com.milkbasket.rest.services.batch.bean.BatchJobConfig;
import com.milkbasket.rest.services.batch.bean.JobBean;

/**
 * The Class JobConfigReader.
 *
 * @author Sanjeev Jha
 * @version $Id: $Id
 */
@Configuration
public class JobConfigReader {

	private static final AppLogger _LOGGER = LoggingManager.getLogger(JobConfigReader.class);

	private static final String JOB_CONFIG_BUCKET = getConfigBucket(".app.config");
	private static final String JOB_CONFIG_KEY = "config/jobs/%s/batch.json";

	private static String getConfigBucket(final String bucketPrefix) {
		String configSuffix = System.getProperty(CommonConstants.SECURE_BUCKET_SUFFIX);
		if (configSuffix == null) {
			configSuffix = "milkbasket";
		}
		return configSuffix.concat(bucketPrefix);
	}

	@Autowired
	private Environment env;

	@Autowired
	private S3FileUtils s3FileUtils;

	@Bean
	public BatchJobConfig batchJobConfig() {
		return load();
	}

	private BatchJobConfig load() {
		final BatchJobConfig config = new BatchJobConfig();
		final Map<String, JobBean> jobMap = new HashMap<>();

		loadConfigFromS3(jobMap);
		if (CollectionUtils.isEmpty(jobMap)) {
			config.setJobMap(getJobMap());
		} else {
			config.setJobMap(jobMap);
		}
		return config;
	}

	@SuppressWarnings("unchecked")
	private void loadConfigFromS3(final Map<String, JobBean> jobMap) {
		final String profile = env.getActiveProfiles()[0];

		if (ApplicationProfiles.LOCAL.equalsIgnoreCase(profile) || ApplicationProfiles.TEST.equalsIgnoreCase(profile)) {
			return;
		}
		InputStream configStream = null;
		try {
			final String s3key = String.format(JOB_CONFIG_KEY, profile);

			configStream = s3FileUtils.readFile(JOB_CONFIG_BUCKET, s3key);
			final Map<String, Map<?, ?>> jobMapS = JSONUtils.streamToObject(configStream, HashMap.class);
			for (final String key : jobMapS.keySet()) {
				final Map<String, Object> job = (Map<String, Object>) jobMapS.get(key);
				jobMap.put(key, JobBean.convertToBean(job));
			}
			_LOGGER.info("Job Config loaded from S3");
		} catch (final Exception e) {
			_LOGGER.error("loadConfigFromS3 FAILED due to " + e.getMessage());
		} finally {
			if (configStream != null) {
				try {
					configStream.close();
				} catch (final IOException e) {
					// do nothing
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private static Map<String, JobBean> getJobMap() {
		final Map<String, JobBean> jobMap = new HashMap<>();
		Map<String, Map<?, ?>> jobMapS = new HashMap<>();
		try {
			final ClassPathResource resource = new ClassPathResource("batch.json");
			jobMapS = JSONUtils.streamToObject(resource.getInputStream(), HashMap.class);
			for (final String key : jobMapS.keySet()) {
				final Map<String, Object> job = (Map<String, Object>) jobMapS.get(key);
				jobMap.put(key, JobBean.convertToBean(job));
			}
			_LOGGER.info("Job Config loaded from classpath");
		} catch (final IOException ioEx) {
			throw new ServerException(ErrorBean.withError("CONFIG_FILE_MISSING"));
		}

		return jobMap;
	}

}
