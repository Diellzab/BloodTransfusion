package com.fiek.transfuzioni_gjakut.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fiek.transfuzioni_gjakut.R;
import com.google.android.material.textfield.TextInputLayout;

public class Admin_registration extends AppCompatActivity {

    TextInputLayout etFullname, etUsername, etEmail, etPassword, etPhone;
    Button btnAddAdmin;
    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_registration);

        etFullname = findViewById(R.id.name);
        etUsername = findViewById(R.id.username);
        etEmail = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);
        etPhone = findViewById(R.id.phone);
        btnAddAdmin = findViewById(R.id.btnAddAdmin);


        databaseHelper = new DatabaseHelper(this);

        btnAddAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nameValue = etFullname.getEditText().toString();
                String emailValue = etEmail.getEditText().toString();
                String usernameValue = etUsername.getEditText().toString();
                String phoneValue = etPhone.getEditText().toString();
                String passwordValue = etPassword.getEditText().toString();

                registerAdmin(view);


                if (emailValue.length() >1){
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("name", nameValue);
                    contentValues.put("email", emailValue);
                    contentValues.put("username", usernameValue);
                    contentValues.put("phone", phoneValue);
                    contentValues.put("password", passwordValue);

                    databaseHelper.inserUser(contentValues);
                    Toast.makeText(Admin_registration.this, "User Registred", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Admin_registration.this, "Enter the correct values", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    private Boolean validateName() {
        String val = etFullname.getEditText().getText().toString();

        if (val.isEmpty()) {
            etFullname.setError("Field cannot be empty");
            return false;
        }
        else {

            etFullname.setError(null);
            etFullname.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateUsername() {
        String val = etUsername.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if (val.isEmpty()) {
            etUsername.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 15) {
            etUsername.setError("Username too long");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            etUsername.setError("White Spaces are not allowed");
            return false;
        } else {
            etUsername.setError(null);
            etUsername.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateEmail() {
        String val = etEmail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            etEmail.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            etEmail.setError("Invalid email address");
            return false;
        } else {
            etEmail.setError(null);
            etEmail.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePhoneNo() {
        String val = etPhone.getEditText().getText().toString();

        if (val.isEmpty()) {
            etPhone.setError("Field cannot be empty");
            return false;
        } else {
            etPhone.setError(null);
            etPhone.setErrorEnabled(false);
            return true;
        }
    }


    private Boolean validatePassword() {
        String val = etPassword.getEditText().getText().toString();
        String passwordVal = "^" +
                //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if (val.isEmpty()) {
            etPassword.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            etPassword.setError("Password is too weak");
            return false;
        } else {
            etPassword.setError(null);
            etPassword.setErrorEnabled(false);
            return true;
        }
    }


    public void registerAdmin(View view) {

        if(!validateName() | !validatePassword() | !validatePhoneNo() | !validateEmail() | !validateUsername()){
            return;
        }
        String input = "Full Name: " + etFullname.getEditText().getText().toString();
        input += "\n";
        input += "Phone number: " + etPhone.getEditText().getText().toString();
        input += "\n";
        input += "Password: " + etPassword.getEditText().getText().toString();
        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
         input = "Email: " + etEmail.getEditText().getText().toString();
        input += "\n";
        input += "Username: " + etUsername.getEditText().getText().toString();
        input += "\n";

        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();

    }


}

