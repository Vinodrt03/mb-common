package com.milkbasket.rest.services.common.communication.test.handler;

import static org.testng.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.milkbasket.core.framework.common.utils.ContextUtils;
import com.milkbasket.core.framework.test.WebTestConfiguration;
import com.milkbasket.rest.services.common.communication.email.bean.EmailAttachment;
import com.milkbasket.rest.services.common.communication.email.bean.EmailRequestBean;
import com.milkbasket.rest.services.common.communication.email.entity.AttachmentEntity;
import com.milkbasket.rest.services.common.communication.email.entity.EmailEntity;
import com.milkbasket.rest.services.common.communication.email.repository.EmailMongoDBRepositoryImpl;

import io.restassured.response.Response;

/**
 * The Class EmailsHandlerTest.
 */
public class EmailHandlerTest extends WebTestConfiguration {

	/** The new id. */
	private Long newId = -1L;

	/** List of numbers. */
	private List<String> to = new ArrayList<>();

	/** List of numbers. */
	private List<String> cc = new ArrayList<>();

	/** List of numbers. */
	private List<String> bcc = new ArrayList<>();

	/** List of numbers. */
	private List<String> to2 = new ArrayList<>();

	/** List of numbers. */
	private List<String> cc2 = new ArrayList<>();

	/** List of numbers. */
	private List<String> bcc2 = new ArrayList<>();

	/** List of numbers. */
	private List<String> instantTo = new ArrayList<>();

	/** List of context Data (if any). */
	private Map<String, Object> contextData = new HashMap<>();

	/** The bean. */
	private EmailRequestBean bean;

	private String templateName = "EMAIL_TEST_DEFAULT_TEMPLATE";

	Map<String, String> headers = new HashMap<>();

	@BeforeTest
	public void test_prepareData() {
		instantTo.add("testinstantto1@domain.com");
		instantTo.add("testinstantto2@domain.com");

		firstEntity();
		secondEntity();

		contextData.put("name", "Nitin");
		contextData.put("status", "pass");
	}

	private void firstEntity() {
		to.add("testTo1@to.com");
		to.add("testto2@to.com");

		cc.add("testcc1@to.com");
		cc.add("testcc2@to.com");

		bcc.add("testbcc1@to.com");
		bcc.add("testbcc2@to.com");
	}

	private void secondEntity() {
		to2.add("testto2.1@domain.com");
		to2.add("testto2.2@domain.com");

		cc2.add("testcc2.1@domain.com");
		cc.add("testcc2.2@domain.com");

		bcc2.add("testbcc2.1@domain.com");
		bcc2.add("testbcc2.2@domain.com");
	}

	@Test(priority = 10)
	public void test_insert_mongo() {
		try {

			final EmailEntity entity = EmailEntity.newInstance();

			entity.setTemplateName(templateName);
			entity.setToIds("testTo@test.com");
			entity.setCcIds("testCc@test.com");
			entity.setBccIds("testBcc@test.com");
			entity.setFromEmailId("testFrom@test.com");
			entity.setFromEmailName("Test From");
			entity.setMessage("Sample Email Message");
			entity.setProvider("Test Provider");

			final Set<AttachmentEntity> emailAttachments = new HashSet<>();

			final AttachmentEntity emailAttachment = new AttachmentEntity();
			emailAttachment.setData("abcd".getBytes());
			emailAttachment.setMimetype("text/html");
			emailAttachment.setName("test file 1");
			emailAttachments.add(emailAttachment);
			entity.setAttachments(emailAttachments);
			final EmailMongoDBRepositoryImpl repo = ContextUtils.getBean(EmailMongoDBRepositoryImpl.class);
			repo.saveEmail(entity);

		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test emails save.
	 */
	@Test(priority = 2010)
	public void test_emailsRequestSend() {
		bean = EmailRequestBean.newInstance();

		bean.setTemplateName(templateName);
		bean.setContextData(contextData);
		bean.setToIds(to);
		bean.setCcIds(cc);
		bean.setBccIds(bcc);

		final List<EmailAttachment> emailAttachments = new ArrayList<>();

		final EmailAttachment emailAttachment = new EmailAttachment();
		emailAttachment.setData("abcd".getBytes());
		emailAttachment.setMimeType("text/html");
		emailAttachment.setName("test file 1");
		emailAttachments.add(emailAttachment);

		bean.setAttachments(emailAttachments);

		final Response response = post("/communication/emails", bean, headers, null, null).then().statusCode(HttpStatus.CREATED.value()).extract()
				.response();
		newId = asObject(response.body().asString(), Long.class);

		assertNotNull(newId);
	}
}
