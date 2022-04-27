package com.milkbasket.rest.services.common.communication.test.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;

import com.milkbasket.core.framework.common.constants.CommonFormats;
import com.milkbasket.core.framework.utility.DateUtils;
import com.milkbasket.rest.services.common.communication.push.bean.PushDeviceResultBean;
import com.milkbasket.rest.services.common.communication.push.entity.PushDeviceResultEntity;

/**
 * The Class PushDeviceResultTestData.
 */
public class PushDeviceResultTestData {

	public static final String AUTO_KEY = RandomStringUtils.randomAlphabetic(3);

	public static PushDeviceResultEntity newEntity(int cntVar, Long pushCustomerId) {
		PushDeviceResultEntity entity = PushDeviceResultEntity.newInstance();
		Date tempDate = DateUtils.addDays(new Date(), cntVar + 5);
		entity.setPushDeviceId(1L);
		entity.setPushCustomerId(pushCustomerId);
		entity.setMessageId("1");
		entity.setError(RandomStringUtils.randomAlphanumeric(255));
		entity.setSent(DateUtils.addDays(tempDate, 1));

		return entity;
	}

	public static List<PushDeviceResultEntity> newEntityList(int cntVar, Long pushCustomerId) {
		List<PushDeviceResultEntity> dataList = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			dataList.add(newEntity(0, pushCustomerId));
		}
		return dataList;
	}

	public static PushDeviceResultBean newBean(int cntVar, Long pushCustomerId) {
		PushDeviceResultBean bean = PushDeviceResultBean.newInstance();
		Date tempDate = DateUtils.addDays(new Date(), cntVar + 5);
		bean.setPushDeviceId(1L);
		bean.setPushCustomerId(pushCustomerId);
		bean.setMessageId("1");
		bean.setError(RandomStringUtils.randomAlphanumeric(255));
		bean.setSent(DateUtils.toString(CommonFormats.DATE_FORMAT, DateUtils.addDays(tempDate, 1)));

		return bean;
	}

	public static List<PushDeviceResultBean> newBeanList(int cntVar, Long pushCustomerId) {
		List<PushDeviceResultBean> dataList = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			dataList.add(newBean(i, pushCustomerId));
		}
		return dataList;
	}

	public static Map<String, PushDeviceResultBean> getInvalidBeans(int cntVar, Long pushCustomerId) {
		Map<String, PushDeviceResultBean> dataMap = new HashMap<>();
		PushDeviceResultBean bean = PushDeviceResultBean.newInstance();
		dataMap.put("ALL_NULL", bean);
		bean = newBean(0, pushCustomerId);
		bean.setPushCustomerId(-1L);
		dataMap.put("PUSH_CUSTOMER_INVALID", bean);

		bean = newBean(0, pushCustomerId);
		bean.setError(RandomStringUtils.randomAlphanumeric(256));
		dataMap.put("ERROR_INVALID_MAX_LENGTH", bean);

		bean = newBean(0, pushCustomerId);
		bean.setSent(RandomStringUtils.randomNumeric(5) + "-" + RandomStringUtils.randomNumeric(2) + "-" + RandomStringUtils.randomNumeric(2));
		dataMap.put("SENT_INVALID_FORMAT", bean);

		return dataMap;
	}

}
