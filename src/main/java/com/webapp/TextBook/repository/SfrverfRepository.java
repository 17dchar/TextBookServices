package com.webapp.TextBook.repository;
import com.webapp.TextBook.Model.Sfrverf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SfrverfRepository extends JpaRepository<Sfrverf, Integer> {
    @Override
    List<Sfrverf> findAll();
}
