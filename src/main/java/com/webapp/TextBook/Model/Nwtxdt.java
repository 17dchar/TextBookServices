package com.webapp.TextBook.Model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name="NWTXDT")
public class Nwtxdt {
    @Column(name="NWTXDT_BOOK_CODE")
    @NotEmpty
    @Size(min=8,max=8)
    private String bookCode;
    @Column(name = "NWTXDT_EDITION_YEAR")
    @NotEmpty
    @Size(min=4,max=4)
    private String editionYear;
    @Column(name = "NWTXDT_SEQ_NR")
    @Size(max=5)
    @Pattern(regexp="^(0|[1-9][0-9]*)$")
    private String seqNr;
    @Column(name = "NWTXDT_BARCODE")
    @Id
    @NotEmpty
    @Size(min=13,max=13)
    private String barcode;
    @Column(name = "NWTXDT_PIDM")
    private String pidm;
    @Size(max=8)
    @Column(name = "NWTXDT_TERM")
    @Size(max=6)
    private String term;
    @Column(name = "NWTXDT_DATE_CHECKED_OUT")
    @Temporal(TemporalType.DATE)
    private Date dateCheckedOut;
    @Column(name = "NWTXDT_DISPOSITION")
    @Size(max = 1)
    private String disposition;
    @Column(name = "NWTXDT_BOOK_SALE_PRICE")
    private String bookSalePrice;
    @Column(name = "NWTXDT_PREV_PIDM")
    @Size(max = 8)
    @Pattern(regexp="^(0|[1-9][0-9]*)$")
    private String prevPidm;
    @Column(name = "NWTXDT_PREV_TERM")
    @Size(max = 6)
    private String prevTerm;
    @Column(name = "NWTXDT_PREV_DATE_CHECKED_IN")
    @Temporal(TemporalType.DATE)
    private Date prevDateCheckedIn;
    @Column(name = "NWTXDT_ACTIVITY_DATE")
    @Temporal(TemporalType.DATE)
    private Date activityDate;
    @Column(name = "NWTXDT_BILLABLE_FLAG")
    @Size(max = 1)
    private String billableFlag;

    public Nwtxdt(){
        editionYear = "";
        barcode = "";
    }
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

    public String getSeqNr() {
        return seqNr;
    }

    public void setSeqNr(String seqNr) {
        this.seqNr = seqNr;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getPidm() {
        return pidm;
    }

    public void setPidm(String pidm) {
        this.pidm = pidm;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public Date getDateCheckedOut() {
        return dateCheckedOut;
    }

    public void setDateCheckedOut(Date dateCheckedOut) {
        this.dateCheckedOut = dateCheckedOut;
    }

    public String getDisposition() {
        return disposition;
    }

    public void setDisposition(String disposition) {
        this.disposition = disposition;
    }

    public String getBookSalePrice() {
        return bookSalePrice;
    }

    public void setBookSalePrice(String bookSalePrice) {
        this.bookSalePrice = bookSalePrice;
    }

    public String getPrevPidm() {
        return prevPidm;
    }

    public void setPrevPidm(String prevPidm) {
        this.prevPidm = prevPidm;
    }

    public String getPrevTerm() {
        return prevTerm;
    }

    public void setPrevTerm(String prevTerm) {
        this.prevTerm = prevTerm;
    }

    public Date getPrevDateCheckedIn() {
        return prevDateCheckedIn;
    }

    public void setPrevDateCheckedIn(Date prevDateCheckedIn) {
        this.prevDateCheckedIn = prevDateCheckedIn;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }

    public String getBillableFlag() {
        return billableFlag;
    }

    public void setBillableFlag(String billableFlag) {
        this.billableFlag = billableFlag;
    }






}
