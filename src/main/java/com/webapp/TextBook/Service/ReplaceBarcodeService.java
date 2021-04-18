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

    public Nwtxdt getNwtxdtByBarcode(String barcode){
        //Create a list of all repositories under given credentials
        List<Nwtxdt> nwtxdtList = nwtxdtRepository.findByBarcode(barcode);

        //If there is at least 1 model under given credentials, return the first
        //Else, return nothing
        if (nwtxdtList.size() > 0) {
            return nwtxdtList.get(0);
        }else{
            return null;
        }
    }
    public Nwtxdt getMostRecentNwtxdt(String bookCode){
        List<Nwtxdt> nwtxdtList = nwtxdtRepository.findByBookCode(bookCode);
        if(nwtxdtList.size() >0){
            int mostRecent = 0;
            for(int i = 0; i < nwtxdtList.size(); i++){
                if(i > 0){
                    System.out.println(nwtxdtList.get(i).getEditionYear());
                    if(Integer.parseInt(nwtxdtList.get(i).getEditionYear()) > Integer.parseInt(nwtxdtList.get(i-1).getEditionYear())){
                        mostRecent= i;
                    }
                }
            }
            return nwtxdtList.get(mostRecent);
        } else{
            return null;
        }
    }

    public Nwtxdt getNwtxdtByBookCodeAndYear(String bookCode, String editionYear){
        List<Nwtxdt> nwtxdtList = nwtxdtRepository.findByBookCode(bookCode);
        if(nwtxdtList.size() >0){
            return nwtxdtList.get(0);
        } else{
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



