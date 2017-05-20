package com.woo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "QUESTION_ID", nullable = false, updatable = false)
	private long id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ITEMA_ID", nullable = false)
	private Item itemA;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ITEMB_ID", nullable = false)
	private Item itemB;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "QUESTIONSCORE_ID")
	private QuestionScore score;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CATEGORY_ID", nullable = false)
	private Category category;
	@Column(name = "LEVEL", nullable = false)
	private int level;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Item getItemA() {
		return itemA;
	}

	public void setItemA(Item itemA) {
		this.itemA = itemA;
	}

	public Item getItemB() {
		return itemB;
	}

	public void setItemB(Item itemB) {
		this.itemB = itemB;
	}

	public QuestionScore getScore() {
		return score;
	}

	public void setScore(QuestionScore score) {
		this.score = score;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", itemA=" + itemA.getId() + ", itemB=" + itemB.getId() + ", category=" + category.getId() + ", level=" + getLevel() + "]";
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
