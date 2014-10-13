package com.aitrich.smsapi.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.aitrich.smsapi.dao.EndUserCreditDAO;
import com.aitrich.smsapi.model.EndUserCredit;
import com.aitrich.smsapi.services.EndUserCreditService;

/**
 * <pre>
 * A service implementation for the {@link EndUserCreditService}.
 * 
 * <h5>&copy;2012 aitrich Technologies. All rights reserved.</h5>
 * </pre>
 * 
 * @author : Kavya M
 * @since : June 3, 2014
 * @version : 1.0
 * 
 */
@Service("EndUserCreditService")
@Scope("prototype")
public class EndUserCreditServiceImpl implements EndUserCreditService {
	private EndUserCreditDAO dao;

	public EndUserCreditServiceImpl() {
		super();
	}

	@Autowired
	public EndUserCreditServiceImpl(
			@Qualifier("EndUserCreditDAO") EndUserCreditDAO dao) {
		super();
		this.dao = dao;
	}

	@Override
	public EndUserCredit findById(long endUserCreditId) {
		return dao.findByPrimaryKey(endUserCreditId);
	}

	@Override
	public EndUserCredit findByEndUserId(long endUserId) {
		return dao.findByEndUserId(endUserId);
	}

	@Override
	public void saveOrUpdate(EndUserCredit endUserCredit) {
		dao.saveOrUpdate(endUserCredit);
	}

}
