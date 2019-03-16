package com.example.mohamed.bank.Views;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mohamed.bank.Home.HomeActivity;
import com.example.mohamed.bank.Models.Datum;
import com.example.mohamed.bank.Models.DatumCity;
import com.example.mohamed.bank.Models.Example;
import com.example.mohamed.bank.Models.ExampleCity;
import com.example.mohamed.bank.Models.register.RegisterResponse;
import com.example.mohamed.bank.Models.register.User;
import com.example.mohamed.bank.R;
import com.example.mohamed.bank.Service.GetDataService;
import com.example.mohamed.bank.Service.RetrofitInstance;
import com.example.mohamed.bank.SharedprefManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mohamed.bank.R.id.city;

public class RegisterActivity extends AppCompatActivity  {

    Toolbar toolbar;
    Spinner spinner, spinner2;
    EditText name;
    EditText email;
    static EditText birth;
    EditText blood;
    EditText phone;
    EditText password;
    EditText confirm;
    EditText last_date;
    String spinnerity;
    ImageButton showDatePicker,showLastDate;
    String date,lastDate;
    int bloodId;

    final Calendar myCalendar = Calendar.getInstance();
    final Calendar lastDateCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        spinner = (Spinner) findViewById(R.id.country);
        spinner2 = (Spinner) findViewById(city);
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        birth = (EditText) findViewById(R.id.birth);
        blood = (EditText) findViewById(R.id.blood);
        phone = (EditText) findViewById(R.id.mobile);
        password = (EditText) findViewById(R.id.password);
        confirm = (EditText) findViewById(R.id.confirm);
        last_date = (EditText) findViewById(R.id.last_date);
        showDatePicker = findViewById(R.id.showDatePicker);
        showLastDate = findViewById(R.id.showLastDate);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        getListGovern();


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        final DatePickerDialog.OnDateSetListener datee = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                lastDateCalendar.set(Calendar.YEAR, year);
                lastDateCalendar.set(Calendar.MONTH, monthOfYear);
                lastDateCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel2();
            }

        };

        showDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(RegisterActivity.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        showLastDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(RegisterActivity.this, datee, lastDateCalendar.get(Calendar.YEAR), lastDateCalendar.get(Calendar.MONTH), lastDateCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel() {
        String myFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        date =sdf.format(myCalendar.getTime());
        birth.setText(date);
    }
    private void updateLabel2() {
        String myFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        lastDate =sdf.format(lastDateCalendar.getTime());
        last_date.setText(lastDate);
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
                listSpinner.add("اختار المحافظه");
                final List<Integer> listIds = new ArrayList<>();

                for (int i = 0; i < listofgouvernet.size(); i++) {
                    listSpinner.add(listofgouvernet.get(i).getName());
                    listIds.add(listofgouvernet.get(i).getId());
                }

                final int[] id = {-1};

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_item, listSpinner);
                adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                        if (position != 0) {
                            id[0] = listIds.get(position - 1);
                            getListCities(id[0]);
                            spinner.getSelectedItem().toString();
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

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_item, listSpinner);
                adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                spinner2.setAdapter(adapter);
                spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                        Log.i("selectedItem", listSpinner.get(position));

                        spinner2.getSelectedItem().toString();
                        spinnerity = String.valueOf(listSpinnerIds.get(position));
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

    @Override
    protected void onStart() {
        super.onStart();
        if (SharedprefManager.getInstance(this).isloggedIn()){
            Intent intent = new Intent(RegisterActivity.this,HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        }
    }



    public void register_button(View view) {

        String namee = name.getText().toString();
        String emaill = email.getText().toString();
        String birthh = birth.getText().toString();
        String bloodd = blood.getText().toString();
        String phonee = phone.getText().toString();
        String pass = password.getText().toString();
        String confirm_pass = confirm.getText().toString();
        String last_datee = last_date.getText().toString();

        SharedPreferences preferences = getSharedPreferences("MYPREFS",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("phone",phonee);
        editor.putString("pass",pass);
        editor.commit();

        SharedPreferences settings = getSharedPreferences("SETTING_INFOS", 0);
        SharedPreferences.Editor edit = settings.edit();
        edit.putString("password", pass);
        edit.commit();

        if (namee.isEmpty()) {
            name.setError("name is required");
            name.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(emaill).matches()) {
            email.setError("email is required");
            email.requestFocus();
            return;
        }
        if (birthh.isEmpty()) {
            birth.setError("birth is required");
            birth.requestFocus();
            return;
        }
        if (bloodd.isEmpty()) {
            blood.setError("blood is required");
            blood.requestFocus();
            return;
        }
        if (phonee.isEmpty()) {
            phone.setError("phone is required");
            phone.requestFocus();
            return;
        }
        if (pass.isEmpty()) {
            password.setError("password is required");
            password.requestFocus();
            return;
        }
        if (last_datee.isEmpty()) {
            last_date.setError("password is required");
            last_date.requestFocus();
            return;
        }

        final User user = new User(namee, emaill, birthh, spinnerity, phonee, last_datee, pass, confirm_pass, 1);
        Call<RegisterResponse> call = RetrofitInstance.getDataService().createUser(user);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                if (response.body().getStatus() == 1) {
                    RegisterResponse dr = response.body();
                    bloodId = user.getBlood_type_id();
                    Toast.makeText(RegisterActivity.this, dr.getMsg(), Toast.LENGTH_LONG).show();
                    Intent i = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(i);
                    Log.e("myResponse", "1   "+response.body().getMsg());


                } else {
                    Log.e("myResponse", response.body().getMsg());

                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Log.e("myResponse", "error:  " + t.getMessage());
            }
        });
    }
}
