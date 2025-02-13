package com.chethana.luxevista;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;



public class SubServiceAdapter extends RecyclerView.Adapter<SubServiceAdapter.SubServiceViewHolder> {

    private final ArrayList<SubService> subServices;

    public SubServiceAdapter(ArrayList<SubService> subServices) {
        this.subServices = subServices;
    }

    @NonNull
    @Override
    public SubServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_details_card, parent, false);
        return new SubServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubServiceViewHolder holder, int position) {
        SubService subservice = subServices.get(position);
        holder.subServiceName.setText(subservice.getName());
        holder.subServiceDescription.setText(subservice.getDescription());
        holder.subServicePrice.setText("Price: " + subservice.getPrice());

        // Load image from URL using Glide
        Glide.with(holder.itemView.getContext())
                .load(subservice.getImageUrl())
                .apply(new RequestOptions().placeholder(R.drawable.home_bg)) // Add placeholder image
                .into(holder.subServiceImage);
    }


    @Override
    public int getItemCount() {
        return subServices.size();
    }

    static class SubServiceViewHolder extends RecyclerView.ViewHolder {
        TextView subServiceName;
        ImageView subServiceImage;
        TextView subServiceDescription;

        TextView subServicePrice;

        SubServiceViewHolder(@NonNull View itemView) {
            super(itemView);
            subServiceName = itemView.findViewById(R.id.subServiceName);
            subServiceImage = itemView.findViewById(R.id.subServiceImage);
            subServiceDescription = itemView.findViewById(R.id.subServiceDescription);
            subServicePrice = itemView.findViewById(R.id.subServicePrice);
        }
    }
}