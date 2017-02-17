package com.voxwalker.lbr.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voxwalker.lbr.entity.Course;
import com.voxwalker.lbr.entity.Lesson;
import com.voxwalker.lbr.repository.CourseRepository;
import com.voxwalker.lbr.repository.LessonRepository;

@Service
@Transactional
public class LessonService {
	@Autowired
	private LessonRepository lessonRepository;
	
	@Autowired
	private CourseRepository courseRepository;

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

	public void save(Lesson lesson, Long course_id, String username) {
		 Course course = courseRepository.findOne(course_id);
		 lesson.setCourse(course);
		 lessonRepository.save(lesson);
		
	}



	public Course findCourseByLesson(Lesson lesson) {		
		return  lesson.getCourse();
	}

}
