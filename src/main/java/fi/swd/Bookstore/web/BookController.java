package fi.swd.Bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import fi.swd.Bookstore.domain.Book;

@Controller
public class BookController {
   
	@RequestMapping(value="/index", method=RequestMethod.GET)
    public String friendForm(Model model) {
    
       model.addAttribute("book", new Book());
        return "index";
    }
}