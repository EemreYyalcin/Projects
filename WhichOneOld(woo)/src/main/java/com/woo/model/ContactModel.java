package com.woo.model;

import org.hibernate.validator.constraints.NotEmpty;

import com.woo.domain.Contact;

public class ContactModel {

	@NotEmpty
	private String email;

	@NotEmpty
	private String password;

	@NotEmpty
	private String confirmPassword;

	@NotEmpty
	private String name;

	@NotEmpty
	private String surname;
	

	public ContactModel() {
	}

	public ContactModel(String email, String name, String surname) {
		setEmail(email).setName(name).setSurname(surname);
	}

	public Contact getContact() {
		Contact contact = new Contact();
		contact.setEmail(email).setPassword(password).setName(name).setSurname(surname);
		return contact;
	}

	public String getEmail() {
		return email;
	}

	public ContactModel setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public ContactModel setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public ContactModel setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
		return this;
	}

	public String getName() {
		return name;
	}

	public ContactModel setName(String name) {
		this.name = name;
		return this;
	}

	public String getSurname() {
		return surname;
	}

	public ContactModel setSurname(String surname) {
		this.surname = surname;
		return this;
	}


}
