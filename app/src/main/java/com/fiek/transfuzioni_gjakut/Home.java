package com.fiek.transfuzioni_gjakut;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.fiek.transfuzioni_gjakut.activities.Login_form;
import com.fiek.transfuzioni_gjakut.activities.Registration_form;
import com.fiek.transfuzioni_gjakut.forms.LoginForm;

public class Home extends AppCompatActivity {
    Button btnHomeLogin, btnHomeSingUP;
    ImageView imgViewHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        btnHomeLogin= findViewById(R.id.btnHomeLogin);
        btnHomeSingUP = findViewById(R.id.btnHomeSignUp);
        imgViewHome = findViewById(R.id.imgViewHome);

        btnHomeLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(Home.this, LoginForm.class);
                startActivity(intent);
                finish();
            }
        });


        btnHomeSingUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Registration_form .class);
                startActivity(intent);
                finish();
            }
        });


    }


}

