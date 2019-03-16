package com.example.mohamed.bank;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mohamed.bank.Home.HomeActivity;
import com.example.mohamed.bank.Models.Datum;
import com.example.mohamed.bank.Models.DatumCity;
import com.example.mohamed.bank.Models.Example;
import com.example.mohamed.bank.Models.ExampleCity;
import com.example.mohamed.bank.Models.UserDonationRequest;
import com.example.mohamed.bank.Models.bloodType.BloodTypeDatum;
import com.example.mohamed.bank.Models.bloodType.BloodTypeResponse;
import com.example.mohamed.bank.Models.create_request.DonCreate_Example;
import com.example.mohamed.bank.Service.GetDataService;
import com.example.mohamed.bank.Service.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Donation_Ask extends AppCompatActivity {

    Toolbar toolbar;
    EditText name,birth,hospital,address,blugsText,phone,notice;
    Spinner country,city,spinnerBlood;
    int bloodId;
    String spinnerity;
//    Button addLocation;
//
//    private final String TAG = "PLACEPICKER_EXERCISE";
//    private final int RESOLVE_CONNECTION_REQUEST_CODE = 1000;
//    private final int PLACE_PICKER_REQUEST = 1001;
//    protected GoogleApiClient mGoogleApiClient;
//    private boolean mHaveLocPerm = false;
    // Invoked when the user clicks on the "Trigger the PlacePicker"
    // button in the main activity
//    private void pickAPlace() {
//        if (mHaveLocPerm) {
//            try {
//                // TODO: set a default viewport
//                // get the bounding area for the Pike Place market
//                LatLngBounds bounds = new LatLngBounds.Builder()
//                        .include(new LatLng(47.608670, -122.340033))
//                        .include(new LatLng(47.610005, -122.342865))
//                        .build();
//
//                // Trigger the PlacePicker, which will then pass the result
//                // to the onActivityResult function below
//                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
//                Intent intent = builder
//                        .setLatLngBounds(bounds)
//                        .build(this);
//
//                startActivityForResult(intent, PLACE_PICKER_REQUEST);
//            }
//            catch (Exception e) {
//                Log.e(TAG, e.toString());
//            }
//        }
//    }
//     Called from onActivityResult to update the UI with the data
//     for the Place that was selected by the user
//    private void updateUI(Place chosenPlace) {
//        address = (EditText) findViewById(R.id.address);
//
//        String str = (String) chosenPlace.getAddress();
////                + chosenPlace.getAddress() + "\n"
////                + chosenPlace.getPhoneNumber() + "\n"
////                + chosenPlace.getWebsiteUri();
//
//        address.setText(str);
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation__ask);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        country = (Spinner) findViewById(R.id.spinnerCountry);
        city = (Spinner) findViewById(R.id.spinnerCity);
//        addLocation = (Button) findViewById(R.id.addLocation);
        name = (EditText) findViewById(R.id.name);
        birth = (EditText) findViewById(R.id.birth);
//        address = (EditText) findViewById(R.id.address);
        spinnerBlood = (Spinner) findViewById(R.id.spinnerBlood);
        hospital = (EditText) findViewById(R.id.hospital);
        phone = (EditText) findViewById(R.id.phone);
        address = findViewById(R.id.address);
        notice = (EditText) findViewById(R.id.notice);
        blugsText = (EditText) findViewById(R.id.blugsText);



//        // Build the GoogleApiClient and connect to the Places API
//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .addApi(Places.GEO_DATA_API)
//                .addApi(Places.PLACE_DETECTION_API)
//                .addConnectionCallbacks(this)
//                .addOnConnectionFailedListener(this)
//                .build();
//
//        findViewById(R.id.addLocation).setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        pickAPlace();
//                    }
//                }
//        );
//

        getListGovern();
        getBloodType();
        goback();

    }


    public void postData(View view){

        String namee = name.getText().toString();
        String birthh = birth.getText().toString();
        String blugs_num = blugsText.getText().toString();
        String hospitall = hospital.getText().toString();
        String addresss = address.getText().toString();
        // cityId
        String phonee = phone.getText().toString();
        String noticee = notice.getText().toString();

        if (namee.isEmpty()) {
            name.setError("name is required");
            name.requestFocus();
            return;
        }
        if (birthh.isEmpty()) {
            birth.setError("birth is required");
            birth.requestFocus();
            return;
        }
        if (blugs_num.isEmpty()) {
            blugsText.setError("bags number is required");
            blugsText.requestFocus();
            return;
        }
        if (hospitall.isEmpty()) {
            hospital.setError("hospital is required");
            hospital.requestFocus();
            return;
        }
        if (phonee.isEmpty()) {
            phone.setError("phone is required");
            phone.requestFocus();
            return;
        }
        if (noticee.isEmpty()) {
            notice.setError("notice is required");
            notice.requestFocus();
            return;
        }

        UserDonationRequest userDonationRequest = new UserDonationRequest("W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl",namee,birthh,bloodId,blugs_num,hospitall,addresss,"1",phonee,noticee,"", "");
        final GetDataService dataservices = RetrofitInstance.getDataService();
        Call<DonCreate_Example> call = dataservices.postData(userDonationRequest);
        call.enqueue(new Callback<DonCreate_Example>() {
            @Override
            public void onResponse(Call<DonCreate_Example> call, Response<DonCreate_Example> response) {

                if (response.body().getStatus() == 1) {
                    DonCreate_Example dr = response.body();
                    Toast.makeText(Donation_Ask.this, dr.getMsg(), Toast.LENGTH_LONG).show();
                    Intent i = new Intent(Donation_Ask.this, HomeActivity.class);
                    startActivity(i);
                    Log.e("myResponse", "1"+response.body().getMsg());

                } else {
                    Log.e("myResponse", response.body().getMsg());

                }
            }

            @Override
            public void onFailure(Call<DonCreate_Example> call, Throwable t) {
                Log.e("myResponse", "error:  " + t.getMessage());
            }
        });
    }

    public void getBloodType() {
        final GetDataService dataservices = RetrofitInstance.getDataService();
        Call<BloodTypeResponse> call = dataservices.getBloodTypes();
        call.enqueue(new Callback<BloodTypeResponse>() {

            @Override
            public void onResponse(Call<BloodTypeResponse> call, Response<BloodTypeResponse> response) {

                BloodTypeResponse govern = response.body();
                List<BloodTypeDatum> listofblood = govern.getData();
                List<String> listSpinner = new ArrayList<String>();
                listSpinner.add("اختار فصيلة الدم");
                final List<Integer> listIds = new ArrayList<>();

                for (int i = 0; i < listofblood.size(); i++) {
                    listSpinner.add(listofblood.get(i).getName());
                    listIds.add(listofblood.get(i).getId());
                }

                final int[] id = {-1};

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Donation_Ask.this, android.R.layout.simple_spinner_item, listSpinner);
                adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                spinnerBlood.setAdapter(adapter);
                spinnerBlood.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                        if (position != 0) {
                            id[0] = listIds.get(position - 1);
                            getListCities(id[0]);

                            spinnerBlood.getSelectedItem().toString();
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }

            @Override
            public void onFailure(Call<BloodTypeResponse> call, Throwable t) {
                Log.i("governetmodel", "error");
            }
        });
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

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Donation_Ask.this, android.R.layout.simple_spinner_item, listSpinner);
                adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                country.setAdapter(adapter);
                country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                        if (position != 0) {
                            id[0] = listIds.get(position - 1);
                            getListCities(id[0]);

                            country.getSelectedItem().toString();
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

    public void getListCities(final int city_id) {
        final GetDataService dataservices2 = RetrofitInstance.getDataService();
        Call<ExampleCity> call = dataservices2.getListCities(city_id);
        call.enqueue(new Callback<ExampleCity>() {

            @Override
            public void onResponse(Call<ExampleCity> call, Response<ExampleCity> response) {

                ExampleCity exampleCity = response.body();
                List<DatumCity> listofcity = exampleCity.getDataList();
                final List<String> listSpinner = new ArrayList<String>();
                final List<Integer> listSpinnerIds = new ArrayList<>();


                for (int i = 0; i < listofcity.size(); i++) {
                    listSpinner.add(listofcity.get(i).getName());
                    listSpinnerIds.add(listofcity.get(i).getId());

                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Donation_Ask.this, android.R.layout.simple_spinner_item, listSpinner);
                adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                city.setAdapter(adapter);
                city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                        Log.i("selectedItem", listSpinner.get(position));

                        city.getSelectedItem().toString();
                        spinnerity = String.valueOf(listSpinnerIds.get(position));
                        bloodId = listSpinnerIds.get(position);
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

    public void goback(){
        ImageView back = findViewById(R.id.donationAsk_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Donation_Ask.this,HomeActivity.class);
                startActivity(intent);
            }
        });
    }

//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        mGoogleApiClient.connect();
//    }
//
//    @Override
//    protected void onStop() {
//        mGoogleApiClient.disconnect();
//        super.onStop();
//    }
//
//    @Override
//    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
//        switch (requestCode) {
//            // This code is passed when the user has resolved whatever connection
//            // problem there was with the Google Play Services library
//            case RESOLVE_CONNECTION_REQUEST_CODE:
//                if (resultCode == RESULT_OK) {
//                    mGoogleApiClient.connect();
//                }
//                break;
//
//            case PLACE_PICKER_REQUEST:
//                if (resultCode == RESULT_OK) {
//                    Place chosenPlace = PlacePicker.getPlace(this, data);
//                    updateUI(chosenPlace);
//                }
//                break;
//        }
//    }
//
//    /**
//     * Called when the user has been prompted at runtime to grant permissions
//     */
//    @Override
//    public void onRequestPermissionsResult(int reqCode, String[] perms, int[] results){
//        if (reqCode == 1) {
//            if (results.length > 0 && results[0] == PackageManager.PERMISSION_GRANTED) {
//                mHaveLocPerm = true;
//            }
//        }
//    }
//
//    /**
//     * Standard Google Play Services lifecycle callbacks
//     */
//    @Override
//    public void onConnected(Bundle connectionHint) {
//        Log.d(TAG, "Connected to Places API");
//
//        // If we're running on API 23 or above, we need to ask permissions at runtime
//        // In this case, we need Fine Location access to use Place Detection
//        int permCheck = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION);
//        if (permCheck != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
//        }
//        else {
//            mHaveLocPerm = true;
//        }
//    }
//
//    @Override
//    public void onConnectionFailed(@NonNull ConnectionResult result) {
//        Log.d(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());
//        if (result.hasResolution()) {
//            try {
//                result.startResolutionForResult(this, RESOLVE_CONNECTION_REQUEST_CODE);
//            }
//            catch (IntentSender.SendIntentException e) {
//                // Unable to resolve, message user appropriately
//            }
//        }
//        else {
//            GoogleApiAvailability gAPI = GoogleApiAvailability.getInstance();
//            gAPI.getErrorDialog(this, result.getErrorCode(), 0).show();
//        }
//    }
//
//    @Override
//    public void onConnectionSuspended(int cause) {
//        Log.d(TAG, "Connection was suspended for some reason");
//        mGoogleApiClient.connect();
//    }


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
//                Intent intent = new Intent(Donation_Ask.this,HomeActivity.class);
//                startActivity(intent);
//
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

}
