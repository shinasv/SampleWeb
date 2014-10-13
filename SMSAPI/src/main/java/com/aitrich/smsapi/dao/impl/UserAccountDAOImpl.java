package com.aitrich.smsapi.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.aitrich.smsapi.dao.UserAccountDAO;
import com.aitrich.smsapi.model.UserAccount;

/**
 * <pre>
 * Implementation of DAO for UserAccounts
 * 
 * 
 * <h5>&copy; 2012 Aitrich Technologies. All rights reserved.</h5>
 * </pre>
 * 
 * @author : Kavya
 * @since : June 14,2014
 * @version : 1.0
 * 
 */
@Repository("UserAccountDAO")
@Scope("prototype")
public class UserAccountDAOImpl extends GenericDAOImpl<UserAccount, Long>
		implements UserAccountDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7676885964275857739L;

	@Override
	public UserAccount findUserByUserName(String userName) {
		return findByUniqueField("userName", userName);
	}

	@Override
	public UserAccount findUserByPassword(String password) {
		// TODO Auto-generated method stub
		return findByUniqueField("password", password);
	}

}
