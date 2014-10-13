package com.aitrich.smsapi.controller.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aitrich.smsapi.model.EndUser;
import com.aitrich.smsapi.model.Reseller;
import com.aitrich.smsapi.model.Status;
import com.aitrich.smsapi.model.UserAccount;
import com.aitrich.smsapi.services.EndUserService;
import com.aitrich.smsapi.services.ResellerService;
import com.aitrich.smsapi.services.SecurityService;

/**
 * <pre>
 * Handles user's login.
 * 
 * <h5>&copy;2014 Aitrich Technologies. All rights reserved.</h5>
 * </pre>
 * 
 * @author : Muhammed Riyas T
 * @since : July 08 2014
 * @version : 1.0
 * 
 */
@Controller
public class AuthenticationController {

	private static final Logger log = LoggerFactory
			.getLogger(AuthenticationController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	@Qualifier("UserDetailsService")
	private SecurityService securityService;

	@Autowired
	private ResellerService resellerService;

	@Autowired
	private EndUserService endUserService;

	@RequestMapping(value = "/login.html", method = RequestMethod.GET)
	public String loginForm(Model model) {
		model.addAttribute("userAccount", new UserAccount());
		return "login";
	}

	/** Authenticate User Login */
	@RequestMapping(value = "/login-auth.html", method = RequestMethod.POST)
	public String loginAuth(final UserAccount userAccount,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session, RedirectAttributes redirectAttributes,
			SessionStatus sessionStatus) {

		final UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
				userAccount.getUserName(), userAccount.getPassword());

		authToken.setDetails(new WebAuthenticationDetails(request));

		final Authentication auth;
		try {
			// This will trigger UserDetailsService.loadUserByUsername(...)
			auth = authenticationManager.authenticate(authToken);
			final SecurityContext secCtx = SecurityContextHolder.getContext();
			secCtx.setAuthentication(auth);
			session.setAttribute(
					HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
					secCtx);

			UserAccount currentUserAcc = securityService
					.getUserAccountByUserName(userAccount.getUserName());

			session.setAttribute("currentUser", currentUserAcc);
			sessionStatus.setComplete();

			log.info("Logged User - " + secCtx.getAuthentication().getName()
					+ "@" + request.getRemoteAddr());

			String redirectUrl = getTargetUrl(currentUserAcc,
					redirectAttributes);
			return "redirect:" + redirectUrl;
		} catch (BadCredentialsException e) {
			log.info("Wrong user name or password");
			redirectAttributes.addFlashAttribute("loginErrorMsg",
					"Wrong user name or password");
			return "redirect:login.html";
		} catch (AuthenticationServiceException e) {
			log.info("Wrong user name or password");
			redirectAttributes.addFlashAttribute("loginErrorMsg",
					"Wrong user name or password");
			return "redirect:login.html";
		}
	}

	private String getTargetUrl(UserAccount userAccount,
			RedirectAttributes redirectAttributes) {
		String targetUrl = "";
		if (userHasRole("ROLE_SYSADMIN")) {
			targetUrl = "admin/index.html";
		} else if (userHasRole("ROLE_RESELLER")) {
			Reseller reseller = resellerService.findByUserAccountId(userAccount
					.getId());
			if (reseller.getStatus().equals(Status.ACTIVE)) {
				targetUrl = "reseller/index.html";

			} else {
				redirectAttributes.addFlashAttribute("loginErrorMsg",
						"Invalid User");
				targetUrl = "login.html";
			}
		} else if (userHasRole("ROLE_END_USER")) {
			EndUser uEndUser = endUserService.findByUserAccountId(userAccount
					.getId());
			if (uEndUser.getStatus().equals(Status.ACTIVE)) {
				targetUrl = "end-user/index.html";

			} else {
				redirectAttributes.addFlashAttribute("loginErrorMsg",
						"Invalid User");
				targetUrl = "login.html";
			}
		}
		return targetUrl;
	}

	public boolean userHasRole(String role) {
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
