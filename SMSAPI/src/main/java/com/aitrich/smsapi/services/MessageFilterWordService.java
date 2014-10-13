package com.aitrich.smsapi.services;

import java.util.List;

import com.aitrich.smsapi.model.MessageFilterWord;

/**
 * <pre>
 * A service interface for the MessageFilterWord Entity.
 * 
 * <h5>&copy;2012 aitrich Technologies. All rights reserved.</h5>
 * </pre>
 * 
 * @author : Kavya M
 * @since : June 14, 2014
 * @version : 1.0
 * 
 */
public interface MessageFilterWordService {
	MessageFilterWord findById(long messageFilterWordId);

	List<MessageFilterWord> findAll();

}
