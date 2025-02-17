package com.chethana.luxevista;

import android.content.Intent;
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


public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {

    private final ArrayList<Location> locations;

    public LocationAdapter(ArrayList<Location> locations) {
        this.locations = locations;
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.location_card, parent, false);
        return new LocationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, int position) {
        Location location = locations.get(position);
        holder.locationName.setText(location.getName());

        // Load image from URL using Glide
        Glide.with(holder.itemView.getContext())
                .load(location.getImageUrl())
                .apply(new RequestOptions().placeholder(R.drawable.home_bg)) // Add placeholder image
                .into(holder.locationImage);

        holder.itemView.setOnClickListener(v -> {
            // Create an Intent to pass data to the detailed page (Activity)
            Intent intent = new Intent(holder.itemView.getContext(), LocationDetailsActivity.class);
            intent.putExtra("locationName", location.getName());
            intent.putExtra("locationDescription", location.getDescription());
            intent.putExtra("locationImage", location.getImageUrl());
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    static class LocationViewHolder extends RecyclerView.ViewHolder {
        TextView locationName,locationDescription;
        ImageView locationImage;


        LocationViewHolder(@NonNull View itemView) {
            super(itemView);
            locationName = itemView.findViewById(R.id.locationName);
            locationImage = itemView.findViewById(R.id.locationImage);
            locationDescription=itemView.findViewById(R.id.locationDescription);
        }
    }
}
