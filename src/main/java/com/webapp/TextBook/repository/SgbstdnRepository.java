package com.webapp.TextBook.repository;

import com.webapp.TextBook.Model.Ssbsect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SgbstdnRepository extends JpaRepository<Sgbstdn, Integer> {
    @Override
    List<Sgbstdn> findAll();
}