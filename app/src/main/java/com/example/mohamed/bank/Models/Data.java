package com.example.mohamed.bank.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohamed on 10/19/2018.
 */

public class Data {
    @SerializedName("api_token")
    @Expose
    private String apiToken;
    @SerializedName("client")
    @Expose
    private Client client;

    public String getApiToken() {
        return apiToken;
    }

    public Client getClient() {
        return client;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
