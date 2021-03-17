package com.webapp.TextBook.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
public class Addbookmodel {


    private String bookCode;
    private String bookYear;

    public Addbookmodel() {

    }

    public Addbookmodel(String bookCode, String bookYear) {
        this.bookCode = bookCode;
        this.bookYear = bookYear;
    }

    @Id
    /*
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
     */

    @Column(name = "bookYear", nullable = false)
    public String getBookCode() {
        return bookCode;
    }
    public void setBookCode(String bookCode){
        this.bookCode = bookCode;
    }

    @Column(name = "bookCode", nullable = false)
    public String getBookYear() {
        return bookYear;
    }
    public void setBookYear(String bookYear){
        this.bookYear = bookYear;
    }
    /*
    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "email_address", nullable = false)
    public String getEmailId() {
        return emailId;
    }
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
    */

}