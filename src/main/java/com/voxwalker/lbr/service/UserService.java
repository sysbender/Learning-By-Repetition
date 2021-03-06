package com.voxwalker.lbr.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.voxwalker.lbr.entity.Course;
import com.voxwalker.lbr.entity.Role;
import com.voxwalker.lbr.entity.User;
import com.voxwalker.lbr.model.UserSummary;
import com.voxwalker.lbr.repository.CourseRepository;
import com.voxwalker.lbr.repository.LessonRepository;
import com.voxwalker.lbr.repository.RoleRepository;
import com.voxwalker.lbr.repository.UserRepository;
import com.voxwalker.lbr.repository.WordRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private WordRepository wordRepository;
	
	@Autowired
	private LessonRepository lessonRepository;
	
	

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findOne(Long id) {
		return userRepository.findOne(id);
	}

	@Transactional
	public User findOneWithCourses(Long id) {
		User user = findOne(id);
		//List<Course> courses = courseRepository.findByUser(user);
		List<Course> courses = courseService.findByUserWithLessons(user);
		user.setCourses(courses);
		return user;
	}

	public void save(User user) {
		user.setEnabled(true);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));

		List<Role> roles = new ArrayList<Role>();
		roles.add( roleRepository.findByName("ROLE_USER"));
		user.setRoles( roles);
		userRepository.save(user);

	}

	public User findOneWithCourses(String name) {
		User user = userRepository.findByName(name);
		 
		return findOneWithCourses(user.getId());
	}

	public void delete(Long id) {
		userRepository.delete(id);
		
	}

	public User findOne(String username) {
		return userRepository.findByName(username);
		
	}

	public User findByName(String username) {

		return userRepository.findByName(username);
	}

	public UserSummary getUserSummary(User user, String lang) {
		UserSummary summary = new UserSummary();
		summary.setUser(user);
		summary.setLang(lang);
		summary.setCountCourse(courseRepository.countByUserAndLang(user, lang));
		//count lesson
		List<Course> courses = courseRepository.findByUserAndLang(user,lang);
		int countLesson = 0;
		for(Course course : courses){
			countLesson += lessonRepository.countByCourse(course);
		}
		summary.setCountLesson(countLesson);
		summary.setCountWordKnown(wordRepository.countByUserAndLangAndState(user,lang,0));
		summary.setCountWordUnknown(wordRepository.countByUserAndLangAndState(user,lang,1));
		return summary;
	}

}
