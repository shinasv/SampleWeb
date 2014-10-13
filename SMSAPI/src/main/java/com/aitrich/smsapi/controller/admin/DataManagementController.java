package com.aitrich.smsapi.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aitrich.smsapi.model.Reseller;
import com.aitrich.smsapi.model.ResellerCredit;
import com.aitrich.smsapi.model.ResellerPurchaseHistory;
import com.aitrich.smsapi.model.Role;
import com.aitrich.smsapi.model.UserAccount;
import com.aitrich.smsapi.services.ResellerCreditService;
import com.aitrich.smsapi.services.ResellerService;
import com.aitrich.smsapi.services.RoleService;
import com.aitrich.smsapi.services.SecurityService;

@Controller
@RequestMapping(value = "/admin")
public class DataManagementController {
	@Autowired
	private ResellerService resellerService;

	@Autowired
	private ResellerCreditService resellerCreditService;

	@Autowired
	@Qualifier(value = "UserDetailsService")
	private SecurityService securityService;

	@Autowired
	private RoleService roleService;

	protected Log log = LogFactory.getLog(this.getClass());

	@RequestMapping(value = "/resellerRegistrationSubmit.html", method = RequestMethod.POST)
	public @ResponseBody
	int resellerRegistrationSubmit(Reseller reseller, BindingResult result,
			Model model) {
		int returnStatus = getErrorMessage(reseller);

		if (returnStatus == 0) {

			Role role = roleService.findRoleByRoleName("ROLE_RESELLER");
			List<Role> roles = new ArrayList<Role>();
			roles.add(role);
			UserAccount userAccount = new UserAccount();
			userAccount.setUserName(reseller.getUserAccount().getUserName());
			userAccount.setPassword(reseller.getUserAccount().getPassword());
			userAccount.setRoles(roles);
			Reseller reseller1 = new Reseller();
			reseller1.setName(reseller.getName());
			reseller1.setAddress(reseller.getAddress());
			reseller1.setEmail(reseller.getEmail());
			reseller1.setPhone(reseller.getPhone());
			reseller1.setSecondaryPhone(reseller.getSecondaryPhone());
			reseller1.setWebsite(reseller.getWebsite());
			resellerService.resellerRegistration(reseller1, userAccount);

		}
		return returnStatus;
	}

	@RequestMapping(value = "/saveOrUpdateResellerInAdmin.html", method = RequestMethod.POST)
	public @ResponseBody
	int saveOrUpdate(@RequestParam long id, @RequestParam String name,
			@RequestParam String address, @RequestParam String email,
			@RequestParam String phone, @RequestParam String secondaryPhone,
			@RequestParam String website) {

		int returnStatus = getEditErrorMessage(email, phone);
		if (returnStatus == 0) {

			Reseller reseller2 = resellerService.findById(id);
			reseller2.setName(name);
			reseller2.setAddress(address);
			reseller2.setEmail(email);
			reseller2.setPhone(phone);
			reseller2.setSecondaryPhone(secondaryPhone);
			reseller2.setWebsite(website);

			resellerService.saveOrUpdate(reseller2);
		}
		return returnStatus;
	}

	private int getEditErrorMessage(String email, String phone) {
		// TODO Auto-generated method stub
		Reseller reseller1 = resellerService.findResellerByEmail(email);
		if (reseller1 != null) {
			if (!reseller1.getEmail().equals(email)) {
				return 1;
			}
		}

		reseller1 = resellerService.findResellerByPhone(phone);
		if (reseller1 != null) {
			if (!reseller1.getPhone().equals(phone))
				return 2;
		}

		return 0;
	}

	@RequestMapping(value = "/saveOrUpdateResellerCredit.html", method = RequestMethod.POST)
	public String addResellerCredit(@RequestParam Long id,
			@RequestParam Long quantity) {

		Reseller reseller = resellerService.findById(id);
		ResellerCredit resellerCredit1 = resellerCreditService
				.findByResellerId(reseller.getId());

		if (resellerCredit1 == null) {
			resellerCredit1 = new ResellerCredit();
			resellerCredit1.setReseller(reseller);
			resellerCredit1.setTotalCredits(quantity);
			resellerCredit1.setCreditUsed(0);
		} else {
			long quantity1 = quantity + resellerCredit1.getTotalCredits();
			resellerCredit1.setTotalCredits(quantity1);
		}

		ResellerPurchaseHistory resellerPurchaseHistory = new ResellerPurchaseHistory();
		resellerPurchaseHistory.setReseller(reseller);
		resellerPurchaseHistory.setPurchasedCredit(quantity);
		resellerCreditService.saveOrUpdate(resellerCredit1,
				resellerPurchaseHistory);

		return "redirect:manageSMSCredit.html";
	}

	@RequestMapping(value = "/deleteReseller.html", method = RequestMethod.POST)
	public String deleteReseller(@RequestParam Long id) {

		Reseller reseller = resellerService.findById(id);
		reseller.setIsDelete(true);

		resellerService.saveOrUpdate(reseller);

		return "redirect:manageResellers.html";
	}

	@RequestMapping(value = "/getResellerByID.html", method = RequestMethod.GET)
	public @ResponseBody
	Reseller getResellerByID(@RequestParam Long id) {
		Reseller reseller = resellerService.findById(id);
		reseller.setUserAccount(null);
		reseller.setEndUserList(null);
		return reseller;
	}

	// function to validate the form
	private int getErrorMessage(Reseller reseller) {
		// TODO Auto-generated method stub
		Reseller reseller1 = resellerService.findResellerByEmail(reseller
				.getEmail());

		if (reseller1 != null) {

			return 1;
		}

		reseller1 = resellerService.findResellerByPhone(reseller.getPhone());

		if (reseller1 != null) {

			return 2;
		}
		UserAccount userAccount = resellerService
				.findResellerByUserName(reseller.getUserAccount().getUserName());

		if (userAccount != null) {
			return 3;
		}
		return 0;
	}

}