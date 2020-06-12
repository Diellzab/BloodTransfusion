package com.fiek.transfuzioni_gjakut;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class donorFragment extends Fragment {

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedIsntance){
        return inflater.inflate(R.layout.donor_fragment, container, false);
    }


}
