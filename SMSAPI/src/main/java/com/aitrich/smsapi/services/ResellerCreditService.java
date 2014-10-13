package com.aitrich.smsapi.services;

import java.util.List;

import com.aitrich.smsapi.model.ResellerCredit;
import com.aitrich.smsapi.model.ResellerPurchaseHistory;

/**
 * <pre>
 * A service interface for the ResellerCredit Entity.
 * 
 * <h5>&copy;2012 aitrich Technologies. All rights reserved.</h5>
 * </pre>
 * 
 * @author : Kavya M
 * @since : June 13, 2014
 * @version : 1.0
 * 
 */
public interface ResellerCreditService {
	void saveOrUpdate(ResellerCredit resellerCredit,ResellerPurchaseHistory resellerPurchaseHistory);

	ResellerCredit findById(long resellerCreditId);

	ResellerCredit findByResellerId(long resellerId);

	List<ResellerCredit> findAll();
}
