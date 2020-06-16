package com.fiek.transfuzioni_gjakut;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.fiek.transfuzioni_gjakut.activities.Coronavirus_Cases;
import com.fiek.transfuzioni_gjakut.forms.LoginForm;

public class depoistFragment extends Fragment {

    View view;
    TextView text;
    Button btn;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.depoist_fragment, container, false);

        text = (TextView) view.findViewById(R.id.texti);
        btn = (Button) view.findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText("u kliku");
            }
        });

        return view;
    }
}
