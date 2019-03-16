package com.example.mohamed.bank.Home;

/**
 * Created by Mohamed on 10/11/2018.
 */

public class DonationData2 {
    private String title;
    private String thumbnail;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public DonationData2(String title, String thumbnail) {
        this.title = title;
        this.thumbnail = thumbnail;
    }

    public DonationData2() {
    }
}
