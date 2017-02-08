package com.voxwalker.lbr.service;

import java.util.Iterator;
import java.util.List;

import org.apache.velocity.runtime.directive.Foreach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.voxwalker.lbr.entity.Course;
import com.voxwalker.lbr.entity.User;
import com.voxwalker.lbr.repository.CourseRepository;
import com.voxwalker.lbr.repository.LessonRepository;
import com.voxwalker.lbr.repository.UserRepository;

@Service
public class CourseService {

	@Autowired 
	private CourseRepository courseRepository;
	
	@Autowired 
	private LessonRepository lessonRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	public void save(Course course, String name) {
		 User user = userRepository.findByName(name);
		 course.setUser(user);
		 courseRepository.save(course);
		
	}


	@PreAuthorize("#course.user.name == authentication.name or hasRole('ROLE_ADMIN')")
	public void delete(@P("course")Course course) {
		courseRepository.delete(course);
		
	}


	public Course findOne(Long id) {
		 
		return courseRepository.findOne(id);
	}


	public List<Course> findByUserWithLessons(User user) {
		List<Course> courses = courseRepository.findByUser(user);
		for (Course course : courses) {	
			System.out.println("-------------------------------"+ course.getName());
			course.setLessons( lessonRepository.findByCourse(course));			
		}	
		 
		return courses;
	}

}
