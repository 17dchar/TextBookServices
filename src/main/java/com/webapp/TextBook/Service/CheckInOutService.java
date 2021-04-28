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
    @Autowired private SfrverfRepository sfrverfRepository;

    //Find latest term of student
    public Stvterm getLatestTerm(String pidm){

        //List of data queried from database
        List<Sfrverf> sfrverfList = sfrverfRepository.findByPidm(Integer.parseInt(pidm));

        //Find most recent term
        int mostRecent = 0;
        for(Sfrverf sfrverf : sfrverfList){
            if(Integer.parseInt(sfrverf.getTermCode()) > mostRecent){
                mostRecent = Integer.parseInt(sfrverf.getTermCode());
            }
        }

        //List to contain latest term code query
        List<Stvterm> stvtermList = stvtermRepository.findByCode(Integer.toString(mostRecent));

        //If latest term code is correlated to a term, return it, else return nothing
        if(stvtermList.size() > 0){
            return stvtermList.get(0);
        } else{
            return null;
        }
    }
    @Autowired
    private SpridenRepository spridenRepository;

    //Get student form 919 number
    public Spriden getStudent(String id){
        List<Spriden> student = spridenRepository.findById(id);
        return student.get(0);
    }
    @Autowired
    private NwtxdtRepository nwtxdtRepository;

    //Check in and out book
    public int checkAvailability(String barcode, Spriden spriden, String term){

        //List for data queried from database
        List<Nwtxdt> bookInfo = nwtxdtRepository.findByBarcode(barcode);

        //If book code is correlated to database spot, continue, else return 0 (error code)
        if(bookInfo.size() > 0){
            Nwtxdt nwtxdt = bookInfo.get(0);

            //If there is a current person this is checked out to, check it in for them and
            //check it out to new person
            if(nwtxdt.getPidm() !=null){

                //If the person that this book is currently checked out to, notify and check it in
                //for said person
                if(nwtxdt.getPidm().equals(spriden.getPidm())){
                    nwtxdt.setPrevPidm(nwtxdt.getPidm());
                    nwtxdt.setPrevTerm(nwtxdt.getTerm());
                    nwtxdt.setTerm(null);
                    nwtxdt.setPidm(null);
                    nwtxdtRepository.save(nwtxdt);
                    return 3;
                } else{
                    //If it isn't then logically the person it is checked out to is someone else.
                    //Check it in for that person then check it out to this person
                    nwtxdt.setPrevPidm(nwtxdt.getPidm());
                    nwtxdt.setPrevTerm(nwtxdt.getTerm());
                    nwtxdt.setPidm(spriden.getPidm());
                    nwtxdt.setTerm(term);
                    nwtxdtRepository.save(nwtxdt);
                    return 2;
                }
            } else{
                //Was not checked out to anyone, so check it out to person
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

    //Get bag number
    public String getBag(String pidm){
        //List to contain data queried from database
        List<Nwtxbn> nwtxbnList = nwtxbnRepository.findByPidm(pidm);

        //If data was found, return it, else return nothing
        if(nwtxbnList.size() > 0){
            return nwtxbnList.get(0).getBagNum();
        } else{
            return null;
        }
    }

    //Get books for pidm based on term code
    public List<Nwtxdt> getBooks(String pidm, String termCode){
        //List to contain data queried from database
        List<Nwtxdt> nwtxdtList = nwtxdtRepository.findByPidmAndTerm(pidm, termCode);

        //If data was found, return it, else return nothing
        if(nwtxdtList.size() > 0){
            return nwtxdtList;
        } else{
            return null;
        }
    }
}
