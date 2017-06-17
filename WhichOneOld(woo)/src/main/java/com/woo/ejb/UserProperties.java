package com.woo.ejb;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.woo.core.attributes.Codes;

@SessionScope
@Component
public class UserProperties implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id = Codes.errorIntCode;

	private String email = null;

	private String name = null;

	private String surname = null;
	
	private String token = null;

	public long getId() {
		return id;
	}

	public UserProperties setId(long id) {
		this.id = id;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public UserProperties setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getName() {
		return name;
	}

	public UserProperties setName(String name) {
		this.name = name;
		return this;
	}

	public String getSurname() {
		return surname;
	}

	public UserProperties setSurname(String surname) {
		this.surname = surname;
		return this;
	}

	public void resetValues() {
		id = Codes.errorIntCode;
		email = null;
		name = null;
		surname = null;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
