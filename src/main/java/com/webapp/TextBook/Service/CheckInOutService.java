package com.webapp.TextBook.Service;

//Spring Dependencies
import com.webapp.TextBook.Model.*;
import com.webapp.TextBook.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

//Textbook Services Dependencies


@Service
public class CheckInOutService {

    @Autowired private StvtermRepository stvtermRepository;
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
    private SpridenRepository spridenRepository;
    public Spriden getStudent(String id){
        List<Spriden> student = spridenRepository.findById(id);
        return student.get(0);
    }
    @Autowired
    private NwtxdtRepository nwtxdtRepository;
    public int checkAvailability(String barcode, Spriden spriden, String term){
        List<Nwtxdt> bookInfo = nwtxdtRepository.findByBarcode(barcode);
        if(bookInfo.size() > 0){
            Nwtxdt nwtxdt = bookInfo.get(0);
            if(nwtxdt.getPidm() !=null){
                if(nwtxdt.getPidm().equals(spriden.getPidm())){
                    nwtxdt.setPrevPidm(nwtxdt.getPidm());
                    nwtxdt.setPrevTerm(nwtxdt.getTerm());
                    nwtxdt.setTerm(null);
                    nwtxdt.setPidm(null);
                    nwtxdtRepository.save(nwtxdt);
                    return 3;
                } else{
                    nwtxdt.setPrevPidm(nwtxdt.getPidm());
                    nwtxdt.setPrevTerm(nwtxdt.getTerm());
                    nwtxdt.setPidm(spriden.getPidm());
                    nwtxdt.setTerm(term);
                    nwtxdtRepository.save(nwtxdt);
                    return 2;
                }
            } else{
                nwtxdt.setPidm(spriden.getPidm());
                nwtxdt.setTerm(term);
                nwtxdtRepository.save(nwtxdt);
                return 1;
            }
        } else{
            return 0;
        }
    }
    @Autowired
    private NwtxbnRepository nwtxbnRepository;
    public String getBag(String pidm){
        List<Nwtxbn> nwtxbnList = nwtxbnRepository.findByPidm(pidm);
        if(nwtxbnList.size() > 0){
            return nwtxbnList.get(0).getBagNum();
        } else{
            return null;
        }
    }
}
