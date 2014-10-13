package com.aitrich.smsapi.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.aitrich.smsapi.dao.EndUserCreditDAO;
import com.aitrich.smsapi.model.EndUserCredit;

/**
 * <pre>
 * A DAO implementation for the {@link EndUserCredit} entity.
 * 
 * <h5>&copy;2012 aitrich Technologies. All rights reserved.</h5>
 * </pre>
 * 
 * @author : Kavya M
 * @since : June 13, 2014
 * @version : 1.0
 * 
 */

@Repository("EndUserCreditDAO")
@Scope("prototype")
@SuppressWarnings("serial")
public class EndUserCreditDAOImpl extends GenericDAOImpl<EndUserCredit, Long>
		implements EndUserCreditDAO {

	@Override
	public EndUserCredit findByEndUserId(long endUserId) {
		return findByUniqueField("endUser.id", endUserId);
	}

}
