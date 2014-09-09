package com.mlo450.se325.a01.customer;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.mlo450.se325.a01.book.Book;
import com.mlo450.se325.a01.person.Person;

/**
 * @author Michael Lo
 * Hibernate mapping bean. Each Person represents a row in the database. Constructors, getters and setters. No computation or other methods.
 * Set() methods return this, so multiple set() calls can be concatenated together.
 */
@Entity
@Table(name = "customer") 
public class Customer extends Person { 
	
	@Column(name = "address")
	private String address;

	@OneToMany
	@JoinColumn(name = "borrowed_id")
	private List<Book> borrowed;
	
	public Customer() {
	}
	
	public String getAddress() {
		return address;
	}
	
	public Customer setAddress(String address) {
		this.address = address;
		return this;
	}
} 