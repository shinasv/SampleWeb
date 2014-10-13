package com.aitrich.smsapi.model;

import java.util.ArrayList;


import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * 
 * <pre>
 * RESELLER Entity
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
@Table(name = "reseller")
public class Reseller {

	private Long id;
	private String name;
	private String address;
	private String phone;
	private String email;
	private String secondaryPhone;
	private String website;
	private UserAccount userAccount;
	private Status status = Status.ACTIVE;
	private boolean isDelete = false;
	private Date createdDate;
	private List<EndUser> endUserList = new ArrayList<EndUser>();

	public Reseller() {
		super();
	}

	public Reseller(Long id, String name, String address, String phone,
			String email, String secondaryPhone, String website,
			UserAccount userAccount, Status status, boolean isDelete,
			Date createdDate) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.secondaryPhone = secondaryPhone;
		this.website = website;
		this.userAccount = userAccount;
		this.status = status;
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

	@Column(name = "NAME", nullable = false, length = 50)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "ADDRESS")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "PHONE", nullable = false, length = 50)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "EMAIL", nullable = false, length = 50)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "SECONDARY_PHONE", nullable = false, length = 50)
	public String getSecondaryPhone() {
		return secondaryPhone;
	}

	public void setSecondaryPhone(String secondaryPhone) {
		this.secondaryPhone = secondaryPhone;
	}

	@Column(name = "WEBSITE", nullable = false, length = 50)
	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@ManyToOne
	@JoinColumn(name = "USER_ACCOUNT_ID", nullable = false, unique = true)
	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	@Column(name = "STATUS")
	@Enumerated(value = EnumType.STRING)
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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

	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "RESELLER_ID")
	public List<EndUser> getEndUserList() {
		return endUserList;
	}

	public void setEndUserList(List<EndUser> endUserList) {
		this.endUserList = endUserList;
	}

}
