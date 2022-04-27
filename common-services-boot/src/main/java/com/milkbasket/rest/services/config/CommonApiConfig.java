package com.milkbasket.rest.services.config;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import com.milkbasket.core.framework.logging.core.AppLogger;
import com.milkbasket.core.framework.logging.manager.LoggingManager;
import com.milkbasket.core.framework.websupport.base.api.ApiConfig;

/**
 *
 * The Class CommonApiConfig
 *
 * @author NitinKhaitan
 * @version $Id: $Id
 */
@Configuration
public class CommonApiConfig extends ApiConfig implements ApplicationListener<ContextRefreshedEvent> {

	private static final AppLogger _LOGGER = LoggingManager.getLogger(CommonApiConfig.class);

	/** The common api client. */
	// @Autowired
	// private com.milkbasket.api.common.client.ApiClient commonApiClient;

	/** {@inheritDoc} */
	@Override
	public void constructApiPath() {
		_LOGGER.debug("Api Client Updation moved to after context creation");
	}

	/** {@inheritDoc} */
	@Override
	public void onApplicationEvent(final ContextRefreshedEvent event) {
		// Assert.notNull(commonApiClient, "commonApiClient is null");
		// commonApiClient.setBasePath(ContextUtils.getApiHost("common", Boolean.TRUE,
		// Protocol.HTTP));
	}

}
