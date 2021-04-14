package com.webapp.TextBook.Repository;

import com.webapp.TextBook.Model.Nwtxms;
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
public class NwtxmsRepositoryTests {

    @TestConfiguration
    static class NwtxmsRepositoryContextConfiguration {

        @Bean
        public NwtxmsRepository nwtxmsRepository(){
            return new NwtxmsRepository(){

                @Override
                public List<Nwtxms> findAll() {
                    List<Nwtxms> nwtxmsList = new ArrayList<Nwtxms>();
                    Nwtxms nwtxms = new Nwtxms();
                    nwtxms.setMessage("Unpaid Bill");
                    nwtxmsList.add(nwtxms);
                    return nwtxmsList;
                }

                @Override
                public List<Nwtxms> findAll(Sort sort) {
                    return null;
                }

                @Override
                public Page<Nwtxms> findAll(Pageable pageable) {
                    return null;
                }

                @Override
                public List<Nwtxms> findAllById(Iterable<Integer> iterable) {
                    return null;
                }

                @Override
                public long count() {
                    return 0;
                }

                @Override
                public void deleteById(Integer integer) {

                }

                @Override
                public void delete(Nwtxms nwtxms) {

                }

                @Override
                public void deleteAll(Iterable<? extends Nwtxms> iterable) {

                }

                @Override
                public void deleteAll() {

                }

                @Override
                public <S extends Nwtxms> S save(S s) {
                    return null;
                }

                @Override
                public <S extends Nwtxms> List<S> saveAll(Iterable<S> iterable) {
                    return null;
                }

                @Override
                public Optional<Nwtxms> findById(Integer integer) {
                    return Optional.empty();
                }

                @Override
                public boolean existsById(Integer integer) {
                    return false;
                }

                @Override
                public void flush() {

                }

                @Override
                public <S extends Nwtxms> S saveAndFlush(S s) {
                    return null;
                }

                @Override
                public void deleteInBatch(Iterable<Nwtxms> iterable) {

                }

                @Override
                public void deleteAllInBatch() {

                }

                @Override
                public Nwtxms getOne(Integer integer) {
                    return null;
                }

                @Override
                public <S extends Nwtxms> Optional<S> findOne(Example<S> example) {
                    return Optional.empty();
                }

                @Override
                public <S extends Nwtxms> List<S> findAll(Example<S> example) {
                    return null;
                }

                @Override
                public <S extends Nwtxms> List<S> findAll(Example<S> example, Sort sort) {
                    return null;
                }

                @Override
                public <S extends Nwtxms> Page<S> findAll(Example<S> example, Pageable pageable) {
                    return null;
                }

                @Override
                public <S extends Nwtxms> long count(Example<S> example) {
                    return 0;
                }

                @Override
                public <S extends Nwtxms> boolean exists(Example<S> example) {
                    return false;
                }
            };
        }

    }

    @Autowired
    private NwtxmsRepository nwtxmsRepository;

    String pidm = "34449";
    String message = "Unpaid Bill";

    @Test
    public void testFindAll(){
        List<Nwtxms> modelList = nwtxmsRepository.findAll();
        assertEquals(message,modelList.get(0).getMessage());
    }

}
