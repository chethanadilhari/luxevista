package com.chethana.luxevista;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        holder.subServiceDuration.setText("Duration: " + subservice.getDuration());

        // Load image from URL using Glide
        Glide.with(holder.itemView.getContext())
                .load(subservice.getImageUrl())
                .apply(new RequestOptions().placeholder(R.drawable.home_bg)) // Add placeholder image
                .into(holder.subServiceImage);

        holder.subServiceBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = holder.itemView.getContext();
                Intent intent = new Intent(context, ReservationDetailsActivity.class);

                // Pass service details to the next activity
                intent.putExtra("serviceName", subservice.getName());
                intent.putExtra("servicePrice", subservice.getPrice());
                intent.putExtra("serviceDuration", subservice.getDuration());
                intent.putExtra("serviceImage", subservice.getImageUrl());

                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return subServices.size();
    }

    static class SubServiceViewHolder extends RecyclerView.ViewHolder {
        TextView subServiceName;
        ImageView subServiceImage;
        TextView subServiceDescription, subServiceDuration;

        TextView subServicePrice;
        Button subServiceBookButton;



        SubServiceViewHolder(@NonNull View itemView) {
            super(itemView);
            subServiceName = itemView.findViewById(R.id.subServiceName);
            subServiceImage = itemView.findViewById(R.id.subServiceImage);
            subServiceDescription = itemView.findViewById(R.id.subServiceDescription);
            subServicePrice = itemView.findViewById(R.id.subServicePrice);
            subServiceDuration=itemView.findViewById(R.id.subServiceDuration);
            subServiceBookButton = itemView.findViewById(R.id.subServiceBookButton);
        }

    }
}