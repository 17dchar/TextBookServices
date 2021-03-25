package com.webapp.TextBook.Model;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@Table(name = "SFRVERF")
public class Sfrverf {
    @Id
    @Column(name = "SFRVERF_PIDM")
    private Integer pidm;
    @Column(name = "SFRVERF_TERM_CODE")
    private String termCode;
    @Column(name = "SFRVERF_ACTIVITY_DATE")
    @Temporal(TemporalType.DATE)
    private Date activityDate;
    @Column(name = "SFRVERF_LEVL_CODE")
    private String levlCode;
    @Column(name = "SFRVERF_CANCEL_FLAG")
    private String cancelFlag;

    public Integer getPidm() {
        return pidm;
    }

    public void setPidm(Integer pidm) {
        this.pidm = pidm;
    }

    public String getTermCode() {
        return termCode;
    }

    public void setTermCode(String termCode) {
        this.termCode = termCode;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }

    public String getLevlCode() {
        return levlCode;
    }

    public void setLevlCode(String levlCode) {
        this.levlCode = levlCode;
    }

    public String getCancelFlag() {
        return cancelFlag;
    }

    public void setCancelFlag(String cancelFlag) {
        this.cancelFlag = cancelFlag;
    }
}
