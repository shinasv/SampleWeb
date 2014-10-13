package com.aitrich.smsapi.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.aitrich.smsapi.dao.EndUserDAO;
import com.aitrich.smsapi.model.EndUser;
import com.aitrich.smsapi.services.EndUserService;

/**
 * <pre>
 * A service implementation for the {@link EndUserService}.
 * 
 * <h5>&copy;2012 aitrich Technologies. All rights reserved.</h5>
 * </pre>
 * 
 * @author : Kavya M
 * @since : June 3, 2014
 * @version : 1.0
 * 
 */
@Service("EndUserService")
@Scope("prototype")
public class EndUserServiceImpl implements EndUserService {
	private EndUserDAO dao;

	public EndUserServiceImpl() {
		super();
	}

	@Autowired
	public EndUserServiceImpl(@Qualifier("EndUserDAO") EndUserDAO dao) {
		super();
		this.dao = dao;
	}

	@Override
	public EndUser findById(long endUserId) {
		return dao.findByPrimaryKey(endUserId);
	}

	@Override
	public EndUser findByUserAccountId(long userAccountId) {
		return dao.findByUserAccountId(userAccountId);
	}

	@Override
	public void saveOrUpdate(EndUser endUser) {
		dao.saveOrUpdate(endUser);
	}

}
