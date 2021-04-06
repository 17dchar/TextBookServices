package com.webapp.TextBook.Service;

import com.webapp.TextBook.Model.Nwtxdt;
import com.webapp.TextBook.Model.Nwtxin;
import org.springframework.beans.factory.annotation.Autowired;
import com.webapp.TextBook.Repository.NwtxdtRepository;
import com.webapp.TextBook.Repository.NwtxinRepository;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Component;

@Service
public class ReplaceBarcodeService {
    @Autowired
    private NwtxdtRepository nwtxdtRepository;
    private NwtxinRepository nwtxinRepository;

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

    public void deleteNwtxdt(String barcode) {
        System.out.println("Deleteing Line");
        if (nwtxdtRepository.findByBarcode(barcode) != null) {
            nwtxdtRepository.deleteById(barcode);
        } else {
            System.out.println("No book found");
        }
    }


    public Nwtxin getNwtxin(String bookCode, String editionYear, String barcode) {
        if (nwtxinRepository.findByBookCodeAndEditionYearAndTitle(bookCode, editionYear, barcode).size() > 0) {
            return nwtxinRepository.findByBookCodeAndEditionYearAndTitle(bookCode, editionYear, barcode).get(0);
        } else {
            return null;
        }
    }

    public Nwtxin saveNwtxin(Nwtxin nwtxin) {
        System.out.println("Saving Added Repository");
        return nwtxinRepository.save(nwtxin);
    }
}



