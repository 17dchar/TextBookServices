package com.webapp.TextBook.Model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@Table(name = "NWTXMS")
public class Nwtxms {
    @Id
    @Column(name = "NWTXMS_PIDM")
    private String pidm;
    @Column(name = "NWTXMS_MESSAGE")
    private String message;

    public String getPidm() {
        return pidm;
    }

    public void setPidm(String pidm) {
        this.pidm = pidm;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
