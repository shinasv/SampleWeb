package com.aitrich.smsapi.controller.reseller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.aitrich.smsapi.services.ResellerService;
import com.aitrich.smsapi.services.RoleService;
import com.aitrich.smsapi.services.SecurityService;

/**
 * <pre>
 * Handles Reseller Registration.
 * 
 * <h5>&copy;2014 Aitrich Technologies. All rights reserved.</h5>
 * </pre>
 * 
 * @author : Muhammed Riyas T
 * @since : July 09 2014
 * @version : 1.0
 * 
 */
@Controller
public class ResellerRegistration {

	@Autowired
	private ResellerService resellerService;

	@Autowired
	@Qualifier(value = "UserDetailsService")
	private SecurityService securityService;

	@Autowired
	private RoleService roleService;

	/*@RequestMapping(value = "/reseller-reg.html", method = RequestMethod.GET)
	public String resellerRegistrationForm() {
		return "reseller-reg";
	}
*/
	
}
