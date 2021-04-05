package com.webapp.TextBook.Repository;

import com.webapp.TextBook.Model.Nwtxcm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NwtxcmRepository extends JpaRepository<Nwtxcm, Integer>{
    @Override
    List<Nwtxcm> findAll();
    List<Nwtxcm> findByCourse(String courseId);
}
