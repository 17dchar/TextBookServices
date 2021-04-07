package com.webapp.TextBook.Service;

//Spring Dependencies
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Textbook Services Dependencies
import com.webapp.TextBook.Repository.NwtxdtRepository;
import com.webapp.TextBook.Model.Nwtxdt;

@Service
public class AddBookService {

    @Autowired private NwtxdtRepository nwtxdtRepository;
    public Nwtxdt saveNwtxdt(Nwtxdt nwtxdt) {
        return nwtxdtRepository.save(nwtxdt);
    }
}
