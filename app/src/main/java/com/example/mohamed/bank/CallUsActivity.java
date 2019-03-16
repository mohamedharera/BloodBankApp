package com.example.mohamed.bank;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohamed.bank.Home.HomeActivity;
import com.example.mohamed.bank.Models.Call_us.CallUs_Data;
import com.example.mohamed.bank.Models.Call_us.CallUs_Example;
import com.example.mohamed.bank.Models.Call_us.Contact;
import com.example.mohamed.bank.Models.Call_us.ContactUsExample;
import com.example.mohamed.bank.Service.GetDataService;
import com.example.mohamed.bank.Service.RetrofitInstance;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallUsActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView phone,email;
    CircleImageView facebook,twitter,gmail,insta,whats,youtube;
    EditText messageText,messageTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_us);

        phone =  findViewById(R.id.phone);
        email =  findViewById(R.id.email);
        facebook =  findViewById(R.id.facebook);
        twitter =  findViewById(R.id.twitter);
        gmail =  findViewById(R.id.gmail);
        insta =  findViewById(R.id.insta);
        whats =  findViewById(R.id.whats);
        youtube =  findViewById(R.id.youtube);
        messageTitle =  findViewById(R.id.messageTitle);
        messageText =  findViewById(R.id.messageText);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        getData();

    }

    public void sendMessage(View view){
        String title = messageTitle.getText().toString();
        String text = messageText.getText().toString();

        Contact contact = new Contact("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27",title,text);
        Call<ContactUsExample> call = RetrofitInstance.getDataService().contact(contact);
        call.enqueue(new Callback<ContactUsExample>() {
            @Override
            public void onResponse(Call<ContactUsExample> call, Response<ContactUsExample> response) {

                if (response.body().getStatus() == 1) {
                    ContactUsExample re = response.body();
                    Toast.makeText(CallUsActivity.this, re.getMsg(), Toast.LENGTH_LONG).show();
                    Intent i = new Intent(CallUsActivity.this,HomeActivity.class);
                    startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<ContactUsExample> call, Throwable t) {

            }
        });

    }

    public void getData(){

        final GetDataService dataservices = RetrofitInstance.getDataService();
        Call<CallUs_Example> call = dataservices.getLinks("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27");
        call.enqueue(new Callback<CallUs_Example>() {
            @Override
            public void onResponse(Call<CallUs_Example> call, Response<CallUs_Example> response) {
                CallUs_Example example = response.body();
                final CallUs_Data postsDetailsCategory= example.getData();
                phone.setText(postsDetailsCategory.getPhone());
                email.setText(postsDetailsCategory.getEmail());

                facebook.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String url = postsDetailsCategory.getFacebookUrl();
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    }
                });

                gmail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String url = postsDetailsCategory.getGoogleUrl();
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    }
                });

                insta.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String url = postsDetailsCategory.getInstagramUrl();
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    }
                });

                youtube.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String url =postsDetailsCategory.getYoutubeUrl();
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    }
                });

                whats.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PackageManager pm=getPackageManager();
                        try {

                            Intent waIntent = new Intent(Intent.ACTION_SEND);
                            waIntent.setType("text/plain");
                            String text = String.valueOf(phone);

                            PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
                            //Check if package exists or not. If not then code
                            //in catch block will be called
                            waIntent.setPackage("com.whatsapp");

                            waIntent.putExtra(Intent.EXTRA_TEXT, text);
                            startActivity(Intent.createChooser(waIntent, "Share with"));

                        } catch (PackageManager.NameNotFoundException e) {
                            Toast.makeText(CallUsActivity.this, "WhatsApp not Installed", Toast.LENGTH_SHORT)
                                    .show();
                        }

                    }

                });

                twitter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String url = postsDetailsCategory.getTwitterUrl();
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    }
                });
            }

            @Override
            public void onFailure(Call<CallUs_Example> call, Throwable t) {

            }
        });
    }

//
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
//                Intent intent = new Intent(CallUsActivity.this,HomeActivity.class);
//                startActivity(intent);
//
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

}
