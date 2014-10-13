package com.aitrich.smsapi.dao;

import java.util.List;

import com.aitrich.smsapi.model.SmsApi;
import com.aitrich.smsapi.model.SmsApi.Type;

/**
 * <pre>
 * A Data Access Object for the SmsApi Entity.
 * 
 * <h5>&copy;2012 aitrich Technologies. All rights reserved.</h5>
 * </pre>
 * 
 * @author : Kavya M
 * @since : May 30, 2012
 * @version : 1.0
 * 
 */
public interface SmsApiDAO extends GenericDAO<SmsApi, Long> {

	SmsApi find_MessageApi_Priority(int priority);

	SmsApi find_CallApi_Priority(int priority);

	List<SmsApi> findAllBy_Active_Type(Type type);

	List<SmsApi> findAll_MessageApi();

	List<SmsApi> findAll_CallApi();
}
