package com.example.mohamed.bank.Models.NotificationSettings;

import com.example.mohamed.bank.Models.DatumCity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohamed on 11/19/2018.
 */

public class NotificationSetting_Example {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private NotificationSetting_Date data;

    private DatumCity city;

    public DatumCity getCity() {
        return city;
    }

    public void setCity(DatumCity city) {
        this.city = city;
    }

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

    public NotificationSetting_Date getData() {
        return data;
    }

    public void setData(NotificationSetting_Date data) {
        this.data = data;
    }
}
