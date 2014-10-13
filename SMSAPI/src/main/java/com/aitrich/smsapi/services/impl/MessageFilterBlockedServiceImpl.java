package com.aitrich.smsapi.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.aitrich.smsapi.dao.MessageFilterBlockedDAO;
import com.aitrich.smsapi.model.MessageFilterBlocked;
import com.aitrich.smsapi.services.MessageFilterBlockedService;

/**
 * <pre>
 * A service implementation for the {@link MessageFilterBlockedService}.
 * 
 * <h5>&copy;2012 aitrich Technologies. All rights reserved.</h5>
 * </pre>
 * 
 * @author : Kavya M
 * @since : June 14, 2014
 * @version : 1.0
 * 
 */
@Service("MessageFilterBlockedService")
@Scope("prototype")
public class MessageFilterBlockedServiceImpl implements
		MessageFilterBlockedService {
	private MessageFilterBlockedDAO dao;

	public MessageFilterBlockedServiceImpl() {
		super();
	}

	@Autowired
	public MessageFilterBlockedServiceImpl(
			@Qualifier("MessageFilterBlockedDAO") MessageFilterBlockedDAO dao) {
		super();
		this.dao = dao;
	}

	@Override
	public MessageFilterBlocked findById(long messageFilterBlockedId) {
		return dao.findByPrimaryKey(messageFilterBlockedId);
	}

	@Override
	public long save(MessageFilterBlocked filterBlocked) {
		return dao.save(filterBlocked);
	}

}
