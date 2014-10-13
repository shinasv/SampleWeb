package com.aitrich.smsapi.services.sms;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.aitrich.smsapi.model.DeliveryStatus;
import com.aitrich.smsapi.model.MobileApiBlockList;
import com.aitrich.smsapi.model.SmsApi;
import com.aitrich.smsapi.model.SmsOutgoingMaster;
import com.aitrich.smsapi.model.SmsOutgoingMasterDetail;
import com.aitrich.smsapi.services.MobileApiBlockListService;
import com.aitrich.smsapi.services.SmsApiService;
import com.aitrich.smsapi.services.SmsOutgoingMasterDetailService;
import com.aitrich.smsapi.services.SmsOutgoingMasterService;
import com.aitrich.smsapi.util.SMS_ReasonCode;

/**
 * <pre>
 * Service class for Handling the SMS forwarding using 
 * appropriate SMS API to Recipients
 * 
 * @author : SHINAS
 * @since : June 17, 2014
 * @version : 1.0
 */

@Service("SmsSendService")
@Scope("prototype")
public class SmsSendServiceImpl implements SmsSendService {
	private Log log = LogFactory.getLog(this.getClass());
	@Autowired
	private SmsApiService smsApiService;
	@Autowired
	private MobileApiBlockListService mobileApiBlockListService;
	@Autowired
	private SmsOutgoingMasterService smsOutgoingMasterService;
	@Autowired
	private SmsOutgoingMasterDetailService smsOutgoingMasterDetailService;

	@Override
	public void sendSMS(SmsOutgoingMaster smsOutgoing) {
		List<SmsApi> smsApis = smsApiService.findAll_MessageApi();
		boolean matchingApiFound = false;
		for (SmsApi smsApi : smsApis) {
			MobileApiBlockList blockedApiForMobile = mobileApiBlockListService
					.findByBlocked_Mobile_ApiId(smsOutgoing.getMobileNo(),
							smsApi.getId());
			if (blockedApiForMobile == null) {
				/* Found API for sending SMS to this mobile No */
				if (smsOutgoing.getMasterDetails() == null
						|| smsOutgoing.getMasterDetails().size() < 1) {
					/* First time submission of SMS goes on here */
					List<SmsOutgoingMasterDetail> masterDetails = new ArrayList<SmsOutgoingMasterDetail>();
					SmsOutgoingMasterDetail detail = new SmsOutgoingMasterDetail();
					detail.setSmsApi(smsApi);
					masterDetails.add(detail);
					smsOutgoing.setMasterDetails(masterDetails);

					smsOutgoingMasterService.saveOrUpdate(smsOutgoing);
					long uniqueId = smsOutgoing.getMasterDetails().get(0)
							.getId();
					submitSMSToUrl(smsOutgoing.getMobileNo(),
							smsOutgoing.getMessage(),
							smsOutgoing.getSenderId(),
							String.valueOf(uniqueId), smsApi);

					deleteApiBlockEntry(smsOutgoing, smsApi);

					matchingApiFound = true;
					break;
				}
			}
		}

		if (!matchingApiFound) {
			/* No matching API found, all are blocked for this number */
			if (smsOutgoing.getMasterDetails() == null
					|| smsOutgoing.getMasterDetails().size() < 1) {
				/*
				 * All API's are previously blocked for this number for the
				 * first chance itself
				 */
				List<SmsOutgoingMasterDetail> masterDetails = new ArrayList<SmsOutgoingMasterDetail>();
				SmsOutgoingMasterDetail detail = new SmsOutgoingMasterDetail();
				detail.setDeliveryStatus(DeliveryStatus.UNDELIVERED);
				detail.setReasonCode(SMS_ReasonCode.API_BLOCKED);
				detail.setNeedDirection(false);
				masterDetails.add(detail);
				smsOutgoing.setMasterDetails(masterDetails);
				smsOutgoing.setNeedRedirection(false);

				smsOutgoingMasterService.saveOrUpdate(smsOutgoing);
			}
		}
	}

	@Override
	public void redirectSMS(SmsOutgoingMaster smsOutgoing) {
		List<SmsApi> smsApis = smsApiService.findAll_MessageApi();
		boolean matchingApiFound = false;
		boolean alreadyDelivered = false;
		boolean apiAlreadyUsed = false;
		for (SmsApi smsApi : smsApis) {
			MobileApiBlockList blockedApiForMobile = mobileApiBlockListService
					.findByBlocked_Mobile_ApiId(smsOutgoing.getMobileNo(),
							smsApi.getId());
			if (blockedApiForMobile == null) {
				/* Found an unblocked API for sending SMS to this mobile No */
				if (smsOutgoing.getMasterDetails() != null
						|| smsOutgoing.getMasterDetails().size() > 0) {
					/*
					 * Check this message is already delivered and this whether
					 * this API has already used for this message
					 */
					for (SmsOutgoingMasterDetail outgoingMasterDetail : smsOutgoing
							.getMasterDetails()) {
						apiAlreadyUsed = false;
						if (outgoingMasterDetail.getDeliveryStatus().equals(
								DeliveryStatus.DELIVERED)) {
							alreadyDelivered = true;
							break;
						} else if (smsApi.getId().equals(
								outgoingMasterDetail.getSmsApi().getId())) {
							apiAlreadyUsed = true;
							break;
						}
					}
					if (alreadyDelivered) {
						matchingApiFound = true;
						break;
					} else if (!apiAlreadyUsed) {
						SmsOutgoingMasterDetail masterDetail = new SmsOutgoingMasterDetail();
						masterDetail.setSmsOutgoingMaster(smsOutgoing);
						masterDetail.setSmsApi(smsApi);

						long uniqueId = smsOutgoingMasterDetailService
								.save(masterDetail);

						submitSMSToUrl(smsOutgoing.getMobileNo(),
								smsOutgoing.getMessage(),
								smsOutgoing.getSenderId(),
								String.valueOf(uniqueId), smsApi);

						deleteApiBlockEntry(smsOutgoing, smsApi);

						matchingApiFound = true;
						break;
					}
				}
			}
		}

		if (!matchingApiFound) {
			/*
			 * Here all the API's are failed to send this SMS to that Mobile No.
			 * So no more redirection required.
			 */
			SmsOutgoingMasterDetail detail = new SmsOutgoingMasterDetail();
			detail.setDeliveryStatus(DeliveryStatus.UNDELIVERED);
			detail.setReasonCode(SMS_ReasonCode.API_BLOCKED);
			detail.setNeedDirection(false);
			smsOutgoing.setNeedRedirection(false);
			smsOutgoing.getMasterDetails().add(detail);

			smsOutgoingMasterService.saveOrUpdate(smsOutgoing);
		}

	}

	/**
	 * <pre>
	 * Method submits SMS to the Vendor URL.
	 * End part of SMS forwarding.
	 * 
	 * @author : SHINAS
	 * @since : June 17, 2014
	 * @version : 1.0
	 */
	@Override
	public void submitSMSToUrl(String mobileNo, String message,
			String senderId, String uniqueId, SmsApi smsApi) {
		String dlr_url = "http://www.parentconnect.in/smsapi/smsReport?"
				/*+ "myid="+ uniqueId + "%26status=%25d%26updated_on=%25t%26res=%252"*/;
		GetMethod method = new GetMethod();
		try {
			String msgText = URLEncoder.encode(message, "UTF-8");

			String urlSplit[] = smsApi.getApi().split("~");
			StringBuffer url = new StringBuffer();
			for (String string : urlSplit) {
				if (string.equals("ourMobNumbers")) {
					string = mobileNo;
				}
				if (string.equals("ourMessage")) {
					string = msgText;
				}
				if (string.equals("senderId"))
					string = senderId;
				if (string.equals("myId"))
					string = uniqueId;
				if (string.equals("dlr"))
					string = dlr_url;
				if(string.equals("uniqueId"))
					string = uniqueId;

				url.append(string);
			}

			HttpClient client = new HttpClient();
			method = new GetMethod(url.toString());
			System.out.println("SMS sent : " + mobileNo);
			// client.executeMethod(method);
		} catch (Exception e) {
			log.error("SMS Sending .. " + e.getMessage() + " : for -UniqueId: "
					+ uniqueId);
		} finally {
			method.releaseConnection();
		}

	}

	private void deleteApiBlockEntry(SmsOutgoingMaster smsOutgoing,
			SmsApi smsApi) {
		MobileApiBlockList unblockedApiForMobile = mobileApiBlockListService
				.findByBlocked_Mobile_ApiId(smsOutgoing.getMobileNo(),
						smsApi.getId());
		if (unblockedApiForMobile != null)
			/*
			 * Delete the Mobile_Api block entry since this API can send SMS to
			 * this number and is not blocked by this API for continuously 3
			 * times
			 */
			mobileApiBlockListService.delete(unblockedApiForMobile.getId());
	}

}
