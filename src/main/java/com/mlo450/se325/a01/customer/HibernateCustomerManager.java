package com.mlo450.se325.a01.customer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mlo450.se325.a01.person.Person;

@Repository
public class HibernateCustomerManager implements CustomerManager{
	private static SessionFactory sessionFactory;
	
	public HibernateCustomerManager() {
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory newsessionFactory) {
		sessionFactory = newsessionFactory;
	}

	public Long addCustomer(String name, String email, String address) { 
		Session session = sessionFactory.openSession(); 
		Transaction tx = null; 
		Long customerId = null;
		
		try { 
			tx = session.beginTransaction(); 
			Customer customer = new Customer(name, email, address); 
			customerId = (Long) session.save(customer);
			tx.commit();
		} catch (HibernateException e) { 
			if (tx!=null) {
				tx.rollback(); 
			}
			e.printStackTrace(); 
		} finally { 
			session.close(); 
		} 
		
		return customerId; 
	} 

	public Long addCustomer(Customer customerWithoutId) { 
		Session session = sessionFactory.openSession(); 
		Transaction tx = null; 
		Long customerId = null; 
		try { 
			tx = session.beginTransaction(); 
			customerId = (Long) session.save(customerWithoutId);
			tx.commit();
		} catch (HibernateException e) { 
			if (tx!=null) {
				tx.rollback(); 
			}
			e.printStackTrace(); 
		} finally { 
			session.close(); 
		} 
		return customerId; 
	} 

	public void updateCustomer(Customer updated) { 
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

	public void deleteCustomer(Long id) { 
		Session session = sessionFactory.openSession(); 
		Transaction tx = null; 

		try { 
			tx = session.beginTransaction(); 
			Customer customer = (Customer)session.get(Customer.class, id); 
			session.delete(customer); 
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
	public List<Customer> getAllCustomers( ){ 
		Session session = sessionFactory.openSession(); 
		Transaction tx = null; 
		ArrayList<Customer> allCustomers = new ArrayList<Customer>();

		try { 
			tx = session.beginTransaction(); 
			List<?> Customers = session.createQuery("FROM Customer").list(); 
			for (Iterator<?> iterator = Customers.iterator(); iterator.hasNext();){ 
				Customer customer = (Customer) iterator.next(); 
				allCustomers.add(customer);
			} 
			tx.commit(); 
			return allCustomers;
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

	public void deleteAllCustomers( ) {
		try {
			for (Person b: this.getAllCustomers()) {
				this.deleteCustomer(b.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 

	public Customer getCustomer(Long id) {
		Session session = sessionFactory.openSession(); 
		return ((Customer) session.get(Customer.class, id));
	}
}
