package com.example.mohamed.bank.Models.create_request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohamed on 10/26/2018.
 */

public class DonCreate_Data {

    @SerializedName("donationRequest")
    @Expose
    private DonCreate_DonationRequest donationRequest;

    public DonCreate_DonationRequest getDonationRequest() {
        return donationRequest;
    }

    public void setDonationRequest(DonCreate_DonationRequest donationRequest) {
        this.donationRequest = donationRequest;
    }
}
