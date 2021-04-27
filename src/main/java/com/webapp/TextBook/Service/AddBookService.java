package com.webapp.TextBook.Service;

//Spring Dependencies
import com.webapp.TextBook.Model.*;
import com.webapp.TextBook.Repository.NwtxinRepository;
import com.webapp.TextBook.Repository.SpridenRepository;
import com.webapp.TextBook.Repository.StvtermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Textbook Services Dependencies
import com.webapp.TextBook.Repository.NwtxdtRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class AddBookService {

    @Autowired private NwtxdtRepository nwtxdtRepository;

    //Saves Nwtxdt to database
    public Nwtxdt saveNwtxdt(Nwtxdt nwtxdt) {
        return nwtxdtRepository.save(nwtxdt);
    }


    //Gets Most recent Nwtxin based off of book code
    @Autowired private NwtxinRepository nwtxinRepository;
    public OutputBookInformationModel getMostRecentNwtxdt(String bookCode){
        //List to hold all data based off of query
        List<Nwtxin> nwtxinList = nwtxinRepository.findByBookCode(bookCode);

        //If the query holds any information, look for most recent edition year out of all information
        if(nwtxinList.size() >0){
            int mostRecent = 0;
            for(int i = 0; i < nwtxinList.size(); i++){
                if(i > 0){
                    System.out.println(nwtxinList.get(i).getEditionYear());
                    if(Integer.parseInt(nwtxinList.get(i).getEditionYear()) > Integer.parseInt(nwtxinList.get(i-1).getEditionYear())){
                        mostRecent= i;
                    }
                }
            }
            List<Nwtxdt> nwtxdtList = nwtxdtRepository.findByBookCodeAndEditionYear(bookCode, nwtxinList.get(mostRecent).getEditionYear());
            int biggestSeqNr = 0;
            for(Nwtxdt nwtxdt: nwtxdtList){
                if(Integer.parseInt(nwtxdt.getSeqNr()) > biggestSeqNr){
                    biggestSeqNr = Integer.parseInt(nwtxdt.getSeqNr());
                }
            }


            //Set all information to output model. **IMPORTANT** We set a lot of it to nothing becasue
            //We did not have a sequence number to go with it, but we can do that now with the code from
            //Maintenance in home controller! This can easily be updated.
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
            outputBookInformationModel.setLastSeqNr(biggestSeqNr);
            return outputBookInformationModel;
        } else{
            return null;
        }
    }

    //Finds by book code and edition year
    public OutputBookInformationModel getNwtxdtByBookCodeAndYear(String bookCode, String editionYear){

        //List to hold data from query
        List<Nwtxin> nwtxinList = nwtxinRepository.findByBookCodeAndEditionYear(bookCode, editionYear);

        //If query has information, populate based off of index 0
        if(nwtxinList.size() >0){
            List<Nwtxdt> nwtxdtList = nwtxdtRepository.findByBookCodeAndEditionYear(bookCode, editionYear);
            int biggestSeqNr = 0;
            for(Nwtxdt nwtxdt: nwtxdtList){
                if(Integer.parseInt(nwtxdt.getSeqNr()) > biggestSeqNr){
                    biggestSeqNr = Integer.parseInt(nwtxdt.getSeqNr());
                }
            }

            //Populates data. We should create another integer that holds the index value
            //of the biggest sequence number so we can populate these based of that book
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
            outputBookInformationModel.setLastSeqNr(biggestSeqNr);
            outputBookInformationModel.setBookCode(nwtxinList.get(0).getBookCode());
            return outputBookInformationModel;
        } else{
            return null;
        }
    }

}
