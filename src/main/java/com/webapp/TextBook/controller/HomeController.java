package com.webapp.TextBook.controller;

//Imported Standard Java Libraries
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


import com.webapp.TextBook.Model.*;
import com.webapp.TextBook.Service.*;
import com.webapp.TextBook.Repository.NwtxdtRepository;

//Imported Spring Libraries
import com.webapp.TextBook.Model.Nwtxin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;


//Imported Services
import com.webapp.TextBook.Service.*;


//Imported Models
import com.webapp.TextBook.Model.*;

import javax.validation.Valid;


@Controller
public class HomeController {

    Boolean supervisor = false;
    Boolean studentEmployee = false;

    @RequestMapping("/")
    public String login() {
        System.out.println("Login GET");
        supervisor = false;
        studentEmployee = false;
        return "login";
    }

    //Just temporary login things for now. Intended to be replaced later!
    @RequestMapping(value="/", method = RequestMethod.POST)
    public String loginPost(@RequestParam (value = "login",required = false, defaultValue = "")String login,
                            @RequestParam (value = "password",required = false, defaultValue = "")String password)
                            throws ParseException{
        System.out.println("Login POST");
        if(login.equals("admin") && password.equals("admin")){
            supervisor = true;
            studentEmployee = false;
            return "Supervisor/supervisorView";
        } else{
            studentEmployee = true;
            supervisor = false;
            return "StudentEmployee/studentEmployeeView";
        }
    }


    @RequestMapping("/Supervisor-Home")
    public String supervisor(){
        return "Supervisor/supervisorView";
    }

    @RequestMapping("/Maintenance-Form")
    public String maintenance(){
        return "Supervisor/maintenanceFormView";
    }


    @Autowired
    AddBookService addBookService;

    //Add-Book GET
    //SUPERVISOR ACCESS ONLY
    @GetMapping("/Add-Book")
    public String addBookPage(){
        System.out.println("Add Book GET");
        if(!supervisor){
            return "redirect:/";
        }
        return "Supervisor/addBook";
    }

    //Add-Book POST
    //SUPERVISOR ACCESS ONLY
    @PostMapping("/Add-Book")
    public String addBookPOST(@Valid Nwtxdt nwtxdt, BindingResult bindingResult,
                              ModelMap model) throws ParseException {
        System.out.println("Add Book POST");
        if(!supervisor){
            return "redirect:/";
        }
        //If There Are Errors Compared To The Model, Then It Will Return Invalid Credentials
        if(bindingResult.hasErrors()){
            model.put("returnVoidError", "Invalid Credentials");
            for (Object object : bindingResult.getAllErrors()) {
                if(object instanceof FieldError) {
                    System.out.println((FieldError) object);
                }
            }
            return "Supervisor/addBook";
        }

        //Add Book Service - Save
        addBookService.saveNwtxdt(nwtxdt);
        return "Supervisor/addBook";
    }


    @Autowired
    BookQueryService bookQueryService;

    //Find-Book GET
    //SUPERVISOR ONLY
    @GetMapping("/Find-Book")
    public String bookQuery(ModelMap model){
        System.out.println("Book Query GET");
        if(!supervisor){
            return "redirect:/";
        }
        return "Supervisor/bookQuery";
    }

    //Find-Book POST
    //SUPERVISOR ONLY
    @PostMapping("/Find-Book")
    public String bookQueryPost(@Valid Nwtxdt nwtxdt, BindingResult bindingResultNwtxdt,
                                @Valid Nwtxin nwtxin, BindingResult bindingResultNwtxin,
                                ModelMap model)
                                throws ParseException {
        System.out.println("Book Query POST");
        if(!supervisor){
            return "redirect:/";
        }
        if(bindingResultNwtxdt.hasErrors() || bindingResultNwtxin.hasErrors()){
            model.put("returnVoidError", "Invalid Credentials");
            for (Object object : bindingResultNwtxdt.getAllErrors()) {
                if(object instanceof FieldError) {
                    System.out.println((FieldError) object);
                }
            }
            for (Object object : bindingResultNwtxin.getAllErrors()) {
                if(object instanceof FieldError) {
                    System.out.println((FieldError) object);
                }
            }
            return "Supervisor/bookQuery";
        }

        if (nwtxdt != null) {
            //Found everything, and putting all needed items to the front page
            model.put("bookTitle",          nwtxdt.getBookCode());
            model.put("seqNr",              nwtxdt.getSeqNr());
            model.put("prevTerm",           nwtxdt.getPrevTerm());
            model.put("bookDisposition",    nwtxdt.getDisposition());
            model.put("termCheckedOut",     nwtxdt.getTerm());
            model.put("checkedOutTo",       nwtxdt.getPidm());
            model.put("dateCheckedOut",     nwtxdt.getDateCheckedOut());
            model.put("prevCheckedOutTo",   nwtxdt.getPrevPidm());
            model.put("dateCheckedIn",      nwtxdt.getPrevDateCheckedIn());

            return "StudentEmployee/bookQuery";
        } else {
            //Wasn't able to find a book off of given credentials
            model.put("returnVoidError", "No Book Found Off of Given Credentials");
        }

        return "Supervisor/bookQuery";
    }


    @Autowired
    BookDispositionService bookDispositionService;

    //Change Disposition GET
    //SUPERVISOR ONLY
    @GetMapping("/Change-Disposition")
    public String bookDisposition(){
        System.out.println("Book Dispostion GET");
        if(!supervisor){
            return "redirect:/";
        }
        return "Supervisor/bookDisposition";
    }

    //Change Disposition POST
    //SUPERVISOR ONLY
    @PostMapping("/Change-Disposition")
    public String bookDispositionPost(@Valid Nwtxdt nwtxdt, BindingResult bindingResult,
                                      ModelMap model)
                                      throws ParseException {
        System.out.println("Book Disposition POST");
        if(!supervisor){
            return "redirect:/";
        }
        if(bindingResult.hasErrors()){
            model.put("returnVoidError", "Invalid Credentials");
            for (Object object : bindingResult.getAllErrors()) {
                if(object instanceof FieldError) {
                    System.out.println((FieldError) object);
                }
            }
            return "Supervisor/addBook";
        }

        Nwtxdt oldNwtxdt = bookDispositionService.getNwtxdt(nwtxdt.getBookCode(), nwtxdt.getEditionYear(), nwtxdt.getBarcode());
        oldNwtxdt.setDisposition(nwtxdt.getDisposition());
        bookDispositionService.setNwtxdt(oldNwtxdt);
        return "Supervisor/bookDisposition";
    }


    @Autowired
    ReplaceBarcodeService replaceBarcodeService;

    //Replace Barcode GET
    //SUPERVISOR ONLY
    @RequestMapping(value = "/Change-Barcode", method = RequestMethod.GET)
    public String replaceBarcode() {
        System.out.println("Replace Barcode GET");
        if(!supervisor){
            return "redirect:/";
        }
        return "Supervisor/replaceBarcode";
    }

    //Replace Barcode POST
    //SUPERVISOR ONLY
    @RequestMapping(value = "/replaceBarcode", method = RequestMethod.POST)
    public String replaceBarcodePOST(@Valid Nwtxdt nwtxdt, BindingResult bindingResult,
                                     ModelMap model)
                                     throws ParseException {
        System.out.println("Replace Barcode POST");
        if(!supervisor){
            return "redirect:/";
        }
        if(bindingResult.hasErrors()){
            model.put("returnVoidError", "Invalid Credentials");
            for (Object object : bindingResult.getAllErrors()) {
                if(object instanceof FieldError) {
                    System.out.println((FieldError) object);
                }
            }
            return "Supervisor/replaceBarcode";
        }

        Nwtxdt oldNwtxdt = replaceBarcodeService.getNwtxdt(nwtxdt.getBookCode(), nwtxdt.getEditionYear(), nwtxdt.getBarcode());
        if (oldNwtxdt != null) {

            //Duplicating everything to the new model
            nwtxdt.setBookCode(oldNwtxdt.getBookCode());
            nwtxdt.setEditionYear(oldNwtxdt.getEditionYear());
            nwtxdt.setSeqNr(oldNwtxdt.getSeqNr());
            nwtxdt.setPidm(oldNwtxdt.getPidm());
            nwtxdt.setTerm(oldNwtxdt.getTerm());
            nwtxdt.setDateCheckedOut(oldNwtxdt.getDateCheckedOut());
            nwtxdt.setDisposition(oldNwtxdt.getDisposition());
            nwtxdt.setBookSalePrice(oldNwtxdt.getBookSalePrice());
            nwtxdt.setPrevPidm(oldNwtxdt.getPrevPidm());
            nwtxdt.setPrevDateCheckedIn(oldNwtxdt.getPrevDateCheckedIn());
            nwtxdt.setActivityDate(oldNwtxdt.getActivityDate());
            nwtxdt.setBillableFlag(oldNwtxdt.getBillableFlag());

            // Deletes Old Repository Item and Saves New One
            replaceBarcodeService.deleteNwtxdt(nwtxdt.getBarcode());
            replaceBarcodeService.saveNwtxdt(nwtxdt);
        }else{
            //Wasn't able to find a book off of given credentials
            model.put("returnVoidError", "No Book Found Off of Given Credentials");
        }
        return "Supervisor/replaceBarcode";
    }


    @Autowired
    QueryCourseService queryCourseService;

    //Find Course GET
    //SUPERVISOR ONLY
    @GetMapping("/Find-Course")
    public String queryCourse(){
        System.out.println("Course Query GET");
        if(!supervisor){
            return "redirect:/";
        }
        return "Supervisor/queryCourse";
    }

    //Find Course POST
    //SUPERVISOR ONLY
    @PostMapping("/Find-Course")
    public String queryCoursePost(ModelMap model, Scbcrse scbcrse,BindingResult bindingResult)
                                  throws ParseException {
        System.out.println("Course Query POST");
        if(!supervisor){
            return "redirect:/";
        }
        //Pseudo Regex
        if(bindingResult.hasErrors()){
            model.put("returnVoidError", "Invalid Credentials");
            for (Object object : bindingResult.getAllErrors()) {
                if(object instanceof FieldError) {
                    System.out.println((FieldError) object);
                }
            }
            return "Supervisor/queryCourse";
        }

        Scbcrse oldScbcrse = queryCourseService.getScbcrse(scbcrse.getSubjCode());
        if (oldScbcrse != null) {
            model.put("crseTable", oldScbcrse);
        }else{
            //Wasn't able to find a book off of given credentials
            model.put("returnVoidError", "No Courses Found Off of Given Credentials");
        }
        return "Supervisor/queryCourse";
    }


    @Autowired
    CourseMessageService courseMessageService;

    //Course Message GET
    //SUPERVISOR ONLY
    @RequestMapping(value = "/Course-Message", method = RequestMethod.GET)
    public String courseMessage(){
        System.out.println("Course Message GET");
        if(!supervisor){
            return "redirect:/";
        }
        return "Supervisor/courseMessage";
    }

    //Course Message POST
    //SUPERVISOR ONLY
    @RequestMapping(value = "/Course-Message", method = RequestMethod.POST)
    public String courseMessagePost(ModelMap model, Nwtxcm nwtxcm, BindingResult bindingResult)
                                    throws ParseException{
        System.out.println("Course Message POST");
        if(!supervisor){
            return "redirect:/";
        }
        //Pseudo Regex
        if(bindingResult.hasErrors()){
            model.put("returnVoidError", "Invalid Credentials");
            for (Object object : bindingResult.getAllErrors()) {
                if(object instanceof FieldError) {
                    System.out.println((FieldError) object);
                }
            }
            return "Supervisor/courseMessage";
        }

        Nwtxcm oldNwtxcm = courseMessageService.getNwtxcm(nwtxcm.getCmCourse());
        if (oldNwtxcm != null) {
            model.put("courseMessage", oldNwtxcm.getCmMessage());
        }else{
            //Wasn't able to find a course off of given credentials
            model.put("returnVoidError", "No Course Found Off of Given Credentials");
        }
        return "Supervisor/courseMessage";
    }

    //Course Message CLEAR
    //SUPERVISOR ONLY
    @RequestMapping(value = "/Course-Message", method = RequestMethod.POST, params="clear")
    public String courseMessageClear(ModelMap model, Nwtxcm nwtxcm, BindingResult bindingResult)
                                     throws ParseException {
        System.out.println("Course Message POST - CLEAR");
        if(!supervisor){
            return "redirect:/";
        }
        //Pseudo Regex
        if(bindingResult.hasErrors()){
            model.put("returnVoidError", "Invalid Credentials");
            for (Object object : bindingResult.getAllErrors()) {
                if(object instanceof FieldError) {
                    System.out.println((FieldError) object);
                }
            }
            return "Supervisor/courseMessage";
        }

        Nwtxcm oldNwtxcm = courseMessageService.getNwtxcm(nwtxcm.getCmCourse());
        if (oldNwtxcm != null) {
            oldNwtxcm.setCmMessage("");
            courseMessageService.saveNwtxcm(oldNwtxcm);
            model.put("courseMessage", "Cleared!");
        }else{
            //Wasn't able to find a course off of given credentials
            model.put("returnVoidError", "No Course Found Off of Given Credentials");
        }
        return "Supervisor/courseMessage";
    }

    @Autowired
    ChangeBookcodeService changeBookcodeService;

    //Change Book Code GET
    //SUPERVISOR ONLY
    @RequestMapping(value= "/Change-Book-Code", method = RequestMethod.GET)
    public String changeBookCode(){
        System.out.println("Course Message GET");
        if(!supervisor){
            return "redirect:/";
        }
        return "Supervisor/changeBookCode";
    }

    //Change Book Code POST
    //SUPERVISOR ONLY
    @RequestMapping(value= "/Book-Code-Change", method = RequestMethod.POST)
    public String changeBookCodePost(ModelMap model, Nwtxin nwtxin, BindingResult bindingResult)
                                     throws ParseException{
        System.out.println("Course Message POST");
        if(!supervisor){
            return "redirect:/";
        }
        //Pseudo Regex
        if(bindingResult.hasErrors()){
            model.put("returnVoidError", "Invalid Credentials");
            for (Object object : bindingResult.getAllErrors()) {
                if(object instanceof FieldError) {
                    System.out.println((FieldError) object);
                }
            }
            return "Supervisor/queryCourse";
        }

        Nwtxin oldNwtxin = changeBookcodeService.getNwtxin(nwtxin.getBookCode(), nwtxin.getEditionYear());
        if(oldNwtxin != null){

            //Duplicating everything to the new model
            //oldNwtxin.setBookCode(newBookCode); //<-- This is the changed Item
            //oldNwtxin.setEditionYear(newEditionYear); //<-- This is the changed Item

            //changeBookcodeService.deleteNwtxin(bookCode);
            changeBookcodeService.saveNwtxin(oldNwtxin);
        }
        return "Supervisor/changeBookCode";
    }


    @Autowired
    PreviousBooksService previousBooksService;

    //Previous Books GET
    @RequestMapping(value= "/Previous-Books", method = RequestMethod.GET)
    public String prevBoooks(){
        System.out.println("Previous Books GET");
        if(supervisor){
            return"Supervisor/previousBooks";
        } else if(studentEmployee){
            return "StudentEmployee/previousBooks";
        } else {
            return "redirect:/";
        }
    }

    //Previoius Books POST
    @RequestMapping(value= "/Previous-Books", method = RequestMethod.POST)
    public String prevBooksPost(ModelMap model,
                                @RequestParam(value = "id", required = false, defaultValue = "")String id,
                                @RequestParam(value = "prevTerm", required = false, defaultValue = "")String prevTerm)
                                throws ParseException{
        System.out.println("Previous Books POST");
        //Pseudo Regex
        if(id.equals("") || prevTerm.equals("")){
            System.out.println("nah dawg");
            //Supervisor Privileges
            if(supervisor){
                return"Supervisor/previousBooks";
            }
            return "StudentEmployee/previousBooks";
        }

        Spriden spriden = previousBooksService.getSpriden(id);
        Stvterm stvterm = previousBooksService.getStvterm(prevTerm);
        if(spriden != null && stvterm != null){
            model.put("prevBooks",previousBooksService.getNwtxdt(spriden.getPidm(), prevTerm));
        }
        //Supervisor Privileges
        if(supervisor){
            return"Supervisor/previousBooks";
        }
        return "StudentEmployee/previousBooks";
    }


    @Autowired
    SoldBooksService soldBooksService;

    //Sold Books GET
    @RequestMapping(value= "/Sold-Books", method = RequestMethod.GET)
    public String soldBooks(){
        System.out.println("Sold Books GET");
        if(supervisor){
            return"Supervisor/soldBooks";
        } else if(studentEmployee){
            return "StudentEmployee/soldBooks";
        } else {
            return "redirect:/";
        }
    }

    //Sold Books POST
    @RequestMapping(value= "/Sold-Books", method = RequestMethod.POST)
    public String soldBooksPost(ModelMap model,
                                @RequestParam(value = "id", required = false, defaultValue = "")String id){
        System.out.println("Sold Books Post");
        //Pseudo Regex
        if(id.equals("")){
            //Supervisor Privileges
            if(supervisor){
                return"Supervisor/soldBooks";
            }
            return "StudentEmployee/soldBooks";
        }

        Spriden spriden = soldBooksService.getSpriden(id);
        if(spriden != null){
            model.put("soldBooks",soldBooksService.getNwtxdt(spriden.getPidm()));
        }
        //Supervisor Privileges
        if(supervisor){
            return"Supervisor/soldBooks";
        }
        return "StudentEmployee/soldBooks";
    }


    @Autowired
    StudentScheduleService studentScheduleService;

    //Student Schedule GET
    @RequestMapping(value= "/Student-Schedule", method = RequestMethod.GET)
    public String studentSchedule(){
        System.out.println("Student Schedule GET");
        if(supervisor){
            return"Supervisor/studentSchedule";
        } else if(studentEmployee){
            return "StudentEmployee/studentSchedule";
        } else {
            return "redirect:/";
        }
    }

    //Student Schedule POST
    @RequestMapping(value= "/Student-Schedule", method = RequestMethod.POST)
    public String studentSchedulePost(ModelMap model,
                                      @RequestParam(value = "termSeason", required = false, defaultValue = "")String termSeason,
                                      @RequestParam(value = "id", required = false, defaultValue = "")String id)
                                      throws ParseException{
        System.out.println("Student Schedule POST");
        if(!supervisor && !studentEmployee){
            return "redirect:/";
        }
        //Pseudo Regex
        if(termSeason.equals("") || id.equals("")){
            System.out.println("nah dawg");
            //Supervisor Privileges
            if(supervisor){
                return"Supervisor/studentSchedule";
            }
            return "StudentEmployee/studentSchedule";
        }

        studentScheduleService.getStvterm(termSeason).getDesc();
        if(studentScheduleService.getSpriden(id).getPidm().equals("")){
            System.out.println("pidm empty");
            //Supervisor Privileges
            if(supervisor){
                return"Supervisor/studentSchedule";
            }
            return "StudentEmployee/studentSchedule";
        }
        int pidm = Integer.parseInt(studentScheduleService.getSpriden(id).getPidm());

        List<Sfrstcr> sfrstcr = studentScheduleService.getSfrstcr(pidm, termSeason);
        List<Ssbsect> output = new ArrayList<>();
        List<String> outputTitle = new ArrayList<>();
        List<Ssrmeet> outputTimes = new ArrayList<>();
        for(Sfrstcr item: sfrstcr){
            Ssbsect ssbsect = studentScheduleService.getSsbsect(item.getCrn(), item.getTermCode());
            output.add(ssbsect);
            Scbcrse scbcrse = studentScheduleService.getScbcrse(ssbsect.getSubjCode(),ssbsect.getCrseNumb());
            if(scbcrse != null){
                System.out.println("there are titles!");
                outputTitle.add(scbcrse.getTitle());
            }
            outputTimes.add(studentScheduleService.getSsrmeet(termSeason,item.getCrn()));
        }
        model.put("output", output);
        model.put("outputTitle", outputTitle);
        model.put("outputTimes", outputTimes);
        //Supervisor Privileges
        if(supervisor){
            return"Supervisor/studentSchedule";
        }
        return "StudentEmployee/studentSchedule";
    }
}