package com.example.mohamed.bank;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.mohamed.bank.IntroSlider.MpagerAdapter;
import com.example.mohamed.bank.IntroSlider.PreferenceManger;
import com.example.mohamed.bank.Views.LoginActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager mpager;
    private int[]layouts = {R.layout.first_slide,R.layout.second_slide};
    private MpagerAdapter mpagerAdapter;
    private LinearLayout dots_layout;
    private ImageView[]dots;
    private Button bnSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            if (window != null) {
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
            }

        }
            bnSkip = (Button) findViewById(R.id.bnSkip);
            mpager = (ViewPager) findViewById(R.id.view_pager);
            dots_layout = (LinearLayout) findViewById(R.id.dotslayout);
            mpagerAdapter = new MpagerAdapter(layouts, this);
            dots_layout = (LinearLayout) findViewById(R.id.dotslayout);
            mpager.setAdapter(mpagerAdapter);


            createDots(0);

            bnSkip.setOnClickListener(this);

        }

    private void createDots(int currentPosition){
        if (dots_layout != null){
            dots_layout.removeAllViews();
            dots = new ImageView[layouts.length];
            for (int i=0 ; i< layouts.length ; i++){
                dots[i] = new ImageView(this);
                if (i == currentPosition){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(this,R.drawable.active_dots));
                }else {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(this,R.drawable.default_dots));
                }
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                params.setMargins(4,0,4,0);
                dots_layout.addView(dots[i],params);
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bnSkip:
                loadHome();
                new PreferenceManger(this).writePreferences();
                break;
        }
    }
    private void loadHome(){
        startActivity(new Intent(MainActivity.this,LoginActivity.class));
        finish();
    }
    private void loadNextSlide(){
        int nextSlide = mpager.getCurrentItem()+1;
        if (nextSlide<layouts.length){
            mpager.setCurrentItem(nextSlide);
            createDots(1);
        }else {
            loadHome();
            new PreferenceManger(this).writePreferences();
        }
    }
}
