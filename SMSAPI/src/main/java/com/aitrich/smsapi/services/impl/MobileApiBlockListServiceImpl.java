package com.aitrich.smsapi.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.aitrich.smsapi.dao.MobileApiBlockListDAO;
import com.aitrich.smsapi.model.MobileApiBlockList;
import com.aitrich.smsapi.services.MobileApiBlockListService;

/**
 * <pre>
 * A service implementation for the {@link MobileApiBlockListService}.
 * 
 * <h5>&copy;2012 aitrich Technologies. All rights reserved.</h5>
 * </pre>
 * 
 * @author : Kavya M
 * @since : June 3, 2014
 * @version : 1.0
 * 
 */
@Service("MobileApiBlockListService")
@Scope("prototype")
public class MobileApiBlockListServiceImpl implements MobileApiBlockListService {

	private MobileApiBlockListDAO dao;

	public MobileApiBlockListServiceImpl() {
		super();
	}

	@Autowired
	public MobileApiBlockListServiceImpl(
			@Qualifier("MobileApiBlockListDAO") MobileApiBlockListDAO dao) {
		super();
		this.dao = dao;
	}

	@Override
	public MobileApiBlockList findById(long id) {
		return dao.findByPrimaryKey(id);
	}

	@Override
	public MobileApiBlockList findByBlocked_Mobile_ApiId(String mobileNo,
			long smsApiId) {
		return dao.findByBlocked_Mobile_ApiId(mobileNo, smsApiId);
	}

	@Override
	public List<MobileApiBlockList> findAllByMobileNo(String mobileNo) {
		return dao.findAllByMobileNo(mobileNo);
	}

	@Override
	public void delete(long id) {
		dao.delete(id);
	}

	@Override
	public MobileApiBlockList findByUnblocked_Mobile_ApiId(String mobileNo,
			long smsApiId) {
		return dao.findByUnblocked_Mobile_ApiId(mobileNo, smsApiId);
	}

	@Override
	public void saveOrUpdate(MobileApiBlockList mobileApiBlockList) {
		dao.saveOrUpdate(mobileApiBlockList);
	}

	@Override
	public MobileApiBlockList findByMobile_ApiId(String mobileNo, long smsApiId) {
		return dao.findByMobile_ApiId(mobileNo, smsApiId);
	}

}
