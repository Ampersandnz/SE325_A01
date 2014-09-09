package com.mlo450.se325.a01.book;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.mlo450.se325.a01.person.Person;

/**
 * @author Michael Lo
 * Hibernate mapping bean. Each Book represents a row in the database.
 * set*() methods return this, so multiple set*() calls can be concatenated together.
 */
@Entity
@Table(name = "BOOK") 
public class Book { 
	@Id 
	@GeneratedValue
	@Column(name = "book_id") 
	private Long id;

	@Column(name = "isbn", nullable = false)
	private int isbn;

	@Column(name = "title", nullable = false)
	private String title;

	@OneToOne
	@JoinColumn(name = "owner_Id", nullable = true)
	private Person owner;

	public Book() {
	}
	
	public Book(int isbn, String title) {
		this();
		this.isbn = isbn;
		this.title = title;
	}

	public Book(int isbn, String title, Person owner) {
		this(isbn, title);
		this.owner = owner;
	}

	public Book(Long id, int isbn, String title, Person owner) {
		this(isbn, title, owner);
		this.id = id;
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

	public Person getOwner() { 
		return owner; 
	} 

	public Book setOwner( Person owner ) { 
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