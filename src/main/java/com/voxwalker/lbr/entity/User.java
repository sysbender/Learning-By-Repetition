package com.voxwalker.lbr.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.voxwalker.lbr.annotation.UniqueUsername;

@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue
	private Long id;
	
	@Size(min=3, message="Name : min size 3")
	@Column(unique=true)
	@UniqueUsername(message="such user name already exist")
	private String name;
	
	@Email
	@Size(min=3, message="Name : min size 3")
	@Column(unique=true)
	private String email;
	
	@Size(min=3, message="Name : min size 3")
	private String password;
	private boolean enabled;
	
	@ManyToMany
	@JoinTable(name="user_role")
	private List<Role> roles;
	
	@OneToMany(mappedBy="user" , fetch=FetchType.LAZY, cascade=CascadeType.REMOVE) // by default, LAZY
	private List<Course> courses;
	
	@OneToMany(mappedBy="user" , cascade=CascadeType.REMOVE) 
	private Set<Word> words;
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	
	
	
}
