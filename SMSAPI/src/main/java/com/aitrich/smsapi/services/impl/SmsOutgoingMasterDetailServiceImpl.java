package com.aitrich.smsapi.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.aitrich.smsapi.dao.SmsOutgoingMasterDetailDAO;
import com.aitrich.smsapi.model.SmsOutgoingMasterDetail;
import com.aitrich.smsapi.services.SmsOutgoingMasterDetailService;

/**
 * <pre>
 * A service implementation for the {@link SmsOutgoingMasterDetailService}.
 * 
 * <h5>&copy;2012 aitrich Technologies. All rights reserved.</h5>
 * </pre>
 * 
 * @author : Kavya M
 * @since : June 3, 2014
 * @version : 1.0
 * 
 */
@Service("SmsOutgoingMasterDetailService")
@Scope("prototype")
public class SmsOutgoingMasterDetailServiceImpl implements
		SmsOutgoingMasterDetailService {
	private SmsOutgoingMasterDetailDAO dao;

	public SmsOutgoingMasterDetailServiceImpl() {
		super();
	}

	@Autowired
	public SmsOutgoingMasterDetailServiceImpl(
			@Qualifier("SmsOutgoingMasterDetailDAO") SmsOutgoingMasterDetailDAO dao) {
		super();
		this.dao = dao;
	}

	@Override
	public SmsOutgoingMasterDetail findById(long id) {
		return dao.findByPrimaryKey(id);
	}

	@Override
	public void saveOrUpdate(SmsOutgoingMasterDetail smsRedirected) {
		dao.saveOrUpdate(smsRedirected);
	}

	@Override
	public long save(SmsOutgoingMasterDetail smsRedirected) {
		return dao.save(smsRedirected);
	}

	@Override
	public void saveOrUpdateAll(List<SmsOutgoingMasterDetail> smsRedirectedList) {
		dao.saveOrUpdateAll(smsRedirectedList);
	}

}
