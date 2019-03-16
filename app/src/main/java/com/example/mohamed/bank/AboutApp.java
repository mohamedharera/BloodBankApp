package com.example.mohamed.bank;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.mohamed.bank.Models.aboutApp.AboutApp_Data;
import com.example.mohamed.bank.Models.aboutApp.AboutApp_Response;
import com.example.mohamed.bank.Service.GetDataService;
import com.example.mohamed.bank.Service.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AboutApp extends AppCompatActivity {

    Toolbar toolbar;
    TextView aboutUS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        aboutUS = findViewById(R.id.aboutUS);

        getData();
    }

    public void getData(){

        final GetDataService dataservices = RetrofitInstance.getDataService();
        Call<AboutApp_Response> call = dataservices.getAppInfo("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27");
        call.enqueue(new Callback<AboutApp_Response>() {
            @Override
            public void onResponse(Call<AboutApp_Response> call, Response<AboutApp_Response> response) {
                AboutApp_Response response1 = response.body();
                AboutApp_Data data = response1.getData();
                aboutUS.setText(data.getAboutApp());
            }

            @Override
            public void onFailure(Call<AboutApp_Response> call, Throwable t) {

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
//                return true;
//
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
}
