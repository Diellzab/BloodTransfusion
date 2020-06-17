package com.fiek.transfuzioni_gjakut.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
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
    RadioGroup radioGroupMF;
    RadioButton radioButtonMF;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form);

        etFName = findViewById(R.id.first_name_id);
        etLName =  findViewById(R.id.last_name_id);
        etEmail = findViewById(R.id.emailAddress_id);
        etPassword =  findViewById(R.id.password_id);
        etTelephone =  findViewById(R.id.phone_number_id);
        etAddress =  findViewById(R.id.address_id);
        btnRegister =  findViewById(R.id.buttonSave);
//        spinner =  findViewById(R.id.spinner);
//        cbDonor =  findViewById(R.id.checkBoxDonor);
        radioGroupMF = findViewById(R.id.genderRadioGroup);


//        Spinner spinner = findViewById(R.id.spinner);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.numbers, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener(this);
//
//
//        @Override
//        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//            String text = parent.getItemAtPosition(position).toString();
//            Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
//        }
//        @Override
//        public void onNothingSelected(AdapterView<?> parent) {
//        }
//
//
//        cbDonor.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(cbDonor.isChecked() == true){
//                    return;
//                }
//
//            }
//        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Registration_form.this, LoginForm.class);
                startActivity(intent);

                int radioButtonIdMF = radioGroupMF.getCheckedRadioButtonId();
                radioButton = findViewById(radioButtonIdMF);


//              Qeto ki mi shti n'datanaz
                String MaleFemale = radioButtonMF.getText().toString().trim(); // MaleFemale
                String emri = etFName.getText().toString().trim(); // Emri
                String mbiemri = etLName.getText().toString().trim(); // Mbiemri
                String email = etEmail.getText().toString().trim(); // Email
                String telefoni = etTelephone.getText().toString().trim(); // Phone
                String password = etPassword.getText().toString().trim(); // Password
                String adresa = etAddress.getText().toString().trim(); // Adresa


            }
        });
//
//
//        tvSignIn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), Login_form.class);
//                startActivity(intent);
//            }
//        });
//    }
//
//
//
//    public  void SetValidation() {
//        //Check for a valid name
//
//        int radioId = radioGroup.getCheckedRadioButtonId();
//
//        if(radioId == -1){
////            Toast.makeText(MainActivity.this, "Click one", Toast.LENGTH_SHORT).show();
//           return;
//        }
//        if(!cbDonor.isChecked()){
////            Toast.makeText(MainActivity.this, "You should check this", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
////        Toast.makeText(MainActivity.this, "Successfull", Toast.LENGTH_SHORT).show();
//    }
//
//    public void onRadioButtonClicked(View v){
//
//        int radioButtonId = radioGroup.getCheckedRadioButtonId();
//        radioButton = findViewById(radioButtonId);
//        Toast.makeText(this, "Selected Blood Type: " + radioButton.getText(),
//                Toast.LENGTH_SHORT).show();
//
//    }
//


        //Check for valid surname


    }

    //Ktu posht i kemi insertu te dhenat per Radio Groups
    public void onRadioButtonClickedGender(View v) {
        int radioId = radioGroupMF.getCheckedRadioButtonId();
        radioButtonMF = findViewById(radioId);
        Toast.makeText(this,  radioButtonMF.getText(),
                Toast.LENGTH_SHORT).show();
    }
}
