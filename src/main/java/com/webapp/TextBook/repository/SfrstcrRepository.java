package com.webapp.TextBook.repository;

import com.webapp.TextBook.Model.;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SfrstcrRepository extends JpaRepository<Sfrstcer, Integer>{
    @Override
    List<Sfrstcr> findAll();
}
