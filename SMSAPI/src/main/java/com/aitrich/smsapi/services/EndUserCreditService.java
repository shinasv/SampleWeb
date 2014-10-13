package com.aitrich.smsapi.services;

import com.aitrich.smsapi.model.EndUserCredit;

/**
 * <pre>
 * A service interface for the EndUserCredit Entity.
 * 
 * <h5>&copy;2012 aitrich Technologies. All rights reserved.</h5>
 * </pre>
 * 
 * @author : Kavya M
 * @since : June 13, 2014
 * @version : 1.0
 * 
 */
public interface EndUserCreditService {
	void saveOrUpdate(EndUserCredit endUserCredit);

	EndUserCredit findById(long endUserCreditId);

	EndUserCredit findByEndUserId(long endUserId);
}
