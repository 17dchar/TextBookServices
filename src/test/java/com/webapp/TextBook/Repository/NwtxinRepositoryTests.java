package com.webapp.TextBook.Repository;


import com.webapp.TextBook.Model.Nwtxin;
import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.*;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
public class NwtxinRepositoryTests {

    @TestConfiguration
    static class NwtxinRepositoryContextConfiguration {

        @Bean
        public NwtxinRepository nwtxinRepository(){
            return new NwtxinRepository(){

                @Override
                public List<Nwtxin> findAll() {
                    List<Nwtxin> nwtxinList = new ArrayList<Nwtxin>();
                    Nwtxin nwtxin = new Nwtxin();
                    nwtxin.setAuthor("Helen Keller");
                    nwtxinList.add(nwtxin);
                    return nwtxinList;
                }

                @Override
                public List<Nwtxin> findAll(Sort sort) {
                    return null;
                }

                @Override
                public Page<Nwtxin> findAll(Pageable pageable) {
                    return null;
                }

                @Override
                public List<Nwtxin> findAllById(Iterable<String> iterable) {
                    return null;
                }

                @Override
                public long count() {
                    return 0;
                }

                @Override
                public <S extends Nwtxin> S save(S s) {
                    return null;
                }

                @Override
                public <S extends Nwtxin> List<S> saveAll(Iterable<S> iterable) {
                    return null;
                }

                @Override
                public Optional<Nwtxin> findById(String s) {
                    return Optional.empty();
                }

                @Override
                public boolean existsById(String s) {
                    return false;
                }

                @Override
                public void flush() {

                }

                @Override
                public <S extends Nwtxin> S saveAndFlush(S s) {
                    return null;
                }

                @Override
                public void deleteInBatch(Iterable<Nwtxin> iterable) {

                }

                @Override
                public void deleteAllInBatch() {

                }

                @Override
                public Nwtxin getOne(String s) {
                    return null;
                }

                @Override
                public <S extends Nwtxin> Optional<S> findOne(Example<S> example) {
                    return Optional.empty();
                }

                @Override
                public <S extends Nwtxin> List<S> findAll(Example<S> example) {
                    return null;
                }

                @Override
                public <S extends Nwtxin> List<S> findAll(Example<S> example, Sort sort) {
                    return null;
                }

                @Override
                public <S extends Nwtxin> Page<S> findAll(Example<S> example, Pageable pageable) {
                    return null;
                }

                @Override
                public <S extends Nwtxin> long count(Example<S> example) {
                    return 0;
                }

                @Override
                public <S extends Nwtxin> boolean exists(Example<S> example) {
                    return false;
                }

                @Override
                public List<Nwtxin> findByBookCodeAndEditionYear(String bookCode, String editionYear) {
                    List<Nwtxin> nwtxinList = new ArrayList<Nwtxin>();
                    Nwtxin nwtxin = new Nwtxin();
                    nwtxin.setAuthor("Helen Keller");
                    nwtxinList.add(nwtxin);
                    return nwtxinList;
                }

                @Override
                public List<Nwtxin> findByBookCode(String bookCode) {
                    List<Nwtxin> nwtxinList = new ArrayList<Nwtxin>();
                    Nwtxin nwtxin = new Nwtxin();
                    nwtxin.setAuthor("Helen Keller");
                    nwtxinList.add(nwtxin);
                    return nwtxinList;
                }

                @Override
                public List<Nwtxin> findDistinctEditionYearByBookCode(String bookCode) {
                    List<Nwtxin> nwtxinList = new ArrayList<Nwtxin>();
                    Nwtxin nwtxin = new Nwtxin();
                    nwtxin.setAuthor("Helen Keller");
                    nwtxinList.add(nwtxin);
                    return nwtxinList;
                }

                @Override
                public void deleteById(String id) {

                }

                @Override
                public void delete(Nwtxin nwtxin) {

                }

                @Override
                public void deleteAll(Iterable<? extends Nwtxin> iterable) {

                }

                @Override
                public void deleteAll() {

                }

                @Override
                public List<Nwtxin> findByBookCodeAndEditionYearAndTitle(String bookCode, String editionYear, String bookTitle) {
                    return null;
                }
            };
        }

    }

    @Autowired
    private NwtxinRepository nwtxinRepository;

    String bookCode = "GEOG5110";
    String editionYear = "2003";
    String author = "Helen Keller";

    @Test
    public void testFindAll(){
        List<Nwtxin> modelList = nwtxinRepository.findAll();
        assertEquals(author,modelList.get(0).getAuthor());
    }

    @Test
    public void testFindByBookCodeAndEditionYear(){
        List<Nwtxin> modelList = nwtxinRepository.findByBookCodeAndEditionYear(bookCode,editionYear);
        assertEquals(author,modelList.get(0).getAuthor());
    }

    @Test
    public void testFindByBarcode(){
        List<Nwtxin> modelList = nwtxinRepository.findByBookCode(bookCode);
        assertEquals(author,modelList.get(0).getAuthor());
    }

    @Test
    public void testFindByPidm(){
        List<Nwtxin> modelList = nwtxinRepository.findDistinctEditionYearByBookCode(bookCode);
        assertEquals(author,modelList.get(0).getAuthor());
    }

}
