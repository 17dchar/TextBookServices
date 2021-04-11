package com.webapp.TextBook.Service;

//Spring Dependencies
import com.webapp.TextBook.Model.Nwtxin;
import com.webapp.TextBook.Repository.NwtxinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

//Textbook Services Dependencies
import com.webapp.TextBook.Repository.NwtxcmRepository;
import com.webapp.TextBook.Model.Nwtxcm;


@Service
public class MaintenanceService {

    @Autowired
    private NwtxinRepository nwtxinRepository;
    public Nwtxin getNwtxin(String bookCode) {
        //Create a list of all repositories under given credentials
        List<Nwtxin> nwtxinList = nwtxinRepository.findByBookCode(bookCode);

        //If there is at least 1 model under given credentials, return the first
        //Else, return nothing
        if (nwtxinList.size() > 0) {
            return nwtxinList.get(0);
        }else{
            return null;
        }
    }
    public List<Nwtxin> getNwtxinList(String bookCode) {
        //Create a list of all repositories under given credentials
        List<Nwtxin> nwtxinList = nwtxinRepository.findDistinctEditionYearByBookCode(bookCode);

        //If there is at least 1 model under given credentials, return the first
        //Else, return nothing
        if (nwtxinList.size() > 0) {
            return nwtxinList;
        }else{
            return null;
        }
    }

}
