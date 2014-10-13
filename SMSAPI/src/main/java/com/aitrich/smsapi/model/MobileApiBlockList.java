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
 * MOBILE_API_BLOCK_LIST Entity
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
@Table(name = "MOBILE_API_BLOCK_LIST")
public class MobileApiBlockList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7363288473831926054L;
	private Long id;
	private String mobile;
	private SmsApi smsApi;
	private int count;
	private Date date;
	private boolean isBlocked;

	public MobileApiBlockList() {
		super();
	}

	public MobileApiBlockList(Long id, String mobile, SmsApi apiId, int count,
			Date date, boolean isBlocked) {
		super();
		this.id = id;
		this.mobile = mobile;
		this.smsApi = apiId;
		this.count = count;
		this.date = date;
		this.isBlocked = isBlocked;
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

	@Column(name = "MOBILE")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@ManyToOne
	@JoinColumn(name = "SMS_API_ID", nullable = false)
	public SmsApi getSmsApi() {
		return smsApi;
	}

	public void setSmsApi(SmsApi smsApi) {
		this.smsApi = smsApi;
	}

	@Column(name = "COUNT")
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Column(name = "DATE", nullable = false)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "IS_BLOCKED")
	public boolean getIsBlocked() {
		return isBlocked;
	}

	public void setIsBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

}
