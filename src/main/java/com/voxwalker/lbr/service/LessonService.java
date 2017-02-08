package com.voxwalker.lbr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voxwalker.lbr.entity.Course;
import com.voxwalker.lbr.entity.Lesson;
import com.voxwalker.lbr.repository.LessonRepository;

@Service
public class LessonService {
	@Autowired
	private LessonRepository lessonRepository;

	public Lesson findOne(Long id) {
		// TODO Auto-generated method stub
		return lessonRepository.findOne(id);
	}

	public void delete(Lesson lesson) {
		lessonRepository.delete(lesson);
		
	}

	public List<Lesson> findByCourse(Course course) {
		 
		return lessonRepository.findByCourse(course);
	}

}
