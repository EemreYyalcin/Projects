package com.woo.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Score {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SCORE_ID", nullable = false, updatable = false)
	private long id;

	public Score(boolean tempValue) {
		veryHardScore = new Ratio(0, 0);
		hardScore = new Ratio(0, 0);
		mediumScore = new Ratio(0, 0);
		easyScore = new Ratio(0, 0);
		veryEasyScore = new Ratio(0, 0);
	}

	public Score() {
	}

	@Embedded
	// rename the basic mappings
	@AttributeOverrides({ @AttributeOverride(name = "trueCount", column = @Column(name = "VERYHARD_TRUECOUNT")), @AttributeOverride(name = "falseCount", column = @Column(name = "VERYHARD_FALSECOUNT")) })
	private Ratio veryHardScore;

	@Embedded
	// rename the basic mappings
	@AttributeOverrides({ @AttributeOverride(name = "trueCount", column = @Column(name = "HARD_TRUECOUNT")), @AttributeOverride(name = "falseCount", column = @Column(name = "HARD_FALSECOUNT")) })
	private Ratio hardScore;

	@Embedded
	// rename the basic mappings
	@AttributeOverrides({ @AttributeOverride(name = "trueCount", column = @Column(name = "MEDIUM_TRUECOUNT")), @AttributeOverride(name = "falseCount", column = @Column(name = "MEDIUM_FALSECOUNT")) })
	private Ratio mediumScore;

	@Embedded
	// rename the basic mappings
	@AttributeOverrides({ @AttributeOverride(name = "trueCount", column = @Column(name = "EASY_TRUECOUNT")), @AttributeOverride(name = "falseCount", column = @Column(name = "EASY_FALSECOUNT")) })
	private Ratio easyScore;

	@Embedded
	// rename the basic mappings
	@AttributeOverrides({ @AttributeOverride(name = "trueCount", column = @Column(name = "VERYEASY_TRUECOUNT")), @AttributeOverride(name = "falseCount", column = @Column(name = "VERYEASY_FALSECOUNT")) })
	private Ratio veryEasyScore;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Ratio getVeryHardScore() {
		return veryHardScore;
	}

	public void setVeryHardScore(Ratio veryHardScore) {
		this.veryHardScore = veryHardScore;
	}

	public Ratio getHardScore() {
		return hardScore;
	}

	public void setHardScore(Ratio hardScore) {
		this.hardScore = hardScore;
	}

	public Ratio getMediumScore() {
		return mediumScore;
	}

	public void setMediumScore(Ratio mediumScore) {
		this.mediumScore = mediumScore;
	}

	public Ratio getEasyScore() {
		return easyScore;
	}

	public void setEasyScore(Ratio easyScore) {
		this.easyScore = easyScore;
	}

	public Ratio getVeryEasyScore() {
		return veryEasyScore;
	}

	public void setVeryEasyScore(Ratio veryEasyScore) {
		this.veryEasyScore = veryEasyScore;
	}

}
