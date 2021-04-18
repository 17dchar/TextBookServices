package com.webapp.TextBook.Service;

//Spring Dependencies
import com.webapp.TextBook.Model.Nwtxin;
import com.webapp.TextBook.Repository.NwtxinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

//Textbook Services Dependencies
import com.webapp.TextBook.Repository.NwtxdtRepository;
import com.webapp.TextBook.Model.Nwtxdt;


@Service
public class BookQueryService {

    @Autowired private NwtxdtRepository nwtxdtRepository;
    @Autowired private NwtxinRepository nwtxinRepository;

    public Nwtxin getNwtxdtByBarcode(String barcode){
        //Create a list of all repositories under given credentials
        List<Nwtxdt> nwtxdtList = nwtxdtRepository.findByBarcode(barcode);

        //If there is at least 1 model under given credentials, return the first
        //Else, return nothing
        if (nwtxdtList.size() > 0) {
            List<Nwtxin> nwtxinList = nwtxinRepository.findByBookCodeAndEditionYear(nwtxdtList.get(0).getBookCode(),nwtxdtList.get(0).getEditionYear());
            if(nwtxinList.size() > 0){
                return nwtxinList.get(0);
            }
        }
        return null;
    }

    public Nwtxin getMostRecentNwtxdt(String bookCode){
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
            return nwtxinList.get(mostRecent);
        } else{
            return null;
        }
    }
    public Nwtxin getNwtxdtByBookCodeAndYear(String bookCode, String editionYear){
        List<Nwtxin> nwtxinList = nwtxinRepository.findByBookCodeAndEditionYear(bookCode, editionYear);
        if(nwtxinList.size() >0){
            return nwtxinList.get(0);
        } else{
            return null;
        }
    }
}
