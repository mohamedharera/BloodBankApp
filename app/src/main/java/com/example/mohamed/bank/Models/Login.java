package com.example.mohamed.bank.Models;

/**
 * Created by Mohamed on 10/22/2018.
 */

public class Login {

    private String phone;
    private String password;

    public Login(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
