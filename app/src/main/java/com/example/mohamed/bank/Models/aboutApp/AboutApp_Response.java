package com.example.mohamed.bank.Models.aboutApp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AboutApp_Response {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private AboutApp_Data data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public AboutApp_Data getData() {
        return data;
    }

    public void setData(AboutApp_Data data) {
        this.data = data;
    }

}
