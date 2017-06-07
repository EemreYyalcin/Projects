package com.woo.model;

import org.hibernate.validator.constraints.NotEmpty;

import com.woo.domain.Contact;

public class ContactModel{

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

	public Contact getContact() {
		Contact contact = new Contact();
		contact.setEmail(email).setPassword(password).setName(name).setSurname(surname);
		return contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

}
