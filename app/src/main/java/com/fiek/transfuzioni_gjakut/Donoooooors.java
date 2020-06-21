package com.fiek.transfuzioni_gjakut;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.firebase.ui.firestore.paging.FirestoreDataSource;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Donoooooors extends AppCompatActivity {

    FirebaseFirestore mFirebaseFirestore = FirebaseFirestore.getInstance();
    CollectionReference shtoDhuruesRef = mFirebaseFirestore.collection("ShtoDhurues");
    DatabaseReference databaseArticles ;
    RecyclerView recyclerView;
    FirestoreRecyclerOptions<addDonorDataInsert> options;
    FirestoreRecyclerAdapter adapter;
    TextView textViewData;
    ListView listViewDonors;
    List<addDonorDataInsert> donorsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donoooooors);
        listViewDonors = (ListView) findViewById(R.id.listViewDonors);

        databaseArticles = FirebaseDatabase.getInstance().getReference("ShtoDhurues");
        donorsList = new ArrayList<>();

//        shtoDhuruesRef.addSnapshotListener(this, new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
//                if ( e != null) {
//                    return;
//                }
//
//                String data ="";
//
//                for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
//                    addDonorDataInsert note = documentSnapshot.toObject(addDonorDataInsert.class);
//                    String emri = note.getEmri();
//                    String mbiemri = note.getMbiemri();
//
//                    data += "Emri : " + emri +" Mbiemri : "+ mbiemri;
//                }
//                textViewData.setText(data);
//            }
//        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseArticles.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                donorsList.clear();
                for (DataSnapshot artistSnapshot1: dataSnapshot.getChildren()) {
                    addDonorDataInsert artist = artistSnapshot1.getValue(addDonorDataInsert.class);
                    donorsList.add(artist);
                }

                DonorsList adapter = new DonorsList(Donoooooors.this, donorsList);
                listViewDonors.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //    public void loadNotes(View v) {
//        shtoDhuruesRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//            @Override
//            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                String data = "";
//                textViewData = findViewById(R.id.text_view_data);
//                for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
//                    addDonorDataInsert note =   documentSnapshot.toObject(addDonorDataInsert.class);
////                note.setEmri(documentSnapshot.getId());
//
////                    String documentEmri = note.getEmri();
//                    String documentMbiemri = note.getMbiemri();
//                    String documentEmail = note.getEmail();
//                    String documentTelefoni = note.getTelefoni();
////                    int documentSasia = note.getSasia();
//                    String documentTipiGjakut = note.getTipiGjakut();
//
//                    data += "Emri : " + " " + documentMbiemri
//                            + "\nEmail : " + documentEmail + "\nTelefoni : " + documentTelefoni+"\nGr Gjakut : " + documentTipiGjakut+"\nSasia : " +  "\n\n";
//                }
//                textViewData.setText(data);
//            }
//        });
//    }
}
