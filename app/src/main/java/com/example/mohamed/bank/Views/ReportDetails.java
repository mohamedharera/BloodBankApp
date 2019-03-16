package com.example.mohamed.bank.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mohamed.bank.Models.NotificationCount.NotificationCountExample;
import com.example.mohamed.bank.Models.PostsDetails_Data;
import com.example.mohamed.bank.Models.PostsDetails_Example;
import com.example.mohamed.bank.Notifications.Notification;
import com.example.mohamed.bank.R;
import com.example.mohamed.bank.Service.GetDataService;
import com.example.mohamed.bank.Service.RetrofitInstance;
import com.nex3z.notificationbadge.NotificationBadge;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportDetails extends AppCompatActivity {

    Toolbar toolbar;
    TextView title,details,toolbarTitle;
    ImageView interface_image;

    int count = 0;
    NotificationBadge mbadge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_details);

        mbadge = (NotificationBadge) findViewById(R.id.badge);
        ImageView img = (ImageView) findViewById(R.id.imageView20);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = 0;
                mbadge.setNumber(count);
                Intent i = new Intent(ReportDetails.this, Notification.class);
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


        title = (TextView) findViewById(R.id.text_title);
        details = (TextView) findViewById(R.id.text_details);
        interface_image = (ImageView) findViewById(R.id.interface_image);
        toolbarTitle = findViewById(R.id.toolbarTitle);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        int newInt;
        String titlee;
        if (getIntent().hasExtra("Id_I_NEED")) {
            newInt = getIntent().getIntExtra("Id_I_NEED", 0);
            getData(newInt);
        }
        if (getIntent().hasExtra("title_I_NEED")) {
            titlee = getIntent().getStringExtra("title_I_NEED");
            title.setText(titlee);
            toolbarTitle.setText(titlee);
        }

//        goback();
    }

    public void getData(int postId){

        final GetDataService dataservices = RetrofitInstance.getDataService();

        Call<PostsDetails_Example> call = dataservices.getPost_details("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27",postId);
        call.enqueue(new Callback<PostsDetails_Example>() {
            @Override
            public void onResponse(Call<PostsDetails_Example> call, Response<PostsDetails_Example> response) {
                PostsDetails_Example example = response.body();
                PostsDetails_Data postsDetailsCategory= example.getData();
                title.setText(postsDetailsCategory.getContent());
                details.setText(postsDetailsCategory.getContent());
                toolbarTitle.setText(postsDetailsCategory.getTitle());
                Glide.with(getApplicationContext()).load(postsDetailsCategory.getThumbnailFullPath()).into(interface_image);
            }

            @Override
            public void onFailure(Call<PostsDetails_Example> call, Throwable t) {

            }
        });
    }

//    public void goback(){
//        ImageView back = findViewById(R.id.reportdetails_back);
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(ReportDetails.this,HomeActivity.class);
//                startActivity(intent);
//            }
//        });
//    }




}
