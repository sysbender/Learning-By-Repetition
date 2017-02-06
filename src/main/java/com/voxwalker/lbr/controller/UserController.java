package com.voxwalker.lbr.controller;

 

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.voxwalker.lbr.entity.Course;
import com.voxwalker.lbr.entity.User;
import com.voxwalker.lbr.repository.CourseRepository;
import com.voxwalker.lbr.service.CourseService;
import com.voxwalker.lbr.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private CourseService courseService;
	
	
	// bind form 
	@ModelAttribute("user")
	private User construct(){
		return new User();
	}
	
	
	@ModelAttribute("course")
	private Course constructCourse(){
		return new Course();
	}
	
	
	@RequestMapping("/users")
	public String users(Model model){
		model.addAttribute("users", userService.findAll());
		return "users";
		
	}
	
	@RequestMapping("/users/{id}")
	public String detail(Model model, @PathVariable Long id){ 
		//Annotation which indicates that a method parameter should be bound to a URI template variable
		model.addAttribute("user", userService.findOneWithCourses(id));
		
		return "user-detail";
	}
	
	@RequestMapping("/register")
	public String showRegister(){
		return "user-register";
	}
	
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String doRegister(@ModelAttribute("user") User user){
		userService.save(user);
		return "redirect:/register.html?success=true";
	}
	
	@RequestMapping("/account")
	public String account(Model model, Principal principal){
		String name = principal.getName();
		model.addAttribute("user",userService.findOneWithCourses(name));
		return "user-detail";
	}
	
	@RequestMapping("/import")
	public String imports(Model model, Principal principal){
		String name = principal.getName();
		model.addAttribute("user",userService.findOneWithCourses(name));
		return "import";
	}
	
	
	@RequestMapping(value="/import", method=RequestMethod.POST)
	public String doAddImport(@ModelAttribute("course") Course course, Principal principal){
		String name = principal.getName();
		courseService.save(course, name);
		return "redirect:/import.html";
		
	}
	
	@RequestMapping("/import/remove/{id}")
	public String removeImport(@PathVariable Long id){
		Course course =  courseService.findOne(id);
		courseService.delete(course);
		return "redirect:/import.html";
	}
	
	@RequestMapping("/users/remove/{id}")
	public String removeUser(@PathVariable Long id){
		userService.delete(id);
		return "redirect:/users.html";
	}
}
