package com.webapp.TextBook.repository;

import com.webapp.TextBook.Model.Ssbsect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SsbsectRepository extends JpaRepository<Ssbsect, Integer>{
    @Override
    List<Ssbsect> findAll();
}

