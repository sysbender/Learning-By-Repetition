package com.voxwalker.lbr.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Sets;
import com.voxwalker.lbr.entity.Item;
import com.voxwalker.lbr.entity.Lesson;
import com.voxwalker.lbr.entity.User;
import com.voxwalker.lbr.entity.Word;
import com.voxwalker.lbr.model.WordSet;
import com.voxwalker.lbr.service.ItemService;
import com.voxwalker.lbr.service.LessonService;
import com.voxwalker.lbr.service.NlpService;
import com.voxwalker.lbr.service.UserService;
import com.voxwalker.lbr.service.WordService;

@Controller
public class LearnController {

	@Autowired
	private UserService userService;

	@Autowired
	private LessonService lessonService;

	@Autowired
	private ItemService itemService;

	@Autowired
	private WordService wordService;

	@RequestMapping("/learn")
	public String learn(Model model, Principal principal) {
		String name = principal.getName();
		// model.addAttribute("user", userService.findOneWithCourses(name));
		model.addAttribute("user", userService.findOneWithCourses(name));
		return "learn";
	}

	@RequestMapping("/learn/lesson/{lesson_id}")
	public String learnLesson(@PathVariable Long lesson_id, Model model,
			Principal principal, HttpServletRequest request) {



		// get user
		String username = principal.getName();
		User user = userService.findByName(username);

		// get lesson
		Lesson lesson = lessonService.findOne(lesson_id);
		System.out.println(lesson_id + " = id;====================== name = "
				+ lesson.getName());
		
		// get lang
		//String lang = (String) request.getSession().getAttribute("target_lang");
		String lang = lesson.getCourse().getLang();

		List<Item> items = itemService.findByLesson(lesson);
		System.out.println("====================item size =" + items.size());
		// List<String> words_unknown = new ArrayList<String>();
		// List<String> words_new = new ArrayList<String>();
		Set<String> wordAllSet = new HashSet<String>();
		for (Item item : items) {
			System.out.println("item genre :" + item.getGenre());
			System.out.println("item txt :" + item.getTxt());
			if ("text".equalsIgnoreCase(item.getGenre())) {
				String txt = item.getTxt();
				wordAllSet = NlpService.tokenizeSet(item.getLang(), txt);
			}
		}

		// get unknown and known for user
		Set<String> userKnownSet = wordService.findByUserAndLangAndState(user,
				lang, 0);

		Set<String> userUnknownSet = wordService.findByUserAndLangAndState(
				user, lang, 1);
		 System.out.println( "user known set : " + Arrays.toString(userKnownSet.toArray()));
		 System.out.println( "user unknown set : " + Arrays.toString(userUnknownSet.toArray()));
		// calculate sets
		Set<String> userAllSet = Sets.union(userKnownSet, userUnknownSet);

		Set<String> wordKnownSet = Sets.intersection(wordAllSet, userKnownSet);
		Set<String> wordUnknownSet = Sets.intersection(wordAllSet,
				userUnknownSet);
		Set<String> wordNewSet = Sets.difference(wordAllSet, userAllSet);

		// convert word list to json
		System.out.println("====== number of word added to json: "
				+ wordAllSet.size());
		ObjectMapper mapper = new ObjectMapper();

		String jsonNew = "", jsonKnown = "", jsonUnknown = "";
		try {
			jsonNew = mapper.writeValueAsString(wordNewSet.toArray());
			jsonKnown = mapper.writeValueAsString(wordKnownSet.toArray());
			jsonUnknown = mapper.writeValueAsString(wordUnknownSet.toArray());
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("jsonNew", jsonNew);
		model.addAttribute("jsonKnown", jsonKnown);
		model.addAttribute("jsonUnknown", jsonUnknown);

		// model.addAttribute("words_new", wordsAllSet.toArray());
		model.addAttribute("items", items);
		model.addAttribute("lesson_id", lesson_id);
		return "learn-lesson";
	}

	/**
	 * @ResponseBody - serialized
	 */
	@RequestMapping(value = "/learn/word/save/{lesson_id}")
	@ResponseBody
	public String saveWords(@RequestBody WordSet wordSet,
			@PathVariable Long lesson_id, Principal principal) {
		User user = userService.findByName(principal.getName());

		Lesson lesson = lessonService.findOne(lesson_id);
		String lang = lesson.getCourse().getLang();
		for (String word : wordSet.getUnknown()) {
			System.out.println("**************  received unknown : " + word);
		}
		for (String word : wordSet.getUnknown()) {
			System.out.println("**************  received unknown : " + word);
		}

		wordService.save(user, lang, wordSet);

		return "";

	}

}
