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
 * EndUserPurchaseHistory Entity
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
@Table(name = "END_USER_PURCHASE_HISTORY")
public class EndUserPurchaseHistory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7936952687918632153L;

	private Long id;
	private EndUser endUser;
	private long purchasedCredit;
	private Date purchaseDate;

	public EndUserPurchaseHistory() {
		super();
	}

	public EndUserPurchaseHistory(Long id, EndUser endUser,
			long purchasedCredit, Date purchaseDate) {
		super();
		this.id = id;
		this.endUser = endUser;
		this.purchasedCredit = purchasedCredit;
		this.purchaseDate = purchaseDate;
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
	@JoinColumn(name = "ENDUSER_ID", nullable = false)
	public EndUser getEndUser() {
		return endUser;
	}

	public void setEndUser(EndUser endUser) {
		this.endUser = endUser;
	}

	@Column(name = "PURCHASED_CREDIT", nullable = false, length = 11)
	public long getPurchasedCredit() {
		return purchasedCredit;
	}

	public void setPurchasedCredit(long purchasedCredit) {
		this.purchasedCredit = purchasedCredit;
	}

	@Column(name = "PURCHASED_DATE", nullable = false)
	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
}
