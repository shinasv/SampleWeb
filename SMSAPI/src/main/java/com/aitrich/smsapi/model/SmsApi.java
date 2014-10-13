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
 * SmsApi Entity
 * 
 * 
 * <h5>&copy; 2012 aitrich Technologies. All rights reserved.</h5>
 * </pre>
 * 
 * @author : Kavya M
 * @since : June 11, 2014
 * @version : 1.0
 * 
 */

@Entity
@Table(name = "SMS_API")
public class SmsApi implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7721131921733678535L;

	private Long id;
	private String vendorName;
	private Status status = Status.ACTIVE;
	private Type type = Type.CALL;
	private String api;
	private int priority;
	private boolean isDelete;
	private Date createdDate;

	public SmsApi() {
		super();

	}

	public SmsApi(Long id, String vendorName, Status status, Type type,
			String api, int priority, boolean isDelete, Date createdDate) {
		super();
		this.id = id;
		this.vendorName = vendorName;
		this.status = status;
		this.type = type;
		this.api = api;
		this.priority = priority;
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

	@Column(name = "VENDOR_NAME")
	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	@Column(name = "STATUS")
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Column(name = "TYPE")
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Column(name = "PRIORITY", length = 11)
	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	@Column(name = "API")
	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
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

	public enum Status {
		ACTIVE, INACTIVE;
	}

	public enum Type {
		MESSAGE, CALL;
	}
}
