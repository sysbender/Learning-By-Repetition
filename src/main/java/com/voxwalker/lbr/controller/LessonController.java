package com.voxwalker.lbr.controller;

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
import com.voxwalker.lbr.service.UploadService;

@Controller
public class LessonController {

	
	@Autowired
	private LessonService lessonService ;
	
	@Autowired 
	private ItemService itemService;
	
	@Autowired 
	private UploadService uploadService;
	
	@RequestMapping("/import/lesson/{lesson_id}")
	public String lesson(@PathVariable Long lesson_id, Model model){
		
		Lesson lesson = lessonService.findOne(lesson_id);
		System.out.println(lesson_id + " = id;====================== name = " + lesson.getName());
		List<Item> items = itemService.findByLesson(lesson);
		System.out.println("====================item size =" + items.size());
		model.addAttribute("items" , itemService.findByLesson(lesson));
		model.addAttribute("uploads" , uploadService.findByLesson(lesson));
		return "import-lesson";
	}
	
	

	
}
