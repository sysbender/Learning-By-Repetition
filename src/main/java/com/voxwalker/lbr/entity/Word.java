package com.voxwalker.lbr.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Word {
	@Id
	@GeneratedValue
	private Long id;
	
	private String txt;		
	private int state; // 0 known, 1~ unknown	
	private String lang;
	
	private Date addedDate;
	private Date reviewedDate; 
	
	@ManyToOne
	@JoinColumn( name="user_id")
	private User user;

	
	// getter and setter
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	public Date getReviewedDate() {
		return reviewedDate;
	}

	public void setReviewedDate(Date reviewedDate) {
		this.reviewedDate = reviewedDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}
	

	
	
	
}
