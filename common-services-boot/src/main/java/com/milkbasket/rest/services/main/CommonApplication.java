/*
 * @author Sushant
 * Copyright milkbasket.com
 */
package com.milkbasket.rest.services.main;

import java.util.TimeZone;
import java.util.concurrent.Executor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.milkbasket.core.framework.common.constants.ApplicationProfiles;
import com.milkbasket.core.framework.common.constants.CommonConstants;

/**
 * The Class CommonApplication.
 *
 * @author NitinKhaitan
 * @version $Id: $Id
 */
@SpringBootApplication
@ComponentScan(basePackages = { CommonConstants.BASE_PACKAGE })
@EnableAspectJAutoProxy
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class, MongoDataAutoConfiguration.class })
@EnableAsync
public class CommonApplication extends SpringBootServletInitializer {

	// private static final AppLogger _LOGGER =
	// LoggingManager.getLogger(CommonApplication.class);

	/** {@inheritDoc} */
	@Override
	protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
		return application.sources(CommonApplication.class);
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(final String[] args) {
		final String timezone = System.getProperty(CommonConstants.APPLICATION_TIME_ZONE);
		TimeZone.setDefault(TimeZone.getTimeZone(StringUtils.isNotBlank(timezone) ? timezone : CommonConstants.TZ_ASIA_CALCUTTA));
		final SpringApplication application = new SpringApplication(CommonApplication.class);
		application.setWebApplicationType(WebApplicationType.SERVLET);
		if (System.getProperty(CommonConstants.SPRING_PROFILE_ACTIVE_KEY) == null) {
			System.setProperty(CommonConstants.SPRING_PROFILE_ACTIVE_KEY, ApplicationProfiles.LOCAL);
		}
		System.setProperty(CommonConstants.LOG4J2_FILE_NAME_PROP, "log4j2-" + System.getProperty(CommonConstants.SPRING_PROFILE_ACTIVE_KEY) + ".xml");
		// _LOGGER.info("Test Message");
		application.run(args);
	}

	@Bean
	public Executor taskExecutor() {
		final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(10);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("mb-common-");
		executor.initialize();
		return executor;
	}

}
