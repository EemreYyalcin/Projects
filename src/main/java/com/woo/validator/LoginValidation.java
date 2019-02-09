package com.woo.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.woo.domain.Contact;
import com.woo.service.impl.ContactServiceImpl;
import com.woo.utils.log.LogMessage;

@Component
public class LoginValidation implements Validator {

	private ContactServiceImpl contactService;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

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

		if (!encoder.matches(contact.getEmail() + contact.getPassword(), contactDb.getPassword())) {
			errors.reject("email", "Username or Password Fail");
			LogMessage.logx(contact.getPassword());
			LogMessage.logx(contactDb.getPassword());
		}
	}

}
