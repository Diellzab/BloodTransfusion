package com.fiek.transfuzioni_gjakut.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.fiek.transfuzioni_gjakut.R;
import com.google.android.material.textfield.TextInputLayout;

public class Admin_login extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnLogin, coronaVirus;
    TextView tvRegister, coronaVirusCases;
    boolean isEmailValid, isPasswordValid;
    TextInputLayout emailError, passwordError;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

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
                 */Intent intent = new Intent(Admin_login.this,
                        Coronavirus_Cases.class);
                startActivity(intent); // startActivity allow you to move
            }
        });



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetValidation();

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


}
