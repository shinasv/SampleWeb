package com.aitrich.smsapi.services;

import com.aitrich.smsapi.model.UserAccount;

/**
 * <pre>
 * Interface for the security service.
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
public interface SecurityService {

	void changePassword(String userName, String oldPassword,
			String newPassword, String confirmPassword);

	void save(UserAccount userAccount);

	public UserAccount getUserAccountByUserName(String userName);

	public void deleteUser(UserAccount userAccount);

	public UserAccount findUserAccountByUserName(String userName);

}
