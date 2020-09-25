package com.example.prohub.Helper;

public class UserHelperClass1 {

    String gender, date;

    public UserHelperClass1() {
    }

    public UserHelperClass1(String gender, String date) {
        this.gender = gender;
        this.date = date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
