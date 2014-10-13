package com.aitrich.smsapi.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.aitrich.smsapi.dao.EndUserPurchaseHistoryDAO;
import com.aitrich.smsapi.model.EndUserPurchaseHistory;
import com.aitrich.smsapi.services.EndUserPurchaseHistoryService;

/**
 * <pre>
 * A service implementation for the {@link EndUserPurchaseHistoryService}.
 * 
 * <h5>&copy;2012 aitrich Technologies. All rights reserved.</h5>
 * </pre>
 * 
 * @author : Kavya M
 * @since : June 3, 2014
 * @version : 1.0
 * 
 */
@Service("EndUserPurchaseHistoryService")
@Scope("prototype")
public class EndPurchaseHistoryServiceImpl implements
		EndUserPurchaseHistoryService {

	private EndUserPurchaseHistoryDAO dao;

	@Autowired
	public EndPurchaseHistoryServiceImpl(
			@Qualifier("EndUserPurchaseHistoryDAO") EndUserPurchaseHistoryDAO dao) {
		super();
		this.dao = dao;
	}

	@Override
	public EndUserPurchaseHistory findById(long endUserPurchaseId) {
		return dao.findByPrimaryKey(endUserPurchaseId);
	}

	@Override
	public List<EndUserPurchaseHistory> findAllByEndUserId(long endUserId) {
		return dao.findAllByEndUserId(endUserId);
	}

	@Override
	public List<EndUserPurchaseHistory> findByEndUserId_PurchaseDate(
			long endUserId, Date date) {
		return dao.findAllByEndUserId_PurchaseDate(endUserId, date);
	}

}
