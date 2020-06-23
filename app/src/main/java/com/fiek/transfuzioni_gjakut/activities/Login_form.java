package com.fiek.transfuzioni_gjakut.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.fiek.transfuzioni_gjakut.R;
import com.fiek.transfuzioni_gjakut.forms.RegistrationForm;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import java.util.regex.Pattern;

public class Login_form  extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnLogin, coronaVirus;
    TextView tvRegister, coronaVirusCases;
    boolean isEmailValid, isPasswordValid;
    TextInputLayout emailError, passwordError;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        tvRegister = (TextView) findViewById(R.id.tvRegister);
        emailError = (TextInputLayout) findViewById(R.id.emailErr);
        passwordError = (TextInputLayout) findViewById(R.id.errPassword);
        coronaVirus = (Button) findViewById(R.id.button_covid19);
//        coronaVirusCases = (TextView) findViewById(R.id.covid_cases_text);



        coronaVirus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                /*
                 * Intent is just like glue which helps to navigate one activity
                 * to another.
                 */Intent intent = new Intent(Login_form.this,
                        Coronavirus_Cases.class);
                startActivity(intent); // startActivity allow you to move
            }
        });



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String userEnteredEmail = etEmail.getText().toString().trim();
                final String userEnteredPassword = etPassword.getText().toString().trim();

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
                Query chechUser = reference.orderByChild("email").equalTo(userEnteredPassword);

                chechUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            String passwordFromDb = dataSnapshot.child(userEnteredEmail).child("password").getValue(String.class);
                            if(passwordFromDb.equals(userEnteredPassword)){
                                tvRegister.setText("OKAAAYYY JE LLOGU");
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Registration_form.class);
                startActivity(intent);
            }
        });
    }

    public  void SetValidation() {
        if(etEmail.getText().toString().isEmpty()){
            emailError.setError(getResources().getString(R.string.email_error));
            isEmailValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString()).matches()) {
            emailError.setError(getResources().getString(R.string.error_invalid_email));
            isEmailValid = false;
        } else {
                isEmailValid = true;
                emailError.setErrorEnabled(false);
            }

        if (etPassword.getText().toString().isEmpty()){
            passwordError.setError(getResources().getString(R.string.password_error));
            isPasswordValid = false;

        } else if (etPassword.getText().length() < 6) {
            passwordError.setError(getResources().getString(R.string.error_invalid_email));
            isPasswordValid = false;
        } else {
            isEmailValid = true;
            passwordError.setErrorEnabled(false);
        }

        if (isEmailValid && isPasswordValid)
        {
            Toast.makeText(getApplicationContext(), "Successfully", Toast.LENGTH_SHORT).show();
        }

  }

    public void isUser() {

    }

}
