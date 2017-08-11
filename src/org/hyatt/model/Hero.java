package org.hyatt.model;
// Generated 2017-8-10 18:56:01 by Hibernate Tools 5.2.3.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Hero generated by hbm2java
 */
@Entity
@Table(name = "Hero")
public class Hero implements java.io.Serializable {

	private long id;
	private String localized_name;
	private String name;

	public Hero() {
	}

	public Hero(long id) {
		this.id = id;
	}

	public Hero(long id, String localized_name, String name) {
		this.id = id;
		this.localized_name = localized_name;
		this.name = name;
	}

	@Id

	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

//	@Column(name = "localized_name")
//	public String getLocalizedName() {
//		return this.localizedName;
//	}
//
//	public void setLocalizedName(String localizedName) {
//		this.localizedName = localizedName;
//	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "localized_name")
	public String getLocalized_name() {
		return localized_name;
	}

	public void setLocalized_name(String localized_name) {
		this.localized_name = localized_name;
	}
	

}
