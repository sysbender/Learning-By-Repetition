package com.voxwalker.lbr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.voxwalker.lbr.entity.Course;
import com.voxwalker.lbr.entity.Lesson;
import com.voxwalker.lbr.service.CourseService;
import com.voxwalker.lbr.service.LessonService;

@Controller
public class CourseController {
	
	@Autowired 
	private LessonService lessonService;
	
	@Autowired 
	private CourseService courseService;
	
 

	@RequestMapping("/import/course/{course_id}")
	public String course(@PathVariable Long course_id, Model model){
		
		// toDo: change repository to service
		Course course = courseService.findOne(course_id);
		model.addAttribute("lessons",lessonService.findByCourse(course));
		return "import-course";
	}
	
	
	@RequestMapping("/import/lesson/remove/{id}")
	public String removeLesson(@PathVariable Long id) {
		
		Lesson lesson = lessonService.findOne(id);
		long courseId= (lesson.getCourse()).getId();  // in transaction, not lazy
		lessonService.delete(lesson);
		return "redirect:/import/course/"+ courseId +".html";
	}

	
	
}
