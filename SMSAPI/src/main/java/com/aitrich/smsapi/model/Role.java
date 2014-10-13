package com.aitrich.smsapi.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 
 * <pre>
 * ROLE Entity
 * 
 * 
 * <h5>&copy; 2012 aitrich Technologies. All rights reserved.</h5>
 * </pre>
 * 
 * @author : Kavya M
 * @since : June 12, 2014
 * @version : 1.0
 * 
 */



@Entity
@Table(name = "ROLE")
public class Role implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6001213785170115111L;
	private Long roleId;
	private String roleName;
	private boolean isDelete = false;
	private Date createdDate;

	public Role() {
		super();
	}

	public Role(Long roleId, String roleName, boolean isDelete, Date createdDate) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.isDelete = isDelete;
		this.createdDate = createdDate;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROLE_ID", nullable = false, length = 11)
	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	@Column(name = "ROLE_NAME")
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	@Column(name = "IS_DELETE", length = 1)
	public boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
	@Column(name = "CREATED_DATE", nullable = false)
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
