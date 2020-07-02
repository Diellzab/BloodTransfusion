package com.fiek.transfuzioni_gjakut.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fiek.transfuzioni_gjakut.R;
import com.fiek.transfuzioni_gjakut.models.DepozitaEgjakutClass;

import java.util.List;

public class DepozitaList extends ArrayAdapter<DepozitaEgjakutClass>{
    private Activity context ;
    private List<DepozitaEgjakutClass> depozitaList;

    public DepozitaList(Activity context, List<DepozitaEgjakutClass> depozitaList) {
        super(context, R.layout.list_items_for_depozita, depozitaList);
        this.context = context;
        this.depozitaList = depozitaList;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem =inflater.inflate(R.layout.list_items_for_depozita,null,true);

        TextView textViewBloodType = (TextView) listViewItem.findViewById(R.id.list_depozita_blood_donator_bloodType);
        TextView textViewQuantity = (TextView) listViewItem.findViewById(R.id.list_depozita_blood_donator_quantity);

        DepozitaEgjakutClass depozita = depozitaList.get(position);


        textViewBloodType.setText("Blood Type : " + depozita.getTipiGjakut());

        textViewQuantity.setText("Quantity : "+depozita.getSasiaGjakut().toString() + " ml");

        return listViewItem;


    }
}
