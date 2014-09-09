package com.mlo450.se325.a01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.mlo450.se325.a01.book.Book;
import com.mlo450.se325.a01.book.BookManager;

public class TestHibernate {

	private static BookManager bookManager;
	
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:app-context.xml");
		ctx.refresh();
		Book newBook = new Book(1247325792, "Book title");
		if (bookManager == null) {
			System.out.println("BookManager is null!");
		} else {
			System.out.println("BookManager is not null!");
		}
		Long id = bookManager.addBook(newBook);
		newBook.setId(id);
		System.out.println("BOOK ADDED - ID IS " + newBook.getId());
		Book retrieveBook = bookManager.getBook(newBook.getId());
		System.out.println(newBook.getId() + ": " + newBook);
		System.out.println(retrieveBook.getId() + ": " + retrieveBook);
		ctx.close();
	}
	
	public BookManager getBookManager() {
		return bookManager;
	}
	
	@Autowired
	public void setBookManager (BookManager newBookManager) {
		bookManager = newBookManager;
	}
}