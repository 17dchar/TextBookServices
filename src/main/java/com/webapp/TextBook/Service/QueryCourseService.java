package com.webapp.TextBook.Service;

//Spring Dependencies
import com.webapp.TextBook.Model.Nwtxin;
import com.webapp.TextBook.Repository.NwtxinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//Textbook Services Dependencies
import com.webapp.TextBook.Repository.ScbcrseRepository;
import com.webapp.TextBook.Model.Scbcrse;


@Service
public class QueryCourseService {
    @Autowired private ScbcrseRepository scbcrseRepository;
    public Scbcrse getScbcrse(String subjCode) {
        //Create a list of all repositories under given credentials
        List<Scbcrse> scbcrseList = scbcrseRepository.findBySubjCode(subjCode);

        //If there is at least 1 model under given credentials, return the first
        //Else, return nothing
        if (scbcrseList.size() > 0) {
            return scbcrseList.get(0);
        }else{
            return null;
        }
    }

    @Autowired private NwtxinRepository nwtxinRepository;

    //Find all books for course
    public List<Nwtxin> getAllBooksForCourse(String course){

        //List to hold all data queried from database
        List<Nwtxin> nwtxinList = nwtxinRepository.findByCrse1OrCrse2OrCrse3OrCrse4OrCrse5(course, course, course, course, course);
        List<Nwtxin> nwtxinListFiltered = new ArrayList<Nwtxin>();

        //If there is data, continue, else return nothing
        if (nwtxinList.size() >0){

            //For all books found, check if they are still continued ("C") or nothing is shown
            //Then check if they are part of the course list. If so, add them to the filtered list
            for(Nwtxin nwtxin: nwtxinList){
                if(nwtxin.getBookStatus() == null || nwtxin.getBookStatus().equals("C")){
                    System.out.println("through here");
                    if(nwtxin.getCrse1() != null && nwtxin.getCrse1().equals(course)){
                        nwtxinListFiltered.add(nwtxin);
                    }
                    if(nwtxin.getCrse2() != null && nwtxin.getCrse2().equals(course)){
                        nwtxinListFiltered.add(nwtxin);
                    }
                    if(nwtxin.getCrse3() != null && nwtxin.getCrse3().equals(course)){
                        nwtxinListFiltered.add(nwtxin);
                    }
                    if(nwtxin.getCrse4() != null && nwtxin.getCrse4().equals(course)){
                        nwtxinListFiltered.add(nwtxin);
                    }
                    if(nwtxin.getCrse5() != null && nwtxin.getCrse5().equals(course)){
                        nwtxinListFiltered.add(nwtxin);
                    }
                }
            }
        }
        return nwtxinListFiltered;
    }

}
