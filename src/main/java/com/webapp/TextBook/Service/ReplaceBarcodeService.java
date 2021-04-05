package com.webapp.TextBook.Service;

import com.webapp.TextBook.Model.Nwtxdt;
import org.springframework.beans.factory.annotation.Autowired;
import com.webapp.TextBook.Repository.NwtxdtRepository;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Component;

@Service
public class ReplaceBarcodeService {
    @Autowired
    private NwtxdtRepository nwtxdtRepository;

    public Nwtxdt saveNwtxdt(Nwtxdt nwtxdt) {
        System.out.println("Saving Added Repository");
        return nwtxdtRepository.save(nwtxdt);
    }

    public Nwtxdt getNwtxdt(String bookCode, String editionYear, String barcode) {
        if (nwtxdtRepository.findByBookCodeAndEditionYearAndBarcode(bookCode, editionYear, barcode).size() > 0) {
            return nwtxdtRepository.findByBookCodeAndEditionYearAndBarcode(bookCode, editionYear, barcode).get(0);
        } else {
            return null;
        }
    }
}
