package com.example.mohamed.bank;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mohamed.bank.Home.HomeActivity;
import com.example.mohamed.bank.Models.Report.Report;
import com.example.mohamed.bank.Models.Report.ReportExample;
import com.example.mohamed.bank.Service.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText report_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        report_text = (EditText) findViewById(R.id.report_text);

    }

    public void sendReport(View view) {
        String reportMsg = report_text.getText().toString();


        Report report_msg = new Report("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27",reportMsg);
        Call<ReportExample> call = RetrofitInstance.getDataService().sendReport(report_msg);
        call.enqueue(new Callback<ReportExample>() {
            @Override
            public void onResponse(Call<ReportExample> call, Response<ReportExample> response) {

                if (response.body().getStatus() == 1) {
                    ReportExample re = response.body();
                    Toast.makeText(ReportActivity.this, re.getMsg(), Toast.LENGTH_LONG).show();
                    Intent i = new Intent(ReportActivity.this,HomeActivity.class);
                    startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<ReportExample> call, Throwable t) {

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
