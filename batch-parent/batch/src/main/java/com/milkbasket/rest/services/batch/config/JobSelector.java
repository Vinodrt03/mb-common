package com.milkbasket.rest.services.batch.config;

import java.util.Map;

import com.milkbasket.core.framework.common.bean.ErrorBean;
import com.milkbasket.core.framework.common.exception.ServerException;
import com.milkbasket.core.framework.common.utils.ContextUtils;
import com.milkbasket.core.framework.utility.ObjectUtils;
import com.milkbasket.rest.services.batch.bean.BatchJobConfig;
import com.milkbasket.rest.services.batch.bean.JobBean;
import com.milkbasket.rest.services.batch.bean.JobEnvBean;

/**
 * <p>
 * JobSelector class.
 * </p>
 *
 * @author nitin
 * @version $Id: $Id
 */
public class JobSelector {
	/**
	 * <p>
	 * getJobDetail.
	 * </p>
	 *
	 * @param jobKey
	 *            a {@link java.lang.String} object.
	 * @return a {@link com.milkbasket.rest.services.batch.bean.JobBean} object.
	 */
	public static JobBean getJobDetail(final String jobKey) {

		final Map<String, JobBean> jobMap = getJobMap();

		final JobBean jobBean = jobMap.get(jobKey);

		if (jobBean == null) {
			throw new ServerException(ErrorBean.withError("JOB_CONFIG_MISSING"));
		}

		return jobBean;
	}

	public static JobBean getJobDetail(final JobEnvBean jobEnvBean) {
		final Map<String, JobBean> jobMap = getJobMap();
		final JobBean bean = jobMap.get(jobEnvBean.getJobKey());
		if (bean == null) {
			throw new ServerException(ErrorBean.withError("JOB_CONFIG_MISSING"));
		}
		final JobBean jobBean = (JobBean) ObjectUtils.deepCopy(bean);
		jobBean.replaceEnvVars(jobEnvBean);
		return jobBean;
	}

	private static Map<String, JobBean> getJobMap() {
		final BatchJobConfig config = ContextUtils.getBean(BatchJobConfig.class);
		return config.getJobMap();
	}

}
