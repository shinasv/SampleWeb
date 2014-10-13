package com.aitrich.smsapi.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.aitrich.smsapi.dao.RoleDAO;
import com.aitrich.smsapi.model.Role;
import com.aitrich.smsapi.services.RoleService;

/**
 * <pre>
 * A service implementation for the {@link RoleService}.
 * 
 * <h5>&copy; 2014 aitrich Technologies. All rights reserved.</h5>
 * </pre>
 * 
 * @author : Muhammed Riyas T
 * @since : July 07, 2014
 * @version : 1.0
 * 
 */
@Service("RoleService")
@Scope("prototype")
public class RoleServiceImpl implements RoleService {

	private RoleDAO roleDAO;

	@Autowired
	public RoleServiceImpl(@Qualifier("RoleDAO") RoleDAO dao) {
		super();
		this.roleDAO = dao;
	}

	@Override
	public Role findRoleByRoleName(String roleName) {
		return roleDAO.findRoleByRoleName(roleName);
	}

}
