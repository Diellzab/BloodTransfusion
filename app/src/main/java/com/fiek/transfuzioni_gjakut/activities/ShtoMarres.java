package com.fiek.transfuzioni_gjakut.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.fiek.transfuzioni_gjakut.R;
import com.fiek.transfuzioni_gjakut.models.AddDepozitaClass;
import com.fiek.transfuzioni_gjakut.models.ShtoMarresClass;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class ShtoMarres extends AppCompatActivity {
    RadioGroup radioGroup_reciever;
    Button buttonAdd_reciever;
    RadioButton radioButton_reciever;
    TextView add_donor_title_reciever;
    EditText add_donor_name_reciever, add_donor_surname_reciever,add_donor_email_reciever,add_donor_phone_reciever,add_donor_quantity_reciever ;
    DatabaseReference reff_reciever;
    DatabaseReference reffDepozita_reciever;

    Toolbar toolbar;

    ImageView backleft;

    AddDepozitaClass shtoNeDepozit_minus;
    ShtoMarresClass shtoMarres;
    //QETA e ke shtu per ME BA UPDATE
    Firebase mRef;
    Firebase mRefSasia;
    AwesomeValidation awesomeValidation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shto_marres);

        Firebase.setAndroidContext(this);

        radioGroup_reciever = findViewById(R.id.add_donor_radioGroup_reciever);
        Button buttonAdd = findViewById(R.id.add_donor_add_btn_reciever);


       backleft = findViewById(R.id.backleft);
       backleft.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(ShtoMarres.this, MainActivity.class);
               startActivity(intent);
           }
       });




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
                final EditText add_donor_quantity_reciever = (EditText) findViewById(R.id.add_donor_quantity_reciever);


                if (awesomeValidation.validate()) {
                    String bloooood = radioButton_reciever.getText().toString();
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
//                    reffDepozita_reciever.push().setValue(shtoNeDepozit_minus);

                    ///////////////////////////////////////////////////////////////////////////
//Krej qeto posht ki mi ba nese asht per A-
                    if(bloooood.equals("A-")) {
                        mRef = new Firebase("https://transufzionigjakut.firebaseio.com/DepozitaPlus/-MAMPvCdCjnbb2k70RrV");
                        mRefSasia = new Firebase("https://transufzionigjakut.firebaseio.com/DepozitaPlus/-MAMPvCdCjnbb2k70RrV/sasiaGjakut");

                        mRefSasia.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                int value = Integer.parseInt(dataSnapshot.getValue(String.class));
                                int insertedDatas = Integer.parseInt(add_donor_quantity_reciever.getText().toString()); //qeta pe merr dyfish

                                int sasiaTotale = value -  insertedDatas;
                                Map<String, Object> map = new HashMap<>();
                                map.put("sasiaGjakut", sasiaTotale );
                                mRef.updateChildren(map);


                                mRefSasia.removeEventListener(this);
                                mRef.removeEventListener(this);
                                Toast.makeText(getApplicationContext(),"Added Successfully ", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {
                                mRefSasia.removeEventListener(this);
                            }
                        });
                    }


                    if(bloooood.equals("B+")) {
                        mRef = new Firebase("https://transufzionigjakut.firebaseio.com/DepozitaPlus/-MA0ridS6CVbzGRxWyoX");
                        mRefSasia = new Firebase("https://transufzionigjakut.firebaseio.com/DepozitaPlus/-MA0ridS6CVbzGRxWyoX/sasiaGjakut");

                        mRefSasia.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                int value = Integer.parseInt(dataSnapshot.getValue(String.class));
                                int insertedDatas = Integer.parseInt(add_donor_quantity_reciever.getText().toString()); //qeta pe merr dyfish

                                int sasiaTotale = value -  insertedDatas;
                                Map<String, Object> map = new HashMap<>();
                                map.put("sasiaGjakut", sasiaTotale );
                                mRef.updateChildren(map);


                                mRefSasia.removeEventListener(this);
                                mRef.removeEventListener(this);
                                Toast.makeText(getApplicationContext(),"Added Successfully ", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {
                                mRefSasia.removeEventListener(this);
                            }
                        });
                    }

                    if(bloooood.equals("B-")) {
                        mRef = new Firebase("https://transufzionigjakut.firebaseio.com/DepozitaPlus/-MAMs7S6lvc6jLiPjopw");
                        mRefSasia = new Firebase("https://transufzionigjakut.firebaseio.com/DepozitaPlus/-MAMs7S6lvc6jLiPjopw/sasiaGjakut");

                        mRefSasia.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                int value = Integer.parseInt(dataSnapshot.getValue(String.class));
                                int insertedDatas = Integer.parseInt(add_donor_quantity_reciever.getText().toString()); //qeta pe merr dyfish

                                int sasiaTotale = value -  insertedDatas;
                                Map<String, Object> map = new HashMap<>();
                                map.put("sasiaGjakut", sasiaTotale );
                                mRef.updateChildren(map);


                                mRefSasia.removeEventListener(this);
                                mRef.removeEventListener(this);
                                Toast.makeText(getApplicationContext(),"Added Successfully ", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {
                                mRefSasia.removeEventListener(this);
                            }
                        });
                    }

                    if(bloooood.equals("A+")) {
                        mRef = new Firebase("https://transufzionigjakut.firebaseio.com/DepozitaPlus/-MAMsQ_kRiGH00nYLk38");
                        mRefSasia = new Firebase("https://transufzionigjakut.firebaseio.com/DepozitaPlus/-MAMsQ_kRiGH00nYLk38/sasiaGjakut");

                        mRefSasia.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                int value = Integer.parseInt(dataSnapshot.getValue(String.class));
                                int insertedDatas = Integer.parseInt(add_donor_quantity_reciever.getText().toString()); //qeta pe merr dyfish

                                int sasiaTotale = value -  insertedDatas;
                                Map<String, Object> map = new HashMap<>();
                                map.put("sasiaGjakut", sasiaTotale );
                                mRef.updateChildren(map);


                                mRefSasia.removeEventListener(this);
                                mRef.removeEventListener(this);
                                Toast.makeText(getApplicationContext(),"Added Successfully ", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {
                                mRefSasia.removeEventListener(this);
                            }
                        });
                    }

                    if(bloooood.equals("AB+")) {
                        mRef = new Firebase("https://transufzionigjakut.firebaseio.com/DepozitaPlus/-MAMszfLOojG4978vyNk");
                        mRefSasia = new Firebase("https://transufzionigjakut.firebaseio.com/DepozitaPlus/-MAMszfLOojG4978vyNk/sasiaGjakut");

                        mRefSasia.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                int value = Integer.parseInt(dataSnapshot.getValue(String.class));
                                int insertedDatas = Integer.parseInt(add_donor_quantity_reciever.getText().toString()); //qeta pe merr dyfish

                                int sasiaTotale = value - insertedDatas;
                                Map<String, Object> map = new HashMap<>();
                                map.put("sasiaGjakut", sasiaTotale );
                                mRef.updateChildren(map);


                                mRefSasia.removeEventListener(this);
                                mRef.removeEventListener(this);
                                Toast.makeText(getApplicationContext(),"Added Successfully ", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {
                                mRefSasia.removeEventListener(this);
                            }
                        });
                    }

                    if(bloooood.equals("AB-")) {
                        mRef = new Firebase("https://transufzionigjakut.firebaseio.com/DepozitaPlus/-MAN0wLbz3rvdPHUw8j-");
                        mRefSasia = new Firebase("https://transufzionigjakut.firebaseio.com/DepozitaPlus/-MAN0wLbz3rvdPHUw8j-/sasiaGjakut");

                        mRefSasia.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                int value = Integer.parseInt(dataSnapshot.getValue(String.class));
                                int insertedDatas = Integer.parseInt(add_donor_quantity_reciever.getText().toString()); //qeta pe merr dyfish

                                int sasiaTotale = value -  insertedDatas;
                                Map<String, Object> map = new HashMap<>();
                                map.put("sasiaGjakut", sasiaTotale );
                                mRef.updateChildren(map);


                                mRefSasia.removeEventListener(this);
                                mRef.removeEventListener(this);
                                Toast.makeText(getApplicationContext(),"Added Successfully ", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {
                                mRefSasia.removeEventListener(this);
                            }
                        });
                    }

                    if(bloooood.equals("O+")) {
                        mRef = new Firebase("https://transufzionigjakut.firebaseio.com/DepozitaPlus/-MAN0wjIjQg0Z-yOvMr7");
                        mRefSasia = new Firebase("https://transufzionigjakut.firebaseio.com/DepozitaPlus/-MAN0wjIjQg0Z-yOvMr7/sasiaGjakut");

                        mRefSasia.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                int value = Integer.parseInt(dataSnapshot.getValue(String.class));
                                int insertedDatas = Integer.parseInt(add_donor_quantity_reciever.getText().toString()); //qeta pe merr dyfish

                                int sasiaTotale = value -  insertedDatas;
                                Map<String, Object> map = new HashMap<>();
                                map.put("sasiaGjakut", sasiaTotale );
                                mRef.updateChildren(map);


                                mRefSasia.removeEventListener(this);
                                mRef.removeEventListener(this);
                                Toast.makeText(getApplicationContext(),"Added Successfully ", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {
                                mRefSasia.removeEventListener(this);
                            }
                        });
                    }

                    if(bloooood.equals("O-")) {
                        mRef = new Firebase("https://transufzionigjakut.firebaseio.com/DepozitaPlus/-MARJBC91yvKCOKpdoHq");
                        mRefSasia = new Firebase("https://transufzionigjakut.firebaseio.com/DepozitaPlus/-MARJBC91yvKCOKpdoHq/sasiaGjakut");

                        mRefSasia.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                int value = Integer.parseInt(dataSnapshot.getValue(String.class));
                                int insertedDatas = Integer.parseInt(add_donor_quantity_reciever.getText().toString()); //qeta pe merr dyfish

                                int sasiaTotale = value -insertedDatas;
                                Map<String, Object> map = new HashMap<>();
                                map.put("sasiaGjakut", sasiaTotale );
                                mRef.updateChildren(map);


                                mRefSasia.removeEventListener(this);
                                mRef.removeEventListener(this);
                                Toast.makeText(getApplicationContext(),"Added Successfully ", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {
                                mRefSasia.removeEventListener(this);
                            }
                        });
                    }

////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

                }

                else {
                    Toast.makeText(getApplicationContext(),"Validation Failed. ", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void onBackPressed(){
        Intent backMainTest = new Intent(ShtoMarres.this, MainActivity.class);
        startActivity(backMainTest);
        finish();
    }


    //Kodi posht eshte metoda qe e qet ni TOAST kur tklikohet ni checkbox
    public void add_donor_bloobType_onclick_method_reciever(View v) {
        int radioId = radioGroup_reciever.getCheckedRadioButtonId();
        radioButton_reciever = findViewById(radioId);
        Toast.makeText(this, "Selected Blood Type: " + radioButton_reciever.getText(),
                Toast.LENGTH_SHORT).show();
    }
}
