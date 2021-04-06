package com.webapp.TextBook.Repository;

import com.webapp.TextBook.Model.Ssrmeet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SsrmeetRepository extends JpaRepository<Ssrmeet, Integer>{
    @Override
    List<Ssrmeet> findAll();
    List<Ssrmeet> findByTermCodeAndCrn(String termCode, String crn);
}
