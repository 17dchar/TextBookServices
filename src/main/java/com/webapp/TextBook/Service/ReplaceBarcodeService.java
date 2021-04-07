package com.webapp.TextBook.Service;

//Spring dependencies
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Textbook Services Dependencies
import com.webapp.TextBook.Repository.NwtxdtRepository;
import com.webapp.TextBook.Repository.NwtxinRepository;
import com.webapp.TextBook.Model.Nwtxdt;
import com.webapp.TextBook.Model.Nwtxin;

import java.util.List;

@Service
public class ReplaceBarcodeService {
    @Autowired
    private NwtxdtRepository nwtxdtRepository;
    //private NwtxinRepository nwtxinRepository;

    public Nwtxdt saveNwtxdt(Nwtxdt nwtxdt) {
        return nwtxdtRepository.save(nwtxdt);
    }

    public Nwtxdt getNwtxdt(String bookCode, String editionYear, String barcode){
        //Create a list of all repositories under given credentials
        List<Nwtxdt> nwtxdtList = nwtxdtRepository.findByBookCodeAndEditionYearAndBarcode(bookCode, editionYear, barcode);

        //If there is at least 1 model under given credentials, return the first
        //Else, return nothing
        if (nwtxdtList.size() > 0) {
            return nwtxdtList.get(0);
        }else{
            return null;
        }
    }

    public void deleteNwtxdt(String barcode) {
        //If there is at least 1 model under given credentials, delete them
        if (nwtxdtRepository.findByBarcode(barcode) != null) {
            nwtxdtRepository.deleteById(barcode);
        }
    }
}



