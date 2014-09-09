package com.mlo450.se325.a01;

import com.mlo450.se325.a01.book.Book;
import com.mlo450.se325.a01.book.BookManager;
import com.mlo450.se325.a01.book.HibernateBookManager;

public class TestHibernate {

	private static BookManager bookManager = new HibernateBookManager();
	
	public static void main(String[] args) {
		bookManager.initialise();
		Book newBook = new Book(1247325792, "Book title");
		newBook.setId(bookManager.addBook(newBook));
		System.out.println("BOOK ADDED - ID IS " + newBook.getId());
		Book retrieveBook = bookManager.getBook(newBook.getId());
		System.out.println(newBook.getId() + ": " + newBook);
		System.out.println(retrieveBook.getId() + ": " + retrieveBook);
	}

}
