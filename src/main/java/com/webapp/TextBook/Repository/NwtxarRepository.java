package com.webapp.TextBook.Repository;
import com.webapp.TextBook.Model.Nwtxar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NwtxarRepository extends JpaRepository<Nwtxar, Integer>{
    @Override
    List<Nwtxar> findAll();
}
