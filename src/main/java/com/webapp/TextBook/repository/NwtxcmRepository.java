package com.webapp.TextBook.repository;

import com.webapp.TextBook.Model.Nwtxcm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NwtxbnRepository extends JpaRepository<Nwtxcm, Integer>{
    @Override
    List<Nwtxcm> findAll();
}
