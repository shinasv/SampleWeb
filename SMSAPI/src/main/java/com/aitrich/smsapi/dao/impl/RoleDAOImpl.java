package com.aitrich.smsapi.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.aitrich.smsapi.dao.RoleDAO;
import com.aitrich.smsapi.model.Role;

/**
 * 
 * <pre>
 * Role Entity
 * 
 * 
 * <h5>&copy; 2014 aitrich Technologies. All rights reserved.</h5>
 * </pre>
 * 
 * @author : Muhammed Riyas T
 * @since : July 07, 2014
 * @version : 1.0
 * 
 */
@Repository("RoleDAO")
@Scope("prototype")
public class RoleDAOImpl extends GenericDAOImpl<Role, Long> implements RoleDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Role findRoleByRoleName(String roleName) {
		return findByUniqueField("roleName", roleName);
	}

}
