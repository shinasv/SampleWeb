package com.aitrich.smsapi.dao;

import com.aitrich.smsapi.model.ResellerCredit;
/**
 * <pre>
 * A Data Access Object for the ResellerCredit Entity.
 * 
 * <h5>&copy;2012 aitrich Technologies. All rights reserved.</h5>
 * </pre>
 * 
 * @author : Kavya M
 * @since  : June 13, 2014
 * @version : 1.0
 * 
 */
public interface ResellerCreditDAO extends GenericDAO<ResellerCredit, Long>{
	ResellerCredit findByResellerId(long resellerId );
	
}
