package com.webapp.TextBook.Service;

//Spring Dependencies
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Textbook Services Dependencies
import com.webapp.TextBook.Repository.NwtxdtRepository;
import com.webapp.TextBook.Model.Nwtxdt;

import java.util.List;

@Service
public class BookDispositionService {

    @Autowired
    private NwtxdtRepository nwtxdtRepository;
    public Nwtxdt setNwtxdt(Nwtxdt nwtxdt){
        return nwtxdtRepository.save(nwtxdt);
    }

    public Nwtxdt getNwtxdt(String bookCode, String editionYear, String barcode) {
        //Create a list of all repositories under given credentials
        List<Nwtxdt> nwtxdtList = nwtxdtRepository.findByBookCodeAndEditionYearAndBarcode(bookCode, editionYear, barcode);

        //If there is at least 1 model under given credentials, return the first
        //Else, return nothing
        if(nwtxdtList.size() > 0){
            return nwtxdtList.get(0);
        }else{
            return null;
        }
    }

}
