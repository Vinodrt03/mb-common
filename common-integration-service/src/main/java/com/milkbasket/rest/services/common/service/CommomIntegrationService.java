package com.milkbasket.rest.services.common.service;

import com.milkbasket.rest.shared.bean.PushOrSmsRequestBean;

/**
 * The Interface CommomIntegrationService.
 *
 * @author Sanjeev Jha
 * @version $Id: $Id
 */
public interface CommomIntegrationService {

	void sendPushOrSms(PushOrSmsRequestBean bean);

}
