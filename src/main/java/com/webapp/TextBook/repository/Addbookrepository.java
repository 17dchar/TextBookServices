package com.webapp.TextBook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webapp.TextBook.Model.Addbookmodel;

@Repository
public interface Addbookrepository extends JpaRepository< Addbookmodel, Long>{

}
