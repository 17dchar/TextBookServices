package com.webapp.TextBook.Service;

import com.webapp.TextBook.Model.*;
import com.webapp.TextBook.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
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
    public List<Stvterm> getLatestTerms(){
        Calendar cal = Calendar.getInstance();
        int currentMonth = cal.get(Calendar.MONTH)+1;
        int currentYear = cal.get(Calendar.YEAR);
        List<Stvterm> stvtermList;
        if(currentMonth <5){
            stvtermList= stvtermRepository.findByCodeOrCodeOrCode(Integer.toString(currentYear)+"10",Integer.toString(currentYear)+"20",Integer.toString(currentYear)+"30");
        } else if(currentMonth < 8){
            stvtermList= stvtermRepository.findByCodeOrCodeOrCode(Integer.toString(currentYear)+"20",Integer.toString(currentYear)+"30",Integer.toString(currentYear+1)+"10");
        } else{
            stvtermList= stvtermRepository.findByCodeOrCodeOrCode(Integer.toString(currentYear)+"30",Integer.toString(currentYear+1)+"10",Integer.toString(currentYear+1)+"20");
        }
        return stvtermList;
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
        List<Ssbsect> ssbsectList = ssbsectRepository.findByCrnAndTermCode(crn, term);
        //If there is at least 1 model under given credentials, return the first
        //Else, return nothing
        if(ssbsectList.size()>0){
            return ssbsectList.get(0);
        }
        else{
            return null;
        }
    }

    public Scbcrse getScbcrse(String subjCode, String crseNumb) {
        List<Scbcrse> scbcrseList = scbcrseRepository.findBySubjCodeAndCrseNumb(subjCode, crseNumb);
        //If there is at least 1 model under given credentials, return the first
        //Else, return nothing
        if (scbcrseList.size() > 0) {
            return scbcrseList.get(0);
        } else{
            return null;
        }
    }
}
