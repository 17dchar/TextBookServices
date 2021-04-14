package com.webapp.TextBook;

import com.webapp.TextBook.Model.Nwtxdt;
import com.webapp.TextBook.Repository.NwtxdtRepository;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Optional;

@TestConfiguration
public class TestsContextConfiguration {

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
