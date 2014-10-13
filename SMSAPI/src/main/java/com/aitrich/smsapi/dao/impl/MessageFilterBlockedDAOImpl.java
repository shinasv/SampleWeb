package com.aitrich.smsapi.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.aitrich.smsapi.dao.MessageFilterBlockedDAO;
import com.aitrich.smsapi.model.MessageFilterBlocked;

@Repository("MessageFilterBlockedDAO")
@Scope("prototype")
public class MessageFilterBlockedDAOImpl extends
		GenericDAOImpl<MessageFilterBlocked, Long> implements
		MessageFilterBlockedDAO {

	private static final long serialVersionUID = 1L;

}
