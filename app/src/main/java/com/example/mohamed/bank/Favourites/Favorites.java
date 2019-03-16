package com.example.mohamed.bank.Favourites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.mohamed.bank.Home.HomeActivity;
import com.example.mohamed.bank.Models.Favourites.Favourite_Datum;
import com.example.mohamed.bank.Models.Favourites.Favourite_Example;
import com.example.mohamed.bank.Models.NotificationCount.NotificationCountExample;
import com.example.mohamed.bank.Notifications.Notification;
import com.example.mohamed.bank.R;
import com.example.mohamed.bank.Service.GetDataService;
import com.example.mohamed.bank.Service.RetrofitInstance;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Favorites extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;


    int count = 0;
    NotificationBadge mbadge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
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
                Intent i = new Intent(Favorites.this, Notification.class);
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


        recyclerView= (RecyclerView) findViewById(R.id.recycler_view_favourite);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        getRecyclerView();
        goback();
  }

  public void getRecyclerView(){
      final GetDataService getDataService = RetrofitInstance.getDataService();
      Call<Favourite_Example> call = getDataService.getFavourite("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27");
      call.enqueue(new Callback<Favourite_Example>() {
          @Override
          public void onResponse(Call<Favourite_Example> call, Response<Favourite_Example> response) {
              Favourite_Example example = response.body();
              List<Favourite_Datum> data = example.getData().getData();
              FavouritesAdapter adapter = new FavouritesAdapter(data,getApplicationContext());
              recyclerView.setAdapter(adapter);
          }

          @Override
          public void onFailure(Call<Favourite_Example> call, Throwable t) {

          }
      });
  }

  public void goback(){
      ImageView back = findViewById(R.id.favourite_back);
      back.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent = new Intent(Favorites.this,HomeActivity.class);
              startActivity(intent);
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
//                Intent intent = new Intent(Favorites.this,HomeActivity.class);
//                startActivity(intent);
//
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
}
