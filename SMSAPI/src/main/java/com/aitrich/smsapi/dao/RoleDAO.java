package com.aitrich.smsapi.dao;

import com.aitrich.smsapi.model.Role;

/**
 * <pre>
 * A Data Access Object for the Role Entity.
 * 
 * <h5>&copy;2012 aitrich Technologies. All rights reserved.</h5>
 * </pre>
 * 
 * @author : Kavya M
 * @since : June 14, 2014
 * @version : 1.0
 * 
 */
public interface RoleDAO extends GenericDAO<Role, Long> {

	public Role findRoleByRoleName(String roleName);

}
