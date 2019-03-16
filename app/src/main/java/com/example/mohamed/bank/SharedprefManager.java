package com.example.mohamed.bank;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mohamed.bank.Models.Client;
import com.example.mohamed.bank.Models.login.LoginClient;

public class SharedprefManager {
    private static final String SHARED_PREF_NAME = "my_shared_preff";

    private static SharedprefManager mInstance;
    private Context mCtx;

    private SharedprefManager (Context mCtx){
        this.mCtx = mCtx;
    }
    public static synchronized SharedprefManager getInstance(Context mCtx){

        if (mInstance == null){
            mInstance = new SharedprefManager(mCtx);
        }
        return mInstance;
    }
    public void saveUser(LoginClient client){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("id",client.getId());
        editor.putString("name",client.getName());
        editor.putString("email",client.getEmail());
        editor.putString("birth_date",client.getBirthDate());
        editor.putString("city_id",client.getCityId());
        editor.putString("phone",client.getPhone());
        editor.putString("blood_type",client.getBloodTypeId());
        editor.putString("last_donation",client.getDonationLastDate());
        editor.apply();
    }
    public boolean isloggedIn (){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getInt("id",-1) != -1;
    }
    public Client getuser(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return new Client(
                sharedPreferences.getInt("id", -1),
                sharedPreferences.getString("name",null),
                sharedPreferences.getString("email",null),
                sharedPreferences.getString("birth_date",null),
                sharedPreferences.getString("city_id",null),
                sharedPreferences.getString("phone",null),
                sharedPreferences.getString("blood_type",null),
                sharedPreferences.getString("last_donation",null));
    }
    public void clear(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }


}
