package com.aitrich.smsapi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * <pre>
 * MessageFilterWord Entity
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
@Table(name = "MESSAGE_FILTER_WORD")
public class MessageFilterWord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7138465452310583423L;
	private Long id;
	private String word;

	public MessageFilterWord() {
		super();
	}

	public MessageFilterWord(Long id, String word) {
		super();
		this.id = id;
		this.word = word;
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

	@Column(name = "WORD")
	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

}
