package com.woo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woo.domain.Contact;
import com.woo.repository.ContactRepository;
import com.woo.service.types.ContactService;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository contactRepository;

	@Override
	public Contact getContactByEmail(String email) {
		return contactRepository.findByEmail(email);
	}

	@Override
	public Contact getContactById(long id) {
		return contactRepository.findOne(id);
	}

	@Override
	public Contact addContact(Contact contact) {
		try {
			return contactRepository.save(contact);
		}
		catch (Exception e) {
			return null;
		}
	}

}
