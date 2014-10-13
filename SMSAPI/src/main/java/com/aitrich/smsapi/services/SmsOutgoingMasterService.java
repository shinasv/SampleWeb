package com.aitrich.smsapi.services;

import java.util.List;

import com.aitrich.smsapi.model.SmsOutgoingMaster;

/**
 * <pre>
 * A service interface for the SmsOutgoing Entity.
 * 
 * <h5>&copy;2012 aitrich Technologies. All rights reserved.</h5>
 * </pre>
 * 
 * @author : Kavya M
 * @since : June 13, 2014
 * @version : 1.0
 * 
 */
public interface SmsOutgoingMasterService {
	void saveOrUpdate(SmsOutgoingMaster smsOutgoingMaster);

	long save(SmsOutgoingMaster smsOutgoingMaster);

	SmsOutgoingMaster findById(long id);

	void saveOrUpdateAll(List<SmsOutgoingMaster> smsOutgoingMasters);

	List<SmsOutgoingMaster> findAll();

}
