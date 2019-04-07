package com.community.protectcommunity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences sharedPref;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set up font
        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath("fonts/Oh_Whale.ttf")
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());
        setContentView(R.layout.activity_game);
        //Set up full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Get Views and set up views
        sharedPref = this.getSharedPreferences("username_gender_choice", Context.MODE_PRIVATE);
        System.out.println(sharedPref.getString("username",null));
        System.out.println(sharedPref.getString("gender",null));

        //Switch fragment to introduction fragment
        Fragment nextFragment = new IntroductionFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.game_change_area, nextFragment).commit();

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
    }

}