package com.aitrich.smsapi.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.aitrich.smsapi.dao.ResellerCreditDAO;
import com.aitrich.smsapi.model.ResellerCredit;
/**
 * <pre>
 * A DAO implementation for the {@link ResellerCredit} entity.
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
@Repository("ResellerCreditDAO")
@Scope("prototype")
public class ResellerCreditDAOImpl extends GenericDAOImpl<ResellerCredit, Long> implements ResellerCreditDAO {

	@Override
	public ResellerCredit findByResellerId(long resellerId) {
	
		return findByUniqueField("reseller.id", resellerId);
	}

}
