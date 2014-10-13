package com.aitrich.smsapi.services;

import com.aitrich.smsapi.model.MessageFilterBlocked;

/**
 * <pre>
 * A service interface for the MessageFilterBlocked Entity.
 * 
 * <h5>&copy;2012 aitrich Technologies. All rights reserved.</h5>
 * </pre>
 * 
 * @author : Kavya M
 * @since : June 13, 2014
 * @version : 1.0
 * 
 */
public interface MessageFilterBlockedService {
	MessageFilterBlocked findById(long messageFilterBlockedId);

	long save(MessageFilterBlocked filterBlocked);
}
