package com.webapp.TextBook.Service;

import com.webapp.TextBook.Model.Nwtxdt;
import com.webapp.TextBook.Model.Spriden;
import com.webapp.TextBook.Repository.NwtxdtRepository;
import com.webapp.TextBook.Repository.SpridenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoldBooksService {
    @Autowired
    private SpridenRepository spridenRepository;
    public Spriden getSpriden(String id) {
        List<Spriden> spridenList = spridenRepository.findById(id);
        //If there is at least 1 model under given credentials, return the first
        //Else, return nothing
        if (spridenList.size() > 0) {
            //Create a list of all repositories under given credentials
            return spridenList.get(0);
        } else{
            return null;
        }
    }

    @Autowired
    private NwtxdtRepository nwtxdtRepository;
    public List<Nwtxdt> getNwtxdt(String pidm){
        //If there is at least 1 model under given credentials, return the first
        //Else, return nothing
        List<Nwtxdt> nwtxdtList = nwtxdtRepository.findByPidm(pidm);
        if(nwtxdtList.size() >0){
            return nwtxdtList;
        } else{
            //Create a list of all repositories under given credentials
            return null;
        }
    }
}
