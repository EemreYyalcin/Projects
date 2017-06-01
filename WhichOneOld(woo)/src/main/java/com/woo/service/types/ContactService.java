package com.woo.service.types;

import com.woo.domain.Contact;

public interface ContactService {

	Contact getContactByEmail(String email);

	Contact addContact(Contact contact);

}
