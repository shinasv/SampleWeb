package com.aitrich.smsapi.controller.ws;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aitrich.smsapi.model.SmsOutgoingMaster;
import com.aitrich.smsapi.model.SmsOutgoingMasterDetail;
import com.aitrich.smsapi.services.SmsOutgoingMasterService;

/**
 * <pre>
 * Method Handles the incoming SMS Report from Vendor
 * 
 * @author : SHINAS
 * @since : September 29, 2014
 * @version : 1.0
 */

@Controller
public class SMSUserReportController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private SmsOutgoingMasterService smsOutgoingMasterService;

	@RequestMapping(value = "/getSMSDlrReport")
	public @ResponseBody String getSMSDlrReport(HttpServletRequest rqst) {
		String response = new String("Invalid");
		String userName = rqst.getParameter("userName");
		String password = rqst.getParameter("password");
		String msgId = rqst.getParameter("msgId");
		if (userName != null && password != null && msgId != null) {
			if (isAuthenticated(userName, password)) {
				SmsOutgoingMaster outgoingMaster = smsOutgoingMasterService
						.findById(Long.valueOf(msgId));
				SmsOutgoingMasterDetail masterDetail = outgoingMaster
						.getMasterDetails().get(
								outgoingMaster.getMasterDetails().size() - 1);
				if (masterDetail != null)
					response = "submittedDate="
							+ outgoingMaster.getSentDateTime() + "&dlrStatus="
							+ masterDetail.getDeliveryStatus() + "&reasonCode="
							+ masterDetail.getReasonCode() + "&dlrdAt="
							+ masterDetail.getSentDateTime();
				else
					response = "Invalid";
			} else
				response = "Authentication Failed";
		}

		return response;
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

}
