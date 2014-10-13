package com.aitrich.smsapi.controller.ws;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aitrich.smsapi.model.DeliveryStatus;
import com.aitrich.smsapi.model.EndUser;
import com.aitrich.smsapi.model.EndUserCredit;
import com.aitrich.smsapi.model.MessageFilterBlocked;
import com.aitrich.smsapi.model.MessageFilterWord;
import com.aitrich.smsapi.model.Reseller;
import com.aitrich.smsapi.model.ResellerCredit;
import com.aitrich.smsapi.model.SmsOutgoingMaster;
import com.aitrich.smsapi.model.SmsOutgoingMasterDetail;
import com.aitrich.smsapi.model.UserAccount;
import com.aitrich.smsapi.services.EndUserCreditService;
import com.aitrich.smsapi.services.EndUserService;
import com.aitrich.smsapi.services.MessageFilterBlockedService;
import com.aitrich.smsapi.services.MessageFilterWordService;
import com.aitrich.smsapi.services.ResellerCreditService;
import com.aitrich.smsapi.services.ResellerService;
import com.aitrich.smsapi.services.SecurityService;
import com.aitrich.smsapi.services.SmsOutgoingMasterService;
import com.aitrich.smsapi.services.sms.SmsSendService;
import com.aitrich.smsapi.util.SMS_ReasonCode;

/**
 * <pre>
 * Controller Class for the SMS submission and 
 * all related operations on SMS submission
 * 
 * <h5>&copy;2012 aitrich Technologies. All rights reserved.</h5>
 * </pre>
 * 
 * @author : Shinas
 * @since : June 13, 2014
 * @version : 1.0
 */

@Controller
public class SubmitSMSController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	@Qualifier("UserDetailsService")
	private SecurityService securityService;
	@Autowired
	private ResellerCreditService resellerCreditService;
	@Autowired
	private EndUserCreditService endUserCreditService;
	@Autowired
	private ResellerService resellerService;
	@Autowired
	private EndUserService endUserService;
	@Autowired
	private SmsOutgoingMasterService smsOutgoingMasterService;
	@Autowired
	private MessageFilterWordService messageFilterWordService;
	@Autowired
	private MessageFilterBlockedService messageFilterBlockedService;
	@Autowired
	private SmsSendService smsSendService;

	/**
	 * <pre>
	 * Method Handles the incoming SMS to Recipients
	 * 
	 * @author : SHINAS
	 * @since : June 13, 2014
	 * @version : 1.0
	 * 
	 * @param rqst
	 * @return String
	 */
	@RequestMapping(value = "/submitSMS")
	public @ResponseBody String submitSMS(HttpServletRequest rqst) {
		String response = new String();
		UserAccount userAccount = null;

		String userName = rqst.getParameter("userName");
		String password = rqst.getParameter("password");
		String mobileNo = rqst.getParameter("to");
		String message = rqst.getParameter("message");
		String senderId = rqst.getParameter("senderId");

		if (userName != null && password != null && senderId != null
				&& mobileNo != null && message != null) {

			if (isAuthenticated(userName, password)) {
				userAccount = securityService
						.findUserAccountByUserName(userName);
				long creditBalance = 0;
				if (userHasRole("ROLE_END_USER")) {
					EndUser endUser = endUserService
							.findByUserAccountId(userAccount.getId());
					EndUserCredit endUserCredit = endUserCreditService
							.findByEndUserId(endUser.getId());
					creditBalance = endUserCredit.getTotalCredits()
							- endUserCredit.getCreditUsed();

				} else if (userHasRole("ROLE_RESELLER")) {
					Reseller reseller = resellerService
							.findByUserAccountId(userAccount.getId());
					ResellerCredit resellerCredit = resellerCreditService
							.findByResellerId(reseller.getId());
					creditBalance = resellerCredit.getTotalCredits()
							- resellerCredit.getCreditUsed();
				}

				if (creditBalance != 0) {
					/* return smsUniqueId */
					long smsUniqueId = saveAndSendSms(rqst, userAccount);
					response = "{\"smsStatus\": Submitted, \"uniqueId\": "
							+ smsUniqueId + "}";

				} else {
					/* No balance */
					response = "No sufficient balance";
					saveSmsForNoBalance(rqst, userAccount);
				}

			} else {
				/* Authentication failed */
				response = "Authentication Failed";

			}
		} else {
			/* Invalid */
			response = "Invalid";

		}
		return response;
	}

	/**
	 * @author SHINAS
	 * @since 07-Jul-2014
	 * @param rqst
	 * @param userAccount
	 * @return long
	 */
	private long saveAndSendSms(HttpServletRequest rqst, UserAccount userAccount) {
		final long smsUniqueId;
		String mobileNo = rqst.getParameter("to");
		String message = rqst.getParameter("message");
		int smsUnit = (int) Math.ceil((double) message.length() / 160);
		String senderId = rqst.getParameter("senderId");

		final SmsOutgoingMaster outgoing = new SmsOutgoingMaster();
		outgoing.setUserAccount(userAccount);
		outgoing.setMobileNo(mobileNo);
		outgoing.setMessage(message);
		outgoing.setSmsUnit(smsUnit);
		outgoing.setSenderId(senderId);

		if (rqst.getParameterMap().containsKey("needRedirection"))
			outgoing.setNeedRedirection(Boolean.valueOf(rqst
					.getParameter("needRedirection")));
		if (rqst.getParameterMap().containsKey("redirectCount"))
			outgoing.setRedirectCount(Integer.valueOf(rqst
					.getParameter("redirectCount")));
		if (rqst.getParameterMap().containsKey("redirectInterval"))
			outgoing.setRedirectInterval(Double.valueOf(rqst
					.getParameter("redirectInterval")));

		smsUniqueId = smsOutgoingMasterService.save(outgoing);

		new Thread(new Runnable() {
			@Override
			public void run() {

				boolean messageFilterSuccess = true;
				List<MessageFilterWord> filterWords = messageFilterWordService
						.findAll();
				for (MessageFilterWord messageFilterWord : filterWords) {
					if (outgoing.getMessage().contains(
							messageFilterWord.getWord())) {
						/*
						 * Contains anonymous words in the message, so this
						 * message has to be blocked from sending
						 */
						MessageFilterBlocked filterBlocked = new MessageFilterBlocked();
						filterBlocked.setMessageFilterWord(messageFilterWord);
						filterBlocked.setSmsOutgoingMaster(outgoing);

						List<SmsOutgoingMasterDetail> masterDetails = new ArrayList<SmsOutgoingMasterDetail>();
						SmsOutgoingMasterDetail detail = new SmsOutgoingMasterDetail();
						detail.setDeliveryStatus(DeliveryStatus.UNDELIVERED);
						detail.setReasonCode(SMS_ReasonCode.ANONYMOUS_MESSAGE);

						masterDetails.add(detail);
						outgoing.setMasterDetails(masterDetails);
						outgoing.setNeedRedirection(false);

						smsOutgoingMasterService.saveOrUpdate(outgoing);
						messageFilterBlockedService.save(filterBlocked);
						messageFilterSuccess = false;
						break;
					}
				}
				if (messageFilterSuccess)
					smsSendService.sendSMS(smsOutgoingMasterService
							.findById(smsUniqueId));
			}
		}).start();

		return smsUniqueId;
	}

	/**
	 * @author SHINAS
	 * @since 07-Jul-2014
	 * @param rqst
	 * @param userAccount
	 * @return long
	 */
	private long saveSmsForNoBalance(HttpServletRequest rqst,
			UserAccount userAccount) {
		long smsUniqueId = 0;
		String mobileNo = rqst.getParameter("to");
		String message = rqst.getParameter("message");
		int smsUnit = (int) Math.ceil((double) message.length() / 160);
		String senderId = rqst.getParameter("senderId");

		SmsOutgoingMaster outgoing = new SmsOutgoingMaster();
		outgoing.setUserAccount(userAccount);
		outgoing.setMobileNo(mobileNo);
		outgoing.setMessage(message);
		outgoing.setSmsUnit(smsUnit);
		outgoing.setSenderId(senderId);

		List<SmsOutgoingMasterDetail> masterDetails = new ArrayList<SmsOutgoingMasterDetail>();
		SmsOutgoingMasterDetail detail = new SmsOutgoingMasterDetail();
		detail.setDeliveryStatus(DeliveryStatus.UNDELIVERED);
		detail.setReasonCode(SMS_ReasonCode.NO_BALANCE);

		masterDetails.add(detail);
		outgoing.setMasterDetails(masterDetails);
		outgoing.setNeedRedirection(false);

		smsUniqueId = smsOutgoingMasterService.save(outgoing);

		return smsUniqueId;
	}

	/**
	 * @author SHINAS
	 * @since 07-Jul-2014
	 * @param userName
	 * @param password
	 * @return boolean
	 */
	private boolean isAuthenticated(String userName, String password) {
		final UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
				userName, password);
		final Authentication auth;
		try {
			auth = authenticationManager.authenticate(authToken);
			final SecurityContext secCtx = SecurityContextHolder.getContext();
			secCtx.setAuthentication(auth);

		} catch (BadCredentialsException e) {
			return false;
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	/**
	 * @author SHINAS
	 * @since 07-Jul-2014
	 * @param role
	 * @return boolean
	 */
	private boolean userHasRole(String role) {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		for (GrantedAuthority authority : authentication.getAuthorities()) {
			if (authority.getAuthority().equals(role)) {
				return true;
			}
		}
		return false;
	}

}
