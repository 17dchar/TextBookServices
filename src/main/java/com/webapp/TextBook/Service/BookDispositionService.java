package com.webapp.TextBook.Service;

import com.webapp.TextBook.Model.Nwtxdt;
import com.webapp.TextBook.Model.Spriden;
import com.webapp.TextBook.Repository.NwtxdtRepository;
import com.webapp.TextBook.Repository.SpridenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookDispositionService {


    @Autowired
    private NwtxdtRepository nwtxdtRepository;
    public Nwtxdt getNwtxdt(String bookCode, String editionYear, String barcode) {
        if(nwtxdtRepository.findByBookCodeAndEditionYearAndBarcode(bookCode, editionYear, barcode).size() > 0){
            return nwtxdtRepository.findByBookCodeAndEditionYearAndBarcode(bookCode, editionYear, barcode).get(0);
        } else{
            return null;
        }
    }

    public Nwtxdt setNwtxdt(Nwtxdt nwtxdt){
        return nwtxdtRepository.save(nwtxdt);
    }
}
