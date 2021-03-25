package com.webapp.TextBook.repository;
import com.webapp.TextBook.Model.Scbcrse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NwtxarRepository extends JpaRepository<Nwtxar, Integer>{
    @Override
    List<Nwtxar> findAll();
}
