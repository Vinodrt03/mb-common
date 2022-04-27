package com.milkbasket.rest.services.common.communication.test.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;

import com.milkbasket.rest.services.common.communication.push.bean.PushRequestBean;
import com.milkbasket.rest.services.common.communication.push.entity.PushConstants.Type;
import com.milkbasket.rest.services.common.communication.push.entity.PushEntity;

/**
 * The Class PushTestData.
 */
public class PushTestData {

	public static final String AUTO_KEY = RandomStringUtils.randomAlphabetic(3);

	public static Long invalidDeviceId = Long.valueOf(1);
	public static Long invalidUserId = Long.valueOf(11);

	public interface InvalidKeys {
		String TYPE_INVALID_MAX_LENGTH = "TYPE_INVALID_MAX_LENGTH";
		String MESSAGE_LANG_INVALID_MAX_LENGTH = "MESSAGE_LANG_INVALID_MAX_LENGTH";
		String MESSAGE_DATA_INVALID_MAX_LENGTH = "MESSAGE_DATA_INVALID_MAX_LENGTH";
		String LINK_INVALID_MAX_LENGTH = "LINK_INVALID_MAX_LENGTH";

		String DEVICE_NOT_EXIST = "DEVICE_NOT_EXIST";
	}

	public static PushEntity newEntity(int cntVar) {
		final PushEntity entity = PushEntity.newInstance();

		entity.setType(RandomStringUtils.randomAlphanumeric(10));
		entity.setMessage(RandomStringUtils.randomAlphanumeric(255));
		entity.setMessageLang("PUSH_TEST_DEFAULT_TEMPLATE");
		entity.setMessageData("");
		entity.setLink(RandomStringUtils.randomAlphanumeric(255));
		entity.setTotal(1);

		return entity;
	}

	public static List<PushEntity> newEntityList(int cntVar) {
		final List<PushEntity> dataList = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			dataList.add(newEntity(0));
		}
		return dataList;
	}

	public static PushRequestBean newBean(int cntVar) {
		final PushRequestBean bean = PushRequestBean.newInstance();

		final List<Long> userIds = new ArrayList<Long>();
		// userIds.add(invalidUserId);
		userIds.add(11l);
		bean.setUserIds(userIds);

		bean.setType(Type.MESSAGE);
		bean.setMessageLang("PUSH_TEST_DEFAULT_TEMPLATE");
		final HashMap<String, Object> msgData = new HashMap<String, Object>();
		msgData.put("name", "Nitin");
		msgData.put("status", "Testing...");
		bean.setMessageData(msgData);
		bean.setLink(RandomStringUtils.randomAlphanumeric(255));
		return bean;
	}

	public static List<PushRequestBean> newBeanList(int cntVar) {
		final List<PushRequestBean> dataList = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			dataList.add(newBean(i));
		}
		return dataList;
	}

	public static Map<String, PushRequestBean> getInvalidBeans(int cntVar) {
		final Map<String, PushRequestBean> dataMap = new HashMap<>();
		PushRequestBean bean = PushRequestBean.newInstance();
		dataMap.put("ALL_NULL", bean);

		// bean = newBean(0);
		// bean.setType(Type.MESSAGE);
		// dataMap.put(InvalidKeys.TYPE_INVALID_MAX_LENGTH, bean);

		bean = newBean(0);
		bean.setMessageLang(RandomStringUtils.randomAlphabetic(257));
		dataMap.put(InvalidKeys.MESSAGE_LANG_INVALID_MAX_LENGTH, bean);

		// bean = newBean(0);
		// bean.setContextData(new HashMap<String, Object>());
		// dataMap.put(InvalidKeys.MESSAGE_DATA_INVALID_MAX_LENGTH, bean);

		bean = newBean(0);
		bean.setLink(RandomStringUtils.randomAlphanumeric(256));
		dataMap.put(InvalidKeys.LINK_INVALID_MAX_LENGTH, bean);

		return dataMap;
	}

	public static Map<String, PushRequestBean> getInvalidSendBeans(int cntVar) {
		final Map<String, PushRequestBean> dataMap = new HashMap<>();
		PushRequestBean bean = PushRequestBean.newInstance();

		bean = newBean(0);
		final List<Long> userIds = new ArrayList<Long>();
		userIds.add(Long.valueOf(999000));
		bean.setUserIds(userIds);
		dataMap.put(InvalidKeys.DEVICE_NOT_EXIST, bean);

		return dataMap;
	}

}
