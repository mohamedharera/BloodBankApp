package com.example.mohamed.bank.Models.Report;

/**
 * Created by Mohamed on 11/19/2018.
 */

public class Report {

    private String api_token;
    private String message;

    public Report(String api_token, String message) {
        this.api_token = api_token;
        this.message = message;
    }

    public String getApi_token() {
        return api_token;
    }

    public void setApi_token(String api_token) {
        this.api_token = api_token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
