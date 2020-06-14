package com.fiek.transfuzioni_gjakut;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class ShtoDhurues extends AppCompatActivity {
    RadioGroup radioGroup;
    Button buttonAdd;
    RadioButton radioButton;
    TextView add_donor_title;
    EditText add_donor_name, add_donor_surname,add_donor_email,add_donor_phone,add_donor_quantity ;
    DatabaseReference reff;

    addDonorDataInsert shtoDhurues;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shto_dhurues);

        radioGroup = findViewById(R.id.add_donor_radioGroup);
        Button buttonAdd = findViewById(R.id.add_donor_add_btn);


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                TextView add_donor_title = (TextView) findViewById(R.id.add_donor_title);
                EditText add_donor_name = (EditText) findViewById(R.id.add_donor_name);
                EditText add_donor_surname = (EditText) findViewById(R.id.add_donor_surname);
                EditText add_donor_email = (EditText) findViewById(R.id.add_donor_email);
                EditText add_donor_phone = (EditText) findViewById(R.id.add_donor_phone);
                EditText add_donor_quantity = (EditText) findViewById(R.id.add_donor_quantity);

                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);

                //Qeto posht duhet mi rujt ne databaz
                String blodTypeChecked = radioButton.getText().toString().trim(); // GRUPI GJAKUT
                String email =  add_donor_email.getText().toString().trim(); //IMELLA
                String name = add_donor_name.getText().toString().trim(); //EMRI
                String surname = add_donor_surname.getText().toString().trim(); //MBIEMRI
                String telefoni = add_donor_phone.getText().toString().trim(); //TELEFONI
                Integer sasia = Integer.parseInt(add_donor_quantity.getText().toString().trim()); //SASIA

                add_donor_title.setText(name);

//                shtoDhurues.setEmri(name);
                shtoDhurues.setTipiGjakut(blodTypeChecked);
                shtoDhurues.setEmail(email);
                shtoDhurues.setMbiemri(surname);
                shtoDhurues.setTelefoni(telefoni);
                shtoDhurues.setSasia(sasia);

                reff = FirebaseDatabase.getInstance().getReference().child("shtoDhurues");
                shtoDhurues = new addDonorDataInsert();
                reff.push().setValue(shtoDhurues);

//                Toast.makeText(this, "Selected Blood Type: " + name,
//                        Toast.LENGTH_SHORT).show();
            }
        });


    }

        //Kodi posht eshte metoda qe e qet ni TOAST kur tklikohet ni checkbox
        public void add_donor_bloobType_onclick_method(View v) {
            int radioId = radioGroup.getCheckedRadioButtonId();
            radioButton = findViewById(radioId);
            Toast.makeText(this, "Selected Blood Type: " + radioButton.getText(),
                    Toast.LENGTH_SHORT).show();
        }
}
