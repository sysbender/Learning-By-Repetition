package com.voxwalker.lbr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.voxwalker.lbr.entity.User;
import com.voxwalker.lbr.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	// bind form 
	@ModelAttribute("user")
	private User construct(){
		return new User();
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
		return "user-register";
	}
}
