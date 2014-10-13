package com.aitrich.smsapi.controller.reseller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/reseller")
public class ResellerController {

	protected Log log = LogFactory.getLog(this.getClass());

	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public String superAdminHomeForm(Model model) {
		return "reseller/index";
	}

	@RequestMapping(value = "/manageEndUsers.html", method = RequestMethod.GET)
	public String addtoGroupForm(Model model) {
		System.out
				.println("========================================   dsgfds=  =========================");
		return "reseller/country";
	}
}
