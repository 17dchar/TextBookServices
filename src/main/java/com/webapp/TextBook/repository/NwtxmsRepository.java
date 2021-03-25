package com.webapp.TextBook.repository;

import com.webapp.TextBook.Model.Nwtxms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NwtxmsRepository extends JpaRepository<Nwtxdt, Integer>{
    @Override
    List<Nwtxms> findAll();
}
