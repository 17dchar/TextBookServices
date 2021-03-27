package com.webapp.TextBook.Service;

import com.webapp.TextBook.Model.Nwtxdt;
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
public class BookQueryService {

    public boolean validateBook(String bookCode, String editionYear, String seqNm){

        return !bookCode.isEmpty() && !editionYear.isEmpty() && !seqNm.isEmpty();
    }

    public void logQuery(String bookCode, String editionYear, String seqNm){
        System.out.println("Book Code: " + bookCode);
        System.out.println("Edition Year: " + editionYear);
        System.out.println("Sequence Number: " + seqNm);
    }



}
