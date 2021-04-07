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
import org.springframework.web.bind.annotation.*;


//Imported Services
import com.webapp.TextBook.Service.AddBookService;
import com.webapp.TextBook.Service.BookQueryService;


//Imported Models
import com.webapp.TextBook.Model.Nwtxdt;


@Controller
public class HomeController {

    @RequestMapping("/")
    public String login() {
        return "login";
    }

    //Just temporary login things for now. Intended to be replaced later!
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String loginPost(@RequestParam(value = "login", required = false, defaultValue = "") String login,
                            @RequestParam(value = "password", required = false, defaultValue = "") String password)
            throws ParseException {
        if (login.equals("admin") && password.equals("admin")) {
            return "supervisorView";
        } else {
            return "maintenanceFormView";
        }
    }

    @RequestMapping("/supervisorView")
    public String supervisor() {
        return "supervisorView";
    }

    @RequestMapping("/maintenanceFormView")
    public String maintenance() {
        return "maintenanceFormView";
    }

    @Autowired
    AddBookService addBookService;
    @RequestMapping(value = "/addBook", method = RequestMethod.GET)
    public String addBookPage(ModelMap model) {
        System.out.println("Add Book GET");
        return "addBook";
    }

    @RequestMapping(value = "/addBook", method = RequestMethod.POST)
    public String addBookPOST(ModelMap model,
                              @RequestParam(value = "bookCode", required = false, defaultValue = "") String bookCode,
                              @RequestParam(value = "editionYear", required = false, defaultValue = "") String editionYear,
                              @RequestParam(value = "bookTitle", required = false, defaultValue = "") String bookTitle,
                              @RequestParam(value = "barcode", required = false, defaultValue = "") String barcode,
                              @RequestParam(value = "seqNr", required = false, defaultValue = "") String seqNr)
                              throws ParseException {
        System.out.println("Add Book POST");
        //Pseudo Regex
        if (bookCode.equals("") || editionYear.equals("") || bookTitle.equals("") || barcode.equals("") || seqNr.equals("")) {
            model.put("returnVoidError", "Invalid Credentials");
            return "addBook";
        }

        //Model for Nwtxdt Changing
        Nwtxdt nwtxdt = new Nwtxdt();
        nwtxdt.setBarcode(barcode);
        nwtxdt.setEditionYear(editionYear);
        nwtxdt.setBookCode(bookCode);
        nwtxdt.setSeqNr(seqNr);

        //Add Book Service - Save
        addBookService.saveNwtxdt(nwtxdt);
        return "addBook";
    }


    @Autowired
    BookQueryService bookQueryService;

    @RequestMapping(value = "/bookQuery", method = RequestMethod.GET)
    public String bookQuery(ModelMap model) {
        System.out.println("Book Query GET");
        return "bookQuery";
    }

    @RequestMapping(value = "/bookQuery", method = RequestMethod.POST)
    public String bookQueryPost(ModelMap model,
                                @RequestParam(value = "bookCode", required = true, defaultValue = "") String bookCode,
                                @RequestParam(value = "editionYear", required = false, defaultValue = "") String editionYear,
                                @RequestParam(value = "barcode", required = false, defaultValue = "") String barcode)
                                throws ParseException {
        System.out.println("Book Query POST");
        //Pseudo Regex
        if (bookCode.equals("") || editionYear.equals("") || barcode.equals("")) {
            model.put("returnVoidError", "Invalid Credentials");
            return "bookQuery";
        }

        Nwtxdt nwtxdt = bookQueryService.getNwtxdt(bookCode, editionYear, barcode);
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

            return "bookQuery";
        } else {
            //Wasn't able to find a book off of given credentials
            model.put("returnVoidError", "No Book Found Off of Given Credentials");
        }
        return "bookQuery";
    }

    @Autowired
    BookDispositionService bookDispositionService;
    @RequestMapping(value = "/bookDisposition", method = RequestMethod.GET)
    public String bookDisposition() {
        System.out.println("Book Dispostion GET");
        return "bookDisposition";
    }

    @RequestMapping(value = "/bookDisposition", method = RequestMethod.POST)
    public String bookDispositionPost(ModelMap model,
                                      @RequestParam(value = "bookCode", required = true, defaultValue = "") String bookCode,
                                      @RequestParam(value = "editionYear", required = false, defaultValue = "") String editionYear,
                                      @RequestParam(value = "barcode", required = false, defaultValue = "") String barcode,
                                      @RequestParam(value = "bookDisposition", required = false, defaultValue = "") String bookDisposition)
                                      throws ParseException {
        System.out.println("Book Disposition POST");
        //Pseudo Regex
        if (bookCode.equals("") || editionYear.equals("") || barcode.equals("") || bookDisposition.equals("")) {
            model.put("returnVoidError", "Invalid Credentials");
            return "bookDisposition";
        }

        Nwtxdt nwtxdt = bookDispositionService.getNwtxdt(bookCode, editionYear, barcode);
        nwtxdt.setDisposition(bookDisposition);
        bookDispositionService.setNwtxdt(nwtxdt);
        return "bookDisposition";
    }

    @Autowired
    ReplaceBarcodeService replaceBarcodeService;

    @RequestMapping(value = "/replaceBarcode", method = RequestMethod.GET)
    public String replaceBarcode(ModelMap model) {
        System.out.println("Replace Barcode GET");

        return "replaceBarcode";
    }


    @RequestMapping(value = "/replaceBarcode", method = RequestMethod.POST)
    public String replaceBarcodePOST(ModelMap model,
                                     @RequestParam(value = "bookCode", required = false, defaultValue = "") String bookCode,
                                     @RequestParam(value = "bookYear", required = false, defaultValue = "") String editionYear,
                                     @RequestParam(value = "barcode", required = false, defaultValue = "") String barcode,
                                     @RequestParam(value = "newBarcode", required = false, defaultValue = "") String newBarcode,
                                     @RequestParam(value = "bookTitle", required = false, defaultValue = "") String bookTitle)
            throws ParseException {
        System.out.println("Replace Barcode POST");
        if (bookCode.equals("") || editionYear.equals("") || barcode.equals("") || newBarcode.equals("")) {
            model.put("returnVoidError", "Invalid Credentials");
            System.out.println("U missed a line!");
            return "replaceBarcode";
        }
        /*if (barcode.equals("")) {
            model.put("returnVoidError", "Invalid Credentials");
            System.out.println("U missed a line!");
            return "replaceBarcode";
        }*/
        if (replaceBarcodeService.getNwtxdt(bookCode, editionYear, barcode) != null) {
            Nwtxdt oldNwtxdt = replaceBarcodeService.getNwtxdt(bookCode, editionYear, barcode);
            Nwtxdt nwtxdt = new Nwtxdt();

            nwtxdt.setBookCode(oldNwtxdt.getBookCode());
            nwtxdt.setEditionYear(oldNwtxdt.getEditionYear());
            nwtxdt.setSeqNr(oldNwtxdt.getSeqNr());
            nwtxdt.setBarcode(newBarcode);
            nwtxdt.setPidm(oldNwtxdt.getPidm());
            nwtxdt.setTerm(oldNwtxdt.getTerm());
            nwtxdt.setDateCheckedOut(oldNwtxdt.getDateCheckedOut());
            nwtxdt.setDisposition(oldNwtxdt.getDisposition());
            nwtxdt.setBookSalePrice(oldNwtxdt.getBookSalePrice());
            nwtxdt.setPrevPidm(oldNwtxdt.getPrevPidm());
            nwtxdt.setPrevDateCheckedIn(oldNwtxdt.getPrevDateCheckedIn());
            nwtxdt.setActivityDate(oldNwtxdt.getActivityDate());
            nwtxdt.setBillableFlag(oldNwtxdt.getBillableFlag());

            replaceBarcodeService.deleteNwtxdt(barcode);
            replaceBarcodeService.saveNwtxdt(nwtxdt);
        }
        /*if (replaceBarcodeService.getNwtxin(bookCode, editionYear, bookTitle) != null) {
            Nwtxin nwtxin = replaceBarcodeService.getNwtxin(bookCode, editionYear, bookTitle);
            model.put("bookTitle",replaceBarcodeService.getNwtxin(nwtxin.getBookCode(), nwtxin.getEditionYear(), nwtxin.getTitle()).getTitle());
        }*/
        return "replaceBarcode";
    }


    @Autowired
    QueryCourseService queryCourseService;

    @RequestMapping(value = "/queryCourse", method = RequestMethod.GET)
    public String queryCourse(ModelMap model) {
        System.out.println("Course Query GET");
        return "queryCourse";
    }

    @RequestMapping(value = "/queryCourse", method = RequestMethod.POST)
    public String queryCoursePost(ModelMap model,
                                  @RequestParam(value = "courseCode", required = false, defaultValue = "") String courseCode)
            throws ParseException {
        System.out.println("Course Query POST");
        Scbcrse scbcrse = new Scbcrse();
        if (courseCode.equals("")) {
            model.put("returnVoidError", "Invalid Credentials");
            return "queryCourse";
        }
        scbcrse.setCrseNumb(courseCode);

        System.out.println("Querying off of: " + scbcrse.getCrseNumb());
        if (queryCourseService.getScbcrse(courseCode) != null) {
            model.put("crseTable", queryCourseService.getScbcrse(courseCode));

        } else {
            model.put("returnVoidError", "No Book Found Off of Given Credentials");
        }
        return "queryCourse";
    }

    @Autowired
    CourseMessageService courseMessageService;

    @RequestMapping(value = "/courseMessage", method = RequestMethod.GET)
    public String courseMessage() {
        System.out.println("Course Message GET");
        return "courseMessage";
    }

    @RequestMapping(value = "/courseMessage", method = RequestMethod.POST)
    public String courseMessagePost(ModelMap model,
                                    @RequestParam(value = "courseId", required = false, defaultValue = "") String courseId)
            throws ParseException {
        System.out.println("Course Message POST");
        if (courseId.equals("")) {
            model.put("returnVoidError", "Invalid Credentials");
            return "courseMessage";
        }
        System.out.println("Querying off of: " + courseId);
        if (courseMessageService.getNwtxcm(courseId) != null) {
            model.put("courseMessage", courseMessageService.getNwtxcm((courseId)).getCmMessage());

        } else {
            model.put("returnVoidError", "No Book Found Off of Given Credentials");
        }
        return "courseMessage";
    }

    @RequestMapping(value = "/courseMessage", method = RequestMethod.POST, params = "clear")
    public String courseMessageClear(ModelMap model,
                                     @RequestParam(value = "courseId", required = false, defaultValue = "") String courseId)
            throws ParseException {
        System.out.println("Course Message POST - CLEAR");
        if (courseId.equals("")) {
            model.put("returnVoidError", "Invalid Credentials");
            return "courseMessage";
        }
        System.out.println("Querying off of: " + courseId);
        if (courseMessageService.getNwtxcm(courseId) != null) {
            Nwtxcm nwtxcm = new Nwtxcm();
            nwtxcm = courseMessageService.getNwtxcm(courseId);
            nwtxcm.setCmMessage("");
            model.put("courseMessage", "Cleared!");
            courseMessageService.saveNwtxcm(nwtxcm);
        }

        return "courseMessage";
    }



    @Autowired
    ChangeBookcodeService changeBookcodeService;
    @RequestMapping(value= "/changeBookCode", method = RequestMethod.GET)
    public String changeBookCode(){
        System.out.println("Course Message GET");

        return "changeBookCode";
    }

    @RequestMapping(value= "/changeBookCode", method = RequestMethod.POST)
    public String changeBookCodePost(ModelMap model,
                                     @RequestParam(value = "bookCode", required = false, defaultValue = "")String bookCode,
                                     @RequestParam(value = "editionYear", required = false, defaultValue = "")String editionYear,
                                     @RequestParam(value = "newBookCode", required = false, defaultValue = "")String newBookCode,
                                     @RequestParam(value = "newEditionYear", required = false, defaultValue = "")String newEditionYear)
                                     throws ParseException{
        System.out.println("Course Message POST");
        if(bookCode.equals("") || editionYear.equals("") || newBookCode.equals("") || newEditionYear.equals("")){
            model.put("returnVoidError", "Invalid Credentials");
            System.out.println("it don't work");
            return "changeBookCode";
        }

        if(changeBookcodeService.getNwtxin(bookCode, editionYear) != null){
            //Nwtxin Creation of new and old versions
            Nwtxin oldNwtxin = changeBookcodeService.getNwtxin(bookCode, editionYear);
            Nwtxin nwtxin = new Nwtxin();

            //Setting all
            nwtxin.setBookCode(newBookCode);
            nwtxin.setEditionYear(newEditionYear);
            nwtxin.setTitle(oldNwtxin.getTitle());
            nwtxin.setAuthor(oldNwtxin.getAuthor());
            nwtxin.setPublisher(oldNwtxin.getPublisher());
            nwtxin.setBookStatus(oldNwtxin.getBookStatus());
            nwtxin.setCurrentPrice(oldNwtxin.getCurrentPrice());
            nwtxin.setIsbn(oldNwtxin.getIsbn());
            nwtxin.setPruchaseDate(oldNwtxin.getPruchaseDate());
            nwtxin.setFirstUsedDate(oldNwtxin.getFirstUsedDate());
            nwtxin.setDiscontinuedDate(oldNwtxin.getDiscontinuedDate());
            nwtxin.setActivityDate(oldNwtxin.getActivityDate());
            nwtxin.setCrseName(oldNwtxin.getCrseName());
            nwtxin.setCrse1(oldNwtxin.getCrse1());
            nwtxin.setCrse2(oldNwtxin.getCrse2());
            nwtxin.setCrse3(oldNwtxin.getCrse3());
            nwtxin.setCrse4(oldNwtxin.getCrse4());
            nwtxin.setCrse5(oldNwtxin.getCrse5());
            nwtxin.setComment(oldNwtxin.getComment());

            changeBookcodeService.deleteNwtxin(bookCode);
            changeBookcodeService.saveNwtxin(nwtxin);
        }
        return "changeBookCode";
    }


    @Autowired
    PreviousBooksService previousBooksService;
    @RequestMapping(value= "/Previous-Books", method = RequestMethod.GET)
    public String prevBoooks(){
        System.out.println("Previous Books GET");
        return "patronPrevBooks";
    }

    @RequestMapping(value= "/Previous-Books", method = RequestMethod.POST)
    public String prevBooksPost(ModelMap model,
                                @RequestParam(value = "id", required = false, defaultValue = "")String id,
                                @RequestParam(value = "prevTerm", required = false, defaultValue = "")String prevTerm)
                                throws ParseException{
        System.out.println("Previous Books POST");
        if(id.equals("") || prevTerm.equals("")){
            System.out.println("nah dawg");
            return "patronPrevBooks";
        }

        if(previousBooksService.getSpriden(id) != null && previousBooksService.getStvterm(prevTerm) != null){
            Spriden spriden = previousBooksService.getSpriden(id);
            System.out.println(spriden.getFirstName() + " " + spriden.getMiddleInitial() + " " + spriden.getLastName());
            previousBooksService.getNwtxdt(spriden.getPidm(), prevTerm);
            model.put("prevBooks",previousBooksService.getNwtxdt(spriden.getPidm(), prevTerm));
        }

        return "patronPrevBooks";
    }

    @Autowired
    SoldBooksService soldBooksService;
    @RequestMapping(value= "/Sold-Books", method = RequestMethod.GET)
    public String soldBooks(){
        System.out.println("Sold Books GET");
        return "patronSoldBooks";
    }

    @RequestMapping(value= "/Sold-Books", method = RequestMethod.POST)
    public String soldBooksPost(ModelMap model,
                                @RequestParam(value = "id", required = false, defaultValue = "")String id){
        System.out.println("Sold Books Post");
        if(id.equals("")){
            return "patronSoldBooks";
        }

        if(soldBooksService.getSpriden(id) != null){
            Spriden spriden = soldBooksService.getSpriden(id);
            System.out.println(spriden.getPidm());
            if(soldBooksService.getNwtxdt(spriden.getPidm()) != null){

            }
            model.put("soldBooks",soldBooksService.getNwtxdt(spriden.getPidm()));
        }

        return "patronSoldBooks";
    }

    @Autowired
    StudentScheduleService studentScheduleService;
    @RequestMapping(value= "/Student-Schedule", method = RequestMethod.GET)
    public String studentSchedule(){
        System.out.println("Student Schedule GET");
        return "patronSchedule";
    }

    @RequestMapping(value= "/Student-Schedule", method = RequestMethod.POST)
    public String studentSchedulePost(ModelMap model,
                                      @RequestParam(value = "termSeason", required = false, defaultValue = "")String termSeason,
                                      @RequestParam(value = "id", required = false, defaultValue = "")String id)
                                      throws ParseException{
        System.out.println("Student Schedule POST");
        if(termSeason.equals("") || id.equals("")){
            System.out.println("nah dawg");
            return "patronSchedule";
        }

        studentScheduleService.getStvterm(termSeason).getDesc();
        System.out.println("Checkpoint 1");
        if(studentScheduleService.getSpriden(id).getPidm().equals("")){
            System.out.println("pidm empty");
            return "patronSchedule";
        }
        int pidm = Integer.parseInt(studentScheduleService.getSpriden(id).getPidm());

        List<Sfrstcr> sfrstcr = studentScheduleService.getSfrstcr(pidm, termSeason);
        List<Ssbsect> output = new ArrayList<>();
        List<String> outputTitle = new ArrayList<>();
        List<Ssrmeet> outputTimes = new ArrayList<>();
        for(Sfrstcr item: sfrstcr){
            Ssbsect ssbsect = studentScheduleService.getSsbsect(item.getCrn(), item.getTermCode());
            output.add(ssbsect);
            String courseSubject = ssbsect.getSubjCode();
            String courseNumber = ssbsect.getCrseNumb();
            if(studentScheduleService.getScbcrse(ssbsect.getSubjCode(),ssbsect.getCrseNumb()) != null){
                System.out.println("there are titles!");
                outputTitle.add(studentScheduleService.getScbcrse(ssbsect.getSubjCode(),ssbsect.getCrseNumb()).getTitle());
            }

            outputTimes.add(studentScheduleService.getSsrmeet(termSeason,item.getCrn()));
        }
        model.put("output", output);
        model.put("outputTitle", outputTitle);
        model.put("outputTimes", outputTimes);


        return "patronSchedule";
    }

}