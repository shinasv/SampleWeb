package com.aitrich.smsapi.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * <pre>
 * SmsRedirected Entity
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
@Table(name = "SMS_OUTGOING_MASTER_DETAIL")
public class SmsOutgoingMasterDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4974773622429725099L;

	private Long id;
	private SmsOutgoingMaster smsOutgoingMaster;
	private Date sentDateTime;
	private SmsApi smsApi;
	private DeliveryStatus deliveryStatus = DeliveryStatus.PENDING_STATUS;
	private String reasonCode;
	private boolean needDirection = false;
	private int redirectCount;
	private boolean isRedirected;

	public SmsOutgoingMasterDetail() {
		super();
	}

	public SmsOutgoingMasterDetail(Long id,
			SmsOutgoingMaster smsOutgoingMaster, Date sentDateTime,
			SmsApi smsApi, DeliveryStatus deliveryStatus, String reasonCode,
			boolean needDirection, int redirectCount, boolean isRedirected) {
		super();
		this.id = id;
		this.smsOutgoingMaster = smsOutgoingMaster;
		this.sentDateTime = sentDateTime;
		this.smsApi = smsApi;
		this.deliveryStatus = deliveryStatus;
		this.reasonCode = reasonCode;
		this.needDirection = needDirection;
		this.redirectCount = redirectCount;
		this.isRedirected = isRedirected;
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
	@JoinColumn(name = "SMS_OUTGOING_MASTER_ID", nullable = false)
	public SmsOutgoingMaster getSmsOutgoingMaster() {
		return smsOutgoingMaster;
	}

	public void setSmsOutgoingMaster(SmsOutgoingMaster smsOutgoingMaster) {
		this.smsOutgoingMaster = smsOutgoingMaster;
	}

	@Column(name = "SENT_DATE_TIME", nullable = false)
	public Date getSentDateTime() {
		return sentDateTime;
	}

	public void setSentDateTime(Date sentDateTime) {
		this.sentDateTime = sentDateTime;
	}

	@ManyToOne
	@JoinColumn(name = "SMS_API_ID", nullable = false)
	public SmsApi getSmsApi() {
		return smsApi;
	}

	public void setSmsApi(SmsApi smsApi) {
		this.smsApi = smsApi;
	}

	@Column(name = "DELIVERY_STATUS")
	@Enumerated(value = EnumType.STRING)
	public DeliveryStatus getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	@Column(name = "REASON_CODE")
	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	@Column(name = "NEED_REDIRECTION")
	public boolean isNeedDirection() {
		return needDirection;
	}

	public void setNeedDirection(boolean needDirection) {
		this.needDirection = needDirection;
	}

	@Column(name = "REDIRECT_COUNT")
	public int getRedirectCount() {
		return redirectCount;
	}

	public void setRedirectCount(int redirectCount) {
		this.redirectCount = redirectCount;
	}

	@Column(name = "IS_REDIRECTED")
	public boolean getIsRedirected() {
		return isRedirected;
	}

	public void setIsRedirected(boolean isRedirected) {
		this.isRedirected = isRedirected;
	}

}
