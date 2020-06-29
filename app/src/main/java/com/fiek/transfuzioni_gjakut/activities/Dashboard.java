package com.fiek.transfuzioni_gjakut.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.fiek.transfuzioni_gjakut.Home;
import com.fiek.transfuzioni_gjakut.R;

import java.sql.Array;
import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {

    RecyclerView featuredRecycler, rarestBlood;
    RecyclerView.Adapter adapter;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(Dashboard.this, Home.class);
                startActivity(intent);
            }
        });


        //Hooks

        featuredRecycler = findViewById(R.id.featured_recycler);
        rarestBlood = findViewById(R.id.rarest_recycler);

        featuredRecycler();
        rarestBloodFunc();

    }

    private void featuredRecycler(){

        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();
        featuredLocations.add(new FeaturedHelperClass(R.drawable.apozitiv, " ", "If your blood is A positive (A+), it means that your blood contains type-A antigens with the presence of a protein called the rhesus (Rh) factor."));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.anegativ, " ", "A- is only found in 6% of people, so donations are always needed."));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.bpozitiv, " ", "Since various types of B+ donations are useful, donations are important. Only 8% of the population has B+ blood."));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.bnegativ, " ", "Less than 2% of the population have B negative blood."));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.abpozitiv, " ", "UNIVERSAL PLATELET DONOR; MOST NEEDED FOR CANCER PATIENTS"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.abnegativ, " ", "ENCOURAGED TO DONATE PLATELETS; MOST NEEDED BY NEWBORNS IN CRISIS"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.zeropozitiv, " ", "As the most common blood type, most patients require 0+ blood."));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.zeronegativ, " ", "UNIVERSAL  DONOR AND CRITICALLY NEEDED -CAN BE TRANSFUSED TO ANY PATIENT IN AN EMERGENCY."));


        adapter = new FeaturedAdapter(featuredLocations);
        featuredRecycler.setAdapter(adapter);




    }


    private void rarestBloodFunc(){

        ArrayList<RarestHelperClass> rarestHelperClasses = new ArrayList<>();
        rarestHelperClasses.add(new RarestHelperClass(R.drawable.abne, " "));
        rarestHelperClasses.add(new RarestHelperClass(R.drawable.bne, "  "));
        rarestHelperClasses.add(new RarestHelperClass(R.drawable.abpo, "  "));
        rarestHelperClasses.add(new RarestHelperClass(R.drawable.ane, "  "));
        rarestHelperClasses.add(new RarestHelperClass(R.drawable.zne, "  "));
        rarestHelperClasses.add(new RarestHelperClass(R.drawable.bpo, "  "));
        rarestHelperClasses.add(new RarestHelperClass(R.drawable.apo, "  "));
        rarestHelperClasses.add(new RarestHelperClass(R.drawable.zpozitiv, "  "));


        rarestBlood.setHasFixedSize(true);
        adapter = new RarestAdapter(rarestHelperClasses);
        rarestBlood.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rarestBlood.setAdapter(adapter);

    }
}
