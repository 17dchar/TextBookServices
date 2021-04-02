package com.webapp.TextBook.Repository;

import com.webapp.TextBook.Model.Scbcrse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Repository
public interface ScbcrseRepository extends JpaRepository<Scbcrse, Integer>{
    @Override
    List<Scbcrse> findAll();

    List<Scbcrse> findBySubjCode(@NotEmpty String subjCode);
}
