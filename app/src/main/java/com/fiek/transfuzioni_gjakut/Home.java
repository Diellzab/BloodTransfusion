package com.fiek.transfuzioni_gjakut;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fiek.transfuzioni_gjakut.activities.Admin_login;
import com.fiek.transfuzioni_gjakut.activities.Dashboard;
import com.fiek.transfuzioni_gjakut.activities.Login_form;
import com.fiek.transfuzioni_gjakut.activities.Registration_form;

public class Home extends AppCompatActivity {
    Button btnHomeLoginUser, btnHomeSingUP, btnHomeLoginAdmin;
    ImageView imgViewHome;
    TextView seemore;
    ImageView forward;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        btnHomeLoginUser= findViewById(R.id.btnHomeLoginUser);
        btnHomeLoginAdmin = findViewById(R.id.btnHomeLoginAdmin);
        btnHomeSingUP = findViewById(R.id.btnHomeSignUp);
        forward = findViewById(R.id.forward);
        seemore = findViewById(R.id.seemore);
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Dashboard.class);
                startActivity(intent);
            }
        });

        seemore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Dashboard.class);
                startActivity(intent);
            }
        });


        btnHomeLoginUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(Home.this, Login_form.class);
                startActivity(intent);

            }
        });

        btnHomeLoginAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Admin_login.class);
                startActivity(intent);

            }
        });


        btnHomeSingUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Registration_form .class);
                startActivity(intent);

            }
        });


    }


}

