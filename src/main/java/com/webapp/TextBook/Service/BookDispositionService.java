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
public class BookDispositionService {

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
            List<Nwtxin> nwtxinList = nwtxinRepository.findByBookCodeAndEditionYear(nwtxdtList.get(0).getBookCode(),nwtxdtList.get(0).getEditionYear());
            if(nwtxinList.size() > 0){
                List<Spriden> currentPersonSpriden = spridenRepository.findByPidm(nwtxdtList.get(0).getPidm());
                List<Spriden> previousPersonSpriden = spridenRepository.findByPidm(nwtxdtList.get(0).getPrevPidm());
                List<Stvterm> currentTerm = stvtermRepository.findByCode(nwtxdtList.get(0).getTerm());
                List<Stvterm> previousTerm = stvtermRepository.findByCode(nwtxdtList.get(0).getPrevTerm());
                OutputBookInformationModel outputBookInformationModel = new OutputBookInformationModel();
                outputBookInformationModel.setBookCode(nwtxdtList.get(0).getBookCode());
                outputBookInformationModel.setBarcode(nwtxdtList.get(0).getBarcode());
                outputBookInformationModel.setEditionYear(nwtxdtList.get(0).getEditionYear());
                if(currentPersonSpriden.size()>0){
                    outputBookInformationModel.setCurrentCheckedOutTo(currentPersonSpriden.get(0).getFirstName() + " " + currentPersonSpriden.get(0).getMiddleInitial() + " " + currentPersonSpriden.get(0).getLastName());
                } else{
                    outputBookInformationModel.setCurrentCheckedOutTo("Currently Isn't Checked Out To Anyone");
                }
                if(previousPersonSpriden.size()>0){
                    outputBookInformationModel.setPreviousCheckedOutTo(previousPersonSpriden.get(0).getFirstName() + " " + previousPersonSpriden.get(0).getMiddleInitial() + " " + previousPersonSpriden.get(0).getLastName());
                } else{
                    outputBookInformationModel.setPreviousCheckedOutTo("No Previous Person Checked Out To");
                }
                outputBookInformationModel.setTitle(nwtxinList.get(0).getTitle());
                outputBookInformationModel.setCurrentDisposition(nwtxdtList.get(0).getDisposition());
                outputBookInformationModel.setSeqNr(nwtxdtList.get(0).getSeqNr());
                DateFormat df = new SimpleDateFormat("MMM/dd/yyyy");
                if(nwtxdtList.get(0).getDateCheckedOut() != null){
                    outputBookInformationModel.setCurrentDateCheckedOut(df.format(nwtxdtList.get(0).getDateCheckedOut()));
                }
                if(currentTerm.size() > 0){
                    outputBookInformationModel.setCurrentTermCheckOut(currentTerm.get(0).getDesc());
                } else{
                    outputBookInformationModel.setCurrentTermCheckOut("Couldn't Find a Term Description for: " + nwtxdtList.get(0).getTerm());
                }
                if(previousTerm.size() > 0){
                    outputBookInformationModel.setPreviousTermCheckedOut(previousTerm.get(0).getDesc());
                } else{
                    outputBookInformationModel.setPreviousTermCheckedOut("Couldn't Find a Term Description for: " + nwtxdtList.get(0).getPrevTerm());
                }
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
        List<Nwtxin> nwtxinList = nwtxinRepository.findByBookCode(bookCode);
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
    public OutputBookInformationModel getNwtxdtByBookCodeAndYear(String bookCode, String editionYear){
        List<Nwtxin> nwtxinList = nwtxinRepository.findByBookCodeAndEditionYear(bookCode, editionYear);
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
