package com.milkbasket.rest.services.common.communication.test.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;

import com.milkbasket.core.framework.common.constants.CommonFormats;
import com.milkbasket.core.framework.utility.DateUtils;
import com.milkbasket.rest.services.common.communication.push.bean.PushDeviceBean;
import com.milkbasket.rest.services.common.communication.push.entity.PushDeviceEntity;

/**
 * The Class PushDeviceTestData.
 */
public class PushDeviceTestData {

	public static final String AUTO_KEY = RandomStringUtils.randomAlphabetic(3);

	public static PushDeviceEntity newEntity(int cntVar) {
		PushDeviceEntity entity = PushDeviceEntity.newInstance();
		Date tempDate = DateUtils.addDays(new Date(), cntVar + 5);
		entity.setPushId(1L);
		entity.setMulticastId(1L);
		entity.setTotal(1);
		entity.setSuccess(1);
		entity.setFailure(1);
		entity.setProcessed(DateUtils.addDays(tempDate, 1));
		entity.setData(RandomStringUtils.randomAlphanumeric(21845));

		return entity;
	}

	public static List<PushDeviceEntity> newEntityList(int cntVar) {
		List<PushDeviceEntity> dataList = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			dataList.add(newEntity(0));
		}
		return dataList;
	}

	public static PushDeviceBean newBean(int cntVar) {
		PushDeviceBean bean = PushDeviceBean.newInstance();
		Date tempDate = DateUtils.addDays(new Date(), cntVar + 5);
		bean.setPushId(1L);
		bean.setMulticastId(1L);
		bean.setTotal(1);
		bean.setSuccess(1);
		bean.setFailure(1);
		bean.setProcessed(DateUtils.toString(CommonFormats.DATE_FORMAT, DateUtils.addDays(tempDate, 1)));
		bean.setData(RandomStringUtils.randomAlphanumeric(21845));

		return bean;
	}

	public static List<PushDeviceBean> newBeanList(int cntVar) {
		List<PushDeviceBean> dataList = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			dataList.add(newBean(i));
		}
		return dataList;
	}

	public static Map<String, PushDeviceBean> getInvalidBeans(int cntVar) {
		Map<String, PushDeviceBean> dataMap = new HashMap<>();
		PushDeviceBean bean = PushDeviceBean.newInstance();
		dataMap.put("ALL_NULL", bean);

		bean = newBean(0);
		bean.setTotal(-1);
		dataMap.put("TOTAL_INVALID_VALUE_RANGE_LESS", bean);

		bean = newBean(0);
		bean.setSuccess(-1);
		dataMap.put("SUCCESS_INVALID_VALUE_RANGE_LESS", bean);
		//
		// bean.setSuccess(-1);
		// dataMap.put("SUCCESS_INVALID_VALUE_RANGE_MORE", bean);

		bean = newBean(0);
		bean.setFailure(-1);
		dataMap.put("FAILURE_INVALID_VALUE_RANGE_LESS", bean);
		//
		// bean.setFailure(-1);
		// dataMap.put("FAILURE_INVALID_VALUE_RANGE_MORE", bean);
		//
		// bean = newBean(0);
		// bean.setProcessed(RandomStringUtils.randomNumeric(5) + "-" +
		// RandomStringUtils.randomNumeric(2) + "-" +
		// RandomStringUtils.randomNumeric(2));
		// dataMap.put("PROCESSED_INVALID_FORMAT", bean);
		//
		// bean = newBean(0);
		// bean.setData(RandomStringUtils.randomAlphanumeric(21846));
		// dataMap.put("DATA_INVALID_MAX_LENGTH", bean);

		return dataMap;
	}

}
