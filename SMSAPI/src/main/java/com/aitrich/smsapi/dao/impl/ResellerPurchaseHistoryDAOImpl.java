package com.aitrich.smsapi.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.aitrich.smsapi.dao.ResellerPurchaseHistoryDAO;
import com.aitrich.smsapi.model.ResellerPurchaseHistory;
/**
 * <pre>
 * A DAO implementation for the {@link ResellerPurchaseHistory} entity.
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
@Repository("ResellerPurchaseHistoryDAO")
@Scope("prototype")
public class ResellerPurchaseHistoryDAOImpl extends GenericDAOImpl<ResellerPurchaseHistory, Long> implements ResellerPurchaseHistoryDAO{

	@Override
	public ResellerPurchaseHistory findByResellerId_PurchaseDate(Date date) {
		return findByUniqueField("purchased_date", date);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ResellerPurchaseHistory> findByRessllerId(long resellerId) {
		
		List<ResellerPurchaseHistory> resellerUserPurchaseHistoryList = getSession()
				.createQuery(
						"from ResellerPurchaseHistory  where reseller.id= :resellerId  order by id asc")
				.setParameter("resellerId", resellerId)
				.list();
				
		return resellerUserPurchaseHistoryList;
	}

}
