package com.fiek.transfuzioni_gjakut.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.fiek.transfuzioni_gjakut.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import dmax.dialog.SpotsDialog;

public class UserProfile extends AppCompatActivity {
    TextView emriProfilit, userProfileBloodType, userProfileBloodQuantity,userProfilePhone,userProfileEmail, fullname, username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        emriProfilit = findViewById(R.id.userProfileName);
        userProfileBloodType = findViewById(R.id.userProfileBloodType);
        userProfileBloodQuantity = findViewById(R.id.userProfileBloodQuantity);
        userProfilePhone = findViewById(R.id.userProfilePhone);
        userProfileEmail = findViewById(R.id.userProfileEmail);
        fullname = findViewById(R.id.fullName);
        username = findViewById(R.id.username);



    }

    @Override
    protected void onStart() {
        final android.app.AlertDialog dialog = new SpotsDialog.Builder().setContext(this).build();
        dialog.show();

        super.onStart();
        //Ktu shkruhet kodi per me u ndryshu te dhanat kur te hapet profili
        final String userEnteredEmailSession = getIntent().getStringExtra("emailSession");

//        Toast.makeText(getApplicationContext(),userEnteredEmailSession, Toast.LENGTH_LONG).show(); //onStart Called

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("ShtoDhurues");
        Query checkUser = reference.orderByChild("email").equalTo(userEnteredEmailSession);

        ///////
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()) {
                    Integer sasiaX =  0;
                    for (DataSnapshot artistSnapshot1: dataSnapshot.getChildren()) {
//                        UsersGetClass artist = artistSnapshot1.getValue(UsersGetClass.class);

                        sasiaX +=  artistSnapshot1.child("sasia").getValue(int.class);

                        String telefoniFromDB = artistSnapshot1.child("telefoni").getValue(String.class);
                        String emriFromDB = artistSnapshot1.child("emri").getValue(String.class);
                        String mbiemriFromDB = artistSnapshot1.child("mbiemri").getValue(String.class);
                        String tipiGjakutFromDB = artistSnapshot1.child("tipiGjakut").getValue(String.class);
//                        String sasiaFromDB = (artistSnapshot1.child("sasia").getValue(int.class)).toString();
                        String sasiaFromDB = sasiaX.toString();

                        Toast.makeText(getApplicationContext(),"Welcome "+userEnteredEmailSession, Toast.LENGTH_LONG).show(); //onStart Called

                        emriProfilit.setText("Full Name : " +emriFromDB + " " + mbiemriFromDB);
                        userProfileBloodType.setText("Blood Type : " + tipiGjakutFromDB);
                        userProfileBloodQuantity.setText("Quantity : " + sasiaFromDB);
                        userProfilePhone.setText("Phone : " + telefoniFromDB);
                        userProfileEmail.setText("Email : " + userEnteredEmailSession);

                        fullname.setText(emriFromDB + " " + mbiemriFromDB);
                        username.setText(userEnteredEmailSession);
                        dialog.dismiss();
                    }


                }

                else {
                    dialog.dismiss();
                    emriProfilit.setText("You've not donated blood!");
                    Toast.makeText(getApplicationContext(),"You have not Donated blod yet !", Toast.LENGTH_LONG).show(); //onStart Called
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        /////
    }
}
