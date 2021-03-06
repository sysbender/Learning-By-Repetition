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
import com.voxwalker.lbr.entity.User;
import com.voxwalker.lbr.model.UserSummary;
import com.voxwalker.lbr.service.CourseService;
import com.voxwalker.lbr.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private CourseService courseService;

	@ModelAttribute("course")
	private Course constructCourse(HttpServletRequest request) {
		Course course = new Course();
		course.setLang((String)request.getSession().getAttribute("target_lang"));
		return course;
	}


	
	
	@RequestMapping("/account")
	public String account(Model model, Principal principal) {	
		
		String name = principal.getName();
		User user = userService.findByName(name);
		//
		UserSummary enSummary = userService.getUserSummary(user, "en");
		UserSummary frSummary = userService.getUserSummary(user, "fr");
		
		model.addAttribute("enSummary", enSummary);
		model.addAttribute("frSummary", frSummary);	
		
		return "account";
	}

	@RequestMapping("/import")
	public String imports(Model model, Principal principal) {
		String name = principal.getName();
		//model.addAttribute("user", userService.findOneWithCourses(name));
		model.addAttribute("user", userService.findOneWithCourses(name));
		return "import";
	}

	@RequestMapping(value = "/import", method = RequestMethod.POST)
	public String doAddImport(Model model,
			@Valid @ModelAttribute("course") Course course,
			Principal principal, BindingResult result) {
		if (result.hasErrors()) {
			return imports(model, principal);
		}
		String name = principal.getName();
		courseService.save(course, name);
		return "redirect:/import.html";

	}

	@RequestMapping("/import/remove/{id}")
	public String removeImport(@PathVariable Long id) {
		Course course = courseService.findOne(id);
		courseService.delete(course);
		return "redirect:/import.html";
	}


}
