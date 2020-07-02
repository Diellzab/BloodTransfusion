package com.fiek.transfuzioni_gjakut.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.fiek.transfuzioni_gjakut.R;
import com.fiek.transfuzioni_gjakut.adapters.DepozitaList;
import com.fiek.transfuzioni_gjakut.models.DepozitaEgjakutClass;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;

public class DepozitaEgjakut extends AppCompatActivity {
    FirebaseFirestore mFirebaseFirestore = FirebaseFirestore.getInstance();
    CollectionReference shtoDhuruesRef = mFirebaseFirestore.collection("ShtoDhurues");
    DatabaseReference databaseArticles ;
    RecyclerView recyclerView;
    FirestoreRecyclerOptions<DepozitaEgjakutClass> options;
    FirestoreRecyclerAdapter adapter;
    TextView textViewData;
    ListView listViewDonors;
    List<DepozitaEgjakutClass> depozitaList;
    Toolbar toolbar;
    ImageView backleft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depozita_egjakut);

        listViewDonors = (ListView) findViewById(R.id.listViewDepozita);

        databaseArticles = FirebaseDatabase.getInstance().getReference("DepozitaPlus");
        depozitaList = new ArrayList<>();

        backleft = findViewById(R.id.backleft);
        backleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DepozitaEgjakut.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        final android.app.AlertDialog dialog = new SpotsDialog.Builder().setContext(this).build();
        dialog.show();
        databaseArticles.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                depozitaList.clear();
                for (DataSnapshot artistSnapshot1: dataSnapshot.getChildren()) {
                    DepozitaEgjakutClass artist = artistSnapshot1.getValue(DepozitaEgjakutClass.class);
                    depozitaList.add(artist);
                }

                DepozitaList adapter = new DepozitaList(DepozitaEgjakut.this, depozitaList);
                listViewDonors.setAdapter(adapter);
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
