package com.voxwalker.lbr.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
import com.voxwalker.lbr.entity.Word;
import com.voxwalker.lbr.repository.CourseRepository;
import com.voxwalker.lbr.repository.ItemRepository;
import com.voxwalker.lbr.repository.LessonRepository;
import com.voxwalker.lbr.repository.RoleRepository;
import com.voxwalker.lbr.repository.UploadRepository;
import com.voxwalker.lbr.repository.UserRepository;
import com.voxwalker.lbr.repository.WordRepository;

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

	@Autowired
	private WordRepository wordRepository;

	@PostConstruct
	// executed after dependency injection is done to perform any
	// initialization.
	public void init() {
		if(roleRepository.findByName("ROLE_ADMIN") == null){

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
		userAdmin.setRoles(Arrays.asList(new Role[] { roleUser, roleAdmin }));
		userRepository.save(userAdmin);

		User userGuest = new User();
		userGuest.setName("guest");
		userGuest.setEnabled(true);
		userGuest.setPassword(encoder.encode("guest"));
		userGuest.setRoles(Arrays.asList(new Role[] { roleUser }));
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
		/*
	// add lessons

		Lesson lesson = new Lesson();
		lesson.setCourse(course);
		lesson.setName("english lesson 01");
		// lesson.setText1("long time ago, there is a hill. on the hill, there is a temple, in the temple, there is a monk.");
		// lesson.setAudio1("http://traffic.libsyn.com/blogrelations/Match20in20the20sky.mp3");
		lessonRepository.save(lesson);

		Lesson lesson2 = new Lesson();
		lesson2.setCourse(course2);
		lesson2.setName("french lesson 01");
		// lesson2.setText1("Il était une fois une maman cochon qui avait trois petits cochons. Elle les aimait beaucoup, mais comme il n'y avait pas assez de nourriture pour qu'ils puissent tous manger à leur faim, elle les a envoyé tenter leur chance dans le vaste monde.");
		// lesson2.setAudio1("http://www.thefrenchexperiment.com/audio/threepigs/threepigs1.mp3");
		lessonRepository.save(lesson2);

		Upload upload = new Upload();
		upload.setLesson(lesson);
		upload.setName("lesson01text");
		upload.setFile("test.mp3");
		upload.setUrl("http://localhost/test.mp3");
		uploadRepository.save(upload);

		Item item = new Item();
		item.setLesson(lesson);
		item.setGenre("explain");
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
		item2.setLesson(lesson);
		item2.setGenre("text");
		item2.setLang("en");
		item2.setTxt("Senum: Hey, Brett, how are you? <br> Brett: Pretty good, how are you? <br> Senum: Good, thank you. <br> Brett: Hey, do you want to play a game? <br> Senum: Sure. What sort of game? <br> Brett: Let's play 20 questions. <br> Senum: OK, go ahead. <br> Brett: OK, who am I thinking about? <br> Senum: Man? Is it a male? <br> Brett: Yes, it's a male. <br> Senum: Is he an actor? <br> Brett: Yes, he's an actor. <br> Senum: Does he have short hair? <br> Brett: Yes, he has short hair. <br> Senum: Right. Is he famous? <br> Brett: Yes, he's famous. <br> Senum: Does he have blue eyes? <br> Brett: Yes. <br> Senum: He does have blue eyes? <br> Brett: No, he has... <br> Senum: He doesn't have blue eyes? <br> Brett: No, he doesn't have blue eyes. <br> Senum: Does he have brown eyes? <br> Brett: Yes.  <br> Senum: Is he short? <br> Brett: No. <br> Senum: Is he in the Ocean's 12? <br> Brett: Yes.  <br> Senum: He is! <br> Brett: Yes. <br> Senum: OK. Is he married, currently? <br> Brett: NO. <br> Senum: He isn't married? <br> Brett: No. <br> Senum: OK. Is he tall? <br> Brett: Yes. <br> Senum: OK. Is he George Clooney? <br> Brett: Yes. <br> Senum: Great. OK, my turn now. Who am I think about it?  <br> Brett: Is he a male? <br> Senum: Yes.  <br> Brett: Is he an actor? <br> Senum: No.  <br> Brett: Is he a singer? <br> Senum: No. <br> Brett: Is he a politician? <br> Senum: Yes.  <br> Brett: Does he have white hair? <br> Senum: Yes. <br> Brett: Yes. Is he American? <br> Senum: No. <br> Brett: Is he British? <br> Senum: No. <br> Brett: Is he Australian? <br> Senum: Yes.  <br> Brett: Is he your Prime Minister? <br> Senum: He is. <br> Brett: Is he John Howard? <br> Senum: Yes, he is. Well done. ");
		item2.setAudioUrl("http://www.manythings.org/elllo/mp3/8.mp3");
		itemRepository.save(item2);

		String[] ws = { "a", "about", "all", "also", "and", "as", "at", "be",
				"because", "but", "by", "can", "come", "could", "day", "do",
				"even", "find", "first", "for", "from", "get", "give", "go",
				"have", "he", "her", "here", "him", "his", "how", "I", "if",
				"in", "into", "it", "its", "just", "know", "like", "look",
				"make", "man", "many", "me", "more", "my", "new", "no", "not",
				"now", "of", "on", "one", "only", "or", "other", "our", "out",
				"people", "say", "see", "she", "so", "some", "take", "tell",
				"than", "that", "the", "their", "them", "then", "there",
				"these", "they", "thing", "think", "this", "those", "time",
				"to", "two", "up", "use", "very", "want", "way", "we", "well",
				"what", "when", "which", "who", "will", "with", "would",
				"year", "you", "your" };

		for (String w : ws) {
			Word word = new Word();
			word.setTxt(w);
			if (w.length() < 3) {
				word.setState(0); // known
			} else {
				word.setState(1); // unknown
			}

			word.setLang("en");
			word.setAddedDate(new Date());
			word.setReviewedDate(new Date());
			word.setUser(userAdmin);
			wordRepository.save(word);
		}

		List<String> wordKnown = wordRepository.findByUserAndLangWordKnown(
				userAdmin.getId(), "en");
		System.out.println(" admin unknown words : "
				+ Arrays.toString(wordKnown.toArray()));

		List<String> wordUnKnown = wordRepository.findByUserAndLangWordUnknown(
				userAdmin.getId(), "en");
		System.out.println(" admin unknown words : "
				+ Arrays.toString(wordUnKnown.toArray()));*/
		}

	}

}
