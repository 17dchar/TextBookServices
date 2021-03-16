package com.webapp.TextBook.Model;

public class Usermodel {

    private String userName;
    private String passWord;

    public Usermodel(){
        super();
    }

    public void setUserName(String uName){
        this.userName = uName;
    }

    public void setPassWord(String pass){
        this.passWord = pass; }

    public String getUserName(){return this.userName;



    }

    public String getPassWord(){return this.passWord;}

}