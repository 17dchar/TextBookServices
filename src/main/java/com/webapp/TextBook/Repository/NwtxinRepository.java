package com.webapp.TextBook.Repository;

import com.webapp.TextBook.Model.Nwtxin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NwtxinRepository extends JpaRepository<Nwtxin, String>{
    @Override
    List<Nwtxin> findAll();
    List<Nwtxin> findByBookCodeAndEditionYear(String bookCode, String editionYear);
    List<Nwtxin> findByBookCode(String bookCode);
    @Modifying
    @Query(value = "DELETE FROM NWTXIN WHERE NWTXIN_BOOK_CODE = :id",nativeQuery = true)
    void deleteById(@Param("id")String id);

    List<Nwtxin> findByBookCodeAndEditionYearAndTitle(String bookCode, String editionYear, String bookTitle);

}
