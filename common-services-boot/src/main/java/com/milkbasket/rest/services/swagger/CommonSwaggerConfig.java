package com.milkbasket.rest.services.swagger;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.milkbasket.core.framework.common.constants.ApplicationProfiles;
import com.milkbasket.core.framework.websupport.swagger.SwaggerApiInfo;
import com.milkbasket.core.framework.websupport.swagger.SwaggerConfig;

@Component
/**
 * <p>
 * CommonSwaggerConfig class.
 * </p>
 *
 * @author NitinKhaitan
 * @version $Id: $Id
 */
@Profile(value = { ApplicationProfiles.DEVELOPMENT, ApplicationProfiles.TEST })
public class CommonSwaggerConfig extends SwaggerConfig {

	/** Constant <code>COMMON_BOOT_MODULE="common"</code> */
	public static final String COMMON_BOOT_MODULE = "common";

	/** {@inheritDoc} */
	@Override
	public SwaggerApiInfo getApiInfo() {
		return getSwaggerFileInfo(COMMON_BOOT_MODULE);
	}

}
