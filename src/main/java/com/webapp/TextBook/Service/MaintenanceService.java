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

    //If there are any changes between data given and data that is in database, save those changes
    public boolean hasDifferences(Nwtxin nwtxin, Nwtxin originalNwtxin) {
        Boolean changes = false;
        if(nwtxin.getFirstUsedDate() != null){
            if(!nwtxin.getFirstUsedDate().equals(originalNwtxin.getFirstUsedDate())){
                changes = true;
            }
        }
        if(nwtxin.getTitle() != null){
            if(!nwtxin.getTitle().equals(originalNwtxin.getTitle())){
                changes = true;
                originalNwtxin.setTitle(nwtxin.getTitle());
            }
        }
        if(nwtxin.getAuthor() != null){
            if(!nwtxin.getAuthor().equals(originalNwtxin.getAuthor())){
                changes = true;
                originalNwtxin.setAuthor(nwtxin.getAuthor());
            }
        }
        if(nwtxin.getPublisher() != null){
            if(!nwtxin.getPublisher().equals(originalNwtxin.getPublisher())){
                changes = true;
                originalNwtxin.setPublisher(nwtxin.getPublisher());
            }
        }
        if(nwtxin.getBookStatus() != null){
            if(!nwtxin.getBookStatus().equals(originalNwtxin.getBookStatus())){
                changes = true;
                originalNwtxin.setBookStatus(nwtxin.getBookStatus());
            }
        }

        if(nwtxin.getCurrentPrice() != null){
            if(!nwtxin.getCurrentPrice().equals(originalNwtxin.getCurrentPrice())){
                changes = true;
                originalNwtxin.setCurrentPrice(nwtxin.getCurrentPrice());
            }
        }
        if(nwtxin.getPruchaseDate() != null){
            if(!nwtxin.getPruchaseDate().equals(originalNwtxin.getPruchaseDate())){
                changes = true;
                originalNwtxin.setPruchaseDate(nwtxin.getPruchaseDate());
            }
        }
        if(nwtxin.getFirstUsedDate() != null){
            if(!nwtxin.getFirstUsedDate().equals(originalNwtxin.getFirstUsedDate())){
                changes = true;
                originalNwtxin.setFirstUsedDate(nwtxin.getFirstUsedDate());
            }
        }
        if(nwtxin.getDiscontinuedDate() != null){
            if(!nwtxin.getDiscontinuedDate().equals(originalNwtxin.getDiscontinuedDate())){
                changes = true;
                originalNwtxin.setDiscontinuedDate(nwtxin.getDiscontinuedDate());
            }
        }
        if(nwtxin.getActivityDate() != null){
            if(!nwtxin.getActivityDate().equals(originalNwtxin.getActivityDate())){
                changes = true;
                originalNwtxin.setActivityDate(nwtxin.getActivityDate());
            }
        }
        if(nwtxin.getCrseName() != null){
            if(!nwtxin.getCrseName().equals(originalNwtxin.getCrseName())){
                changes = true;
                originalNwtxin.setCrseName(nwtxin.getCrseName());
            }
        }
        if(nwtxin.getCrse1() != null){
            if(!nwtxin.getCrse1().equals(originalNwtxin.getCrse1())){
                changes = true;
                originalNwtxin.setCrse1(nwtxin.getCrse1());
            }
        }
        if(nwtxin.getCrse2() != null){
            if(!nwtxin.getCrse2().equals(originalNwtxin.getCrse2())){
                changes = true;
                originalNwtxin.setCrse2(nwtxin.getCrse2());
            }
        }
        if(nwtxin.getCrse3() != null){
            if(!nwtxin.getCrse3().equals(originalNwtxin.getCrse3())){
                changes = true;
                originalNwtxin.setCrse3(nwtxin.getCrse3());
            }
        }
        if(nwtxin.getCrse4() != null){
            if(!nwtxin.getCrse4().equals(originalNwtxin.getCrse4())){
                changes = true;
                originalNwtxin.setCrse4(nwtxin.getCrse4());
            }
        }
        if(nwtxin.getCrse5() != null){
            if(!nwtxin.getCrse5().equals(originalNwtxin.getCrse5())){
                changes = true;
                originalNwtxin.setCrse5(nwtxin.getCrse5());
            }
        }
        if(nwtxin.getComment() != null){
            if(!nwtxin.getComment().equals(originalNwtxin.getComment())){
                changes = true;
                originalNwtxin.setComment(nwtxin.getComment());
            }
        }

        //If there is at least 1 model under given credentials, return the first
        //Else, return nothing
        if(changes){
            nwtxinRepository.save(originalNwtxin);
        }
        return changes;
    }

    public void save(Nwtxin nwtxin) {
        nwtxinRepository.save(nwtxin);
    }
}
