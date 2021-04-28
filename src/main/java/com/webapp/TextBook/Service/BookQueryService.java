package com.webapp.TextBook.Service;

//Spring Dependencies
import com.webapp.TextBook.Model.*;
import com.webapp.TextBook.Repository.NwtxinRepository;
import com.webapp.TextBook.Repository.SpridenRepository;
import com.webapp.TextBook.Repository.StvtermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

//Textbook Services Dependencies
import com.webapp.TextBook.Repository.NwtxdtRepository;


@Service
public class BookQueryService {

    @Autowired private NwtxdtRepository nwtxdtRepository;
    @Autowired private NwtxinRepository nwtxinRepository;
    @Autowired private SpridenRepository spridenRepository;
    @Autowired private StvtermRepository stvtermRepository;

    public OutputBookInformationModel getNwtxdtByBarcode(String barcode){
        //Create a list of all repositories under given credentials
        List<Nwtxdt> nwtxdtList = nwtxdtRepository.findByBarcode(barcode);
        //If there is at least 1 model under given credentials, return the first
        //Else, return nothing
        if (nwtxdtList.size() > 0) {

            //List for data queried from database
            List<Nwtxin> nwtxinList = nwtxinRepository.findByBookCodeAndEditionYear(nwtxdtList.get(0).getBookCode(),nwtxdtList.get(0).getEditionYear());

            //If there is data in list, populate output, else return nothing
            if(nwtxinList.size() > 0){

                //Lists for data queried from database
                List<Spriden> currentPersonSpriden = spridenRepository.findByPidm(nwtxdtList.get(0).getPidm());
                List<Spriden> previousPersonSpriden = spridenRepository.findByPidm(nwtxdtList.get(0).getPrevPidm());
                List<Stvterm> currentTerm = stvtermRepository.findByCode(nwtxdtList.get(0).getTerm());
                List<Stvterm> previousTerm = stvtermRepository.findByCode(nwtxdtList.get(0).getPrevTerm());

                //Populate output
                OutputBookInformationModel outputBookInformationModel = new OutputBookInformationModel();
                outputBookInformationModel.setBookCode(nwtxdtList.get(0).getBookCode());
                outputBookInformationModel.setBarcode(nwtxdtList.get(0).getBarcode());
                outputBookInformationModel.setEditionYear(nwtxdtList.get(0).getEditionYear());

                //If there is a person this is checked out to, show their name, else notification no one has book
                if(currentPersonSpriden.size()>0){
                    outputBookInformationModel.setCurrentCheckedOutTo(currentPersonSpriden.get(0).getFirstName() + " " + currentPersonSpriden.get(0).getMiddleInitial() + " " + currentPersonSpriden.get(0).getLastName());
                } else{
                    outputBookInformationModel.setCurrentCheckedOutTo("Currently Isn't Checked Out To Anyone");
                }
                //If there was a previous person checked out to, show their name, else notification no one had book
                if(previousPersonSpriden.size()>0){
                    outputBookInformationModel.setPreviousCheckedOutTo(previousPersonSpriden.get(0).getFirstName() + " " + previousPersonSpriden.get(0).getMiddleInitial() + " " + previousPersonSpriden.get(0).getLastName());
                } else{
                    outputBookInformationModel.setPreviousCheckedOutTo("No Previous Person Checked Out To");
                }
                outputBookInformationModel.setTitle(nwtxinList.get(0).getTitle());
                outputBookInformationModel.setCurrentDisposition(nwtxdtList.get(0).getDisposition());
                outputBookInformationModel.setSeqNr(nwtxdtList.get(0).getSeqNr());
                DateFormat df = new SimpleDateFormat("MMM/dd/yyyy");

                //If there is a date checked out, populate it
                if(nwtxdtList.get(0).getDateCheckedOut() != null){
                    outputBookInformationModel.setCurrentDateCheckedOut(df.format(nwtxdtList.get(0).getDateCheckedOut()));
                }
                //If there is a current term checked out, populate it
                if(currentTerm.size() > 0){
                    outputBookInformationModel.setCurrentTermCheckOut(currentTerm.get(0).getDesc());
                } else{
                    outputBookInformationModel.setCurrentTermCheckOut("Couldn't Find a Term Description for: " + nwtxdtList.get(0).getTerm());
                }

                //If there is a previous term, populate it
                if(previousTerm.size() > 0){
                    outputBookInformationModel.setPreviousTermCheckedOut(previousTerm.get(0).getDesc());
                } else{
                    outputBookInformationModel.setPreviousTermCheckedOut("Couldn't Find a Term Description for: " + nwtxdtList.get(0).getPrevTerm());
                }
                //if there is a previous date checked in, populate it
                if(nwtxdtList.get(0).getPrevDateCheckedIn() != null){
                    outputBookInformationModel.setPreviousDateCheckedIn(df.format(nwtxdtList.get(0).getPrevDateCheckedIn()));
                }
                outputBookInformationModel.setBookCode(nwtxdtList.get(0).getBookCode());
                return outputBookInformationModel;
            }
        }
        return null;
    }

    public OutputBookInformationModel getMostRecentNwtxdt(String bookCode){

        //List to hold data from database
        List<Nwtxin> nwtxinList = nwtxinRepository.findByBookCode(bookCode);

        //If there is data, populate output, else return nothing
        if(nwtxinList.size() >0){

            //Looks for most recent edition year
            int mostRecent = 0;
            for(int i = 0; i < nwtxinList.size(); i++){
                if(i > 0){
                    System.out.println(nwtxinList.get(i).getEditionYear());
                    if(Integer.parseInt(nwtxinList.get(i).getEditionYear()) > Integer.parseInt(nwtxinList.get(i-1).getEditionYear())){
                        mostRecent= i;
                    }
                }
            }

            //Populate output
            OutputBookInformationModel outputBookInformationModel = new OutputBookInformationModel();
            outputBookInformationModel.setBookCode(nwtxinList.get(mostRecent).getBookCode());
            outputBookInformationModel.setBarcode("No Specific Barcode Given");
            outputBookInformationModel.setEditionYear(nwtxinList.get(mostRecent).getEditionYear());
            outputBookInformationModel.setCurrentCheckedOutTo("We Need A Barcode For This");outputBookInformationModel.setPreviousCheckedOutTo("We Need A Barcode For This");
            outputBookInformationModel.setTitle(nwtxinList.get(mostRecent).getTitle());
            outputBookInformationModel.setCurrentDisposition("We Need A Barcode For This");
            outputBookInformationModel.setSeqNr("We Need A Barcode For This");
            DateFormat df = new SimpleDateFormat("MMM/dd/yyyy");
            outputBookInformationModel.setCurrentDateCheckedOut("We Need A Barcode For This");
            outputBookInformationModel.setCurrentTermCheckOut("We Need A Barcode For This");
            outputBookInformationModel.setPreviousTermCheckedOut("We Need A Barcode For This");
            outputBookInformationModel.setPreviousDateCheckedIn("We Need A Barcode For This");
            outputBookInformationModel.setBookCode(nwtxinList.get(mostRecent).getBookCode());
            return outputBookInformationModel;
        } else{
            return null;
        }
    }

    //Return output based on book code and edition year
    public OutputBookInformationModel getNwtxdtByBookCodeAndYear(String bookCode, String editionYear){

        //List to contain data queried from database
        List<Nwtxin> nwtxinList = nwtxinRepository.findByBookCodeAndEditionYear(bookCode, editionYear);

        //If there is data, then populate output, else return nothing
        if(nwtxinList.size() >0){
            OutputBookInformationModel outputBookInformationModel = new OutputBookInformationModel();
            outputBookInformationModel.setBookCode(nwtxinList.get(0).getBookCode());
            outputBookInformationModel.setBarcode("No Specific Barcode Given");
            outputBookInformationModel.setEditionYear(nwtxinList.get(0).getEditionYear());
            outputBookInformationModel.setCurrentCheckedOutTo("We Need A Barcode For This");outputBookInformationModel.setPreviousCheckedOutTo("We Need A Barcode For This");
            outputBookInformationModel.setTitle(nwtxinList.get(0).getTitle());
            outputBookInformationModel.setCurrentDisposition("We Need A Barcode For This");
            outputBookInformationModel.setSeqNr("We Need A Barcode For This");
            DateFormat df = new SimpleDateFormat("MMM/dd/yyyy");
            outputBookInformationModel.setCurrentDateCheckedOut("We Need A Barcode For This");
            outputBookInformationModel.setCurrentTermCheckOut("We Need A Barcode For This");
            outputBookInformationModel.setPreviousTermCheckedOut("We Need A Barcode For This");
            outputBookInformationModel.setPreviousDateCheckedIn("We Need A Barcode For This");
            outputBookInformationModel.setBookCode(nwtxinList.get(0).getBookCode());
            return outputBookInformationModel;
        } else{
            return null;
        }
    }
}
