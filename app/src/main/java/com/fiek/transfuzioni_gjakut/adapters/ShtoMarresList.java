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
import com.fiek.transfuzioni_gjakut.models.ShtoMarresClass;

import java.util.List;

public class ShtoMarresList extends ArrayAdapter<ShtoMarresClass> {

    private Activity context ;
    private List<ShtoMarresClass> marresList;

    public ShtoMarresList(Activity context, List<ShtoMarresClass> marresList) {
        super(context, R.layout.list_items_for_recievers, marresList);
        this.context = context;
        this.marresList = marresList;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem =inflater.inflate(R.layout.list_items_for_recievers,null,true);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.list_blood_reciever_name);
//        TextView textViewSurname = (TextView) listViewItem.findViewById(R.id.list_blood_donator_surname);
        TextView textViewEmail = (TextView) listViewItem.findViewById(R.id.list_blood_reciever_email);
        TextView textViewPhone = (TextView) listViewItem.findViewById(R.id.list_blood_reciever_phone);
        TextView textViewBloodType = (TextView) listViewItem.findViewById(R.id.list_blood_reciever_bloodType);
        TextView textViewQuantity = (TextView) listViewItem.findViewById(R.id.list_blood_reciever_quantity);
        ShtoMarresClass reciever = marresList.get(position);

        textViewName.setText("Full Name : " + reciever.getEmri() + " " + reciever.getMbiemri());
//        textViewSurname.setText();
        textViewBloodType.setText("Blood Type : " + reciever.getTipiGjakut());
        textViewEmail.setText("Email : "+reciever.getEmail());
        textViewPhone.setText("Phone : "+reciever.getTelefoni());
        textViewQuantity.setText("Quantity : "+reciever.getSasia().toString() + " ml");

        return listViewItem;
    }
}
