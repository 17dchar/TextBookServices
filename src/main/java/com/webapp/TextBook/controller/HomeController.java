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