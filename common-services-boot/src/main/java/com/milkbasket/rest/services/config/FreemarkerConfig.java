package com.milkbasket.rest.services.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Configuration;

@org.springframework.context.annotation.Configuration
/**
 * <p>
 * FreemarkerConfig class.
 * </p>
 *
 * @author NitinKhaitan
 * @version $Id: $Id
 */
public class FreemarkerConfig {
	/**
	 * <p>
	 * freeMarkerConfigurer.
	 * </p>
	 *
	 * @return a
	 *         {@link org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer}
	 *         object.
	 */
	@Bean
	@Primary
	@SuppressWarnings("deprecation")
	public FreeMarkerConfigurer freeMarkerConfigurer() {
		final FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
		final Configuration config = new Configuration();
		configurer.setConfiguration(config);
		return configurer;
	}
}
