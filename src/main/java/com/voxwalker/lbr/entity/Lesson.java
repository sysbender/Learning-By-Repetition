package com.voxwalker.lbr.entity;

import java.util.List;

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
	
	@ManyToOne
	@JoinColumn(name="course_id")
	private Course course;
	
	@OneToMany(mappedBy="lesson")
	private List<Upload> upload;
	
	@Size(min=3, message="Name : min size 3")
	private String name;

	
	
	private String text1;
	@URL
	private String audio1;
	private int start1;
	private int end1;
	
	
	private String text2;
	@URL
	private String audio2;
	private int start2;
	private int end2;
	
	
	
	
	
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
	public String getText1() {
		return text1;
	}
	public void setText1(String text1) {
		this.text1 = text1;
	}
	public String getAudio1() {
		return audio1;
	}
	public void setAudio1(String audio1) {
		this.audio1 = audio1;
	}
	public int getStart1() {
		return start1;
	}
	public void setStart1(int start1) {
		this.start1 = start1;
	}
	public int getEnd1() {
		return end1;
	}
	public void setEnd1(int end1) {
		this.end1 = end1;
	}
	public String getText2() {
		return text2;
	}
	public void setText2(String text2) {
		this.text2 = text2;
	}
	public String getAudio2() {
		return audio2;
	}
	public void setAudio2(String audio2) {
		this.audio2 = audio2;
	}
	public int getStart2() {
		return start2;
	}
	public void setStart2(int start2) {
		this.start2 = start2;
	}
	public int getEnd2() {
		return end2;
	}
	public void setEnd2(int end2) {
		this.end2 = end2;
	}
	public List<Upload> getUpload() {
		return upload;
	}
	public void setUpload(List<Upload> upload) {
		this.upload = upload;
	}
	
	// getter and setter
	
	

	
}
