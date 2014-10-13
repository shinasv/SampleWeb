package com.aitrich.smsapi.dao;

import com.aitrich.smsapi.model.UserAccount;

/**
 * <pre>
 * A Data Access Object for the UserAccount Entity.
 * 
 * <h5>&copy;2012 aitrich Technologies. All rights reserved.</h5>
 * </pre>
 * 
 * @author : Kavya M
 * @since : June 14, 2014
 * @version : 1.0
 * 
 */
public interface UserAccountDAO extends GenericDAO<UserAccount, Long> {
	UserAccount findUserByUserName(String userName);

	UserAccount findUserByPassword(String password);
}
