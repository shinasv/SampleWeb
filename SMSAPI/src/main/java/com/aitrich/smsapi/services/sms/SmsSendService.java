package com.aitrich.smsapi.services.sms;

import com.aitrich.smsapi.model.SmsApi;
import com.aitrich.smsapi.model.SmsOutgoingMaster;

public interface SmsSendService {
	void sendSMS(SmsOutgoingMaster smsOutgoingMaster);

	void redirectSMS(SmsOutgoingMaster smsOutgoingMaster);

	void submitSMSToUrl(String mobileNo, String message, String senderId,
			String uniqueId, SmsApi smsApi);
}
