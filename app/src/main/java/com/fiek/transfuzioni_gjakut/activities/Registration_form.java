package com.fiek.transfuzioni_gjakut.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.fiek.transfuzioni_gjakut.R;


public class Registration_form extends AppCompatActivity {

    TextView tvSignIn;
    EditText etFName, etLName, etEmail, etPassword, etTelephone, etAddress;
    RadioButton rbFemale, rbMale;
    Button btnRegister;
    Spinner spinner;
    CheckBox cbDonor;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form);

        etFName = (EditText) findViewById(R.id.EditTextFirstName);
        etLName = (EditText) findViewById(R.id.EditTextLastName);
        rbFemale = (RadioButton) findViewById(R.id.radioButtonFemale);
        rbMale = (RadioButton) findViewById(R.id.radioButtonMale);
        etEmail = (EditText) findViewById(R.id.EditTextEmailAddress);
        etPassword = (EditText) findViewById(R.id.EditTextPassword);
        etTelephone = (EditText) findViewById(R.id.EditTextTelephone);
        etAddress = (EditText) findViewById(R.id.EditTextAddress);
        btnRegister = (Button) findViewById(R.id.buttonSave);
        spinner = (Spinner) findViewById(R.id.spinner);
        cbDonor = (CheckBox) findViewById(R.id.checkBoxDonor);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login_form.class);
                startActivity(intent);
            }
        });
    }

    public  void SetValidation() {
        //Check for a valid name
        if(etFName.getText().toString().isEmpty()){

        }
    }


    //Check for valid surname


}
