package com.milkbasket.rest.services.common.service;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.milkbasket.core.framework.logging.core.AppLogger;
import com.milkbasket.core.framework.logging.manager.LoggingManager;
import com.milkbasket.core.framework.utility.JSONUtils;
import com.milkbasket.rest.shared.url.shortner.bean.AndroidInfoBean;
import com.milkbasket.rest.shared.url.shortner.bean.DynamicLinkInfoBean;
import com.milkbasket.rest.shared.url.shortner.bean.FirebaseDynamicLinkRequest;
import com.milkbasket.rest.shared.url.shortner.bean.FirebaseDynamicLinkResponse;
import com.milkbasket.rest.shared.url.shortner.bean.IosInfoBean;
import com.milkbasket.rest.shared.url.shortner.bean.SuffixBean;
import com.milkbasket.rest.shared.url.shortner.config.FirebaseDynamicLinkConfig;
import com.milkbasket.rest.shared.url.shortner.utils.FirebaseDynamicLinkErrorHandler;

@Service
public class FirebaseDynamicLinkAdaptor {

	private static final AppLogger _LOGGER = LoggingManager.getLogger(FirebaseDynamicLinkAdaptor.class);

	private static final RequestConfig REQUEST_CONFIG = getRequestConfig().build();

	@Autowired
	private FirebaseDynamicLinkConfig firebaseDynamicLinkConfig;

	public String getDynamicShortLink(final String longUrl) {

		FirebaseDynamicLinkResponse responseBean = null;

		try {

			final HttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(REQUEST_CONFIG).build();

			final FirebaseDynamicLinkRequest firebaseDynamicLinkRequest = getRequest(longUrl);

			final String requestStr = JSONUtils.objectToJson(firebaseDynamicLinkRequest).toString();
			final StringEntity params = new StringEntity(requestStr);
			_LOGGER.info("Firebase dynamic link request: " + requestStr);

			final HttpPost post = new HttpPost(firebaseDynamicLinkConfig.getBaseDeepLinkUrl());
			post.setHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE);
			post.setEntity(params);

			final HttpResponse response = client.execute(post);
			final HttpEntity entity = response.getEntity();
			final String responseStr = EntityUtils.toString(entity);
			_LOGGER.info("Firebase dynamic link response: " + responseStr);

			responseBean = JSONUtils.jsonToObject(responseStr, FirebaseDynamicLinkResponse.class);

		} catch (final Exception e) {
			FirebaseDynamicLinkErrorHandler.handleError(e, "getDynamicShortLink");
		}
		if (responseBean != null) {
			return responseBean.getShortLink();
		}
		return "";
	}

	private FirebaseDynamicLinkRequest getRequest(final String longUrl) {
		final FirebaseDynamicLinkRequest firebaseDynamicLinkRequest = new FirebaseDynamicLinkRequest();
		final DynamicLinkInfoBean dynamicLinkInfo = new DynamicLinkInfoBean();
		final AndroidInfoBean androidInfo = new AndroidInfoBean();
		final IosInfoBean iosInfo = new IosInfoBean();
		final SuffixBean suffix = new SuffixBean();

		dynamicLinkInfo.setLink(longUrl);
		dynamicLinkInfo.setDomainUriPrefix(firebaseDynamicLinkConfig.getDomainUrlPrefix());
		androidInfo.setAndroidPackageName(firebaseDynamicLinkConfig.getPackageName());
		iosInfo.setIosBundleId(firebaseDynamicLinkConfig.getPackageName());
		iosInfo.setIosAppStoreId(firebaseDynamicLinkConfig.getIosAppId());
		dynamicLinkInfo.setAndroidInfo(androidInfo);
		dynamicLinkInfo.setIosInfo(iosInfo);
		suffix.setOption(firebaseDynamicLinkConfig.getSuffixOption());
		firebaseDynamicLinkRequest.setSuffix(suffix);
		firebaseDynamicLinkRequest.setDynamicLinkInfo(dynamicLinkInfo);
		return firebaseDynamicLinkRequest;
	}

	private static RequestConfig.Builder getRequestConfig() {
		RequestConfig.Builder requestBuilder = RequestConfig.custom();
		requestBuilder = requestBuilder.setConnectTimeout(15 * 1000);
		requestBuilder = requestBuilder.setConnectionRequestTimeout(15 * 1000);
		return requestBuilder.setSocketTimeout(15 * 1000);
	}

}
