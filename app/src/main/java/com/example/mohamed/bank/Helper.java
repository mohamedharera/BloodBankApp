package com.example.mohamed.bank;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

public class Helper {


    public static void ree(Fragment fragment, int id, FragmentTransaction fragmentTransaction) {
        FragmentTransaction transaction = fragmentTransaction;
        transaction.replace(id, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}
