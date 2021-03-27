package com.webapp.TextBook.controller;

import java.util.List;

import com.webapp.TextBook.Service.AddBookService;
import com.webapp.TextBook.Service.BookQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {


    @Autowired
    AddBookService service;

    @RequestMapping("/")
    public String login(){
        return "login";
    }

    @RequestMapping("/supervisorView")
    public String supervisor(){
        return "supervisorView";
    }

    @RequestMapping("/maintenanceFormView")
    public String maintenance(){
        return "maintenanceFormView";
    }
/*
    @RequestMapping("/addBook")
    public String addBook(ModelMap model){
        System.out.println("went through here at home");
        model.put("books", service.retrieveBooks());
        return "addBook";
    }

 */


    @Autowired
    BookQueryService bookQueryService;

    @RequestMapping(value = "/bookQuery", method = RequestMethod.GET)
    public String bookQuery(ModelMap model){
        System.out.println("Book Query GET");
        return "bookQuery";
    }

    @RequestMapping(value = "/bookQuery", method = RequestMethod.POST)
    public String bookQueryPost(ModelMap model, @RequestParam String bookCode, @RequestParam String editionYear, @RequestParam String seqNm){
        System.out.println("Book Query POST");
        if(!bookQueryService.validateBook(bookCode, editionYear, seqNm)){
            model.put("errorMessage", "Invalid Credentials");
            return "addBook";
        }
        System.out.println("Passed Book Validation");
        bookQueryService.logQuery(bookCode,editionYear,seqNm);
        return "bookQuery";
    }

    @RequestMapping("/bookDisposition")
    public String bookDisposition(){
        return "bookDisposition";
    }

    @RequestMapping("/replaceBarcode")
    public String replaceBarcode(){
        return "replaceBarcode";
    }

    @RequestMapping("/queryCourse")
    public String queryCourse(){
        return "queryCourse";
    }

    @RequestMapping("/courseMessage")
    public String courseMessage(){
        return "courseMessage";
    }

    @RequestMapping("/changeBookCode")
    public String changeBookCode(){
        return "changeBookCode";
    }

}