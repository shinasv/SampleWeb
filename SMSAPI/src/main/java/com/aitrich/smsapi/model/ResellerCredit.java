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
 * CREDIT Entity
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
@Table(name = "RESELLER_CREDIT")
public class ResellerCredit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Reseller reseller;
	private long totalCredits;
	private long creditUsed;
	private Date createdDate;

	public ResellerCredit() {
		super();
	}

	public ResellerCredit(Long id, Reseller reseller, long totalCredits,
			long creditUsed, Date createdDate) {
		super();
		this.id = id;
		this.reseller = reseller;
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
	@JoinColumn(name = "RESELLER_ID", nullable = false, unique=true)
	public Reseller getReseller() {
		return reseller;
	}

	public void setReseller(Reseller reseller) {
		this.reseller = reseller;
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
