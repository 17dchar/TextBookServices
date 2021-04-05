package com.webapp.TextBook.Repository;

import com.webapp.TextBook.Model.Nwtxin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NwtxinRepository extends JpaRepository<Nwtxin, Integer>{
    @Override
    List<Nwtxin> findAll();
    List<Nwtxin> findByBookCode(String bookCode);
}
