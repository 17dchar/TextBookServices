package com.webapp.TextBook.Repository;

import com.webapp.TextBook.Model.Nwtxbn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NwtxbnRepository extends JpaRepository<Nwtxbn, Integer>{
    @Override
    List<Nwtxbn> findAll();
}
