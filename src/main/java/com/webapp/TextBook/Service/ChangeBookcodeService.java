package com.webapp.TextBook.Service;


//Spring Dependencies
import com.webapp.TextBook.Model.*;
import com.webapp.TextBook.Repository.NwtxdtRepository;
import com.webapp.TextBook.Repository.SpridenRepository;
import com.webapp.TextBook.Repository.StvtermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

//Textbook Services Dependencies
import com.webapp.TextBook.Repository.NwtxinRepository;


@Service
public class ChangeBookcodeService {

    @Autowired private NwtxdtRepository nwtxdtRepository;
    @Autowired private NwtxinRepository nwtxinRepository;
    @Autowired private SpridenRepository spridenRepository;
    @Autowired private StvtermRepository stvtermRepository;

    //Changes book code and edition year
    public void changeByBookCodeAndEditionYear(String bookCode, String editionYear, String newBookCode, String newEditionYear){

        //List of data queried from database
        List<Nwtxin> nwtxinList = nwtxinRepository.findByBookCodeAndEditionYear(bookCode, editionYear);
        //If there is data, make changes, else do nothing
        if( nwtxinList.size() > 0 ){
            System.out.println("found one");
            nwtxinList.get(0).setBookCode(newBookCode);
            nwtxinList.get(0).setEditionYear(newEditionYear);
            //nwtxinRepository.deleteByBookCodeAndEditionYearAndTitle(bookCode,editionYear, nwtxinList.get(0).getTitle());
            nwtxinRepository.save(nwtxinList.get(0));
        }
    }

    //Get most recent nwtxdt
    public OutputBookInformationModel getMostRecentNwtxdt(String bookCode){

        //List of data queried from database
        List<Nwtxin> nwtxinList = nwtxinRepository.findByBookCode(bookCode);

        //If there is data, populate output, else return nothing
        if(nwtxinList.size() >0){

            //Find most recent edition year
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

    //Find nwtxdt from book code and edition year
    public OutputBookInformationModel getNwtxdtByBookCodeAndYear(String bookCode, String editionYear){
        //List for data quried from database
        List<Nwtxin> nwtxinList = nwtxinRepository.findByBookCodeAndEditionYear(bookCode, editionYear);

        //If there is data, populate output, else return nothing
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
