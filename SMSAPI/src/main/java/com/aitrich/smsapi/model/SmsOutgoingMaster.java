package com.aitrich.smsapi.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import org.hibernate.annotations.OrderBy;

/**
 * 
 * <pre>
 * SMSOUTGOING Entity
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
@Table(name = "SMS_OUTGOING_MASTER")
public class SmsOutgoingMaster implements Serializable {

	/**
*
*/
	private static final long serialVersionUID = -3264281334543382179L;
	private Long id;
	private UserAccount userAccount;
	private String mobileNo;
	private String message;
	private String senderId;
	private int smsUnit;
	private Date sentDateTime;
	private boolean needRedirection;
	private double redirectInterval;
	private int redirectCount;
	private String remarks;
	private Date deliveredTime;
	private boolean isDelivered;

	private List<SmsOutgoingMasterDetail> masterDetails = new ArrayList<SmsOutgoingMasterDetail>();

	public SmsOutgoingMaster() {
		super();
	}

	public SmsOutgoingMaster(Long id, UserAccount userAccount, String mobileNo,
			String message, String senderId, int smsUnit, Date sentDateTime,
			boolean needRedirection, double redirectInterval,
			int redirectCount, String remarks, Date deliveredTime,
			boolean isDelivered, List<SmsOutgoingMasterDetail> masterDetails) {
		super();
		this.id = id;
		this.userAccount = userAccount;
		this.mobileNo = mobileNo;
		this.message = message;
		this.senderId = senderId;
		this.smsUnit = smsUnit;
		this.sentDateTime = sentDateTime;
		this.needRedirection = needRedirection;
		this.redirectInterval = redirectInterval;
		this.redirectCount = redirectCount;
		this.remarks = remarks;
		this.deliveredTime = deliveredTime;
		this.isDelivered = isDelivered;
		this.masterDetails = masterDetails;
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

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	@Column(name = "MOBILE_NO", nullable = false)
	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	@Column(name = "MESSAGE", nullable = false)
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Column(name = "SENDER_ID", nullable = false)
	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	@Column(name = "SMS_UNIT", nullable = false)
	public int getSmsUnit() {
		return smsUnit;
	}

	public void setSmsUnit(int smsunit) {
		this.smsUnit = smsunit;
	}

	@Column(name = "SENT_DATE_TIME", nullable = false)
	public Date getSentDateTime() {
		return sentDateTime;
	}

	public void setSentDateTime(Date sentDateTime) {
		this.sentDateTime = sentDateTime;
	}

	@Column(name = "NEED_REDIRECTION")
	public boolean getNeedRedirection() {
		return needRedirection;
	}

	public void setNeedRedirection(boolean needRedirection) {
		this.needRedirection = needRedirection;
	}

	@Column(name = "REDIRECT_INTERVAL")
	public double getRedirectInterval() {
		return redirectInterval;
	}

	public void setRedirectInterval(double redirectInterval) {
		this.redirectInterval = redirectInterval;
	}

	@Column(name = "REDIRECT_COUNT", length = 2)
	public int getRedirectCount() {
		return redirectCount;
	}

	public void setRedirectCount(int redirectCount) {
		this.redirectCount = redirectCount;
	}

	@Column(name = "REMARKS")
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "DELIVERED_DATE_TIME")
	public Date getDeliveredTime() {
		return deliveredTime;
	}

	public void setDeliveredTime(Date deliveredTime) {
		this.deliveredTime = deliveredTime;
	}

	@Column(name = "IS_DELIVERED")
	public boolean getIsDelivered() {
		return isDelivered;
	}

	public void setIsDelivered(boolean isDelivered) {
		this.isDelivered = isDelivered;
	}

	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "SMS_OUTGOING_MASTER_ID")
	@OrderBy(clause = "id asc")
	public List<SmsOutgoingMasterDetail> getMasterDetails() {
		return masterDetails;
	}

	public void setMasterDetails(List<SmsOutgoingMasterDetail> masterDetails) {
		this.masterDetails = masterDetails;
	}
}
