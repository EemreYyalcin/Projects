package com.woo.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.woo.model.ContactModel;
import com.woo.service.impl.ContactServiceImpl;

@Component
public class RegisterValidation implements Validator {

	private ContactServiceImpl contactService;

	@Autowired
	public RegisterValidation(ContactServiceImpl contactService) {
		this.contactService = contactService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(ContactModel.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ContactModel contactModel = (ContactModel) target;
		validateContact(errors, contactModel);

	}

	private void validateContact(Errors errors, ContactModel contactModel) {

		if (!contactModel.getEmail().contains("@")) {
			errors.reject("email", "Email can not use");
		}

		if (!contactModel.getPassword().equals(contactModel.getConfirmPassword())) {
			errors.reject("password", "Passwords not equal !");
		}

		if (contactService.getContactByEmail(contactModel.getEmail()) != null) {
			errors.reject("emailX", "User with usernamealready exists");
		}

	}

}
