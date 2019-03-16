package com.example.mohamed.bank.NotificationSettings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.mohamed.bank.Models.DatumCity;
import com.example.mohamed.bank.Models.ExampleCity;
import com.example.mohamed.bank.Models.NotificationSettings.NotificationSetting_Example;
import com.example.mohamed.bank.R;
import com.example.mohamed.bank.Service.GetDataService;
import com.example.mohamed.bank.Service.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsSettings extends AppCompatActivity{

    Toolbar toolbar;
    private RecyclerView notifyRecyclerView;
    private NotificationCitesAdapter notifiAdapter;
    CheckBox OPositive,ANegative,APositive,ABNegative,ABPositive,ONegative;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications_settings);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        notifyRecyclerView= (RecyclerView) findViewById(R.id.recyclerview_checkbox);
        OPositive = (CheckBox) findViewById(R.id.OPositive);
        ANegative = (CheckBox) findViewById(R.id.ANegative);
        APositive = (CheckBox) findViewById(R.id.APositive);
        ABNegative = (CheckBox) findViewById(R.id.ABNegative);
        ONegative = (CheckBox) findViewById(R.id.ONegative);
        ABPositive = (CheckBox) findViewById(R.id.ABPositive);

        // Load
        SharedPreferences prefs = getSharedPreferences("syllabus", 0);
        OPositive.setChecked(prefs.getBoolean("cbx1_ischecked" ,false));
        ONegative.setChecked(prefs.getBoolean("cbx2_ischecked" ,false));
        ANegative.setChecked(prefs.getBoolean("cbx3_ischecked" ,false));
        APositive.setChecked(prefs.getBoolean("cbx4_ischecked" ,false));
        ABPositive.setChecked(prefs.getBoolean("cbx5_ischecked" ,false));
        ABNegative.setChecked(prefs.getBoolean("cbx6_ischecked" ,false));

        setupRecycle();
        getAllCities();
        getBloodChecked();
    }

    public void getBloodChecked(){
        final SharedPreferences.Editor editor = getSharedPreferences("syllabus", 0).edit();

        OPositive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("cbx1_ischecked", b);
                editor.commit();

                if (OPositive.isChecked()) {
                    boolean check = addnotification();

                    if (check) {
                        Log.i("isChecked", "start");
                    }
                } else {

                    boolean check = addnotification();

                    if (check) {
                        Log.i("isChecked", "start");
                    }
                }
        }});


        ONegative.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("cbx2_ischecked", b);
                editor.commit();

                if (ONegative.isChecked()) {
                    boolean check = addnotification();

                    if (check) {
                        Log.i("isChecked", "start");
                    }
                } else {

                    boolean check = addnotification();

                    if (check) {
                        Log.i("isChecked", "start");
                    }
                }

            }
        });


        ANegative.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("cbx3_ischecked", b);
                editor.commit();

                if (ANegative.isChecked()) {
                    boolean check = addnotification();

                    if (check) {
                        Log.i("isChecked", "start");
                    }
                } else {

                    boolean check = addnotification();

                    if (check) {
                        Log.i("isChecked", "start");
                    }
                }
            }
        });
        APositive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("cbx4_ischecked", b);
                editor.commit();

                if (APositive.isChecked()) {
                    boolean check = addnotification();

                    if (check) {
                        Log.i("isChecked", "start");
                    }
                } else {

                    boolean check = addnotification();

                    if (check) {
                        Log.i("isChecked", "start");
                    }
                }
            }
        });
        ABPositive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("cbx5_ischecked", b);
                editor.commit();

                if (ABPositive.isChecked()) {
                    boolean check = addnotification();

                    if (check) {
                        Log.i("isChecked", "start");
                    }
                } else {

                    boolean check = addnotification();

                    if (check) {
                        Log.i("isChecked", "start");
                    }
                }
            }
        });


        ABNegative.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("cbx6_ischecked", b);
                editor.commit();

                if (ABNegative.isChecked()) {
                    boolean check = addnotification();

                    if (check) {
                        Log.i("isChecked", "start");
                    }
                } else {

                    boolean check = addnotification();

                    if (check) {
                        Log.i("isChecked", "start");
                    }
                }
            }});
    }
    public Boolean  addnotification(){
        String Api ="mzj4aDhSta6ulLzA4igpTrNXSSNqeNpzYBOKQKCyBNaCf9EgtOh5Vu6sXFyf";
        final boolean[] ischecked = {false};
        final GetDataService dataservices = RetrofitInstance.getDataService();

        Call<NotificationSetting_Example> call = dataservices.selectNotificationBlood(Api);
        call.enqueue(new Callback<NotificationSetting_Example>() {
            @Override
            public void onResponse(Call<NotificationSetting_Example> call, Response<NotificationSetting_Example> response) {
                NotificationSetting_Example notificationSettingExample = response.body();
                if (notificationSettingExample.getStatus() == 1){
                    ischecked[0] = true;
//                    Toast.makeText(getApplicationContext(),notificationSetting_example.getMsg() , Toast.LENGTH_SHORT).show();
                }else {
                    ischecked[0] = false;
                }

            }

            @Override
            public void onFailure(Call<NotificationSetting_Example> call, Throwable t) {

            }
        });
        return ischecked[0];
    }


    private void setupRecycle() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        notifyRecyclerView.setLayoutManager(layoutManager);
        notifiAdapter = new NotificationCitesAdapter(this);
        notifyRecyclerView.setAdapter(notifiAdapter);
    }
    private void viewRes(ExampleCity body) {

        List<DatumCity> data = body.getDataList();
        notifiAdapter.sendData(data);
        notifiAdapter.notifyDataSetChanged();

    }
    public void getAllCities() {

        GetDataService getDataService = RetrofitInstance.getDataService();
        Call<ExampleCity>call = getDataService.getCities();
        call.enqueue(new Callback<ExampleCity>() {
            @Override
            public void onResponse(Call<ExampleCity> call, Response<ExampleCity> response) {

                if (response.isSuccessful()) {
                    ExampleCity body = response.body();
                    viewRes(body);
                }
            }

            @Override
            public void onFailure(Call<ExampleCity> call, Throwable t) {

            }
        });
    }



//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_item, menu);
//
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        switch (item.getItemId()) {
//            case R.id.menu_back:
//                Intent intent = new Intent(NotificationsSettings.this,HomeActivity.class);
//                startActivity(intent);
//
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }


}
