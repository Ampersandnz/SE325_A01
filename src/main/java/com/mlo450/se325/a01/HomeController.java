package com.mlo450.se325.a01;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mlo450.se325.a01.book.Book;
import com.mlo450.se325.a01.book.BookManager;
import com.mlo450.se325.a01.book.HibernateBookManager;
import com.mlo450.se325.a01.customer.Customer;
import com.mlo450.se325.a01.customer.CustomerManager;
import com.mlo450.se325.a01.customer.HibernateCustomerManager;
import com.mlo450.se325.a01.staff.HibernateStaffManager;
import com.mlo450.se325.a01.staff.StaffManager;
import com.mlo450.se325.a01.staff.StaffMember;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	private static final BookManager bookManager = new HibernateBookManager();
	private static final StaffManager staffManager = new HibernateStaffManager();
	private static final CustomerManager customerManager = new HibernateCustomerManager();
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		model.addAttribute("allBooks", bookManager.getAllBooks());
		model.addAttribute("allStaffMembers", staffManager.getAllStaffMembers());
		model.addAttribute("allCustomers", customerManager.getAllCustomers());
		return "home";
	}
	
	@RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
	public String viewBook(@PathVariable("id") Long id, Model model) {
		// Find the specific Book.
		Book book = bookManager.getBook(id);
		
		// Add the Book to the model.
		model.addAttribute("book", book);
		
		// Return the logical view name that will render the model.
		return "singleBookDetails";
	}
	
	@RequestMapping(value = "/book/edit/{id}", method = RequestMethod.GET)
	public String editBook(@PathVariable("id") Long id, Model model) {
		// Find the specific Book.
		Book book = bookManager.getBook(id);
		
		// Add the Book to the model.
		model.addAttribute("book", book);
		
		// Return the logical view name that will render the model.
		return "editBook";
	}

	@RequestMapping(value = "/book/{id}", method = RequestMethod.POST)
	public String updateBook(@PathVariable("id") Long id, int isbn, String title, String author, Model model) {
		// Find the specific Book.
		Book book = bookManager.getBook(id);
		
		book.setIsbn(isbn);
		book.setTitle(title);
		book.setAuthor(author);
		
		bookManager.updateBook(book);
		
		// Add the Book to the model.
		model.addAttribute("book", book);
		
		// Return the logical view name that will render the model.
		return "singleBookDetails";
	}
	
	@RequestMapping(value = "/book/deleted/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long id, Model model) {
		Book book = bookManager.getBook(id);
		bookManager.deleteBook(id);
		
		model.addAttribute("deletedBook", book);
		return "deletedBook";
	}
	
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
	public String viewCustomer(@PathVariable("id") Long id, Model model) {
		// Find the specific Book.
		Customer customer = customerManager.getCustomer(id);
		
		// Add the Book to the model.
		model.addAttribute("customer", customer);
		
		// Return the logical view name that will render the model.
		return "singleCustomerDetails";
	}
	
	@RequestMapping(value = "/staffmember/{id}", method = RequestMethod.GET)
	public String viewStaffMember(@PathVariable("id") Long id, Model model) {
		// Find the specific Book.
		StaffMember staffMember = staffManager.getStaffMember(id);
		
		// Add the Book to the model.
		model.addAttribute("staffMember", staffMember);
		
		// Return the logical view name that will render the model.
		return "singleStaffMemberDetails";
	}
}