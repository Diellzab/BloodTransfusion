package com.fiek.transfuzioni_gjakut;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.fiek.transfuzioni_gjakut.activities.Login_form;
import com.fiek.transfuzioni_gjakut.activities.Registration_form;

public class Home extends AppCompatActivity {
    Button btnHomeLogin, btnHomeSingUP;
    ImageView imgViewHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnHomeLogin= findViewById(R.id.btnHomeLogin);
        btnHomeSingUP = findViewById(R.id.btnHomeSignUp);
        imgViewHome = findViewById(R.id.imgViewHome);

        btnHomeLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(Home.this, Login_form.class);
                startActivity(intent);
            }
        });


        btnHomeSingUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Registration_form.class);
                startActivity(intent);
            }
        });


    }


}

