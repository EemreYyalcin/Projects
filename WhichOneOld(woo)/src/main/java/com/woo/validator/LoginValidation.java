package com.woo.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.woo.domain.Contact;
import com.woo.service.impl.ContactServiceImpl;

@Component
public class LoginValidation implements Validator {

	private ContactServiceImpl contactService;

	@Autowired
	public LoginValidation(ContactServiceImpl contactService) {
		this.contactService = contactService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(Contact.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Contact contact = (Contact) target;
		validateContact(errors, contact);
	}

	private void validateContact(Errors errors, Contact contact) {

		if (!contact.getEmail().contains("@")) {
			errors.reject("email", "Email is not valid");
			return;
		}
		Contact contactDb = contactService.getContactByEmail(contact.getEmail());
		if (contactDb == null) {
			errors.reject("email", "Username or Password Fail");
			return;
		}

		if (!contactDb.getPassword().equals(contact.getPassword())) {
			errors.reject("email", "Username or Password Fail");
		}
	}

}
