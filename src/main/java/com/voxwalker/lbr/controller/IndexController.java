package com.voxwalker.lbr.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@RequestMapping("/index/{target_lang}")
	public String targetLang(@PathVariable String target_lang,
			HttpServletRequest request) {
		
		if ("en".equals(target_lang) || "fr".equals(target_lang)) {
			request.getSession().setAttribute("target_lang", target_lang);
		}
		return "redirect:/index.html";

	}
}
