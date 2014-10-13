package com.aitrich.smsapi.services;

import java.util.Date;
import java.util.List;

import com.aitrich.smsapi.model.EndUserPurchaseHistory;

/**
 * <pre>
 * A service interface for the EndUserPurchaseHistory Entity.
 * 
 * <h5>&copy;2012 aitrich Technologies. All rights reserved.</h5>
 * </pre>
 * 
 * @author : Kavya M
 * @since : June 13, 2014
 * @version : 1.0
 * 
 */
public interface EndUserPurchaseHistoryService {

	EndUserPurchaseHistory findById(long endUserPurchaseId);

	List<EndUserPurchaseHistory> findByEndUserId_PurchaseDate(long endUserId, Date date);

	List<EndUserPurchaseHistory> findAllByEndUserId(long endUserId);

}
