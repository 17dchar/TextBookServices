package com.webapp.TextBook.controller;

//Imported Standard Java Libraries
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

//Imported Spring Libraries
import com.webapp.TextBook.Model.*;
import com.webapp.TextBook.Service.*;
import com.webapp.TextBook.Model.Nwtxin;


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
    //Supervisor:
        //Login: admin
        //Password: admin
    //Student Employee:
        //Login: student
        //Password: student
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
        //If the user didn't put in supervisor credentials prior, then
        //They will be redirected to the login page. These should all be
        //taken out when security is fully implemented.
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
        //Log to prove we've gotten to this function
        System.out.println("Maintenance GET");

        //Pseudo Security
        if(!supervisor){
            return "redirect:/";
        }
        return "Supervisor/maintenanceFormView";
    }

    //Maintenance POST
    //SUPERVISOR ACCESS ONLY
    @PostMapping("/Maintenance-Form")
    public @ResponseBody OutputBookInformationModel maintenancePost(@Valid @RequestBody @ModelAttribute("inputNwtxin") Nwtxin nwtxin, BindingResult bindingResult,
                                  ModelMap model){
        //Log to prove we've gotten to this function
        System.out.println("Maintenance POST");

        //Pseudo Security
        if(!supervisor){
            return null;
        }

        //Creating a model to use and ultimately send via AJAX
        OutputBookInformationModel outputBookInformationModel = new OutputBookInformationModel();

        //Javax Data Validation
        if(bindingResult.hasErrors()){
            //Print all errors for debugging sake. Can be removed if wanted
            for (ObjectError object : bindingResult.getAllErrors()) {
                if(object instanceof FieldError) {
                    System.out.println((FieldError) object);
                }
            }
            //Checks if bookcode at least exists. If not, then we return an error
            if(nwtxin.getBookCode() == ""){
                outputBookInformationModel.setErrors(true);
                outputBookInformationModel.setErrorMessage("We Need at Least a Book Code or a Barcode");
                return outputBookInformationModel;
            }
        }
        //Passed Data Validation

        //Model created to hold data queried. Created outside of if statements, because both
        //sections of if statements need it for different purposes
        Nwtxin originalNwtxin;

        if(nwtxin.getEditionYear() != null){
            //If an edition year was entered, we'll run this

            //Model to Hold Data Queried Base off of the bookcode and edition year
            originalNwtxin = maintenanceService.getNwtxin(nwtxin.getBookCode(), nwtxin.getEditionYear());

            //If Model is Empty, Return Error
            if(originalNwtxin == null){
                outputBookInformationModel.setErrors(true);
                outputBookInformationModel.setErrorMessage("We Couldn't Find a Book With That Code");
                return outputBookInformationModel;
            }

            //If Old database query Model is Different from Information Given, then
            //There was a Change Made by the User, thus We Shall
            //Change the Original Model and Update the Database.
            //We can Only See the Message Sent to the User Here,
            //But the Logic for this is in the Method Called Here.
            if(maintenanceService.hasDifferences(nwtxin, originalNwtxin)){
                outputBookInformationModel.setErrors(true);
                outputBookInformationModel.setErrorMessage("Changes Made!");
                return outputBookInformationModel;
            }
        }else{
            //If no edition year was entered, then we look for the most recent edition year

            //Model to Hold Data Queried based off of book code. Will derive most recent edition year
            originalNwtxin = maintenanceService.getMostRecentNwtxin(nwtxin.getBookCode());

            //If Model is Empty, Return Error
            if(originalNwtxin == null){
                outputBookInformationModel.setErrors(true);
                outputBookInformationModel.setErrorMessage("We Couldn't Find a Book With That Code");
                return outputBookInformationModel;
            }

            //If Model is Different from Information Given, then
            //There was a Change Made by the User, thus We Shall
            //Change the Original Model and Update the Database.
            //We can Only See the Message Sent to the User Here,
            //But the Logic for this is in the Method Called Here.
            if(maintenanceService.hasDifferences(nwtxin, originalNwtxin)){
                outputBookInformationModel.setErrors(true);
                outputBookInformationModel.setErrorMessage("Changes Made!");
                return outputBookInformationModel;
            }
        }

        //If No Changes Have been Made to the Original Model,
        //Then we run this code, which populates data required for the maintenance page

        nwtxin = originalNwtxin;
        List<Nwtxdt> nwtxdtList = maintenanceService.getNwtxdtList(nwtxin.getBookCode(), nwtxin.getEditionYear());
        int largestSeqNr = 0;
        int booksPurchased = 0;
        int booksSold = 0;
        int booksUnrepairable = 0;
        int booksNotReturned = 0;
        int booksInInventory = 0;
        int booksCheckedIn = 0;
        int booksCheckedOut = 0;
        int count = 1;
        for(Nwtxdt nwtxdt: nwtxdtList){
            booksPurchased++;
            if(Integer.parseInt(nwtxdt.getSeqNr()) > largestSeqNr){
                largestSeqNr = Integer.parseInt(nwtxdt.getSeqNr());
            }
            if(nwtxdt.getDisposition() != null){
                if(nwtxdt.getDisposition().equals("S")){
                    booksSold++;
                }
                if(nwtxdt.getDisposition().equals("U")){
                    booksUnrepairable++;
                }
                if(nwtxdt.getDisposition().equals("N")){
                    booksNotReturned++;
                }
                if(nwtxdt.getDisposition().equals("I") || nwtxdt.getDisposition().equals("O") ){
                    booksInInventory++;
                }
                if(nwtxdt.getDisposition().equals("O")){
                    booksCheckedOut++;
                }
                if(nwtxdt.getDisposition().equals("I") ){
                    booksCheckedIn++;
                }
                count++;
            }
        }
        outputBookInformationModel.setStatus(nwtxin.getBookStatus());
        outputBookInformationModel.setBooksCheckedIn(booksCheckedIn);
        outputBookInformationModel.setBooksCheckedOut(booksCheckedOut);
        outputBookInformationModel.setBooksinInventory(booksInInventory);
        outputBookInformationModel.setBooksNonreturned(booksNotReturned);
        outputBookInformationModel.setBooksPurchased(booksPurchased);
        outputBookInformationModel.setBooksUnrepairable(booksUnrepairable);
        outputBookInformationModel.setBooksSold(booksSold);
        outputBookInformationModel.setLastSeqNr(largestSeqNr);
        outputBookInformationModel.setEditionYear(nwtxdtList.get(0).getEditionYear());
        outputBookInformationModel.setTitle(nwtxin.getTitle());
        //outputBookInformationModel.setAuthor(nwtxin.getAuthor());
        //Nwtxin inputNwtxin = maintenanceService.getNwtxin(nwtxin.getBookCode(), nwtxdtList.get(0).getEditionYear());
        outputBookInformationModel.setAuthor(nwtxin.getAuthor());
        outputBookInformationModel.setPublisher(nwtxin.getPublisher());
        outputBookInformationModel.setIsbn(nwtxin.getIsbn());

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        outputBookInformationModel.setPurchaseDate(df.format(nwtxin.getPruchaseDate()));
        if(nwtxin.getDiscontinuedDate() != null){
            outputBookInformationModel.setDiscontinued(df.format(nwtxin.getDiscontinuedDate()));
        }
        if(nwtxin.getFirstUsedDate() != null){
            outputBookInformationModel.setFirstDateUsed(df.format(nwtxin.getFirstUsedDate()));
        }
        outputBookInformationModel.setPrice(nwtxin.getCurrentPrice());
        outputBookInformationModel.setCourseTitle(nwtxin.getCrseName());
        outputBookInformationModel.setCourse1(nwtxin.getCrse1());
        outputBookInformationModel.setCourse2(nwtxin.getCrse2());
        outputBookInformationModel.setCourse3(nwtxin.getCrse3());
        outputBookInformationModel.setCourse4(nwtxin.getCrse4());
        outputBookInformationModel.setCourse5(nwtxin.getCrse5());
        return outputBookInformationModel;
    }


    @Autowired
    AddBookService addBookService;

    //Add-Book GET
    //SUPERVISOR ACCESS ONLY
    @GetMapping("/Add-Book")
    public String addBookPage(){
        //Log to prove we've gotten to this function
        System.out.println("Add Book GET");

        //Pseudo Security
        if(!supervisor){
            return "redirect:/";
        }
        return "Supervisor/addBook";
    }

    //Add-Book POST
    //SUPERVISOR ACCESS ONLY
    @PostMapping("/Add-Book")
    public @ResponseBody OutputBookInformationModel addBookPOST(@Valid @RequestBody @ModelAttribute("inputNwtxdt") Nwtxdt nwtxdt, BindingResult bindingResult,
                              ModelMap model) throws ParseException {
        //Log to prove we've gotten to this function
        System.out.println("Add Book POST");

        //Pseudo Security
        if(!supervisor){
            return null;
        }

        //Creating a model to use and ultimately send via AJAX
        OutputBookInformationModel outputBookInformationModel = new OutputBookInformationModel();

        //Javax Data Validation
        if(bindingResult.hasErrors()){
            //Print all errors for debugging sake. Can be removed if wanted
            for (Object object : bindingResult.getAllErrors()) {
                if(object instanceof FieldError) {
                    System.out.println((FieldError) object);
                }
            }
            //Checks if bookcode at least exists. If not, then we return an error
            if(nwtxdt.getBookCode() == ""){
                outputBookInformationModel.setErrors(true);
                outputBookInformationModel.setErrorMessage("We Need at Least a Book Code or a Barcode");
                return outputBookInformationModel;
            }
        }
        outputBookInformationModel.setErrors(false);
        //Passed Data Validation

        if(nwtxdt.getEditionYear() == ""){
            //If No Edition Year, Then we Look for Most Recent Edition Year
            outputBookInformationModel = addBookService.getMostRecentNwtxdt(nwtxdt.getBookCode());
        } else if(nwtxdt.getEditionYear() != ""){
            //If There Is Edition Year, Then Query off of Both Bookcode and Edition Year
            outputBookInformationModel = addBookService.getNwtxdtByBookCodeAndYear(nwtxdt.getBookCode(), nwtxdt.getEditionYear());
        }else{
            //If this Function Gets Called, Email Zachariah Durbin, Because That should NEVER Happen
            System.out.println("How did we get here?");
        }

        //If the Data Query is Null, Notify the User that there is no correlating data
        if(outputBookInformationModel == null){
            outputBookInformationModel = new OutputBookInformationModel();
            outputBookInformationModel.setErrors(true);
            outputBookInformationModel.setErrorMessage("We Couldn't Find a Book Off of Given Credentials");
            return outputBookInformationModel;
        }

        //If there is data found off of given credentials AND a barcode was entered,
        //Save a copy of this book with the given barcode to database
        if(nwtxdt.getBarcode() != ""){
            System.out.println("saving this!");
            nwtxdt.setEditionYear(outputBookInformationModel.getEditionYear());
            nwtxdt.setSeqNr(String.valueOf(outputBookInformationModel.getLastSeqNr()+1));
            addBookService.saveNwtxdt(nwtxdt);
        }
        outputBookInformationModel.setLastSeqNr(outputBookInformationModel.getLastSeqNr()+1);
        return outputBookInformationModel;
    }


    @Autowired
    BookQueryService bookQueryService;

    //Find-Book GET
    //SUPERVISOR ONLY
    @GetMapping("/Find-Book")
    public String bookQuery(ModelMap model){
        //Log to prove we've gotten to this function
        System.out.println("Book Query GET");

        //Pseudo Security
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
        //Log to prove we've gotten to this function
        System.out.println("Book Query POST");

        //Pseudo Security
        if(!supervisor){
            return null;
        }

        //Creating a model to use and ultimately send via AJAX
        OutputBookInformationModel outputBookInformationModel = new OutputBookInformationModel();
        //Javax Data Validation
        if(bindingResult.hasErrors()){
            //Print all errors for debugging sake. Can be removed if wanted
            for (Object object : bindingResult.getAllErrors()) {
                if(object instanceof FieldError) {
                    System.out.println((FieldError) object);
                }
            }
            //Checks if bookcode or barcode at least exists. If not, then we return an error
            if(nwtxdt.getBookCode() == "" && nwtxdt.getBarcode() == ""){
                outputBookInformationModel.setErrors(true);
                outputBookInformationModel.setErrorMessage("We Need at Least a Book Code or a Barcode");
                return outputBookInformationModel;
            }
        }
        outputBookInformationModel.setErrors(false);
        //Passed Data Validation

        System.out.println("Passed Data Validation");
        if(nwtxdt.getBarcode() == "" && nwtxdt.getEditionYear() == ""){
            //If no edition year or barcode was given, we look for the most recent edition year of given book code
            outputBookInformationModel = bookQueryService.getMostRecentNwtxdt(nwtxdt.getBookCode());
        } else if(nwtxdt.getBarcode() == "" && nwtxdt.getEditionYear() != ""){
            //If no barcode was given but edition year is given, we look for the book based off of book code and edition year
            outputBookInformationModel = bookQueryService.getNwtxdtByBookCodeAndYear(nwtxdt.getBookCode(), nwtxdt.getEditionYear());
        }
        else if(!nwtxdt.getBarcode().equals("")){
            //If barcode was given, we look off of barcode given
            outputBookInformationModel = bookQueryService.getNwtxdtByBarcode(nwtxdt.getBarcode());
        } else{
            //Email Zachariah Durbin if this ever gets called. This should be impossible
            System.out.println("How did we get here?");
        }

        //If no data was found from given credentials, return error
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
        //Log to prove we've gotten to this function
        System.out.println("Book Dispostion GET");

        //Pseudo Security
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

        //Pseudo Security
        if(!supervisor){
            return null;
        }

        //Creating a model to use and ultimately send via AJAX
        OutputBookInformationModel outputBookInformationModel = new OutputBookInformationModel();
        String changingDisposition = nwtxdt.getDisposition();
        //Javax Data Validation
        if(bindingResult.hasErrors()){
            //Print all errors for debugging sake. Can be removed if wanted
            for (Object object : bindingResult.getAllErrors()) {
                if(object instanceof FieldError) {
                    System.out.println((FieldError) object);
                }
            }
            //Checks if bookcode or barcode at least exists. If not, then we return an error
            if(nwtxdt.getBookCode() == "" && nwtxdt.getBarcode() == ""){
                outputBookInformationModel.setErrors(true);
                outputBookInformationModel.setErrorMessage("We Need at Least a Book Code or a Barcode");
                return outputBookInformationModel;
            }
        }
        //Passed Data Validation
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

        if(changingDisposition != null){
            System.out.println("changing stuff");
            if(!outputBookInformationModel.getCurrentDisposition().equals(changingDisposition)){
                System.out.println("in here");
                nwtxdt.setDisposition(changingDisposition);
                nwtxdt.setBookCode(outputBookInformationModel.getBookCode());
                nwtxdt.setEditionYear(outputBookInformationModel.getEditionYear());
                nwtxdt.setSeqNr(outputBookInformationModel.getSeqNr());
                nwtxdt.setBarcode(outputBookInformationModel.getBookCode());
                nwtxdt.setBookCode(outputBookInformationModel.getBookCode());
                nwtxdt.setBookCode(outputBookInformationModel.getBookCode());
                nwtxdt.setBookCode(outputBookInformationModel.getBookCode());
                nwtxdt.setBookCode(outputBookInformationModel.getBookCode());
                nwtxdt.setBookCode(outputBookInformationModel.getBookCode());
                nwtxdt.setBookCode(outputBookInformationModel.getBookCode());
                bookDispositionService.save(nwtxdt);
                outputBookInformationModel.setErrors(true);
                outputBookInformationModel.setErrorMessage("New Disposition Saved!");
            }
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
        //Log to prove we've gotten to this function
        System.out.println("Replace Barcode GET");

        //Pseudo Security
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
        //Log to prove we've gotten to this function
        System.out.println("Replace Barcode POST");

        //Pseudo Security
        if(!supervisor){
            return null;
        }

        //Creating a model to use and ultimately send via AJAX
        OutputBookInformationModel outputBookInformationModel = new OutputBookInformationModel();
        //Javax Data Validation
        if(bindingResult.hasErrors()){
            //Print all errors for debugging sake. Can be removed if wanted
            System.out.println("ope, there were errors");
            for (Object object : bindingResult.getAllErrors()) {
                if(object instanceof FieldError) {
                    System.out.println((FieldError) object);
                }
            }
            //Checks if bookcode or barcode at least exists. If not, then we return an error
            if(nwtxdt.getBookCode() == "" && nwtxdt.getBarcode() == ""){
                outputBookInformationModel.setErrors(true);
                outputBookInformationModel.setErrorMessage("We Need at Least a Book Code or a Barcode");
                return outputBookInformationModel;
            }
        }
        //Passed Data Validation
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
        //Log to prove we've gotten to this function
        System.out.println("Course Query GET");

        //Pseudo Security
        if(!supervisor){
            return "redirect:/";
        }
        return "Supervisor/queryCourse";
    }

    //Find Course POST
    //SUPERVISOR ONLY
    @PostMapping("/Find-Course")
    //public @ResponseBody List<Nwtxin> queryCoursePost(ModelMap model, @Valid @RequestBody @ModelAttribute("inputNwtxin")  Nwtxin nwtxin, BindingResult bindingResult)
    public @ResponseBody List<Nwtxin> queryCoursePost(ModelMap model, @RequestParam("course")  String course)
                                  throws ParseException {
        //Log to prove we've gotten to this function
        System.out.println("Course Query POST");

        //Pseudo Security
        if(!supervisor){
            return null;
        }

        //We completely forgot to put regex for this function. Sorry!

        //Creating a model to use and ultimately send via AJAX
        List<Nwtxin> nwtxinList = new ArrayList<Nwtxin>();

        nwtxinList = queryCourseService.getAllBooksForCourse(course);
        if (nwtxinList != null) {
            return nwtxinList;
        }else{
            
            //Wasn't able to find a book off of given credentials
            //nwtxinList.add("No Courses Were Found with Given Credentials");
            return nwtxinList;
        }
    }


    @Autowired
    CourseMessageService courseMessageService;

    //Course Message GET
    //SUPERVISOR ONLY
    @GetMapping("/Course-Message")
    public String courseMessage(ModelMap model){
        //Log to prove we've gotten to this function
        System.out.println("Course Message GET");

        //Pseudo Security
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
        //Log to prove we've gotten to this function
        System.out.println("Course Message POST");

        //Pseudo Security
        if(!supervisor){
            return nwtxcm;
        }
        /*
         * IF WE HAD MORE TIME FOR THIS PROJECT, WE WOULD HAVE DONE MORE DATA
         * VALIDATION LIKE THIS ONE IN THE SENSE THAT WE WOULD CHECK IF THE
         * DATA INPUTTED WAS PROPER. NOT JUST IF IT EXISTED OR NOT

         * This data validation is weird. This was one of the first methods
         * we tried to do data validation for, and at this moment we didn't
         * want to create new models to send to the views. This method will
         * change the inputs given from the user and return the model given,
         * and in turn, whenever the view gets our response, it will check to
         * see if there are any changes to the inputs it gave. If so, then it
         * knows there was an error and will read the error for each specific
         * input that was changed. This ultimately didn't work out for other
         * methods, and should be replaced.
         */
        //Javax Data Validation
        if(bindingResult.hasErrors()){
            //Print all errors for debugging sake. Can be removed if wanted
            for (Object object : bindingResult.getAllErrors()) {
                if(object instanceof FieldError) {
                    System.out.println((FieldError) object);
                }
            }
            //Checks if course message at least exists. If not, then we return an error
            if(nwtxcm.getCourse() == ""){
                nwtxcm.setCourse("Course Cannot Be Empty (They're All 9 Characters Long)");
            } else if(nwtxcm.getCourse().length() != 9){
                //Checks to see if course message is the proper length
                nwtxcm.setCourse("Course Has to Be 9 Characters Long");
            } else if(nwtxcm.getMessage().length() > 15){
                //If the new message inputted is too long, we return an error
                nwtxcm.setCourse("Message is too long!");
            }
            return nwtxcm;
        }
        //Passed Data Validation

        //Queries data from database
        Nwtxcm oldNwtxcm = courseMessageService.getNwtxcm(nwtxcm.getCourse());

        //Checks to see that the query had any results
        if (oldNwtxcm != null) {
            //There was at least one object that worked for the query, so we'll continue

            //Checks to see if there was any message input from the user. If there
            //was, we assume they want to change the message. If not, we send the
            //old course's message
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
        //Log to prove we've gotten to this function
        System.out.println("Course Message GET");

        //Pseudo Security
        if(!supervisor){
            return "redirect:/";
        }
        return "Supervisor/changeBookCode";
    }

    //Change Book Code POST
    //SUPERVISOR ONLY
    @RequestMapping(value= "/Change-Book-Code", method = RequestMethod.POST)

    //**IMPORTANT** This method uses the "ChangingVariableModel", which I meant to get rid of. Sorry!
    public @ResponseBody OutputBookInformationModel changeBookCodePost(@Valid @RequestBody @ModelAttribute("inputNwtxdt") ChangingVariableModel changingVariableModel, BindingResult bindingResult, ModelMap model)
                                     throws ParseException{
        //Log to prove we've gotten to this function
        System.out.println("Course Message POST");

        //Pseudo Security
        if(!supervisor){
            return null;
        }

        //Creating a model to use and ultimately send via AJAX
        OutputBookInformationModel outputBookInformationModel = new OutputBookInformationModel();
        //Javax Data Validation
        if(bindingResult.hasErrors()){
            //Print all errors for debugging sake. Can be removed if wanted
            for (Object object : bindingResult.getAllErrors()) {
                if(object instanceof FieldError) {
                    System.out.println((FieldError) object);
                }
            }

            //Checks if bookcode at least exists. If not, then we return an error
            if(changingVariableModel.getCurrentBookCode() == ""){
                outputBookInformationModel.setErrors(true);
                outputBookInformationModel.setErrorMessage("We Need at Least a Book Code or a Barcode");
                return outputBookInformationModel;
            }
        }
        //Passed Data Validation
        outputBookInformationModel.setErrors(false);
        System.out.println(changingVariableModel.getCurrentBookCode());
        System.out.println(changingVariableModel.getCurrentEditionYear());
        System.out.println(changingVariableModel.getChangingBookCode());
        System.out.println(changingVariableModel.getChangingEditionYear());
        System.out.println("Passed Data Validation");
        if(changingVariableModel.getCurrentEditionYear() == null){
            outputBookInformationModel = changeBookcodeService.getMostRecentNwtxdt(changingVariableModel.getCurrentBookCode());
        } else if(changingVariableModel.getCurrentEditionYear() != null){
            outputBookInformationModel = changeBookcodeService.getNwtxdtByBookCodeAndYear(changingVariableModel.getCurrentBookCode(), changingVariableModel.getCurrentEditionYear());
        }else{
            System.out.println("How did we get here?");
        }
        if(outputBookInformationModel == null){
            outputBookInformationModel = new OutputBookInformationModel();
            outputBookInformationModel.setErrors(true);
            outputBookInformationModel.setErrorMessage("We Couldn't Find a Book Off of Given Credentials");
            return outputBookInformationModel;
        }
        if(changingVariableModel.getCurrentEditionYear() == null){
            changingVariableModel.setCurrentEditionYear(outputBookInformationModel.getEditionYear());
        }
        if(changingVariableModel.getChangingBookCode() != null && changingVariableModel.getChangingEditionYear() !=null){
            System.out.println("Chaning both book code and edition year");
            changeBookcodeService.changeByBookCodeAndEditionYear(changingVariableModel.getCurrentBookCode(),changingVariableModel.getCurrentEditionYear(),changingVariableModel.getChangingBookCode(),changingVariableModel.getChangingEditionYear());
        } else if(changingVariableModel.getChangingBookCode() != null && changingVariableModel.getChangingEditionYear() ==null){
            System.out.println(outputBookInformationModel.getEditionYear());
            System.out.println("changing only book code");
        } else if(changingVariableModel.getChangingBookCode() == null && changingVariableModel.getChangingEditionYear() !=null){
            System.out.println(outputBookInformationModel.getEditionYear());
            System.out.println("changing only edition year");
        } else{
            System.out.println("Detected no changes");
        }
        return outputBookInformationModel;
    }


    @Autowired
    PreviousBooksService previousBooksService;

    //Previous Books GET
    @RequestMapping(value= "/Previous-Books", method = RequestMethod.GET)
    public String prevBoooks(){
        //Log to prove we've gotten to this function
        System.out.println("Previous Books GET");

        //Pseudo Security
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
    public @ResponseBody OutputStudentInformationModel previousBooksPost(@Valid @RequestBody @ModelAttribute("inputSpriden") Spriden spriden, BindingResult bindingResult, ModelMap model)
                                throws ParseException{
        //Log to prove we've gotten to this function
        System.out.println("Previous Books POST");

        //This function we completely forgot to put any security or regex on. Sorry!

        spriden = previousBooksService.getSpriden(spriden.getId());

        //Creating a model to use and ultimately send via AJAX
        OutputStudentInformationModel outputStudentInformationModel = new OutputStudentInformationModel();
        outputStudentInformationModel.setLastName(spriden.getLastName());

        //If Spriden is not null from database query, get all books relevant
        if(spriden != null){
            List<Nwtxdt> nwtxdtList =  previousBooksService.getNwtxdt(spriden.getPidm());
            List<Nwtxin> nwtxinList = previousBooksService.getTitles(nwtxdtList);
            outputStudentInformationModel.setNwtxinList(nwtxinList);
            outputStudentInformationModel.setNwtxdtList(nwtxdtList);
        }
        //Supervisor Privileges
        if(supervisor){
            return outputStudentInformationModel;
        }
        return outputStudentInformationModel;
    }


    @Autowired
    SoldBooksService soldBooksService;

    //Sold Books GET
    @RequestMapping(value= "/Sold-Books", method = RequestMethod.GET)
    public String soldBooks(){
        //Log to prove we've gotten to this function
        System.out.println("Sold Books GET");

        //Pseudo Security
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
    public @ResponseBody OutputStudentInformationModel soldBooksPost(@Valid @RequestBody @ModelAttribute("inputSpriden") Spriden spriden, BindingResult bindingResult, ModelMap model){
        //Log to prove we've gotten to this function
        System.out.println("Sold Books Post");

        //This function we completely forgot to put any security or regex on. Sorry!

        //Creating a model to use and ultimately send via AJAX
        spriden = soldBooksService.getSpriden(spriden.getId());
        OutputStudentInformationModel outputStudentInformationModel = new OutputStudentInformationModel();

        //If Spriden is not null from database query, get all books relevant
        if(spriden != null){
            List<Nwtxdt> nwtxdtList =  soldBooksService.getNwtxdt(spriden.getPidm());
            List<Nwtxin> nwtxinList = soldBooksService.getTitles(nwtxdtList);
            outputStudentInformationModel.setNwtxinList(nwtxinList);
            outputStudentInformationModel.setNwtxdtList(nwtxdtList);
        }

        //Supervisor Privileges
        if(supervisor){
            return outputStudentInformationModel;
        }
        return outputStudentInformationModel;
    }


    @Autowired
    StudentScheduleService studentScheduleService;

    //Student Schedule GET
    @RequestMapping(value= "/Student-Schedule", method = RequestMethod.GET)
    public String studentSchedule(ModelMap model){
        //Log to prove we've gotten to this function
        System.out.println("Student Schedule GET");

        //Pseudo Security
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
    public @ResponseBody OutputStudentInformationModel studentSchedulePost(@Valid @RequestBody @ModelAttribute("inputSpriden") Spriden spriden, BindingResult bindingResult, ModelMap model)
                                      throws ParseException{
        //Log to prove we've gotten to this function
        System.out.println("Student Schedule POST");

        //Pseudo Security
        if(!supervisor && !studentEmployee){
            return null;
        }

        //We completely forgot to put regex for this function. Sorry!

        //Making variable to place data query info into
        spriden = previousBooksService.getSpriden(spriden.getId());
        //Creating a model to use and ultimately send via AJAX
        OutputStudentInformationModel outputStudentInformationModel = new OutputStudentInformationModel();
        outputStudentInformationModel.setLastName(spriden.getLastName());
        //Making variable to place data query info into
        Stvterm term = studentScheduleService.getLatestTerm(spriden.getPidm());
        //Making variable to place data query info into
        List<Sfrstcr> sfrstcr = studentScheduleService.getSfrstcr(Integer.parseInt(spriden.getPidm()), term.getCode());
        List<Ssbsect> output = new ArrayList<>();
        List<String> outputTitle = new ArrayList<>();
        List<Ssrmeet> outputTimes = new ArrayList<>();
        //For each class the student is in, add that class to the output
        for(Sfrstcr item: sfrstcr){
            Ssbsect ssbsect = studentScheduleService.getSsbsect(item.getCrn(), item.getTermCode());
            output.add(ssbsect);
            Scbcrse scbcrse = studentScheduleService.getScbcrse(ssbsect.getSubjCode(),ssbsect.getCrseNumb());
            //If the class has a title, add that as well
            if(scbcrse != null){
                outputTitle.add(scbcrse.getTitle());
            }
        }

        //Sets the output list to the returning model
        outputStudentInformationModel.setSsbsectList(output);
        //Supervisor Privileges
        if(supervisor){
            return outputStudentInformationModel;
        }
        return outputStudentInformationModel;
    }


    @Autowired
    CheckInOutService checkInOutService;

    //Check-In-Out GET
    @GetMapping("/Check-In-Out")
    public String checkInOut(ModelMap model){
        //Log to prove we've gotten to this function
        System.out.println("Check In Out GET");

        //Pseudo Security
        if(supervisor){
            //List<Stvterm> terms = checkInOutService.getLatestTerms();
            //model.put("term", terms);
            return"Supervisor/checkInOut";
        } else if(studentEmployee){
            //List<Stvterm> terms = checkInOutService.getLatestTerms();
            //model.put("term", terms);
            return "StudentEmployee/checkInOut";
        } else {
            return "redirect:/";
        }
    }


    //Check-In-Out POST
    @PostMapping("/Check-In-Out")
    public @ResponseBody OutputStudentInformationModel changeBookCodePost(@Valid @RequestBody @ModelAttribute("inputSpriden") Spriden spriden, BindingResult bindingResult, ModelMap model){
        //Log to prove we've gotten to this function
        System.out.println("Check In Out POST");

        //This function we completely forgot to put any security or regex on. Sorry!

        //Data to hold the query information
        spriden = checkInOutService.getStudent(spriden.getId());
        Stvterm term = checkInOutService.getLatestTerm(spriden.getPidm());
        String bagNumber = checkInOutService.getBag(spriden.getPidm());

        //If statement that was meant to hold a function in case there was a bag number... apparently got deleted?
        if(bagNumber != null){

        }

        //Creating a model to use and ultimately send via AJAX
        OutputStudentInformationModel outputStudentInformationModel = new OutputStudentInformationModel();
        //Setting information into output model
        outputStudentInformationModel.setBagNumber(bagNumber);
        outputStudentInformationModel.setTerm(term.getDesc());
        outputStudentInformationModel.setFirstName(spriden.getFirstName());
        outputStudentInformationModel.setMiddleInitial(spriden.getMiddleInitial());
        outputStudentInformationModel.setLastName(spriden.getLastName());

        //Collects all books relevant to student of this term, and sends them to output model
        List<Nwtxdt> nwtxdtList = checkInOutService.getBooks(spriden.getPidm(), term.getCode());
        outputStudentInformationModel.setNwtxdtList(nwtxdtList);


        /* **IMPORTANT** This code originally dealt with checking books in and out to the id given
         * Unfortunately, we didn't have enough time to get both systems working where we could
         * show all books checked out to a certain person AND this system that can check them in
         * and out, so we set this one where it never gets used, as you can see commented below
         * This can easily be implemented back in, however. There just wasn't enough time for us
         * to implement that back in. If only we had a week more to work on this, so many of these
         * problems would be gone!
         */


        //Checks the availability of the book
        //0 = Book couldn't be found by given barcode
        //1 = Book isn't currently checked out to anyone
        //2 = Book is currently checked out to someone
        //3 = Book is currently checked out to this current person

        //Originally availability would check to see if the inputted barcode was valid to check
        //In or out. We set it to never do anything, because we needed data validation for
        //the barcode section.
        int availability = 0;//checkInOutService.checkAvailability(barCode, spriden, term);
        if(availability == 0){
            System.out.println("nah dawg");
        } else if(availability == 1){
            System.out.println("Not checked out to anyone");
        } else if(availability == 2){
            System.out.println("book was checked out to someone");
        } else{
            System.out.println("book was checked out to this person");

        }
        model.put("bagNumber", checkInOutService.getBag(spriden.getPidm()));

        return outputStudentInformationModel;
    }
}