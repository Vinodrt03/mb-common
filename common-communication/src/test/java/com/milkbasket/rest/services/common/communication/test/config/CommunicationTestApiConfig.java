/*
 * @author Sushant Copyright milkbasket.com
 */
package com.milkbasket.rest.services.common.communication.test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.milkbasket.core.framework.common.constants.ApplicationProfiles;
import com.milkbasket.core.framework.websupport.base.api.ApiConfig;

/**
 * The Class CommunicationTestApiConfig.
 */
@Configuration
@Profile(ApplicationProfiles.TEST)
public class CommunicationTestApiConfig extends ApiConfig {

	@Override
	public void constructApiPath() {
	}

}
