package com.webapp.TextBook.Service;

import com.webapp.TextBook.Model.*;
import com.webapp.TextBook.Model.OutputBookInformationModel;
import com.webapp.TextBook.Repository.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class AddBookServiceTests {

    @Autowired
    private AddBookService addBookService;
    private OutputBookInformationModel outputBookInformationModel;
    private NwtxinRepository nwtxinRepository;


    @Before
    public void setUp() throws Exception{
        nwtxinRepository = new NwtxinRepository() {
            @Override
            public List<Nwtxin> findAll() {
                return null;
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
                return null;
            }

            @Override
            public List<Nwtxin> findByBookCode(String bookCode) {
                return null;
            }

            @Override
            public List<Nwtxin> findDistinctEditionYearByBookCode(String bookCode) {
                return null;
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
            public void deleteByBookCodeAndEditionYearAndTitle(String bookCode, String editionYear, String title) {

            }

            @Override
            public List<Nwtxin> findByCrse1OrCrse2OrCrse3OrCrse4OrCrse5(String course1, String course2, String course3, String course4, String course5) {
                return null;
            }

            @Override
            public List<Nwtxin> findByBookCodeAndEditionYearAndTitle(String bookCode, String editionYear, String bookTitle) {
                return null;
            }
        };
        addBookService = new AddBookService(nwtxinRepository);
        outputBookInformationModel = new OutputBookInformationModel();
    }

    @Test
    public void testGetMostRecentNwtxdt(){
        addBookService.getMostRecentNwtxdt("GEOL1140");
        assertFalse(outputBookInformationModel == null);
    }

}
