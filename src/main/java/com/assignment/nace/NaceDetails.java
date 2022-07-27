package com.assignment.nace;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "nacedetails")
public class NaceDetails {
	public NaceDetails() {
	}

	@Id
	@Column(name = "order1")
	// This column name is changed to "order1" since order was a keyword
	private int order1;
	@Column(name = "level")
	private int level;
	@Lob
	private String code;
	@Lob
	private String parent;
	@Lob
	private String description;
	@Lob
	private String itemincludes;
	@Lob
	private String itemalsoincludes;
	@Lob
	private String rulings;
	@Lob
	private String itemexcludes;

	public NaceDetails(int order1, int level, String code, String parent, String description, String itemincludes,
			String itemalsoincludes, String rulings, String itemexcludes, String reference) {
		super();
		this.order1 = order1;
		this.level = level;
		this.code = code;
		this.parent = parent;
		this.description = description;
		this.itemincludes = itemincludes;
		this.itemalsoincludes = itemalsoincludes;
		this.rulings = rulings;
		this.itemexcludes = itemexcludes;
		this.reference = reference;
	}

	public int getOrder1() {
		return order1;
	}

	public void setOrder1(int order1) {
		this.order1 = order1;
	}

	@Column(name = "reference")
	private String reference;

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getItemincludes() {
		return itemincludes;
	}

	public void setItemincludes(String itemincludes) {
		this.itemincludes = itemincludes;
	}

	public String getItemalsoincludes() {
		return itemalsoincludes;
	}

	public void setItemalsoincludes(String itemalsoincludes) {
		this.itemalsoincludes = itemalsoincludes;
	}

	public String getRulings() {
		return rulings;
	}

	public void setRulings(String rulings) {
		this.rulings = rulings;
	}

	public String getItemexcludes() {
		return itemexcludes;
	}

	public void setItemexcludes(String itemexcludes) {
		this.itemexcludes = itemexcludes;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

}
