package com.aitrich.smsapi.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.aitrich.smsapi.dao.SmsApiDAO;
import com.aitrich.smsapi.model.SmsApi;
import com.aitrich.smsapi.model.SmsApi.Type;

/**
 * <pre>
 * A DAO implementation for the {@link SmsApi} entity.
 * 
 * <h5>&copy;2012 aitrich Technologies. All rights reserved.</h5>
 * </pre>
 * 
 * @author : Kavya M
 * @since : June 13, 2014
 * @version : 1.0
 * 
 */
@Repository("SmsApiDAO")
@Scope("prototype")
public class SmsApiDAOImpl extends GenericDAOImpl<SmsApi, Long> implements
		SmsApiDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7833645094717219978L;

	@Override
	public SmsApi find_MessageApi_Priority(int priority) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery(
						"from SmsApi  where type ='MESSAGE' and priority =:priority order by id asc ")
				.setParameter("priority", priority);

		return (SmsApi) query.uniqueResult();
	}

	@Override
	public SmsApi find_CallApi_Priority(int priority) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery(
						"from SmsApi  where type ='CALL' and priority =:priority order by id asc ")
				.setParameter("priority", priority);

		return (SmsApi) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SmsApi> findAllBy_Active_Type(Type type) {
		List<SmsApi> smsApiList = getSession()
				.createQuery(
						"from SmsApi  where isDelete = 'false' and status = 'Active' and type =:type  order by priority asc ")
				.setParameter("type", type).list();
		return smsApiList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SmsApi> findAll_MessageApi() {
		List<SmsApi> smsApiList = getSession()
				.createQuery(
						"from SmsApi  where type ='MESSAGE' and isDelete = 'false' and status = 'Active' order by priority asc")
				.list();
		return smsApiList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SmsApi> findAll_CallApi() {
		List<SmsApi> smsApiList = getSession()
				.createQuery(
						"from SmsApi  where type ='CALL' and isDelete = 'false' and status = 'Active' order by priority asc")
				.list();
		return smsApiList;
	}

}
