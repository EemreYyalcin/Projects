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

import com.woo.core.attributes.Link;
import com.woo.domain.Contact;
import com.woo.domain.Statistic;
import com.woo.ejb.UserProperties;
import com.woo.model.ContactModel;
import com.woo.service.impl.ContactServiceImpl;
import com.woo.service.impl.StatisticServiceImpl;
import com.woo.validator.RegisterValidation;

@Controller
@RequestMapping(value = "/woo/register")
public class RegisterPageController {

	private ContactServiceImpl contactService;

	private RegisterValidation registerValidation;

	private StatisticServiceImpl statisticService;

	private UserProperties userProperties;

	@Autowired
	public RegisterPageController(ContactServiceImpl contactService, RegisterValidation registerValidation, StatisticServiceImpl statisticService, UserProperties userProperties) {
		this.contactService = contactService;
		this.registerValidation = registerValidation;
		this.statisticService = statisticService;
		this.userProperties = userProperties;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(registerValidation);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getRegisterPage() {
		ModelAndView view = new ModelAndView("register", "contactModel", new ContactModel());
		view.addObject("login", Link.login);
		view.addObject("register", Link.register);
		return view;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String handleRegisterForm(@Valid @ModelAttribute("contactModel") ContactModel contactModel, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "register";
		}
		Contact contactX = contactModel.getContact();
		contactX.setStatistic(statisticService.addStatistic(new Statistic()));
		Contact contactSaved = contactService.addContact(contactX);
		if (contactSaved == null) {
			model.addAttribute("errorRegister", "Contact is not being added!");
			return "register";
		}

		userProperties.setId(contactSaved.getId()).setEmail(contactSaved.getEmail()).setName(contactSaved.getName()).setSurname(contactSaved.getSurname());
		return "redirect:" + Link.profile;
	}

}
