package com.milkbasket.rest.services.push.common.test.mapper;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.milkbasket.rest.services.entity.base.mapping.MappingConfig;
import com.milkbasket.rest.services.push.common.bean.DeviceBean;
import com.milkbasket.rest.services.communication.entity.DeviceEntity;
import com.milkbasket.rest.services.communication.entity.DeviceConstants.Device;
import com.milkbasket.rest.services.push.common.mapper.DeviceMapper;

@Test
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { DeviceMapper.class })
public class DeviceMapperTest extends AbstractTestNGSpringContextTests {

	private DeviceBean bean;
	private DeviceEntity entity;

	@Autowired
	private DeviceMapper deviceMapper;

	@BeforeTest
	public void setUp() {
		bean = new DeviceBean();
		entity = new DeviceEntity();
		entity.setId(1L);
		entity.setCustomerId(1L);
		entity.setDeviceOs(Device.getValue(Device.AND));
		entity.setPushId("1");
		entity.setUdid(RandomStringUtils.randomAlphanumeric(45));
		entity.setDeviceLogout(1);

		// entity.setFlag(1);
		bean.setId(1L);
		bean.setCustomerId(1L);
		bean.setDeviceOs(Device.AND);
		bean.setPushId("1");
		bean.setUdid(RandomStringUtils.randomAlphanumeric(45));
		bean.setDeviceLogout(1);
		// bean.setFlag(1);

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
		assertNotNull(deviceMapper);
	}

	@Test(priority = 40)
	public void testMapper_hasMappingConfig() {
		MappingConfig config = deviceMapper.getMappingConfig();
		assertNotNull(config);
		assertTrue(config.getMappingTypeConfig().size() > 0);
	}

	@Test(priority = 60)
	public void testMapper_validateBeanToEntityConversion() {
		DeviceEntity pgEntity = new DeviceEntity();
		deviceMapper.buildEntity(pgEntity, bean);

		DeviceBean conBean = new DeviceBean();
		deviceMapper.buildBean(pgEntity, conBean);

		assertEquals(conBean.getId(), bean.getId());
		assertEquals(conBean.getCustomerId(), bean.getCustomerId());
		// assertEquals(conBean.getDeviceOs(), bean.getDeviceOs());
		assertEquals(conBean.getPushId(), bean.getPushId());
		assertEquals(conBean.getUdid(), bean.getUdid());
		assertEquals(conBean.getDeviceLogout(), bean.getDeviceLogout());

	}

	@Test(priority = 70)
	public void testMapper_validateEntityToBeanConversion() {
		DeviceBean pgBean = DeviceBean.newInstance();
		deviceMapper.buildBean(entity, pgBean);

		DeviceEntity conEntity = DeviceEntity.newInstance();
		deviceMapper.buildEntity(conEntity, pgBean);

		assertEquals(conEntity.getId(), entity.getId());
		assertEquals(conEntity.getCustomerId(), entity.getCustomerId());

		// assertEquals(conEntity.getDeviceOs(), entity.getDeviceOs());
		assertEquals(conEntity.getPushId(), entity.getPushId());
		assertEquals(conEntity.getUdid(), entity.getUdid());
		assertEquals(conEntity.getDeviceLogout(), entity.getDeviceLogout());
		assertEquals(conEntity.getFlag(), entity.getFlag());

	}

	@Test(priority = 80)
	public void testMapper_validateAnotantionConfig() {
		assertNotNull(entity.getCustomerId());
		assertNotNull(entity.getDeviceOs());
		assertNotNull(entity.getPushId());
		assertNotNull(entity.getUdid());
		assertNotNull(entity.getDeviceLogout());
		assertNotNull(entity.getFlag());

		entity.setCustomerId(null);
		entity.setDeviceOs(null);
		entity.setPushId(null);
		entity.setUdid(null);
		entity.setDeviceLogout(null);
		entity.setFlag(null);

		assertNotNull(deviceMapper.getAnnotationConfig());
	}

	/**
	 * Test mapper validate entity type.
	 */
	@Test(priority = 90)
	public void testMapper_validateEntityType() {
		assertEquals(deviceMapper.getEntityType().getName(), DeviceEntity.class.getName());
	}

}
