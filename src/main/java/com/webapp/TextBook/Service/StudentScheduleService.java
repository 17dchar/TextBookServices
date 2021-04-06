package com.webapp.TextBook.Service;

import com.webapp.TextBook.Model.*;
import com.webapp.TextBook.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentScheduleService {
    @Autowired private ScbcrseRepository scbcrseRepository;
    @Autowired private StvtermRepository stvtermRepository;

    public Stvterm getStvterm(String code){
        if (stvtermRepository.findByCode(code).size()
                > 0) {
            return stvtermRepository.findByCode(code).get(0);
        } else{
            return null;
        }
    }

    @Autowired
    private SsrmeetRepository ssrmeetRepository;
    public Ssrmeet getSsrmeet(String term, String crn){
        if (ssrmeetRepository.findByTermCodeAndCrn(term, crn).size()
                > 0) {
            return ssrmeetRepository.findByTermCodeAndCrn(term, crn).get(0);
        } else{
            return null;
        }
    }

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
    private SfrverfRepository sfrverfRepository;
    public List<Sfrverf> getSfrverf(String pidm, String term){
        return sfrverfRepository.findByPidmAndTermCode(pidm, term);
    }

    @Autowired
    private SfrstcrRepository sfrstcrRepository;
    public List<Sfrstcr> getSfrstcr(int pidm, String termCode){
        return sfrstcrRepository.findAllByTermCodeAndPidm(termCode, pidm);
    }

    @Autowired
    private SsbsectRepository ssbsectRepository;
    public Ssbsect getSsbsect(String crn, String term){
        List<Ssbsect> ssbsects = ssbsectRepository.findByCrnAndTermCode(crn, term);
        if(ssbsects.size()>0){
            return ssbsectRepository.findByCrnAndTermCode(crn, term).get(0);
        }
        else{
            return null;
        }
    }

    public Scbcrse getScbcrse(String subjCode, String crseNumb) {
        if (scbcrseRepository.findBySubjCodeAndCrseNumb(subjCode, crseNumb).size()
                > 0) {
            return scbcrseRepository.findBySubjCodeAndCrseNumb(subjCode, crseNumb).get(0);
        } else{
            return null;
        }
    }
}
