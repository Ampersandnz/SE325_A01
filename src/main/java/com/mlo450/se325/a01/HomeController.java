package com.mlo450.se325.a01;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mlo450.se325.a01.book.Book;
import com.mlo450.se325.a01.book.BookManager;
import com.mlo450.se325.a01.book.HibernateBookManager;
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
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private static final BookManager bookManager = new HibernateBookManager();
	private static final StaffManager staffManager = new HibernateStaffManager();
	private static final CustomerManager customerManager = new HibernateCustomerManager();
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
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
}