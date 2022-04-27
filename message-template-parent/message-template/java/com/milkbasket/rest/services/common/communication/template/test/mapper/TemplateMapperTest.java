package com.milkbasket.rest.services.common.communication.template.test.mapper;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.milkbasket.core.framework.dbsupport.jdbc.constants.Flag;
import com.milkbasket.rest.services.common.communication.template.bean.TemplateBean;
import com.milkbasket.rest.services.common.communication.template.entity.TemplateConstants.SendType;
import com.milkbasket.rest.services.common.communication.template.entity.TemplateEntity;
import com.milkbasket.rest.services.common.communication.template.mapper.TemplateMapper;
import com.milkbasket.rest.services.entity.base.mapping.MappingConfig;

@Test
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { TemplateMapper.class })
public class TemplateMapperTest extends AbstractTestNGSpringContextTests {

	private TemplateBean bean;
	private TemplateEntity entity;

	@Autowired
	private TemplateMapper templateMapper;

	@BeforeTest
	public void setUp() {
		bean = TemplateBean.newInstance();
		bean.setId(4L);
		bean.setType("EMAIL");
		bean.setText("This is {owner} test tmplate ");
		bean.setActive(Flag.ACTIVE);
		bean.setName("TEST__ORDER_PLACED");
		bean.setManual(1);
		bean.setSubject("Dummy");
		bean.setModule("BASKET");
		bean.setSendType(SendType.TRANSACTIONAL);

		entity = TemplateEntity.newInstance();
		entity.setId(4L);
		entity.setType("EMAIL");
		entity.setText("This is {owner} test tmplate ");
		entity.setName("TEST__ORDER_PLACED");
		entity.setActive(Flag.ACTIVE);
		entity.setFlag(Flag.ACTIVE);
		entity.setManual(1);
		entity.setSubject("Dummy");
		entity.setModule("BASKET");
		entity.setType(SendType.TRANSACTIONAL.toString());
	}

	@Test(priority = 10)
	public void testBean_isNotNull() {
		assertNotNull(bean);
	}

	@Test(priority = 20)
	public void testEntity_isNotNull() {
		assertNotNull(entity);
	}

	@Test(priority = 30)
	public void testMapper_isNotNull() {
		assertNotNull(templateMapper);
	}

	@Test(priority = 40)
	public void testMapper_hasMappingConfig() {
		MappingConfig config = templateMapper.getMappingConfig();
		assertNotNull(config);
		assertTrue(config.getMappingTypeConfig().size() > 0);
	}

	@Test(priority = 50)
	public void testMapper_validateBeanToEntityConversion() {
		TemplateEntity pgEntity = TemplateEntity.newInstance();
		templateMapper.buildEntity(pgEntity, bean);

		assertEquals(pgEntity.getId(), bean.getId());
		assertEquals(pgEntity.getType(), bean.getType());
		assertEquals(pgEntity.getText(), bean.getText());
		assertEquals(pgEntity.getActive(), bean.getActive());
		// assertEquals(true, true);
	}

	@Test(priority = 60)
	public void testMapper_validateEntityToBeanConversion() {
		TemplateBean pgBean = new TemplateBean();
		templateMapper.buildBean(entity, pgBean);

		assertEquals(pgBean.getId().longValue(), entity.getId().longValue());
		assertEquals(pgBean.getType(), entity.getType());
		assertEquals(pgBean.getText(), entity.getText());
		assertEquals(pgBean.getActive(), entity.getActive());
		// assertEquals(true, true);
	}

	@Test(priority = 70)
	public void testMapper_validateAnnotationConfig() {

		bean.setId(4L);
		bean.setType("EMAIL");
		bean.setText("This is {owner} test tmplate ");
		bean.setName("TEST__ORDER_PLACED");
		bean.setActive(Flag.ACTIVE);
		bean.setManual(1);
		bean.setModule("BASKET");
		bean.setSubject("Dummy");

		assertNotNull(entity.getId());
		assertNotNull(entity.getType());
		assertNotNull(entity.getText());
		assertNotNull(entity.getActive());
		assertNotNull(entity.getFlag());
		assertNotNull(entity.getManual());
		assertNotNull(entity.getModule());
		assertNotNull(entity.getSubject());
		bean.setType(null);
		bean.setText(null);
		bean.setActive(null);
		bean.setName(null);
		bean.setActive(null);
		bean.setManual(null);
		bean.setModule(null);
		bean.setSubject(null);

		assertNotNull(templateMapper.getAnnotationConfig());
	}

	/**
	 * Test mapper entity type.
	 */
	@Test(priority = 80)
	public void testMapper_validateEntityType() {
		assertEquals(templateMapper.getEntityType().getName(), TemplateEntity.class.getName());
	}
}
