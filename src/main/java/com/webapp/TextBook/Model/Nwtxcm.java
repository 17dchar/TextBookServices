package com.webapp.TextBook.Model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name="NWTXCM")
public class Nwtxcm
{
    @Id
    @Column(name = "NWTXCM_COURSE")
    @NotEmpty
    @Size(min = 9, max=9)
    private String course;
    @Column(name = "NWTXCM_MESSAGE")
    @Size(max = 15)
    private String message;
    @Column(name = "NWTXCM_ACTIVITY_DATE")
    @Temporal(TemporalType.DATE)
    private Date acDate;

    public String getCourse() {
        return course;
    }

    public void setCourse(String cmCourse) {
        this.course = cmCourse;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String cmMessage) {
        this.message = cmMessage;
    }

    public Date getAcDate() {
        return acDate;
    }

    public void setAcDate(Date acDate) {
        this.acDate = acDate;
    }
}
