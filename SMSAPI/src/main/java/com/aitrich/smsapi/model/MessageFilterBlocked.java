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
 * MessageFilterBlocked Entity
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
@Table(name = "MESSAGE_FILTER_BLOCKED")
public class MessageFilterBlocked implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3204051583138177366L;
	private Long id;
	private SmsOutgoingMaster smsOutgoingMaster;
	private MessageFilterWord messageFilterWord;
	private Date dateTime;

	public MessageFilterBlocked() {
		super();
	}

	public MessageFilterBlocked(Long id, SmsOutgoingMaster smsOutgoingMaster,
			MessageFilterWord messageFilterWord, Date dateTime) {
		super();
		this.id = id;
		this.smsOutgoingMaster = smsOutgoingMaster;
		this.messageFilterWord = messageFilterWord;
		this.dateTime = dateTime;
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
	@JoinColumn(name = "SMS_OUTGOING_ID", nullable = false)
	public SmsOutgoingMaster getSmsOutgoingMaster() {
		return smsOutgoingMaster;
	}

	public void setSmsOutgoingMaster(SmsOutgoingMaster smsOutgoingMaster) {
		this.smsOutgoingMaster = smsOutgoingMaster;
	}

	@ManyToOne
	@JoinColumn(name = "MESSAGE_FILTER_WORD_ID", nullable = false)
	public MessageFilterWord getMessageFilterWord() {
		return messageFilterWord;
	}

	public void setMessageFilterWord(MessageFilterWord messageFilterWord) {
		this.messageFilterWord = messageFilterWord;
	}

	@Column(name = "DATE_TIME", nullable = false)
	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

}
