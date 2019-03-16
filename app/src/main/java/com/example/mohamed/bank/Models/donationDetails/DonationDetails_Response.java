package com.example.mohamed.bank.Models.donationDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DonationDetails_Response {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private DonationDetails_Data data;

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

    public DonationDetails_Data getData() {
        return data;
    }

    public void setData(DonationDetails_Data data) {
        this.data = data;
    }
}
