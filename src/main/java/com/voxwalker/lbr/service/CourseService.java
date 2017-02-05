package com.voxwalker.lbr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voxwalker.lbr.entity.Course;
import com.voxwalker.lbr.entity.User;
import com.voxwalker.lbr.repository.CourseRepository;
import com.voxwalker.lbr.repository.UserRepository;

@Service
public class CourseService {

	@Autowired 
	private CourseRepository courseRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	public void save(Course course, String name) {
		 User user = userRepository.findByName(name);
		 course.setUser(user);
		 courseRepository.save(course);
		
	}

}
