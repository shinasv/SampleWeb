package com.aitrich.smsapi.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;

/**
 * 
 * <pre>
 * USER_ACCOUNT Entity
 *  
 *  
 *  <h5>&copy; 2012 aitrich Technologies. All rights reserved.</h5>
 * </pre>
 * 
 * @author : Kavya M
 * @since : June 11, 2014
 * @version : 1.0
 * 
 */

@Entity
@Table(name = "USER_ACCOUNT")
public class UserAccount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3840484702963250440L;
	private Long id;
	private String userName;
	private String password;
	private boolean isDelete = false;
	private Date createdDate;
	
	private List<Role> roles;

	public UserAccount() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserAccount(Long id, String userName, String password,
			boolean isDelete, Date createdDate) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
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

	@Column(name = "USER_NAME")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "USER_ACCOUNT_ROLE", joinColumns = { @JoinColumn(name = "USER_ACCOUNT_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
	@JsonBackReference
	public List<Role> getRoles() {
		return roles;
	}

	
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
