package com.example.mohamed.bank.Views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mohamed.bank.Home.HomeActivity;
import com.example.mohamed.bank.Models.Login;
import com.example.mohamed.bank.Models.login.LoginResponse;
import com.example.mohamed.bank.R;
import com.example.mohamed.bank.Service.GetDataService;
import com.example.mohamed.bank.Service.RetrofitInstance;
import com.example.mohamed.bank.SharedprefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText phone,pass;
    String  phone2,pass2;

    private Boolean saveLogin;
    CheckBox remember;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        phone = (EditText) findViewById(R.id.phone);
        pass = (EditText) findViewById(R.id.pass);
//        remember = findViewById(R.id.remember);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (SharedprefManager.getInstance(this).isloggedIn()){
                Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
        }
    }

    public void enter(View view) {
         phone2 = phone.getText().toString();
         pass2 = pass.getText().toString();

        if (phone2.isEmpty()) {
            phone.setError("phone is required");
            phone.requestFocus();
            return;
        }
        if (pass2.isEmpty()) {
            pass.setError("password is required");
            pass.requestFocus();
            return;
        }

        Login login = new Login(phone2,pass2);
        GetDataService getDataService = RetrofitInstance.getDataService();
        Call<LoginResponse> call = getDataService.login(login);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                    LoginResponse example = response.body();
                    SharedprefManager.getInstance(LoginActivity.this).saveUser(example.getData().getClient());
                    Intent i = new Intent(LoginActivity.this,HomeActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void register(View view){
        Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
    }

}

