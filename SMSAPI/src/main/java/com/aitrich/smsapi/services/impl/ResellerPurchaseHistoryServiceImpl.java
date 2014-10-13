package com.aitrich.smsapi.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.aitrich.smsapi.dao.ResellerPurchaseHistoryDAO;
import com.aitrich.smsapi.model.ResellerPurchaseHistory;
import com.aitrich.smsapi.services.ResellerPurchaseHistoryService;

/**
 * <pre>
 * A service implementation for the {@link ResellerPurchaseHistoryService}.
 * 
 * <h5>&copy;2012 aitrich Technologies. All rights reserved.</h5>
 * </pre>
 * 
 * @author : Kavya M
 * @since : June 3, 2014
 * @version : 1.0
 * 
 */
@Service("ResellerPurchaseHistoryService")
@Scope("prototype")
public class ResellerPurchaseHistoryServiceImpl implements
		ResellerPurchaseHistoryService {
	private ResellerPurchaseHistoryDAO dao;

	public ResellerPurchaseHistoryServiceImpl() {
		super();
	}

	@Autowired
	public ResellerPurchaseHistoryServiceImpl(
			@Qualifier("ResellerPurchaseHistoryDAO") ResellerPurchaseHistoryDAO dao) {
		super();
		this.dao = dao;
	}

	@Override
	public ResellerPurchaseHistory findByResslerId(long resellerPurchaseId) {
		return dao.findByPrimaryKey(resellerPurchaseId);
	}

	@Override
	public ResellerPurchaseHistory findByResellerId_PurchaseDate(Date date) {
		return dao.findByResellerId_PurchaseDate(date);
	}

	@Override
	public List<ResellerPurchaseHistory> findByRessllerId(long resellerId) {
		return dao.findByRessllerId(resellerId);
	}

}
