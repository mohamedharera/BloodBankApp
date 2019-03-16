package com.example.mohamed.bank.Models.Call_us;

/**
 * Created by Mohamed on 11/19/2018.
 */

public class Contact {
    private String api_token;
    private String title;
    private String message;

    public Contact(String api_token, String title, String message) {
        this.api_token = api_token;
        this.title = title;
        this.message = message;
    }

    public String getApi_token() {
        return api_token;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public void setApi_token(String api_token) {
        this.api_token = api_token;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
