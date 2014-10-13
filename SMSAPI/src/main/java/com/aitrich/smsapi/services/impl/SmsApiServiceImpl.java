package com.aitrich.smsapi.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.aitrich.smsapi.dao.SmsApiDAO;
import com.aitrich.smsapi.model.SmsApi;
import com.aitrich.smsapi.model.SmsApi.Type;
import com.aitrich.smsapi.services.SmsApiService;

/**
 * <pre>
 * A service implementation for the {@link SmsApiService}.
 * 
 * <h5>&copy;2012 aitrich Technologies. All rights reserved.</h5>
 * </pre>
 * 
 * @author : Kavya M
 * @since : June 3, 2014
 * @version : 1.0
 * 
 */
@Service("SmsApiService")
@Scope("prototype")
public class SmsApiServiceImpl implements SmsApiService {
	private SmsApiDAO dao;

	public SmsApiServiceImpl() {
		super();
	}

	@Autowired
	public SmsApiServiceImpl(@Qualifier("SmsApiDAO") SmsApiDAO dao) {
		super();
		this.dao = dao;
	}

	@Override
	public SmsApi findSmsApiById(long smsApiId) {
		return dao.findByPrimaryKey(smsApiId);
	}

	@Override
	public SmsApi find_MessageApi_Priority(int priority) {
		return dao.find_MessageApi_Priority(priority);
	}

	@Override
	public SmsApi find_CallApi_Priority(int priority) {
		return dao.find_CallApi_Priority(priority);
	}

	@Override
	public List<SmsApi> findAllBy_Active_Type(Type type) {
		return dao.findAllBy_Active_Type(type);
	}

	@Override
	public List<SmsApi> findAll_MessageApi() {
		return dao.findAll_MessageApi();
	}

	@Override
	public List<SmsApi> findAll_CallApi() {
		return dao.findAll_CallApi();
	}

}
