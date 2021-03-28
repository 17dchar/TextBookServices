package com.webapp.TextBook.Service;

import com.webapp.TextBook.Model.Nwtxdt;
import com.webapp.TextBook.Repository.NwtxdtRepository;
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

    @Autowired
    private NwtxdtRepository nwtxdtRepository;

    public Nwtxdt saveNwtxdt(Nwtxdt nwtxdt){
        return nwtxdtRepository.save(nwtxdt);
    }

    public Nwtxdt getNwtxdt(String bookCode){
        System.out.println("This is the book code: " + bookCode);
        System.out.println("Returns: " + nwtxdtRepository.findByBookCode(bookCode).size() + " Length \n" +
                "of Book Code " + bookCode);
        return nwtxdtRepository.findById(bookCode).orElse(null);
    }

    public void testNwtxdt(){
        System.out.println("Testing Database connection");
        System.out.println("Your Book Code, Fine Sir: " + nwtxdtRepository.findAll().get(0).getBookCode());
    }

    public void logQuery(String bookCode, String editionYear, String seqNm){
        System.out.println("Book Code: " + bookCode);
        System.out.println("Edition Year: " + editionYear);
        System.out.println("Sequence Number: " + seqNm);
    }
/*

       OLD CODE DO NOT USE

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Nwtxdt nwtxdt;
    public Nwtxdt queryDatabase(String bookCode, String editionYear, String seqNm){

        jdbcTemplate.update("INSERT INTO SCBCRSE VALUES(?,?,?)", bookCode, editionYear, seqNm);

        return nwtxdt;
    }
    private Nwtxdt nwtxdtTemp;
    private static List<Nwtxdt> books = new ArrayList<Nwtxdt>();
    public void queryDatabaseTerm(String bookCode, String editionYear, String seqNm){
        String sql = "SELECT * FROM NWTXDT WHERE NWTXDT_BOOK_CODE = 'POLS1020' AND NWTXDT_EDITION_YEAR = '2017' and NWTXDT_SEQ_NR = '366'";

        books = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Nwtxdt.class));
        System.out.println("Query Success");
        System.out.println(books.get(0).getPidm());
    }


 */

}
