package com.webapp.TextBook.Service;

//Spring Dependencies
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
}
