package com.woo.domain;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CATEGORY_ID", nullable = false, updatable = false)
	private long id;
	@Column(name = "NAME", nullable = false, updatable = false)
	private String name;
	@Column(name = "LASTUPDATEDATE")
	private Date lastUpdateDate;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	private Set<Question> questions;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	private Set<Item> items;
	@Column(name = "DECADE", nullable = false)
	private int decade;
	@Column(name = "MAPCOUNTITEM")
	private int mapCountItem;
	@Column(name = "MAPCOUNTQUESTION")
	private int mapCountQuestion;

	public Category(String name) {
		super();
		this.name = name;
		this.lastUpdateDate = Calendar.getInstance().getTime();
	}

	public Category(String name, int decade) {
		super();
		this.name = name;
		this.lastUpdateDate = Calendar.getInstance().getTime();
		this.setDecade(decade);
		this.mapCountItem = 1;
		this.mapCountQuestion = 1;

	}

	public Category() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

	public int getDecade() {
		return decade;
	}

	public void setDecade(int decade) {
		this.decade = decade;
	}

	public int getMapCountItem() {
		return mapCountItem;
	}

	public void setMapCountItem(int mapCountItem) {
		this.mapCountItem = mapCountItem;
	}

	public int getMapCountQuestion() {
		return mapCountQuestion;
	}

	public void setMapCountQuestion(int mapCountQuestion) {
		this.mapCountQuestion = mapCountQuestion;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name +", decade=" + decade + ", mapCountItem=" + mapCountItem + ", mapCountQuestion=" + mapCountQuestion + "]";
	}

}
