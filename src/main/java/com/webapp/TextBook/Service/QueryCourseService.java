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
    public List<Nwtxin> getAllBooksForCourse(String course){
        List<Nwtxin> nwtxinList = nwtxinRepository.findByCrse1OrCrse2OrCrse3OrCrse4OrCrse5(course, course, course, course, course);
        List<Nwtxin> nwtxinListFiltered = new ArrayList<Nwtxin>();
        if (nwtxinList.size() >0){
            for(Nwtxin nwtxin: nwtxinList){
                if(nwtxin.getBookStatus().equals("C") || nwtxinList.get(0).getBookStatus() == null){
                    if(nwtxin.getCrse1() != course){
                        nwtxinListFiltered.add(nwtxin);
                    }
                    if(nwtxin.getCrse2() != course){
                        nwtxinListFiltered.add(nwtxin);
                    }
                    if(nwtxin.getCrse3() != course){
                        nwtxinListFiltered.add(nwtxin);
                    }
                    if(nwtxin.getCrse4() != course){
                        nwtxinListFiltered.add(nwtxin);
                    }
                    if(nwtxin.getCrse5() != course){
                        nwtxinListFiltered.add(nwtxin);
                    }
                }

            }

        }
        return nwtxinListFiltered;
    }

}
