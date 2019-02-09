package com.woo.domain;

import java.util.Date;

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
public class CategoryScore {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CATEGORYSCORE_ID", nullable = false, updatable = false)
	private long id;

	public CategoryScore() {
		// TODO Auto-generated constructor stub
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CATEGORY_ID", nullable = false)
	private Category category;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SCORE_ID", nullable = false)
	private Score score;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "STATISTIC_ID", nullable = false)
	private Statistic statistic;

	@Column(name = "LASTUPDATEDATE")
	private Date lastUpdateDate;

	@Column(name = "VERYEASYPAGEID", nullable = false, columnDefinition = "int default 0")
	private int veryEasyPageId = 0;

	@Column(name = "EASYPAGEID", nullable = false, columnDefinition = "int default 0")
	private int easyPageId = 0;

	@Column(name = "MEDIUMPAGEID", nullable = false, columnDefinition = "int default 0")
	private int mediumPageId = 0;

	@Column(name = "HARDPAGEID", nullable = false, columnDefinition = "int default 0")
	private int hardPageId = 0;

	@Column(name = "VERYHARDPAGEID", nullable = false, columnDefinition = "int default 0")
	private int veryHardPageId = 0;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public Statistic getStatistic() {
		return statistic;
	}

	public void setStatistic(Statistic statistic) {
		this.statistic = statistic;
	}

	public int getVeryEasyPageId() {
		return veryEasyPageId;
	}

	public void setVeryEasyPageId(int veryEasyPageId) {
		this.veryEasyPageId = veryEasyPageId;
	}

	public int getEasyPageId() {
		return easyPageId;
	}

	public void setEasyPageId(int easyPageId) {
		this.easyPageId = easyPageId;
	}

	public int getMediumPageId() {
		return mediumPageId;
	}

	public void setMediumPageId(int mediumPageId) {
		this.mediumPageId = mediumPageId;
	}

	public int getHardPageId() {
		return hardPageId;
	}

	public void setHardPageId(int hardPageId) {
		this.hardPageId = hardPageId;
	}

	public int getVeryHardPageId() {
		return veryHardPageId;
	}

	public void setVeryHardPageId(int veryHardPageId) {
		this.veryHardPageId = veryHardPageId;
	}

	public void increasePageId(int level) {

		switch (level) {
		case 1:
			veryEasyPageId++;
			break;
		case 2:
			easyPageId++;
			break;
		case 3:
			mediumPageId++;
			break;
		case 4:
			hardPageId++;
			break;
		case 5:
			veryHardPageId++;
			break;
		}
	}
}
