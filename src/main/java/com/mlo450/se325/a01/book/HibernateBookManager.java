package com.mlo450.se325.a01.book;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateBookManager implements BookManager{

	private static SessionFactory sessionFactory;

	public HibernateBookManager() {
		if (sessionFactory == null) {
			System.out.println("SessionFactory is null!");
		} else {
			System.out.println("SessionFactory is not null!");
		}
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory newsessionFactory) {
		sessionFactory = newsessionFactory;
	}
	
	public Long addBook(Book bookWithoutId) { 
		Session session = sessionFactory.openSession(); 
		Transaction tx = null; 
		Long bookId = null; 
		try { 
			tx = session.beginTransaction(); 
			bookId = (Long) session.save(bookWithoutId);
			tx.commit();
		} catch (HibernateException e) { 
			if (tx!=null) {
				tx.rollback(); 
			}
			e.printStackTrace(); 
		} finally { 
			session.close(); 
		} 
		return bookId; 
	} 

	public void updateBook(Book updated) { 
		Session session = sessionFactory.openSession(); 
		Transaction tx = null; 
		try { 
			tx = session.beginTransaction(); 
			session.update(updated); 
			tx.commit(); 
		} catch (HibernateException e) { 
			if (tx!=null) {
				tx.rollback(); 
			}
			e.printStackTrace(); 
		} finally { 
			session.close(); 
		} 
	} 

	public void deleteBook(Long id) { 
		Session session = sessionFactory.openSession(); 
		Transaction tx = null; 

		try { 
			tx = session.beginTransaction(); 
			Book book = (Book)session.get(Book.class, id); 
			session.delete(book); 
			tx.commit(); 
		} catch (HibernateException e) { 
			if (tx!=null) {
				tx.rollback(); 
			}
			e.printStackTrace(); 
		} finally { 
			session.close(); 
		} 
	} 

	public ArrayList<Book> getAllBooks( ){ 
		Session session = sessionFactory.openSession(); 
		Transaction tx = null; 
		ArrayList<Book> allBooks = new ArrayList<Book>();

		try { 
			tx = session.beginTransaction(); 
			List<?> Books = session.createQuery("FROM Book").list(); 
			for (Iterator<?> iterator = Books.iterator(); iterator.hasNext();){ 
				Book book = (Book) iterator.next(); 
				allBooks.add(book);
			} 
			tx.commit(); 
			return allBooks;
		} catch (HibernateException e) { 
			if (tx!=null) {
				tx.rollback(); 
			}
			e.printStackTrace(); 
		} finally { 
			session.close(); 
		} 
		return null;
	}

	public void deleteAllBooks( ) { 
		try {
			for (Book b: this.getAllBooks()) {
				this.deleteBook(b.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 

	public Book getBook(Long id) {
		Session session = sessionFactory.openSession(); 
		return ((Book) session.get(Book.class, id));
	}
}