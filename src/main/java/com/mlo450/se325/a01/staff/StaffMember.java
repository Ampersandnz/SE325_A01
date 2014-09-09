package com.mlo450.se325.a01.staff;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.mlo450.se325.a01.person.Person;

/**
 * @author Michael Lo
 * Hibernate mapping bean. Each Person represents a row in the database. Constructors, getters and setters. No computation or other methods.
 * Set() methods return this, so multiple set() calls can be concatenated together.
 */
@Entity
@Table(name = "staff") 
public class StaffMember extends Person { 
	
	@Column(name = "position")
	private String position;
	
	public StaffMember(String name, String email, String position) {
		super(name, email);
		this.position = position;
	}
	
	public String getPosition() {
		return position;
	}
	
	public StaffMember setPosition(String position) {
		this.position = position;
		return this;
	}
}