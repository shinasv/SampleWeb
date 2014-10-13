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
 * PURCHASE_HISTORY Entity
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
@Table(name = "RESELLER_PURCHASE_HISTORY")
public class ResellerPurchaseHistory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4660879575306038072L;
	private Long id;
	private Reseller reseller;
	private long purchasedCredit;
	private Date purchasedDate;

	public ResellerPurchaseHistory() {
		super();
	}

	public ResellerPurchaseHistory(Long id, Reseller reseller,
			long purchasedCredit, Date purchasedDate) {
		super();
		this.id = id;
		this.reseller = reseller;
		this.purchasedCredit = purchasedCredit;
		this.purchasedDate = purchasedDate;
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
	@JoinColumn(name = "RESELLER_ID", nullable = false)
	public Reseller getReseller() {
		return reseller;
	}

	public void setReseller(Reseller reseller) {
		this.reseller = reseller;
	}

	@Column(name = "PURCHASED_CREDIT", nullable = false, length = 11)
	public long getPurchasedCredit() {
		return purchasedCredit;
	}

	public void setPurchasedCredit(long purchasedCredit) {
		this.purchasedCredit = purchasedCredit;
	}

	@Column(name = "PURCHASED_DATE", nullable = false)
	public Date getPurchasedDate() {
		return purchasedDate;
	}

	public void setPurchasedDate(Date purchasedDate) {
		this.purchasedDate = purchasedDate;
	}

}
