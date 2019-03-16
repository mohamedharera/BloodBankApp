package com.example.mohamed.bank.Models.NotificationSettings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Mohamed on 11/19/2018.
 */

public class NotificationSetting_Date {
    @SerializedName("cities")
    @Expose
    private List<String> cities = null;

    @SerializedName("bloodTypes")
    @Expose
    private List<String> bloodTypes = null;

    private boolean isChecked;

    public boolean getChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }

    public List<String> getBloodTypes() {
        return bloodTypes;
    }

    public void setBloodTypes(List<String> bloodTypes) {
        this.bloodTypes = bloodTypes;
    }
}
