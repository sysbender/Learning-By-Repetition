package com.voxwalker.lbr.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.voxwalker.lbr.entity.Item;
import com.voxwalker.lbr.entity.Lesson;
import com.voxwalker.lbr.model.Student;
import com.voxwalker.lbr.service.ItemService;
import com.voxwalker.lbr.service.LessonService;
import com.voxwalker.lbr.service.NlpService;
import com.voxwalker.lbr.service.UserService;

@Controller
public class LearnController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LessonService lessonService;
	
	@Autowired
	private ItemService itemService;

	@RequestMapping("/learn")
	public String learn( Model model, Principal principal){
		String name = principal.getName();
		//model.addAttribute("user", userService.findOneWithCourses(name));
		model.addAttribute("user", userService.findOneWithCourses(name));
		return "learn";
	}
	
	@RequestMapping("/learn/lesson/{lesson_id}")
	public String learnLesson(@PathVariable Long lesson_id, Model model) {

		Lesson lesson = lessonService.findOne(lesson_id);
		System.out.println(lesson_id + " = id;====================== name = "
				+ lesson.getName());
		List<Item> items = itemService.findByLesson(lesson);
		System.out.println("====================item size =" + items.size() );
		//List<String> words_unknown = new ArrayList<String>();
		//List<String> words_new = new ArrayList<String>();
		Set<String> wordsAllSet = new HashSet<String>();
		for(Item item : items){
			System.out.println( "item genre :" + item.getGenre());
			System.out.println( "item txt :" + item.getTxt());
			if("text".equalsIgnoreCase(item.getGenre() ) ){
				String txt = item.getTxt();
				wordsAllSet = NlpService.tokenizeSet(item.getLang(), txt);
			}
		}
		
		// convert word list to json
		System.out.println( "====== number of word added to json: " + wordsAllSet.size() );
		ObjectMapper mapper = new ObjectMapper();
		String json ="";
		try{
			json = mapper.writeValueAsString(wordsAllSet.toArray());
		}catch( Exception e){			
			e.printStackTrace();
		}
		
		model.addAttribute("words_new", json);
		
		//model.addAttribute("words_new", wordsAllSet.toArray());
		model.addAttribute("items", items);
		model.addAttribute("lesson_id", lesson_id);
		return "learn-lesson";
	}
	
	/**
	 * @ResponseBody - serialized
	 */
	@RequestMapping(value="/learn/word/{lesson_id}")
	 
	public List<Student> saveWords(@RequestParam List<String> unknown_words, @PathVariable Long lesson_id){
		

	 
		return null ;
	}
	
	
}
