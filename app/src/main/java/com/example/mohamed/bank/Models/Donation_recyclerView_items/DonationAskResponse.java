package com.example.mohamed.bank.Models.Donation_recyclerView_items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DonationAskResponse {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private DonationAskData data;

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

    public DonationAskData getData() {
        return data;
    }

    public void setData(DonationAskData data) {
        this.data = data;
    }
}
