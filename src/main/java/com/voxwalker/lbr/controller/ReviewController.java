package com.voxwalker.lbr.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.voxwalker.lbr.entity.User;
import com.voxwalker.lbr.entity.Word;
import com.voxwalker.lbr.repository.WordRepository;
import com.voxwalker.lbr.service.UserService;
import com.voxwalker.lbr.service.WordService;

@Controller
public class ReviewController {
	
	@Autowired
	private WordRepository wordRepository;
	
	@Autowired
	private WordService wordService;
	
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/review/{page_number}")
	public String review( @PathVariable int page_number , Model model, HttpServletRequest request, Principal principal){
		
		
		List<Word> allwords = wordRepository.findAll();
		for(Word w : allwords){
			System.out.println(w.getUser().getName() + w.getLang()+w.getState()+" ############## : " + w.getTxt() );
		}
		
		
		
		User user = userService.findByName(principal.getName());
		// get lang
		String lang = (String)request.getSession().getAttribute("target_lang");
		
		
		// search all unknown words sorted desc by review date 
		Set<String> wordSet = wordService.findByUserAndLangAndState(user, lang, 1);
		System.out.println("user="+ user.getName() + "lang = " +lang +" unknown : " + Arrays.toString(wordSet.toArray()));
		Page<Word>  page= wordService.getPage(user, lang, 1 , page_number); 
		System.out.println("total in page: " + page.getTotalElements());
		
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
