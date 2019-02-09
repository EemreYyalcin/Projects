package com.woo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

@Entity
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CONTACT_ID", nullable = false, updatable = false)
	private long id;

	@Column(name = "EMAIL", nullable = false, unique = true)
	private String email;

	@Column(name = "PASSWORD", nullable = false)
	@Lob
	private String password;

	@Column(name = "NAME")
	private String name;

	@Column(name = "SURNAME")
	private String surname;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "STATISTIC_ID")
	private Statistic statistic;

	public long getId() {
		return id;
	}

	public Contact setId(long id) {
		this.id = id;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public Contact setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public Contact setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getName() {
		return name;
	}

	public Contact setName(String name) {
		this.name = name;
		return this;
	}

	public String getSurname() {
		return surname;
	}

	public Contact setSurname(String surname) {
		this.surname = surname;
		return this;
	}

	public Statistic getStatistic() {
		return statistic;
	}

	public void setStatistic(Statistic statistic) {
		this.statistic = statistic;
	}

}
