package com.mlo450.se325.a01.staff;

import java.util.List;

import org.hibernate.SessionFactory;

/**
 * @author Michael Lo Database interface class. Has methods to Create, Read,
 *         Update and Delete User entries and objects to and from the database.
 *         Also provides methods to get and delete all Users in the database,
 *         for convenience.
 */
public interface StaffManager {
	
	public SessionFactory getSessionFactory();

	public void setSessionFactory(SessionFactory newsessionFactory);

	/**
	 * @param userWithoutId
	 * @return User ID Method to create a new row in the database, and return
	 *         its unique primary key identifier. Takes as arguments a User
	 *         object. Should only be called for Users that are not already in
	 *         the database (have no id), or a copy will be created.
	 */
	public Long addStaffMember(StaffMember userWithoutId);

	/**
	 * @param userId
	 * @param field
	 * @param newData
	 *            Method to update a single value in the database. User ID
	 *            corresponds to the row, field is the name of the column to be
	 *            altered.
	 */
	public void updateStaffMember(StaffMember updated);

	/**
	 * @param id
	 *            Method to delete the row from the database with the given
	 *            primary key.
	 */
	public void deleteStaffMember(Long id);

	/**
	 * @param id
	 *            Method to empty the database.
	 */
	public void deleteAllStaffMembers();

	/**
	 * @return AllUsers Method to return a list of User objects, each
	 *         representing one row in the database.
	 */
	public List<StaffMember> getAllStaffMembers();

	/**
	 * @param id
	 * @return user Method to return the User object representing the row of the
	 *         database identified by the given primary key.
	 */
	public StaffMember getStaffMember(Long id);
}
