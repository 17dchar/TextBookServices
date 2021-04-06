package com.webapp.TextBook.Repository;

import com.webapp.TextBook.Model.Ssbsect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SsbsectRepository extends JpaRepository<Ssbsect, Integer>{
    @Override
    List<Ssbsect> findAll();
    List<Ssbsect> findByCrnAndTermCode(String crn, String termCode);
}

