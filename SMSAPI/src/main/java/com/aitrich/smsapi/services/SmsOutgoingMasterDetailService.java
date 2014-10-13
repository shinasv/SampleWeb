package com.aitrich.smsapi.services;

import java.util.List;

import com.aitrich.smsapi.model.SmsOutgoingMasterDetail;

/**
 * <pre>
 * A service interface for the SmsRedirected Entity.
 * 
 * <h5>&copy;2012 aitrich Technologies. All rights reserved.</h5>
 * </pre>
 * 
 * @author : Kavya M
 * @since : June 13, 2014
 * @version : 1.0
 * 
 */
public interface SmsOutgoingMasterDetailService {
	void saveOrUpdate(SmsOutgoingMasterDetail smsOutgoingMasterDetail);

	long save(SmsOutgoingMasterDetail smsRedirected);

	void saveOrUpdateAll(List<SmsOutgoingMasterDetail> smsOutgoingMasterDetails);

	SmsOutgoingMasterDetail findById(long id);

}
