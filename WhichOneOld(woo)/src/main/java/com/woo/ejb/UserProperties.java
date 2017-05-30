package com.woo.ejb;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Component
public class UserProperties {

	private String username = "demo";
	private long userId = -1;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setUserId(long id) {
		this.userId = id;
	}

	public long getUserId() {
		return userId;
	}

}
