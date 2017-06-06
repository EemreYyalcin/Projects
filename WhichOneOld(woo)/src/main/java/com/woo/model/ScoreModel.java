package com.woo.model;

import com.woo.domain.Score;

public class ScoreModel {

	private RatioModel veryHardRatio;

	private RatioModel hardRatio;

	private RatioModel mediumRatio;

	private RatioModel easyRatio;

	private RatioModel veryEasyRatio;

	public ScoreModel() {
	}

	public ScoreModel(Score score) {
		if (score == null) {
			this.veryHardRatio = new RatioModel(1, 1);
			this.hardRatio = new RatioModel(1, 1);
			this.mediumRatio = new RatioModel(1, 1);
			this.easyRatio = new RatioModel(1, 1);
			this.veryEasyRatio = new RatioModel(1, 1);
			return;
		}
		this.veryHardRatio = new RatioModel(score.getVeryHardScore());
		this.hardRatio = new RatioModel(score.getHardScore());
		this.mediumRatio = new RatioModel(score.getMediumScore());
		this.easyRatio = new RatioModel(score.getEasyScore());
		this.veryEasyRatio = new RatioModel(score.getVeryEasyScore());
	}

	public RatioModel getVeryHardRatio() {
		return veryHardRatio;
	}

	public ScoreModel setVeryHardRatio(RatioModel veryHardRatio) {
		this.veryHardRatio = veryHardRatio;
		return this;
	}

	public RatioModel getHardRatio() {
		return hardRatio;
	}

	public ScoreModel setHardRatio(RatioModel hardRatio) {
		this.hardRatio = hardRatio;
		return this;
	}

	public RatioModel getMediumRatio() {
		return mediumRatio;
	}

	public ScoreModel setMediumRatio(RatioModel mediumRatio) {
		this.mediumRatio = mediumRatio;
		return this;
	}

	public RatioModel getEasyRatio() {
		return easyRatio;
	}

	public ScoreModel setEasyRatio(RatioModel easyRatio) {
		this.easyRatio = easyRatio;
		return this;
	}

	public RatioModel getVeryEasyRatio() {
		return veryEasyRatio;
	}

	public ScoreModel setVeryEasyRatio(RatioModel veryEasyRatio) {
		this.veryEasyRatio = veryEasyRatio;
		return this;
	}

	public int getTotalCount() {
		return veryHardRatio.getTotalCount() + hardRatio.getTotalCount() + mediumRatio.getTotalCount() + easyRatio.getTotalCount() + veryEasyRatio.getTotalCount();
	}

	public ScoreModel addScoreModel(ScoreModel score) {
		ScoreModel scoreModel = new ScoreModel();
		scoreModel.setVeryHardRatio(score.getVeryHardRatio().addRatioModel(getVeryHardRatio()));
		scoreModel.setHardRatio(score.getHardRatio().addRatioModel(getHardRatio()));
		scoreModel.setMediumRatio(score.getMediumRatio().addRatioModel(getMediumRatio()));
		scoreModel.setEasyRatio(score.getEasyRatio().addRatioModel(getEasyRatio()));
		scoreModel.setVeryEasyRatio(score.getVeryEasyRatio().addRatioModel(getVeryEasyRatio()));
		return scoreModel;
	}

	public static ScoreModel getScoreModel(Score score) {
		if (score == null) {
			return new ScoreModel(null);
		}
		ScoreModel scoreModel = new ScoreModel();
		scoreModel.setVeryHardRatio(RatioModel.getRatioModel(score.getVeryHardScore()));
		scoreModel.setHardRatio(RatioModel.getRatioModel(score.getHardScore()));
		scoreModel.setMediumRatio(RatioModel.getRatioModel(score.getMediumScore()));
		scoreModel.setEasyRatio(RatioModel.getRatioModel(score.getEasyScore()));
		scoreModel.setVeryEasyRatio(RatioModel.getRatioModel(score.getVeryEasyScore()));
		return scoreModel;
	}

}
