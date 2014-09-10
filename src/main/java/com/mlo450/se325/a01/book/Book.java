package com.mlo450.se325.a01.book;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.mlo450.se325.a01.customer.Customer;

/**
 * @author Michael Lo
 * Hibernate mapping bean. Each Book represents a row in the database.
 * set*() methods return this, so multiple set*() calls can be concatenated together.
 */
@Entity
@Table(name = "BOOK") 
public class Book { 
	private static final int _NO_ISBN = 0;
	private static final String _NO_TITLE = "No title listed.";
	private static final String _NO_AUTHOR = "No author listed.";
	private static final Customer _NO_OWNER = new Customer("Library", "library@theLibrary.com", "Library address.");
	
	@Id 
	@GeneratedValue
	@Column(name = "book_id") 
	private Long id;

	@Column(name = "isbn", nullable = false)
	private int isbn;

	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name = "author", nullable = false)
	private String author;

	@OneToOne
	@JoinColumn(name = "owner_Id", nullable = false)
	private Customer owner;

	public Book() {
		isbn = _NO_ISBN;
		title = _NO_TITLE;
		author = _NO_AUTHOR;
		owner = _NO_OWNER;
	}

	public Book(int isbn, String title, String author, Customer owner) {
		if (isbn == 0) { isbn = _NO_ISBN; }
		if (title == null) { title = _NO_TITLE; }
		if (author == null) { author = _NO_AUTHOR; }
		if (owner == null) { owner = _NO_OWNER; }
		
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.owner = owner;
	}

	public Long getId() { 
		return id; 
	} 

	public Book setId( Long id ) { 
		this.id = id;
		return this;
	} 

	public int getIsbn() { 
		return isbn; 
	} 

	public Book setIsbn(int isbn) { 
		this.isbn = isbn;
		return this;
	} 

	public String getTitle() { 
		return title; 
	} 

	public Book setTitle( String title ) { 
		this.title = title;
		return this;
	} 

	public String getAuthor() { 
		return author; 
	} 

	public Book setAuthor(String author) { 
		this.author = author;
		return this;
	} 

	public Customer getOwner() { 
		return owner; 
	} 

	public Book setOwner(Customer owner) { 
		this.owner = owner;
		return this;
	} 

	public String toString() {
		return this.title + " (" + this.isbn + ")";
	}

	public boolean equals(Object other) {
		if (other == null) {
			return false;
		} else {
		
			if (!this.getClass().equals(other.getClass())) { 
				return false;
			} else {
		
				Book book2 = (Book) other;
				if ((this.id == book2.getId()) && (this.isbn == book2.getIsbn())) {
					return true;
				} else {
					
					return false;
				}
			}
		}
	}
	
	public int hashCode() {
		int temp = 0;
		temp = ( id + " " + isbn).hashCode();
		return temp;
	}
} 