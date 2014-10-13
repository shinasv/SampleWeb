package com.aitrich.smsapi.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.aitrich.smsapi.dao.MessageFilterWordDAO;
import com.aitrich.smsapi.model.MessageFilterWord;

@Repository("MessageFilterWordDAO")
@Scope("prototype")
public class MessageFilterWordDAOImpl extends
		GenericDAOImpl<MessageFilterWord, Long> implements MessageFilterWordDAO {

	private static final long serialVersionUID = 1L;

}
