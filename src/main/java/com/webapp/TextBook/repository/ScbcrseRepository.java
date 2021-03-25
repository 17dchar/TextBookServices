package com.webapp.TextBook.repository;

import com.webapp.TextBook.Model.Scbcrse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScbcrseRepository extends JpaRepository<Scbrse, Integer>{
    @Override
    List<Spriden> findAll();
}
