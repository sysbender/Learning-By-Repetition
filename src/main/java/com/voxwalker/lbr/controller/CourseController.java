package com.voxwalker.lbr.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	// init lesson bean for lessonForm
	@ModelAttribute("lesson")
	private Lesson constructLesson(HttpServletRequest request) {	
		return new Lesson();
	}
	
	

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

	@RequestMapping(value = "/import/course/{course_id}", method = RequestMethod.POST)
	public String doAddImport(@PathVariable Long course_id, Model model,
			@Valid @ModelAttribute("lesson") Lesson lesson,
			Principal principal, BindingResult result) {
		if (result.hasErrors()) {
			// toDo : errors
		}
		System.out.println("=========== add new lesson ==================: " +  lesson.getName());
		String username = principal.getName();
		lessonService.save(lesson, course_id, username);
		// refresh lesson list of the course
		return "redirect:/import/course/"+ course_id+".html";

	}
	
}
