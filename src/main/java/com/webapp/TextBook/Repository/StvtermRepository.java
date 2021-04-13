package com.webapp.TextBook.Repository;

import com.webapp.TextBook.Model.Stvterm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StvtermRepository extends JpaRepository<Stvterm, Integer>{
    @Override
    List<Stvterm> findAll();
    List<Stvterm> findByCode(String code);
    List<Stvterm> findByCodeOrCodeOrCode(String code1, String code2, String code3);
}
