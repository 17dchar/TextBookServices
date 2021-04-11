package com.webapp.TextBook.Model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@Table(name = "NWTXIN")
public class Nwtxin {
    @Column(name = "NWTXIN_BOOK_CODE")
    @NotEmpty
    private String bookCode;
    @Column (name = "NWTXIN_EDITION_YEAR")
    @NotEmpty
    private String editionYear;
    @Id
    @Column (name = "NWTXIN_TITLE")
    private String title;
    @Column(name = "NWTXIN_AUTHOR")
    private String author;
    @Column(name = "NWTXIN_PUBLISHER")
    private String publisher;
    @Column(name = "NWTXIN_BOOK_STATUS")
    private String bookStatus;
    @Column (name = "NWTXIN_CURRENT_PRICE")
    private String currentPrice;
    @Column(name = "NWTXIN_ISBN")
    private String isbn;
    @Column(name = "NWTXIN_PURCHASE_DATE")
    @Temporal(TemporalType.DATE)
    private Date pruchaseDate;
    @Column(name= "NWTXIN_FIRST_USED_DATE")
    @Temporal(TemporalType.DATE)
    private Date firstUsedDate;
    @Column(name = "NWTXIN_DISCONTINUED_DATE")
    @Temporal(TemporalType.DATE)
    private Date discontinuedDate;
    @Column(name = "NWTXIN_ACTIVITY_DATE")
    @Temporal(TemporalType.DATE)
    private Date activityDate;
    @Column(name = "NWTXIN_COURSE_NAME")
    private String crseName;
    @Column(name = "NWTXIN_COURSE1")
    private String crse1;
    @Column(name = "NWTXIN_COURSE2")
    private String crse2;
    @Column(name = "NWTXIN_COURSE3")
    private String crse3;
    @Column(name = "NWTXIN_COURSE4")
    private String crse4;
    @Column(name = "NWTXIN_COURSE5")
    private String crse5;
    @Column(name = "NWTXIN_COMMENT")
    private String comment;

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    public String getEditionYear() {
        return editionYear;
    }

    public void setEditionYear(String editionYear) {
        this.editionYear = editionYear;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(String bookStatus) {
        this.bookStatus = bookStatus;
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Date getPruchaseDate() {
        return pruchaseDate;
    }

    public void setPruchaseDate(Date pruchaseDate) {
        this.pruchaseDate = pruchaseDate;
    }

    public Date getFirstUsedDate() {
        return firstUsedDate;
    }

    public void setFirstUsedDate(Date firstUsedDate) {
        this.firstUsedDate = firstUsedDate;
    }

    public Date getDiscontinuedDate() {
        return discontinuedDate;
    }

    public void setDiscontinuedDate(Date discontinuedDate) {
        this.discontinuedDate = discontinuedDate;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }

    public String getCrseName() {
        return crseName;
    }

    public void setCrseName(String crseName) {
        this.crseName = crseName;
    }

    public String getCrse1() {
        return crse1;
    }

    public void setCrse1(String crse1) {
        this.crse1 = crse1;
    }

    public String getCrse2() {
        return crse2;
    }

    public void setCrse2(String crse2) {
        this.crse2 = crse2;
    }

    public String getCrse3() {
        return crse3;
    }

    public void setCrse3(String crse3) {
        this.crse3 = crse3;
    }

    public String getCrse4() {
        return crse4;
    }

    public void setCrse4(String crse4) {
        this.crse4 = crse4;
    }

    public String getCrse5() {
        return crse5;
    }

    public void setCrse5(String crse5) {
        this.crse5 = crse5;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

