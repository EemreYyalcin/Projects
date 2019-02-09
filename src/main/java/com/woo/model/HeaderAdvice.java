package com.woo.model;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class HeaderAdvice {

	public void addHeaderAttribute(Model model) {
		model.addAttribute("homeUrl", "/");
		model.addAttribute("categories", "/woo/categories");
	}

}
