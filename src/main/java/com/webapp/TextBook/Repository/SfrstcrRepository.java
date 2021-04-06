package com.webapp.TextBook.Repository;

import com.webapp.TextBook.Model.Sfrstcr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SfrstcrRepository extends JpaRepository<Sfrstcr, Integer>{
    @Override
    List<Sfrstcr> findAll();
    List<Sfrstcr> findAllByTermCodeAndPidm(String termCode, int pidm);
}
