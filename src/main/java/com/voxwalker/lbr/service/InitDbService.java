package com.voxwalker.lbr.service;

import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.voxwalker.lbr.entity.Course;
import com.voxwalker.lbr.entity.Item;
import com.voxwalker.lbr.entity.Lesson;
import com.voxwalker.lbr.entity.Role;
import com.voxwalker.lbr.entity.Upload;
import com.voxwalker.lbr.entity.User;
import com.voxwalker.lbr.repository.CourseRepository;
import com.voxwalker.lbr.repository.ItemRepository;
import com.voxwalker.lbr.repository.LessonRepository;
import com.voxwalker.lbr.repository.RoleRepository;
import com.voxwalker.lbr.repository.UploadRepository;
import com.voxwalker.lbr.repository.UserRepository;

@Transactional
@Service
public class InitDbService {
	@Autowired
	private RoleRepository roleRepository;
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	
	@Autowired
	private LessonRepository lessonRepository;
	
	@Autowired
	private UploadRepository uploadRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	
	@PostConstruct // executed after dependency injection is done to perform any initialization.
	public void init(){
		
		// add roles
		Role roleUser = new Role();
		roleUser.setName("ROLE_USER");
		roleRepository.save(roleUser);
		
		Role roleAdmin = new Role();
		roleAdmin.setName("ROLE_ADMIN");
		roleRepository.save(roleAdmin);
		
		// add users
		User userAdmin = new User();
		userAdmin.setName("admin");
		userAdmin.setEnabled(true);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		userAdmin.setPassword(encoder.encode("admin"));
		userAdmin.setRoles( Arrays.asList( new Role[]{roleUser, roleAdmin}));
		userRepository.save(userAdmin);
		
		User userGuest = new User();
		userGuest.setName("guest");
		userGuest.setEnabled(true);
		userGuest.setPassword(encoder.encode("guest"));
		userGuest.setRoles( Arrays.asList( new Role[]{roleUser}));
		userRepository.save(userGuest);
		
		
		// add courses
		
		Course course = new Course();
		course.setName("English Course 1");
		course.setLang("En");
		course.setUser(userAdmin);
		course.setDesc("this is english course 1");
		courseRepository.save(course);
		
		
		Course course2 = new Course(); 
		course2.setName("French Course 2");
		course2.setLang("Fr");
		course2.setUser(userAdmin);
		course2.setDesc("this is French course 2");
		courseRepository.save(course2);
		
		
		// add lessons
		
		Lesson lesson = new Lesson();
		lesson.setCourse(course);
		lesson.setName("english lesson 01");
		//lesson.setText1("long time ago, there is a hill. on the hill, there is a temple, in the temple, there is a monk.");
		//lesson.setAudio1("http://traffic.libsyn.com/blogrelations/Match20in20the20sky.mp3");
		lessonRepository.save(lesson);
		
		
		
		Lesson lesson2 = new Lesson();
		lesson2.setCourse(course2);
		lesson2.setName("french lesson 01");
		//lesson2.setText1("Il était une fois une maman cochon qui avait trois petits cochons. Elle les aimait beaucoup, mais comme il n'y avait pas assez de nourriture pour qu'ils puissent tous manger à leur faim, elle les a envoyé tenter leur chance dans le vaste monde.");
		//lesson2.setAudio1("http://www.thefrenchexperiment.com/audio/threepigs/threepigs1.mp3");
		lessonRepository.save(lesson2);
		
		Upload upload = new Upload();
		upload.setLesson(lesson);
		upload.setName("lesson01text");
		upload.setFile("test.mp3");
		upload.setUrl("http://localhost/test.mp3");
		uploadRepository.save(upload);
		
		Item item = new Item();
		item.setLesson(lesson);
		item.setGenre("Text");
		item.setLang("en");
		item.setTxt("this is text for lesson, please learn hard....");
		item.setAudioUrl("http://localhost/test.mp3");
		itemRepository.save(item);
		
		
		Upload upload2 = new Upload();
		upload2.setLesson(lesson2);
		upload2.setName("lesson01text");
		upload2.setFile("test.mp3");
		upload2.setUrl("http://localhost/test.mp3");
		uploadRepository.save(upload2);
		
		Item item2 = new Item();
		item2.setLesson(lesson2);
		item2.setGenre("Text");
		item2.setLang("en");
		item2.setTxt("this is text for lesson, please learn hard....");
		item2.setAudioUrl("http://localhost/test.mp3");
		itemRepository.save(item2);
		
	}
	
	
	
	
	
	
}
