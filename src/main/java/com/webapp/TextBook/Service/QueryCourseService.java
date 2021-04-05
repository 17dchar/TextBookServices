package com.webapp.TextBook.Service;

import com.webapp.TextBook.Model.Scbcrse;
import com.webapp.TextBook.Repository.ScbcrseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueryCourseService {
    @Autowired private ScbcrseRepository scbcrseRepository;

    public Scbcrse getScbcrse(String subjCode) {
    if (scbcrseRepository.findBySubjCode(subjCode).size()
        > 0) {
            return scbcrseRepository.findBySubjCode(subjCode).get(0);
        } else{
            return null;
        }
    }
}
