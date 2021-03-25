package com.webapp.TextBook.repository;

import com.webapp.TextBook.Model.Sfrstcr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SfrstcrRepository extends JpaRepository<Sfrstcr, Integer>{
    @Override
    List<Sfrstcr> findAll();
}
