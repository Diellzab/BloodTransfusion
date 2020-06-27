
package com.fiek.transfuzioni_gjakut;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.fiek.transfuzioni_gjakut.R;
import com.fiek.transfuzioni_gjakut.RecipientFragment;
import com.fiek.transfuzioni_gjakut.activities.ActivityListaMarresve;
import com.fiek.transfuzioni_gjakut.activities.Admin_login;
import com.fiek.transfuzioni_gjakut.activities.Admin_registration;
import com.fiek.transfuzioni_gjakut.activities.Dashboard;
import com.fiek.transfuzioni_gjakut.activities.DepozitaEgjakut;
import com.fiek.transfuzioni_gjakut.activities.DepozitaList;
import com.fiek.transfuzioni_gjakut.activities.InfoFragment;
import com.fiek.transfuzioni_gjakut.activities.Login_form;
import com.fiek.transfuzioni_gjakut.activities.Registration_form;
import com.fiek.transfuzioni_gjakut.activities.ShtoMarres;
import com.fiek.transfuzioni_gjakut.activities.ShtoMarresList;
import com.fiek.transfuzioni_gjakut.depoistFragment;
import com.fiek.transfuzioni_gjakut.donorFragment;
import com.fiek.transfuzioni_gjakut.registerFragment;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_closer);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        if (savedInstanceState==null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new InfoFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_info);
        }

    }

    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_Home:
                startActivity(new Intent(MainActivity.this, Dashboard.class));
                break;
            case R.id.nav_Register_donor:
                startActivity(new Intent(MainActivity.this, ShtoDhurues.class));
                break;
            case R.id.nav_donor:
                startActivity(new Intent(MainActivity.this, Donoooooors.class));
            case R.id.nav_recipient:
               startActivity(new Intent(MainActivity.this, ActivityListaMarresve.class));
               break;
            case R.id.nav_register_recipient:
                startActivity(new Intent(MainActivity.this, ShtoMarres.class));
            case R.id.nav_depoist:
                startActivity(new Intent(MainActivity.this, DepozitaEgjakut.class));
                break;
            case R.id.nav_Register_admin:
                startActivity(new Intent(MainActivity.this, Admin_registration.class));
                break;
            case R.id.nav_logOut:
               Intent shto = new Intent(MainActivity.this, Admin_login.class);
               startActivity(shto);
            case R.id.nav_info:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new InfoFragment()).commit();
                break;
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;



    }
}

