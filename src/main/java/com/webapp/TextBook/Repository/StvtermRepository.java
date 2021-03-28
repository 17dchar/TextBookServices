package com.webapp.TextBook.repository;

import com.webapp.TextBook.Model.Stvterm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StvtermRepository extends JpaRepository<Stvterm, Integer>{
    @Override
    List<Stvterm> findAll();
}
