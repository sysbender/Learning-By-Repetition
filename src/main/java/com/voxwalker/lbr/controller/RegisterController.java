package com.voxwalker.lbr.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.voxwalker.lbr.entity.User;
import com.voxwalker.lbr.service.UserService;

@Controller
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private UserService userService;
	

	// bind form
	@ModelAttribute("user")
	private User construct() {
		return new User();
	}

	
	// show user registration form
	@RequestMapping
	public String showRegister() {
		return "user-register";
	}

	// register user
	@RequestMapping( method = RequestMethod.POST)
	public String doRegister(@Valid @ModelAttribute("user") User user,
			BindingResult result) {
		if (result.hasErrors()) {
			return "user-register";
		}
		userService.save(user);
		return "redirect:/register.html?success=true";
	}
	
	
	// check if username is available
	@RequestMapping("/available")
	@ResponseBody
	public String available(@RequestParam String username){
		Boolean available= userService.findOne(username) == null ;
		return available.toString();
		
	}
}
