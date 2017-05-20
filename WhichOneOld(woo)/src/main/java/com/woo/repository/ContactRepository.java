package com.woo.repository;

import org.springframework.data.repository.CrudRepository;

import com.woo.domain.Contact;

public interface ContactRepository extends CrudRepository<Contact, Long> {

}
