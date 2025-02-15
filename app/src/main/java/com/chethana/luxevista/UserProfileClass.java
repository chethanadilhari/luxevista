package com.chethana.luxevista;

public class UserProfileClass {

    String name, mobile,address;

    public UserProfileClass(String name, String mobile, String address) {
        this.mobile = mobile;
        this.name = name;
        this.address = address;
    }

    public UserProfileClass() {
    }

    public String getAddress() {
        return address;
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
