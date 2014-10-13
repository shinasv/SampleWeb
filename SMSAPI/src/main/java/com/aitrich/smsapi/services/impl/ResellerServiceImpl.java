package com.aitrich.smsapi.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aitrich.smsapi.dao.ResellerCreditDAO;
import com.aitrich.smsapi.dao.ResellerDAO;
import com.aitrich.smsapi.dao.ResellerPurchaseHistoryDAO;
import com.aitrich.smsapi.dao.UserAccountDAO;
import com.aitrich.smsapi.model.Reseller;
import com.aitrich.smsapi.model.ResellerCredit;
import com.aitrich.smsapi.model.ResellerPurchaseHistory;
import com.aitrich.smsapi.model.UserAccount;
import com.aitrich.smsapi.services.ResellerService;

/**
 * <pre>
 * A service implementation for the {@link ResellerService}.
 * 
 * <h5>&copy;2012 aitrich Technologies. All rights reserved.</h5>
 * </pre>
 * 
 * @author : Kavya M
 * @since : June 3, 2014
 * @version : 1.0
 * 
 */

@Service("ResellerService")
@Scope("prototype")
public class ResellerServiceImpl implements ResellerService {

	private ResellerDAO dao;
	private UserAccountDAO userAccountDAO;
	private ResellerCreditDAO resellerCreditDAO;
	private ResellerPurchaseHistoryDAO resellerPurchaseHistoryDAO;
	

	public ResellerServiceImpl() {
		super();
	}

	@Autowired
	public ResellerServiceImpl(@Qualifier("ResellerDAO") ResellerDAO dao,
			@Qualifier("ResellerCreditDAO") ResellerCreditDAO resellerCreditDAO,
			@Qualifier("ResellerPurchaseHistoryDAO") ResellerPurchaseHistoryDAO resellerPurchaseHistoryDAO,
			@Qualifier("UserAccountDAO") UserAccountDAO userAccountDAO)
	{
		super();
		this.dao = dao;
		this.userAccountDAO = userAccountDAO;
		this.resellerCreditDAO=resellerCreditDAO;
		this.resellerPurchaseHistoryDAO=resellerPurchaseHistoryDAO;
	}

	@Override
	@Transactional
	public void resellerRegistration(Reseller reseller, UserAccount userAccount) {
		userAccountDAO.save(userAccount);
		reseller.setUserAccount(userAccount);
		dao.save(reseller);
	}
	
	
	@Override
	@Transactional
	public void saveOrUpdate(Reseller reseller) {
		// TODO Auto-generated method stub
		
		
		
		dao.saveOrUpdate(reseller);
		
	}
	
	

	@Override
	public Reseller findById(long resellerId) {
		return dao.findByPrimaryKey(resellerId);
	}

	@Override
	public Reseller findByUserAccountId(long userAccountId) {
		return dao.findByUserAccountId(userAccountId);
	}

	@Override
	public List<Reseller> findAll() {
		List<Reseller> resellers=new ArrayList<Reseller>();
		List<Reseller> resellerList=dao.findAll();
		for (Reseller reseller : resellerList) {
			if(reseller.getIsDelete()==false)
				resellers.add(reseller);
			
		}
		
		return resellers;
	}

	@Override
	public Reseller findResellerByPhone(String phone) {
		return dao.findResellerByPhone(phone);
	}

	@Override
	public Reseller findResellerByEmail(String email) {
		return dao.findResellerByEmail(email);
	}

	@Override
	public UserAccount findResellerByUserName(String userName) {
		// TODO Auto-generated method stub
		UserAccount userAccount=userAccountDAO.findUserByUserName(userName);
     	return userAccount;
		
	}

	@Override
	public void deleteReseller(Reseller reseller) {
		// TODO Auto-generated method stub
		
		List<ResellerPurchaseHistory> resellerPurchaseHistories=resellerPurchaseHistoryDAO.findByRessllerId(reseller.getId());
		 resellerPurchaseHistoryDAO.deleteAll(resellerPurchaseHistories);
		 ResellerCredit resellerCredit=resellerCreditDAO.findByResellerId(reseller.getId());
		 resellerCreditDAO.delete(resellerCredit);
		 userAccountDAO.delete(reseller.getUserAccount().getId());
		
		
		
		
		
	}

	

	
	

}
