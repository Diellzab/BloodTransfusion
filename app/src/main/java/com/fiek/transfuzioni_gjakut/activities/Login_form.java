package com.fiek.transfuzioni_gjakut.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.fiek.transfuzioni_gjakut.R;
import com.fiek.transfuzioni_gjakut.addDonorDataInsert;
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
    CheckBox rememberMe;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;



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

        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();
        checkSharedPreferences();





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

//                if (rememberMe.isChecked()){
//
//                    mEditor.putString(getString(R.string.checkbox), "True");
//                    mEditor.commit();
//
//                    //save the name
//                    String username = etEmail.getText().toString();
//                    mEditor.putString(getString(R.string.username), username);
//                    mEditor.commit();
//
//                    String password = etPassword.getText().toString();
//                    mEditor.putString(getString(R.string.password), password);
//                    mEditor.commit();
//
//                    Intent intent = new Intent(Login_form.this, Dashboard.class);
//                    startActivity(intent);
//
//
//                }
//                else{
//
//                    mEditor.putString(getString(R.string.checkbox), "False");
//                    mEditor.commit();
//
//                    //save the name
//                    String username = etEmail.getText().toString();
//                    mEditor.putString(getString(R.string.username), "");
//                    mEditor.commit();
//
//                    String password = etPassword.getText().toString();
//                    mEditor.putString(getString(R.string.password), "");
//                    mEditor.commit();
//
//                    Intent intent = new Intent(Login_form.this, Dashboard.class);
//                    startActivity(intent);
//
//                    SetValidation();
//
//
//
//
//
//                }

                isUser();

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

    private void isUser(){
        final String userEnteredEmailSession = etEmail.getText().toString().trim();
        final String userEnteredEmail = etEmail.getText().toString().trim();
        final String userEnteredPassword = etPassword.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
        Query checkUser = reference.orderByChild("email").equalTo(userEnteredEmail);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {

                    etEmail.setError(null);

                    for (DataSnapshot artistSnapshot1: dataSnapshot.getChildren()) {
//                        UsersGetClass artist = artistSnapshot1.getValue(UsersGetClass.class);


                        String passwordFromDB = artistSnapshot1.child("password").getValue(String.class);
//                        String passwordFromDB = dataSnapshot.child().child("password").getValue(String.class);
                        if(passwordFromDB.equals(userEnteredPassword)){


//////////////////%%%%%%%%%%%%%%%%%%%%%%%%%%//////////////////////////////////////
                            tvRegister.setText("Jeni lloguar");
                            Intent intent = new Intent(getBaseContext(), UserProfile.class);
                            intent.putExtra("emailSession", userEnteredEmailSession);
                            startActivity(intent);
                        }

                        else  {
                            tvRegister.setText("nuk ben");
                        }

                    }



//                        String nameFromDB = dataSnapshot.child(userEnteredEmail).child("emri").getValue(String.class);
//                        String surnameFromDB = dataSnapshot.child(userEnteredEmail).child("mbiemri").getValue(String.class);
//                        String emailFromDB = dataSnapshot.child(userEnteredEmail).child("email").getValue(String.class);
//                        String phoneFromDB = dataSnapshot.child(userEnteredEmail).child("telefoni").getValue(String.class);
//                        String bloodTypeFromDB = dataSnapshot.child(userEnteredEmail).child("tipiGjakut").getValue(String.class);
//
//
//
//
//                        Intent intent = new Intent(getApplicationContext(), UserProfile.class);
//                        intent.putExtra("emri", nameFromDB);
//                        intent.putExtra("mbiemri", surnameFromDB);
//                        intent.putExtra("email", emailFromDB);
//                        intent.putExtra("telefoni", phoneFromDB);
//                        intent.putExtra("tipiGjakut", bloodTypeFromDB);
//
//                        startActivity(intent);




//                    else{
////                        etPassword.setError("Wrong Password");
////                        etPassword.requestFocus();
//                    }
                }

                else{
                    etEmail.setError("No such user exist");
                    etEmail.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    public void checkSharedPreferences(){

        String checkbox = mPreferences.getString(getString(R.string.checkbox), "false");
        String username = mPreferences.getString(getString(R.string.username), "");
        String password = mPreferences.getString(getString(R.string.password), "");

        etEmail.setText(username);
        etPassword.setText(password);
        if (checkbox.equals("True")){
            rememberMe.setChecked(true);
        }else{
            rememberMe.setChecked(false);
        }

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

        } else if (etPassword.getText().length() < 5) {
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
