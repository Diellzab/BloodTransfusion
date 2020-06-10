package com.fiek.transfuzioni_gjakut.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.fiek.transfuzioni_gjakut.R;

public class Coronavirus_Cases extends AppCompatActivity {
    public static TextView data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coronavirus__cases);

        data = (TextView) findViewById(R.id.global_sases_id);

        fetchData process = new fetchData();
        process.execute() ;
    }
}
