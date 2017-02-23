package com.voxwalker.lbr.model;

import com.voxwalker.lbr.entity.User;

public class UserSummary {
	private User user;
	private String lang;
	private int countCourse;
	private int countLesson;
	private int countWordKnown;
	private int countWordUnknown;
	
	
	
	
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
	public int getCountCourse() {
		return countCourse;
	}
	public void setCountCourse(int countCourse) {
		this.countCourse = countCourse;
	}
	public int getCountLesson() {
		return countLesson;
	}
	public void setCountLesson(int countLesson) {
		this.countLesson = countLesson;
	}
	public int getCountWordKnown() {
		return countWordKnown;
	}
	public void setCountWordKnown(int countWordKnown) {
		this.countWordKnown = countWordKnown;
	}
	public int getCountWordUnknown() {
		return countWordUnknown;
	}
	public void setCountWordUnknown(int countWordUnknown) {
		this.countWordUnknown = countWordUnknown;
	}

	
	

}
