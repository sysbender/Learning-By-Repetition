package com.voxwalker.lbr.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="t_course")
public class Course {
	@Id
	@GeneratedValue
	private Long id;
	
	@Size(min=3, message="Name : min size 3")
	private String name;
	
	@Column(name="description")
	private String desc;
	
	
	private String lang;
	@Column(name="shared")
	private int share; // 0-private, 1-public
	
	@ManyToOne
	@JoinColumn( name="user_id")
	private User user;
	
	@OneToMany(mappedBy="course", cascade=CascadeType.REMOVE)
	private List<Lesson> lessons;
	

	
	
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Lesson> getLessons() {
		return lessons;
	}
	public void setLessons(List<Lesson> lessons) {
		this.lessons = lessons;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public int getShare() {
		return share;
	}
	public void setShare(int share) {
		this.share = share;
	}
	
	
	
	
	
}