package com.webapp.TextBook.Repository;

import com.webapp.TextBook.Model.Nwtxbn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NwtxbnRepository extends CrudRepository<Nwtxbn, String> {
    @Override
    List<Nwtxbn> findAll();
    List<Nwtxbn> findByPidm(String pidm);
}
