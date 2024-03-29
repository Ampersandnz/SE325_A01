package com.mlo450.se325.a01.customer;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.mlo450.se325.a01.book.Book;
import com.mlo450.se325.a01.person.Person;

/**
 * @author Michael Lo
 * Hibernate mapping bean. Each Person represents a row in the database. Constructors, getters and setters. No computation or other methods.
 * Set() methods return this, so multiple set() calls can be concatenated together.
 */
@Entity
@Table(name = "customer")
@PrimaryKeyJoinColumn(name="person_id")
public class Customer extends Person { 
	
	private static final String _NO_ADDRESS = "No address listed.";
	
	@Column(name = "address")
	private String address;
	
    @OneToMany(mappedBy = "owner")
	@Cascade(CascadeType.ALL)
    private List<Book> borrowed;

	public Customer() {
		super();
		this.address = _NO_ADDRESS;
	}
	
	public Customer(String name, String email, String address) {
		super(name, email);
		if (address == null || address.equals("")) { address = _NO_ADDRESS; }
		this.address = address;
	}

	public String getAddress() {
		return address;
	}
	
	public Customer setAddress(String address) {
		this.address = address;
		return this;
	}
    
    public List<Book> getBorrowed() {
        return borrowed;
    }

    public Customer setBorrowed(List<Book> borrowed) {
        this.borrowed = borrowed;
        return this;
    }
} 