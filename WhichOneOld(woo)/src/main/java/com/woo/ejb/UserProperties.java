package com.woo.ejb;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.woo.domain.Contact;

@SessionScope
@Component
public class UserProperties {

	private Contact contact;

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

}
