package com.webapp.TextBook.Service;

//Spring Dependencies
import com.webapp.TextBook.Model.Nwtxdt;
import com.webapp.TextBook.Model.Nwtxin;
import com.webapp.TextBook.Repository.NwtxdtRepository;
import com.webapp.TextBook.Repository.NwtxinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

//Textbook Services Dependencies
import com.webapp.TextBook.Repository.NwtxcmRepository;
import com.webapp.TextBook.Model.Nwtxcm;


@Service
public class MaintenanceService {

    @Autowired
    private NwtxinRepository nwtxinRepository;
    public Nwtxin getMostRecentNwtxin(String bookCode) {
        //Create a list of all repositories under given credentials
        List<Nwtxin> nwtxinList = nwtxinRepository.findByBookCode(bookCode);

        //If there is at least 1 model under given credentials, return the first
        //Else, return nothing
        if (nwtxinList.size() > 0) {
            int mostRecent = 0;
            for(int i = 0; i < nwtxinList.size(); i++){
                if(i > 0){
                    if(Integer.parseInt(nwtxinList.get(i).getEditionYear()) > Integer.parseInt(nwtxinList.get(i-1).getEditionYear())){
                        mostRecent= i;
                    }
                }
            }
            System.out.println(nwtxinList.get(mostRecent).getEditionYear());
            return nwtxinList.get(mostRecent);
        }else{
            return null;
        }
    }
    public Nwtxin getNwtxin(String bookCode, String editionYear) {
        //Create a list of all repositories under given credentials
        List<Nwtxin> nwtxinList = nwtxinRepository.findByBookCodeAndEditionYear(bookCode, editionYear);

        //If there is at least 1 model under given credentials, return the first
        //Else, return nothing
        if (nwtxinList.size() > 0) {
            int mostRecent = 0;
            for(int i = 0; i < nwtxinList.size(); i++){
                if(i > 0){
                    if(Integer.parseInt(nwtxinList.get(i).getEditionYear()) > Integer.parseInt(nwtxinList.get(i-1).getEditionYear())){
                        mostRecent= i;
                    }
                }
            }
            System.out.println(nwtxinList.get(mostRecent).getEditionYear());
            return nwtxinList.get(mostRecent);
        }else{
            return null;
        }
    }
    public List<Nwtxin> getNwtxinList(String bookCode) {
        //Create a list of all repositories under given credentials
        List<Nwtxin> nwtxinList = nwtxinRepository.findByBookCode(bookCode);

        //If there is at least 1 model under given credentials, return the first
        //Else, return nothing
        if (nwtxinList.size() > 0) {
            return nwtxinList;
        }else{
            return null;
        }
    }

    @Autowired
    private NwtxdtRepository nwtxdtRepository;
    public List<Nwtxdt> getNwtxdtList(String bookCode, String editionYear) {
        //Create a list of all repositories under given credentials
        List<Nwtxdt> nwtxdtList = nwtxdtRepository.findByBookCodeAndEditionYear(bookCode, editionYear);
        //If there is at least 1 model under given credentials, return the first
        //Else, return nothing
        if (nwtxdtList.size() > 0) {
            return nwtxdtList;
        }else{
            return null;
        }
    }

}
