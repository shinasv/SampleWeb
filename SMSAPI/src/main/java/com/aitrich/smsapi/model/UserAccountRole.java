package com.aitrich.smsapi.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * <pre>
 * USER_ACCOUNT_ROLE Entity
 *  
 *  
 *  <h5>&copy; 2012 aitrich Technologies. All rights reserved.</h5>
 * </pre>
 * 
 * @author : Kavya M
 * @since : June 12, 2014
 * @version : 1.0
 * 
 */

@Entity
@Table(name = "USER_ACCOUNT_ROLE")
public class UserAccountRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3451580954940496517L;
	private Long id;
	private UserAccount userAccount;
	private Role roleId;
	private boolean isDelete = false;
	private Date createdDate;

	public UserAccountRole() {
		super();
	}

	public UserAccountRole(Long id, UserAccount userAccount, Role roleId,
			boolean isDelete, Date createdDate) {
		super();
		this.id = id;
		this.userAccount = userAccount;
		this.roleId = roleId;
		this.isDelete = isDelete;
		this.createdDate = createdDate;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, length = 11)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "USER_ACCOUNT_ID", nullable = false)
	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccountId) {
		this.userAccount = userAccountId;
	}

	@ManyToOne
	@JoinColumn(name = "ROLE_ID", nullable = false)
	public Role getRoleId() {
		return roleId;
	}

	public void setRoleId(Role roleId) {
		this.roleId = roleId;
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
