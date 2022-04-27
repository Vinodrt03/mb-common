package com.milkbasket.rest.services.common.communication.test.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;

import com.milkbasket.core.framework.common.constants.CommonFormats;
import com.milkbasket.core.framework.utility.DateUtils;
import com.milkbasket.rest.services.common.communication.push.bean.PushCustomerBean;
import com.milkbasket.rest.services.common.communication.push.entity.PushCustomerEntity;

/**
 * The Class PushCustomerTestData.
 */
public class PushCustomerTestData {

	public static final String AUTO_KEY = RandomStringUtils.randomAlphabetic(3);

	public static PushCustomerEntity newEntity(int cntVar, Long pushId, Long customerId, Long deviceId) {
		PushCustomerEntity entity = PushCustomerEntity.newInstance();
		Date tempDate = DateUtils.addDays(new Date(), cntVar + 5);
		entity.setPushId(pushId);
		entity.setCustomerId(customerId);
		entity.setDeviceId(deviceId);
		entity.setProcessed(1);
		entity.setProcessedTime(DateUtils.addDays(tempDate, 1));
		entity.setPush(1);

		return entity;
	}

	public static List<PushCustomerEntity> newEntityList(int cntVar, Long pushId, Long customerId, Long deviceId) {
		List<PushCustomerEntity> dataList = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			dataList.add(newEntity(0, pushId, customerId, deviceId));
		}
		return dataList;
	}

	public static PushCustomerBean newBean(int cntVar, Long pushId, Long customerId, Long deviceId) {
		PushCustomerBean bean = PushCustomerBean.newInstance();
		Date tempDate = DateUtils.addDays(new Date(), cntVar + 5);
		bean.setPushId(pushId);
		bean.setCustomerId(customerId);
		bean.setDeviceId(deviceId);
		bean.setProcessed(1);
		bean.setProcessedTime(DateUtils.toString(CommonFormats.DATE_FORMAT, DateUtils.addDays(tempDate, 1)));
		bean.setPush(1);

		return bean;
	}

	public static List<PushCustomerBean> newBeanList(int cntVar, Long pushId, Long customerId, Long deviceId) {
		List<PushCustomerBean> dataList = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			dataList.add(newBean(i, pushId, customerId, deviceId));
		}
		return dataList;
	}

	public static Map<String, PushCustomerBean> getInvalidBeans(int cntVar, Long pushId, Long customerId, Long deviceId) {
		Map<String, PushCustomerBean> dataMap = new HashMap<>();
		PushCustomerBean bean = PushCustomerBean.newInstance();
		dataMap.put("ALL_NULL", bean);
		bean = newBean(0, pushId, customerId, deviceId);
		bean.setPushId(-1L);
		dataMap.put("PUSH_INVALID", bean);

		bean = newBean(0, pushId, customerId, deviceId);
		bean.setCustomerId(-1L);
		dataMap.put("CUSTOMERS_INVALID", bean);

		bean = newBean(0, pushId, customerId, deviceId);
		bean.setDeviceId(-1L);
		dataMap.put("DEVICE_INVALID", bean);

		bean = newBean(0, pushId, customerId, deviceId);
		bean.setProcessedTime(
				RandomStringUtils.randomNumeric(5) + "-" + RandomStringUtils.randomNumeric(2) + "-" + RandomStringUtils.randomNumeric(2));
		dataMap.put("PROCESSED_TIME_INVALID_FORMAT", bean);

		return dataMap;
	}

}
