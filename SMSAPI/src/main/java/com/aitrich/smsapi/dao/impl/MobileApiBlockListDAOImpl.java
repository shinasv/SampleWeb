package com.aitrich.smsapi.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.aitrich.smsapi.dao.MobileApiBlockListDAO;
import com.aitrich.smsapi.model.MobileApiBlockList;

/**
 * <pre>
 * A DAO implementation for the {@link MobileApiBlockList} entity.
 * 
 * <h5>&copy;2012 aitrich Technologies. All rights reserved.</h5>
 * </pre>
 * 
 * @author : Kavya M
 * @since : June 13, 2014
 * @version : 1.0
 * 
 */
@SuppressWarnings("serial")
@Repository("MobileApiBlockListDAO")
@Scope("prototype")
public class MobileApiBlockListDAOImpl extends
		GenericDAOImpl<MobileApiBlockList, Long> implements
		MobileApiBlockListDAO {

	@Override
	public MobileApiBlockList findByBlocked_Mobile_ApiId(String mobileNo,
			long smsApiId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery(
						"from MobileApiBlockList  where mobile =:mobileNo  and smsApi.id = :smsApiId and isBlocked = '1'")
				.setParameter("mobileNo", mobileNo)
				.setParameter("smsApiId", smsApiId);
		return (MobileApiBlockList) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MobileApiBlockList> findAllByMobileNo(String mobileNo) {
		List<MobileApiBlockList> mobileApiBlockList = getSession()
				.createQuery(
						"from MobileApiBlockList  where mobile =:mobileNo  order by id asc")
				.setParameter("mobileNo", mobileNo).list();
		return mobileApiBlockList;
	}

	@Override
	public MobileApiBlockList findByUnblocked_Mobile_ApiId(String mobileNo,
			long smsApiId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery(
						"from MobileApiBlockList  where mobile =:mobileNo  and smsApi.id = :smsApiId and isBlocked = '0'")
				.setParameter("mobileNo", mobileNo)
				.setParameter("smsApiId", smsApiId);
		return (MobileApiBlockList) query.uniqueResult();
	}

	@Override
	public MobileApiBlockList findByMobile_ApiId(String mobileNo, long smsApiId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery(
						"from MobileApiBlockList  where mobile =:mobileNo  and smsApi.id = :smsApiId")
				.setParameter("mobileNo", mobileNo)
				.setParameter("smsApiId", smsApiId);
		return (MobileApiBlockList) query.uniqueResult();
	}

}
