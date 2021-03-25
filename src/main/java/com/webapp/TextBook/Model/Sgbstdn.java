package com.webapp.TextBook.Model;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@Table(name="SGBSTDN")
public class Sgbstdn {
    @Id
    @Column(name = "SGBSTDN_PIDM")
    @NotEmpty
    private Integer pidm;
    @Column(name = "SGBSTDN_TERM_CODE_EFF")
    @NotEmpty
    private String termCodeEff;
    @Column(name = "SGNSTDN_STST_CODE")
    @NotEmpty
    private String ststCode;
    @Column(name = "SGBSTDN_LEVL_CODE")
    private String levlCode;

    public Integer getPidm() {
        return pidm;
    }

    public void setPidm(Integer pidm) {
        this.pidm = pidm;
    }

    public String getTermCodeEff() {
        return termCodeEff;
    }

    public void setTermCodeEff(String termCodeEff) {
        this.termCodeEff = termCodeEff;
    }

    public String getStstCode() {
        return ststCode;
    }

    public void setStstCode(String ststCode) {
        this.ststCode = ststCode;
    }

    public String getLevlCode() {
        return levlCode;
    }

    public void setLevlCode(String levlCode) {
        this.levlCode = levlCode;
    }
}
