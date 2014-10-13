package com.aitrich.smsapi.controller.enduser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/end-user")
public class EndUserController {

	protected Log log = LogFactory.getLog(this.getClass());

	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public String superAdminHomeForm(Model model) {
		return "end-user/index";
	}

	@RequestMapping(value = "/endUserSampleView.html", method = RequestMethod.GET)
	public String addtoGroupForm(Model model) {
		return "end-user/endUserSampleView";
	}
}
