/**@author Sanjeev Jha Copyright milkbasket.com*/
package com.milkbasket.rest.services.common.test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.milkbasket.core.framework.common.constants.ApplicationProfiles;
import com.milkbasket.core.framework.websupport.base.api.ApiConfig;

/*** The Class TestApiConfig. */

@Configuration
@Profile(ApplicationProfiles.TEST)
public class CommonIntegrationTestApiConfig extends ApiConfig {

	@Override
	public void constructApiPath() {
	}

}
