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
 * EndUserCredit Entity
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
@Table(name = "END_USER_CREDIT")
public class EndUserCredit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2613099285702528448L;

	private Long id;
	private EndUser endUser;
	private long totalCredits;
	private long creditUsed;
	private Date createdDate;

	public EndUserCredit() {
		super();
	}

	public EndUserCredit(Long id, EndUser endUser, long totalCredits,
			long creditUsed, Date createdDate) {
		super();
		this.id = id;
		this.endUser = endUser;
		this.totalCredits = totalCredits;
		this.creditUsed = creditUsed;
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
	@JoinColumn(name = "END_USER_ID", nullable = false,unique=true)
	public EndUser getEndUser() {
		return endUser;
	}

	public void setEndUser(EndUser endUser) {
		this.endUser = endUser;
	}

	@Column(name = "TOTAL_CREDIT")
	public long getTotalCredits() {
		return totalCredits;
	}

	public void setTotalCredits(long totalCredits) {
		this.totalCredits = totalCredits;
	}

	@Column(name = "CREDIT_USED")
	public long getCreditUsed() {
		return creditUsed;
	}

	public void setCreditUsed(long creditUsed) {
		this.creditUsed = creditUsed;
	}

	@Column(name = "CREATED_DATE", nullable = false)
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
