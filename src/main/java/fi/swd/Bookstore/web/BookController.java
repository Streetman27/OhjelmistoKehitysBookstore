package fi.swd.Bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.swd.Bookstore.BookRepository;
import fi.swd.Bookstore.domain.Book;

@Controller
public class BookController {

	@Autowired
	private BookRepository repository;

	@RequestMapping(value = "/booklist", method = RequestMethod.GET)
	public String bookForm(Model model) {

		model.addAttribute("books", repository.findAll());
		return "booklist";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {

		model.addAttribute("books", repository.findAll());
		return "addbook";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Book book) {
		repository.save(book);
		return "booklist";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Book bookId) {
		repository.delete(bookId);
		return "booklist";
	}
	
	
}