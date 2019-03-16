package com.example.mohamed.bank.IntroSlider;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mohamed.bank.R;

/**
 * Created by Mohamed on 7/8/2018.
 */

public class PreferenceManger {

    private Context context;
    private SharedPreferences sharedPreferences;

    public PreferenceManger(Context context){
        this.context=context;
        getSharedPreferences();
    }

    private void getSharedPreferences(){
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.myprefrence),context.MODE_PRIVATE);

    }

    public void writePreferences(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.myprefrence_key),"Init_ok");
        editor.commit();
    }

    public boolean checkPreference(){
        boolean status = false;
        if (sharedPreferences.getString(context.getString(R.string.myprefrence_key),"null").equals("null")){
            status=false;
        }else {
            status=true;
        }
        return status;
    }

    public void clearPreference(){
        sharedPreferences.edit().clear().commit();
    }
}
