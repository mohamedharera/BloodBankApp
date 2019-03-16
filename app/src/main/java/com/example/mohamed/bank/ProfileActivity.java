package com.example.mohamed.bank;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.mohamed.bank.Models.Datum;
import com.example.mohamed.bank.Models.DatumCity;
import com.example.mohamed.bank.Models.Example;
import com.example.mohamed.bank.Models.ExampleCity;
import com.example.mohamed.bank.Service.GetDataService;
import com.example.mohamed.bank.Service.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    EditText user_name,user_email,user_birth,user_blood,user_mobile,user_password,user_confirm,user_last_date;
    String name,email,birth,blood,phone,passworrd,confirmPass,date,Stringgovern,Stringcity;
    Spinner GovernSpinner,countrySpinner;
    String countrySpinnerString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        user_name = findViewById(R.id.user_name);
        user_email = findViewById(R.id.user_email);
        user_birth = findViewById(R.id.user_birth);
        user_blood = findViewById(R.id.user_blood);
        user_mobile = findViewById(R.id.user_mobile);
        user_password = findViewById(R.id.user_password);
        user_confirm = findViewById(R.id.user_confirm);
        user_last_date = findViewById(R.id.user_last_date);
        GovernSpinner = findViewById(R.id.GovernSpinner);
        countrySpinner = findViewById(R.id.countrySpinner);


        SharedPreferences prefs = getSharedPreferences("my_shared_preff", MODE_PRIVATE);

        String nameText = prefs.getString("name", null);
        if (nameText != null) {
            name = prefs.getString("name", "No name defined");
            user_name.setText(name);
        }

        String emailText = prefs.getString("email", null);
        if (emailText != null) {
            email = prefs.getString("email", "No email defined");
            user_email.setText(email);
        }

        String birth_dateText = prefs.getString("birth_date", null);
        if (birth_dateText != null) {
            birth = prefs.getString("birth_date", "No birth_date defined");
            user_birth.setText(birth);
        }

        String phoneText = prefs.getString("phone", null);
        if (phoneText != null) {
            phone = prefs.getString("phone", "No phone defined");
            user_mobile.setText(phone);
        }

        String blood_typeText = prefs.getString("blood_type", null);
        if (blood_typeText != null) {
            blood = prefs.getString("blood_type", "No name defined");
            user_blood.setText(blood);
        }

        String last_donationText = prefs.getString("last_donation", null);
        if (last_donationText != null) {
            date = prefs.getString("last_donation", "No last_donation defined");
            user_last_date.setText(date);
        }
        String city_idText = prefs.getString("city_id", null);
        if (city_idText != null) {
            Stringcity = prefs.getString("city_id", "No city_id defined");
        }

        SharedPreferences settings = getSharedPreferences("SETTING_INFOS", 0);
        passworrd = settings.getString("password", "");
        user_password.setText(passworrd);
        user_confirm.setText(passworrd);

        getListGovern();
    }


    public void getListGovern() {
        final GetDataService dataservices = RetrofitInstance.getDataService();
        Call<Example> call = dataservices.getListGovern();
        call.enqueue(new Callback<Example>() {

            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {

                Example govern = response.body();
                List<Datum> listofgouvernet = govern.getData();
                List<String> listSpinner = new ArrayList<String>();
//                listSpinner.add("اختار المحافظه");
                final List<Integer> listIds = new ArrayList<>();

                for (int i = 0; i < listofgouvernet.size(); i++) {
                    listSpinner.add(listofgouvernet.get(i).getName());
                    listIds.add(listofgouvernet.get(i).getId());
                }

                final int[] id = {-1};

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(ProfileActivity.this, android.R.layout.simple_spinner_item, listSpinner);
                adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                GovernSpinner.setAdapter(adapter);
                GovernSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                        if (position != 0) {
                            id[0] = listIds.get(position-1);
                            getListCities(id[0]);
                            GovernSpinner.getSelectedItem().toString();
                        }
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.i("governetmodel", "error");
            }
        });
    }

    public void getListCities(int city_id) {
        final GetDataService dataservices2 = RetrofitInstance.getDataService();
        Call<ExampleCity> call = dataservices2.getListCities(city_id);
        call.enqueue(new Callback<ExampleCity>() {

            @Override
            public void onResponse(Call<ExampleCity> call, Response<ExampleCity> response) {

                ExampleCity city = response.body();
                List<DatumCity> listofcity = city.getDataList();
                final List<String> listSpinner = new ArrayList<String>();
                final List<Integer> listSpinnerIds = new ArrayList<>();

                for (int i = 0; i < listofcity.size(); i++) {
                    listSpinner.add(listofcity.get(i).getName());
                    listSpinnerIds.add(listofcity.get(i).getId());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(ProfileActivity.this, android.R.layout.simple_spinner_item, listSpinner);
                adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                countrySpinner.setAdapter(adapter);
                countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                        Log.i("selectedItem", listSpinner.get(position));

                        countrySpinner.getSelectedItem().toString();

                        countrySpinnerString = String.valueOf(listSpinnerIds.get(position));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
            @Override
            public void onFailure(Call<ExampleCity> call, Throwable t) {
                Log.i("selected item", "error");
            }
        });
    }
}
