package com.mlo450.se325.a01;

import java.util.ArrayList;
import java.util.List;

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
	private static final int _LIBRARY_ID = 1;
	private static final BookManager bookManager = new HibernateBookManager();
	private static final StaffManager staffManager = new HibernateStaffManager();
	private static final CustomerManager customerManager = new HibernateCustomerManager();
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("allBooks", bookManager.getAllBooks());
		model.addAttribute("allStaffMembers", staffManager.getAllStaffMembers());
		model.addAttribute("allCustomers", customerManager.getAllCustomers());
		return "home";
	}
	
	@RequestMapping(value = "/book/added", method = RequestMethod.POST)
	public String bookAdded(Model model, int isbn, String title, String author) {
		Book bookToAdd = new Book(isbn, title, author, customerManager.getCustomer((long) _LIBRARY_ID));
		
		bookManager.addBook(bookToAdd);
		
		model.addAttribute("addedBook", bookToAdd);
		return "addedBook";
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
	
	@RequestMapping(value = "/book/add", method = RequestMethod.GET)
	public String addBook() {
		// Return the logical view name that will render the model.
		return "addBook";
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
		List<Book> borrowedBooks = new ArrayList<Book>();
		
		for (Book b: bookManager.getAllBooks()) {
			if (b.getOwner().equals(customer)) {
				borrowedBooks.add(b);
			}
		}
		
		// Add the Book to the model.
		model.addAttribute("customer", customer);
		model.addAttribute("borrowedBooks", borrowedBooks);
		
		// Return the logical view name that will render the model.
		return "singleCustomerDetails";
	}
	
	@RequestMapping(value = "/customer/edit/{id}", method = RequestMethod.GET)
	public String editCustomer(@PathVariable("id") Long id, Model model) {
		// Find the specific Customer.
		Customer customer = customerManager.getCustomer(id);
		
		// Add the Customer to the model.
		model.addAttribute("customer", customer);
		
		// Return the logical view name that will render the model.
		return "editCustomer";
	}

	@RequestMapping(value = "/customer/{id}", method = RequestMethod.POST)
	public String updateCustomer(@PathVariable("id") Long id, String name, String email, String address, Model model) {
		// Find the specific Customer.
		Customer customer = customerManager.getCustomer(id);
		
		customer.setName(name);
		customer.setEmail(email);
		customer.setAddress(address);
		
		customerManager.updateCustomer(customer);
		
		// Add the Customer to the model.
		model.addAttribute("customer", customer);
		
		// Return the logical view name that will render the model.
		return "singleCustomerDetails";
	}
	
	@RequestMapping(value = "/customer/deleted/{id}", method = RequestMethod.GET)
	public String deleteCustomer(@PathVariable("id") Long id, Model model) {
		Customer customer = customerManager.getCustomer(id);
		
		if (customer.getId() != _LIBRARY_ID) {
			customerManager.deleteCustomer(id);
			
			model.addAttribute("deletedCustomer", customer);
			return "deletedCustomer";
		} else {
			return "deletedLibrary";
		}
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
	
	@RequestMapping(value = "/staffMember/edit/{id}", method = RequestMethod.GET)
	public String editStaffMember(@PathVariable("id") Long id, Model model) {
		// Find the specific StaffMember.
		StaffMember staffMember = staffManager.getStaffMember(id);
		
		// Add the StaffMember to the model.
		model.addAttribute("staffMember", staffMember);
		
		// Return the logical view name that will render the model.
		return "editStaffMember";
	}

	@RequestMapping(value = "/staffMember/{id}", method = RequestMethod.POST)
	public String updateStaffMember(@PathVariable("id") Long id, String name, String email, String position, Model model) {
		// Find the specific StaffMember.
		StaffMember staffMember = staffManager.getStaffMember(id);
		
		staffMember.setName(name);
		staffMember.setEmail(email);
		staffMember.setPosition(position);
		
		staffManager.updateStaffMember(staffMember);
		
		// Add the StaffMember to the model.
		model.addAttribute("staffMember", staffMember);
		
		// Return the logical view name that will render the model.
		return "singleStaffMemberDetails";
	}
	
	@RequestMapping(value = "/staffMember/deleted/{id}", method = RequestMethod.GET)
	public String deleteStaffMember(@PathVariable("id") Long id, Model model) {
		StaffMember staffMember = staffManager.getStaffMember(id);
		staffManager.deleteStaffMember(id);
		
		model.addAttribute("deletedStaffMember", staffMember);
		return "deletedStaffMember";
	}
}