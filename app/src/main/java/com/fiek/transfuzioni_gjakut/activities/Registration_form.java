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

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.fiek.transfuzioni_gjakut.R;
import com.fiek.transfuzioni_gjakut.models.AddUserClass;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Registration_form extends AppCompatActivity {

    TextView tvSignIn;
    EditText etFName, etLName, etEmail, etPassword, etTelephone, etAddress;

    Button btnRegister;
    Spinner spinner;
    CheckBox cbDonor;
    RadioGroup radioGroupMF;
    RadioButton radioButtonMF;

    RadioGroup radioGroupBloodType;
    RadioButton radioButtonBloodType;

    AwesomeValidation awesomeValidation;

    DatabaseReference reffUser;
    AddUserClass addUserClass;

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
//        cbDonor =  findViewById(R.id.checkBoxDonor);
        radioGroupMF = findViewById(R.id.genderRadioGroup);
        radioGroupBloodType = findViewById(R.id.add_donor_radioGroupRegister);


        reffUser = FirebaseDatabase.getInstance().getReference().child("User");


        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this,R.id.first_name_id, RegexTemplate.NOT_EMPTY,R.string.invalid_name);
        awesomeValidation.addValidation(this,R.id.last_name_id, RegexTemplate.NOT_EMPTY,R.string.invalid_surnname);
        awesomeValidation.addValidation(this, R.id.emailAddress_id, android.util.Patterns.EMAIL_ADDRESS, R.string.err_email);
        awesomeValidation.addValidation(this, R.id.phone_number_id, RegexTemplate.TELEPHONE, R.string.err_tel);
        awesomeValidation.addValidation(this,R.id.address_id, RegexTemplate.NOT_EMPTY,R.string.invalid_adress);

        awesomeValidation.addValidation(this, R.id.password_id, ".{6,}", R.string.err_password);

        

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (awesomeValidation.validate()) {

                    int radioButtonIdMF = radioGroupMF.getCheckedRadioButtonId();
                    radioButtonMF = findViewById(radioButtonIdMF);

                    int radioButtonIdBloodType = radioGroupBloodType.getCheckedRadioButtonId();
                    radioButtonBloodType = findViewById(radioButtonIdBloodType);


//              Qeto ki mi shti n'datanaz
                    String MaleFemale = radioButtonMF.getText().toString().trim(); // MaleFemale
                    String emri = etFName.getText().toString().trim(); // Emri
                    String mbiemri = etLName.getText().toString().trim(); // Mbiemri
                    String email = etEmail.getText().toString().trim(); // Email
                    String telefoni = etTelephone.getText().toString().trim(); // Phone
                    String password = etPassword.getText().toString().trim(); // Password
                    String adresa = etAddress.getText().toString().trim(); // Adresa
                    String blood_type_register = radioButtonBloodType.getText().toString().trim(); // MaleFemale

                    addUserClass = new AddUserClass();

                    addUserClass.setEmri(emri);
                    addUserClass.setMbiemri(mbiemri);
                    addUserClass.setEmail(email);
                    addUserClass.setTelefoni(telefoni);
                    addUserClass.setMaleFemale(MaleFemale);
                    addUserClass.setPassword(password);
                    addUserClass.setAdresa(adresa);
                    addUserClass.setTipiGjakut(blood_type_register);

                    reffUser.push().setValue(addUserClass);

                    Intent intent = new Intent(Registration_form.this, Login_form.class);
                    startActivity(intent);
                }

                else {
                    Toast.makeText(getApplicationContext(),"Validation Failed. ", Toast.LENGTH_LONG).show();
                }
            }
        });



    }

    //Ktu posht i kemi insertu te dhenat per Radio Groups
    public void onRadioButtonClickedGender(View v) {
        int radioId = radioGroupMF.getCheckedRadioButtonId();
        radioButtonMF = findViewById(radioId);
        Toast.makeText(this,  radioButtonMF.getText(),
                Toast.LENGTH_SHORT).show();
    }

    public void add_donor_bloobType_onclick_methodRegister(View v) {
        int radioId = radioGroupBloodType.getCheckedRadioButtonId();
        radioButtonBloodType = findViewById(radioId);
        Toast.makeText(this,  radioButtonBloodType.getText(),
                Toast.LENGTH_SHORT).show();
    }

    public void sign_in_method_register_form(View v) {
        Intent intent = new Intent(Registration_form.this, Login_form.class);
        startActivity(intent);
    }
}
