package com.fiek.transfuzioni_gjakut;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
import com.fiek.transfuzioni_gjakut.forms.AddDepozitaClass;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.firebase.client.Firebase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class ShtoDhurues extends AppCompatActivity {
    RadioGroup radioGroup;
    Button buttonAdd;
    RadioButton radioButton;
    TextView add_donor_title;
    EditText add_donor_name, add_donor_surname,add_donor_email,add_donor_phone,add_donor_quantity ;
    DatabaseReference reff;
    DatabaseReference reffDepozita;
    Toolbar toolbar;


    AddDepozitaClass shtoNeDepozit;
    addDonorDataInsert shtoDhurues;

    AwesomeValidation awesomeValidation;

//QETA e ke shtu per ME BA UPDATE
    Firebase mRef;
    Firebase mRefSasia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shto_dhurues);

        Firebase.setAndroidContext(this);

        radioGroup = findViewById(R.id.add_donor_radioGroup);
        Button buttonAdd = findViewById(R.id.add_donor_add_btn);


        toolbar = findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add a Donor");


        reff = FirebaseDatabase.getInstance().getReference().child("ShtoDhurues");
        reffDepozita = FirebaseDatabase.getInstance().getReference().child("DepozitaPlus");

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this,R.id.add_donor_name, RegexTemplate.NOT_EMPTY,R.string.invalid_name);
        awesomeValidation.addValidation(this,R.id.add_donor_surname, RegexTemplate.NOT_EMPTY,R.string.invalid_surnname);
        awesomeValidation.addValidation(this, R.id.add_donor_email, android.util.Patterns.EMAIL_ADDRESS, R.string.err_email);
        awesomeValidation.addValidation(this, R.id.add_donor_phone, RegexTemplate.TELEPHONE, R.string.err_tel);


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);

                final TextView add_donor_title = (TextView) findViewById(R.id.add_donor_title);
                EditText add_donor_name = (EditText) findViewById(R.id.add_donor_name);
                EditText add_donor_surname = (EditText) findViewById(R.id.add_donor_surname);
                EditText add_donor_email = (EditText) findViewById(R.id.add_donor_email);
                EditText add_donor_phone = (EditText) findViewById(R.id.add_donor_phone);
                final EditText add_donor_quantity = (EditText) findViewById(R.id.add_donor_quantity);


                if (awesomeValidation.validate()) {
                    //Qeto posht duhet mi rujt ne databaz
                    String bloooood = radioButton.getText().toString();
                    String blodTypeChecked = radioButton.getText().toString().trim(); // GRUPI GJAKUT
                    String email = add_donor_email.getText().toString().trim(); //IMELLA
                    String name = add_donor_name.getText().toString().trim(); //EMRI
                    String surname = add_donor_surname.getText().toString().trim(); //MBIEMRI
                    String telefoni = add_donor_phone.getText().toString().trim(); //TELEFONI
                    final int sasia = Integer.parseInt(add_donor_quantity.getText().toString().trim()); //SASIA

//                    add_donor_title.setText(radioButton.getText().toString().trim());
                    shtoDhurues = new addDonorDataInsert();
                    shtoNeDepozit = new AddDepozitaClass();

                    shtoDhurues.setEmri(name);
                    shtoDhurues.setTipiGjakut(blodTypeChecked);
                    shtoDhurues.setEmail(email);
                    shtoDhurues.setMbiemri(surname);
                    shtoDhurues.setTelefoni(telefoni);
                    shtoDhurues.setSasia(sasia);

                    shtoNeDepozit.setTipiGjakut(blodTypeChecked);
                    shtoNeDepozit.setSasiaGjakut(sasia);

                    reff.push().setValue(shtoDhurues);
//                    reffDepozita.push().setValue(shtoNeDepozit);


////////////////////////////////////////////////////////////////////////////
//Krej qeto posht ki mi ba nese asht per A-
                    if(bloooood.equals("A-")) {
                    mRef = new Firebase("https://transufzionigjakut.firebaseio.com/DepozitaPlus/-MAMPvCdCjnbb2k70RrV");
                    mRefSasia = new Firebase("https://transufzionigjakut.firebaseio.com/DepozitaPlus/-MAMPvCdCjnbb2k70RrV/sasiaGjakut");

                    mRefSasia.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            int value = Integer.parseInt(dataSnapshot.getValue(String.class));
                            int insertedDatas = Integer.parseInt(add_donor_quantity.getText().toString()); //qeta pe merr dyfish

                            int sasiaTotale = value +  insertedDatas;
                            Map<String, Object> map = new HashMap<>();
                            map.put("sasiaGjakut", sasiaTotale );
                            mRef.updateChildren(map);


                            mRefSasia.removeEventListener(this);
                            mRef.removeEventListener(this);

                            Toast.makeText(getApplicationContext(),"Added Successfully ", Toast.LENGTH_LONG).show();
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
                                int insertedDatas = Integer.parseInt(add_donor_quantity.getText().toString()); //qeta pe merr dyfish

                                int sasiaTotale = value +  insertedDatas;
                                Map<String, Object> map = new HashMap<>();
                                map.put("sasiaGjakut", sasiaTotale );
                                mRef.updateChildren(map);


                                mRefSasia.removeEventListener(this);
                                mRef.removeEventListener(this);
                                Toast.makeText(getApplicationContext(),"Added Successfully ", Toast.LENGTH_LONG).show();
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
                                int insertedDatas = Integer.parseInt(add_donor_quantity.getText().toString()); //qeta pe merr dyfish

                                int sasiaTotale = value +  insertedDatas;
                                Map<String, Object> map = new HashMap<>();
                                map.put("sasiaGjakut", sasiaTotale );
                                mRef.updateChildren(map);


                                mRefSasia.removeEventListener(this);
                                mRef.removeEventListener(this);
                                Toast.makeText(getApplicationContext(),"Added Successfully ", Toast.LENGTH_LONG).show();
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
                                int insertedDatas = Integer.parseInt(add_donor_quantity.getText().toString()); //qeta pe merr dyfish

                                int sasiaTotale = value +  insertedDatas;
                                Map<String, Object> map = new HashMap<>();
                                map.put("sasiaGjakut", sasiaTotale );
                                mRef.updateChildren(map);


                                mRefSasia.removeEventListener(this);
                                mRef.removeEventListener(this);
                                Toast.makeText(getApplicationContext(),"Added Successfully ", Toast.LENGTH_LONG).show();
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
                                int insertedDatas = Integer.parseInt(add_donor_quantity.getText().toString()); //qeta pe merr dyfish

                                int sasiaTotale = value +  insertedDatas;
                                Map<String, Object> map = new HashMap<>();
                                map.put("sasiaGjakut", sasiaTotale );
                                mRef.updateChildren(map);


                                mRefSasia.removeEventListener(this);
                                mRef.removeEventListener(this);
                                Toast.makeText(getApplicationContext(),"Added Successfully ", Toast.LENGTH_LONG).show();
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
                                int insertedDatas = Integer.parseInt(add_donor_quantity.getText().toString()); //qeta pe merr dyfish

                                int sasiaTotale = value +  insertedDatas;
                                Map<String, Object> map = new HashMap<>();
                                map.put("sasiaGjakut", sasiaTotale );
                                mRef.updateChildren(map);


                                mRefSasia.removeEventListener(this);
                                mRef.removeEventListener(this);
                                Toast.makeText(getApplicationContext(),"Added Successfully ", Toast.LENGTH_LONG).show();
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
                                int insertedDatas = Integer.parseInt(add_donor_quantity.getText().toString()); //qeta pe merr dyfish

                                int sasiaTotale = value +  insertedDatas;
                                Map<String, Object> map = new HashMap<>();
                                map.put("sasiaGjakut", sasiaTotale );
                                mRef.updateChildren(map);


                                mRefSasia.removeEventListener(this);
                                mRef.removeEventListener(this);
                                Toast.makeText(getApplicationContext(),"Added Successfully ", Toast.LENGTH_LONG).show();
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
                                int insertedDatas = Integer.parseInt(add_donor_quantity.getText().toString()); //qeta pe merr dyfish

                                int sasiaTotale = value +  insertedDatas;
                                Map<String, Object> map = new HashMap<>();
                                map.put("sasiaGjakut", sasiaTotale );
                                mRef.updateChildren(map);


                                mRefSasia.removeEventListener(this);
                                mRef.removeEventListener(this);
                                Toast.makeText(getApplicationContext(),"Added Successfully ", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {
                                mRefSasia.removeEventListener(this);
                            }
                        });
                    }

////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

//                    QETO I KE SHTU PER UPDATE
//                    mRef = new Firebase("https://transufzionigjakut.firebaseio.com/DepozitaPlus/-MA0ridS6CVbzGRxWyoX/sasiaGjakut");
//
//                    mRef.addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(DataSnapshot dataSnapshot) {
//                            String value = dataSnapshot.getValue(String.class);
//                            add_donor_title.setText(value);
//                        }
//
//                        @Override
//                        public void onCancelled(FirebaseError firebaseError) {
//
//                        }
//                    });
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
        public void add_donor_bloobType_onclick_method(View v) {
            int radioId = radioGroup.getCheckedRadioButtonId();
            radioButton = findViewById(radioId);
            Toast.makeText(this, "Selected Blood Type: " + radioButton.getText(),
                    Toast.LENGTH_SHORT).show();
        }
}
