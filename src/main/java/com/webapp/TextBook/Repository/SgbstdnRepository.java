package com.webapp.TextBook.Repository;

import com.webapp.TextBook.Model.Sgbstdn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SgbstdnRepository extends JpaRepository<Sgbstdn, Integer> {
    @Override
    List<Sgbstdn> findAll();
}
