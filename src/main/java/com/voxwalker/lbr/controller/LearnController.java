package com.voxwalker.lbr.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.voxwalker.lbr.entity.Item;
import com.voxwalker.lbr.entity.Lesson;
import com.voxwalker.lbr.service.ItemService;
import com.voxwalker.lbr.service.LessonService;
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
		model.addAttribute("items", items);
		return "learn-lesson";
	}
	
}
