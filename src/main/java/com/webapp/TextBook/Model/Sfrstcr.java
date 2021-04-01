package com.webapp.TextBook.Model;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@Table(name="SFRSTCR")
public class Sfrstcr {
    @Id
    @Column(name = "SFRSTCR_TERM_CODE")
    @NotEmpty
    private String termCode;
    @Column(name = "SFRSTCR_PIDM")
    @NotEmpty
    private int pidm;
    @Column(name = "SFRSTCR_CRN")
    @NotEmpty
    private String crn;
    @Column(name = "SFRSTCR_RSTS_CODE")
    private String rstsCode;
    @Column(name = "SFRSTCR_LEVL_OVER")
    private String levlOver;

    public String getTermCode() {
        return termCode;
    }

    public void setTermCode(String termCode) {
        this.termCode = termCode;
    }

    public Integer getPidm() {
        return pidm;
    }

    public void setPidm(Integer pidm) {
        this.pidm = pidm;
    }

    public String getCrn() {
        return crn;
    }

    public void setCrn(String crn) {
        this.crn = crn;
    }

    public String getRstsCode() {
        return rstsCode;
    }

    public void setRstsCode(String rstsCode) {
        this.rstsCode = rstsCode;
    }

    public String getLevlOver() {
        return levlOver;
    }

    public void setLevlOver(String levlOver) {
        this.levlOver = levlOver;
    }
}