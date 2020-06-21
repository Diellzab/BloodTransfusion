package com.fiek.transfuzioni_gjakut;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class DonorsList extends ArrayAdapter<addDonorDataInsert> {
    private Activity context ;
    private List<addDonorDataInsert> donorList;

    public DonorsList(Activity context, List<addDonorDataInsert> donorList) {
        super(context,R.layout.list_items_for_donnors, donorList);
        this.context = context;
        this.donorList = donorList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem =inflater.inflate(R.layout.list_items_for_donnors,null,true);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.list_blood_donator_name);
        TextView textViewSurname = (TextView) listViewItem.findViewById(R.id.list_blood_donator_surname);
        TextView textViewEmail = (TextView) listViewItem.findViewById(R.id.list_blood_donator_email);
        TextView textViewPhone = (TextView) listViewItem.findViewById(R.id.list_blood_donator_phone);
        TextView textViewBloodType = (TextView) listViewItem.findViewById(R.id.list_blood_donator_bloodType);
        TextView textViewQuantity = (TextView) listViewItem.findViewById(R.id.list_blood_donator_quantity);

        addDonorDataInsert donors = donorList.get(position);

        textViewName.setText(donors.getEmri());
        textViewSurname.setText(donors.getMbiemri());
        textViewBloodType.setText(donors.getTipiGjakut());
        textViewEmail.setText(donors.getEmail());
        textViewPhone.setText(donors.getTelefoni());
//        textViewQuantity.setText(donors.getSasia());

        return listViewItem;


    }
}
