package com.webapp.TextBook.Service;

import com.webapp.TextBook.Model.Nwtxdt;
import com.webapp.TextBook.Model.Spriden;
import com.webapp.TextBook.Repository.NwtxdtRepository;
import com.webapp.TextBook.Repository.SpridenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreviousBooksService {

    @Autowired
    private SpridenRepository spridenRepository;
    public Spriden getSpriden(String id) {
        if (spridenRepository.findById(id).size()
                > 0) {
            return spridenRepository.findById(id).get(0);
        } else{
            return null;
        }
    }

    @Autowired
    private NwtxdtRepository nwtxdtRepository;
    public List<Nwtxdt> getNwtxdt(String pidm){
        if(nwtxdtRepository.findByPidm(pidm).size() >0){
            return nwtxdtRepository.findByPidm(pidm);

        } else{
            return null;
        }
    }
}
