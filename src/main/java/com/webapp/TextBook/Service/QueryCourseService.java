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
    public List<String> getAllCourses(String bookCode){
        List<Nwtxin> nwtxinList = nwtxinRepository.findByBookCode(bookCode);
        List<String> stringList = new ArrayList<>();
        if (nwtxinList.size() >0){
            if(nwtxinList.get(0).getBookStatus().equals("C") || nwtxinList.get(0).getBookStatus() == null)
            if(nwtxinList.get(0).getCrse1() != null){
                stringList.add(nwtxinList.get(0).getCrse1());
            }
            if(nwtxinList.get(0).getCrse2() != null){
                stringList.add(nwtxinList.get(0).getCrse2());
            }
            if(nwtxinList.get(0).getCrse3() != null){
                stringList.add(nwtxinList.get(0).getCrse3());
            }
            if(nwtxinList.get(0).getCrse4() != null){
                stringList.add(nwtxinList.get(0).getCrse4());
            }
            if(nwtxinList.get(0).getCrse5() != null){
                stringList.add(nwtxinList.get(0).getCrse5());
            }
        }
        return stringList;
    }

}
