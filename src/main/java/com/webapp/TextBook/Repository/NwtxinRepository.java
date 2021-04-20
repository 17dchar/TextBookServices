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
    //@Modifying
    //@Query(value = "SELECT * FROM Nwtxin WHERE NWTXIN_BOOK_CODE = :bookCode", nativeQuery = true)
    List<Nwtxin> findDistinctEditionYearByBookCode(String bookCode);
    @Modifying
    @Query(value = "DELETE FROM NWTXIN WHERE NWTXIN_BOOK_CODE = :id",nativeQuery = true)
    void deleteById(@Param("id")String id);

    @Modifying
    @Query(value = "DELETE FROM NWTXIN WHERE NWTXIN_BOOK_CODE = :bookCode AND NWTXIN_EDITION_YEAR = :editionYear AND NWTXIN_TITLE = :title" , nativeQuery = true)
    void deleteByBookCodeAndEditionYearAndTitle(@Param("bookCode") String bookCode, @Param("editionYear") String editionYear, @Param("title") String title);
    
    List<Nwtxin> findByCrse1OrCrse2OrCrse3OrCrse4OrCrse5(String course1,String course2,String course3,String course4,String course5);

    List<Nwtxin> findByBookCodeAndEditionYearAndTitle(String bookCode, String editionYear, String bookTitle);

}
