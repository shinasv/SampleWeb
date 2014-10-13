package com.aitrich.smsapi.dao;

import java.util.Date;
import java.util.List;

import com.aitrich.smsapi.model.ResellerPurchaseHistory;
/**
 * <pre>
 * A Data Access Object for the ResellerPurchaseHistory Entity.
 * 
 * <h5>&copy;2012 aitrich Technologies. All rights reserved.</h5>
 * </pre>
 * 
 * @author : Kavya M
 * @since  : June 13, 2014
 * @version : 1.0
 * 
 */
public interface ResellerPurchaseHistoryDAO extends GenericDAO<ResellerPurchaseHistory, Long>{

	ResellerPurchaseHistory findByResellerId_PurchaseDate(Date date);
	List<ResellerPurchaseHistory> findByRessllerId(long resellerId);
}
