package com.webapp.TextBook.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.webapp.TextBook.Service.AddBookService;

@Controller
@SessionAttributes("books")
public class AddBookController {

  @Autowired AddBookService service;

  @RequestMapping(value = "/getBook", method = RequestMethod.GET)
  public String showAddBookPage(ModelMap model) {
    System.out.println("went through here");
    model.put("books", service.retrieveBooks());

    return "addBook";
  }

  @RequestMapping(value = "/addBook", method = RequestMethod.POST)
  public String showAddBookRecognition(
      ModelMap model,
      @RequestParam String bookCode,
      @RequestParam String bookYear,
      @RequestParam String bookTitle,
      @RequestParam String seqNm) {
    boolean isValidBook = service.validateBook(bookCode, bookTitle, bookYear, seqNm);

    if (!isValidBook) {
      model.put("errorMessage", "Invalid Credentials");
      return "addBook";
    }
    System.out.println("and here");
    service.addToModel(bookCode, bookTitle, bookYear, seqNm);
    service.logBook(bookCode, bookTitle, bookYear, seqNm);
    service.postToDatabase(bookCode, bookTitle, bookYear, seqNm);
    return "addBook";
  }

  @RequestMapping(value = "/addBook", method = RequestMethod.GET)
  public String returnToBookPage(ModelMap model) {

    return "addBook";
  }
}
