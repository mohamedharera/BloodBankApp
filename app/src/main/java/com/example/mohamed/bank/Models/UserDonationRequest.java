package com.example.mohamed.bank.Models;

/**
 * Created by Mohamed on 10/26/2018.
 */

public class UserDonationRequest {
    private String api_token;
    private String patient_name;
    private String patient_age;
    private int blood_type_id;
    private String bags_num;
    private String hospital_name;
    private String hospital_address;
    private String city_id;
    private String phone;
    private String notes;
    private String latitude;
    private String longitude;

    public UserDonationRequest(String api_token, String patient_name, String patient_age, int blood_type, String bags_num, String hospital_name, String hospital_address, String city_id, String phone, String notes, String latitude, String longitude) {
        this.api_token = api_token;
        this.patient_name = patient_name;
        this.patient_age = patient_age;
        this.blood_type_id = blood_type;
        this.bags_num = bags_num;
        this.hospital_name = hospital_name;
        this.hospital_address = hospital_address;
        this.city_id = city_id;
        this.phone = phone;
        this.notes = notes;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getApi_token() {
        return api_token;
    }

    public void setApi_token(String api_token) {
        this.api_token = api_token;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public String getPatient_age() {
        return patient_age;
    }

    public int getBlood_type() {
        return blood_type_id;
    }

    public String getBags_num() {
        return bags_num;
    }

    public String getHospital_name() {
        return hospital_name;
    }

    public String getHospital_address() {
        return hospital_address;
    }

    public String getCity_id() {
        return city_id;
    }

    public String getPhone() {
        return phone;
    }

    public String getNotes() {
        return notes;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public void setPatient_age(String patient_age) {
        this.patient_age = patient_age;
    }

    public void setBlood_type(int blood_type) {
        this.blood_type_id = blood_type;
    }

    public void setBags_num(String bags_num) {
        this.bags_num = bags_num;
    }

    public void setHospital_name(String hospital_name) {
        this.hospital_name = hospital_name;
    }

    public void setHospital_address(String hospital_address) {
        this.hospital_address = hospital_address;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLongitude(String longitude) {

        this.longitude = longitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}

