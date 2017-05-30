package com.woo.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Access(AccessType.FIELD)
public class Ratio {
	
	public Ratio(int trueCount, int falseCount) {
		this.trueCount = trueCount;
		this.falseCount = falseCount;
	}
	
	public Ratio() {}
	
	@Column(name = "TRUECOUNT")
	private int trueCount;

	@Column(name = "FALSECOUNT")
	private int falseCount;

	public int getTrueCount() {
		return trueCount;
	}

	public void setTrueCount(int trueCount) {
		this.trueCount = trueCount;
	}

	public int getFalseCount() {
		return falseCount;
	}

	public void setFalseCount(int falseCount) {
		this.falseCount = falseCount;
	}

}
