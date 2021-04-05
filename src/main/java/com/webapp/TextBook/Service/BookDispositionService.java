package com.webapp.TextBook.Service;

import com.webapp.TextBook.Model.Nwtxdt;
import com.webapp.TextBook.Model.Nwtxin;
import com.webapp.TextBook.Repository.NwtxdtRepository;
import com.webapp.TextBook.Repository.NwtxinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class BookDispositionService {

    @Autowired private NwtxdtRepository nwtxdtRepository;
    @Autowired private NwtxinRepository nwtxinRepository;

    public Nwtxdt saveNwtxdt(Nwtxdt nwtxdt) {
        return nwtxdtRepository.save(nwtxdt);
    }

    public Nwtxdt getNwtxdt(String bookCode, String editionYear, String barcode) {
        if(nwtxdtRepository.findByBookCodeAndEditionYearAndBarcode(bookCode, editionYear, barcode).size() > 0){
            return nwtxdtRepository.findByBookCodeAndEditionYearAndBarcode(bookCode, editionYear, barcode).get(0);
        } else{
            return null;
        }
    }

    public Nwtxin getNwtxin(String bookCode) {
        if(nwtxinRepository.findByBookCode(bookCode).size() > 0){
            return nwtxinRepository.findByBookCode(bookCode).get(0);
        } else{
            return null;
        }
    }
}
