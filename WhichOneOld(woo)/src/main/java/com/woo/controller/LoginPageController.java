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
import com.woo.ejb.UserProperties;
import com.woo.model.ContactModel;
import com.woo.model.ProfileModel;
import com.woo.service.impl.ContactServiceImpl;
import com.woo.utils.log.LogMessage;
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
		if (binder.getTarget() instanceof ContactModel) {
			binder.addValidators(loginValidation);
		}
	}

	@RequestMapping(value = "/woo/login", method = RequestMethod.POST)
	public String getLogin(@Valid @ModelAttribute("contact") Contact contact, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "login";
		}
		Contact contactX = contactService.getContactByEmail(contact.getEmail());
		if (contactX == null) {
			LogMessage.error("Contact is not found in DB!!");
			return "login";
		}
		userProperties.setId(contactX.getId()).setEmail(contactX.getEmail()).setName(contactX.getName()).setSurname(contactX.getSurname());
		return "redirect:" + Link.profile;
	}

	@RequestMapping(value = "/woo/login", method = RequestMethod.GET)
	public ModelAndView getLoginPage() {
		ModelAndView view = new ModelAndView("login", "contact", new Contact());
		view.addObject("profile", ProfileModel.getBasicProfileModel(userProperties));
		return view;
	}

}
