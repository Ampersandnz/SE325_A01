package com.mlo450.se325.a01;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.mlo450.se325.a01.book.Book;
import com.mlo450.se325.a01.book.BookManager;
import com.mlo450.se325.a01.book.HibernateBookManager;

public class TestHibernate {

	private static BookManager bookManager;
	
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:app-context.xml");
		ctx.refresh();
		Book newBook = new Book(1247325792, "Book title");
		bookManager = new HibernateBookManager();
		newBook.setId(bookManager.addBook(newBook));
		System.out.println("BOOK ADDED - ID IS " + newBook.getId());
		Book retrieveBook = bookManager.getBook(newBook.getId());
		System.out.println(newBook.getId() + ": " + newBook);
		System.out.println(retrieveBook.getId() + ": " + retrieveBook);
		ctx.close();
	}
}