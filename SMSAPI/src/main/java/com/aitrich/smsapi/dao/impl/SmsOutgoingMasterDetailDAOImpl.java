package com.aitrich.smsapi.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.aitrich.smsapi.dao.SmsOutgoingMasterDetailDAO;
import com.aitrich.smsapi.model.SmsOutgoingMasterDetail;

/**
 * <pre>
 * A DAO implementation for the {@link SmsOutgoingMasterDetail} entity.
 * 
 * <h5>&copy;2012 aitrich Technologies. All rights reserved.</h5>
 * </pre>
 * 
 * @author : Kavya M
 * @since : June 13, 2014
 * @version : 1.0
 * 
 */
@Repository("SmsOutgoingMasterDetailDAO")
@Scope("prototype")
public class SmsOutgoingMasterDetailDAOImpl extends
		GenericDAOImpl<SmsOutgoingMasterDetail, Long> implements
		SmsOutgoingMasterDetailDAO {

	private static final long serialVersionUID = -4530016706443042277L;

}
