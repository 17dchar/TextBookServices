package com.webapp.TextBook.Service;


//Spring Dependencies
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

//Textbook Services Dependencies
import com.webapp.TextBook.Repository.NwtxinRepository;
import com.webapp.TextBook.Model.Nwtxin;


@Service
public class ChangeBookcodeService {
    @Autowired
    private NwtxinRepository nwtxinRepository;

    public Nwtxin getNwtxin(String bookCode, String bookYear) {
        //Create a list of all repositories under given credentials
        List<Nwtxin> nwtxinList = nwtxinRepository.findByBookCodeAndEditionYear(bookCode, bookYear);

        //If there is at least 1 model under given credentials, return the first
        //Else, return nothing
        if (nwtxinList.size() > 0) {
            return nwtxinList.get(0);
        }else{
            return null;
        }
    }

    public Nwtxin saveNwtxin(Nwtxin nwtxin){
        return nwtxinRepository.save(nwtxin);
    }

    public void deleteNwtxin(String bookCode){
        //If there is at least 1 model under given credentials, delete them
        if(nwtxinRepository.findByBookCode(bookCode) != null){
            nwtxinRepository.deleteById(bookCode);
        }
    }
}
