package com.mlo450.se325.a01.book;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * 
 * @author Michael Lo
 * Hibernate database interface class. Has methods to Create, Read, Update and Delete User entries and objects to and from the database.
 * Also provides methods to get and delete all Books in the database, for convenience.
 *
 */
public class HibernateBookManager implements BookManager{
	private static SessionFactory sf;
	private static ServiceRegistry serviceRegistry;

	public HibernateBookManager() {
	}

	/**
	 * Sets up the database interface objects. Only needs to be called once, although no harm can come of calling it again.
	 */
	public void initialise () {
		try {
			Configuration configuration = new Configuration();
			configuration.configure("hibernate.cfg.xml");
			serviceRegistry = new ServiceRegistryBuilder().applySettings(
		            configuration.getProperties()).buildServiceRegistry();
			sf = configuration.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex); 
		}
	}

	/**
	 * @param bookWithoutId
	 * @return User ID
	 * 
	 * Method to create a new row in the database, and return its unique primary key identifier.
	 * Takes as arguments a User object. Should only be called for Books that are not already in the database (have no id), or a copy will be created.
	 */
	public Long addBook(Book bookWithoutId) { 
		Session session = sf.openSession(); 
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

	/**
	 * @param bookId
	 * @param field
	 * @param newData
	 * 
	 * Method to update a single value in the database. User ID corresponds to the row, field determines the column to be altered.
	 */
	public void updateBook(Book updated) { 
		Session session = sf.openSession(); 
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

	/**
	 * @param id
	 * 
	 * Method to delete the row from the database with the given primary key.
	 */
	public void deleteBook(Long id) { 
		Session session = sf.openSession(); 
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

	/**
	 * @return AllBooks
	 * 
	 * Method to return a list of User objects, each representing one row in the database.
	 */
	public ArrayList<Book> getAllBooks( ){ 
		Session session = sf.openSession(); 
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

	/**
	 * Method to delete all entries in all rows of the database.
	 */
	public void deleteAllBooks( ) { 
		try {
			for (Book b: this.getAllBooks()) {
				this.deleteBook(b.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 

	/**
	 * @param id
	 * @return book
	 * 
	 * Method to return the User object representing the row of the database identified by the given primary key.
	 */
	public Book getBook(Long id) {
		Session session = sf.openSession(); 
		return ((Book) session.get(Book.class, id));
	}
}