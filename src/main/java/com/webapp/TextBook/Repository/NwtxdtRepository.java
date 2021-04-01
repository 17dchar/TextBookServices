package com.webapp.TextBook.Repository;

import com.webapp.TextBook.Model.Nwtxdt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface NwtxdtRepository extends CrudRepository<Nwtxdt, String> {

    @Override
    List<Nwtxdt> findAll();

    List<Nwtxdt> findByBookCode(String bookCode);
    List<Nwtxdt> findByBarcode(String barcode);
    List<Nwtxdt> findByBookCodeAndEditionYearAndBarcode(String BookCode, String editionYear, String barcode);
}