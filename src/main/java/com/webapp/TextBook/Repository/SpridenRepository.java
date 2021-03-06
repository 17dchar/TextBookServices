package com.webapp.TextBook.Repository;

import com.webapp.TextBook.Model.Spriden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpridenRepository extends JpaRepository<Spriden, Integer>{
    @Override
    List<Spriden> findAll();
    List<Spriden> findById(String id);
    List<Spriden> findByPidm(String pidm);
}
