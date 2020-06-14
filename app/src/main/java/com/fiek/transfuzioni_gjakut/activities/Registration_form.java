package com.fiek.transfuzioni_gjakut.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fiek.transfuzioni_gjakut.MainActivity;
import com.fiek.transfuzioni_gjakut.R;
import com.fiek.transfuzioni_gjakut.forms.LoginForm;
import com.fiek.transfuzioni_gjakut.forms.RegistrationForm;


public class Registration_form extends AppCompatActivity {

    TextView tvSignIn;
    EditText etFName, etLName, etEmail, etPassword, etTelephone, etAddress;
    RadioButton  radioButton;
    Button btnRegister;
    Spinner spinner;
    CheckBox cbDonor;
    RadioGroup radioGroup;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form);

        etFName = findViewById(R.id.EditTextFirstName);
        etLName =  findViewById(R.id.EditTextLastName);
//        rbFemale =  findViewById(R.id.radioButtonFemale);
//        rbMale =  findViewById(R.id.radioButtonMale);
        etEmail = findViewById(R.id.EditTextEmailAddress);
        etPassword =  findViewById(R.id.EditTextPassword);
        etTelephone =  findViewById(R.id.EditTextTelephone);
        etAddress =  findViewById(R.id.EditTextAddress);
        btnRegister =  findViewById(R.id.buttonSave);
        spinner =  findViewById(R.id.spinner);
        cbDonor =  findViewById(R.id.checkBoxDonor);
        radioGroup = findViewById(R.id.radioGroup);

        cbDonor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cbDonor.isChecked() == true){
                    return;
                }

            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Registration_form.this, Login_form.class);
                startActivity(intent);

                int radioButtonId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioButtonId);
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

        int radioId = radioGroup.getCheckedRadioButtonId();

        if(radioId == -1){
            Toast.makeText(MainActivity.this, "Click one", Toast.LENGTH_SHORT).show();
           return;
        }
        if(!cbDonor.isChecked()){
            Toast.makeText(MainActivity.this, "You should check this", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(MainActivity.this, "Successfull", Toast.LENGTH_SHORT).show();
    }

    public void onRadioButtonClicked(View v){

        int radioButtonId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioButtonId);
        Toast.makeText(this, "Selected Blood Type: " + radioButton.getText(),
                Toast.LENGTH_SHORT).show();

    }





    //Check for valid surname


}
