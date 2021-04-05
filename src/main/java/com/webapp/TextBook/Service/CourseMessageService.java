package com.webapp.TextBook.Service;

import com.webapp.TextBook.Model.Nwtxcm;
import com.webapp.TextBook.Repository.NwtxcmRepository;
import com.webapp.TextBook.Repository.ScbcrseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseMessageService {

    @Autowired private NwtxcmRepository nwtxcmRepository;
    public Nwtxcm getNwtxcm(String courseId) {
        if (nwtxcmRepository.findByCourse(courseId).size()
                > 0) {
            return nwtxcmRepository.findByCourse(courseId).get(0);
        } else{
            return null;
        }
    }

    public Nwtxcm saveNwtxcm(Nwtxcm nwtxcm){
        System.out.println("Saving Added Repository");
        return nwtxcmRepository.save(nwtxcm);

    }
}
