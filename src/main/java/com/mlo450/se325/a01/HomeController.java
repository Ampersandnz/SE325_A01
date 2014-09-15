package com.mlo450.se325.a01;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mlo450.se325.a01.book.Book;
import com.mlo450.se325.a01.book.BookManager;
import com.mlo450.se325.a01.book.HibernateBookManager;
import com.mlo450.se325.a01.customer.Customer;
import com.mlo450.se325.a01.customer.CustomerManager;
import com.mlo450.se325.a01.customer.HibernateCustomerManager;
import com.mlo450.se325.a01.logging.LoggingAdvice;
import com.mlo450.se325.a01.staff.HibernateStaffManager;
import com.mlo450.se325.a01.staff.StaffManager;
import com.mlo450.se325.a01.staff.StaffMember;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	private static final boolean _LOGGING_ENABLED = true;
	private static boolean _FIRST_RUN = true;
	private static final int _LIBRARY_ID = 1;
	private static BookManager bookManager;
	private static CustomerManager customerManager;
	private static StaffManager staffManager;
	
	@PostConstruct
	private void initialise() {
		if (_LOGGING_ENABLED) {
	        ProxyFactory pf1 = new ProxyFactory();
	        pf1.setInterfaces(new Class[] {BookManager.class});
	        pf1.addAdvice(new LoggingAdvice());
	        
	        ProxyFactory pf2 = new ProxyFactory();
	        pf2.setInterfaces(new Class[] {CustomerManager.class});
	        pf2.addAdvice(new LoggingAdvice());
	        
	        ProxyFactory pf3 = new ProxyFactory();
	        pf3.setInterfaces(new Class[] {StaffManager.class});
	        pf3.addAdvice(new LoggingAdvice());

	        pf1.setTarget(new HibernateBookManager());
	        bookManager = (BookManager) pf1.getProxy();
	        
	        pf2.setTarget(new HibernateCustomerManager());
	        customerManager = (CustomerManager) pf2.getProxy();
	        
	        pf3.setTarget(new HibernateStaffManager());
	        staffManager = (StaffManager) pf3.getProxy();
		} else {
			bookManager = new HibernateBookManager();
			staffManager = new HibernateStaffManager();
			customerManager = new HibernateCustomerManager();
		}
		
		_FIRST_RUN = false;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
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
	
	@RequestMapping(value = "/staffmember/{id}", method = RequestMethod.GET)
	public String viewStaffMember(@PathVariable("id") Long id, Model model) {
		// Find the specific Book.
		StaffMember staffMember = staffManager.getStaffMember(id);
		
		// Add the Book to the model.
		model.addAttribute("staffMember", staffMember);
		
		// Return the logical view name that will render the model.
		return "singleStaffMemberDetails";
	}
	
	@RequestMapping(value = "/book/add", method = RequestMethod.GET)
	public String addBook() {
		// Return the logical view name that will render the model.
		return "addBook";
	}
	
	@RequestMapping(value = "/customer/add", method = RequestMethod.GET)
	public String addCustomer() {
		// Return the logical view name that will render the model.
		return "addCustomer";
	}
	
	@RequestMapping(value = "/staffMember/add", method = RequestMethod.GET)
	public String addStaffMember() {
		// Return the logical view name that will render the model.
		return "addStaffMember";
	}

	@RequestMapping(value = "/book/added", method = RequestMethod.POST)
	public String bookAdded(Model model, int isbn, String title, String author) {
		Book bookToAdd = new Book(isbn, title, author, customerManager.getCustomer((long) _LIBRARY_ID));
		
		bookManager.addBook(bookToAdd);
		
		model.addAttribute("addedBook", bookToAdd);
		return "addedBook";
	}

	@RequestMapping(value = "/customer/added", method = RequestMethod.POST)
	public String customerAdded(Model model, String name, String email, String address) {
		Customer customerToAdd = new Customer(name, email, address);
		
		customerManager.addCustomer(customerToAdd);
		
		model.addAttribute("addedCustomer", customerToAdd);
		return "addedCustomer";
	}

	@RequestMapping(value = "/staffMember/added", method = RequestMethod.POST)
	public String staffMemberAdded(Model model, String name, String email, String position) {
		StaffMember staffMemberToAdd = new StaffMember(name, email, position);
		
		staffManager.addStaffMember(staffMemberToAdd);
		
		model.addAttribute("addedStaffMember", staffMemberToAdd);
		return "addedStaffMember";
	}
	
	@RequestMapping(value = "/book/edit/{id}", method = RequestMethod.GET)
	public String editBook(@PathVariable("id") Long id, Model model) {
		// Find the specific Book.
		Book book = bookManager.getBook(id);
		
		// Add the Book to the model.
		model.addAttribute("book", book);
		model.addAttribute("allCustomers", customerManager.getAllCustomers());
		
		// Return the logical view name that will render the model.
		return "editBook";
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

	@RequestMapping(value = "/staffMember/edit/{id}", method = RequestMethod.GET)
	public String editStaffMember(@PathVariable("id") Long id, Model model) {		
		// Find the specific StaffMember.
		StaffMember staffMember = staffManager.getStaffMember(id);
		
		// Add the StaffMember to the model.
		model.addAttribute("staffMember", staffMember);
		
		// Return the logical view name that will render the model.
		return "editStaffMember";
	}
	
	@RequestMapping(value = "/book/deleted/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long id, Model model) {
		Book book = bookManager.getBook(id);
		
		if (book.getOwner().getId() == _LIBRARY_ID) {
			bookManager.deleteBook(id);
			
			model.addAttribute("deletedBook", book);
			return "deletedBook";
		} else {
			return "deletedInPossession";
		}
	}

	@RequestMapping(value = "/customer/deleted/{id}", method = RequestMethod.GET)
	public String deleteCustomer(@PathVariable("id") Long id, Model model) {
		Customer customer = customerManager.getCustomer(id);
		
		if (customer.getId() == _LIBRARY_ID) {
			return "deletedLibrary";
		} else if (customer.getBorrowed().size() > 0) {
			return "deletedHasBorrowedBooks";
		} else {
			customerManager.deleteCustomer(id);
			
			model.addAttribute("deletedCustomer", customer);
			return "deletedCustomer";
		}
	}
	
	@RequestMapping(value = "/staffMember/deleted/{id}", method = RequestMethod.GET)
	public String deleteStaffMember(@PathVariable("id") Long id, Model model) {
		StaffMember staffMember = staffManager.getStaffMember(id);
		staffManager.deleteStaffMember(id);
		
		model.addAttribute("deletedStaffMember", staffMember);
		return "deletedStaffMember";
	}

	@RequestMapping(value = "/book/{id}", method = RequestMethod.POST)
	public String updateBook(@PathVariable("id") Long id, int isbn, String title, String author, String owner, Model model) {
		// Find the specific Book.
		Book book = bookManager.getBook(id);
		
		book.setIsbn(isbn);
		book.setTitle(title);
		book.setAuthor(author);
		
		for (Customer c: customerManager.getAllCustomers()) {
			if (c.getName().equals(owner)) {
				book.setOwner(c);
			}
		}
		
		bookManager.updateBook(book);
		
		// Add the Book to the model.
		model.addAttribute("book", book);
		
		// Return the logical view name that will render the model.
		return "singleBookDetails";
	}
	
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.POST)
	public String updateCustomer(@PathVariable("id") Long id, String name, String email, String address, Model model) {
		if (_FIRST_RUN) {
			this.initialise();
		}
		
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
	
	@RequestMapping(value="/book/{id}", method=RequestMethod.PUT)
	public void putBook(@RequestBody Book book, @PathVariable Long id) {
		bookManager.addBook(book);
	}

	@RequestMapping(value="/book/{id}", method=RequestMethod.DELETE)
	public void deleteBook(@PathVariable Long id) {
		bookManager.deleteBook(id);
	}
	
	@RequestMapping(value="/customer/{id}", method=RequestMethod.PUT)
	public void putCustomer(@RequestBody Customer customer, @PathVariable Long id) {
		customerManager.addCustomer(customer);
	}

	@RequestMapping(value="/customer/{id}", method=RequestMethod.DELETE)
	public void deleteCustomer(@PathVariable Long id) {
		customerManager.deleteCustomer(id);
	}
	
	@RequestMapping(value="/staffMember/{id}", method=RequestMethod.PUT)
	public void putStaffMember(@RequestBody StaffMember staffMember, @PathVariable Long id) {
		staffManager.addStaffMember(staffMember);
	}

	@RequestMapping(value="/staffMember/{id}", method=RequestMethod.DELETE)
	public void deleteStaffMember(@PathVariable Long id) {
		staffManager.deleteStaffMember(id);
	}
}