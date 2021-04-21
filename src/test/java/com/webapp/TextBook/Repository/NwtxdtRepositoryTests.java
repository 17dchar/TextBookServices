package com.webapp.TextBook.Repository;

import com.webapp.TextBook.Model.Nwtxdt;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
class NwtxdtRepositoryTests {

    final String bookCode = "GEOL1140";
    final String editionYear = "2018";
    final String pidm = "510194";
    final String barCode = "3075102588400+";
    final String prevTerm = "202110";
    final String prevPidm = "536706";

    @Autowired
    private NwtxdtRepository nwtxdtRepository;

    @Test
    public void testFindAll(){
        List<Nwtxdt> nwtxdts = nwtxdtRepository.findAll();
        assertEquals(prevPidm, nwtxdts.get(0).getPrevPidm());
    }

    public void testFindByBookCode(){
        List<Nwtxdt> nwtxdts = nwtxdtRepository.findByBookCode(bookCode);
        assertEquals(prevPidm, nwtxdts.get(0).getPrevPidm());
    }

    public void testFindByBarcode(){
        List<Nwtxdt> nwtxdts = nwtxdtRepository.findByBarcode(barCode);
        assertEquals(prevPidm, nwtxdts.get(0).getPrevPidm());
    }

    public void testFindByPidm(){
        List<Nwtxdt> nwtxdts = nwtxdtRepository.findByPidm(pidm);
        assertEquals(prevPidm, nwtxdts.get(0).getPrevPidm());
    }

    public void testFindByPidmAndPrevTerm(){
        List<Nwtxdt> nwtxdts = nwtxdtRepository.findByPidmAndPrevTerm(pidm, prevTerm);
        assertEquals(prevPidm, nwtxdts.get(0).getPrevPidm());
    }

    public void testFindByBookCodeAndEditionYearAndBarcode(){
        List<Nwtxdt> nwtxdts = nwtxdtRepository.findByBookCodeAndEditionYearAndBarcode(bookCode, editionYear, barCode);
        assertEquals(prevPidm, nwtxdts.get(0).getPrevPidm());
    }

    public void testFindByBookCodeAndEditionYear(){
        List<Nwtxdt> nwtxdts = nwtxdtRepository.findByBookCodeAndEditionYear(bookCode, editionYear);
        assertEquals(prevPidm, nwtxdts.get(0).getPrevPidm());
    }
}
