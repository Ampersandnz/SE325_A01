package com.mlo450.se325.a01.customer;

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

import com.mlo450.se325.a01.person.Person;

/**
 * 
 * @author Michael Lo
 * Hibernate database interface class. Has methods to Create, Read, Update and Delete User entries and objects to and from the database.
 * Also provides methods to get and delete all Users in the database, for convenience.
 *
 */
public class HibernateCustomerManager implements CustomerManager{
	private static SessionFactory sf;
	private static ServiceRegistry serviceRegistry;

	private static final String NAME = "name";
	private static final String EMAIL = "email";
	private static final String NONAME = "No name available.";
	private static final String NOEMAIL = "No email available.";
	
	public HibernateCustomerManager() {
	}

	/**
	 * Sets up the database interface objects. Only needs to be called once, although no harm can come of calling it again.
	 */
	public void initialise () {
		try {
			Configuration configuration = new Configuration();
			configuration.configure();
			serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();        
			sf = configuration.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) { 
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex); 
		}
	} 

	/**
	 * @param name
	 * @param email
	 * @return User ID
	 * 
	 * Method to create a new row in the database, and return its unique primary key identifier.
	 * Takes as arguments the three fields of a User object, each corresponding to a column in the database.
	 */
	public Long addUser(String name, String email) { 
		if (name.equals("")||null==name) {
			name = NONAME;
		}

		if (email.equals("")||null==email) {
			email = NOEMAIL;
		}

		Session session = sf.openSession(); 
		Transaction tx = null; 
		Long userId = null; 
		
		try { 
			tx = session.beginTransaction(); 
			Person person = new Person(name, email); 
			userId = (Long) session.save(person);
			tx.commit();
		} catch (HibernateException e) { 
			if (tx!=null) {
				tx.rollback(); 
			}
			e.printStackTrace(); 
		} finally { 
			session.close(); 
		} 
		
		return userId; 
	} 

	/**
	 * @param userWithoutId
	 * @return User ID
	 * 
	 * Method to create a new row in the database, and return its unique primary key identifier.
	 * Takes as arguments a User object. Should only be called for Users that are not already in the database (have no id), or a copy will be created.
	 */
	public Long addUser(Person userWithoutId) { 
		Session session = sf.openSession(); 
		Transaction tx = null; 
		Long userId = null; 
		try { 
			tx = session.beginTransaction(); 
			userId = (Long) session.save(userWithoutId);
			tx.commit();
		} catch (HibernateException e) { 
			if (tx!=null) {
				tx.rollback(); 
			}
			e.printStackTrace(); 
		} finally { 
			session.close(); 
		} 
		return userId; 
	} 

	/**
	 * @param userId
	 * @param field
	 * @param newData
	 * 
	 * Method to update a single value in the database. User ID corresponds to the row, field is the name of the column to be altered.
	 */
	public void updateUser(Long userId, String field, String newData) { 
		Session session = sf.openSession(); 
		Transaction tx = null; 
		try { 
			tx = session.beginTransaction(); 
			Person person = (Person)session.get(Person.class, userId); 
			
			if (field.equals(NAME)) {
				if (newData.equals("")||null==newData) {
					newData = NONAME;
				}
				person.setName(newData);
				
			} else if (field.equals(EMAIL)) {
				if (newData.equals("")||null==newData) {
					newData = NOEMAIL;
				}
				person.setEmail(newData);
			}
			
			session.update(person); 
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
	public void deleteUser(Long id) { 
		Session session = sf.openSession(); 
		Transaction tx = null; 

		try { 
			tx = session.beginTransaction(); 
			Person person = (Person)session.get(Person.class, id); 
			session.delete(person); 
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
	 * @return AllUsers
	 * 
	 * Method to return a list of User objects, each representing one row in the database.
	 */
	public ArrayList<Person> getAllUsers( ){ 
		Session session = sf.openSession(); 
		Transaction tx = null; 
		ArrayList<Person> allUsers = new ArrayList<Person>();

		try { 
			tx = session.beginTransaction(); 
			List<?> Users = session.createQuery("FROM User").list(); 
			for (Iterator<?> iterator = Users.iterator(); iterator.hasNext();){ 
				Person person = (Person) iterator.next(); 
				allUsers.add(person);
			} 
			tx.commit(); 
			return allUsers;
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
	public void deleteAllUsers( ) {
		try {
			for (Person b: this.getAllUsers()) {
				this.deleteUser(b.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 

	/**
	 * @param id
	 * @return user
	 * 
	 * Method to return the User object representing the row of the database identified by the given primary key.
	 */
	public Person getUser(Long id) {
		Session session = sf.openSession(); 
		return ((Person) session.get(Person.class, id));
	}
}
