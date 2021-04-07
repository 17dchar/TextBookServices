package com.webapp.TextBook.Service;

import com.webapp.TextBook.Model.Nwtxdt;
import org.springframework.beans.factory.annotation.Autowired;
import com.webapp.TextBook.Repository.NwtxdtRepository;
import org.springframework.stereotype.Service;

@Service
public class AddBookService {

    @Autowired private NwtxdtRepository nwtxdtRepository;
    public Nwtxdt saveNwtxdt(Nwtxdt nwtxdt) {
        return nwtxdtRepository.save(nwtxdt);
    }
}
