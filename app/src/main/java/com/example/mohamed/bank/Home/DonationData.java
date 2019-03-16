package com.example.mohamed.bank.Home;

/**
 * Created by Mohamed on 10/11/2018.
 */

public class DonationData {
    private  String name;
    private  String hospital;
    private  String country;
    private  String blood_type;

    public DonationData(){

    }

    public DonationData(String name, String hospital, String country, String blood_type) {
        this.name = name;
        this.hospital = hospital;
        this.country = country;
        this.blood_type = blood_type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setBlood_type(String blood_type) {
        this.blood_type = blood_type;
    }

    public String getName() {
        return name;
    }

    public String getHospital() {
        return hospital;
    }

    public String getCountry() {
        return country;
    }

    public String getBlood_type() {
        return blood_type;
    }
}
