package com.mlo450.se325.a01.book;

import java.util.List;

/**
 * @author Michael Lo Database interface class. Has methods to Create, Read,
 *         Update and Delete User entries and objects to and from the database.
 *         Also provides methods to get and delete all Books in the database,
 *         for convenience.
 */
public interface BookManager {

	/**
	 * Sets up the database interface objects. Only needs to be called once,
	 * although no harm can come of calling it again.
	 */
	public void initialise();

	/**
	 * @param bookWithoutId
	 * @return User ID Method to create a new row in the database, and return
	 *         its unique primary key identifier. Takes as arguments a User
	 *         object. Should only be called for Books that are not already in
	 *         the database (have no id), or a copy will be created.
	 */
	public Long addBook(Book bookWithoutId);

	/**
	 * @param bookId
	 * @param field
	 * @param newData
	 *            Method to update a single value in the database. User ID
	 *            corresponds to the row, field determines the column to be
	 *            altered.
	 */
	public void updateBook(Book updated);

	/**
	 * @param id
	 *            Method to delete the row from the database with the given
	 *            primary key.
	 */
	public void deleteBook(Long id);

	/**
	 * @return AllBooks Method to return a list of User objects, each
	 *         representing one row in the database.
	 */
	public List<Book> getAllBooks();

	/**
	 * Method to delete all entries in all rows of the database.
	 */
	public void deleteAllBooks();

	/**
	 * @param id
	 * @return book Method to return the User object representing the row of the
	 *         database identified by the given primary key.
	 */
	public Book getBook(Long id);
}
