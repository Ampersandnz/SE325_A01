package com.mlo450.se325.a01.staff;

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
 * Hibernate database interface class. Has methods to Create, Read, Update and Delete StaffMember entries and objects to and from the database.
 * Also provides methods to get and delete all StaffMembers in the database, for convenience.
 *
 */
public class HibernateStaffManager implements StaffManager{
	private static SessionFactory sf;
	private static ServiceRegistry serviceRegistry;

	private static final String NONAME = "No name listed.";
	private static final String NOEMAIL = "No email listed.";
	private static final String NOPOSITION = "No position listed.";
	
	public HibernateStaffManager() {
		
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
	 * @return StaffMember ID
	 * 
	 * Method to create a new row in the database, and return its unique primary key identifier.
	 * Takes as arguments the three fields of a StaffMember object, each corresponding to a column in the database.
	 */
	public Long addStaffMember(String name, String email, String position) { 
		if (name.equals("") || null == name) {
			name = NONAME;
		}

		if (email.equals("") || null == email) {
			email = NOEMAIL;
		}
		
		if (position.equals("") || null == position) {
			position = NOPOSITION;
		}

		Session session = sf.openSession(); 
		Transaction tx = null; 
		Long staffMemberId = null; 
		
		try { 
			tx = session.beginTransaction(); 
			StaffMember staffMember = new StaffMember(name, email, position); 
			staffMemberId = (Long) session.save(staffMember);
			tx.commit();
		} catch (HibernateException e) { 
			if (tx!=null) {
				tx.rollback(); 
			}
			e.printStackTrace(); 
		} finally { 
			session.close(); 
		} 
		
		return staffMemberId; 
	} 

	/**
	 * @param staffMemberWithoutId
	 * @return StaffMember ID
	 * 
	 * Method to create a new row in the database, and return its unique primary key identifier.
	 * Takes as arguments a StaffMember object. Should only be called for StaffMembers that are not already in the database (have no id), or a copy will be created.
	 */
	public Long addStaffMember(StaffMember staffMemberWithoutId) { 
		Session session = sf.openSession(); 
		Transaction tx = null; 
		Long staffMemberId = null; 
		try { 
			tx = session.beginTransaction(); 
			staffMemberId = (Long) session.save(staffMemberWithoutId);
			tx.commit();
		} catch (HibernateException e) { 
			if (tx!=null) {
				tx.rollback(); 
			}
			e.printStackTrace(); 
		} finally { 
			session.close(); 
		} 
		return staffMemberId; 
	} 

	/**
	 * @param staffMemberId
	 * @param field
	 * @param newData
	 * 
	 * Method to update a single value in the database. StaffMember ID corresponds to the row, field is the name of the column to be altered.
	 */
	public void updateStaffMember(StaffMember updated) { 
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
	public void deleteStaffMember(Long id) { 
		Session session = sf.openSession(); 
		Transaction tx = null; 

		try { 
			tx = session.beginTransaction(); 
			StaffMember staffMember = (StaffMember)session.get(StaffMember.class, id); 
			session.delete(staffMember); 
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
	 * @return AllStaffMembers
	 * 
	 * Method to return a list of StaffMember objects, each representing one row in the database.
	 */
	public ArrayList<StaffMember> getAllStaffMembers( ){ 
		Session session = sf.openSession(); 
		Transaction tx = null; 
		ArrayList<StaffMember> allStaffMembers = new ArrayList<StaffMember>();

		try { 
			tx = session.beginTransaction(); 
			List<?> StaffMembers = session.createQuery("FROM StaffMember").list(); 
			for (Iterator<?> iterator = StaffMembers.iterator(); iterator.hasNext();){ 
				StaffMember staffMember = (StaffMember) iterator.next(); 
				allStaffMembers.add(staffMember);
			} 
			tx.commit(); 
			return allStaffMembers;
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
	public void deleteAllStaffMembers( ) {
		try {
			for (StaffMember b: this.getAllStaffMembers()) {
				this.deleteStaffMember(b.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 

	/**
	 * @param id
	 * @return staffMember
	 * 
	 * Method to return the StaffMember object representing the row of the database identified by the given primary key.
	 */
	public StaffMember getStaffMember(Long id) {
		Session session = sf.openSession(); 
		return ((StaffMember) session.get(StaffMember.class, id));
	}
}
