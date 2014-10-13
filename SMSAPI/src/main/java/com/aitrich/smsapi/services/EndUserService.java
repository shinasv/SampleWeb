package com.aitrich.smsapi.services;

import com.aitrich.smsapi.model.EndUser;

/**
 * <pre>
 * A service interface for the EndUser Entity.
 * 
 * <h5>&copy;2012 aitrich Technologies. All rights reserved.</h5>
 * </pre>
 * 
 * @author : Kavya M
 * @since : June 13, 2014
 * @version : 1.0
 * 
 */
public interface EndUserService {
	void saveOrUpdate(EndUser endUser);

	EndUser findById(long endUserId);

	EndUser findByUserAccountId(long userAccountId);
}
