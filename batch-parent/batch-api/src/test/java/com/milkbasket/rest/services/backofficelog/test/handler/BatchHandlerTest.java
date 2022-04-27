package com.milkbasket.rest.services.backofficelog.test.handler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.testng.annotations.Test;

import com.milkbasket.core.framework.common.constants.CommonFormats;
import com.milkbasket.core.framework.test.WebTestConfiguration;
import com.milkbasket.core.framework.utility.DateUtils;
import com.milkbasket.rest.shared.job.bean.BatchJobRequestBean;

public class BatchHandlerTest extends WebTestConfiguration {

	/** The headers. */
	Map<String, String> headers = new HashMap<>();

	@Test(priority = 10)
	public void test_batch() {

		final String dataListHandler = "/batch";

		final BatchJobRequestBean request = new BatchJobRequestBean();
		request.setJobKey("NOTIFY_VENDOR");
		request.setHubId("8");
		request.setDate(DateUtils.toString(CommonFormats.DATE_FORMAT, new Date()));

		post(dataListHandler, request, headers, null, null).then().statusCode(HttpStatus.NO_CONTENT.value()).extract().response();
	}

}
