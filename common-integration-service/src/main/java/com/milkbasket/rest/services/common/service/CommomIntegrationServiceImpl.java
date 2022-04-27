package com.milkbasket.rest.services.common.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milkbasket.core.framework.logging.core.AppLogger;
import com.milkbasket.core.framework.logging.manager.LoggingManager;
import com.milkbasket.rest.services.common.communication.push.bean.PushRequestBean;
import com.milkbasket.rest.services.common.communication.push.entity.PushConstants;
import com.milkbasket.rest.services.common.communication.push.service.PushService;
import com.milkbasket.rest.services.common.communication.sms.bean.SmsRequestBean;
import com.milkbasket.rest.services.common.communication.sms.service.SmsService;
import com.milkbasket.rest.services.push.common.service.DeviceService;
import com.milkbasket.rest.shared.bean.PushOrSmsRequestBean;
import com.milkbasket.core.framework.communication.sms.constant.SmsConstants;

/**
 * The Class CommomIntegrationServiceImpl.
 *
 * @author Sanjeev Jha
 * @version $Id: $Id
 */
@Service
public class CommomIntegrationServiceImpl implements CommomIntegrationService {

	private static final AppLogger _LOGGER = LoggingManager.getLogger(CommomIntegrationServiceImpl.class);

	@Autowired
	private PushService pushService;

	@Autowired
	private SmsService smsService;

	@Autowired
	private DeviceService deviceService;

	/** {@inheritDoc} */
	@Override
	public void sendPushOrSms(final PushOrSmsRequestBean bean) {
		try {
			final Boolean validPushUser = deviceService.isvalidPushUser(bean.getUserId());

			if (validPushUser) {
				final PushRequestBean pushBean = PushRequestBean.newInstance();
				pushBean.setUserIds(Collections.singletonList(bean.getUserId()));
				pushBean.setMessageLang(bean.getTemplateName());
				pushBean.setLink(bean.getLink());
				pushBean.setType(PushConstants.Type.getType(bean.getType()));
				pushBean.setMessageData(bean.getContextData());
				pushService.send(pushBean);
			}
			if (!validPushUser || bean.getSendSms()) {
				final SmsRequestBean smsBean = SmsRequestBean.newInstance();
				smsBean.setNumbers(Collections.singletonList(bean.getPhone()));
				smsBean.setTemplateName(bean.getTemplateName());
				smsBean.setSendType(SmsConstants.SendType.getType(bean.getType()).toString());
				smsBean.setContextData(bean.getContextData());
				smsBean.setTemplateId(bean.getTemplateId());
				smsService.send(smsBean);
			}
		} catch (final Exception e) {
			_LOGGER.error("Unable to send push or sms message " + e.getMessage());
		}

	}

}
