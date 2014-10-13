package com.aitrich.smsapi.services.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aitrich.smsapi.dao.UserAccountDAO;
import com.aitrich.smsapi.model.Role;
import com.aitrich.smsapi.model.UserAccount;
import com.aitrich.smsapi.services.SecurityService;

/**
 * <pre>
 * Service for fetching User and Roles data from DB
 * 
 * 
 * <h5>&copy; 2014 Aitrich Technologies. All rights reserved.</h5>
 * </pre>
 * 
 * @author : Shinas
 * @since : July 07, 2014
 * @version : 1.0
 * 
 */
@Service("UserDetailsService")
public class SpringUserDetailsServiceImpl implements UserDetailsService,
		SecurityService {
	private Log log = LogFactory.getLog(this.getClass());
	private UserAccountDAO userAccountDAO;

	public SpringUserDetailsServiceImpl() {

	}

	@Autowired
	public SpringUserDetailsServiceImpl(
			@Qualifier(value = "UserAccountDAO") UserAccountDAO userAccountDAO) {
		this.userAccountDAO = userAccountDAO;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		log.info("==>calling loadUserByUsername for the user " + userName);
		UserAccount userAccount = findUserAccount(userName);
		return new User(userAccount.getUserName(), userAccount.getPassword(),
				true, true, true, true, getGrantedAuthorities(userAccount));
	}

	private UserAccount findUserAccount(String userName) {
		UserAccount userAccount = userAccountDAO.findUserByUserName(userName);
		if (userAccount == null) {
			throw new BadCredentialsException(
					"Account with such user name not found");
		}
		return userAccount;
	}

	private List<GrantedAuthority> getGrantedAuthorities(UserAccount userAccount) {

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_NONE"));

		for (Role role : userAccount.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		}
		return authorities;
	}

	@Override
	public void changePassword(String userName, String oldPassword,
			String newPassword, String confirmPassword) {
		UserAccount userAccount = findUserAccount(userName);

		if (userAccount.getPassword().equals(oldPassword)) {
			validateNewPassword(oldPassword, newPassword, confirmPassword);
			userAccount.setPassword(newPassword);
			userAccountDAO.saveOrUpdate(userAccount);

		} else {
			throw new BadCredentialsException("Old password is wrong");
		}
	}

	private void validateNewPassword(String oldPass, String newPassword,
			String confirmPassword) {

		StringBuffer compoundMessage = new StringBuffer();
		if (newPassword == null || !newPassword.equals(confirmPassword)) {
			compoundMessage
					.append("New password is not matching with the confirm password\n");
		}
		if (newPassword.length() < 6) {
			compoundMessage.append("New password should be "
					+ "atleast 6 symbol long\n");
		}
		if (compoundMessage.length() != 0) {
			throw new BadCredentialsException(compoundMessage.toString());
		}
		if (oldPass.equals(newPassword)) {
			throw new BadCredentialsException(
					"Old password and new password are same");
		}
	}

	@Override
	@Transactional
	public void save(UserAccount userAccount) {
		userAccountDAO.saveOrUpdate(userAccount);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public UserAccount getUserAccountByUserName(String userName) {

		UserAccount userAccount = userAccountDAO.findUserByUserName(userName);
		if (userAccount == null) {
			throw new BadCredentialsException(
					"Account with such user name not found");
		}
		return userAccount;
	}

	@Override
	public void deleteUser(UserAccount userAccount) {
		userAccountDAO.delete(userAccount);
	}

	@Override
	public UserAccount findUserAccountByUserName(String userName) {
		return userAccountDAO.findUserByUserName(userName);
	}

}
