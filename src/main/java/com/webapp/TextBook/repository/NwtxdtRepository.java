package com.webapp.TextBook.repository;

import com.webapp.TextBook.Model.Nwtxdt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NwtxdtRepository extends JpaRepository<Nwtxdt, Integer>{
    @Override
    List<Nwtxdt> findAll();
}
