package com.aitrich.smsapi.services;

import com.aitrich.smsapi.model.UserAccount;



public interface UserAccountService {
	
	void saveOrUpdateUserAccount(UserAccount userAccount);
	UserAccount findUserAccountById (long userAccountId);
	UserAccount findUserByUserName(String userName);
	UserAccount findUserByPassword(String password);

}
