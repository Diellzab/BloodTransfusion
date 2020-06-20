package com.fiek.transfuzioni_gjakut.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.WindowManager;

import com.fiek.transfuzioni_gjakut.R;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {

    RecyclerView featuredRecycler;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);


        //Hooks

        featuredRecycler = findViewById(R.id.featured_recycler);
        featuredRecycler();

    }

    private void featuredRecycler(){

        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();
        featuredLocations.add(new FeaturedHelperClass(R.drawable.apozitiv, "bloood", "kdweifwfwobfif"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.anegativ, "bloood", "kdweifwfwobfif"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.abpozitiv, "bloood", "kdweifwfwobfif"));

        adapter = new FeaturedAdapter(featuredLocations);
        featuredRecycler.setAdapter(adapter);

        GradientDrawable gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffeff400, 0xffaff600});


    }
}
