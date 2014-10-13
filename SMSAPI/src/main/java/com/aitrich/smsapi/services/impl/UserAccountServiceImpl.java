package com.aitrich.smsapi.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.aitrich.smsapi.dao.UserAccountDAO;
import com.aitrich.smsapi.model.UserAccount;
import com.aitrich.smsapi.services.UserAccountService;


@Service("UserAccountService")
@Scope("prototype")
public class UserAccountServiceImpl implements UserAccountService {
	
	private UserAccountDAO userAccountdao;
	

	public UserAccountServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	public UserAccountServiceImpl(@Qualifier("UserAccountDAO") UserAccountDAO userAccountdao) {
		super();
		this.userAccountdao = userAccountdao;
	}

	@Override
	public UserAccount findUserByUserName(String userName) {
		// TODO Auto-generated method stub
		return userAccountdao.findUserByUserName(userName);
	}

	@Override
	public UserAccount findUserByPassword(String password) {
		// TODO Auto-generated method stub
		return userAccountdao.findUserByPassword(password);
	}

	@Override
	public void saveOrUpdateUserAccount(UserAccount userAccount) {
		// TODO Auto-generated method stub
		userAccountdao.saveOrUpdate(userAccount);
		
	}

	@Override
	public UserAccount findUserAccountById(long userAccountId) {
		// TODO Auto-generated method stub
		return userAccountdao.findByPrimaryKey(userAccountId);
	}

}
