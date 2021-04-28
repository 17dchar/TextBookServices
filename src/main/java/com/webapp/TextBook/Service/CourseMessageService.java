package com.webapp.TextBook.Service;

//Spring Dependencies
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

//Textbook Services Dependencies
import com.webapp.TextBook.Repository.NwtxcmRepository;
import com.webapp.TextBook.Model.Nwtxcm;


@Service
public class CourseMessageService {

    @Autowired private NwtxcmRepository nwtxcmRepository;

    //Get course
    public Nwtxcm getNwtxcm(String courseId) {
        //Create a list of all repositories under given credentials
        List<Nwtxcm> nwtxcmList = nwtxcmRepository.findByCourse(courseId);

        //If there is at least 1 model under given credentials, return the first
        //Else, return nothing
        if (nwtxcmList.size() > 0) {
            return nwtxcmList.get(0);
        }else{
            return null;
        }
    }

    //Save Changes
    public Nwtxcm saveNwtxcm(Nwtxcm nwtxcm){
        System.out.println("Saving Added Repository");
        return nwtxcmRepository.save(nwtxcm);

    }
}
