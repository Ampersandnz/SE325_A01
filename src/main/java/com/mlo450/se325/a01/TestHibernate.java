package com.mlo450.se325.a01;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.mlo450.se325.a01.book.Book;
import com.mlo450.se325.a01.book.BookManager;
import com.mlo450.se325.a01.book.HibernateBookManager;
import com.mlo450.se325.a01.customer.Customer;
import com.mlo450.se325.a01.customer.CustomerManager;
import com.mlo450.se325.a01.customer.HibernateCustomerManager;
import com.mlo450.se325.a01.staff.HibernateStaffManager;
import com.mlo450.se325.a01.staff.StaffManager;
import com.mlo450.se325.a01.staff.StaffMember;

public class TestHibernate {

	private static final BookManager bookManager = new HibernateBookManager();
	private static final StaffManager staffManager = new HibernateStaffManager();
	private static final CustomerManager customerManager = new HibernateCustomerManager();
	
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:app-context.xml");
		ctx.refresh();
		checkBook();
		checkStaff();
		checkCustomer();
		ctx.close();
	}
	
	private static void checkBook() {
		Book newBook = new Book(1247325792, "Book title", "Author", null);
		Long id = bookManager.addBook(newBook);
		newBook.setId(id);
		System.out.println("Book added - Id is " + newBook.getId());
		Book retrieveBook = bookManager.getBook(id);
		System.out.println(newBook.getId() + ": " + newBook);
		System.out.println(retrieveBook.getId() + ": " + retrieveBook);
	}
	
	private static void checkStaff() {
		StaffMember newStaffMember = new StaffMember("Michael Lo", "nz.ampersand@gmail.com", "CEO");
		Long id = staffManager.addStaffMember(newStaffMember);
		newStaffMember.setId(id);
		System.out.println("StaffMember added - Id is " + newStaffMember.getId());
		StaffMember retrieveStaff = staffManager.getStaffMember(id);
		System.out.println(newStaffMember.getId() + ": " + newStaffMember);
		System.out.println(retrieveStaff.getId() + ": " + retrieveStaff);
	}
	
	private static void checkCustomer() {
		Customer newCustomer = new Customer("Michael Lo", "nz.ampersand@gmail.com", "131F St Georges Road");
		Long id = customerManager.addCustomer(newCustomer);
		newCustomer.setId(id);
		System.out.println("Customer added - Id is " + newCustomer.getId());
		Customer retrieveCustomer = customerManager.getCustomer(id);
		System.out.println(newCustomer.getId() + ": " + newCustomer);
		System.out.println(retrieveCustomer.getId() + ": " + retrieveCustomer);
	}
}