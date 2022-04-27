package com.milkbasket.rest.services.common.test.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;

import com.milkbasket.core.framework.utility.DateUtils;
import com.milkbasket.rest.services.push.common.bean.DeviceBean;
import com.milkbasket.rest.services.communication.entity.DeviceEntity;
import com.milkbasket.rest.services.communication.entity.DeviceConstants.Device;

/**
 * The Class DeviceTestData.
 */
public class DeviceTestData {

	public static final String AUTO_KEY = RandomStringUtils.randomAlphabetic(3);

	public static DeviceEntity newEntity(int cntVar, Long customerId) {
		DeviceEntity entity = DeviceEntity.newInstance();
		Date tempDate = DateUtils.addDays(new Date(), cntVar + 5);

		entity.setCustomerId(customerId);
		entity.setDeviceOs(Device.getValue(Device.AND));
		entity.setPushId("1");
		entity.setUdid(RandomStringUtils.randomAlphanumeric(45));
		entity.setDeviceLogout(1);
		entity.setFlag(1);

		return entity;
	}

	public static List<DeviceEntity> newEntityList(int cntVar, Long customerId) {
		List<DeviceEntity> dataList = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			dataList.add(newEntity(0, customerId));
		}
		return dataList;
	}

	public static DeviceBean newBean(int cntVar, Long customerId) {
		DeviceBean bean = DeviceBean.newInstance();
		Date tempDate = DateUtils.addDays(new Date(), cntVar + 5);

		bean.setCustomerId(customerId);
		bean.setDeviceOs(Device.AND);
		bean.setPushId("1");
		bean.setUdid(RandomStringUtils.randomAlphanumeric(45));
		bean.setDeviceLogout(1);

		return bean;
	}

	public static List<DeviceBean> newBeanList(int cntVar, Long customerId) {
		List<DeviceBean> dataList = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			dataList.add(newBean(i, customerId));
		}
		return dataList;
	}

	public static Map<String, DeviceBean> getInvalidBeans(int cntVar, Long customerId) {
		Map<String, DeviceBean> dataMap = new HashMap<>();
		DeviceBean bean = DeviceBean.newInstance();
		dataMap.put("ALL_NULL", bean);
		bean = newBean(0, customerId);
		bean.setCustomerId(-1L);
		dataMap.put("CUSTOMERS_INVALID", bean);

		// bean = newBean(0, customerId);
		// bean.setDeviceOs(Device.ANDROID);
		// dataMap.put("DEVICE_OS_INVALID_MAX_LENGTH", bean);

		bean = newBean(0, customerId);
		bean.setUdid(RandomStringUtils.randomAlphanumeric(46));
		dataMap.put("UDID_INVALID_MAX_LENGTH", bean);

		return dataMap;
	}

}
