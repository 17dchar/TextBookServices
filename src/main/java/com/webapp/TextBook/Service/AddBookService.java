package com.webapp.TextBook.Service;

import com.webapp.TextBook.Model.Nwtxdt;
import org.springframework.beans.factory.annotation.Autowired;
import com.webapp.TextBook.Repository.NwtxdtRepository;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Component;


@Service
public class AddBookService {

    @Autowired private NwtxdtRepository nwtxdtRepository;
    public Nwtxdt saveNwtxdt(Nwtxdt nwtxdt) {
        System.out.println("Saving Added Repository");
        return nwtxdtRepository.save(nwtxdt);
    }
    /*
      Create Model class for all tables
      Implement findAll(), findByID(1234) ==> repository
      Create a demo jsp where you can get the repo data
      Things to notice:
        @Id for the model class
        map to the repo <Location, Integer>
        look into spriden repoistory how you can implement native query.
        Look into student model for date mapping .
    */
}
