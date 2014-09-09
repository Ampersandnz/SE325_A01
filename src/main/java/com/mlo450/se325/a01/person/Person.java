package com.mlo450.se325.a01.person;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;


/**
 * @author Michael Lo
 * Hibernate mapping bean. Each User represents a row in the database. Constructors, getters and setters. No computation or other methods.
 * Set() methods return this, so multiple set() calls can be concatenated together.
 */
@Entity
@Table(name = "person") 
@Inheritance(strategy=InheritanceType.JOINED)
public class Person {

	private static final String _NO_NAME = "No name specified.";
	
	@Id
	@GeneratedValue
	@Column(name = "person_id", unique = true, nullable = false) 
	private Long id; 
	
	@Column(name = "name", nullable = false, length = 50)
	private String name; 
	
	@Column(name = "email", nullable = true, length = 50) 
	private String email; 

	public Person() {
		this.name = _NO_NAME;
	}
	
	public Person(String name) {
		this();
	}

	public Person(String name, String email) {
		this(name);
		this.name = name;
		this.email = email;
	}
	
	public Person(Long id, String name, String email) {
		this(name, email);
		this.id = id;
	}

	public Long getId() { 
		return id; 
	} 

	public Person setId(Long id) { 
		this.id = id;
		return this;
	} 

	public String getName() { 
		return name; 
	} 

	public Person setName( String name ) { 
		this.name = name;
		return this;
	} 

	public String getEmail() { 
		return email; 
	} 

	public Person setEmail( String email ) { 
		this.email = email;
		return this;
	} 
	
	public String toString() {
		return this.name + " (" + this.email + ")";
	}
	
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		} else {
		
			if (!this.getClass().equals(other.getClass())) { 
				return false;
			} else {
		
				Person person2 = (Person) other;
				if ((this.id == person2.getId()) && (this.name.equals(person2.getName()) && (this.email.equals(person2.getEmail())))) {
					return true;
				} else {
					
					return false;
				}
			}
		}
	}
	
	public int hashCode() {
		int temp = 0;
		temp = ( id + name + email ).hashCode();
		return temp;
	}
} 
