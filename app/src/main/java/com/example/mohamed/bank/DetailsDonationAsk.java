package com.example.mohamed.bank;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mohamed.bank.Models.NotificationCount.NotificationCountExample;
import com.example.mohamed.bank.Models.donationDetails.DonationDetails_Data;
import com.example.mohamed.bank.Models.donationDetails.DonationDetails_Response;
import com.example.mohamed.bank.Notifications.Notification;
import com.example.mohamed.bank.Service.GetDataService;
import com.example.mohamed.bank.Service.RetrofitInstance;
import com.nex3z.notificationbadge.NotificationBadge;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsDonationAsk extends AppCompatActivity {

    Toolbar toolbar;
    TextView name,birth,bugs,hospital,address,phone,detail,requestname,type;
    Button call_client;
    int count = 0;
    NotificationBadge mbadge;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_donation_ask);

        name = (TextView) findViewById(R.id.name);
        birth = (TextView) findViewById(R.id.birth);
        bugs = (TextView) findViewById(R.id.bugs);
        hospital = (TextView) findViewById(R.id.hospital);
        address = (TextView) findViewById(R.id.address);
        phone = (TextView) findViewById(R.id.phone);
        detail= (TextView) findViewById(R.id.detail);
        requestname = (TextView) findViewById(R.id.requestname);
        type = (TextView) findViewById(R.id.type);
        call_client = (Button) findViewById(R.id.call);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        mbadge = (NotificationBadge) findViewById(R.id.badge);
        ImageView img = (ImageView) findViewById(R.id.imageView20);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = 0;
                mbadge.setNumber(count);
                Intent i = new Intent(DetailsDonationAsk.this, Notification.class);
                startActivity(i);
            }
        });
        final GetDataService getDataService = RetrofitInstance.getDataService();
        Call<NotificationCountExample> call = getDataService.getNotificationCount("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27");
        call.enqueue(new Callback<NotificationCountExample>() {
            @Override
            public void onResponse(Call<NotificationCountExample> call, Response<NotificationCountExample> response) {
                count = response.body().getData().getNotificationsCount();
                mbadge.setNumber(count);
            }

            @Override
            public void onFailure(Call<NotificationCountExample> call, Throwable t) {

            }
        });

        int newInt;
        if (getIntent().hasExtra("Id_I_NEED")) {
            newInt = getIntent().getIntExtra("Id_I_NEED", 0);
            getData(newInt);
        }

    }


    public void getData(int id){

        final GetDataService dataservices = RetrofitInstance.getDataService();
        Call<DonationDetails_Response> call = dataservices.getDonationdetails("W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl",id);
        call.enqueue(new Callback<DonationDetails_Response>() {
            @Override
            public void onResponse(Call<DonationDetails_Response> call, Response<DonationDetails_Response> response) {
                DonationDetails_Response example = response.body();
                final DonationDetails_Data postsDetailsCategory= example.getData();
                name.setText(postsDetailsCategory.getPatientName());
                birth.setText(postsDetailsCategory.getPatientAge());
                bugs.setText(postsDetailsCategory.getBagsNum());
                hospital.setText(postsDetailsCategory.getHospitalName());
                address.setText(postsDetailsCategory.getHospitalAddress());
                phone.setText(postsDetailsCategory.getPhone());
                detail.setText(postsDetailsCategory.getNotes());
                type.setText(postsDetailsCategory.getBloodType().getName());
                requestname.setText(postsDetailsCategory.getPatientName());

                call_client.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String phone = postsDetailsCategory.getPhone();

                        Intent i = new Intent(Intent.ACTION_DIAL);
                        i.setData(Uri.parse("tel:"+phone));
                        if (ActivityCompat.checkSelfPermission(DetailsDonationAsk.this, android.Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                            requestPermission();
                        }
                        else {
                            startActivity(i);
                        }
                    }
                });
            }

            @Override
            public void onFailure(Call<DonationDetails_Response> call, Throwable t) {

            }
        });
    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(DetailsDonationAsk.this,new String[]{Manifest.permission.CALL_PHONE},1);
    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_item, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        switch (item.getItemId()) {
//            case R.id.menu_back:
//                return true;
//
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

}
