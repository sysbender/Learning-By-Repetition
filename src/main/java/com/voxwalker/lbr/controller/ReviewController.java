package com.voxwalker.lbr.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.voxwalker.lbr.entity.User;
import com.voxwalker.lbr.entity.Word;
import com.voxwalker.lbr.service.UserService;
import com.voxwalker.lbr.service.WordService;

@Controller
public class ReviewController {
	
	@Autowired
	private WordService wordService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/review/{page_number}")
	public String review( @PathVariable int page_number , Model model, HttpServletRequest request, Principal principal){
		
		User user = userService.findByName(principal.getName());
		// get lang
		String lang = (String)request.getSession().getAttribute("target_lang");
		
		
		// search all unknown words sorted desc by review date 
		Page<Word>  page= wordService.getPage(page_number); 
		int current = page.getNumber() + 1;
		int begin = Math.max(1,  current -5 );
		int end = Math.min(begin +10,page.getTotalPages());
		
		model.addAttribute("wordPage", page);
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);

		// show pagination,  10 words per page
		return "review";
	}
}
