package com.webapp.TextBook.controller;

import java.util.List;

import com.webapp.TextBook.model.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

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

    @RequestMapping("/addBook")
    public String addBook(){
        return "addBook";
    }

    @RequestMapping("/bookQuery")
    public String bookQuery(){
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
    @RequestMapping("/createReport")
    public String createReport(){
        return "createReport";
    }
    @RequestMapping("/patronCheckInOut")
    public String patronCheckInOut(){
        return "patronCheckInOut";
    }
    @RequestMapping("/patronPrevBooks")
    public String patronPrevBooks(){
        return "patronPrevBooks";
    }
    @RequestMapping("/patronSchedule")
    public String patronSchedule(){
        return "patronSchedule";
    }
    @RequestMapping("/patronSoldBooks")
    public String patronSoldBooks(){
        return "patronSoldBooks";
    }

}