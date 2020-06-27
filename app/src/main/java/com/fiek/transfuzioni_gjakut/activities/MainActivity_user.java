
package com.fiek.transfuzioni_gjakut.activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.fiek.transfuzioni_gjakut.MainActivity;
import com.fiek.transfuzioni_gjakut.R;
import com.fiek.transfuzioni_gjakut.RecipientFragment;
import com.fiek.transfuzioni_gjakut.depoistFragment;
import com.fiek.transfuzioni_gjakut.donorFragment;
import com.fiek.transfuzioni_gjakut.registerFragment;
import com.google.android.material.navigation.NavigationView;


public class MainActivity_user extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);

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
        switch (menuItem.getItemId()) {
            case R.id.nav_Home:
                startActivity(new Intent(MainActivity_user.this, Dashboard.class));
            case R.id.nav_Profile:
                startActivity(new Intent(MainActivity_user.this, UserProfile.class));

            case R.id.nav_info:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new InfoFragment()).commit();
                break;

            case R.id.nav_logOut:
                startActivity(new Intent(MainActivity_user.this, Login_form.class));
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;



    }
}

