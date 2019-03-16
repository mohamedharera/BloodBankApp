package com.example.mohamed.bank.Favourites;

/**
 * Created by Mohamed on 10/30/2018.
 */

public class FavouriteData {
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

    public FavouriteData(String title, String thumbnail) {
        this.title = title;
        this.thumbnail = thumbnail;
    }

    public FavouriteData() {
    }
}
