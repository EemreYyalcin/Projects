package com.woo.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Statistic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "STATISTIC_ID", nullable = false, updatable = false)
	private long id;

	public Statistic() {
	}
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "statistic")
	private List<CategoryScore> categoryScoreList;

	@OneToOne(fetch = FetchType.EAGER, mappedBy = "statistic")
	private Contact contact;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<CategoryScore> getCategoryScoreList() {
		return categoryScoreList;
	}

	public void setCategoryList(List<CategoryScore> categoryScoreList) {
		this.categoryScoreList = categoryScoreList;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

}
