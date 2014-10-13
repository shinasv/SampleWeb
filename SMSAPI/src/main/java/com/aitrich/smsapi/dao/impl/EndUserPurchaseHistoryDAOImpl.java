package com.aitrich.smsapi.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.aitrich.smsapi.dao.EndUserPurchaseHistoryDAO;
import com.aitrich.smsapi.model.EndUserPurchaseHistory;

/**
 * <pre>
 * A DAO implementation for the {@link EndUserPurchaseHistory} entity.
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
@Repository("EndUserPurchaseHistoryDAO")
@Scope("prototype")
public class EndUserPurchaseHistoryDAOImpl extends
		GenericDAOImpl<EndUserPurchaseHistory, Long> implements
		EndUserPurchaseHistoryDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<EndUserPurchaseHistory> findAllByEndUserId(long endUserId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery(
						"from EndUserPurchaseHistory where endUser.id:endUserId order by id asc ")
				.setParameter("endUserId", endUserId);
		List<EndUserPurchaseHistory> endUserPurchaseHistory = query.list();
		return endUserPurchaseHistory == null
				|| endUserPurchaseHistory.size() == 0 ? null
				: endUserPurchaseHistory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EndUserPurchaseHistory> findAllByEndUserId_PurchaseDate(
			long endUserId, Date date) {
		List<EndUserPurchaseHistory> endUserPurchaseHistoryList = getSession()
				.createQuery(
						"from EndUserPurchaseHistory  where endUser.id :endUserId and purchaseDate :date  order by id asc")
				.setParameter("endUserId", endUserId)
				.setParameter("date", date).list();
		return endUserPurchaseHistoryList;
	}

}
