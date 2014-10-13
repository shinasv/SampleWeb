package com.aitrich.smsapi.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.aitrich.smsapi.dao.ResellerCreditDAO;
import com.aitrich.smsapi.dao.ResellerPurchaseHistoryDAO;
import com.aitrich.smsapi.model.ResellerCredit;
import com.aitrich.smsapi.model.ResellerPurchaseHistory;
import com.aitrich.smsapi.services.ResellerCreditService;

/**
 * <pre>
 * A service implementation for the {@link ResellerCreditService}.
 * 
 * <h5>&copy;2012 aitrich Technologies. All rights reserved.</h5>
 * </pre>
 * 
 * @author : Kavya M
 * @since : June 3, 2014
 * @version : 1.0
 * 
 */

@Service("ResellerCreditService")
@Scope("prototype")
public class ResellerCreditServiceImpl implements ResellerCreditService {
	private ResellerCreditDAO dao;
	
	private ResellerPurchaseHistoryDAO purchaseHistoryDAO;

	public ResellerCreditServiceImpl() {
		super();
	}
	 @Autowired
		public ResellerCreditServiceImpl(
				@Qualifier("ResellerCreditDAO") ResellerCreditDAO dao,
				@Qualifier ("ResellerPurchaseHistoryDAO") ResellerPurchaseHistoryDAO purchaseHistoryDAO ) {
			super();
			this.dao = dao;
			this.purchaseHistoryDAO=purchaseHistoryDAO;
	 }

	@Override
	public ResellerCredit findById(long resellerCreditId) {
		return dao.findByPrimaryKey(resellerCreditId);
	}

	@Override
	public ResellerCredit findByResellerId(long resellerId) {
		return dao.findByResellerId(resellerId);
	}

	public List<ResellerCredit> findAll() {
		return dao.findAll();
	}

	@Override
	public void saveOrUpdate(ResellerCredit resellerCredit,ResellerPurchaseHistory resellerPurchaseHistory) {
		
		
		dao.saveOrUpdate(resellerCredit);
		purchaseHistoryDAO.saveOrUpdate(resellerPurchaseHistory);
		
	}

}
