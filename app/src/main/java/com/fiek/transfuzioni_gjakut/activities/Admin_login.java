package com.fiek.transfuzioni_gjakut.activities;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fiek.transfuzioni_gjakut.R;
import com.fiek.transfuzioni_gjakut.database.DatabaseHelper;
import com.fiek.transfuzioni_gjakut.models.User;

public class Admin_login extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnLogin, btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin1);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckUsername(etUsername.getText().toString(),etPassword.getText().toString());
                /*if(etUsername.getText().toString().equals("admin") &&
                etPassword.getText().toString().equals("admin"))
                {
                    Toast.makeText(Main2Activity.this,getString(R.string.loguar_sukses),
                            Toast.LENGTH_LONG).show();


                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(Main2Activity.this);

                    LayoutInflater layoutInflater = getLayoutInflater();
                    alertDialog.setView(layoutInflater.inflate(R.layout.custom_dialog_layout,null));
                    alertDialog.setPositiveButton(R.string.positive_button, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent main3Activity = new Intent(Main2Activity.this,Main3Activity.class);
                            main3Activity.putExtra("username",etUsername.getText().toString());
                            startActivity(main3Activity);
                        }
                    });
                    alertDialog.setNegativeButton(R.string.negative_button, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alertDialog.show();


                }
                else
                {
                    etPassword.setError(getString(R.string.kredencialet_gabim));
                    etPassword.requestFocus();
                }*/
            }
        });


    }

    public void CheckUsername(String username, String password)
    {
        SQLiteDatabase objDb = new DatabaseHelper(Admin_login.this).getReadableDatabase();

        Cursor c = objDb.query(DatabaseHelper.PerdoruesitTable,
                new String[]{User.ID,User.Emri, User.Mbiemri, User.Username,User.Password},
                User.Username+"=?",new String[]{username},"","","");
        /*Cursor c = objDb.rawQuery("select * from "+ Databaza.PerdoruesitTable+" where "+
                Perdoruesi.Username+" =?",new String[]{username});*/
        if(c.getCount()==1)
        {
            c.moveToFirst();
            String dbUsername = c.getString(3);
            String dbPassword = c.getString(4);

            if(username.equals(dbUsername) &&
                    password.equals(dbPassword))
            {
                Toast.makeText(Admin_login.this,getString(R.string.loguar_sukses),
                        Toast.LENGTH_SHORT).show();


                AlertDialog.Builder alertDialog = new AlertDialog.Builder(Admin_login.this);

                LayoutInflater layoutInflater = getLayoutInflater();
                alertDialog.setView(layoutInflater.inflate(R.layout.custom_dialog_layout,null));
                alertDialog.setPositiveButton(R.string.positive_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent main3Activity = new Intent(Admin_login.this, MainActivity.class);
                        main3Activity.putExtra("username",etUsername.getText().toString());
                        startActivity(main3Activity);
                    }
                });
                alertDialog.setNegativeButton(R.string.negative_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();


            }
            else
            {
                etPassword.setError(getString(R.string.kredencialet_gabuara));
                etPassword.requestFocus();
            }
        }
        else
        {
            etPassword.setError(getString(R.string.kredencialet_gabuara));
            etPassword.requestFocus();
        }
    }
}
