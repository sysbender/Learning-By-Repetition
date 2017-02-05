package com.voxwalker.lbr.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voxwalker.lbr.entity.Course;
import com.voxwalker.lbr.entity.User;
import com.voxwalker.lbr.repository.CourseRepository;
import com.voxwalker.lbr.repository.UserRepository;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	
	public List<User> findAll(){
		return userRepository.findAll();
	}

	public User findOne(Long id) {		 
		return userRepository.findOne(id);
	}
	
	@Transactional
	public User findOneWithCourses(Long id) {
		User user = findOne(id);
		List<Course> courses = courseRepository.findByUser(user);
		user.setCourses(courses);
		return user;
	}

	public void save(User user) {
		
		userRepository.save(user);
		
	}

}
