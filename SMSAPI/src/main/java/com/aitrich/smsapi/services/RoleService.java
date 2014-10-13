package com.aitrich.smsapi.services;

import com.aitrich.smsapi.model.Role;

/**
 * <pre>
 * A service interface for the Role Entity.
 * 
 * <h5>&copy;2012 aitrich Technologies. All rights reserved.</h5>
 * </pre>
 * 
 * @author : Kavya M
 * @since : June 13, 2014
 * @version : 1.0
 * 
 */
public interface RoleService {

	public Role findRoleByRoleName(String roleName);

}
