package com.woo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ITEM_ID", nullable = false, updatable = false)
	private long id;
	@Column(name = "FILENAME", nullable = false, updatable = false, unique = true)
	private String filename;
	@Lob
	@Column(name = "CONTENT", nullable = false)
	private byte[] content;
	@Column(name = "YEAR", nullable = false)
	private int year;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CATEGORY_ID", nullable = false)
	private Category category;
	@Column(name = "MAPCOUNT")
	private int mapCount;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getMapCount() {
		return mapCount;
	}

	public void setMapCount(int mapCount) {
		this.mapCount = mapCount;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", filename=" + filename + ", year=" + year + ", category=" + category + ", mapCount=" + mapCount + "]";
	}

	
}
