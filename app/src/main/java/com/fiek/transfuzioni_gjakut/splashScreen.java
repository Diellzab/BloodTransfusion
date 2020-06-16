package com.fiek.transfuzioni_gjakut;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class splashScreen extends AppCompatActivity {

    //Sa gjate duhet me qendru
    private static int SPLASH_TIME_OUT = 5000;

    //Animation
    Animation topAnimation, bottomAnimation, middleAnimation;
    View first, second, third, fourth, fifth, sixth, seventh, eigtht;
    TextView bloodDonation, tagLine;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        middleAnimation = AnimationUtils.loadAnimation(this, R.anim.middle_animation);

        first = findViewById(R.id.first_line);
        second = findViewById(R.id.second_line);
        third = findViewById(R.id.third_line);
        fourth = findViewById(R.id.fourth_left);
        fifth = findViewById(R.id.first_linee);
        sixth = findViewById(R.id.second_linee);
        seventh = findViewById(R.id.third_linee);
        eigtht = findViewById(R.id.fourth_linee);


        bloodDonation  = findViewById(R.id.bloodDonation);
        tagLine = findViewById(R.id.tagLine);


        first.setAnimation(topAnimation);
        second.setAnimation(topAnimation);
        third.setAnimation(topAnimation);
        fourth.setAnimation(topAnimation);
        fifth.setAnimation(topAnimation);
        sixth.setAnimation(topAnimation);
        seventh.setAnimation(topAnimation);
        eigtht.setAnimation(topAnimation);

        bloodDonation.setAnimation(middleAnimation);
        tagLine.setAnimation(bottomAnimation);


        new Handler() .postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splashScreen.this, Home.class);
                startActivity(intent);
                finish();
            }

        }, SPLASH_TIME_OUT);

    }
}
