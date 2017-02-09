package com.voxwalker.lbr.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;


@Entity
public class Lesson {
	@Id
	@GeneratedValue
	private Long id;
	
	
	@Size(min=3, message="Name : min size 3")
	private String name;
	
	@ManyToOne
	@JoinColumn(name="course_id")
	private Course course;
	
	@OneToMany(mappedBy="lesson", cascade=CascadeType.REMOVE)
	private List<Upload> uploads;
	
	
	@OneToMany(mappedBy="lesson" , cascade=CascadeType.REMOVE)
	private List<Item> items;
	


	
	
	
	
	
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
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public List<Upload> getUploads() {
		return uploads;
	}
	public void setUploads(List<Upload> uploads) {
		this.uploads = uploads;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	

	
	// getter and setter
	
	

	
}
