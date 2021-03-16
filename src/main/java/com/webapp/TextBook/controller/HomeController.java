package com.webapp.TextBook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String login(){ return "supervisorView"; }

    @RequestMapping("/maintenanceFormView.jsp")
    public String maintenance(){ return "maintenanceFormView";}

    @RequestMapping("/addBook.jsp")
    public String addBook(){ return "addBook";}

}