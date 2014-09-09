package com.mlo450.se325.a01.staff;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.mlo450.se325.a01.person.Person;

/**
 * @author Michael Lo
 * Hibernate mapping bean. Each Person represents a row in the database. Constructors, getters and setters. No computation or other methods.
 * Set() methods return this, so multiple set() calls can be concatenated together.
 */
@Entity
@Table(name = "staff") 
@PrimaryKeyJoinColumn(name="person_id")
public class StaffMember extends Person { 

	private static final String _NO_POSITION = "No position specified.";
	
	@Column(name = "position")
	private String position;

	public StaffMember() {
		super();
		this.position = _NO_POSITION;
	}
	
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