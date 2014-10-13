package com.aitrich.smsapi.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.aitrich.smsapi.dao.SmsOutgoingMasterDAO;
import com.aitrich.smsapi.model.EndUserCredit;
import com.aitrich.smsapi.model.SmsOutgoingMaster;

/**
 * <pre>
 * A DAO implementation for the {@link EndUserCredit} entity.
 * 
 * <h5>&copy;2012 aitrich Technologies. All rights reserved.</h5>
 * </pre>
 * 
 * @author : Kavya M
 * @since : June 13, 2014
 * @version : 1.0
 * 
 */
@Repository("SmsOutgoingMasterDAO")
@Scope("prototype")
public class SmsOutgoingMasterDAOImpl extends
		GenericDAOImpl<SmsOutgoingMaster, Long> implements SmsOutgoingMasterDAO {

	private static final long serialVersionUID = 1L;

}
