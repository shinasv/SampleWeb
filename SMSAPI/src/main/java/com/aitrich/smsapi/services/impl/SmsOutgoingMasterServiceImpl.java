package com.aitrich.smsapi.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.aitrich.smsapi.dao.SmsOutgoingMasterDAO;
import com.aitrich.smsapi.model.SmsOutgoingMaster;
import com.aitrich.smsapi.services.SmsOutgoingMasterService;

@Service("SmsOutgoingMasterService")
public class SmsOutgoingMasterServiceImpl implements SmsOutgoingMasterService {
	private SmsOutgoingMasterDAO dao;

	@Autowired
	public SmsOutgoingMasterServiceImpl(
			@Qualifier("SmsOutgoingMasterDAO") SmsOutgoingMasterDAO dao) {
		super();
		this.dao = dao;
	}

	public SmsOutgoingMasterServiceImpl() {
		super();
	}

	@Override
	public SmsOutgoingMaster findById(long id) {
		return dao.findByPrimaryKey(id);
	}

	@Override
	public void saveOrUpdate(SmsOutgoingMaster smsOutgoingMaster) {
		dao.saveOrUpdate(smsOutgoingMaster);
	}

	@Override
	public long save(SmsOutgoingMaster smsOutgoingMaster) {
		return dao.save(smsOutgoingMaster);
	}

	@Override
	public void saveOrUpdateAll(List<SmsOutgoingMaster> smsOutgoingList) {
		dao.saveOrUpdateAll(smsOutgoingList);
	}

	@Override
	public List<SmsOutgoingMaster> findAll() {
		return dao.findAll();
	}

}
