package com.fiek.transfuzioni_gjakut.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.fiek.transfuzioni_gjakut.R;

public class Admin_registration extends AppCompatActivity {

    EditText etName, etSurname, etAddress, etEmailAddress, etUsername, etPassword;
    Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_registration);

        etName = findViewById(R.id.etName);
        etSurname = findViewById(R.id.etSurname);
        etAddress = findViewById(R.id.etAddress);
        etEmailAddress = findViewById(R.id.etMail);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv = new ContentValues();
                cv.put(User.Emri,etName.getText().toString());
                cv.put(User.Mbiemri,etSurname.getText().toString());
                cv.put(User.Adresa, etAddress.getText().toString());
                cv.put(User.Email, etEmailAddress.getText().toString());
                cv.put(User.Username,etUsername.getText().toString());
                cv.put(User.Password,etPassword.getText().toString());

                SQLiteDatabase objDb = new DatabaseHelper(Admin_registration.this).getWritableDatabase();

                try
                {
                    long retValue = objDb.insert(DatabaseHelper.PerdoruesitTable,null,cv);
                    if(retValue>0)
                    {
                        Toast.makeText(Admin_registration.this,"Admin added: "+retValue,
                                Toast.LENGTH_LONG).show();
                    }

                }
                catch (Exception ex)
                {
                    Log.e("excep",ex.getMessage());
                }
                finally {
                    objDb.close();
                }
            }
        });




    }
}
