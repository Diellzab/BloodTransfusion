package com.fiek.transfuzioni_gjakut.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.fiek.transfuzioni_gjakut.R;
import com.fiek.transfuzioni_gjakut.addDonorDataInsert;
import com.fiek.transfuzioni_gjakut.forms.AddDepozitaClass;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ShtoMarres extends AppCompatActivity {
    RadioGroup radioGroup_reciever;
    Button buttonAdd_reciever;
    RadioButton radioButton_reciever;
    TextView add_donor_title_reciever;
    EditText add_donor_name_reciever, add_donor_surname_reciever,add_donor_email_reciever,add_donor_phone_reciever,add_donor_quantity_reciever ;
    DatabaseReference reff_reciever;
    DatabaseReference reffDepozita_reciever;

    AddDepozitaClass shtoNeDepozit_minus;
    ShtoMarresClass shtoMarres;

    AwesomeValidation awesomeValidation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shto_marres);

        radioGroup_reciever = findViewById(R.id.add_donor_radioGroup_reciever);
        Button buttonAdd = findViewById(R.id.add_donor_add_btn_reciever);


        reff_reciever = FirebaseDatabase.getInstance().getReference().child("ShtoMarres");
        reffDepozita_reciever = FirebaseDatabase.getInstance().getReference().child("DepozitaMinus");

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this,R.id.add_donor_name_reciever, RegexTemplate.NOT_EMPTY,R.string.invalid_name);
        awesomeValidation.addValidation(this,R.id.add_donor_surname_reciever, RegexTemplate.NOT_EMPTY,R.string.invalid_surnname);
        awesomeValidation.addValidation(this, R.id.add_donor_email_reciever, android.util.Patterns.EMAIL_ADDRESS, R.string.err_email);
        awesomeValidation.addValidation(this, R.id.add_donor_phone_reciever, RegexTemplate.TELEPHONE, R.string.err_tel);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int radioId = radioGroup_reciever.getCheckedRadioButtonId();
                radioButton_reciever = findViewById(radioId);

                TextView add_donor_title_reciever = (TextView) findViewById(R.id.add_donor_title_reciever);
                EditText add_donor_name_reciever = (EditText) findViewById(R.id.add_donor_name_reciever);
                EditText add_donor_surname_reciever = (EditText) findViewById(R.id.add_donor_surname_reciever);
                EditText add_donor_email_reciever = (EditText) findViewById(R.id.add_donor_email_reciever);
                EditText add_donor_phone_reciever = (EditText) findViewById(R.id.add_donor_phone_reciever);
                EditText add_donor_quantity_reciever = (EditText) findViewById(R.id.add_donor_quantity_reciever);


                if (awesomeValidation.validate()) {
                    //Qeto posht duhet mi rujt ne databaz
                    String blodTypeChecked = radioButton_reciever.getText().toString().trim(); // GRUPI GJAKUT
                    String email = add_donor_email_reciever.getText().toString().trim(); //IMELLA
                    String name = add_donor_name_reciever.getText().toString().trim(); //EMRI
                    String surname = add_donor_surname_reciever.getText().toString().trim(); //MBIEMRI
                    String telefoni = add_donor_phone_reciever.getText().toString().trim(); //TELEFONI
                    int sasia = Integer.parseInt(add_donor_quantity_reciever.getText().toString().trim()); //SASIA

                    add_donor_title_reciever.setText(radioButton_reciever.getText().toString().trim());
                    shtoMarres = new ShtoMarresClass();
                    shtoNeDepozit_minus = new AddDepozitaClass();

                    shtoMarres.setEmri(name);
                    shtoMarres.setTipiGjakut(blodTypeChecked);
                    shtoMarres.setEmail(email);
                    shtoMarres.setMbiemri(surname);
                    shtoMarres.setTelefoni(telefoni);
                    shtoMarres.setSasia(sasia);

                    shtoNeDepozit_minus.setTipiGjakut(blodTypeChecked);
                    shtoNeDepozit_minus.setSasiaGjakut(sasia);

                    reff_reciever.push().setValue(shtoMarres);
                    reffDepozita_reciever.push().setValue(shtoNeDepozit_minus);

//                Toast.makeText(this, "Selected Blood Type: " + name,
//                        Toast.LENGTH_SHORT).show();
                }

                else {
                    Toast.makeText(getApplicationContext(),"Validation Failed. ", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    //Kodi posht eshte metoda qe e qet ni TOAST kur tklikohet ni checkbox
    public void add_donor_bloobType_onclick_method_reciever(View v) {
        int radioId = radioGroup_reciever.getCheckedRadioButtonId();
        radioButton_reciever = findViewById(radioId);
        Toast.makeText(this, "Selected Blood Type: " + radioButton_reciever.getText(),
                Toast.LENGTH_SHORT).show();
    }
}
