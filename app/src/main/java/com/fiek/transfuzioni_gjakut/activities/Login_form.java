package com.fiek.transfuzioni_gjakut.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.fiek.transfuzioni_gjakut.R;
import com.fiek.transfuzioni_gjakut.forms.RegistrationForm;
import com.google.android.material.textfield.TextInputLayout;


import java.util.regex.Pattern;

public class Login_form  extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnLogin, coronaVirus;
    TextView tvRegister, coronaVirusCases;
    boolean isEmailValid, isPasswordValid;
    TextInputLayout emailError, passwordError;
    CheckBox rememberMe;



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
        rememberMe = findViewById(R.id.rememberMe);

        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        String checkbox = preferences.getString("remember", "");
        if(checkbox.equals("true")){
            //Intent intent = new Intent(Login_form.this, Dashboard.class);
            //startActivity(intent);
        }
        else if(checkbox.equals("false")){
            //Toast.makeText(this, "Please Sign In", Toast.LENGTH_SHORT).show();

        }

        rememberMe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(compoundButton.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","true");
                    editor.apply();
                    Toast.makeText(Login_form.this, "Checked", Toast.LENGTH_SHORT).show();

                }
                else if (!compoundButton.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","false");
                    editor.apply();
                    Toast.makeText(Login_form.this, "Unchecked", Toast.LENGTH_SHORT).show();

                }
            }
        });








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

                Intent intent = new Intent(Login_form.this, Dashboard.class);
                startActivity(intent);

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
