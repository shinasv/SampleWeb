package com.aitrich.smsapi.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.aitrich.smsapi.dao.EndUserDAO;
import com.aitrich.smsapi.model.EndUser;
/**
 * <pre>
 * A DAO implementation for the {@link EndUser} entity.
 * 
 * <h5>&copy;2012 aitrich Technologies. All rights reserved.</h5>
 * </pre>
 * 
 * @author : Kavya M
 * @since : June 13, 2014
 * @version : 1.0
 * 
 */
@SuppressWarnings("serial")
@Repository("EndUserDAO")
@Scope("prototype")
public class EndUserDAOImpl extends GenericDAOImpl<EndUser, Long> implements EndUserDAO{

	@Override
	public EndUser findByUserAccountId(long userAccountId) {
		return findByUniqueField("userAccount.id", userAccountId);
	}

}
