/*
 * @author Sushant Copyright milkbasket.com
 */
package com.milkbasket.rest.services.common.event.test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.milkbasket.core.framework.common.constants.ApplicationProfiles;
import com.milkbasket.core.framework.websupport.base.api.ApiConfig;

/**
 * The Class TestApiConfig.
 */
@Configuration
@Profile(ApplicationProfiles.TEST)
public class CommonEventTestConfig extends ApiConfig {

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.milkbasket.core.framework.websupport.api.ApiConfig#constructApiPath()
	 */
	@Override
	public void constructApiPath() {
	}

}
