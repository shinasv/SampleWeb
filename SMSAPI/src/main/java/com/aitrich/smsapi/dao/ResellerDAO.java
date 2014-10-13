package com.aitrich.smsapi.dao;

import com.aitrich.smsapi.model.Reseller;

/**
 * <pre>
 * A Data Access Object for the Reseller Entity.
 * 
 * <h5>&copy;2012 aitrich Technologies. All rights reserved.</h5>
 * </pre>
 * 
 * @author : Kavya M
 * @since : June 13, 2014
 * @version : 1.0
 * 
 */

public interface ResellerDAO extends GenericDAO<Reseller, Long> {
	Reseller findByUserAccountId(long userAccountId);

	public Reseller findResellerByPhone(String phone);

	public Reseller findResellerByEmail(String email);

}
