package com.webapp.TextBook;


import com.webapp.TextBook.Model.Nwtxdt;
import com.webapp.TextBook.Repository.NwtxdtRepository;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
public class NwtxdtRepositoryTests {

    @TestConfiguration
    static class NwtxdtRepositoryContextConfiguration {

        @Bean
        public NwtxdtRepository nwtxdtRepository(){
            return new NwtxdtRepository(){
                @Override
                public <S extends Nwtxdt> S save(S s) {
                    return null;
                }

                @Override
                public <S extends Nwtxdt> Iterable<S> saveAll(Iterable<S> iterable) {
                    return null;
                }

                @Override
                public Optional<Nwtxdt> findById(String s) {
                    return Optional.empty();
                }

                @Override
                public boolean existsById(String s) {
                    return false;
                }

                @Override
                public List<Nwtxdt> findAll() {
                    return null;
                }

                @Override
                public Iterable<Nwtxdt> findAllById(Iterable<String> iterable) {
                    return null;
                }

                @Override
                public long count() {
                    return 0;
                }

                @Override
                public List<Nwtxdt> findByBookCode(String bookCode) {
                    return null;
                }

                @Override
                public List<Nwtxdt> findByBarcode(String barcode) {
                    return null;
                }

                @Override
                public List<Nwtxdt> findByPidm(String pidm) {
                    return null;
                }

                @Override
                public List<Nwtxdt> findByPidmAndPrevTerm(String pidm, String prevTerm) {
                    return null;
                }

                @Override
                public List<Nwtxdt> findByBookCodeAndEditionYearAndBarcode(String BookCode, String editionYear, String barcode) {
                    return null;
                }

                @Override
                public void deleteById(String id) {

                }

                @Override
                public void delete(Nwtxdt nwtxdt) {

                }

                @Override
                public void deleteAll(Iterable<? extends Nwtxdt> iterable) {

                }

                @Override
                public void deleteAll() {

                }
            };
        }

    }

    @Autowired
    private NwtxdtRepository nwtxdtRepository;

    String bookCode = "GEOG5110";
    String barCode = "307510115836/";
    String pidm = "34449";
    String prevTerm = "200730";
    String editionYear = "2003";
    String prevPidm = "73753";

    @Test
    public void testFindAll(){
        List<Nwtxdt> modelList = nwtxdtRepository.findAll();
        assertEquals(prevPidm,modelList.get(0).getPrevPidm());
    }

    public void testFindByBookCode(){
        List<Nwtxdt> modelList = nwtxdtRepository.findByBookCode(bookCode);
        assertEquals(prevPidm,modelList.get(0).getPrevPidm());
    }

    public void testFindByBarcode(){
        List<Nwtxdt> modelList = nwtxdtRepository.findByBarcode(barCode);
        assertEquals(prevPidm,modelList.get(0).getPrevPidm());
    }

    public void testFindByPidm(){
        List<Nwtxdt> modelList = nwtxdtRepository.findByPidm(pidm);
        assertEquals(prevPidm,modelList.get(0).getPrevPidm());
    }

    public void testFindByPidmAndPrevTerm(){
        List<Nwtxdt> modelList = nwtxdtRepository.findByPidmAndPrevTerm(pidm,prevTerm);
        assertEquals(prevPidm,modelList.get(0).getPrevPidm());
    }

    public void testFindByBookCodeAndEditionYearAndBarcode(){
        List<Nwtxdt> modelList = nwtxdtRepository.findByBookCodeAndEditionYearAndBarcode(bookCode,editionYear,barCode);
        assertEquals(prevPidm,modelList.get(0).getPrevPidm());
    }

}
