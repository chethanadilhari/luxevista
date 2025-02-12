package com.chethana.luxevista;

public class UserProfileClass {

    String name, mobile;

    public UserProfileClass(String name, String mobile) {
        this.mobile = mobile;
        this.name = name;
    }

    public UserProfileClass() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
