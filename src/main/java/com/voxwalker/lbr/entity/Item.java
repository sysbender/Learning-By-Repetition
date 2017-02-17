package com.voxwalker.lbr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.URL;

@Entity
@Table(name="item")
public class Item {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String genre;
	private String lang;
	@Lob
	@Type(type="org.hibernate.type.StringClobType")
	@Column(length = Integer.MAX_VALUE)
	private String txt;
	@Column(name="match1")
	private boolean match; // if txt match audio
	@URL
	@Column(name="audio_url")
	private String audioUrl;
	@Column(name="cue_start")
	private int cueStart;
	@Column(name="cue_end")
	private int cueEnd;
	
	@ManyToOne
	@JoinColumn(name="lesson_id")
	private Lesson lesson;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public boolean isMatch() {
		return match;
	}

	public void setMatch(boolean match) {
		this.match = match;
	}

	public String getAudioUrl() {
		return audioUrl;
	}

	public void setAudioUrl(String audioUrl) {
		this.audioUrl = audioUrl;
	}

	public int getCueStart() {
		return cueStart;
	}

	public void setCueStart(int cueStart) {
		this.cueStart = cueStart;
	}

	public int getCueEnd() {
		return cueEnd;
	}

	public void setCueEnd(int cueEnd) {
		this.cueEnd = cueEnd;
	}

	public Lesson getLesson() {
		return lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}


	// getter and setter
	
	

	
}
