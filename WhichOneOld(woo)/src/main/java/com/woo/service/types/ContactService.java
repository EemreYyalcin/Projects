package com.woo.service.types;

import com.woo.domain.Contact;

public interface ContactService {

	Contact getContactByEmail(String email);

	boolean addContact(Contact contact);

}
