package com.aitrich.smsapi.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aitrich.smsapi.model.Reseller;
import com.aitrich.smsapi.model.ResellerCredit;
import com.aitrich.smsapi.services.ResellerCreditService;
import com.aitrich.smsapi.services.ResellerService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	private ResellerService resellerService;

	@Autowired
	private ResellerCreditService resellerCreditService;

	protected Log log = LogFactory.getLog(this.getClass());

	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public String superAdminHomeForm(Model model) {

		return "admin/index";
	}
	
	@RequestMapping(value = "/resellerRegistration.html", method = RequestMethod.GET)
	public String resellerRegistration(Model model) {
		model.addAttribute("reseller", new Reseller());
		return "admin/resellerRegistration";
	}

	@RequestMapping(value = "/uploadRegistration.html", method = RequestMethod.GET)
	public String uploadRegistration(Model model) {
		return "admin/uploadRegistration";
	}

	@RequestMapping(value = "/downloadRegistration.html", method = RequestMethod.GET)
	public String downloadRegistration(Model model) {
		return "admin/downloadRegistration";
	}

	@RequestMapping(value = "/manageResellers.html", method = RequestMethod.GET)
	public String manageResellers(Model model) {
		model.addAttribute("reseller", new Reseller());
		List<Reseller> resellers = resellerService.findAll();
		List<Reseller> resellerList = new ArrayList<Reseller>();
		for (Reseller reseller : resellers) {
			if (reseller.getIsDelete() == false)
				resellerList.add(reseller);
		}
		model.addAttribute("resellerList", resellerList);
		return "admin/manageResellers";
	}

	@RequestMapping(value = "/manageSMSCredit.html", method = RequestMethod.GET)
	public String manageSMSCredit(Model model) {

		List<Reseller> resellers = resellerService.findAll();

		List<ResellerCredit> resellerCreditList = new ArrayList<ResellerCredit>();

		for (Reseller reseller : resellers) {
			ResellerCredit resellerCredit = resellerCreditService
					.findByResellerId(reseller.getId());

			if (resellerCredit == null) {
				resellerCredit = new ResellerCredit();
				resellerCredit.setReseller(reseller);
				resellerCredit.setTotalCredits(0);
				resellerCredit.setCreditUsed(0);
			}

			resellerCreditList.add(resellerCredit);
		}

		model.addAttribute("resellerCreditList", resellerCreditList);
		return "admin/manageSMSCredit";
	}

	
}
