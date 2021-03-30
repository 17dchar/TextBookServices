package com.webapp.TextBook.Service;

import com.webapp.TextBook.Model.AddBookModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Component;

import com.webapp.TextBook.Model.AddBookModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class AddBookService {

    private static List<AddBookModel> books = new ArrayList<AddBookModel>();

    static {
        books.add(new AddBookModel("test", "new", "DEMO", "one"));
    }


    public void addToModel(String bookCode, String bookTitle, String bookYear, String seqNm){
        books.add(new AddBookModel(bookYear, bookCode, bookTitle, seqNm));
    }


    public void logBook(String bookCode, String bookTitle, String bookYear, String seqNm){

        System.out.println(books.get(books.size()-1).getBookTitle());
        System.out.println(books.get(books.size()-1).getBookCode());
        System.out.println(books.get(books.size()-1).getBookYear());
        System.out.println(books.get(books.size()-1).getSeqNr());
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void postToDatabase(String bookCode, String bookTitle, String bookYear, String seqNm){
        jdbcTemplate.update("INSERT INTO SCBCRSE VALUES(?,?,?,?)", bookCode, bookTitle, bookYear, seqNm);
    }
    //Create Model class for all tables
    // Implement findAll(), findByID(1234) ==> repository
    //Create a demo jsp where you can get the repo data
    //Things to notice:
    /*
    @Id for the model class
    map to the repo <Location, Integer>
    look into spriden repoistory how you can implement native query.
    Look into student model for date mapping .
*/
}
