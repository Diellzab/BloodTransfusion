package com.fiek.transfuzioni_gjakut;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class registerFragment extends Fragment {

    View view;
    TextView tvSignIn;
    EditText etFName, etLName, etEmail, etPassword, etTelephone, etAddress;
    RadioButton rbFemale, rbMale;
    Button btnRegister;
    Spinner spinner;
    CheckBox cbDonor;

    @Nullable
    @Override


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedIsntance){
        return inflater.inflate(R.layout.activity_registration_form, container, false);

//        btnRegister = (Button) view.findViewById(R.id.buttonSave);

          }
}
