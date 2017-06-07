package com.woo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.woo.core.attributes.Link;

@Controller
public class HomeController {

	// testtt
	@RequestMapping("/")
	public String getHomePage() {
		// return "redirect:/woo/categories";
		// return "redirect:/woo/questionRandom/1/5";
		// return "index2";
		return "redirect:" + Link.login;
	}

}
