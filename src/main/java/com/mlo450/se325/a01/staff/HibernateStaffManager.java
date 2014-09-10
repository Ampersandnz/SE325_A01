package com.mlo450.se325.a01.staff;

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
public class HibernateStaffManager implements StaffManager {
	
	private static SessionFactory sessionFactory;

	private static final String NONAME = "No name listed.";
	private static final String NOEMAIL = "No email listed.";
	private static final String NOPOSITION = "No position listed.";
	
	public HibernateStaffManager() {
		
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory newsessionFactory) {
		sessionFactory = newsessionFactory;
	}

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

		Session session = sessionFactory.openSession(); 
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

	public Long addStaffMember(StaffMember staffMemberWithoutId) { 
		Session session = sessionFactory.openSession(); 
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

	public void updateStaffMember(StaffMember updated) { 
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

	public void deleteStaffMember(Long id) { 
		Session session = sessionFactory.openSession(); 
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

	public ArrayList<StaffMember> getAllStaffMembers( ){ 
		Session session = sessionFactory.openSession(); 
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

	public void deleteAllStaffMembers( ) {
		try {
			for (StaffMember b: this.getAllStaffMembers()) {
				this.deleteStaffMember(b.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 

	public StaffMember getStaffMember(Long id) {
		Session session = sessionFactory.openSession(); 
		return ((StaffMember) session.get(StaffMember.class, id));
	}
}
