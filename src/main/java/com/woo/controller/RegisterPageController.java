package com.woo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.woo.core.attributes.Codes;
import com.woo.core.attributes.Link;
import com.woo.domain.Contact;
import com.woo.domain.Statistic;
import com.woo.ejb.UserProperties;
import com.woo.model.ContactModel;
import com.woo.model.ProfileModel;
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

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Autowired
	public RegisterPageController(ContactServiceImpl contactService, RegisterValidation registerValidation, StatisticServiceImpl statisticService, UserProperties userProperties) {
		this.contactService = contactService;
		this.registerValidation = registerValidation;
		this.statisticService = statisticService;
		this.userProperties = userProperties;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		if (binder.getTarget() instanceof ContactModel) {
			binder.addValidators(registerValidation);
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getRegisterPage() {
		ModelAndView view = new ModelAndView("register", "contactModel", new ContactModel());
		view.addObject("profile", ProfileModel.getBasicProfileModel(userProperties));
		return view;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String handleRegisterForm(@Valid @ModelAttribute("contactModel") ContactModel contactModel, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("profile", ProfileModel.getBasicProfileModel(userProperties));
			return "register";
		}
		Contact contactX = contactModel.getContact();
		contactX.setStatistic(statisticService.addStatistic(new Statistic()));
		contactX.setPassword(encoder.encode(contactX.getEmail() + contactX.getPassword()));
		Contact contactSaved = contactService.addContact(contactX);
		if (contactSaved == null) {
			model.addAttribute("errorRegister", "Contact is not being added!");
			model.addAttribute("profile", ProfileModel.getBasicProfileModel(userProperties));
			return "register";
		}

		userProperties.setId(contactSaved.getId()).setEmail(contactSaved.getEmail()).setName(contactSaved.getName()).setSurname(contactSaved.getSurname());
		return "redirect:" + Link.profile;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView getReRegisterPage() {
		if (userProperties.getId() == Codes.errorIntCode) {
			ModelAndView view = new ModelAndView("login", "contact", new Contact());
			view.addObject("profile", ProfileModel.getBasicProfileModel(userProperties));
			return view;
		}
		ModelAndView view = new ModelAndView("reRegister");
		ProfileModel profile = ProfileModel.getBasicProfileModel(userProperties);
		ContactModel contactModel = new ContactModel(userProperties.getEmail(), userProperties.getName(), userProperties.getSurname());
		view.addObject("contactModel", contactModel);
		view.addObject("profile", profile);
		view.addObject("reregister", Link.editProfilePost);
		return view;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String getReRegisterPage(@Valid @ModelAttribute("contactModel") ContactModel contactModel, BindingResult bindingResult, Model model) {
		if (userProperties.getId() == Codes.errorIntCode) {
			return "redirect:" + Link.login;
		}
		if (bindingResult.hasErrors()) {
			boolean isError = true;
			List<ObjectError> errors = bindingResult.getAllErrors();
			if (errors.size() == 1) {
				String error = errors.get(0).getCode();
				if (error.equals("emailX")) {
					if (contactModel.getEmail().equals(userProperties.getEmail())) {
						isError = false;
					}
				}
			}
			if (isError) {
				model.addAttribute("profile", ProfileModel.getBasicProfileModel(userProperties));
				return "reRegister";
			}
		}
		Contact contactX = contactModel.getContact();
		contactX.setPassword(encoder.encode(contactX.getEmail() + contactX.getPassword()));
		contactX.setId(userProperties.getId());
		Contact contactSaved = contactService.addContact(contactX);
		model.addAttribute("profile", ProfileModel.getBasicProfileModel(userProperties));
		if (contactSaved == null) {
			model.addAttribute("errorRegister", "Contact is not being added!");
			return "reRegister";
		}
		userProperties.setId(contactSaved.getId()).setEmail(contactSaved.getEmail()).setName(contactSaved.getName()).setSurname(contactSaved.getSurname());
		return "redirect:" + Link.profile;
	}

}
