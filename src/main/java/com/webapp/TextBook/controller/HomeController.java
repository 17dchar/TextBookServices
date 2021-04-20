package com.webapp.TextBook.controller;

//Imported Standard Java Libraries
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


import com.webapp.TextBook.Model.*;
import com.webapp.TextBook.Service.*;

//Imported Spring Libraries
import com.webapp.TextBook.Model.Nwtxin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;


//Imported Services


//Imported Models

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
        } else if (login.equals("student") && password.equals("student")){
            studentEmployee = true;
            supervisor = false;
            return "StudentEmployee/studentEmployeeView";
        } else{
            return "login";
        }
    }

    //Supervisor Home
    //SUPERVISOR ACCESS ONLY
    @RequestMapping("/Supervisor-Home")
    public String supervisor(){
        if(!supervisor){
            return "redirect:/";
        }
        return "Supervisor/supervisorView";
    }


    @Autowired
    MaintenanceService maintenanceService;

    //Maintenance-Form GET
    //SUPERVISOR ACCESS ONLY
    @GetMapping("/Maintenance-Form")
    public String maintenance(){
        System.out.println("Maintenance GET");
        if(!supervisor){
            return "redirect:/";
        }
        return "Supervisor/maintenanceFormView";
    }

    //Maintenance POST
    //SUPERVISOR ACCESS ONLY
    @PostMapping("/Maintenance-Form")
    public String maintenancePost(@Valid @ModelAttribute Nwtxin nwtxin, BindingResult bindingResult,
                                  ModelMap model){
        System.out.println("Maintenance POST");
        if(!supervisor){
            return "redirect:/";
        }
        //If There Are Errors Compared To The Model, Then It Will Return Invalid Credentials
        if(bindingResult.hasErrors()){
            model.put("returnVoidError", "Invalid Credentials");
            for (ObjectError object : bindingResult.getAllErrors()) {
                //check for specific errors
            }
        }
        List<Nwtxin> nwtxinList = maintenanceService.getNwtxinList(nwtxin.getBookCode());
        if(nwtxinList != null){
            //Get stats of book with most recent edition year
            int mostRecent = 0;
            for(int i = 0; i < nwtxinList.size(); i++){
                if(i > 0){
                    System.out.println(nwtxinList.get(i).getEditionYear());
                    if(Integer.parseInt(nwtxinList.get(i).getEditionYear()) > Integer.parseInt(nwtxinList.get(i-1).getEditionYear())){
                        mostRecent= i;
                    }
                }
            }
            model.put("ok",nwtxinList.get(mostRecent));
            //Get stats of course name 1
        } else {
            //Wasn't able to find a book off of given credentials
            model.put("returnVoidError", "No Book Found Off of Given Credentials");
        }

        //If edits made, make those changes
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
    public @ResponseBody
    OutputBookInformationModel bookQueryPost(@Valid @RequestBody @ModelAttribute("inputNwtxdt") Nwtxdt nwtxdt, BindingResult bindingResult,
                                             ModelMap model)
                                             throws ParseException {
        System.out.println("Book Query POST");
        if(!supervisor){
            return null;
        }

        OutputBookInformationModel outputBookInformationModel = new OutputBookInformationModel();
        //If There Are Errors Compared To The Model, Then We'll Check for Invalid Inputs
        if(bindingResult.hasErrors()){
            for (Object object : bindingResult.getAllErrors()) {
                if(object instanceof FieldError) {
                    System.out.println((FieldError) object);
                }
            }
            if(nwtxdt.getBookCode() == "" && nwtxdt.getBarcode() == ""){
                outputBookInformationModel.setErrors(true);
                outputBookInformationModel.setErrorMessage("We Need at Least a Book Code or a Barcode");
                return outputBookInformationModel;
            }
        }
        outputBookInformationModel.setErrors(false);
        System.out.println("Passed Data Validation");
        if(nwtxdt.getBarcode() == "" && nwtxdt.getEditionYear() == ""){
            outputBookInformationModel = bookQueryService.getMostRecentNwtxdt(nwtxdt.getBookCode());
        } else if(nwtxdt.getBarcode() == "" && nwtxdt.getEditionYear() != ""){
            outputBookInformationModel = bookQueryService.getNwtxdtByBookCodeAndYear(nwtxdt.getBookCode(), nwtxdt.getEditionYear());
        }
        else if(!nwtxdt.getBarcode().equals("")){
            outputBookInformationModel = bookQueryService.getNwtxdtByBarcode(nwtxdt.getBarcode());
        } else{
            System.out.println("How did we get here?");
        }
        if(outputBookInformationModel == null){
            outputBookInformationModel = new OutputBookInformationModel();
            outputBookInformationModel.setErrors(true);
            outputBookInformationModel.setErrorMessage("We Couldn't Find a Book Off of Given Credentials");
        }
        return outputBookInformationModel;
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
    public @ResponseBody
    OutputBookInformationModel bookDispositionPost(@Valid @RequestBody @ModelAttribute("inputNwtxdt") Nwtxdt nwtxdt, BindingResult bindingResult,
                                      ModelMap model)
                                      throws ParseException {
        System.out.println("Book Disposition POST");
        if(!supervisor){
            return null;
        }
        OutputBookInformationModel outputBookInformationModel = new OutputBookInformationModel();
        Nwtxin returningNwtxin = new Nwtxin();
        if(bindingResult.hasErrors()){
            model.put("returnVoidError", "Invalid Credentials");
            for (Object object : bindingResult.getAllErrors()) {
                if(object instanceof FieldError) {
                    System.out.println((FieldError) object);
                }
            }
            if(nwtxdt.getBookCode() == "" && nwtxdt.getBarcode() == ""){
                outputBookInformationModel.setErrors(true);
                outputBookInformationModel.setErrorMessage("We Need at Least a Book Code or a Barcode");
                return outputBookInformationModel;
            }
        }
        outputBookInformationModel.setErrors(false);
        System.out.println("Passed Data Validation");
        if(nwtxdt.getBarcode() == "" && nwtxdt.getEditionYear() == ""){
            outputBookInformationModel = bookDispositionService.getMostRecentNwtxdt(nwtxdt.getBookCode());
        } else if(nwtxdt.getBarcode() == "" && nwtxdt.getEditionYear() != ""){
            outputBookInformationModel = bookDispositionService.getNwtxdtByBookCodeAndYear(nwtxdt.getBookCode(), nwtxdt.getEditionYear());
        } else if(!nwtxdt.getBarcode().equals("")){
            outputBookInformationModel = bookDispositionService.getNwtxdtByBarcode(nwtxdt.getBarcode());
        } else{
            System.out.println("How did we get here?");
        }
        if(outputBookInformationModel == null){
            outputBookInformationModel = new OutputBookInformationModel();
            outputBookInformationModel.setErrors(true);
            outputBookInformationModel.setErrorMessage("We Couldn't Find a Book Off of Given Credentials");
        }
        return outputBookInformationModel;
    }


    @Autowired
    ReplaceBarcodeService replaceBarcodeService;

    //Replace Barcode GET
    //SUPERVISOR ONLY
    @RequestMapping(value = "/Change-Barcode", method = RequestMethod.GET)
    public String replaceBarcode(ModelMap model) {
        model.addAttribute("inputNwtxin", new Nwtxin());
        System.out.println("Replace Barcode GET");
        if(!supervisor){
            return "redirect:/";
        }
        return "Supervisor/replaceBarcode";
    }

    //Replace Barcode POST
    //SUPERVISOR ONLY
    @RequestMapping(value = "/Change-Barcode", method = RequestMethod.POST)
    public @ResponseBody OutputBookInformationModel replaceBarcodePOST(@Valid @RequestBody @ModelAttribute("inputNwtxdt") Nwtxdt nwtxdt, BindingResult bindingResult,
                                     ModelMap model)
                                     throws ParseException {
        System.out.println("Replace Barcode POST");
        if(!supervisor){
            return null;
        }
        //Pseudo Regex
        OutputBookInformationModel outputBookInformationModel = new OutputBookInformationModel();
        if(bindingResult.hasErrors()){
            System.out.println("ope, there were errors");
            for (Object object : bindingResult.getAllErrors()) {
                if(object instanceof FieldError) {
                    System.out.println((FieldError) object);
                }
            }
            if(nwtxdt.getBookCode() == "" && nwtxdt.getBarcode() == ""){
                outputBookInformationModel.setErrors(true);
                outputBookInformationModel.setErrorMessage("We Need at Least a Book Code or a Barcode");
                return outputBookInformationModel;
            }
        }
        outputBookInformationModel.setErrors(false);
        System.out.println("Passed Data Validation");
        if(nwtxdt.getBarcode() == "" && nwtxdt.getEditionYear() == ""){
            outputBookInformationModel = bookDispositionService.getMostRecentNwtxdt(nwtxdt.getBookCode());
        } else if(nwtxdt.getBarcode() == "" && nwtxdt.getEditionYear() != ""){
            outputBookInformationModel = bookDispositionService.getNwtxdtByBookCodeAndYear(nwtxdt.getBookCode(), nwtxdt.getEditionYear());
        } else if(!nwtxdt.getBarcode().equals("")){
            outputBookInformationModel = bookDispositionService.getNwtxdtByBarcode(nwtxdt.getBarcode());
        } else{
            System.out.println("How did we get here?");
        }
        if(outputBookInformationModel == null){
            outputBookInformationModel = new OutputBookInformationModel();
            outputBookInformationModel.setErrors(true);
            outputBookInformationModel.setErrorMessage("We Couldn't Find a Book Off of Given Credentials");
        }
        return outputBookInformationModel;
    }


    @Autowired
    QueryCourseService queryCourseService;

    //Find Course GET
    //SUPERVISOR ONLY
    @GetMapping("/Find-Course")
    public String queryCourse(ModelMap model){
        model.addAttribute("inputNwtxin", new Nwtxin());
        System.out.println("Course Query GET");
        if(!supervisor){
            return "redirect:/";
        }
        return "Supervisor/queryCourse";
    }

    //Find Course POST
    //SUPERVISOR ONLY
    @PostMapping("/Find-Course")
    public @ResponseBody List<Nwtxin> queryCoursePost(ModelMap model, @Valid @RequestBody @ModelAttribute("inputNwtxin")  Nwtxin nwtxin, BindingResult bindingResult)
                                  throws ParseException {
        System.out.println("Course Query POST");
        if(!supervisor){
            return null;
        }
        //Pseudo Regex

        List<Nwtxin> stringList = new ArrayList<Nwtxin>();
        if(bindingResult.hasErrors()){
            model.put("returnVoidError", "Invalid Credentials");
            for (Object object : bindingResult.getAllErrors()) {
                if(object instanceof FieldError) {
                    System.out.println((FieldError) object);
                }if(nwtxin.getBookCode().length() != 8){
                    //stringList.add("These Are All 8 Characters Long");
                    return stringList;
                }
            }
        }

        //stringList = queryCourseService.getAllCourses(nwtxin.getBookCode());
        if (stringList != null) {
            return stringList;
        }else{
            //Wasn't able to find a book off of given credentials
            //stringList.add("No Courses Were Found with Given Credentials");
            return stringList;
        }
    }


    @Autowired
    CourseMessageService courseMessageService;

    //Course Message GET
    //SUPERVISOR ONLY
    @GetMapping("/Course-Message")
    public String courseMessage(ModelMap model){
        System.out.println("Course Message GET");
        model.addAttribute("inputNwtxcm", new Nwtxcm());
        if(!supervisor){
            return "redirect:/";
        }
        return "Supervisor/courseMessage";
    }

    //Course Message POST
    //SUPERVISOR ONLY
    @PostMapping("/Course-Message")
    public @ResponseBody Nwtxcm courseMessagePost(@RequestBody @ModelAttribute("inputNwtxcm") @Valid Nwtxcm nwtxcm, BindingResult bindingResult, ModelMap model)
                                    throws ParseException{
        System.out.println("Course Message POST");
        if(!supervisor){
            return nwtxcm;
        }
        //Pseudo Regex
        if(bindingResult.hasErrors()){
            for (Object object : bindingResult.getAllErrors()) {
                if(object instanceof FieldError) {
                    System.out.println((FieldError) object);
                }
            }
            if(nwtxcm.getCourse() == ""){
                nwtxcm.setCourse("Course Cannot Be Empty (They're All 9 Characters Long)");
            } else if(nwtxcm.getCourse().length() != 9){
                nwtxcm.setCourse("Course Has to Be 9 Characters Long");
            } else if(nwtxcm.getMessage().length() > 15){
                nwtxcm.setCourse("Message is too long!");
            }
            return nwtxcm;
        }

        Nwtxcm oldNwtxcm = courseMessageService.getNwtxcm(nwtxcm.getCourse());
        System.out.println(nwtxcm.getCourse());
        if (oldNwtxcm != null) {
            if (nwtxcm.getMessage() == null) {
                nwtxcm.setMessage(oldNwtxcm.getMessage());
            } else if(!nwtxcm.getMessage().equals(oldNwtxcm.getMessage())) {
                courseMessageService.saveNwtxcm(nwtxcm);
                nwtxcm.setCourse("Message Saved!");
            }
        }else{
            //Wasn't able to find a course off of given credentials
            nwtxcm.setCourse("**No Course Found Off of Given Credentials**");
        }
        return nwtxcm;
    }

    @Autowired
    ChangeBookcodeService changeBookcodeService;

    //Change Book Code GET
    //SUPERVISOR ONLY
    @RequestMapping(value= "/Change-Book-Code", method = RequestMethod.GET)
    public String changeBookCode(ModelMap model){
        model.addAttribute("inputNwtxin", new Nwtxin());
        System.out.println("Course Message GET");
        if(!supervisor){
            return "redirect:/";
        }
        return "Supervisor/changeBookCode";
    }

    //Change Book Code POST
    //SUPERVISOR ONLY
    @RequestMapping(value= "/Change-Book-Code", method = RequestMethod.POST)
    public @ResponseBody String changeBookCodePost(@Valid @RequestBody @ModelAttribute("inputNwtxin") Nwtxin nwtxin, BindingResult bindingResult, ModelMap model)
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
            return "Supervisor/changeBookCode";
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
    public String studentSchedule(ModelMap model){
        System.out.println("Student Schedule GET");
        if(supervisor){
            model.put("term", studentScheduleService.getLatestTerms());
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
                                      @RequestParam(value = "selectedTerm", required = false, defaultValue = "")String termSeason,
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


    @Autowired
    CheckInOutService checkInOutService;

    //Check-In-Out GET
    @GetMapping("/Check-In-Out")
    public String checkInOut(ModelMap model){
        System.out.println("Check In Out GET");

        if(supervisor){
            List<Stvterm> terms = checkInOutService.getLatestTerms();
            model.put("term", terms);
            return"Supervisor/checkInOut";
        } else if(studentEmployee){
            List<Stvterm> terms = checkInOutService.getLatestTerms();
            model.put("term", terms);
            return "StudentEmployee/checkInOut";
        } else {
            return "redirect:/";
        }
    }


    //Check-In-Out POST
    @PostMapping("/Check-In-Out")
    public String checkInOutPost(ModelMap model,
                                 @RequestParam(value = "id", required = false, defaultValue = "")String id,
                                 @RequestParam(value = "barCode", required = false, defaultValue = "")String barCode,
                                 @RequestParam(value = "selectedTerm", required = false, defaultValue = "")String term){
        System.out.println("Check In Out POST");
        Spriden spriden = checkInOutService.getStudent(id);
        System.out.println(spriden.getFirstName());
        List<Stvterm> terms = checkInOutService.getLatestTerms();
        model.put("term", terms);
        //Checks the availability of the book
        //0 = Book couldn't be found by given barcode
        //1 = Book isn't currently checked out to anyone
        //2 = Book is currently checked out to someone
        //3 = Book is currently checked out to this current person
        int availability = checkInOutService.checkAvailability(barCode, spriden, term);
        if(availability == 0){
            System.out.println("nah dawg");
        } else if(availability == 1){
            System.out.println("Not checked out to anyone");
        } else if(availability == 2){
            System.out.println("book was checked out to someone");
        } else{
            System.out.println("book was checked out to this person");

        }
        model.put("barCode", barCode);
        model.put("id", id);
        model.put("selectedTerm", term);

        return "Supervisor/checkInOut";
    }
    /*
    $$When a barcode is scanned on the check in/check out screen we know the
    $$user is Checking in a book or check out a book.  If the book is checked out to the ID
    $$we will check the book in.  If the book is checked out to someone else we will check the
    $$book in for the other person and display a message.  If the book is not checked out we
    $$assume they are wanting to check the book out for the ID
    $$
    $$If employee user clicks onthe book status, nothing will happen
    $$If the supervisor clicks on the book status a new screen will appear to either sell
    $$the book or reverse charges.
     */
}