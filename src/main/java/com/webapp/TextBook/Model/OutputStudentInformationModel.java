package com.webapp.TextBook.Model;

import java.util.List;

public class OutputStudentInformationModel {
    private String firstName;

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    private String term;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBagNumber() {
        return bagNumber;
    }

    public void setBagNumber(String bagNumber) {
        this.bagNumber = bagNumber;
    }

    public List<Nwtxdt> getNwtxdtList() {
        return nwtxdtList;
    }


    private List<Ssbsect> ssbsectList;

    public List<Ssbsect> getSsbsectList() {
        return ssbsectList;
    }

    public void setSsbsectList(List<Ssbsect> ssbsectList) {
        this.ssbsectList = ssbsectList;
    }

    public List<Scbcrse> getScbcrseList() {
        return scbcrseList;
    }

    public void setScbcrseList(List<Scbcrse> scbcrseList) {
        this.scbcrseList = scbcrseList;
    }

    private List<Scbcrse> scbcrseList;

    public List<Nwtxin> getNwtxinList() {
        return nwtxinList;
    }

    public void setNwtxinList(List<Nwtxin> nwtxinList) {
        this.nwtxinList = nwtxinList;
    }

    private List<Nwtxin> nwtxinList;


    public Nwtxdt getNwtxdt() {
        return nwtxdt;
    }

    public void setNwtxdt(Nwtxdt nwtxdt) {
        this.nwtxdt = nwtxdt;
    }

    public void setNwtxdtList(List<Nwtxdt> nwtxdtList) {
        this.nwtxdtList = nwtxdtList;
    }

    private Nwtxdt nwtxdt;

    private String lastName;
    private String middleInitial;
    private String id;
    private String bagNumber;
    private List<Nwtxdt> nwtxdtList;

}
