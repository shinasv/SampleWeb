package com.aitrich.smsapi.dao;

import com.aitrich.smsapi.model.EndUser;

/**
 * <pre>
 * A Data Access Object for the SmsApi Entity.
 * 
 * <h5>&copy;2012 aitrich Technologies. All rights reserved.</h5>
 * </pre>
 * 
 * @author : Kavya M
 * @since  : June 13, 2014
 * @version : 1.0
 * 
 */
public interface EndUserDAO extends GenericDAO<EndUser, Long>{
	EndUser findByUserAccountId(long userAccountId);

}
