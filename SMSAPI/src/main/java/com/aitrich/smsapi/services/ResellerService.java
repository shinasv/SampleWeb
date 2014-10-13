package com.aitrich.smsapi.services;

import java.util.List;

import com.aitrich.smsapi.model.Reseller;
import com.aitrich.smsapi.model.UserAccount;

/**
 * <pre>
 * A service interface for the Reseller Entity.
 * 
 * <h5>&copy;2012 aitrich Technologies. All rights reserved.</h5>
 * </pre>
 * 
 * @author : Kavya M
 * @since : June 13, 2014
 * @version : 1.0
 * 
 */
public interface ResellerService {

	public void resellerRegistration(Reseller reseller, UserAccount userAccount);

	public Reseller findById(long resellerId);

	public Reseller findByUserAccountId(long userAccountId);

	public List<Reseller> findAll();

	public Reseller findResellerByPhone(String phone);

	public Reseller findResellerByEmail(String email);

	public UserAccount findResellerByUserName(String userName);
	
	public void saveOrUpdate(Reseller reseller);
	
	public void deleteReseller(Reseller reseller);

}
