package com.example.mohamed.bank.Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.mohamed.bank.AboutApp;
import com.example.mohamed.bank.CallUsActivity;
import com.example.mohamed.bank.Favourites.Favorites;
import com.example.mohamed.bank.Models.NotificationCount.NotificationCountExample;
import com.example.mohamed.bank.Notifications.Notification;
import com.example.mohamed.bank.R;
import com.example.mohamed.bank.ReportActivity;
import com.example.mohamed.bank.Service.GetDataService;
import com.example.mohamed.bank.Service.RetrofitInstance;
import com.example.mohamed.bank.SharedprefManager;
import com.example.mohamed.bank.Views.LoginActivity;
import com.nex3z.notificationbadge.NotificationBadge;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    int count = 0;
    NotificationBadge mbadge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.content_center, new NewFragment());
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();

        mbadge = (NotificationBadge) findViewById(R.id.badge);
        ImageView img = (ImageView) findViewById(R.id.imageView20);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {
                count = 0;
                mbadge.setNumber(count);
                Intent i = new Intent(HomeActivity.this, Notification.class);
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

        dl = (DrawerLayout) findViewById(R.id.drawer_layout);
        t = new ActionBarDrawerToggle(
                this, dl, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        dl.addDrawerListener(t);
        t.syncState();

        nv = (NavigationView) findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
//                    case R.id.myinformation:
//                        Intent profile = new Intent(HomeActivity.this, ProfileActivity.class);
//                        startActivity(profile);
//                        break;
//                    case R.id.settings:
//                        Intent intent2 = new Intent(HomeActivity.this, NotificationsSettings.class);
//                        startActivity(intent2);
//                        break;
                    case R.id.app_home:
                        Intent home = new Intent(HomeActivity.this, HomeActivity.class);
                        startActivity(home);
                        break;
                    case R.id.mylikes:
                        Intent intent3 = new Intent(HomeActivity.this, Favorites.class);
                        startActivity(intent3);
                        break;
                    case R.id.report:
                        Intent intent6 = new Intent(HomeActivity.this, ReportActivity.class);
                        startActivity(intent6);
                        break;
                    case R.id.contact_us:
                        Intent intent7 = new Intent(HomeActivity.this, CallUsActivity.class);
                        startActivity(intent7);
                        break;
                    case R.id.about_app:
                        Intent intent8 = new Intent(HomeActivity.this, AboutApp.class);
                        startActivity(intent8);
                        break;
//                    case R.id.evalution:
//
//                        break;
                    case R.id.logout:
                        SharedprefManager.getInstance(HomeActivity.this).clear();
                        Intent intent = new Intent(HomeActivity.this,LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
    }
}
