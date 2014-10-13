package com.aitrich.smsapi.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.aitrich.smsapi.dao.ResellerDAO;
import com.aitrich.smsapi.model.Reseller;

/**
 * <pre>
 * A DAO implementation for the {@link Reseller} entity.
 * 
 * <h5>&copy;2012 aitrich Technologies. All rights reserved.</h5>
 * </pre>
 * 
 * @author : Kavya M
 * @since : June 13, 2014
 * @version : 1.0
 * 
 */
@Repository("ResellerDAO")
@Scope("prototype")
public class ResellerDAOImpl extends GenericDAOImpl<Reseller, Long> implements
		ResellerDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2842757848203009673L;

	@Override
	public Reseller findByUserAccountId(long userAccountId) {
		return findByUniqueField("userAccount.id", userAccountId);
	}

	@Override
	public Reseller findResellerByPhone(String phone) {
		return findByUniqueField("phone", phone);
	}

	@Override
	public Reseller findResellerByEmail(String email) {
		return findByUniqueField("email", email);
	}

}
