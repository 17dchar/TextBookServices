package com.webapp.TextBook.controller;

import java.text.ParseException;
import java.util.List;

import com.webapp.TextBook.Model.Nwtxin;
import com.webapp.TextBook.Service.AddBookService;
import com.webapp.TextBook.Repository.NwtxdtRepository;
import com.webapp.TextBook.Model.Nwtxdt;
import com.webapp.TextBook.Service.BookDispositionService;
import com.webapp.TextBook.Service.BookQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class HomeController {


    @Autowired
    NwtxdtRepository nwtxdtRepository;


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

    @Autowired
    AddBookService addBookService;

    @RequestMapping(value="/addBook", method = RequestMethod.GET)
    public String addBookPage(ModelMap model){
        System.out.println("Add Book GET");
        return "addBook";
    }

    @RequestMapping(value="/addBook", method = RequestMethod.POST)
    public String addBookPOST(ModelMap model,
                              @RequestParam (value = "bookCode",required = false, defaultValue = "")String bookCode,
                              @RequestParam (value = "editionYear",required = false, defaultValue = "")String editionYear,
                              @RequestParam (value = "bookTitle",required = false, defaultValue = "")String bookTitle,
                              @RequestParam (value = "barcode",required = false, defaultValue = "")String barcode,
                              @RequestParam (value = "seqNr",required = false, defaultValue = "")String seqNr)
                              throws ParseException{
        System.out.println("Add Book POST");
        if(bookCode.equals("") || editionYear.equals("") || bookTitle.equals("") ||barcode.equals("") || seqNr.equals("")){
            model.put("returnVoidError", "Invalid Credentials");
            return "addBook";
        }
        Nwtxdt nwtxdt = new Nwtxdt();
        nwtxdt.setBarcode(barcode);
        nwtxdt.setEditionYear(editionYear);
        nwtxdt.setBookCode(bookCode);
        nwtxdt.setSeqNr(seqNr);

        System.out.println("Adding Book with " + nwtxdt.getBarcode() + ", " + nwtxdt.getEditionYear() + ", " + nwtxdt.getBookCode() + ", " +
                nwtxdt.getSeqNr() + " Credentials");
        addBookService.saveNwtxdt(nwtxdt);
        return "addBook";
    }


    @Autowired
    BookQueryService bookQueryService;

    @RequestMapping(value = "/bookQuery", method = RequestMethod.GET)
    public String bookQuery(ModelMap model){
        System.out.println("Book Query GET");
        return "bookQuery";
    }

    @RequestMapping(value = "/bookQuery", method = RequestMethod.POST)
    public String bookQueryPost(/*@Valid @ModelAttribute("nwtxdt") Nwtxdt nwtxdt*/ ModelMap model,
                                @RequestParam (value = "bookCode",required = true, defaultValue = "") String bookCode,
                                @RequestParam (value = "editionYear", required = false, defaultValue = "") String editionYear,
                                @RequestParam (value = "barcode", required = false, defaultValue = "") String barcode
                                /*BindingResult result*/) throws ParseException {
        System.out.println("Book Query POST");
        Nwtxdt nwtxdt = new Nwtxdt();
        if(bookCode.equals("") || editionYear.equals("") || barcode.equals("")){
            model.put("returnVoidError", "Invalid Credentials");
            return "bookQuery";
        }
        nwtxdt.setBarcode(barcode);
        nwtxdt.setEditionYear(editionYear);
        nwtxdt.setBookCode(bookCode);

        //nwtxdt = bookQueryService.getNwtxdt(nwtxdt.getBookCode(), nwtxdt.getEditionYear(),nwtxdt.getBarcode());

        System.out.println("Querying off of: " + nwtxdt.getBookCode() + ", " + nwtxdt.getEditionYear() + ", " + nwtxdt.getBarcode());
        if(bookQueryService.getNwtxdt(nwtxdt.getBookCode(), nwtxdt.getEditionYear(),nwtxdt.getBarcode()) != null){
            model.put("bookTitle",bookQueryService.getNwtxdt(nwtxdt.getBookCode(), nwtxdt.getEditionYear(),nwtxdt.getBarcode()).getBookCode());
            model.put("seqNr",bookQueryService.getNwtxdt(nwtxdt.getBookCode(), nwtxdt.getEditionYear(),nwtxdt.getBarcode()).getSeqNr());
            model.put("prevTerm", bookQueryService.getNwtxdt(nwtxdt.getBookCode(), nwtxdt.getEditionYear(),nwtxdt.getBarcode()).getPrevTerm());
            model.put("bookDisposition", bookQueryService.getNwtxdt(nwtxdt.getBookCode(), nwtxdt.getEditionYear(),nwtxdt.getBarcode()).getDisposition());
            model.put("termCheckedOut", bookQueryService.getNwtxdt(nwtxdt.getBookCode(), nwtxdt.getEditionYear(),nwtxdt.getBarcode()).getTerm());
            model.put("checkedOutTo", bookQueryService.getNwtxdt(nwtxdt.getBookCode(), nwtxdt.getEditionYear(),nwtxdt.getBarcode()).getPidm());
            model.put("dateCheckedOut", bookQueryService.getNwtxdt(nwtxdt.getBookCode(), nwtxdt.getEditionYear(),nwtxdt.getBarcode()).getDateCheckedOut());
            model.put("prevCheckedOutTo", bookQueryService.getNwtxdt(nwtxdt.getBookCode(), nwtxdt.getEditionYear(),nwtxdt.getBarcode()).getPrevPidm());
            model.put("dateCheckedIn", bookQueryService.getNwtxdt(nwtxdt.getBookCode(), nwtxdt.getEditionYear(),nwtxdt.getBarcode()).getPrevDateCheckedIn());

            return "bookQuery";
        } else {
            model.put("returnVoidError", "No Book Found Off of Given Credentials");
        }

        return "bookQuery";
    }

    @Autowired
    BookDispositionService bookDispositionService;

    @RequestMapping(value = "/bookDisposition", method = RequestMethod.GET)
    public String bookDisposition(ModelMap model){
        System.out.println("Book Disposition GET");
        return "bookDisposition";
    }

    @RequestMapping(value = "/bookQuery", method = RequestMethod.POST)
    public String bookDispositionPost(/*@Valid @ModelAttribute("nwtxdt") Nwtxdt nwtxdt*/ ModelMap model,
                                      @RequestParam (value = "bookCode",required = true, defaultValue = "") String bookCode,
                                      @RequestParam (value = "editionYear", required = false, defaultValue = "") String editionYear,
                                      @RequestParam (value = "barcode", required = false, defaultValue = "") String barcode
            /*BindingResult result*/) throws ParseException {
        System.out.println("Book Disposition POST");
        Nwtxdt nwtxdt = new Nwtxdt();
        Nwtxin nwtxin = new Nwtxin();
        if(bookCode.equals("") || editionYear.equals("") || barcode.equals("")){
            model.put("returnVoidError", "Invalid Credentials");
            return "bookDisposition";
        }
        nwtxdt.setBarcode(barcode);
        nwtxdt.setEditionYear(editionYear);
        nwtxdt.setBookCode(bookCode);
        nwtxin.setBookCode(bookCode);

        System.out.println("Querying off of: " + nwtxdt.getBookCode() + ", " + nwtxdt.getEditionYear() + ", " + nwtxdt.getBarcode());
        if(bookDispositionService.getNwtxdt(nwtxdt.getBookCode(), nwtxdt.getEditionYear(),nwtxdt.getBarcode()) != null){
            model.put("seqNr",bookDispositionService.getNwtxdt(nwtxdt.getBookCode(), nwtxdt.getEditionYear(),nwtxdt.getBarcode()).getSeqNr());
            model.put("bookDisposition", bookDispositionService.getNwtxdt(nwtxdt.getBookCode(), nwtxdt.getEditionYear(),nwtxdt.getBarcode()).getDisposition());
            model.put("bookTitle", bookDispositionService.getNwtxin(nwtxin.getBookCode()).getBookCode());

            return "bookDisposition";
        } else {
            model.put("returnVoidError", "No Book Found Off of Given Credentials");
        }

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