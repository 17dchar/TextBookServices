package com.webapp.TextBook.Service;

import com.webapp.TextBook.Model.Nwtxin;
import com.webapp.TextBook.Repository.NwtxcmRepository;
import com.webapp.TextBook.Repository.NwtxinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChangeBarcodeService {
    @Autowired
    private NwtxinRepository nwtxinRepository;

    public Nwtxin getNwtxin(String bookCode, String bookYear) {
        if (nwtxinRepository.findByBookCodeAndEditionYear(bookCode, bookYear).size()
                > 0) {
            return nwtxinRepository.findByBookCodeAndEditionYear(bookCode,bookYear).get(0);
        } else{
            return null;
        }
    }

    public Nwtxin saveNwtxin(Nwtxin nwtxin){
        System.out.println("Saving Added Repository");
        return nwtxinRepository.save(nwtxin);
    }

    public void deleteNwtxin(String bookCode){
        System.out.println("Deleteing Line");
        if(nwtxinRepository.findByBookCode(bookCode) != null){
            nwtxinRepository.deleteById(bookCode);
        } else{
            System.out.println("No book found");
        }
    }
}
