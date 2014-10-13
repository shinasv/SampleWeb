package com.aitrich.smsapi.services;

/**
 * <pre>
 * A service interface for the SmsApi Entity.
 * 
 * <h5>&copy;2012 aitrich Technologies. All rights reserved.</h5>
 * </pre>
 * 
 * @author : Kavya M
 * @since : June 13, 2014
 * @version : 1.0
 * 
 */

import java.util.List;

import com.aitrich.smsapi.model.SmsApi;
import com.aitrich.smsapi.model.SmsApi.Type;

public interface SmsApiService {

	SmsApi findSmsApiById(long smsApiId);

	SmsApi find_MessageApi_Priority(int priority);

	SmsApi find_CallApi_Priority(int priority);

	List<SmsApi> findAllBy_Active_Type(Type type);

	List<SmsApi> findAll_MessageApi();

	List<SmsApi> findAll_CallApi();
}
