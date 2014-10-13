package com.aitrich.smsapi.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.aitrich.smsapi.dao.MessageFilterWordDAO;
import com.aitrich.smsapi.model.MessageFilterWord;
import com.aitrich.smsapi.services.MessageFilterWordService;

@Service("MessageFilterWordService")
public class MessageFilterWordServiceImpl implements MessageFilterWordService {
	private MessageFilterWordDAO dao;

	public MessageFilterWordServiceImpl() {
		super();
	}

	@Autowired
	public MessageFilterWordServiceImpl(
			@Qualifier("MessageFilterWordDAO") MessageFilterWordDAO dao) {
		super();
		this.dao = dao;
	}

	@Override
	public MessageFilterWord findById(long messageFilterWordId) {
		return dao.findByPrimaryKey(messageFilterWordId);
	}

	@Override
	public List<MessageFilterWord> findAll() {
		return dao.findAll();
	}

}
