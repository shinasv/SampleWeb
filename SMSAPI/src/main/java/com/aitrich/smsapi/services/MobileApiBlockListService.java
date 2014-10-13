package com.aitrich.smsapi.services;

import java.util.List;

import com.aitrich.smsapi.model.MobileApiBlockList;

/**
 * <pre>
 * A service interface for the MobileApiBlockList Entity.
 * 
 * <h5>&copy;2012 aitrich Technologies. All rights reserved.</h5>
 * </pre>
 * 
 * @author : Kavya M
 * @since : June 13, 2014
 * @version : 1.0
 * 
 */
public interface MobileApiBlockListService {
	void delete(long id);

	void saveOrUpdate(MobileApiBlockList mobileApiBlockList);

	MobileApiBlockList findById(long id);

	MobileApiBlockList findByBlocked_Mobile_ApiId(String mobileNo, long smsApiId);

	MobileApiBlockList findByUnblocked_Mobile_ApiId(String mobileNo,
			long smsApiId);

	MobileApiBlockList findByMobile_ApiId(String mobileNo, long smsApiId);

	List<MobileApiBlockList> findAllByMobileNo(String mobileNo);
}
