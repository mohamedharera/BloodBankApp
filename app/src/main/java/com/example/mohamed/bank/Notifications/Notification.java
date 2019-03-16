package com.example.mohamed.bank.Notifications;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.mohamed.bank.Models.Notifications.NotificationsDatum;
import com.example.mohamed.bank.Models.Notifications.NotificationsExample;
import com.example.mohamed.bank.R;
import com.example.mohamed.bank.Service.GetDataService;
import com.example.mohamed.bank.Service.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Notification extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        recyclerView= (RecyclerView) findViewById(R.id.recycler_view_notification);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        final GetDataService getDataService = RetrofitInstance.getDataService();
        Call<NotificationsExample> call = getDataService.getNotifications("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27");
        call.enqueue(new Callback<NotificationsExample>() {
            @Override
            public void onResponse(Call<NotificationsExample> call, Response<NotificationsExample> response) {


                NotificationsExample example = response.body();
                List<NotificationsDatum> data = example.getData().getData();
                NotificationsAdapter adapter = new NotificationsAdapter(data,getApplicationContext());
                recyclerView.setAdapter(adapter);
            }


            @Override
            public void onFailure(Call<NotificationsExample> call, Throwable t) {

            }
        });

    }



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
