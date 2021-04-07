package com.webapp.TextBook.controller;

//Imported Standard Java Libraries
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


//Imported Spring Libraries
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


//Imported Services
import com.webapp.TextBook.Service.*;


//Imported Models
import com.webapp.TextBook.Model.*;


@Controller
public class HomeController {

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    //Just temporary login things for now. Intended to be replaced later!
    @RequestMapping(value="/", method = RequestMethod.POST)
    public String loginPost(@RequestParam (value = "login",required = false, defaultValue = "")String login,
                            @RequestParam (value = "password",required = false, defaultValue = "")String password)
                            throws ParseException{
        if(login.equals("admin") && password.equals("admin")){
            return "Supervisor/supervisorView";
        } else{
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

    @RequestMapping(value="/Add-Book", method = RequestMethod.GET)
    public String addBookPage(ModelMap model){
        System.out.println("Add Book GET");
        return "Supervisor/addBook";
    }

    @RequestMapping(value="/Add-Book", method = RequestMethod.POST)
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
            return "Supervisor/addBook";
        }
        Nwtxdt nwtxdt = new Nwtxdt();
        nwtxdt.setBarcode(barcode);
        nwtxdt.setEditionYear(editionYear);
        nwtxdt.setBookCode(bookCode);
        nwtxdt.setSeqNr(seqNr);

        System.out.println("Adding Book with " + nwtxdt.getBarcode() + ", " + nwtxdt.getEditionYear() + ", " + nwtxdt.getBookCode() + ", " +
                nwtxdt.getSeqNr() + " Credentials");
        addBookService.saveNwtxdt(nwtxdt);
        return "Supervisor/addBook";
    }


    @Autowired
    BookQueryService bookQueryService;

    @RequestMapping(value = "/Find-Book", method = RequestMethod.GET)
    public String bookQuery(ModelMap model){
        System.out.println("Book Query GET");
        return "StudentEmployee/bookQuery";
    }

    @RequestMapping(value = "/Find-Book", method = RequestMethod.POST)
    public String bookQueryPost(/*@Valid @ModelAttribute("nwtxdt") Nwtxdt nwtxdt*/ ModelMap model,
                                @RequestParam (value = "bookCode", required = true, defaultValue = "") String bookCode,
                                @RequestParam (value = "editionYear", required = false, defaultValue = "") String editionYear,
                                @RequestParam (value = "barcode", required = false, defaultValue = "") String barcode
                                /*BindingResult result*/) throws ParseException {
        System.out.println("Book Query POST");
        Nwtxdt nwtxdt = new Nwtxdt();
        if(bookCode.equals("") || editionYear.equals("") || barcode.equals("")){
            model.put("returnVoidError", "Invalid Credentials");
            return "StudentEmployee/bookQuery";
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

            return "StudentEmployee/bookQuery";
        } else {
            model.put("returnVoidError", "No Book Found Off of Given Credentials");
        }

        return "StudentEmployee/bookQuery";
    }

    @Autowired
    BookDispositionService bookDispositionService;
    @RequestMapping(value = "/Change-Disposition", method = RequestMethod.GET)
    public String bookDisposition(){
        System.out.println("Book Dispostion GET");
        return "Supervisor/bookDisposition";
    }

  @RequestMapping(value = "/Disposition-Change", method = RequestMethod.POST)
  public String bookDispositionPost(ModelMap model,
                                    @RequestParam (value = "bookCode", required = true, defaultValue = "") String bookCode,
                                    @RequestParam (value = "editionYear", required = false, defaultValue = "") String editionYear,
                                    @RequestParam (value = "barcode", required = false, defaultValue = "") String barcode,
                                    @RequestParam (value = "bookDisposition", required = false, defaultValue = "") String bookDisposition)
                                    throws ParseException {
        System.out.println("Book Disposition POST");
        System.out.println(bookCode + editionYear + barcode + bookDisposition);

        Nwtxdt nwtxdt = bookDispositionService.getNwtxdt(bookCode,editionYear,barcode);
        nwtxdt.setDisposition(bookDisposition);
        bookDispositionService.setNwtxdt(nwtxdt);
        return "Supervisor/bookDisposition";
  }

        @RequestMapping("/Change-Barcode")
    public String replaceBarcode(){
        return "Supervisor/replaceBarcode";
    }

    @Autowired
    QueryCourseService queryCourseService;
    @RequestMapping(value = "/Find-Course", method = RequestMethod.GET)
    public String queryCourse(ModelMap model){
        System.out.println("Course Query GET");
        return "StudentEmployee/queryCourse";
    }
    @RequestMapping(value = "/Find-Course", method = RequestMethod.POST)
    public String queryCoursePost(ModelMap model,
                                  @RequestParam(value = "courseCode", required = false, defaultValue = "")String courseCode)
                                  throws ParseException{
        System.out.println("Course Query POST");
        Scbcrse scbcrse = new Scbcrse();
        if(courseCode.equals("")){
            model.put("returnVoidError", "Invalid Credentials");
            return "StudentEmployee/queryCourse";
        }
        scbcrse.setCrseNumb(courseCode);

        System.out.println("Querying off of: " + scbcrse.getCrseNumb());
        if(queryCourseService.getScbcrse(courseCode) != null){
            model.put("crseTable", queryCourseService.getScbcrse(courseCode));

        } else {
            model.put("returnVoidError", "No Book Found Off of Given Credentials");
        }
        return "StudentEmployee/queryCourse";
    }

    @Autowired
    CourseMessageService courseMessageService;
    @RequestMapping(value = "/Course-Message", method = RequestMethod.GET)
    public String courseMessage(){
        System.out.println("Course Message GET");
        return "StudentEmployee/courseMessage";
    }

    @RequestMapping(value = "/Course-Message", method = RequestMethod.POST)
    public String courseMessagePost(ModelMap model,
                                    @RequestParam(value = "courseId", required = false, defaultValue = "")String courseId)
                                     throws ParseException{
        System.out.println("Course Message POST");
        if(courseId.equals("")){
            model.put("returnVoidError", "Invalid Credentials");
            return "StudentEmployee/courseMessage";
        }
        System.out.println("Querying off of: " + courseId);
        if(courseMessageService.getNwtxcm(courseId) != null){
            model.put("courseMessage", courseMessageService.getNwtxcm((courseId)).getCmMessage());

        } else {
            model.put("returnVoidError", "No Book Found Off of Given Credentials");
        }
        return "StudentEmployee/courseMessage";
    }
    @RequestMapping(value = "/Course-Message", method = RequestMethod.POST, params="clear")
    public String courseMessageClear(ModelMap model,
                                    @RequestParam(value = "courseId", required = false, defaultValue = "")String courseId)
            throws ParseException{
        System.out.println("Course Message POST - CLEAR");
        if(courseId.equals("")){
            model.put("returnVoidError", "Invalid Credentials");
            return "StudentEmployee/courseMessage";
        }
        System.out.println("Querying off of: " + courseId);
        if(courseMessageService.getNwtxcm(courseId) != null){
            Nwtxcm nwtxcm = new Nwtxcm();
            nwtxcm = courseMessageService.getNwtxcm(courseId);
            nwtxcm.setCmMessage("");
            model.put("courseMessage", "Cleared!");
            courseMessageService.saveNwtxcm(nwtxcm);

        } else {
            model.put("returnVoidError", "No Course Found Off of Given Credentials");
        }
        return "StudentEmployee/courseMessage";
    }

    @Autowired
    ChangeBookcodeService changeBookcodeService;
    @RequestMapping(value= "/Change-Book-Code", method = RequestMethod.GET)
    public String changeBookCode(){
        System.out.println("Course Message GET");
        return "Supervisor/changeBookCode";
    }

    @RequestMapping(value= "/Book-Code-Change", method = RequestMethod.POST)
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
            return "Supervisor/changeBookCode";
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
        return "Supervisor/changeBookCode";
    }

    @Autowired
    PreviousBooksService previousBooksService;
    @RequestMapping(value= "/Previous-Books", method = RequestMethod.GET)
    public String prevBoooks(){
        System.out.println("Previous Books GET");
        return "StudentEmployee/previousBooks";
    }

    @RequestMapping(value= "/Previous-Books", method = RequestMethod.POST)
    public String prevBooksPost(ModelMap model,
                                @RequestParam(value = "id", required = false, defaultValue = "")String id,
                                @RequestParam(value = "prevTerm", required = false, defaultValue = "")String prevTerm)
                                throws ParseException{
        System.out.println("Previous Books POST");
        if(id.equals("") || prevTerm.equals("")){
            System.out.println("nah dawg");
            return "StudentEmployee/previousBooks";
        }

        if(previousBooksService.getSpriden(id) != null && previousBooksService.getStvterm(prevTerm) != null){
            Spriden spriden = previousBooksService.getSpriden(id);
            System.out.println(spriden.getFirstName() + " " + spriden.getMiddleInitial() + " " + spriden.getLastName());
            previousBooksService.getNwtxdt(spriden.getPidm(), prevTerm);
            model.put("prevBooks",previousBooksService.getNwtxdt(spriden.getPidm(), prevTerm));
        }

        return "StudentEmployee/previousBooks";
    }

    @Autowired
    SoldBooksService soldBooksService;
    @RequestMapping(value= "/Sold-Books", method = RequestMethod.GET)
    public String soldBooks(){
        System.out.println("Sold Books GET");
        return "StudentEmployee/soldBooks";
    }

    @RequestMapping(value= "/Sold-Books", method = RequestMethod.POST)
    public String soldBooksPost(ModelMap model,
                                @RequestParam(value = "id", required = false, defaultValue = "")String id){
        System.out.println("Sold Books Post");
        if(id.equals("")){
            return "StudentEmployee/soldBooks";
        }

        if(soldBooksService.getSpriden(id) != null){
            Spriden spriden = soldBooksService.getSpriden(id);
            System.out.println(spriden.getPidm());
            if(soldBooksService.getNwtxdt(spriden.getPidm()) != null){

            }
            model.put("soldBooks",soldBooksService.getNwtxdt(spriden.getPidm()));
        }

        return "StudentEmployee/soldBooks";
    }

    @Autowired
    StudentScheduleService studentScheduleService;
    @RequestMapping(value= "/Student-Schedule", method = RequestMethod.GET)
    public String studentSchedule(){
        System.out.println("Student Schedule GET");
        return "StudentEmployee/studentSchedule";
    }

    @RequestMapping(value= "/Student-Schedule", method = RequestMethod.POST)
    public String studentSchedulePost(ModelMap model,
                                      @RequestParam(value = "termSeason", required = false, defaultValue = "")String termSeason,
                                      @RequestParam(value = "id", required = false, defaultValue = "")String id)
                                      throws ParseException{
        System.out.println("Student Schedule POST");
        if(termSeason.equals("") || id.equals("")){
            System.out.println("nah dawg");
            return "StudentEmployee/studentSchedule";
        }

        studentScheduleService.getStvterm(termSeason).getDesc();
        System.out.println("Checkpoint 1");
        if(studentScheduleService.getSpriden(id).getPidm().equals("")){
            System.out.println("pidm empty");
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


        return "StudentEmployee/studentSchedule";
    }

}