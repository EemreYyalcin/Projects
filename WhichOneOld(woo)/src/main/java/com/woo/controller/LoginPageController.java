package com.woo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.woo.domain.Contact;
import com.woo.ejb.UserProperties;
import com.woo.service.impl.ContactServiceImpl;
import com.woo.validator.LoginValidation;

@Controller
public class LoginPageController {

	@Autowired
	private LoginValidation loginValidation;

	@Autowired
	private UserProperties userProperties;

	@Autowired
	private ContactServiceImpl contactService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(loginValidation);
	}

	@RequestMapping(value = "/woo/login", method = RequestMethod.POST)
	public String getLogin(@Valid @ModelAttribute("contact") Contact contact, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "login";
		}
		userProperties.setContact(contactService.getContactByEmail(contact.getEmail()));

		return "redirect:/woo/categories";
	}

	@RequestMapping(value = "/woo/login", method = RequestMethod.GET)
	public ModelAndView getLoginPage() {
		return new ModelAndView("login", "contact", new Contact());
	}

}
