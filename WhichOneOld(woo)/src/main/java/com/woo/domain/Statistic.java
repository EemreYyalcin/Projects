package com.woo.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Statistic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "STATISTIC_ID", nullable = false, updatable = false)
	private long id;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "STATISTIC_CATEGORYSCORE", joinColumns = @JoinColumn(name = "STATISTIC_ID", referencedColumnName = "STATISTIC_ID"), inverseJoinColumns = @JoinColumn(name = "CATEGORYSCORE_ID", referencedColumnName = "CATEGORYSCORE_ID"))
	private List<CategoryScore> categoryScoreList;
	@OneToOne
	@JoinColumn(name = "CONTACT_ID", nullable = false)
	private Contact contact;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<CategoryScore> getCategoryList() {
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
