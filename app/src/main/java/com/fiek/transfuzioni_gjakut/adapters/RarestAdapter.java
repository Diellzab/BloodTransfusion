package com.fiek.transfuzioni_gjakut.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fiek.transfuzioni_gjakut.R;
import com.fiek.transfuzioni_gjakut.models.RarestHelperClass;

import java.util.ArrayList;

public class RarestAdapter extends RecyclerView.Adapter<RarestAdapter.AdapterAllRarestViewHolder> {
    ArrayList<RarestHelperClass> mostViewedLocations;

    public RarestAdapter(ArrayList<RarestHelperClass> mostViewedLocations) {
        this.mostViewedLocations = mostViewedLocations;
    }

    @NonNull
    @Override
    public AdapterAllRarestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rared_card_design, parent, false);
        AdapterAllRarestViewHolder lvh = new AdapterAllRarestViewHolder(view);
        return lvh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAllRarestViewHolder holder, int position) {

        RarestHelperClass helperClass = mostViewedLocations.get(position);
        holder.imageView.setImageResource(helperClass.getImg());
        holder.textView.setText(helperClass.getTitle());

    }

    @Override
    public int getItemCount() {
        return mostViewedLocations.size();
    }

    public static class AdapterAllRarestViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout relativeLayout;
        ImageView imageView;
        TextView textView;

        public AdapterAllRarestViewHolder(@NonNull View itemView) {
            super(itemView);


            imageView = itemView.findViewById(R.id.rarest_image);
            textView = itemView.findViewById(R.id.rarest_title);
        }
    }


}
