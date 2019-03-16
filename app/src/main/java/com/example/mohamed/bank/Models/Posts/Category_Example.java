package com.example.mohamed.bank.Models.Posts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohamed on 10/22/2018.
 */

public class Category_Example {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private Category_Data category_data;

    public Category_Example(Integer status, String msg, Category_Data data) {
        this.status = status;
        this.msg = msg;
        this.category_data = data;
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

    public Category_Data getCategory_data() {
        return category_data;
    }

    public void setCategory_data(Category_Data category_data) {
        this.category_data = category_data;
    }
}
