package com.webapp.TextBook.Service;

import com.webapp.TextBook.Model.Nwtxdt;
import com.webapp.TextBook.Model.Spriden;
import com.webapp.TextBook.Model.Stvterm;
import com.webapp.TextBook.Repository.NwtxdtRepository;
import com.webapp.TextBook.Repository.SpridenRepository;
import com.webapp.TextBook.Repository.StvtermRepository;
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
    private StvtermRepository stvtermRepository;
    public Stvterm getStvterm(String term){
        if(stvtermRepository.findByCode(term).size() > 0){
            return stvtermRepository.findByCode(term).get(0);
        } else{
            return null;
        }
    }


    @Autowired
    private NwtxdtRepository nwtxdtRepository;
    public List<Nwtxdt> getNwtxdt(String pidm, String prevTerm){
        if(nwtxdtRepository.findByPidmAndPrevTerm(pidm, prevTerm).size() >0){
            return nwtxdtRepository.findByPidmAndPrevTerm(pidm, prevTerm);

        } else{
            return null;
        }
    }
}
