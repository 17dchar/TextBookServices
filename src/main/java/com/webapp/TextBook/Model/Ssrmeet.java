package com.webapp.TextBook.Model;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@Table(name="SSRMEET")
public class Ssrmeet {
    @Id
    @Column(name = "SSRMEET_TERM_CODE")
    @NotEmpty
    private String termCode;
    @Column(name = "SSRMEET_CRN")
    @NotEmpty
    private String crn;
    @Column(name = "SSRMEET_BEGIN_TIME")
    private String beginTime;
    @Column(name = "SSRMEET_END_TIME")
    private String endTime;
    @Column(name = "SSRMEET_MON_DAY")
    private String monday;
    @Column(name = "SSRMEET_TUE_DAY")
    private String tuesday;
    @Column(name = "SSRMEET_WED_DAY")
    private String wednesday;
    @Column(name = "SSRMEET_THU_DAY")
    private String thursday;
    @Column(name = "SSRMEET_FRI_DAY")
    private String friday;

    public String getTermCode() {
        return termCode;
    }

    public void setTermCode(String termCode) {
        this.termCode = termCode;
    }

    public String getCrn() {
        return crn;
    }

    public void setCrn(String crn) {
        this.crn = crn;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getMonday() {
        return monday;
    }

    public void setMonday(String monday) {
        this.monday = monday;
    }

    public String getTuesday() {
        return tuesday;
    }

    public void setTuesday(String tuesday) {
        this.tuesday = tuesday;
    }

    public String getWednesday() {
        return wednesday;
    }

    public void setWednesday(String wednesday) {
        this.wednesday = wednesday;
    }

    public String getThursday() {
        return thursday;
    }

    public void setThursday(String thursday) {
        this.thursday = thursday;
    }

    public String getFriday() {
        return friday;
    }

    public void setFriday(String friday) {
        this.friday = friday;
    }
}