package com.aitrich.smsapi.services;

import java.util.Date;
import java.util.List;

import com.aitrich.smsapi.model.ResellerPurchaseHistory;
/**
 * <pre>
 * A service interface for the ResellerPurchaseHistory Entity.
 * 
 * <h5>&copy;2012 aitrich Technologies. All rights reserved.</h5>
 * </pre>
 * 
 * @author : Kavya M
 * @since : June 13, 2014
 * @version : 1.0
 * 
 */
public interface ResellerPurchaseHistoryService {
ResellerPurchaseHistory findByResslerId(long resellerPurchaseId);
ResellerPurchaseHistory findByResellerId_PurchaseDate(Date date);
List<ResellerPurchaseHistory> findByRessllerId(long resellerId);


}
