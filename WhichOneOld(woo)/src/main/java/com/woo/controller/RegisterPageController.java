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

import com.woo.model.ContactModel;
import com.woo.service.impl.ContactServiceImpl;
import com.woo.validator.RegisterValidation;

@Controller
public class RegisterPageController {

	@Autowired
	private ContactServiceImpl contactService;
	@Autowired
	private RegisterValidation registerValidation;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(registerValidation);
	}

	@RequestMapping(value = "/woo/register", method = RequestMethod.GET)
	public ModelAndView getRegisterPage() {
		return new ModelAndView("register", "contactModel", new ContactModel());
	}

	@RequestMapping(value = "/woo/register", method = RequestMethod.POST)
	public String handleRegisterForm(@Valid @ModelAttribute("contactModel") ContactModel contact,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "register";
		}
		boolean result = contactService.addContact(contact.getContact());
		if (!result) {
			model.addAttribute("errorRegister", "Contact is not being added!");
			return "register";
		}
		return "redirect:/woo/login";
	}

}
