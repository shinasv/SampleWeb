package com.aitrich.smsapi.controller.ws;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aitrich.smsapi.model.DeliveryStatus;
import com.aitrich.smsapi.model.MobileApiBlockList;
import com.aitrich.smsapi.model.SmsOutgoingMasterDetail;
import com.aitrich.smsapi.services.EndUserCreditService;
import com.aitrich.smsapi.services.EndUserService;
import com.aitrich.smsapi.services.MobileApiBlockListService;
import com.aitrich.smsapi.services.ResellerCreditService;
import com.aitrich.smsapi.services.ResellerService;
import com.aitrich.smsapi.services.SmsOutgoingMasterDetailService;
import com.aitrich.smsapi.services.SmsOutgoingMasterService;
import com.aitrich.smsapi.services.sms.SmsSendService;
import com.aitrich.smsapi.util.DateUtility;
import com.aitrich.smsapi.util.SMS_ReasonCode;

/**
 * <pre>
 * Method Handles the incoming SMS Report from Vendor
 * 
 * @author : SHINAS
 * @since : June 17, 2014
 * @version : 1.0
 */

@Controller
public class SMSVendorReportController {
	@Autowired
	private SmsOutgoingMasterService smsOutgoingMasterService;
	@Autowired
	private SmsOutgoingMasterDetailService smsOutgoingMasterDetailService;
	@Autowired
	private EndUserService endUserService;
	@Autowired
	private ResellerService resellerService;
	@Autowired
	private ResellerCreditService resellerCreditService;
	@Autowired
	private EndUserCreditService endUserCreditService;
	@Autowired
	private MobileApiBlockListService mobileApiBlockListService;
	@Autowired
	private SmsSendService smsSendService;

	/**
	 * @author SHINAS
	 * @since 10-Jul-2014
	 * @param rqst
	 * @return String
	 */
	@RequestMapping(value = "/smsReport")
	public @ResponseBody String getReportFromVendor(HttpServletRequest rqst) {
		long uniqueSmsId = 0;

		if (rqst.getParameterMap().containsKey("myid")) {
			uniqueSmsId = Long.valueOf(rqst.getParameter("myid"));
		} else if (rqst.getParameterMap().containsKey("msgid")) {
			uniqueSmsId = Long.valueOf(rqst.getParameter("msgid"));
		}
		String reasonCode;
		if (rqst.getParameterMap().containsKey("res")) {
			reasonCode = rqst.getParameter("res");
		} else {
			reasonCode = getReasonCodeFromStatus(rqst.getParameter("status"));
		}

		SmsOutgoingMasterDetail outgoingMasterDetail = smsOutgoingMasterDetailService
				.findById(uniqueSmsId);

		outgoingMasterDetail.setReasonCode(reasonCode);
		outgoingMasterDetail
				.setDeliveryStatus(getDeliveryStatusFromReasonCode(reasonCode));

		smsOutgoingMasterDetailService.saveOrUpdate(outgoingMasterDetail);

		handleApiMobileBlockingAndRedirect(outgoingMasterDetail);
		return "Success";
	}

	/**
	 * @author SHINAS
	 * @since 10-Jul-2014
	 * @param parameter
	 * @return String
	 */
	private String getReasonCodeFromStatus(String parameter) {
		String reasoneCode = null;
		switch (parameter) {
		case "SUBMITTED":

			break;
		case "DELIVRD":
			reasoneCode = SMS_ReasonCode.DELIVERED;
			break;
		case "UNDELIVRD":

			break;
		case "REJECTED":

			break;

		default:
			break;
		}
		return reasoneCode;
	}

	/**
	 * @author SHINAS
	 * @since 10-Jul-2014
	 * @param outgoingMasterDetail
	 *            void
	 */
	private void handleApiMobileBlockingAndRedirect(
			SmsOutgoingMasterDetail outgoingMasterDetail) {
		MobileApiBlockList unblockedOrBlockedApiForMobile = mobileApiBlockListService
				.findByMobile_ApiId(outgoingMasterDetail.getSmsOutgoingMaster()
						.getMobileNo(), outgoingMasterDetail.getSmsApi()
						.getId());
		if (outgoingMasterDetail.getDeliveryStatus() == DeliveryStatus.DELIVERED) {
			if (unblockedOrBlockedApiForMobile != null)
				/*
				 * Delete the Mobile_Api block entry since this API can send SMS
				 * to this number and is not blocked by this API for
				 * continuously 3 times
				 */
				mobileApiBlockListService.delete(unblockedOrBlockedApiForMobile
						.getId());

			outgoingMasterDetail.getSmsOutgoingMaster().setDeliveredTime(
					new Date());
			outgoingMasterDetail.getSmsOutgoingMaster().setIsDelivered(true);
			smsOutgoingMasterService.saveOrUpdate(outgoingMasterDetail
					.getSmsOutgoingMaster());
		}

		if (!outgoingMasterDetail.getSmsOutgoingMaster().getIsDelivered()
				&& needBlockApiAndRedirectSms(outgoingMasterDetail
						.getReasonCode())) {
			if (unblockedOrBlockedApiForMobile == null) {
				unblockedOrBlockedApiForMobile = new MobileApiBlockList();
				unblockedOrBlockedApiForMobile.setMobile(outgoingMasterDetail
						.getSmsOutgoingMaster().getMobileNo());
				unblockedOrBlockedApiForMobile.setSmsApi(outgoingMasterDetail
						.getSmsApi());
				unblockedOrBlockedApiForMobile.setCount(1);
			} else {
				Date todaysDate = DateUtility.removeTime(new Date());
				Date apiBlockedDate = DateUtility
						.removeTime(unblockedOrBlockedApiForMobile.getDate());
				unblockedOrBlockedApiForMobile.setDate(new Date());
				if (apiBlockedDate.before(todaysDate)
						&& unblockedOrBlockedApiForMobile.getCount() < 3) {
					unblockedOrBlockedApiForMobile
							.setCount(unblockedOrBlockedApiForMobile.getCount() + 1);
					if (unblockedOrBlockedApiForMobile.getCount() == 3)
						unblockedOrBlockedApiForMobile.setIsBlocked(true);
				} else {

				}

			}
			mobileApiBlockListService
					.saveOrUpdate(unblockedOrBlockedApiForMobile);
			smsSendService.redirectSMS(outgoingMasterDetail
					.getSmsOutgoingMaster());
		}
	}

	/**
	 * @author SHINAS
	 * @since 10-Jul-2014
	 * @param reasonCode
	 * @return boolean
	 */
	private boolean needBlockApiAndRedirectSms(String reasonCode) {
		boolean needBlockApi = false;
		switch (reasonCode) {
		case SMS_ReasonCode.NETWORK_ERROR:
			needBlockApi = true;
			break;

		case SMS_ReasonCode.BARRING:
			needBlockApi = true;
			break;

		case SMS_ReasonCode.INVALID_SENDER_ID:
			needBlockApi = true;
			break;

		case SMS_ReasonCode.NDNC_FAILURE:
			needBlockApi = true;
			break;

		case SMS_ReasonCode.UNKNOWN_ERROR:
			needBlockApi = true;
			break;

		default:
			break;
		}
		return needBlockApi;
	}

	/**
	 * @author SHINAS
	 * @since 10-Jul-2014
	 * @param reasonCode
	 * @return DeliveryStatus
	 */
	private DeliveryStatus getDeliveryStatusFromReasonCode(String reasonCode) {
		DeliveryStatus deliveryStatus = null;
		switch (reasonCode) {
		case SMS_ReasonCode.DELIVERED:
			deliveryStatus = DeliveryStatus.DELIVERED;
			break;
		case SMS_ReasonCode.INVALID_NUMBER:
			deliveryStatus = DeliveryStatus.UNDELIVERED;
			break;
		case SMS_ReasonCode.ABSENT_SUBSCRIBER:
			deliveryStatus = DeliveryStatus.UNDELIVERED;
			break;
		case SMS_ReasonCode.MEMORY_CAPACITY_EXCEEDED:
			deliveryStatus = DeliveryStatus.DELIVERED;
			break;
		case SMS_ReasonCode.MOBILE_EQUIPMENT_ERROR:
			deliveryStatus = DeliveryStatus.DELIVERED;
			break;
		case SMS_ReasonCode.NETWORK_ERROR:
			deliveryStatus = DeliveryStatus.UNDELIVERED;
			break;
		case SMS_ReasonCode.BARRING:
			deliveryStatus = DeliveryStatus.UNDELIVERED;
			break;
		case SMS_ReasonCode.INVALID_SENDER_ID:
			deliveryStatus = DeliveryStatus.UNDELIVERED;
			break;
		case "008":
			deliveryStatus = DeliveryStatus.DELIVERED;
			break;
		case SMS_ReasonCode.NDNC_FAILURE:
			deliveryStatus = DeliveryStatus.UNDELIVERED;
			break;
		case SMS_ReasonCode.UNKNOWN_ERROR:
			deliveryStatus = DeliveryStatus.UNDELIVERED;
			break;

		default:
			break;
		}
		return deliveryStatus;
	}

}
