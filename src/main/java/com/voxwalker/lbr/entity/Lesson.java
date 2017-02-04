package com.voxwalker.lbr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Lesson {
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	private String content;
	
	@Column(name="audio_url")
	private String audioUrl;
	
	@Column(name="audio_start")
	private int audioStart;
	
	@Column(name="audio_end")
	private int audioEnd;
	
	@ManyToOne
	@JoinColumn(name="course_id")
	private Course course;
	
	
	
	
	
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
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


	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAudioUrl() {
		return audioUrl;
	}
	public void setAudioUrl(String audioUrl) {
		this.audioUrl = audioUrl;
	}
	public int getAudioStart() {
		return audioStart;
	}
	public void setAudioStart(int audioStart) {
		this.audioStart = audioStart;
	}
	public int getAudioEnd() {
		return audioEnd;
	}
	public void setAudioEnd(int audioEnd) {
		this.audioEnd = audioEnd;
	}
	
	
	
	
	
}
