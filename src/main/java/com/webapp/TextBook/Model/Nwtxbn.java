package com.webapp.TextBook.Model;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@Table(name = "NWTXBN")
public class Nwtxbn {

    @Id
    @Column(name="NWTXBN_PIDM")
    private String pidm;
    @Column(name= "NWTXBN_BAG_NUMBER")
    private String bagNum;

    public String getPidm() {
        return pidm;
    }

    public void setPidm(String pidm) {
        this.pidm = pidm;
    }

    public String getBagNum() {
        return bagNum;
    }

    public void setBagNum(String bagNum) {
        this.bagNum = bagNum;
    }
}
